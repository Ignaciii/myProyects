package proyectoMaven.DAO;

import jakarta.persistence.EntityManager;

import proyectoMaven.Models.Comision;

import java.util.List;

public class ComisionDao {

    public ComisionDao(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public List<Comision> mostrarComisionesDao() {
        if (em != null) {

            try {
                List<Comision> comisiones = em
                        .createQuery("SELECT c FROM Comision c WHERE c.esActivo IS TRUE", Comision.class)
                        .getResultList();
                return comisiones;
            } catch (Exception e) {
                System.out.println("Error al conectarse con la bd: " + e.getMessage());
            }

        }
        return null;
    }

    // soft delete
    public Boolean cambiarEstadoComisionDao(int id) {
        if (em == null) {
            return null;
        }
        // aca cuando haces el cambio de estado hibernate se da cuenta y lo guarda sin
        // decirle
        try {

            em.getTransaction().begin();
            Comision comisionBorrar = em.find(Comision.class, id);
            if (comisionBorrar != null) {
                comisionBorrar.cambiarEstado();

            }
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            System.out.println("Error al borrar la comision de la base de datos: " + e.getMessage());
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }

    }

    public Boolean agregarComisionDao(Comision comision) {
        if (em == null) {
            return null;
        }

        try {
            em.getTransaction().begin();
            em.persist(comision);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al cargar comision en la base de datos: " + e.getMessage());
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }

    }
}
