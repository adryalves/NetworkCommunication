package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReceiveData {

    public void ReceiveMessage(String message, Socket cliente) {
        try {
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            String mensagemDoCliente = (String) entrada.readObject();

            String[] partes = mensagemDoCliente.split("/");

            String type = partes[0];
            String groupName = partes[1];
            String name = partes[2];

            if (type.equals("1")) {
                servidor.groups.addUserToGroup(groupName, name);
            } else if (type.equals("2")) {
                servidor.groups.removeUserFromGroup(groupName, name);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
