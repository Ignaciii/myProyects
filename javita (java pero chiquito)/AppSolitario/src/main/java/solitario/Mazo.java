package solitario;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;

public class Mazo implements Iterable<Carta> {
    public Mazo() {
        this.mazo = new ArrayList<>(); // capaz en un futuro cambiar a diccionario
        cargarMazo();
    }

    private List<Carta> mazo;

    public void cargarMazo() {
        if (mazo.isEmpty()) {
            String[] palos = { "Diamantes", "Treboles", "Corazones", "Picas" };
            String[] valores = { "As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

            // el j + 1 es para manejar de forma mas facil el ordenamiento en los mazos
            // solucion
            for (int i = 0; i < palos.length; i++) {
                for (int j = 0; j < valores.length; j++) {
                    if (palos[i].equals("Diamantes") || palos[i].equals("Corazones")) {
                        mazo.add(new Carta(palos[i], valores[j], j + 1, "Rojo"));
                    } else {
                        mazo.add(new Carta(palos[i], valores[j], j + 1, "Negro"));
                    }

                }
            }
            shuffle();
        }

    }

    public Iterator<Carta> iterator() {
        return mazo.iterator();
    }

    public void resetear() {
        mazo.clear();
        cargarMazo();
        // shuffle();
    }

    public List<Carta> getMazo() {
        if (!mazo.isEmpty())
            return mazo;
        else
            return null;
    }

    public void shuffle() {
        Collections.shuffle(mazo);
    }

    public Carta drawOne() {
        if (!mazo.isEmpty()) {
            Carta cartaRobada = mazo.getLast();
            mazo.removeLast();
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