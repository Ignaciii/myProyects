import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppCorrer {
    public AppCorrer() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RepositorioSesiones repositorio = new RepositorioSesiones();
        cargarSesiones(repositorio, scanner);
        visualizarSesiones(repositorio);
        RepositorioSesiones repoLlanos = new RepositorioSesiones(repositorio.obtenerSesionesTerreno("llano"));
        visualizarSesiones(repoLlanos);
        scanner.close();
    }

    public static void cargarSesiones(RepositorioSesiones repositorio, Scanner scanner) {

        String terreno;
        Double distancia;
        Double duracionTotal;
        int intervalosIda;
        int intervalosVuelta;
        Double tiempoTroteIda;
        Double tiempoCaminataIda;
        Double tiempoTroteVuelta;
        Double tiempoCaminataVuelta;
        String sensacionFinal;
        String anotaciones;
        int sesion;

        try {
            System.out.println("Vamos a cargar una sesion ahora (RECORDAR: valores con decimales van con ',')");
            System.out.print("Numero de sesion: ");
            sesion = scanner.nextInt();
            System.out.print("intervalos de ida: ");
            intervalosIda = scanner.nextInt();
            System.out.print("intervalos de vuelta: ");
            intervalosVuelta = scanner.nextInt();

            System.out.print("distancia recorrida (km): ");
            distancia = scanner.nextDouble();
            System.out.print("duracion total de la sesion (como lo da la app en horas): ");
            duracionTotal = scanner.nextDouble();
            System.out.print("tiempo trote de ida (en min): ");
            tiempoTroteIda = scanner.nextDouble();
            System.out.print("tiempo caminata de ida (en min): ");
            tiempoCaminataIda = scanner.nextDouble();
            System.out.print("tiempo trote de vuelta (en min): ");
            tiempoTroteVuelta = scanner.nextDouble();
            System.out.print("tiempo caminata de vuelta (en min): ");
            tiempoCaminataVuelta = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Tipo de terreno (siendo llano o en desnivel): ");
            terreno = scanner.nextLine();
            System.out.print("sensacion final: ");
            sensacionFinal = scanner.nextLine();
            System.out.print("anotaciones (ej recorte x tramo o cambie y parametro): ");
            anotaciones = scanner.nextLine();

            SesionEntrenamiento sesionNueva = new SesionEntrenamiento(sesion, terreno, distancia, duracionTotal,
                    intervalosIda, intervalosVuelta, tiempoTroteIda, tiempoCaminataIda, tiempoTroteVuelta,
                    tiempoCaminataVuelta,
                    sensacionFinal, anotaciones);

            repositorio.agregarSesion(sesionNueva);

        } catch (Exception exception) {
            System.out.println("error encontrado: " + exception.toString());
        }

    }

    public static void visualizarSesiones(RepositorioSesiones repositorio) {

        for (SesionEntrenamiento s : repositorio) {
            System.out.println("\n" + s.toString());
        }

        /*
         * esto es lo que usaba antes
         * Iterator<SesionEntrenamiento> iterador = repositorio.iterator();
         * while (iterador.hasNext()) {
         * SesionEntrenamiento actual = iterador.next();
         * System.out.println();
         * System.out.println(actual.toString());
         * }
         */
        System.out
                .println("Cantidad total de sesiones de entrenamiento: " + repositorio.getCantidad());
    }

}
