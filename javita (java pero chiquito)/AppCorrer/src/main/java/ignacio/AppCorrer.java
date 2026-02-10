package ignacio;

import java.util.Scanner;
import java.util.Locale; // Para que el 1.41 entre como piña
import java.sql.Connection;
import java.sql.SQLException;

public class AppCorrer {
    public AppCorrer() {
    }

    public static void main(String[] args) {
        // 1. Configuramos el Scanner con punto decimal
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        // 2. Abrimos la conexión y preparamos la tabla
        Connection conn = Conexion.conectar();
        if (conn != null) {
            Conexion.crearTablaSesiones(conn);
        }

        // 3. Inicializamos el Repositorio con la conexión
        // El "que sabe" de los datos ahora tiene el caño a la DB
        SesionDAO sesionDAO = new SesionDAO(conn);
        RepositorioSesiones repositorio = new RepositorioSesiones(sesionDAO);
        MenuOpciones.mostrarOpciones();
        System.out.print("Ingrese una opcion para comenzar: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        while (opcion != 8) {
            switch (opcion) {
                case 1:

                    try {
                        cargarSesiones(repositorio, scanner);
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                    }
                    break;

                case 2:
                    visualizarSesiones(repositorio);

                    break;

                case 3:
                    System.out.print("Ingrese el numero de sesion que desea borrar: ");
                    try {
                        int idBorrar = scanner.nextInt();
                        scanner.nextLine();
                        repositorio.borrarSesion(idBorrar);

                    } catch (Exception e) {
                        System.out.println("Error al borrar: " + e.getMessage());
                    }
                    break;

            }
            System.out.print("Que desea hacer ahora: ");
            opcion = scanner.nextInt();
        }

        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }

        } catch (SQLException e) {
            System.out.println("No se pudo cerrar la conexion: " + e.getMessage());
        } finally {
            try {
                scanner.close();
            } catch (Exception e) {
                System.out.println("No se pudo cerrar el scanner: " + e.getMessage());
            }
        }

    }

    // 4. Lógica de carga y visualización

    public static void cargarSesiones(RepositorioSesiones repositorio, Scanner scanner) {
        String terreno;
        Double distancia, duracionTotal;

        // Actualizamos el mensaje para no confundir: ahora es con PUNTO
        System.out.println("Vamos a cargar una sesion ahora (RECORDAR: decimales con PUNTO ',')");

        System.out.print("distancia recorrida (km): ");
        distancia = scanner.nextDouble();
        System.out.print("duracion total de la sesion (en horas): ");
        duracionTotal = scanner.nextDouble();

        scanner.nextLine(); // Limpiamos buffer
        System.out.print("Tipo de terreno (llano / desnivel): ");
        terreno = scanner.nextLine();

        repositorio.agregarSesion(terreno, distancia, duracionTotal);

    }

    public static void visualizarSesiones(RepositorioSesiones repositorio) {
        for (SesionEntrenamiento s : repositorio) {
            System.out.println(s.toString());
        }
        System.out.println("Cantidad total de sesiones: " + repositorio.getCantidad());
    }
}
