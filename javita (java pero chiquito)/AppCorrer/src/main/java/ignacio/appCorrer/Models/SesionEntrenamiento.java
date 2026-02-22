package ignacio.appCorrer.Models;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDate;
import jakarta.persistence.GenerationType;

@Table(name = "Sesiones")

@Data
@Entity
public class SesionEntrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "terreno", nullable = false)
    private String terreno;
    @Column(name = "distancia", nullable = false)
    private Double distancia;
    @Column(name = "duracionTotal", nullable = false)
    private Double duracionTotal;
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
}
