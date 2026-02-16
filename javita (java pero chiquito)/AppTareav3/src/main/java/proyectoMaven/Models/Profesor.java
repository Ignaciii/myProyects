package proyectoMaven.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "profesor")
@Entity
public class Profesor {
    public Profesor() {
    }

    public Profesor(int numeroMatricula, String nombre, String apellido, Boolean titular) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titular = titular;
        this.cursos = new ArrayList<>();
    }

    @Id
    @Column(name = "numeroMatricula", nullable = false)
    private int numeroMatricula;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellido", nullable = false)
    private String apellido;
    @Column(name = "titular", nullable = false)
    private Boolean titular;

    @ManyToMany
    private List<Curso> cursos;

    public void setCursos(List<Curso> cursos) {
        if (!cursos.isEmpty())
            this.cursos = cursos;
    }

}
