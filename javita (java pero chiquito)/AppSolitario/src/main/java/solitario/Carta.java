package solitario;

public class Carta {

    public Carta() {
    }

    public Carta(String color, String valor, int numero) {
        this.color = color;
        this.valor = valor;
        this.visible = false;

        this.numero = numero;
    }

    private String color;
    private String valor;
    private Boolean visible;
    private int numero;

    public void darVuelta() {
        visible = true;
    }

    public String getColor() {
        return color;
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

    public String toString() {
        if (visible) {
            return ",color: " + color + ", valor: " + valor;
        }
        return "invisible";
    }
}