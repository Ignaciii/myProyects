package proyectoMaven;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import proyectoMaven.DAO.*;
import proyectoMaven.Repository.*;
import proyectoMaven.Menues.MenuAlumno;
import proyectoMaven.Menues.MenuComision;

public class AppTareav3 {
    public AppTareav3() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
        EntityManager em = emf.createEntityManager();

        AlumnoDao alumnoDao = new AlumnoDao(em);
        RepositorioAlumno repositorioAlumno = new RepositorioAlumno(alumnoDao);

        ProfesorDao profesorDao = new ProfesorDao(em);
        RepositorioProfesor repositorioProfesor = new RepositorioProfesor(profesorDao);

        ComisionDao comisionDao = new ComisionDao(em);
        RepositorioComision repositorioComision = new RepositorioComision(comisionDao);

        MateriaDao materiaDao = new MateriaDao(em);
        RepositorioMateria repositorioMateria = new RepositorioMateria(materiaDao);

        MenuAlumno menuAlumno = new MenuAlumno(repositorioAlumno, scanner, repositorioMateria);
        MenuComision menuComision = new MenuComision(repositorioMateria, repositorioComision, scanner);

        int opcion = 0;
        while (opcion != 5) {
            menuOpciones();
            System.out.print("Ingrese una opcion para continuar: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println();
                    menuAlumno.iniciarMenu();
                    break;
                case 2:
                    System.out.println();
                    menuComision.iniciarMenu();
                    break;

            }
        }
        System.out.println("Saliendo...");
    }

    public static void menuOpciones() {
        String str = "---------------------------------------------------------------------------------------";

        System.out.println(str);
        System.out.println("Menu de opciones");
        System.out.println("Opcion 1) Gestionar Alumnos ");
        System.out.println("Opcion 2) Gestionar Comisiones ");
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
