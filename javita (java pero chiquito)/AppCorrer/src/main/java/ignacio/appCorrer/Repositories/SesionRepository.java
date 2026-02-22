package ignacio.appCorrer.Repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ignacio.appCorrer.Models.SesionEntrenamiento;

//esto es todo lo que hay que hacer para que tenga los comportamientos de crudRepository, chau sesionDao
public interface SesionRepository extends CrudRepository<SesionEntrenamiento, Integer> {

    public Iterable<SesionEntrenamiento> findByTerreno(String terreno);

    @Query("SELECT s FROM SesionEntrenamiento s WHERE duracionTotal > :duracion")
    public Iterable<SesionEntrenamiento> findByDuracion(Double duracion);

    public Iterable<SesionEntrenamiento> findByTerrenoAndDuracionTotalGreaterThan(String terreno, Double duracion);

    @Query("SELECT s FROM SesionEntrenamiento s WHERE fecha BETWEEN :fecha1 AND :fecha2")
    public Iterable<SesionEntrenamiento> findByIntervaloFechas(LocalDate fecha1, LocalDate fecha2);

    public Iterable<SesionEntrenamiento> findAllByOrderByFechaDesc();
}
