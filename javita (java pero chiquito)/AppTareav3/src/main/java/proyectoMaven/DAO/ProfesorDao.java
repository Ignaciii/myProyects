package proyectoMaven.DAO;

import jakarta.persistence.EntityManager;
import java.util.List;

import proyectoMaven.Models.Alumno;
import proyectoMaven.Models.Profesor;

public class ProfesorDao {
    public ProfesorDao(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public List<Profesor> mostrarProfesoresDao() {
        if (em != null) {
            try {
                List<Profesor> profesores = em
                        .createQuery("SELECT p FROM Profesor p WHERE p.esActivo IS TRUE", Profesor.class)
                        .getResultList();
                return profesores;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;

    }

    public Boolean cambiarEstadoProfesorDao(int matricula) {
        if (em != null) {
            try {
                em.getTransaction().begin();
                Profesor profesor = em.find(Profesor.class, matricula);
                if (profesor != null) {
                    profesor.cambiarEstado();
                }
                em.getTransaction().commit();
                return true;

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
                em.getTransaction().rollback();
            }
        }
        return false;
    }

    public Boolean agregarProfesorDao(Profesor profesor) {
        if (em != null) {
            try {
                em.getTransaction().begin();
                em.persist(profesor);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
                em.getTransaction().rollback();
            }
        }
        return false;
    }

    public Boolean actualizarProfesor(Profesor profesor) {
        try {
            em.getTransaction().begin();
            em.merge(profesor);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
