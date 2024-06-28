package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Cliente;
//import Cliente.Cliente;
import Model.MessageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerJoinGroups implements Initializable {

    @FXML
    private TextField NomeDoGrupo;

    @FXML
    private Button botaoEntrarGrupo;

    @FXML
    private Button voltar;

    @FXML
    void EntrarGrupo(ActionEvent event) {
        String NomeGrupo = NomeDoGrupo.getText();
        if (NomeGrupo.isEmpty()) {
            return;
        }
        Cliente userCliente = ControllerInitial.cliente;
        userCliente.clienteTcp.join(NomeGrupo, userCliente.Nome);

        try {
            ControllerChat.GrupoAtual = NomeGrupo;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/TelaChat.fxml"));
            Parent mainScreen = fxmlLoader.load();
            Stage stage = (Stage) voltar.getScene().getWindow();
            stage.setScene(new Scene(mainScreen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void voltarTelaListarGrupos(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/TelaGroups.fxml"));
            Parent mainScreen = fxmlLoader.load();
            Stage stage = (Stage) voltar.getScene().getWindow();
            stage.setScene(new Scene(mainScreen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}
