package solitario;

import java.util.ArrayList;
import java.util.List;

public class Columna {
    public Columna(int numeroDeColumna) {
        this.elementos = new ArrayList<>();
        this.numeroDeColumna = numeroDeColumna;
    }

    private List<Carta> elementos;
    private int numeroDeColumna;

    public List<Carta> getElementos() {
        return elementos.stream().toList();
    }

    public void marcarCartaArriba() {
        if (!elementos.isEmpty())
            elementos.getLast().darVuelta();
    }

    public String verCartas() {

        if (!elementos.isEmpty()) {
            String str = "";
            for (Carta e : elementos) {
                if (e.visible()) {
                    str += e.toString() + " ";
                } else {
                    str += "X ";
                }
            }
            return "Columna " + numeroDeColumna + "| " + str + "\n";
        } else
            return "Columna " + numeroDeColumna + "| vacia.\n";
    }

    public Carta sacarDeColumna() {
        if (!elementos.isEmpty()) {
            Carta carta = elementos.getLast();
            elementos.removeLast();
            if (!elementos.isEmpty()) {
                elementos.getLast().darVuelta();
            }
            return carta;
        } else
            return null;
    }

    public Boolean agregarCartaColumna(Carta carta) {
        if (validarCarta(carta)) {
            elementos.add(carta);
            return true;
        } else {

            return false;
        }
    }

    public Boolean validarCarta(Carta cartaAValidar) {

        if (cartaAValidar.getNumero() == 13 && elementos.isEmpty()) {
            return true;
        } else if (cartaAValidar.getNumero() != 13 && !elementos.isEmpty()) {
            if ((cartaAValidar.getColor() != elementos.getLast().getColor())
                    && (cartaAValidar.getNumero() == elementos.getLast().getNumero() - 1)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public void llenarColumna(Carta carta) {
        elementos.add(carta);
    }

    public void rollbackColumna(Carta carta) {
        if (!elementos.isEmpty()) {
            elementos.getLast().darVuelta();
            elementos.add(carta);
        } else {
            elementos.add(carta);
        }

    }

    public void resetear() {
        elementos.clear();
    }

}
