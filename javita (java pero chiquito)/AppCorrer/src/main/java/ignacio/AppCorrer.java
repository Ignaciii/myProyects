package ignacio;

import java.util.InputMismatchException;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AppCorrer {
    public AppCorrer() {
    }

    public static void main(String[] args) {
        // 1. Configuramos el Scanner con punto decimal
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
        EntityManager em = emf.createEntityManager();

        SesionDAO sesionDAO = new SesionDAO(em);
        RepositorioSesiones repositorio = new RepositorioSesiones(sesionDAO);

        int opcion = -1;

        while (opcion != 8) {
            try {
                MenuOpciones.mostrarOpciones();
                System.out.print("Ingrese una opcion para comenzar: ");
                opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1:

                        cargarSesiones(repositorio, scanner);

                        break;

                    case 2:
                        visualizarSesiones(repositorio);

                        break;

                    case 3:
                        borrarSesion(scanner, repositorio);

                        break;
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
                scanner.nextLine();
                System.out.println("Upa metimos un valor invalido!!!");
            }
        }

        scanner.close();
        emf.close();
        em.close();

    }

    public static void cargarSesiones(RepositorioSesiones repositorio, Scanner scanner) {
        String terreno;
        Double distancia, duracionTotal;

        // Actualizamos el mensaje para no confundir: ahora es con PUNTO
        System.out.println("Vamos a cargar una sesion ahora (usar valores con coma ej 11,4 km");

        System.out.print("distancia recorrida (km): ");
        distancia = scanner.nextDouble();
        System.out.print("duracion total de la sesion (en minutos): ");
        duracionTotal = scanner.nextDouble();

        scanner.nextLine(); // Limpiamos buffer
        System.out.print("Tipo de terreno (llano / desnivel): ");
        terreno = scanner.nextLine();

        repositorio.agregarSesion(terreno, distancia, duracionTotal);

    }

    public static void visualizarSesiones(RepositorioSesiones repositorio) {
        if (repositorio.getSesiones() != null) {
            for (SesionEntrenamiento s : repositorio.getSesiones()) {
                System.out.println(s.toString());
            }
            System.out.println("Cantidad total de sesiones: " + repositorio.getCantidad());
            return;
        }
        System.out.println("No hay sesiones cargadas!!!");
    }

    public static void borrarSesion(Scanner scanner, RepositorioSesiones repositorioSesiones) {
        System.out.print("Ingrese el numero de sesion que desea borrar: ");
        int idBorrar = scanner.nextInt();
        scanner.nextLine();
        repositorioSesiones.borrarSesion(idBorrar);

    }
}
