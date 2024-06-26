package Cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {
    private DatagramSocket socket;
    private String servidorIp;

    public ClienteUDP() {
        try {
            socket = new DatagramSocket(3323);
            // servidorIp = "127.0.0.1";
            servidorIp = "192.168.1.4";
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void enviarMensagemProServidor(String grupo, String usuario, String mensagem) {
        try {
            InetAddress endereco = InetAddress.getByName(servidorIp);
            String mensagemParaEnviar = grupo + "/" + usuario + "/" + mensagem;
            byte[] buffer = mensagemParaEnviar.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, endereco, 3323);
            socket.send(packet);
            System.out.println("Mensagem enviada pro servidor");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void receberMensagem() {
        byte[] buffer = new byte[1024];

        while (true) {
            try {
                // System.out.println("chegou aq");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                // System.out.println("chegou aq");
                String mensagemRecebida = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensagem recebida: " + mensagemRecebida);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}