package proyectoMaven.Menues;

import java.util.InputMismatchException;
import java.util.Scanner;

import proyectoMaven.Models.Comision;
import proyectoMaven.Models.Materia;

import proyectoMaven.Repository.RepositorioComision;
import proyectoMaven.Repository.RepositorioMateria;

public class MenuMateria {

    public MenuMateria(RepositorioMateria repositorioMateria,
            Scanner scanner, RepositorioComision repositorioComision) {

        this.repositorioMateria = repositorioMateria;
        this.scanner = scanner;
        this.repositorioComision = repositorioComision;
    }

    private RepositorioMateria repositorioMateria;
    private RepositorioComision repositorioComision;
    private Scanner scanner;

    public void iniciarMenu() {
        int opcion = -1;
        String str = "----------------------------------------------------------------";
        try {
            while (opcion != 5) {

                System.out.println(str);
                Menu();
                System.out.println(str);
                System.out.print("Ingrese una opcion: ");
                opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1:
                        mostrarMaterias();
                        break;
                    case 2:
                        cargarMateria();
                        break;
                    case 3:
                        borrarMateria();
                        break;

                    case 4:
                        asignarMateriaComision();
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Upa parece que ingresaste un valor invalido!!!");
            System.out.println("Se anulo la carga que estabas haciendo, volve a intentarlo!!!");
            System.out.println(e.getMessage());
            e.printStackTrace();
            scanner.nextLine();
        }

    }

    public void Menu() {
        System.out.println("Gestor de materias");
        System.out.println("Opcion 1) Mostrar materias");
        System.out.println("Opcion 2) Agregar materia");
        System.out.println("Opcion 3) Eliminar materia");
        System.out.println("Opcion 4) Asignar a una Comision");
        System.out.println("Opcion 5) Volver atras");
    }

    public void mostrarMaterias() {
        System.out.println(repositorioMateria.mostrarMaterias());
    }

    public void cargarMateria() {

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la duracion [Anual-Cuatrimestral]: ");
        String duracion = scanner.nextLine();
        System.out.print("Es troncal [SI-NO]: ");
        String respuesta = scanner.nextLine();
        Boolean troncal = false;
        if (respuesta.equals("SI")) {
            troncal = true;
        }

        if (repositorioMateria.agregarMateria(new Materia(troncal, duracion, nombre))) {
            System.out.println("Materia cargada exitosamente!!!");
            return;
        }
        System.out.println("Oops, la materia no se pudo cargar");

    }

    public void borrarMateria() {

        mostrarMaterias();
        System.out.print("Ingrese la codigo de materia a borrar: ");
        int materiaBorrar = scanner.nextInt();
        scanner.nextLine();
        if (repositorioMateria.borrarMateria(materiaBorrar)) {
            System.out.println("Borrado exitoso!!");
            return;
        }
        System.out.println("No se pudo borrar la materia!!!");

    }

    public void asignarMateriaComision() {

        System.out.println(repositorioComision.mostrarListadoComisiones());
        System.out.print("Ingrese el id de la comision a asignar: ");
        int idComision = scanner.nextInt();
        scanner.nextLine();
        Comision comision = repositorioComision.getListadoComisiones().stream()
                .filter(c -> c.getId() == idComision).findFirst().orElse(null);

        if (comision == null) {
            System.out.println("Oops no encontramos esa comision");
            return;
        }
        mostrarMaterias();
        System.out.print("Ingrese el codigo de la materia:");
        int codigoMateria = scanner.nextInt();
        scanner.nextLine();
        Materia materia = repositorioMateria.getMaterias().stream()
                .filter(m -> m.getCodigoMateria() == codigoMateria)
                .findFirst().orElse(null);
        scanner.nextLine();
        if (materia == null) {
            System.out.println("Oops no encontramos esa materia");
            return;
        }

        if (comision.agregarMateria(materia)) {
            if (repositorioMateria.actualizarMateria(materia)) {
                System.out.println("Se asigno la comision exitosamente!!!");
                return;
            }

        }
        System.out.println("Oops, error al asignar la comision a la materia!!!");

    }

}
