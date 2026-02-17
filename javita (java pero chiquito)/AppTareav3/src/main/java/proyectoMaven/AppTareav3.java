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

        ProfesorDao profesorDao = new ProfesorDao(em);
        RepositorioProfesor repositorioProfesor = new RepositorioProfesor(profesorDao);

        CursoDao cursoDao = new CursoDao(em);
        RepositorioCurso repositorioCurso = new RepositorioCurso(cursoDao);

        MateriaDao materiaDao = new MateriaDao(em);
        RepositorioMateria repositorioMateria = new RepositorioMateria(materiaDao);

        int opcion = 0;
        while (opcion != 5) {
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
                case 4:
                    break;

            }

        }
        horaDeCerrar(scanner, emf, em);

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

    public static void mostrarAlumnos(RepositorioAlumno repositorio) {

        for (Alumno a : repositorio.getListadoAlumnos()) {
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
        String str = "---------------------------------------------------------------------------------------";

        System.out.println(str);
        System.out.println("Menu de opciones");
        System.out.println("Opcion 1) Gestionar Alumnos ");
        System.out.println("Opcion 2) Gestionar Cursos ");
        System.out.println("Opcion 3) Gestionar Profesores");
        System.out.println("Opcion 4) Gestionar Materias");
        System.out.println("Opcion 5) Salir. ");
        System.out.println(str);
    }

    public static void horaDeCerrar(Scanner sc, EntityManagerFactory emf, EntityManager em) {
        sc.close();
        try {
            emf.close();
            em.close();

        } catch (Exception e) {
            System.out.println("Error al desconectar: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
