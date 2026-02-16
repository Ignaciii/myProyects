package proyectoMaven.Models;

import java.util.List;

import jakarta.persistence.*;

@Table(name = "materia")
@Entity
public class Materia {
    public Materia() {
    }

    public Materia(int codigoMateria, Boolean troncal, String duracion, String nombre, List<Alumno> alumnos,
            List<Curso> cursos) {
        this.codigoMateria = codigoMateria;
        this.troncal = troncal;
        this.duracion = duracion;
        this.nombre = nombre;
        this.alumnos = alumnos;
        this.cursos = cursos;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigoMateria;

    @Column(name = "troncal", nullable = false)
    private Boolean troncal;
    @Column(name = "duracion", nullable = false)
    private String duracion;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "alumnos", nullable = false)
    @ManyToMany(mappedBy = "curso")
    private List<Alumno> alumnos;

    @Column(name = "cursos", nullable = false)
    @OneToMany(mappedBy = "materia")
    private List<Curso> cursos;

}
