package solitario;

import java.util.List;
import java.util.ArrayList;

public class Partida {

    // TODO: cambiar el constructor, ya no son listas las columnas sino objetos
    public Partida(Mazo mazo, PalosArmados palosArmados) {
        this.columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            columnas.add(new ArrayList<>());
        }
        this.mazo = mazo;
        this.cartasUsables = new ArrayList<>();
        this.palosArmados = palosArmados;
    }

    // aca tambien
    private Mazo mazo;
    private List<List<Carta>> columnas;
    private List<Carta> cartasUsables;
    private PalosArmados palosArmados;

    public void llenarColumna() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= i; j++)
                columnas.get(i).add(mazo.sacarCarta());

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

    // TODO: todas estas funciones van a ir en la clase nueva llamada columna

    public void marcarCartaArriba() {
        for (List<Carta> columna : columnas) {
            if (!columna.isEmpty())
                columna.get(columna.size() - 1).darVuelta();
        }
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
        if (!cartasUsables.isEmpty()) {
            cartasVisibles += "La ultima carta robada usable es: " + cartasUsables.getLast().toString();
        }

        return cartasVisibles;
    }

    public Carta sacarDeColumna(int columna) {
        if (columna > 0 && columna < 8) {
            Carta carta = columnas.get(columna).getLast();
            columnas.get(columna).removeLast();
            return carta;
        } else {
            return null;
        }
    }

    public void agregarAColumnaDeColumna(Carta carta, int columna) {
        if (validarCarta(carta, columna)) {
            columnas.get(columna).add(carta);
        } else {
            System.out.println("No se puede agregar esa carta alli!!!");
        }
    }

    public Boolean validarCarta(Carta cartaAValidar, int columna) {

        if (cartaAValidar.getNumero() == 13 && columnas.get(columna).isEmpty()) {
            return true;
        } else if (cartaAValidar.getNumero() != 13 && !columnas.get(columna).isEmpty()) {
            if ((cartaAValidar.getColor() != columnas.get(columna).getLast().getColor())
                    && (cartaAValidar.getNumero() == columnas.get(columna).getLast().getNumero() - 1)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
