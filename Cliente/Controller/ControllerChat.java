package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Cliente;
//import Cliente.Cliente;
import Model.Message;
import Model.MessageManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerChat implements Initializable {

    public static String GrupoAtual;

    @FXML
    private Button Buttonvoltar;

    @FXML
    private Label LabelNomeDoGrupo;

    @FXML
    private Button enviarMensagem;

    @FXML
    private TextField mensagem;

    @FXML
    private Button ButtonsairDoGrupo;

    @FXML
    private VBox vboxMensagens;

    @FXML
    private ScrollPane telaScrollPane;

    public Cliente cliente = ControllerInitial.cliente;

    public Timeline timeline;

    @FXML
    void Voltar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/TelaGroups.fxml"));
            Parent mainScreen = fxmlLoader.load();
            Stage stage = (Stage) mensagem.getScene().getWindow();
            stage.setScene(new Scene(mainScreen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void enviarMensagem(ActionEvent event) {
        String mensagemDoUsuario = mensagem.getText();
        if (mensagemDoUsuario.isEmpty()) {
            return;
        }
        ControllerInitial.gerenciadorMensagens.adicionarMensagem(GrupoAtual, cliente.Nome, mensagemDoUsuario);
        cliente.clienteUdp.SEND(GrupoAtual, cliente.Nome, mensagemDoUsuario);
        Platform.runLater(() -> {
            ExibirTodasAsMensagensNaTela(GrupoAtual);
        });
        // criarCaixinhaMensagem(cliente.Nome, mensagemDoUsuario, LocalDateTime.now());
        mensagem.clear();
    }

    @FXML
    void sairDoGrupo(ActionEvent event) {
        cliente.clienteTcp.LEAVE(GrupoAtual, cliente.Nome);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/TelaGroups.fxml"));
            Parent mainScreen = fxmlLoader.load();
            Stage stage = (Stage) mensagem.getScene().getWindow();
            stage.setScene(new Scene(mainScreen));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void receberSEND(String grupo, String remetente, String mensagemDoUsuario) {
        // ControllerInitial.gerenciadorMensagens.adicionarMensagem(grupo, remetente,
        // mensagemDoUsuario);
        // criarCaixinhaMensagem(remetente, mensagemDoUsuario, LocalDateTime.now());
        Platform.runLater(() -> {
            ControllerInitial.gerenciadorMensagens.adicionarMensagem(grupo, remetente, mensagemDoUsuario);
            ExibirTodasAsMensagensNaTela(GrupoAtual);
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        LabelNomeDoGrupo.setText(GrupoAtual);

        vboxMensagens.heightProperty().addListener((observable, oldValue, newValue) -> {
            telaScrollPane.setVvalue(1.0); // Desce para o final
        });

        new Thread(cliente.clienteUdp::receberMensagem).start();

        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            ExibirTodasAsMensagensNaTela(GrupoAtual);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        ControllerInitial.gerenciadorMensagens.addListener((MapChangeListener<String, List<Message>>) change -> {

            // if (change.wasAdded() || change.wasRemoved()) {
            Platform.runLater(() -> ExibirTodasAsMensagensNaTela(GrupoAtual));
            // }
        });

        ExibirTodasAsMensagensNaTela(GrupoAtual);

        mensagem.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                enviarMensagem(new ActionEvent()); // Chama o método enviarMensagem() quando Enter é pressionado
            }
        });
        // System.out.println("abriu");
    }

    public void setGrupoAtual(String grupoAtual) {
        this.GrupoAtual = grupoAtual;
    }

    public void ExibirTodasAsMensagensNaTela(String grupoAtual) {
        // Platform.runLater(() -> {
        vboxMensagens.getChildren().clear();
        List<Message> todasAsMensagens = ControllerInitial.gerenciadorMensagens.getMensagens(grupoAtual);
        for (Message message : todasAsMensagens) {
            Platform.runLater(
                    () -> criarCaixinhaMensagem(message.getUsuario(), message.getTexto(), message.getHora())); // System.out.println(message.getUsuario()
            // + ": " + message.getTexto());
        }
        ;

    }

    public void criarCaixinhaMensagem(String nomeUsuario, String texto, LocalDateTime date) {
        // ScrollPane p = telaScrollPane;

        cliente = ControllerInitial.cliente;

        VBox vboxMensagem = new VBox();
        vboxMensagem.setSpacing(5);

        HBox hbox = new HBox();
        Label labelUsuario = new Label(nomeUsuario);

        Label labelMensagem = new Label(texto);

        // Label data = new Label();

        vboxMensagens.setSpacing(10);
        // labelUsuario.setStyle("-fx-font-weight: bold;");
        // HBox da mensagem
        HBox hboxMensagem = new HBox();

        HBox.setHgrow(labelMensagem, Priority.ALWAYS);

        String clienteAtual = cliente.getNome();
        if (nomeUsuario.equals(clienteAtual)) {

            hboxMensagem.setAlignment(Pos.CENTER_RIGHT);
            // labelMensagem.getStyleClass().add("user");
            labelMensagem.setStyle("-fx-background-color: #6b5bde;-fx-background-radius: 100; " +
                    "-fx-background-radius: 100; -fx-border-radius: 100; -fx-text-fill: white; -fx-padding: 10 20 10 20; "
                    +
                    "fx-font-family: 'Arial';-fx-font-size: 18px; " +
                    "-fx-border-color: transparent; -fx-border-width: 2;");

        } else {

            hboxMensagem.setAlignment(Pos.CENTER_LEFT);
            labelMensagem.setStyle("-fx-background-color: #C238EA;-fx-background-radius: 100; " +
                    "-fx-background-radius: 100; -fx-border-radius: 100; -fx-text-fill: white; -fx-padding: 10 20 10 20; "
                    +
                    "fx-font-family: 'Arial';-fx-font-size: 18px; " +
                    "-fx-border-color: transparent; -fx-border-width: 2;");
            ;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        Label labelData = new Label(date.format(formatter));
        // labelData.getStyleClass().add("date");
        // labelData.setStyle("-fx-font-size: 10; -fx-text-fill: gray;");

        vboxMensagem.getChildren().addAll(labelUsuario, labelMensagem, labelData);

        vboxMensagem.setStyle("-fx-padding: 10 20 10 10;");
        hboxMensagem.getChildren().add(vboxMensagem);

        vboxMensagens.getChildren().addAll(hboxMensagem);

    }

    public void recarregarTela() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/TelaChat.fxml"));
            Parent mainScreen = fxmlLoader.load();
            Stage stage = (Stage) mensagem.getScene().getWindow();
            stage.setScene(new Scene(mainScreen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}