import java.util.List;
import java.util.ArrayList;

public class RepositorioPartida {
    public RepositorioPartida(RepositorioMazo mazo) {
        this.columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            columnas.add(new ArrayList<>());
        }
        this.repositorioMazo = mazo;
        this.cartasUsables = new ArrayList<>();
    }

    private RepositorioMazo repositorioMazo;
    private List<List<Carta>> columnas;
    private List<Carta> cartasUsables;

    public void llenarColumna(int cantidad) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= i; j++)
                columnas.get(i).add(repositorioMazo.sacarCarta());

        }
    }

    public void marcarCartaArriba() {
        for (List<Carta> columna : columnas) {
            if (!columna.isEmpty())
                columna.get(columna.size() - 1).darVuelta();
        }
    }

    public void drawOne() {
        cartasUsables.add(repositorioMazo.drawOne());
    }

    public String verCartas() {
        String cartasVisibles = "";
        int i = 1;
        for (List<Carta> columna : columnas) {
            if (!columna.isEmpty()) {
                i++;
                cartasVisibles += "Columna: " + String.valueOf(i) + columna.getLast().toString() + "\n";
            }
        }
        cartasVisibles += cartasUsables.getLast().toString();
        return cartasVisibles;
    }
}
