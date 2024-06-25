package Cliente;

public class Principal {
    public static void main(String[] args) {
        // Cliente cliente = new Cliente();
        // cliente.establishConnectionClient();
        ClienteTCP clienteTcp = new ClienteTCP("192.168.1.4");

        clienteTcp.solicitarListaDeGrupos();
        ReceiveData rd = new ReceiveData();
        rd.ReceiveGroupList(clienteTcp.getCliente());
        clienteTcp.join("redes", "dry");

        clienteTcp.solicitarListaDeGrupos();
        rd.ReceiveGroupList(clienteTcp.getCliente());

        ClienteUDP clienteUDP = new ClienteUDP();

        clienteUDP.enviarMensagemProServidor("redes", "dry", "ooi drydry2");

    }
}
