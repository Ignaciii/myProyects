package ignacio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    // Datos de la base (Asegurate que el nombre coincida con la que creaste)
    private static final String URL = "jdbc:mysql://localhost:3306/app_correr";
    private static final String USER = "root";
    private static final String PASS = "root"; // <-- Pone tu clave de MySQL

    public static Connection conectar() {
        Connection conexion = null;
        try {
            // Intentamos establecer el vínculo
            conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("¡Conectado a la base!");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return conexion;
    }

    public static void crearTablaSesiones(Connection conexion) {
        if (conexion == null)
            return;

        // El SQL con el "IF NOT EXISTS" es clave para que no explote la segunda vez
        String sql = "CREATE TABLE IF NOT EXISTS sesiones (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "distancia DECIMAL(5,2), " +
                "tiempo DECIMAL(5,2), " +
                "terreno VARCHAR(50)" +
                ") ENGINE=InnoDB;";

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
            System.out.println("¡Chequeo de tabla completado! Todo en orden en la base.");
        } catch (SQLException e) {
            System.out.println("Se nos pinchó un hernio creando la tabla: " + e.getMessage());
        }
    }
}