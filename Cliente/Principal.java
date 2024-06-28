
import Controller.ControllerChat;
import Controller.ControllerGroups;
import Controller.ControllerInitial;
import Controller.ControllerJoinGroups;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application {
    public static void main(String[] args) {
        launch(args);
        /*
         * ClienteTCP clienteTCP = new ClienteTCP("192.168.1.4");
         * 
         * // Solicitar lista de grupos
         * clienteTCP.solicitarListaDeGrupos();
         * 
         * // Entrar nos grupos
         * clienteTCP.join("redes", "dry");
         * // clienteTCP.join("redes2", "mily");
         * 
         * // Solicitar lista de grupos novamente
         * clienteTCP.solicitarListaDeGrupos();
         * 
         * // Cliente UDP
         * ClienteUDP clienteUDP = new ClienteUDP();
         * 
         * new Thread(clienteUDP::receberMensagem).start();
         * 
         * // Enviar mensagem ao servidor
         * clienteUDP.enviarMensagemProServidor("redes", "dry", "ooi drydry2");
         * 
         * // Iniciar thread para receber mensagens UDP
         * 
         * // Aguardar um tempo antes de encerrar (opcional)
         */
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/TelaInitial.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false); // para nao ser possivel aumentar de tamanho
        stage.setTitle("ZipZop"); // para declarar o titulo do
        // program
        // css no meu projeto
        // stage.getIcons().add(new Image("view/icons8-spaghetti-96.png"));

        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show(); // rodar e iniciar a tela

    }
}
