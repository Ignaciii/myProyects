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

        // Actualizamos el mensaje para no confundir: ahora es con PUNTO
        System.out.println("Vamos a cargar una sesion ahora (usar valores con coma ej 11,4 km");

        System.out.print("distancia recorrida (km): ");
        Double distancia = scanner.nextDouble();
        System.out.print("duracion total de la sesion (en minutos): ");
        Double duracionTotal = scanner.nextDouble();

        scanner.nextLine();
        System.out.print("Tipo de terreno [llano / desnivel]: ");
        String terreno = scanner.nextLine();
        while (!terreno.toLowerCase().equals("llano") && !terreno.toLowerCase().equals("desnivel")) {
            System.out.print("Oops, se ingreso una opcion invalida, ingrese una valida [llano / desnivel]: ");
            terreno = scanner.nextLine();
        }
        SesionEntrenamiento sesion = new SesionEntrenamiento(terreno.toUpperCase(), distancia, duracionTotal);
        if (repositorio.agregarSesion(sesion)) {
            System.out.println("Se cargo la sesion de forma exitosa!!!");
            return;
        }
        System.out.println("No se pudo cargar la sesion, intentelo nuevamente");

    }

    public static void visualizarSesiones(RepositorioSesiones repositorio) {
        System.out.println(recorrerSesiones(repositorio));
        System.out.println("Cantidad total de sesiones: " + repositorio.getCantidad());
        return;

    }

    public static void borrarSesion(Scanner scanner, RepositorioSesiones repositorioSesiones) {
        if (repositorioSesiones.getCantidad() == 0) {
            System.out.println("No hay sesiones cargadas para borrar");
            return;
        }
        System.out.println(recorrerSesiones(repositorioSesiones));
        System.out.print("Ingrese el numero de sesion que desea borrar: ");
        int idBorrar = scanner.nextInt();
        scanner.nextLine();
        if (repositorioSesiones.borrarSesion(idBorrar)) {
            System.out.println("Borrado exitoso!!!");
            return;
        }
        System.out.println("No se pudo encontrar el elemento!!!");

    }

    public static String recorrerSesiones(RepositorioSesiones repositorio) {
        StringBuilder sb = new StringBuilder();
        if (!repositorio.getSesiones().isEmpty()) {
            for (SesionEntrenamiento s : repositorio.getSesiones()) {
                sb.append(s.toString()).append("\n");
            }
            return sb.toString();

        }
        return "No se cargaron sesiones aun!!!";
    }
}
