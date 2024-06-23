package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

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
}
