package proyectoMaven.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "alumno")
@Entity
public class Alumno {
    public Alumno() {
    }

    public Alumno(String nombre, int legajo, Double parcial1, Double parcial2, Curso curso, List<Materia> materias) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.parcial1 = parcial1;
        this.parcial2 = parcial2;
        this.curso = curso;
        this.materias = new ArrayList<>();
        this.esActivo = true;
    }

    @Id
    @Column(name = "legajo")
    private int legajo;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "parcial1", nullable = false)
    private Double parcial1;
    @Column(name = "parcial2", nullable = false)
    private Double parcial2;
    @Column(name = "esActivo", nullable = false)
    private Boolean esActivo = true;

    @ManyToOne
    private Curso curso;
    @ManyToMany
    private List<Materia> materias;

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

    public Boolean estaActivo() {
        return esActivo;
    }

    public Double calcularPromedio() {

        return (this.parcial1 + this.parcial2) / 2;
    }

    public String toString() {
        return "Alumno: " + nombre + "\n Legajo es: " + legajo + "\nPrimer parcial: " + parcial1 + "\nSegundo parcial: "
                + parcial2;
    }

    public void setMaterias(List<Materia> materias) {
        if (!materias.isEmpty())
            this.materias = materias;
    }

    public void cambiarEstado() {
        this.esActivo = !esActivo;
    }

}
