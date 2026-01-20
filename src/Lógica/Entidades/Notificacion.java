package Lógica.Entidades;

import java.time.LocalDateTime;

public class Notificacion {
    private String tipo;
    private String destinatario;
    private LocalDateTime fechaEnvio;
    private String contenido;
    private String estado;


    public Notificacion(String tipo, String destinatario, String contenido, String estado) {
        this.tipo = tipo;
        this.destinatario = destinatario;
        this.contenido = contenido;
        this.estado = estado;
    }


}
