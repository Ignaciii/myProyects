package ignacio;

import java.util.List;

import jakarta.persistence.EntityManager;

public class SesionDAO {
    public SesionDAO(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public Boolean insertarEnBaseDeDatos(String terreno, Double distancia, Double duracionTotal) {
        try {
            em.getTransaction().begin();
            em.persist(new SesionEntrenamiento(terreno, distancia, duracionTotal));
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean borrarDeBaseDeDatos(int id) {
        try {
            em.getTransaction().begin();
            SesionEntrenamiento sesion = em.find(SesionEntrenamiento.class, id);
            if (sesion != null) {
                em.remove(sesion);
            }
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<SesionEntrenamiento> getDatos() {
        try {

            List<SesionEntrenamiento> sesiones = em
                    .createQuery("SELECT s FROM SesionEntrenamiento s", SesionEntrenamiento.class)
                    .getResultList();
            return sesiones;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
