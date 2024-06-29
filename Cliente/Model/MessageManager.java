package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class MessageManager {

    private ObservableMap<String, List<Message>> grupos;

    public MessageManager() {
        this.grupos = FXCollections.observableHashMap();
    }

    public void adicionarMensagem(String grupo, String usuario, String texto) {
        Message mensagem = new Message(usuario, texto, LocalDateTime.now());
        // grupos.computeIfAbsent(grupo, k -> new ArrayList<>()).add(mensagem);
        // grupos.put(grupo, grupos.get(grupo));
        grupos.computeIfAbsent(grupo, k -> FXCollections.observableArrayList()).add(mensagem);

    }

    public List<Message> getMensagens(String grupo) {
        return grupos.getOrDefault(grupo, new ArrayList<>());
    }

    public void addListener(MapChangeListener<String, List<Message>> listener) {
        grupos.addListener(listener);
    }

    @Override
    public String toString() {
        return "GerenciadorDeGrupos{" +
                "grupos=" + grupos +
                '}';
    }
}
