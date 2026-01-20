package Lógica.Entidades;

public class Proyecto {

    private String codigo;
    private String nombre;
    private String periodoInicio;
    private int duracionSemestres;
    private EstadoProyecto estado;
    private int numeroAyudantesPLanificados;
    private int director;

    public Proyecto (){};

    public Proyecto(String codigo, String nombre, String periodoInicio, int duracionSemestres, EstadoProyecto estado, int numeroAyudantesPlanificados) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.periodoInicio = periodoInicio;
        this.duracionSemestres = duracionSemestres;
        this.estado = estado;
        this.numeroAyudantesPLanificados = numeroAyudantesPlanificados;
    }

    public void cambiarEstado (EstadoProyecto nuevoEstado){
        this.estado = nuevoEstado;
    }

    public boolean esAprobado(){
        if (this.estado == EstadoProyecto.APROBADO){
            return true;
        }
        return false;
    }

    public boolean tieneAyudantesPLanificados(){
        if (numeroAyudantesPLanificados > 0){
            return true;
        }
        return false;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public int getDuracionSemestres() {
        return duracionSemestres;
    }

    public void setDuracionSemestres(int duracionSemestres) {
        this.duracionSemestres = duracionSemestres;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = EstadoProyecto.obtenerPorNombre(estado);
    }

    public int getNumeroAyudantesPLanificados() {
        return numeroAyudantesPLanificados;
    }

    public void setNumeroAyudantesPLanificados(int numeroAyudantesPLanificados) {
        this.numeroAyudantesPLanificados = numeroAyudantesPLanificados;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    public int getDirector() {
        return director;
    }
}
