import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

//vos existis para sacar responsabilidades al gestor
public class RepositorioSesiones implements Iterable<SesionEntrenamiento> {
    public RepositorioSesiones(List<SesionEntrenamiento> sesiones) {
        this.sesiones = sesiones;
    };

    public RepositorioSesiones() {
        this.sesiones = new ArrayList<>();
    };

    private List<SesionEntrenamiento> sesiones;

    public void agregarSesion(SesionEntrenamiento sesion) {
        this.sesiones.add(sesion);
    }

    // esto existe por el hecho de que el gestor no deveria tener esta logica, solo
    // preguntar al que sabe y el que sabe es el repositorio
    // ademas habiamos hecho un getSesiones que devolvia la lista de sesiones, pero
    // asi cualquiera podia modificar la lista, la mejor solucion es esta
    public Iterator<SesionEntrenamiento> iterator() {
        return sesiones.iterator();
    }

    // traduccion de esto, a la sesiones las hacemos un stream o flujo, al ser un
    // flujo del tipo SesionEntrenamiento puedo usar el filter (defino la variable y
    // con ella hago la comparacion, el equals se usa para comparar siempre antes
    // que el ==)

    public List<SesionEntrenamiento> obtenerSesionesTerreno(String tipoTerreno) {
        Stream<SesionEntrenamiento> sesionesStream = sesiones.stream();
        return sesionesStream.filter(s -> s.getTerreno().equalsIgnoreCase(tipoTerreno)).toList();

        // sesiones.stream().filter(s ->
        // s.getTerreno().equalsIgnoreCase(tipoTerreno).toList()) es lo mismo que lo de
        // arriba pero mejor

    }

    public int getCantidad() {
        return sesiones.size();
    }

}