package proyectoMaven.DAO;

import jakarta.persistence.EntityManager;
import proyectoMaven.Models.Alumno;
import proyectoMaven.Models.Materia;

import java.util.List;

public class MateriaDao {
    public MateriaDao(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public List<Materia> mostrarMateriasDAO() {
        if (em != null) {

            try {
                List<Materia> materias = em.createQuery("SELECT m FROM Materia m WHERE m.esActivo IS TRUE",
                        Materia.class)
                        .getResultList();
                return materias;
            } catch (Exception e) {
                System.out.println("Error al conectarse con la bd: " + e.getMessage());
            }

        }
        return null;
    }

    // soft delete
    public Boolean cambiarEstadoMateriaDAO(int id) {
        if (em == null) {
            return null;
        }
        // aca cuando haces el cambio de estado hibernate se da cuenta y lo guarda sin
        // decirle
        try {

            em.getTransaction().begin();
            Materia materiaBorrar = em.find(Materia.class, id);
            if (materiaBorrar != null) {
                materiaBorrar.cambiarEstado();

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

    public Boolean agregarMateriaBD(Materia materia) {
        if (em == null) {
            return null;
        }

        try {
            em.getTransaction().begin();
            em.persist(materia);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al cargar alumno en la base de datos: " + e.getMessage());
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }

    }

    // va aca el actualizar y no en la comision por ser la dueña de la relacion

    public Boolean actualizarMateria(Materia materia) {
        try {
            em.getTransaction().begin();
            em.merge(materia);
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
