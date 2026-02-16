package proyectoMaven.Models;

import jakarta.persistence.*;
import java.util.List;

@Table(name = "profesor")
@Entity
public class Profesor {
    public Profesor() {
    }

    public Profesor(int numeroMatricula, String nombre, String apellido, Boolean titular, List<Curso> cursos) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titular = titular;
        this.cursos = cursos;
    }

    @Id
    private int numeroMatricula;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellido", nullable = false)
    private String apellido;
    @Column(name = "titular", nullable = false)
    private Boolean titular;
    @Column(name = "cursos", nullable = false)
    @OneToMany(mappedBy = "profesor")
    private List<Curso> cursos;

}
