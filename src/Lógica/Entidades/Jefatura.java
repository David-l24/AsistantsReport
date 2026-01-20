package Lógica.Entidades;

public class Jefatura {

    private String identificacion;
    private String nombre;
    private String correo;

    public void registrarProyecto(String codigo, String nombre, String periodoInicio, int duracionSemsetres, EstadoProyecto estado, int numeroAyudantesPlanificados) {
        Proyecto proyecto = new Proyecto(codigo, nombre, periodoInicio, duracionSemsetres, estado, numeroAyudantesPlanificados);
    }

    public void actualizarEstado(Proyecto proyecto, EstadoProyecto estado) {
        proyecto.cambiarEstado(estado);
    }

    public void consultarReporte() {

    }


}
