package proyectoMaven.Menues;

import proyectoMaven.Models.Curso;
import proyectoMaven.Models.Materia;
import proyectoMaven.Repository.RepositorioAlumno;
import proyectoMaven.Repository.RepositorioCurso;
import proyectoMaven.Repository.RepositorioMateria;
import proyectoMaven.Models.Alumno;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAlumno {
    public MenuAlumno(RepositorioAlumno repositorioAlumno, Scanner scanner,
            RepositorioCurso repositorioCurso, RepositorioMateria repositorioMateria) {
        this.repositorioAlumno = repositorioAlumno;
        this.repositorioCurso = repositorioCurso;
        this.repositorioMateria = repositorioMateria;
        this.scanner = scanner;
    }

    private RepositorioAlumno repositorioAlumno;
    private RepositorioCurso repositorioCurso;
    private RepositorioMateria repositorioMateria;
    private Scanner scanner;

    public void iniciarMenu() {
        int opcion = -1;
        while (opcion != 5) {
            Menu();
            opcion = scanner.nextInt();
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
        System.out.print("Ingrese una opcion: ");
    }

    public void cargarAlumnos() {

        try {
            System.out.print("\nCuantos alumnos desea cargar: ");
            int cantidad = scanner.nextInt();
            for (int i = 0; i < cantidad; i++) {
                System.out.print("Ingrese los datos del alumno " + (i + 1));
                System.out.print("\tLegajo: ");
                int legajo = scanner.nextInt();
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

                    for (Curso c : repositorioCurso.getListadoCursos()) {
                        System.out.println("Id: " + c.getId() + "| Nombre: " + c.getNombre());
                    }
                    System.out.print("Ingrese el id del curso a inscribir el alumno:");
                    int idCurso = scanner.nextInt();
                    Curso curso = repositorioCurso.getListadoCursos().stream()
                            .filter(c -> c.getId() == idCurso).findFirst().orElse(null);
                    if (curso != null) {
                        Alumno alumno = new Alumno(nombre, legajo, parcial1, parcial2, curso);
                        if (repositorioAlumno.agregarAlumno(alumno)) {
                            System.out.println("Alumno creado con exito.");
                        } else {
                            System.out.println("Oops algo salio mal en la creacion!!");
                        }
                    } else {
                        System.out.println("Error no se pudo crear el alumno!!!");
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

        for (Alumno a : repositorioAlumno.getListadoAlumnos()) {
            System.out.println("Legajo: " + a.getLegajo() + "| Nombre y Apellido: " + a.getNombre());
        }
        System.out.print("Ingrese el legajo del alumno a borrar: ");
        int legajoBorrar = scanner.nextInt();
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
        for (Materia m : repositorioMateria.mostrarMaterias()) {
            System.out.println("Codigo de materia: " + m.getCodigoMateria() + "| Nombre: " + m.getNombre());
        }
        System.out.print("Ingrese el codigo de la materia: ");
        int codigoMateria = scanner.nextInt();
        Materia materia = repositorioMateria.mostrarMaterias().stream()
                .filter(m -> m.getCodigoMateria() == codigoMateria).findFirst().orElse(null);
        if (materia == null) {
            System.out.println("Error materia no encontrada");
            return;
        }

        mostrarAlumnos();
        System.out.print("Ingrese el legajo del alumno a inscribir: ");
        int legajo = scanner.nextInt();
        Alumno alumno = repositorioAlumno.getListadoAlumnos().stream().filter(a -> a.getLegajo() == legajo).findFirst()
                .orElse(null);
        if (alumno == null) {
            System.out.println("Error alumno no encontrado");
            return;
        }
        if (repositorioAlumno.actualizarAlumno(alumno)) {
            alumno.agregarMateria(materia);
            System.out.println("Alumno inscripto a la materia!!!");
            return;

        }
        System.out.println("Fallo la inscripcion!!");

    }
}