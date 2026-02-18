package proyectoMaven.Menues;

import proyectoMaven.Models.Materia;
import proyectoMaven.Repository.RepositorioAlumno;
import proyectoMaven.Repository.RepositorioMateria;
import proyectoMaven.Models.Alumno;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAlumno {
    public MenuAlumno(RepositorioAlumno repositorioAlumno, Scanner scanner,
            RepositorioMateria repositorioMateria) {
        this.repositorioAlumno = repositorioAlumno;
        this.repositorioMateria = repositorioMateria;
        this.scanner = scanner;
    }

    private RepositorioAlumno repositorioAlumno;

    private RepositorioMateria repositorioMateria;
    private Scanner scanner;

    public void iniciarMenu() {
        int opcion = -1;
        String str = "----------------------------------------------------------------";

        while (opcion != 5) {
            System.out.println(str);
            Menu();
            System.out.println(str);
            System.out.print("Ingrese una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    mostrarAlumnos();
                    break;

                case 2:
                    cargarAlumnos();
                    break;

                case 3:
                    borrarAlumno();

                    break;

                case 4:
                    inscribirEnMateria();
                    break;

            }
        }
    }

    public void Menu() {

        System.out.println("Gestor de Alumnos");
        System.out.println("Opcion 1) Mostrar Alumnos.");
        System.out.println("Opcion 2) Agregar Alumno.");
        System.out.println("Opcion 3) Eliminar Alumno.");
        System.out.println("Opcion 4) Inscribir en una materia.");
        System.out.println("Opcion 5) Volver Atras.");

    }

    public void cargarAlumnos() {

        try {
            System.out.print("\nCuantos alumnos desea cargar: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < cantidad; i++) {
                System.out.print("Ingrese los datos del alumno " + (i + 1));
                System.out.print("\tLegajo: ");
                int legajo = scanner.nextInt();
                scanner.nextLine();
                if (repositorioAlumno.repetido(legajo)) {
                    System.out.println("\nNo se puede cargar dos veces el mismo alumno!!!!\n");
                    i = i - 1;
                } else {
                    scanner.nextLine();
                    System.out.print("Nombre y Apellido: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese nota del primer parcial: ");
                    Double parcial1 = scanner.nextDouble();
                    System.out.print("Ingrese nota del segundo parcial: ");
                    Double parcial2 = scanner.nextDouble();

                    Alumno alumno = new Alumno(nombre, legajo, parcial1, parcial2);
                    if (repositorioAlumno.agregarAlumno(alumno)) {
                        System.out.println("Alumno creado con exito.");
                    } else {
                        System.out.println("Oops algo salio mal en la creacion!!");
                    }

                    System.out.println();
                    scanner.nextLine();
                }
            }

        } catch (InputMismatchException exception) {
            System.out.println("Error: " + exception.getMessage());
            exception.printStackTrace();
        }

    }

    public void borrarAlumno() {

        mostrarAlumnos();
        System.out.print("Ingrese el legajo del alumno a borrar: ");
        int legajoBorrar = scanner.nextInt();
        scanner.nextLine();
        if (repositorioAlumno.borrarAlumno(legajoBorrar)) {
            System.out.println("Borrado exitoso!!!");
        } else {
            System.out.println("No se pudo borrar el alumno!!!");
        }
    }

    public void mostrarAlumnos() {
        System.out.println(repositorioAlumno.mostrarAlumnos());
    }

    public void inscribirEnMateria() {

        System.out.println(repositorioMateria.mostrarMaterias());
        System.out.print("Ingrese el codigo de la materia: ");
        int codigoMateria = scanner.nextInt();
        scanner.nextLine();
        Materia materia = repositorioMateria.getMaterias().stream()
                .filter(m -> m.getCodigoMateria() == codigoMateria).findFirst().orElse(null);
        if (materia == null) {
            System.out.println("Error materia no encontrada");
            return;
        }

        mostrarAlumnos();
        System.out.print("Ingrese el legajo del alumno a inscribir: ");
        int legajo = scanner.nextInt();
        scanner.nextLine();
        Alumno alumno = repositorioAlumno.getListadoAlumnos().stream().filter(a -> a.getLegajo() == legajo).findFirst()
                .orElse(null);
        if (alumno == null) {
            System.out.println("Error alumno no encontrado");
            return;
        }
        alumno.agregarMateria(materia);
        if (repositorioAlumno.actualizarAlumno(alumno)) {
            System.out.println("Alumno inscripto a la materia!!!");
            return;

        }
        System.out.println("Fallo la inscripcion!!");

    }
}