package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class cliente {
    public void establishConnectionClient() {
        int porta = 3322;
        String host = "127.0.0.1";
        // String host = "192.168.1.4";
        try {
            Socket cliente = new Socket(host, porta);
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            String mensagem = (String) entrada.readObject();
            System.out.println("Mensagem do servidor: " + mensagem);
            entrada.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}