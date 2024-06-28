
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Servidor {
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
    private static Map<String, String> clientes = new HashMap<>();

    public void establishConnection() {
        try {
            ServerSocket server = new ServerSocket(3322);

            while (true) {

                Socket cliente = server.accept();
                String clienteIp = cliente.getInetAddress().getHostAddress();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                new Thread(() -> handleClient(cliente, clienteIp)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleClient(Socket cliente, String clienteIp) {
        try {
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());

            while (true) {
                try {
                    String mensagemDoCliente = (String) entrada.readObject();
                    System.out.println("Mensagem recebida: " + mensagemDoCliente);

                    String[] partes = mensagemDoCliente.split("/");
                    String type = partes[0];

                    switch (type) {
                        case "0":
                            String user = partes[1];
                            sendGroupListToClient(saida, user);
                            break;
                        case "1":
                            String groupName = partes[1];
                            user = partes[2];
                            groups.addUserToGroup(groupName, user);
                            clientes.put(user, clienteIp);
                            System.out.println(user + " adicionado ao grupo " + groupName);
                            break;
                        case "2":
                            groupName = partes[1];
                            user = partes[2];
                            groups.removeUserFromGroup(groupName, user);
                            clientes.remove(user);
                            break;
                        default:
                            System.out.println("Tipo de mensagem desconhecido: " + type);
                            break;
                    }
                } catch (SocketException e) {
                    // System.out.println("Conexao com o cliente " + clienteIp + " foi encerrada
                    // abruptamente");
                    // e.printStackTrace();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGroupListToClient(ObjectOutputStream saida, String userName) {
        // ObjectOutputStream saida;
        try {
            // saida = new ObjectOutputStream(cliente.getOutputStream());

            Set<String> grupos = groups.getGroupsForUser(userName);
            StringBuilder lista = new StringBuilder();
            // saida.writeObject("\n");
            for (String grupo : grupos) {
                lista.append(grupo).append("/");
            }
            saida.writeObject(lista);
            // saida.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getClientIp(String username) {
        return clientes.get(username);
    }
}