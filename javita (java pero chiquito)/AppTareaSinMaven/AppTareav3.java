import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AppTareav3 {
    public AppTareav3() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Repositorio repositorio = new Repositorio();
        cargarAlumnos(repositorio, scanner);
        mostrarAlumnos(repositorio);
        System.out.print("Ingrese legajo del alumno a borrar: ");
        int legajo = scanner.nextInt();
        deleteAlumno(repositorio, legajo);
        System.out.println("\nAlumno borrado\n");
        mostrarAlumnos(repositorio);

        scanner.close();

    }

    // DONE
    public static void cargarAlumnos(Repositorio repositorio, Scanner scanner) {

        try {
            System.out.print("\nCuantos alumnos desea cargar: ");
            int cantidad = scanner.nextInt();
            for (int i = 0; i < cantidad; i++) {
                System.out.print("Ingrese los datos del alumno " + (i + 1));
                System.out.print("\tLegajo: ");
                int legAux = scanner.nextInt();
                if (repositorio.repetido(legAux)) {
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
                    repositorio.agregarAlumno(alumno);
                    System.out.println();
                    scanner.nextLine();
                }
            }

        } catch (InputMismatchException exception) {
            System.err.println(exception);
        }

    }

    // DONE
    public static void mostrarAlumnos(Repositorio repositorio) {

        for (Alumno a : repositorio) {
            System.out.println(a.toString() + "\nPromedio:  "
                    + a.calcularPromedio());
            System.out.println("--------------------------------");
        }

    }

    // DONE
    public static void deleteAlumno(Repositorio repositorio, int legajo) {
        repositorio.borrarAlumno(legajo);

    }

}
