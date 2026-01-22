public class Carta {

    public Carta() {
    }

    public Carta(String color, String valor) {
        this.color = color;
        this.valor = valor;
        this.visible = false;
    }

    private String color;
    private String valor;
    private Boolean visible;

    public void darVuelta() {
        visible = true;
    }

    public String getColor() {
        return color;
    }

    public String getValor() {
        return valor;
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