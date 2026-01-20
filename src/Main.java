import Lógica.DAO.ReporteAyudantesDAO;
import Lógica.Entidades.ReporteAyudantes;
import Lógica.Entidades.EstadoReporte;

import java.time.LocalDateTime;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            ReporteAyudantesDAO dao = new ReporteAyudantesDAO();

            // 🔹 1. CREAR REPORTE
            ReporteAyudantes nuevo = new ReporteAyudantes();
            nuevo.setPeriodoAcademicoId("2025B");
            nuevo.setProyectoId("PROY001");
            nuevo.setEstado(EstadoReporte.EN_EDICION);
            nuevo.setFechaCreacion(LocalDateTime.now());
            nuevo.setFechaCierre(null);

            dao.guardar(nuevo);
            System.out.println("✅ Reporte creado con ID: " + nuevo.getId());

            // 🔹 2. BUSCAR POR ID
            ReporteAyudantes encontrado = dao.buscarPorId(nuevo.getId());
            if (encontrado != null) {
                System.out.println("📌 Reporte encontrado:");
                System.out.println("ID: " + encontrado.getId());
                System.out.println("Periodo: " + encontrado.getPeriodoAcademicoId());
                System.out.println("Proyecto: " + encontrado.getProyectoId());
                System.out.println("Estado: " + encontrado.getEstado());
                System.out.println("Creado: " + encontrado.getFechaCreacion());
            }

            // 🔹 3. ACTUALIZAR ESTADO
            dao.actualizarEstado(nuevo.getId(), EstadoReporte.CERRADO);
            System.out.println("🔄 Estado actualizado a CERRADO");

            // 🔹 4. CERRAR REPORTE (fecha + estado)
            dao.cerrarReporte(nuevo.getId());
            System.out.println("🔒 Reporte cerrado");

            // 🔹 5. LISTAR TODOS
            System.out.println("\n📋 LISTA DE REPORTES:");
            List<ReporteAyudantes> reportes = dao.obtenerTodos();
            for (ReporteAyudantes r : reportes) {
                System.out.println(
                        r.getId() + " | " +
                                r.getPeriodoAcademicoId() + " | " +
                                r.getProyectoId() + " | " +
                                r.getEstado()
                );
            }

            dao.cerrarConexion();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
