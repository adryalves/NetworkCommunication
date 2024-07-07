package Controller;

<<<<<<< HEAD
/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 22/06/2024
* Ultima alteracao.: 29/06/2024
* Nome.............: ControllerInitial
* Funcao...........: eh a classe que executa o programa que mostra a primeira tela do programa ela que pega  eo ip do servidor e chama metodos pra fzr as conexoes
*************************************************************** */

=======
>>>>>>> a8ee0be3a6de79f9f19b9a2833e892d8f5ba26ac
import java.net.URL;
import java.util.ResourceBundle;

import Model.Cliente;
import Model.ClienteTCP;
import Model.ClienteUDP;
import Model.IPAddressValidator;
import Model.MessageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class ControllerInitial implements Initializable {

    public static MessageManager gerenciadorMensagens = new MessageManager();

    @FXML
    private TextField TextAreaNomeServidor;
    @FXML
    private TextField textAreaIpServidor;

    @FXML
    private Button buttonEnviarIpENome;

    public static Cliente cliente;

    @FXML
    private Label labelValidacao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }

    @FXML
    void EnviarIpCliente(ActionEvent event) {

        IPAddressValidator ipValidator = new IPAddressValidator();

        if (TextAreaNomeServidor.getText().isEmpty()) {

            return;
        }
        if (textAreaIpServidor.getText().isEmpty() || !ipValidator.validate(textAreaIpServidor.getText())) {
            labelValidacao.setText("Digite um IP valido");
            labelValidacao.setVisible(true);

        } else {
            String nomeCliente = TextAreaNomeServidor.getText();
            String ipServidor = textAreaIpServidor.getText();
            ClienteTCP clienteTcp = new ClienteTCP(ipServidor);
            ClienteUDP clienteUdp = new ClienteUDP(ipServidor);

            cliente = new Cliente(nomeCliente, clienteTcp, clienteUdp);

            AbrirTelaListagemGrupos();

        }

    }

    public void AbrirTelaListagemGrupos() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/TelaGroups.fxml"));
            Parent mainScreen = fxmlLoader.load();
            Stage stage = (Stage) TextAreaNomeServidor.getScene().getWindow();
            stage.setScene(new Scene(mainScreen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
