package proyectoMaven.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "comision")
@Entity
public class Comision {
    public Comision() {
    }

    public Comision(String nombre, int año) {
        this.nombre = nombre;
        this.año = año;
        this.materias = new ArrayList<>();
        this.esActivo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "año", nullable = false)
    private int año;
    @Column(name = "esActivo", nullable = false)
    private Boolean esActivo = true;

    @ManyToMany(mappedBy = "comisiones")
    private List<Materia> materias;

    public int getId() {
        return id;
    }

    public Boolean agregarMateria(Materia materia) {
        if (materia != null && !this.materias.contains(materia)) {
            this.materias.add(materia);
            materia.dictarEnComision(this);
            return true;
        }
        return false;
    }

    public void cambiarEstado() {
        this.esActivo = !esActivo;
    }

    public Boolean estaActivo() {
        return esActivo;

    }

    public String getNombre() {
        return nombre;
    }

    public String toString() {
        String str = "";
        str += "Id de la comision: " + id + "| Nombre: " + nombre + "| Año: " + año + "";
        return str;
    }

}
