package Lógica.DAO;

import Lógica.Conexiones.ConexionBD;
import Lógica.Entidades.ReporteAyudantes;
import Lógica.Entidades.EstadoReporte;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReporteAyudantesDAO {

    private Connection connection;

    public ReporteAyudantesDAO() {
        try {
            this.connection = ConexionBD.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }


    public List<ReporteAyudantes> obtenerTodos() throws SQLException {
        List<ReporteAyudantes> reportes = new ArrayList<>();
        String sql = "SELECT * FROM reporte_ayudante";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                reportes.add(mapear(rs));
            }
        }
        return reportes;
    }

    public ReporteAyudantes buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM reporte_ayudante WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    public void guardar(ReporteAyudantes reporte) throws SQLException {
        String sql = """
            INSERT INTO reporte_ayudante
            (periodo_academico_id, proyecto_id, estado, fecha_creacion, fecha_cierre)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, reporte.getPeriodoAcademicoId());
            stmt.setString(2, reporte.getProyectoId());
            stmt.setString(3, reporte.getEstado().name());
            stmt.setObject(4, reporte.getFechaCreacion());
            stmt.setObject(5, reporte.getFechaCierre());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    reporte.setId(keys.getInt(1));
                }
            }
        }
    }

    public void actualizarEstado(int id, EstadoReporte estado) throws SQLException {
        String sql = "UPDATE reporte_ayudante SET estado = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estado.name());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void cerrarReporte(int id) throws SQLException {
        String sql = """
            UPDATE reporte_ayudante
            SET estado = ?, fecha_cierre = ?
            WHERE id = ?
            """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, EstadoReporte.CERRADO.name());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    private ReporteAyudantes mapear(ResultSet rs) throws SQLException {
        ReporteAyudantes reporte = new ReporteAyudantes();
        reporte.setId(rs.getInt("id"));
        reporte.setPeriodoAcademicoId(rs.getString("periodo_academico_id"));
        reporte.setProyectoId(rs.getString("proyecto_id"));
        reporte.setEstado(EstadoReporte.valueOf(rs.getString("estado")));
        reporte.setFechaCreacion(
                rs.getTimestamp("fecha_creacion").toLocalDateTime()
        );
        Timestamp cierre = rs.getTimestamp("fecha_cierre");
        if (cierre != null) {
            reporte.setFechaCierre(cierre.toLocalDateTime());
        }        return reporte;
    }

    public void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
