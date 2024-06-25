package Servidor;

public class Principal {
    public static void main(String[] args) {

        Servidor servidor = new Servidor();
        servidor.establishConnection();

        ServidorUDP servidorUDP = new ServidorUDP(Servidor.groups);

        servidorUDP.EstablishConnectionUDP();
    }
}
