package proyectoMaven.DAO;

import jakarta.persistence.EntityManager;

import proyectoMaven.Models.Curso;

import java.util.List;

public class CursoDao {

    public CursoDao(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public List<Curso> mostrarCursosDAO() {
        if (em != null) {

            try {
                List<Curso> cursos = em.createQuery("SELECT c FROM Curso c WHERE c.esActivo IS TRUE", Curso.class)
                        .getResultList();
                return cursos;
            } catch (Exception e) {
                System.out.println("Error al conectarse con la bd: " + e.getMessage());
            }

        }
        return null;
    }

    // soft delete
    public Boolean cambiarEstadoCursoDAO(int id) {
        if (em == null) {
            return null;
        }
        // aca cuando haces el cambio de estado hibernate se da cuenta y lo guarda sin
        // decirle
        try {

            em.getTransaction().begin();
            Curso cursoBorrar = em.find(Curso.class, id);
            if (cursoBorrar != null) {
                cursoBorrar.cambiarEstado();
                em.merge(cursoBorrar);
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

    public Boolean agregarCursoBD(Curso curso) {
        if (em == null) {
            return null;
        }

        try {
            em.getTransaction().begin();
            em.persist(curso);
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
