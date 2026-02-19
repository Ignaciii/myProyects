package ignacio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "Sesiones")
@Entity
public class SesionEntrenamiento {
    public SesionEntrenamiento(String terreno, Double distancia, Double duracionTotal) {

        this.terreno = terreno;
        this.distancia = distancia;
        this.duracionTotal = duracionTotal;

    }

    public SesionEntrenamiento() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "terreno", nullable = false)
    private String terreno;
    @Column(name = "distancia", nullable = false)
    private Double distancia;
    @Column(name = "duracionTotal", nullable = false)
    private Double duracionTotal;

    public int getNumeroSesion() {
        return id;
    }

    public String getTerreno() {
        return terreno;
    }

    public Double getDistancia() {
        return distancia;
    }

    public Double getDuracionTotal() {
        return duracionTotal;
    }

    public String toString() {

        return "Sesion: N" + id + " | Tipo de terreno: " + terreno + " | Distancia: " + distancia
                + " | Duracion total: " + duracionTotal;
    }
}
