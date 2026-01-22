import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AppTareav2 {
    public AppTareav2() {
    }

    public static void main(String[] args) {

        // aca usamos list a la izq en vez de arraylist porque es un tip de gemini, al
        // hacer esto si en un futuro necesitas cambiar de usar AL a linked ej solo
        // cambias eso
        // valido porque todo hijo es a su vez su padre pero no viceversa (siempre que
        // se pueda hacer hacelo asi es mejor)
        List<Alumno> alumnos = new ArrayList<>();
        alumnos = cargarAlumnos(alumnos);
        mostrarAlumnos(alumnos);
        // mostrar(alumnos);

    }

    // metodo cargar del repo
    public static List<Alumno> cargarAlumnos(List<Alumno> alumnos) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("\nCuantos alumnos desea cargar: ");
            int cantidad = scanner.nextInt();
            for (int i = 0; i < cantidad; i++) {
                System.out.print("Ingrese los datos del alumno " + (i + 1));
                System.out.print("\tLegajo: ");
                int legAux = scanner.nextInt();
                if (repetido(alumnos, legAux)) {
                    System.out.println("\nNo se puede cargar dos veces el mismo alumno!!!!\n");
                    i = i - 1;
                } else {
                    scanner.nextLine();
                    System.out.print("Nombre y Apellido: ");
                    String nomAux = scanner.nextLine();
                    System.out.print("Ingrese nota del primer parcial: ");
                    Double p1Aux = scanner.nextDouble();
                    System.out.print("Ingrese nota del segundo parcial: ");
                    Double p2Aux = scanner.nextDouble();
                    Alumno alumno = new Alumno(nomAux, legAux, p1Aux, p2Aux);
                    alumnos.add(alumno);
                    System.out.println();
                    scanner.nextLine();

                }
            }
            return alumnos;

        } catch (InputMismatchException exception) {
            alumnos.add(new Alumno());
            return alumnos;

        } finally {
            scanner.close();

        }
    }

    // con iterable
    public static void mostrarAlumnos(List<Alumno> alumnos) {
        if (!alumnos.isEmpty()) {
            Iterator<Alumno> iterador = alumnos.iterator();
            while (iterador.hasNext()) {
                Alumno actual = iterador.next();
                // si esto esta mal entonces cambialo por iterator.next
                System.out.println(actual.toString() + "\nPromedio:  "
                        + actual.calcularPromedio());
                System.out.println("--------------------------------");

            }
        }
    }

    // aca podemos hacer un stream con el .distinct()
    public static Boolean repetido(List<Alumno> alumnos, int legajo) {
        if (!alumnos.isEmpty()) {
            Iterator<Alumno> iterator = alumnos.iterator(); // -> iterable
            while (iterator.hasNext()) {
                Alumno actual = iterator.next();

                if (actual.getLegajo() == legajo)
                    return true;
            }
        }
        return false;
    }

    // el signo de pregunta es llamado wildcard es la forma de resolver lo que dijo
    // gemini "un arreglo de cualquier cosa", esta es la forma y no la de usar
    // Object

    // stream
    public static void mostrarTodo(List<?> objects) {
        Iterator<?> iterator = objects.iterator();
        while (iterator.hasNext()) {
            Object actual = iterator.next();
            System.out.println(actual.toString());
        }
    }

    // metodo que le hable al repo
    public void deleteAlumno(List<Alumno> alumnos, int legajo) {
        if (!alumnos.isEmpty()) {
            Iterator<Alumno> iterador = alumnos.iterator();
            while (iterador.hasNext()) {
                Alumno actual = iterador.next();
                if (actual.getLegajo() == legajo) {
                    iterador.remove();
                }
            }
        }

    }

}
