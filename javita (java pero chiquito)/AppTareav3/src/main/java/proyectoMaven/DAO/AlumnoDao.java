package proyectoMaven.DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import proyectoMaven.Models.Alumno;;

public class AlumnoDao {
    public AlumnoDao(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public List<Alumno> mostrarAlumnosDAO() {
        if (em != null) {

            try {
                List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
                return alumnos;
            } catch (Exception e) {
                System.out.println("Error al conectarse con la bd: " + e.getMessage());
            }

        }
        return null;
    }

    public Boolean deleteAlumnoDAO(int legajoBorrar) {
        if (em == null) {
            return null;
        }

        try {

            em.getTransaction().begin();
            Alumno alumnoBorrar = em.find(Alumno.class, legajoBorrar);
            if (alumnoBorrar != null) {
                em.remove(alumnoBorrar);
            }
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            System.out.println("Error al llevar a cabo el borrado en la base de datos: " + e.getMessage());
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }

    }

    public Boolean agregarAlumnoBD(Alumno alumno) {
        if (em == null) {
            return null;
        }

        try {
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al cargar alumno en la base de datos: " + e.getMessage());
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }

    }

}
