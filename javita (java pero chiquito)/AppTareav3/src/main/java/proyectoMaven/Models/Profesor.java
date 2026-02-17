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
        this.materias = new ArrayList<>();
        this.esActivo = true;
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
    @Column(name = "esActivo", nullable = false)
    private Boolean esActivo = true;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "profesorXmateria", joinColumns = @JoinColumn(name = "numeroMatricula"), inverseJoinColumns = @JoinColumn(name = "codigoMateria"))
    private List<Materia> materias;

    public void agregarMateria(Materia materia) {
        if (materia != null && !this.materias.contains(materia)) {
            this.materias.add(materia);
            materia.agregarProfesor(this);
        }

    }

    public void cambiarEstado() {
        this.esActivo = !esActivo;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public Boolean estaActivo() {
        return esActivo;
    }

}
