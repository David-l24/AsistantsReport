package Lógica.Entidades;

public enum EstadoProyecto {
    EN_REVISION(1), APROBADO(2), NO_APROBADO(3), FINALIZADO(4);

    private final int código;

    EstadoProyecto(int código) {
        this.código = código;
    }

    public int obtenerCódigo() {
        return código;
    }

    public static EstadoProyecto obtenerPorCódigo(int código) {
        for (EstadoProyecto estado : EstadoProyecto.values()) {
            if (estado.obtenerCódigo() == código) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Código de estado de proyecto inválido: " + código);
    }

    public static EstadoProyecto obtenerPorNombre(String nombre) {
        switch (nombre) {
            case "En revisión":
                return EN_REVISION;
            case "Aprobado":
                return APROBADO;
            case "No aprobado":
                return NO_APROBADO;
            case "Finalizado":
                return FINALIZADO;
        }
        return null;
    }
}