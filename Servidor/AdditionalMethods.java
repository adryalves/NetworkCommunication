
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 22/06/2024
* Ultima alteracao.: 29/06/2024
* Nome.............: AdditionalMethods
* Funcao...........: classe com metodo para retornar o ip da maquina rodando o servidor
*************************************************************** */

public class AdditionalMethods {
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
