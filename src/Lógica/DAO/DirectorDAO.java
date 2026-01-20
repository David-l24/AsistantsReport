package Lógica.DAO;
import Lógica.Conexiones.ConexionBD;
//import Lógica.DTO.DirectorDTO;
import Lógica.Entidades.Director;

import java.sql.*;
import java.util.*;

public class DirectorDAO {
    private Connection connection;

    public DirectorDAO() {
        try {
            this.connection = ConexionBD.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Director> obtenerTodos() throws SQLException {
        List<Director> directores = new ArrayList<>();
        String sql = "SELECT * FROM director";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Director director = new Director();
                director.setId(rs.getInt("id"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidos(rs.getString("apellidos"));
                director.setCorreo(rs.getString("correo"));
                directores.add(director);
            }
        }
        return directores;
    }

    public Director buscarPorId(String id) throws SQLException {
        String sql = "SELECT * FROM director WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Director director = new Director();
                    director.setId(rs.getInt("id"));
                    director.setNombres(rs.getString("nombres"));
                    director.setApellidos(rs.getString("apellidos"));
                    director.setCorreo(rs.getString("correo"));


                    return director;
                }
            }
        }
        return null;
    }

    public void guardar(Director director) throws SQLException {
        String sql = "INSERT INTO director (nombres, apellidos, id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, director.getNombres());
            stmt.setString(2, director.getApellidos());
            stmt.setInt(3, director.getId());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    director.setId(generatedKeys.getInt(1));
                }
            }
        }catch (SQLException e){
            throw new SQLException("Director ya registrado");
        }
    }

    public void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void actualizarCorreo(String id, String correo) throws SQLException {
        String sql = "UPDATE director SET correo = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, id);
            stmt.executeUpdate();
        }
    }

}

