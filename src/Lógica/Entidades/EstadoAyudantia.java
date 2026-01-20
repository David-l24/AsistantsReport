package Lógica.Entidades;

public enum EstadoAyudantia {
    ACTIVO(1), RETIRADO(2), INACTIVO(3);

    private final int código;

    EstadoAyudantia(int código) {
        this.código = código;
    }

    public int obtenerCódigo() {
        return código;
    }

    public static EstadoAyudantia obtenerPorCódigo(int código) {
        for (EstadoAyudantia estado : EstadoAyudantia.values()) {
            if (estado.obtenerCódigo() == código) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Código de estado de ayudantía inválido: " + código);
    }

    public static EstadoAyudantia obtenerPorNombre(String nombre) {
        switch (nombre) {
            case "Activo":
                return ACTIVO;
            case "Retirado":
                return RETIRADO;
            case "Inactivo":
                return INACTIVO;
        }
        return null;
    }
}