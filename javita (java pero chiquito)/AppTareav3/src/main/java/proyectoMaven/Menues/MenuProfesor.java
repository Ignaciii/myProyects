package proyectoMaven.Menues;

import java.util.InputMismatchException;
import java.util.Scanner;

import proyectoMaven.Models.Materia;
import proyectoMaven.Models.Profesor;
import proyectoMaven.Repository.RepositorioMateria;
import proyectoMaven.Repository.RepositorioProfesor;

public class MenuProfesor {
    public MenuProfesor(RepositorioProfesor repositorioProfesor, RepositorioMateria repositorioMateria,
            Scanner scanner) {
        this.repositorioProfesor = repositorioProfesor;
        this.repositorioMateria = repositorioMateria;
        this.scanner = scanner;
    }

    private RepositorioProfesor repositorioProfesor;
    private RepositorioMateria repositorioMateria;
    private Scanner scanner;

    public void iniciarMenu() {
        try {
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
                        mostrarProfesores();
                        break;
                    case 2:
                        cargarProfesor();
                        break;
                    case 3:
                        borrarProfesor();
                        break;

                    case 4:
                        asignarMateriaProfesor();
                        break;
                }
            }
        }

        catch (InputMismatchException e) {
            System.out.println("Upa parece que ingresaste un valor invalido!!!");
            System.out.println("Se anulo la carga que estabas haciendo, volve a intentarlo!!!");
            System.out.println(e.getMessage());
            e.printStackTrace();
            scanner.nextLine();
        }

    }

    public void Menu() {
        System.out.println("Gestor de profesores");
        System.out.println("Opcion 1) Mostrar profesores");
        System.out.println("Opcion 2) Agregar profesor");
        System.out.println("Opcion 3) Eliminar profesor");
        System.out.println("Opcion 4) Asignar una Materia");
        System.out.println("Opcion 5) Volver atras");
    }

    public void mostrarProfesores() {
        System.out.println(repositorioProfesor.mostrarProfesores());
    }

    public void cargarProfesor() {

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Es titular [SI-NO]: ");
        String respuesta = scanner.nextLine();
        System.out.print("Ingrese numero de matricula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();
        Boolean titular = false;
        if (respuesta.equals("SI")) {
            titular = true;
        }

        if (repositorioProfesor.agregarProfesor(new Profesor(matricula, nombre, apellido, titular))) {
            System.out.println("Profesor cargado exitosamente!!!");
            return;
        }
        System.out.println("Oops, el profesor no se pudo cargar");

    }

    public void borrarProfesor() {

        mostrarProfesores();
        System.out.print("Ingrese la matricula del profesor a borrar: ");
        int profesorBorrar = scanner.nextInt();
        scanner.nextLine();
        if (repositorioProfesor.borrarProfesor(profesorBorrar)) {
            System.out.println("Borrado exitoso!!");
            return;
        }
        System.out.println("Oops, no se pudo completar el borrado!!!");

    }

    public void asignarMateriaProfesor() {

        System.out.println(repositorioMateria.mostrarMaterias());
        System.out.print("Ingrese el codigo de la materia a asignar: ");
        int codigoMateria = scanner.nextInt();
        scanner.nextLine();
        Materia materia = repositorioMateria.getMaterias().stream()
                .filter(m -> m.getCodigoMateria() == codigoMateria).findFirst().orElse(null);
        if (materia == null) {
            System.out.println("Oops materia no encontrada");
            return;
        }
        mostrarProfesores();
        System.out.print("Ingrese la matricula del profesor al cual asignar la materia:");
        int matricula = scanner.nextInt();
        scanner.nextLine();
        Profesor profesor = repositorioProfesor.getProfesores().stream()
                .filter(c -> c.getNumeroMatricula() == matricula)
                .findFirst().orElse(null);
        scanner.nextLine();
        if (profesor == null) {
            System.out.println("Oops profesor no encontrado");
            return;
        }
        if (profesor.agregarMateria(materia)) {
            if (repositorioProfesor.actualizarProfesor(profesor)) {
                System.out.println("Se asigno la materia exitosamente!!!");
                return;
            }

        }
        System.out.println("Oops, error al asignar la materia al profesor!!!");

    }

}