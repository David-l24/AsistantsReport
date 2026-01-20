package Lógica.Entidades;

import java.util.Date;


public class PeriodoAcademico {
    private String codigo;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaLimiteRegistro;


    public PeriodoAcademico(){
    }

    public PeriodoAcademico(String codigo, String nombre, Date fechaInicio, Date fechaFin, Date fechaLimiteRegsitro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaLimiteRegistro = fechaLimiteRegsitro;
    }

    public boolean esActivo(){
        Date fechaActual = new Date();
        return !fechaActual.after(fechaInicio) && !fechaActual.before(fechaFin);
    }

    public int calacularDiasRestantes(){
        Date fechaActual = new Date();

        if(fechaActual.after(fechaLimiteRegistro)){
            return 0;
        }

        long diasRestantes = fechaActual.getTime() - fechaLimiteRegistro.getTime();
        return (int) diasRestantes / (24 * 60 * 60 * 1000);
    }
}
