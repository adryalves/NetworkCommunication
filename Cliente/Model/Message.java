package Model;

/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 22/06/2024
* Ultima alteracao.: 29/06/2024
* Nome.............: Message
* Funcao...........: objeto Message que representa a mensagem do usuario, quem enviou e a hora que foi enviada
*************************************************************** */

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
