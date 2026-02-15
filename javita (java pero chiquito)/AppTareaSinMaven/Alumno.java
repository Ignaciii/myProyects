import java.util.Iterator;
import java.util.List;

public class Alumno {
    public Alumno() {
    }

    public Alumno(String nombre, int legajo, Double parcial1, Double parcial2) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.parcial1 = parcial1;
        this.parcial2 = parcial2;
    }

    private String nombre;
    private int legajo;
    private Double parcial1;
    private Double parcial2;

    public String getNombre() {
        return nombre;
    }

    public int getLegajo() {
        return legajo;
    }

    public Double getParcial1() {
        return parcial1;
    }

    public Double getParcial2() {
        return parcial2;

    }

    public Double calcularPromedio() {

        return (this.parcial1 + this.parcial2) / 2;
    }

    public String toString() {
        return "Alumno: " + nombre + "\n Legajo es: " + legajo + "\nPrimer parcial: " + parcial1 + "\nSegundo parcial: "
                + parcial2;
    }

}
