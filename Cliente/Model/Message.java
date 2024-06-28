package Cliente.Model;

import java.time.LocalDateTime;

public class Message {
    private String usuario;
    private String texto;
    private LocalDateTime hora;

    public Message(String usuario, String texto, LocalDateTime hora) {
        this.usuario = usuario;
        this.texto = texto;
        this.hora = hora;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "usuario='" + usuario + '\'' +
                ", texto='" + texto + '\'' +
                ", hora=" + hora +
                '}';
    }
}
