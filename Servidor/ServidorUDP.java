
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Set;

public class ServidorUDP {
    private DatagramSocket socket;
    private GroupManager groups;
    private int porta;

    public ServidorUDP(GroupManager groups) {

        try {
            porta = 3323;
            socket = new DatagramSocket(porta);
            this.groups = groups;
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void EstablishConnectionUDP() {
        System.out.println("Servidor UDP rodando na porta " + porta);
        // socket = new DatagramSocket(porta);
        receiveMessages();

    }

    public void receiveMessages() {
        byte[] buffer = new byte[1024];
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensagem recebida: " + message);

                String[] partes = message.split("/");
                if (partes.length >= 3) {
                    String grupo = partes[0];
                    String remetente = partes[1];
                    String mensagemTexto = partes[2];

                    Set<String> members = groups.getUsersInGroup(grupo);
                    for (String member : members) {
                        if (!member.equals(remetente)) {
                            String ipDestinatario = Servidor.getClientIp(member);
                            // String ipDestinatario = ;
                            if (ipDestinatario != null) {
                                sendMessage(ipDestinatario, grupo, remetente, mensagemTexto);

                            }
                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String ipDestinatario, String grupo, String remetente, String mensagem) {
        try {
            InetAddress enderecoDestino = InetAddress.getByName(ipDestinatario);
            String mensagemParaEnviar = grupo + "/" + remetente + "/" + mensagem;
            byte[] buffer = mensagemParaEnviar.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, enderecoDestino, porta);
            socket.send(packet);
            System.out.println("Mensagem encaminhada");
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
