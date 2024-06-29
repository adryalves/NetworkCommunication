package Model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Controller.ControllerChat;
import Controller.ControllerInitial;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClienteUDP {
    private DatagramSocket socket;
    private String servidorIp;
    private ControllerChat CC = new ControllerChat();

    public ClienteUDP(String servidorIp) {
        try {
            socket = new DatagramSocket(3323);
            // servidorIp = "127.0.0.1";
            this.servidorIp = servidorIp;
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void SEND(String grupo, String usuario, String mensagem) {
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
                tratarMensagemRecebida(mensagemRecebida);
                // tratarMensagemRecebida(mensagemRecebida);
                System.out.println("Mensagem recebida: " + mensagemRecebida);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void tratarMensagemRecebida(String message) {
        String[] partes = message.split("/");
        String grupo = "";
        String remetente = "";
        String mensagemTexto = "";
        if (partes.length >= 3) {
            grupo = partes[0];
            remetente = partes[1];
            mensagemTexto = partes[2];
        }
        String grupoFinal = grupo;
        String remetenteFinal = remetente;
        String mensagemFinal = mensagemTexto;
        ControllerInitial.gerenciadorMensagens.adicionarMensagem(grupoFinal, remetenteFinal,
                mensagemFinal);

        // CC.receberSEND(grupoFinal, remetenteFinal, mensagemFinal);

    }
}