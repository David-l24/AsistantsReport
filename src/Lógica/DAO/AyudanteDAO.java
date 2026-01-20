package Lógica.DAO;

import Lógica.Conexiones.ConexionBD;
//import Lógica.DTO.ClienteDTO;
import Lógica.Entidades.Ayudante;

import java.sql.*;
import java.util.*;

public class AyudanteDAO {
    private Connection connection;

    public AyudanteDAO() {
        try {
            this.connection = ConexionBD.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ayudante> obtenerTodos() throws SQLException {
        List<Ayudante> ayudantes = new ArrayList<>();
        String sql = "SELECT * FROM ayudante";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ayudante ayudante = new Ayudante();
                ayudante.setIdentificacion(rs.getString("identiicacion"));
                ayudante.setNombres(rs.getString("nombres"));
                ayudante.setApellidos(rs.getString("apellidos"));
                ayudante.setCorreo(rs.getString("correo"));
                ayudante.setCarrera(rs.getString("carrera"));
                ayudante.setSemestre(rs.getInt("nivel_semestre"));
                ayudantes.add(ayudante);
            }
        }
        return ayudantes;
    }

    public Ayudante buscarPorIdentificacion(String identificacion) throws SQLException {
        String sql = "SELECT * FROM ayudantes WHERE identificacion = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, identificacion);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Ayudante ayudante = new Ayudante();
                    ayudante.setIdentificacion(rs.getString("identificacion"));
                    ayudante.setNombres(rs.getString("nombres"));
                    ayudante.setApellidos(rs.getString("apellidos"));
                    ayudante.setCorreo(rs.getString("correo"));
                    ayudante.setCarrera(rs.getString("carrera"));
                    ayudante.setSemestre(rs.getInt("nivel_semestre"));

                    return ayudante;
                }
            }
        }
        return null;
    }

    public void guardar(Ayudante ayudante) throws SQLException {
        String sql = "INSERT INTO ayudantes (identificacion, nombres, apellidos, correo, carrera, semestre) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ayudante.getIdentificacion());
            stmt.setString(2, ayudante.getNombres());
            stmt.setString(3, ayudante.getApellidos());
            stmt.setString(4, ayudante.getCorreo());
            stmt.setString(5, ayudante.getCarrera());
            stmt.setInt   (6, ayudante.getSemestre());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ayudante.setIdentificacion(String.valueOf(generatedKeys.getInt(1)));
                }
            }
        }catch (SQLException e){
            throw new SQLException("Cliente ya registrado");
        }
    }

    public void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
