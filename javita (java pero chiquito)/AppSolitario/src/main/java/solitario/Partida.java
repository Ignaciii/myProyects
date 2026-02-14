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

    private Mazo mazo;
    private List<Columna> columnas;
    private List<Carta> cartasUsables;
    private PalosArmados palosArmados;

    public void llenarColumna() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= i; j++) {
                Carta carta = mazo.drawOne();
                columnas.get(i).llenarColumna(carta);

            }
            columnas.get(i).marcarCartaArriba();
        }
    }

    public void drawOne() {
        Carta carta = mazo.drawOne();
        if (carta != null) {
            carta.darVuelta();
            cartasUsables.add(carta);
        } else {
            System.out.println("No hay mas cartas en el mazo!!!");
        }

    }

    public void reiniciarPartida() {

        mazo.resetear();
        palosArmados.resetear();

        cartasUsables.clear();

        for (Columna col : columnas) {
            col.resetear();
        }
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
            Carta carta = columnas.get(columna - 1).sacarDeColumna();

            return carta;
        } else {
            return null;
        }
    }

    public Boolean agregarCartaColumna(Carta carta, int columna) {
        return columnas.get(columna - 1).agregarCartaColumna(carta);

    }

    public Carta usarCartaRobada() {
        if (!cartasUsables.isEmpty()) {
            Carta carta = cartasUsables.getLast();
            cartasUsables.removeLast();
            return carta;
        }
        return null;
    }

    public Boolean agregarCartaSolucion(Carta carta) {
        return palosArmados.agregarCartaSolucion(carta);
    }

    public void devolverCartaRobada(Carta carta) {
        cartasUsables.add(carta);
    }

    public void verSolucion() {
        palosArmados.verSolucion();
    }

    public void rollbackColumna(Carta carta, int columnaOrigen) {
        columnas.get(columnaOrigen - 1).rollbackColumna(carta);
    }

    public Boolean validarVictoria() {
        return palosArmados.contarCartas() == 52;
    }

}
