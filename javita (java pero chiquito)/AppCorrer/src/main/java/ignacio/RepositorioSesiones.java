package ignacio;

import java.util.List;

public class RepositorioSesiones {

    public RepositorioSesiones(SesionDAO sesionDAO) {
        this.sesiones = sesionDAO.getDatos();
        this.sesionDAO = sesionDAO;

    }

    private List<SesionEntrenamiento> sesiones;
    private SesionDAO sesionDAO;

    public void agregarSesion(String terreno, Double distancia, Double duracionTotal) {

        if (sesionDAO.insertarEnBaseDeDatos(terreno, distancia, duracionTotal))
            this.sesiones.add(new SesionEntrenamiento(terreno, distancia, duracionTotal));

    }

    public int getCantidad() {
        return sesiones.size();
    }

    public void borrarSesion(int id) {
        if (sesionDAO.borrarDeBaseDeDatos(id)) {
            Boolean resultado = sesiones.removeIf(s -> s.getNumeroSesion() == id);
            if (resultado) {
                System.out.println("Borrado con exito!!!");
            } else {
                System.out.println("Se borro con exito de la BD pero no estaba cargado en memoria!!!");
            }
        } else {
            System.out.println("No se pudo borrar el elemento!!!");
        }
    }

    public List<SesionEntrenamiento> getSesiones() {
        return sesiones;
    }

}