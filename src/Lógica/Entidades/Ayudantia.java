package Lógica.Entidades;

import java.time.LocalDateTime;

public class Ayudantia {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private LocalDateTime fechaRetiro;
    private String motivoRetiro;
    private EstadoAyudantia estado;

    public Ayudantia(LocalDateTime fechaInicio, LocalDateTime fechaRetiro,LocalDateTime fechaFin, String motivoRetiro) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaRetiro = fechaRetiro;
        this.motivoRetiro = motivoRetiro;
    }

    public Boolean esActivo() {
        return estado== EstadoAyudantia.ACTIVO;
    }

    public void registrarRetiro() {
        estado = EstadoAyudantia.RETIRADO;
    }
}
