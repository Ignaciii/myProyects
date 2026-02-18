package proyectoMaven.Menues;

import java.util.InputMismatchException;
import java.util.Scanner;

import proyectoMaven.Models.Comision;
import proyectoMaven.Models.Materia;
import proyectoMaven.Repository.RepositorioComision;
import proyectoMaven.Repository.RepositorioMateria;

public class MenuComision {
    public MenuComision(RepositorioMateria repositorioMateria, RepositorioComision repositorioComision,
            Scanner scanner) {
        this.repositorioMateria = repositorioMateria;
        this.repositorioComision = repositorioComision;
        this.scanner = scanner;
    }

    private RepositorioComision repositorioComision;
    private RepositorioMateria repositorioMateria;
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
                        mostrarComisiones();
                        break;

                    case 2:
                        cargarComision();
                        break;

                    case 3:
                        borrarComision();

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
        System.out.println("Gestor de comisiones");
        System.out.println("Opcion 1) Mostrar comisiones");
        System.out.println("Opcion 2) Agregar comision");
        System.out.println("Opcion 3) Eliminar comision");
        System.out.println("Opcion 4) Asignar una Materia");
        System.out.println("Opcion 5) Volver atras");
    }

    public void mostrarComisiones() {
        System.out.println(repositorioComision.mostrarListadoComisiones());
    }

    public void cargarComision() {

        System.out.print("Ingrese el nombre (ej K7): ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese a que año pertenece (1-5): ");
        int año = scanner.nextInt();
        while (año < 1 || año > 5) {
            System.out.print("Upa parece que ingresaste un año invalido, intentalo con uno distinto: ");
            año = scanner.nextInt();

        }

        scanner.nextLine();
        if (repositorioComision.agregarComision(new Comision(nombre, año))) {
            System.out.println("Comision creada exitosamente!!!");
            return;
        }
        System.out.println("Oops, la comision no se pudo crear");

    }

    public void borrarComision() {

        mostrarComisiones();
        System.out.print("Ingrese el Id de la comision a borrar: ");
        int comisionBorrar = scanner.nextInt();
        scanner.nextLine();
        if (repositorioComision.borrarComision(comisionBorrar)) {
            System.out.println("Borrado exitoso!!");
            return;
        }
        System.out.println("Oops, no se pudo completar el borrado!!!");

    }

    public void asignarMateriaComision() {

        System.out.println(repositorioMateria.mostrarMaterias());
        System.out.print("Ingrese el codigo de la materia a asignar: ");
        int codigoMateria = scanner.nextInt();
        scanner.nextLine();
        Materia materia = repositorioMateria.getMaterias().stream()
                .filter(m -> m.getCodigoMateria() == codigoMateria).findFirst().orElse(null);
        if (materia == null) {
            System.out.println("Oops no se encontro esa materia");
            return;
        }

        mostrarComisiones();
        System.out.print("Ingrese el id de la comision a la cual asignar la materia:");
        int idComision = scanner.nextInt();
        scanner.nextLine();
        Comision comision = repositorioComision.getListadoComisiones().stream().filter(c -> c.getId() == idComision)
                .findFirst().orElse(null);
        if (comision == null) {
            System.out.println("Oops no se encontro la comision");
            return;
        }
        if (comision.agregarMateria(materia)) {
            if (repositorioMateria.actualizarMateria(materia)) {
                System.out.println("Se agrego la materia exitosamente!!!");
                return;
            }

        }
        System.out.println("Oops, error al asignar la materia a la comision!!!");

    }
}
