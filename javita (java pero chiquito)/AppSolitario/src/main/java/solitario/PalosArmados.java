package solitario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalosArmados {
    public PalosArmados() {

        this.palosArmados = new HashMap<>();
        inicializarMap();
    }

    private Map<String, List<Carta>> palosArmados;

    public void inicializarMap() {

        palosArmados.put("Corazones", new ArrayList<Carta>());
        palosArmados.put("Diamantes", new ArrayList<Carta>());
        palosArmados.put("Picas", new ArrayList<Carta>());
        palosArmados.put("Treboles", new ArrayList<Carta>());
    }

    public void agregarCartaSolucion(Carta carta) {
        String palo = carta.getPalo();

        if (palosArmados.containsKey(palo)) {
            if (validarCartaConSolucion(carta, palosArmados.get(palo))) {
                palosArmados.get(palo).add(carta);
                System.out.println("Jugada realizada");
            } else {
                System.out.println("Jugada invalida, no se puede agregar esa carta alli");
            }
        } else {
            System.out.println("El palo de esa carta no lo conozco!!!");
        }
    }

    // DONE: fijarse como cambiar esto para que funcione con el mapa

    public Boolean validarCartaConSolucion(Carta carta, List<Carta> lista) {

        if (carta.getNumero() == 1) {
            return true;

        } else if (carta.getNumero() == lista.getLast().getNumero() + 1) {
            return true;
        }

        else {
            return false;
        }

    }

    public void resetear() {
        palosArmados.values().forEach(lista -> lista.clear());

    }

}
