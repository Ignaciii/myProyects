package ignacio;

import java.sql.*;
import java.sql.ResultSet;
import java.util.List;

import java.util.ArrayList;

public class SesionDAO {
    public SesionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    private Connection conexion;

    public SesionEntrenamiento insertarEnBaseDeDatos(String terreno, Double distancia, Double duracionTotal) {
        // CURDATE() pone la fecha de hoy automáticamente

        try (PreparedStatement pstmt = conexion
                .prepareStatement("INSERT INTO sesiones (distancia, tiempo, terreno) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);) {
            // Llenamos los '?' con los datos del objeto
            pstmt.setDouble(1, distancia);
            pstmt.setDouble(2, duracionTotal);
            pstmt.setString(3, terreno);

            pstmt.executeUpdate(); // ¡Gatillamos la inserción!

            try (
                    ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("[DB] Sesión guardada con éxito.");
                    return new SesionEntrenamiento(id, terreno, distancia, duracionTotal);
                }
            }

        } catch (SQLException e) {
            System.out.println("[DB] Error al insertar datos: " + e.getMessage());
        }
        return null;

    }

    public Boolean borrarDeBaseDeDatos(int id) {

        try (PreparedStatement pstmt = conexion.prepareStatement("DELETE FROM sesiones WHERE id = ?");) {
            pstmt.setInt(1, id);
            int resultado = pstmt.executeUpdate();
            if (resultado > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        }
        return false;
    }

    public List<SesionEntrenamiento> mostrarDatos() {
        String sql = "SELECT * FROM sesiones";
        List<SesionEntrenamiento> lista = new ArrayList<>();
        try (PreparedStatement pstmt = conexion.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt(1);
                Double distancia = rs.getDouble(2);
                Double duracion = rs.getDouble(3);
                String terreno = rs.getString(4);
                SesionEntrenamiento sesionEntrenamiento = new SesionEntrenamiento(id, terreno, distancia, duracion);
                lista.add(sesionEntrenamiento);

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}

// pide la conexion en el main como paremetro para el metodo