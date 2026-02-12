package solitario;

import java.util.ArrayList;
import java.util.List;

public class PalosArmados {
    public PalosArmados() {
        // TODO: estos van en el diccionario
        this.corazones = new ArrayList<>();
        this.treboles = new ArrayList<>();
        this.diamantes = new ArrayList<>();
        this.picas = new ArrayList<>();

    }

    private List<Carta> corazones;
    private List<Carta> treboles;
    private List<Carta> diamantes;
    private List<Carta> picas;

    public void agregarCartaSolucion(Carta carta) {
        String palo = carta.getPalo();
        Boolean resultado;

        // TODO: hacer volar esto de aca meter un diccionario y listo donde la clave sea
        // el nombre del palo y el valor las lista de cartas
        switch (palo) {

            case "Corazones":
                resultado = validarCartaConSolucion(carta, corazones);
                if (resultado) {
                    corazones.add(carta);
                } else {
                    System.out.println("Jugada invalida, no se puede agregar esa carta ahi !!");
                }
                break;

            case "Diamantes":
                resultado = validarCartaConSolucion(carta, diamantes);
                if (resultado) {
                    diamantes.add(carta);
                } else {
                    System.out.println("Jugada invalida, no se puede agregar esa carta ahi !!");
                }
                break;

            case "Treboles":
                resultado = validarCartaConSolucion(carta, treboles);
                if (resultado) {
                    treboles.add(carta);
                } else {
                    System.out.println("Jugada invalida, no se puede agregar esa carta ahi !!");
                }
                break;

            case "Picas":
                resultado = validarCartaConSolucion(carta, picas);
                if (resultado) {
                    picas.add(carta);
                } else {
                    System.out.println("Jugada invalida, no se puede agregar esa carta ahi !!");
                }
                break;

        }
    }

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
        corazones.clear();
        treboles.clear();
        diamantes.clear();
        picas.clear();
    }

}
