package ignacio;

import java.util.ArrayList;
import java.util.List;

public class RepositorioSesiones {

    public RepositorioSesiones(SesionDAO sesionDAO) {
        this.sesiones = sesionDAO.getDatos();
        this.sesionDAO = sesionDAO;
        this.sesiones = (sesionDAO.getDatos() == null ? new ArrayList<>() : sesionDAO.getDatos());

    }

    private List<SesionEntrenamiento> sesiones;
    private SesionDAO sesionDAO;

    public Boolean agregarSesion(SesionEntrenamiento sesionEntrenamiento) {

        if (sesionDAO.insertarEnBaseDeDatos(sesionEntrenamiento)) {
            this.sesiones.add(sesionEntrenamiento);
            return true;
        }
        return false;

    }

    public int getCantidad() {
        return sesiones.size();
    }

    public Boolean borrarSesion(int id) {
        if (sesionDAO.borrarDeBaseDeDatos(id)) {
            Boolean resultado = sesiones.removeIf(s -> s.getNumeroSesion() == id);
            if (resultado) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public List<SesionEntrenamiento> getSesiones() {
        return sesiones;
    }

}