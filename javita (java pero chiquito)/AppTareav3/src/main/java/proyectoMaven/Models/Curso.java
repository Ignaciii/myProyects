package proyectoMaven.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "curso")
@Entity
public class Curso {
    public Curso() {
    }

    public Curso(String nombre, int año, int id) {
        this.nombre = nombre;
        this.id = id;
        this.año = año;
        this.profesores = new ArrayList<>();
        this.alumnos = new ArrayList<>();
        this.materia = null;
        this.esActivo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "año", nullable = false)
    private int año;
    @Column(name = "esActivo", nullable = false)
    private Boolean esActivo;

    @ManyToMany(mappedBy = "cursos")
    private List<Profesor> profesores;
    @OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos;
    @ManyToOne
    private Materia materia;

    public void setAlumnos(List<Alumno> alumnos) {
        if (!alumnos.isEmpty())
            this.alumnos = alumnos;
    }

    public void setProfesor(List<Profesor> profesores) {
        if (!profesores.isEmpty())
            this.profesores = profesores;
    }

    public void setMateria(Materia materia) {
        if (materia != null)
            this.materia = materia;
    }

    public void cambiarEstado() {
        this.esActivo = !esActivo;
    }

}
