package solitario;

import java.util.ArrayList;
import java.util.Collections;
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
            return "Columna " + numeroDeColumna + "| [" + str + "]\n";
        } else
            return "Columna " + numeroDeColumna + "| [vacia]\n";
    }

    public List<Carta> sacarDeColumna(int cantidad) {
        List<Carta> cartas = new ArrayList<>();
        if (!elementos.isEmpty()) {
            if (cantidad == 1) {
                Carta carta = elementos.getLast();
                elementos.removeLast();
                if (!elementos.isEmpty()) {
                    if (!elementos.isEmpty() && !elementos.getLast().visible())
                        elementos.getLast().darVuelta();
                }
                cartas.add(carta);

            } else if (cantidad > 1) {

                if (!elementos.isEmpty()) {
                    cartas = sacarCartasColumna(cantidad);
                    if (!elementos.isEmpty() && !elementos.getLast().visible())
                        elementos.getLast().darVuelta();
                }
            }

            return cartas;
        } else
            return null;
    }

    // la cantidad aca dice cuantas cartas vamos a sacar, en el caso comun solo
    // sacamos una por eso devuelve el ultimo nada mas
    public Boolean agregarCartaColumna(List<Carta> listadoCarta) {
        if (!listadoCarta.isEmpty()) {
            if (validarCarta(listadoCarta.getLast()) && listadoCarta.size() == 1) {
                elementos.add(listadoCarta.getLast());
                return true;
            } else if (listadoCarta.size() > 1) {

                return agregarCartasColumna(listadoCarta);
            } else {

                return false;
            }
        } else
            return false;
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

    public void rollbackColumna(List<Carta> cartas) {
        if (!elementos.isEmpty() && cartas.size() == 1) {
            elementos.getLast().darVuelta();
            elementos.add(cartas.getLast());
        } else if (cartas.size() > 1) {
            rollbackVariasCartas(cartas);
        } else {
            elementos.add(cartas.getLast());
        }

    }

    public void resetear() {
        elementos.clear();
    }

    public Boolean agregarCartasColumna(List<Carta> listadoCartas) {
        if (validarCarta(listadoCartas.getFirst())) {
            listadoCartas.forEach(carta -> elementos.add(carta));
            return true;
        }
        return false;
    }

    public List<Carta> sacarCartasColumna(int cantidadASacar) {

        if (cantidadASacar > elementosVisibles() || elementos.isEmpty()) {
            return null;
        }
        List<Carta> cartas = new ArrayList<>();

        for (int i = 0; i < cantidadASacar; i++) {
            cartas.add(elementos.getLast());
            elementos.removeLast();
        }

        if (!elementos.isEmpty() && !elementos.getLast().visible())
            elementos.getLast().darVuelta();

        // return cartas.reversed(); funca pero antes de java 21 no se puede usar
        // reversed, asi que lo hago a mano
        Collections.reverse(cartas);
        return cartas;

    }

    public int elementosVisibles() {
        int elementosVisibles = 0;
        for (Carta c : elementos) {
            if (c.visible()) {
                elementosVisibles += 1;
            }
        }
        return elementosVisibles;
    }

    public void rollbackVariasCartas(List<Carta> cartas) {
        if (!cartas.isEmpty()) {
            for (Carta carta : cartas) {
                elementos.add(carta);
            }
        }

    }

}
