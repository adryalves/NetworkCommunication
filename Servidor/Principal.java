package Servidor;

public class Principal {
    public static void main(String[] args) {

        new Thread(() -> {
            Servidor servidor = new Servidor();
            servidor.establishConnection();
        }).start();

        new Thread(() -> {
            // GroupManager groupManager = new GroupManager();
            ServidorUDP servidorUDP = new ServidorUDP(Servidor.groups);
            servidorUDP.EstablishConnectionUDP();
        }).start();

        /*
         * Servidor servidor = new Servidor();
         * servidor.establishConnection();
         * 
         * GroupManager groupManager = new GroupManager();
         * 
         * ServidorUDP servidorUDP = new ServidorUDP(Servidor.groups);
         * // while (true) {
         * servidorUDP.EstablishConnectionUDP();
         * // }
         * }
         */
        // ServidorUDP servidorUDP = new ServidorUDP(Servidor.groups);

        // servidorUDP.EstablishConnectionUDP();
    }
}