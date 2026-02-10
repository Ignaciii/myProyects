package proyectoMaven;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/app_tareav3";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection crearConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion exitosa");

        } catch (SQLException e) {
            System.out.println("Ocurrio un problema: " + e.getMessage());

        }

        return conexion;

    }

    public static void inicializarBD(Connection conexion) {
        if (conexion == null)
            return;

        String sentencia = "CREATE TABLE IF NOT EXISTS alumnos (legajo int PRIMARY KEY, nombre VARCHAR(40), parcial1 DOUBLE, parcial2 DOUBLE) ENGINE=InnoDB";
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sentencia);
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }

}
