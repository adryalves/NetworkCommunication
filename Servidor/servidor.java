package Servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

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

    public void establishConnection() {
        try {
            ServerSocket server = new ServerSocket(3322);
            Socket cliente = server.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

            String mensagem = "Olá, cliente!";
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            saida.flush();
            saida.writeObject(mensagem);
            saida.close();
            cliente.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // conseguir pegar o IP que o servidor esta rodando pro cliente
    public static InetAddress getServerIp() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (!address.isLoopbackAddress() && address instanceof Inet4Address) {
                    if (networkInterface.isUp() && !networkInterface.isVirtual()
                            && !networkInterface.isPointToPoint()) {
                        return address;
                    }
                }
            }
        }
        throw new SocketException("No suitable network interface found");
    }
}
