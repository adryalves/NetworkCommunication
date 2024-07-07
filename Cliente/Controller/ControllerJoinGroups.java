package Controller;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 22/06/2024
* Ultima alteracao.: 29/06/2024
* Nome.............: ControllerJoinGroups
* Funcao...........: eh a classe que executa o programa que mostra na tela a opcao de se juntar a um grupo digitando o nome do grupo
*************************************************************** */

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
import javafx.scene.input.KeyCode;
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
        userCliente.clienteTcp.JOIN(NomeGrupo, userCliente.Nome);

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

        NomeDoGrupo.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Cliente userCliente = ControllerInitial.cliente;
                userCliente.clienteTcp.JOIN(NomeDoGrupo.getText(), userCliente.Nome);

                try {
                    ControllerChat.GrupoAtual = NomeDoGrupo.getText();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/TelaChat.fxml"));
                    Parent mainScreen = fxmlLoader.load();
                    Stage stage = (Stage) voltar.getScene().getWindow();
                    stage.setScene(new Scene(mainScreen));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
