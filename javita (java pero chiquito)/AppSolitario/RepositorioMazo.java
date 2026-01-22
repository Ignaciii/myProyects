import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.Collections;

public class RepositorioMazo implements Iterable<Carta> {
    public RepositorioMazo() {
        this.mazo = new ArrayList<>(); // capaz en un futuro cambiar a diccionario

    }

    private List<Carta> mazo;

    public void cargarMazo() {
        if (mazo.isEmpty()) {
            String[] colores = { "Diamantes", "Treboles", "Corazones", "Picas" };
            String[] valores = { "As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
            for (int i = 0; i < colores.length; i++) {
                for (int j = 0; j < valores.length; j++) {
                    mazo.add(new Carta(colores[i], valores[j]));
                }
            }
        }
    }

    public Iterator<Carta> iterator() {
        return mazo.iterator();
    }

    public void resetearMazo() {
        mazo.clear();
        cargarMazo();
    }

    public List<Carta> getMazo() {
        if (mazo.isEmpty())
            cargarMazo();
        return mazo.stream().toList();
    }

    public Carta sacarCarta() {
        Carta carta = mazo.getLast();
        mazo.removeLast();
        return carta;

    }

    public void shuffle() {
        Collections.shuffle(mazo);
    }

    public Carta drawOne() {
        if (!mazo.isEmpty()) {
            Carta cartaRobada = mazo.getLast();
            mazo.removeLast();
            cartaRobada.darVuelta();
            return cartaRobada;
        } else {
            return null;
        }

    }

    /*
     * mas casera
     * 
     * public void shuffle() {
     * Random generator = new Random();
     * for (int i = 0; i < mazo.size(); i++) {
     * int pos1 = generator.nextInt(mazo.size() - 1);
     * Carta mezclada = mazo.get(pos1);
     * mazo.set(i, mazo.get(pos1));
     * mazo.set(pos1, mezclada);
     * }
     * 
     * }
     */

}