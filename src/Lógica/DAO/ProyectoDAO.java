package Lógica.DAO;

import Lógica.Conexiones.ConexionBD;
//import Lógica.DTO.ClienteDTO;
import Lógica.Entidades.Proyecto;

import java.sql.*;
import java.util.*;

public class ProyectoDAO {
    private Connection connection;

    public ProyectoDAO() {
        try {
            this.connection = ConexionBD.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Proyecto> obtenerTodos() throws SQLException {
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM proyecto ORDER BY fecha_registro DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setCodigo(rs.getString("codigo"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setPeriodoInicio(rs.getString("periodo_inicio"));
                proyecto.setDuracionSemestres(rs.getInt("duracion_semestres"));
                proyecto.setEstado(rs.getString("estado"));
                proyecto.setNumeroAyudantesPLanificados(rs.getInt("numero_ayudantes_planificados"));
                proyecto.setDirector(rs.getInt("director"));
                proyectos.add(proyecto);
            }
        }
        return proyectos;
    }

    public void guardar(Proyecto proyecto) throws SQLException {
        String sql = "INSERT INTO proyecto (codigo, nombre, periodo_inicio, duracion_semestres, estado, numero_ayudantes, director_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, proyecto.getCodigo());
            stmt.setString(2, proyecto.getNombre());
            stmt.setString(3, proyecto.getPeriodoInicio());
            stmt.setInt(4, proyecto.getDuracionSemestres());
            stmt.setString(5, String.valueOf(proyecto.getEstado()));
            stmt.setInt(6, proyecto.getNumeroAyudantesPLanificados());
            stmt.setInt(7, proyecto.getDirector());

            stmt.executeUpdate();

        }catch (SQLException e){
            throw new SQLException("Proyecto ya registrado");
        }
    }

    public void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void actualizarCelular(String cédula, String teléfono) throws SQLException {
        String sql = "UPDATE proyecto SET estado = ? WHERE cedula = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, teléfono);
            stmt.setString(2, cédula);
            stmt.executeUpdate();
        }
    }

    public void actualizarCorreo(String cédula, String correo) throws SQLException {
        String sql = "UPDATE clientes SET email = ? WHERE cedula = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, cédula);
            stmt.executeUpdate();
        }
    }

    public void actualizarHistoria(String cédula, String historia) throws SQLException {
        String sql = "UPDATE clientes SET historia = ? WHERE cedula = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, historia);
            stmt.setString(2, cédula);
            stmt.executeUpdate();
        }
    }

    public void actualizarCondicionesMedicas(String cédula, String condicionesMedicas) throws SQLException {
        String sql = "UPDATE clientes SET condiciones_medicas = ? WHERE cedula = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, condicionesMedicas);
            stmt.setString(2, cédula);
            stmt.executeUpdate();
        }
    }

    public void actualizarActivo(String cédula, boolean activo) throws SQLException {
        String sql = "UPDATE clientes SET activo = ? WHERE cedula = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, activo);
            stmt.setString(2, cédula);
            stmt.executeUpdate();
        }
    }

    public String obtenerNúmeroDeClientesActivos() throws SQLException {
        String sql = "SELECT COUNT (*) FROM clientes WHERE activo = true";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return String.valueOf(rs.getInt(1));
            }else {
                return null;
            }
        }
    }

    public List<Proyecto> obtenerProyectoPorPeriodo(String periodo)  throws SQLException {
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM proyecto WHERE activo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, periodo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setCodigo(rs.getString("codigo"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setPeriodoInicio(rs.getString("periodo_inicio"));
                proyecto.setDuracionSemestres(rs.getInt("duracion_semestres"));
                proyecto.setEstado(rs.getString("estado"));
                proyecto.setNumeroAyudantesPLanificados(rs.getInt("numero_ayudantes_planificados"));
                proyecto.setDirector(rs.getInt("director"));
                proyectos.add(proyecto);
            }
        }
        return proyectos;
    }
}
