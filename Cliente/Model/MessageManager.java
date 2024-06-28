package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {

    private Map<String, List<Message>> grupos;

    public MessageManager() {
        this.grupos = new HashMap<>();
    }

    public void adicionarMensagem(String grupo, String usuario, String texto) {
        Message mensagem = new Message(usuario, texto, LocalDateTime.now());
        grupos.computeIfAbsent(grupo, k -> new ArrayList<>()).add(mensagem);
    }

    public List<Message> getMensagens(String grupo) {
        return grupos.getOrDefault(grupo, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "GerenciadorDeGrupos{" +
                "grupos=" + grupos +
                '}';
    }
}
