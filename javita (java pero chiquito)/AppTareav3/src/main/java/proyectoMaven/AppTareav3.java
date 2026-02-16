package proyectoMaven;

import java.util.InputMismatchException;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import proyectoMaven.DAO.*;
import proyectoMaven.Models.*;
import proyectoMaven.Repository.*;

public class AppTareav3 {
    public AppTareav3() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
        EntityManager em = emf.createEntityManager();

        AlumnoDao alumnoDao = new AlumnoDao(em);
        RepositorioAlumno repositorio = new RepositorioAlumno(alumnoDao);

        int opcion = 0;
        while (opcion != 8) {
            menuOpciones();
            System.out.print("Ingrese una opcion para continuar: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarAlumnos(repositorio);
                    break;

                case 2:
                    cargarAlumnos(repositorio, scanner);
                    break;

                case 3:
                    System.out.print("Ingrese legajo del alumno a borrar: ");
                    int legajo = scanner.nextInt();
                    deleteAlumno(repositorio, legajo);
                    break;

            }

        }

        try {
            em.close();
            scanner.close();
            emf.close();
        }

        catch (Exception e) {
            System.out.println("Error al cerrar conexiones:" + e.getMessage());
        }

    }

    // DONE
    public static void cargarAlumnos(RepositorioAlumno repositorio, Scanner scanner) {

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
                    // Alumno alumno = new Alumno(nomAux, legAux, p1Aux, p2Aux);
                    // repositorio.agregarAlumno(alumno);
                    System.out.println();
                    scanner.nextLine();
                }
            }

        } catch (InputMismatchException exception) {
            System.err.println(exception);
        }

    }

    // DONE
    public static void mostrarAlumnos(RepositorioAlumno repositorio) {

        for (Alumno a : repositorio) {
            System.out.println(a.toString() + "\nPromedio:  "
                    + a.calcularPromedio());
            System.out.println("--------------------------------");
        }

    }

    // DONE
    public static void deleteAlumno(RepositorioAlumno repositorio, int legajo) {
        repositorio.borrarAlumno(legajo);

    }

    public static void menuOpciones() {
        System.out.println("Menu de opciones");
        System.out.println("opcion 1) mostrar alumnos ");
        System.out.println("opcion 2) agregar alumno ");
        System.out.println("opcion 3) borrar alumno");
        System.out.println("opcion 8) salir. ");
    }

}
