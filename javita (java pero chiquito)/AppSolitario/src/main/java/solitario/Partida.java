package solitario;

import java.util.List;
import java.util.ArrayList;

public class Partida {
    public Partida(RepositorioMazo mazo) {
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

    public void llenarColumna() {
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
        if (repositorioMazo.drawOne() != null) {
            cartasUsables.add(repositorioMazo.drawOne());
        } else {
            System.out.println("No hay mas cartas en el mazo!!!");
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
        cartasVisibles += cartasUsables.getLast().toString();
        return cartasVisibles;
    }

    public void reiniciarPartida() {
        repositorioMazo.cargarMazo();
        columnas.clear();
        llenarColumna();
    }

    // deberia poder: sacar una carta de una columna (no se pueden tener mas de 1 en
    // la mano en cualquier momento)
    public Carta sacarDeColumna(int columna) {
        if (columna > 0 && columna < 8) {
            Carta carta = columnas.get(columna).getLast();
            columnas.get(columna).removeLast();
            return carta;
        } else {
            return null;
        }
    }

    // deberia poder agregar una carta a una columna o al mazo resuelto segun
    // corresponda

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
