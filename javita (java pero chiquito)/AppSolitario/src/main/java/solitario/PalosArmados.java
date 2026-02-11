package solitario;

import java.util.ArrayList;
import java.util.List;

//este va a contener los 4 palos de la solucion 
public class PalosArmados {
    public PalosArmados() {
        this.corazones = new ArrayList<>();
        this.treboles = new ArrayList<>();
        this.diamantes = new ArrayList<>();
        this.picas = new ArrayList<>();

    }

    private List<Carta> corazones;
    private List<Carta> treboles;
    private List<Carta> diamantes;
    private List<Carta> picas;

    // Todo: chequear que la carta se condiga con el palo de la lista de solucion
    // ademas que sea el valor correcto
    // este metodo deberia permitir agregar una carta de las posibles es decir una
    // de las columnas, o de las visibles del mazo
    public void agregarCartaSolucion(Carta carta) {
        String color = carta.getColor();
        Boolean resultado;
        switch (color) {

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

    // retorna si es o no valida la operacion
    public Boolean validarCartaConSolucion(Carta carta, List<Carta> lista) {

        int numero = carta.getNumero();

        if (numero == 1) {
            return true;

        } else if (numero == lista.getLast().getNumero() + 1) {
            return true;
        }

        else {
            return false;
        }

    }

}
