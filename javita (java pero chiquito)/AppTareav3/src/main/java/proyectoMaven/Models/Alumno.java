package proyectoMaven.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "alumno")
@Entity
public class Alumno {
    public Alumno() {
    }

    public Alumno(String nombre, int legajo, Double parcial1, Double parcial2) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.parcial1 = parcial1;
        this.parcial2 = parcial2;
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

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "alumnoXmateria", joinColumns = @JoinColumn(name = "legajo"), inverseJoinColumns = @JoinColumn(name = "codigoMateria"))
    private List<Materia> materias;

    public void agregarMateria(Materia materia) {
        if (materia != null && !this.materias.contains(materia)) {
            this.materias.add(materia);
            materia.agregarAlumno(this);
        }

    }

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
        String str = "Alumno: " + nombre + "| Legajo es: " + legajo + "| Primer parcial: " + parcial1
                + "| Segundo parcial: "
                + parcial2 + "| Materias inscriptas: " + materias.size();

        return str;
    }

    public void setMaterias(List<Materia> materias) {
        if (!materias.isEmpty())
            this.materias = materias;
    }

    public void cambiarEstado() {
        this.esActivo = !esActivo;
    }

}
