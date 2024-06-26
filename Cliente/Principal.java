package Cliente;

public class Principal {
    public static void main(String[] args) {
        // Cliente cliente = new Cliente();
        // cliente.establishConnectionClient();
        // ClienteTCP clienteTcp = new ClienteTCP("192.168.1.4");
        // while (true) {

        ClienteTCP clienteTCP = new ClienteTCP("127.0.0.1");

        // Solicitar lista de grupos
        clienteTCP.solicitarListaDeGrupos();

        // Entrar nos grupos
        clienteTCP.join("redes", "dry");
        clienteTCP.join("redes2", "mily");

        // Solicitar lista de grupos novamente
        clienteTCP.solicitarListaDeGrupos();

        // Cliente UDP
        ClienteUDP clienteUDP = new ClienteUDP();

        new Thread(clienteUDP::receberMensagem).start();

        // Enviar mensagem ao servidor
        clienteUDP.enviarMensagemProServidor("redes", "dry", "ooi drydry2");

        // Iniciar thread para receber mensagens UDP

        // Aguardar um tempo antes de encerrar (opcional)

    }
}
