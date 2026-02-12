package solitario;

public class Carta {

    public Carta() {
    }

    public Carta(String palo, String valor, int numero, String color) {
        this.palo = palo;
        this.valor = valor;
        this.visible = false;
        this.color = color;

        this.numero = numero;
    }

    private String palo;
    private String valor;
    private Boolean visible;
    private int numero;
    private String color;

    public void darVuelta() {
        visible = true;
    }

    public String getPalo() {
        return palo;
    }

    public String getValor() {
        return valor;
    }

    public int getNumero() {
        return numero;
    }

    public Boolean visible() {
        return visible;
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        return valor + palo;

    }
}