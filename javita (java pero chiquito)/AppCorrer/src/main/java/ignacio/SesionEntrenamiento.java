package ignacio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;

@Table(name = "Sesiones")
@Entity
public class SesionEntrenamiento {
    public SesionEntrenamiento(String terreno, Double distancia, Double duracionTotal, LocalDate fecha) {

        this.terreno = terreno;
        this.distancia = distancia;
        this.duracionTotal = duracionTotal;
        this.fecha = fecha;
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
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

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

    public LocalDate getFecha() {
        return fecha;
    }

    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaS = fecha.format(formato);

        return "Sesion: N" + id + " | Tipo de terreno: " + terreno + " | Distancia: " + distancia
                + " | Duracion total: " + duracionTotal + " minutos | Fecha: " + fechaS;
    }
}
