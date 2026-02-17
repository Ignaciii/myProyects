package proyectoMaven.Models;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Table(name = "materia")
@Entity
public class Materia {
    public Materia() {
    }

    public Materia(Boolean troncal, String duracion, String nombre, List<Comision> comisiones) {

        this.troncal = troncal;
        this.duracion = duracion;
        this.nombre = nombre;
        this.alumnos = new ArrayList<>();
        this.profesores = new ArrayList<>();
        this.comisiones = comisiones;
        this.esActivo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(mappedBy = "materias")
    private List<Profesor> profesores;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "materiaXcomision", joinColumns = @JoinColumn(name = "codigoMateria"), inverseJoinColumns = @JoinColumn(name = "comisionId"))
    private List<Comision> comisiones;

    public void agregarAlumno(Alumno alumno) {
        if (alumno != null && !this.alumnos.contains(alumno)) {
            this.alumnos.add(alumno);
            alumno.agregarMateria(this);
        }

    }

    public void agregarProfesor(Profesor profesor) {
        if (profesor != null && !this.profesores.contains(profesor)) {
            this.profesores.add(profesor);
            profesor.agregarMateria(this);

        }

    }

    public Boolean dictarEnComision(Comision comision) {
        if (comision != null && !this.comisiones.contains(comision)) {
            this.comisiones.add(comision);
            comision.agregarMateria(this);
            return true;
        }
        return false;

    }

    public void cambiarEstado() {
        this.esActivo = !esActivo;
    }

    public int getCodigoMateria() {
        return codigoMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean estaActivo() {
        return esActivo;
    }

    public String toString() {
        return "Nombre: " + nombre + " | Duracion: " + duracion + " | Codigo de materia: " + codigoMateria
                + "| Es troncal: " + (troncal ? "Si" : "No");
    }
}
