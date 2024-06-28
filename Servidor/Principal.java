
public class Principal {
    public static void main(String[] args) {

        new Thread(() -> {
            Servidor servidor = new Servidor();
            servidor.establishConnection();
        }).start();

        // Servidor.groups.addUserToGroup("redes", "dry");
        // Servidor.groups.addUserToGroup("redes2", "dry");

        // ServidorUDP servidorUDP = new ServidorUDP(Servidor.groups);
        new Thread(() -> {
            ServidorUDP servidorUDP = new ServidorUDP(Servidor.groups);
            // GroupManager groupManager = new GroupManager();
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