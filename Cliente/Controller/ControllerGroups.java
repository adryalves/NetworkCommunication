package Controller;

import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import Model.Cliente;
import Model.ReceiveData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControllerGroups implements Initializable {

    @FXML
    private Label ipUsuario;

    @FXML
    private Button entrarGrupo;

    @FXML
    private VBox vboxDados;

    ReceiveData rd = new ReceiveData();

    @FXML
    void entrarGrupo(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/TelaJoinGroups.fxml"));
            Parent mainScreen = fxmlLoader.load();
            Stage stage = (Stage) vboxDados.getScene().getWindow();
            stage.setScene(new Scene(mainScreen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarGrupos() {
        Cliente userCliente = ControllerInitial.cliente;
        StringBuilder listaGruposSb = userCliente.clienteTcp.solicitarListaDeGrupos(userCliente.Nome);
        String listaGrupos = listaGruposSb.toString();

        List<String> nomesGrupos = new ArrayList<>(Arrays.asList(listaGrupos.split("/")));

        if (nomesGrupos.isEmpty() || listaGrupos.isEmpty()) {
            Image img = new Image("View/nadaencontrado.png");
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(90); // Definir largura desejada
            imageView.setPreserveRatio(true); // Manter proporções da imagem
            Label label = new Label("Nenhum grupo por aqui ainda.");

            // Adicionar ImageView e Label à VBox
            vboxDados.getChildren().addAll(imageView, label);
        } else {
            for (String nomeGrupo : nomesGrupos) {
                HBox grupo = new HBox();
                Label titulo = new Label();
                titulo.setText(nomeGrupo);
                grupo.getChildren().add(titulo);
                grupo.setStyle("-fx-background-color: #E6E6FA; " +
                        "-fx-background-radius: 10; " +
                        "-fx-border-radius: 10; " +
                        "-fx-padding: 10;" +
                        "-fx-margin-bottom: 10;" +
                        "-fx-margin-top: 10;" +
                        "-fx-cursor: hand;");

                titulo.setStyle("-fx-font-family: Georgia; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #6B5BDE;");
                vboxDados.getChildren().add(grupo);

                grupo.setOnMouseClicked(event -> {

                    System.out.println("Clicou no grupo: " + nomeGrupo);
                    irParaTelaGrupo(nomeGrupo);
                });

            }
        }

    }

    public void irParaTelaGrupo(String nomeGrupo) {
        try {
            ControllerChat.GrupoAtual = nomeGrupo;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/TelaChat.fxml"));
            Parent mainScreen = fxmlLoader.load();
            Stage stage = (Stage) vboxDados.getScene().getWindow();
            stage.setScene(new Scene(mainScreen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listarGrupos();

    }

}
