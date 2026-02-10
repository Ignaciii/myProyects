package proyectoMaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;;

public class AlumnoDao {
    public AlumnoDao(Connection conexion) {
        this.conexion = conexion;
    }

    private Connection conexion;

    public List<Alumno> mostrarAlumnosDAO() {
        if (conexion != null) {

            List<Alumno> alumnos = new ArrayList<>();
            try (PreparedStatement pstm = conexion.prepareStatement("SELECT * FROM alumnos");
                    ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    int legajo = rs.getInt("legajo");
                    String nombre = rs.getNString("nombre");
                    Double parcial1 = rs.getDouble("parcial1");
                    Double parcial2 = rs.getDouble("parcial2");
                    alumnos.add(new Alumno(nombre, legajo, parcial1, parcial2));
                }
                return alumnos;

            } catch (SQLException e) {
                System.out.println("No se pudo conectar a la base de datos: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    public Boolean deleteAlumnoDAO(int legajoBorrar) {
        if (conexion == null) {
            return null;
        }
        String sql = "DELETE FROM alumnos WHERE legajo = ?";

        try (PreparedStatement pstm = conexion.prepareStatement(sql)) {
            pstm.setInt(1, legajoBorrar);
            int resultado = pstm.executeUpdate();
            if (resultado > 0) {
                System.out.println("Se borro con exito de la base de datos!!!");
                return true;
            } else
                System.out.println("No se encontro tal elemento en la base de datos!!");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al llevar a cabo el borrado en la base de datos: " + e.getMessage());
            return null;
        }
    }

    public Boolean agregarAlumnoBD(Alumno alumno) {
        if (conexion == null) {
            return null;
        }
        String sentencia = "INSERT INTO alumnos (legajo,nombre,parcial1,parcial2) VALUES (?,?,?,?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sentencia)) {
            stmt.setInt(1, alumno.getLegajo());
            stmt.setString(2, alumno.getNombre());
            stmt.setDouble(3, alumno.getParcial1());
            stmt.setDouble(4, alumno.getParcial2());

            stmt.executeUpdate();
            System.out.println("Listo alumno guardado con exito!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al cargar alumno en la base de datos: " + e.getMessage());
            return false;
        }

    }

    // debe tener el abmc contra base de datos DONE
    // luego de eso debe guardar en memoria
    // los datos obtenidos de la bd e impactar la lista en memoria

}
