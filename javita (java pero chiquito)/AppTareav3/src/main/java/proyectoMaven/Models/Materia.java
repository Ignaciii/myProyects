package proyectoMaven.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Table(name = "materia")
@Entity
public class Materia {
    public Materia() {
    }

    public Materia(int codigoMateria, Boolean troncal, String duracion, String nombre) {
        this.codigoMateria = codigoMateria;
        this.troncal = troncal;
        this.duracion = duracion;
        this.nombre = nombre;
        this.alumnos = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.esActivo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigoMateria")
    private int codigoMateria;

    @Column(name = "troncal", nullable = false)
    private Boolean troncal;
    @Column(name = "duracion", nullable = false)
    private String duracion;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "esActivo", nullable = false)
    private Boolean esActivo = true;

    @ManyToMany(mappedBy = "materias")
    private List<Alumno> alumnos;
    @OneToMany(mappedBy = "materia")
    private List<Curso> cursos;

    public void setAlumnos(List<Alumno> alumnos) {
        if (!alumnos.isEmpty())
            this.alumnos = alumnos;
    }

    public void setCursos(List<Curso> cursos) {
        if (!cursos.isEmpty())
            this.cursos = cursos;
    }

    public void cambiarEstado() {
        this.esActivo = !esActivo;
    }

    public int getCodigoMateria() {
        return codigoMateria;
    }

    public Boolean estaActivo() {
        return esActivo;
    }
}
