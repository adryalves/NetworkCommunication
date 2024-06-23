package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Set;

public class servidor {
    /*
     * public static void main(String[] args) {
     * try {
     * InetAddress localAddress = getServerIp();
     * System.out.println("Local IP Address: " + localAddress.getHostAddress());
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     */
    public static GroupManager groups = new GroupManager();

    public void establishConnection() {
        try {
            ServerSocket server = new ServerSocket(3322);

            while (true) {

                Socket cliente = server.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                // Usando uma classe anônima para criar a thread
                new Thread(() -> {
                    try {
                        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                        String mensagemDoCliente = (String) entrada.readObject();

                        String[] partes = mensagemDoCliente.split("/");
                        String type = partes[0];

                        if (type.equals("0")) {
                            SendGroupListToClient(cliente);
                        } else if (type.equals("1") && partes.length == 3) {
                            String groupName = partes[1];
                            String user = partes[2];
                            groups.addUserToGroup(groupName, user);
                        } else if (type.equals("2") && partes.length == 3) {
                            String groupName = partes[1];
                            String user = partes[2];
                            groups.removeUserFromGroup(groupName, user);
                        }

                        entrada.close();
                        cliente.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void SendGroupListToClient(Socket cliente) {
        ObjectOutputStream saida;
        try {
            saida = new ObjectOutputStream(cliente.getOutputStream());

            Set<String> grupos = groups.listGroups();
            StringBuilder lista = new StringBuilder();
            saida.writeObject("\n");
            for (String grupo : grupos) {
                lista.append(grupo).append("\n");
            }
            saida.writeObject(lista);
            saida.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
