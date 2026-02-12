package solitario;

import java.util.List;
import java.util.ArrayList;

public class Partida {

    public Partida(Mazo mazo, PalosArmados palosArmados) {
        this.columnas = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            columnas.add(new Columna(i));
        }
        this.mazo = mazo;
        this.cartasUsables = new ArrayList<>();
        this.palosArmados = palosArmados;
    }

    // aca tambien
    private Mazo mazo;
    private List<Columna> columnas;
    private List<Carta> cartasUsables;
    private PalosArmados palosArmados;

    public void llenarColumna() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= i; j++)
                columnas.get(i).getElementos().add(mazo.sacarCarta());

        }
    }

    public void drawOne() {
        if (mazo.drawOne() != null) {
            cartasUsables.add(mazo.drawOne());
        } else {
            System.out.println("No hay mas cartas en el mazo!!!");
        }

    }

    public void reiniciarPartida() {

        mazo.resetear();
        palosArmados.resetear();

        columnas.clear();
        llenarColumna();
        System.out.println("Partida reinicida!!!");
    }

    public void marcarCartaArriba() {
        for (Columna columna : columnas) {
            columna.marcarCartaArriba();
        }
    }

    public String verCartas() {

        String cartasVisibles = "";

        for (Columna columna : columnas) {
            cartasVisibles += columna.verCartas();
        }
        if (!cartasUsables.isEmpty()) {
            cartasVisibles += "La ultima carta robada usable es: " + cartasUsables.getLast().toString();
        } else {
            cartasVisibles += "No hay cartas robadas";
        }

        return cartasVisibles;
    }

    public Carta sacarDeColumna(int columna) {
        if (columna > 0 && columna < 8) {
            return columnas.get(columna).sacarDeColumna();
        } else {
            return null;
        }
    }

    public void agregarCartaColumna(Carta carta, int columna) {
        columnas.get(columna).agregarCartaColumna(carta);

    }

}
