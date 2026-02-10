package ignacio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepositorioSesiones implements Iterable<SesionEntrenamiento> {

    private List<SesionEntrenamiento> sesiones;
    private SesionDAO sesionDAO;

    // Constructor para cuando ya tenés una lista y la conexión
    public RepositorioSesiones(List<SesionEntrenamiento> sesiones, SesionDAO sesionDAO) {
        this.sesiones = sesiones;
        this.sesionDAO = sesionDAO;

    }

    // Constructor estándar: inicializa la lista y recibe la conexión
    public RepositorioSesiones(SesionDAO sesionDAO) {
        this.sesiones = new ArrayList<>(sesionDAO.mostrarDatos());
        this.sesionDAO = sesionDAO;

    }

    public void agregarSesion(String terreno, Double distancia, Double duracionTotal) {

        // 1. Lo guardamos en la lista (memoria) para que los Streams sigan andando
        SesionEntrenamiento nueva = sesionDAO.insertarEnBaseDeDatos(terreno, distancia, duracionTotal);
        if (nueva != null)
            this.sesiones.add(nueva);

        // 2. Lo mandamos a la base de datos (persistencia)

    }

    // Tu lógica de Iterator sigue igual (el gestor no sabe que hay una DB)
    @Override
    public Iterator<SesionEntrenamiento> iterator() {
        return sesiones.iterator();
    }

    // Tus Streams para filtrar por terreno (laburan sobre la lista en memoria)
    public List<SesionEntrenamiento> obtenerSesionesTerreno(String tipoTerreno) {
        return sesiones.stream()
                .filter(s -> s.getTerreno().equalsIgnoreCase(tipoTerreno))
                .toList();
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
}