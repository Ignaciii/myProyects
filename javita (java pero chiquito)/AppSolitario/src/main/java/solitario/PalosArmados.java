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

    public Boolean agregarCartaSolucion(Carta carta) {
        String palo = carta.getPalo();

        if (palosArmados.containsKey(palo)) {
            if (validarCartaConSolucion(carta, palosArmados.get(palo))) {
                palosArmados.get(palo).add(carta);
                System.out.println("Jugada realizada");
                return true;
            } else {
                System.out.println("Jugada invalida, no se puede agregar esa carta alli");
            }
        } else {
            System.out.println("El palo de esa carta no lo conozco!!!");
        }
        return false;
    }

    public Boolean validarCartaConSolucion(Carta carta, List<Carta> lista) {

        if (lista.isEmpty()) {
            return carta.getNumero() == 1;

        } else {
            return (!lista.isEmpty() && carta.getNumero() == lista.getLast().getNumero() + 1);

        }
    }

    public void resetear() {
        palosArmados.values().forEach(lista -> lista.clear());
    }

    public void verSolucion() {

        System.out.println("--- CARTAS EN LOS PALOS ---");
        palosArmados.forEach((palo, lista) -> {
            System.out.print(palo + ": ");
            if (lista.isEmpty()) {
                System.out.println("[ VACIO ]");
            } else {
                // Mostramos solo la de arriba, que es la que importa en la solución
                System.out.println("[" + lista.getLast().toString() + "]");
            }
        });
    }

    public int contarCartas() {
        int cartasSolucion = 0;
        for (List<Carta> lista : palosArmados.values()) {
            cartasSolucion += lista.size();
        }
        return cartasSolucion;
    }

}
