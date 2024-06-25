package Servidor;

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
        byte[] buffer = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("mensagem recebida " + message);
                String[] partes = message.split("/");

                String grupo = partes[0];
                String remetente = partes[1];
                String mensagem = partes[2];

                Set<String> members = groups.getUsersInGroup(grupo);
                for (String member : members) {
                    if (!member.equals(remetente)) {
                        String ipDestinatario = Servidor.getClientIp(member);
                        if (ipDestinatario != null) {
                            sendMessageToEveryone(ipDestinatario, grupo, remetente, mensagem);
                        }
                    }
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToEveryone(String ipDestinatario, String grupo, String remetente, String mensagem) {
        try {
            InetAddress enderecoDestino = InetAddress.getByName(ipDestinatario);
            String mensagemParaEnviar = grupo + "/" + remetente + "/" + mensagem;
            byte[] buffer = mensagemParaEnviar.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, enderecoDestino, porta);
            socket.send(packet);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
