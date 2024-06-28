package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Cliente;
import Model.ClienteTCP;
import Model.ClienteUDP;
import Model.IPAddressValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerInitial implements Initializable {

    @FXML
    private TextField TextAreaNomeServidor;
    @FXML
    private TextField textAreaIpServidor;

    @FXML
    private Button buttonEnviarIpENome;

    public static Cliente cliente;

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
            return;

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
