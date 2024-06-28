package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {
    private Socket cliente;
    private int porta;
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;

    public ClienteTCP(String host) {
        try {
            porta = 3322;
            cliente = new Socket(host, porta);
            saida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
            // System.out.println("ta aq");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public StringBuilder solicitarListaDeGrupos(String user) {
        try {
            String codigo = "0/" + user;
            saida.writeObject(codigo);
            saida.flush();

            // new Thread(() -> {

            // ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            StringBuilder mensagem = (StringBuilder) entrada.readObject();
            System.out.println("Lista de grupos do usuario: " + user + " : " + mensagem);
            return mensagem;
            // entrada.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new StringBuilder();
        // }).start();

    }

    public void join(String groupName, String user) {
        try {
            // ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            String mensagemParaServidor = "1/" + groupName + "/" + user;
            saida.writeObject(mensagemParaServidor);
            saida.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leave(String groupName, String user) {
        try {
            // ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            String mensagemParaServidor = "2/" + groupName + "/" + user;
            saida.writeObject(mensagemParaServidor);
            saida.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReceiveGroupList() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            String listaDeGrupos = (String) entrada.readObject();
            System.out.println("Lista de grupos do servidor: " + listaDeGrupos);
            entrada.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {

            if (saida != null)
                saida.close();
            if (cliente != null)
                cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getCliente() {
        return cliente;
    }

}
