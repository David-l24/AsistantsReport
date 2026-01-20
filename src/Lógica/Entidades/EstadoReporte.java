package Lógica.Entidades;

public enum EstadoReporte {
    SIN_REGISTRO(1), EN_EDICION(2), CERRADO(3);

    private final int código;

    EstadoReporte(int código) {
        this.código = código;
    }

    public int obtenerCódigo() {
        return código;
    }

    public static EstadoReporte obtenerPorCódigo(int código) {
        for (EstadoReporte estado : EstadoReporte.values()) {
            if (estado.obtenerCódigo() == código) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Código de estado de reporte inválido: " + código);
    }

    public static EstadoReporte obtenerPorNombre(String nombre) {
        switch (nombre) {
            case "Sin registro":
                return SIN_REGISTRO;
            case "En edición":
                return EN_EDICION;
            case "Cerrado":
                return CERRADO;
        }
        return null;
    }
}