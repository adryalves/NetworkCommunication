package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class ReceiveData {

    public void ReceiveGroupList(Socket cliente) {
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(cliente.getInputStream());
            String mensagem = (String) entrada.readObject();
            System.out.println("Mensagem do servidor: " + mensagem);
            entrada.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
