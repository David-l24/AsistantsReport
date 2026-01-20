package Lógica.Entidades;

import java.time.LocalDateTime;

public class Director {
    
    private int id;
    private String nombres;
    private String apellidos;
    private String correo;
    
    public Director (int id, String nombre, String apellidos, String correo) {
        this.id = id;
        this.nombres = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public Director() {

    }

    public void generarReporteAyudantes(int id, String periodoAcademico, EstadoReporte estado, LocalDateTime fechaActual, LocalDateTime fechaCierre) {
        ReporteAyudantes reporte = new ReporteAyudantes(id, periodoAcademico, estado, fechaActual, fechaCierre);
    }


    public Ayudante registrarAyudante(Ayudante ayudante) {
        return ayudante;
    }
    public void registrarAyudantia(Ayudante ayudante) {
        
    }
    public void finalizarReporte() {
        
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
