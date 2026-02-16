package proyectoMaven.Models;

import jakarta.persistence.*;
import java.util.List;

@Table(name = "curso")
@Entity
public class Curso {
    public Curso() {
    }

    public Curso(String nombre, int año, int id, Profesor profesor, List<Alumno> alumnos, Materia materia) {
        this.nombre = nombre;
        this.id = id;
        this.año = año;
        this.profesor = profesor;
        this.alumnos = alumnos;
        this.materia = materia;
    }

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "año", nullable = false)
    private int año;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "profesor", nullable = false)
    @ManyToOne
    private Profesor profesor;

    @Column(name = "alumnos", nullable = false)
    @OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos;

    @Column(name = "materia", nullable = false)
    @ManyToOne
    private Materia materia;

    public String getNombre() {
        return nombre;
    }

    public int getAño() {
        return año;
    }

    public int getId() {
        return id;
    }
}
