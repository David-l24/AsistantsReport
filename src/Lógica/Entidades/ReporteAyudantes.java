package Lógica.Entidades;

import java.time.LocalDateTime;
import java.time.LocalDateTime;

public class ReporteAyudantes {

    private int id;
    private String periodoAcademicoId;
    private String proyectoId;
    private EstadoReporte estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCierre;

    public ReporteAyudantes() {
    }

    public ReporteAyudantes(int id, String periodoAcademico, EstadoReporte estado, LocalDateTime fechaActual, LocalDateTime fechaCierre) {
    this.id = id;
    this.periodoAcademicoId = periodoAcademico;
    this.estado = estado;
    this.fechaCreacion = fechaActual;
    this.fechaCierre = fechaCierre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPeriodoAcademicoId() { return periodoAcademicoId; }
    public void setPeriodoAcademicoId(String periodoAcademicoId) {
        this.periodoAcademicoId = periodoAcademicoId;
    }

    public String getProyectoId() { return proyectoId; }
    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }

    public EstadoReporte getEstado() { return estado; }
    public void setEstado(EstadoReporte estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
}
