package ignacio.appCorrer.Controllers;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ignacio.appCorrer.Models.SesionEntrenamiento;
import ignacio.appCorrer.Repositories.SesionRepository;

@RestController // hola soy un controlador
@RequestMapping("/api/sesiones") // todos mis endpoints van a empezar asi
public class SesionController {

    // inyectale esta dependencia por el constructor spring gracias
    public SesionController(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    // nadie tiene que cambiar esto
    public final SesionRepository sesionRepository;

    // devolveme esto gracias, era el mostrar sesiones
    @GetMapping
    public Iterable<SesionEntrenamiento> visualizarSesiones() {
        return sesionRepository.findAll();
    }

    // con lo que te hayan pasado arma la sesion y guardala en la bd
    @PostMapping("/cargar")
    public SesionEntrenamiento cargarSesion(@RequestBody SesionEntrenamiento sesionEntrenamiento) {
        return sesionRepository.save(sesionEntrenamiento);
    }

    // el valor que pongan ultimo en la URL es el ID del objeto a matar
    @DeleteMapping("/borrar/{id}")
    public void borrarSesion(@PathVariable Integer id) {
        sesionRepository.deleteById(id);
    }

    @GetMapping("/terreno/{terreno}")
    public Iterable<SesionEntrenamiento> visualizarPorTerreno(@PathVariable String terreno) {
        return sesionRepository.findByTerreno(terreno);
    }

    @GetMapping("/duracion/{duracion}")
    public Iterable<SesionEntrenamiento> visualizarPorDuracion(@PathVariable Double duracion) {
        return sesionRepository.findByDuracion(duracion);
    }

    @GetMapping("/practica1/{terreno}/{duracion}")
    public Iterable<SesionEntrenamiento> visualizarPractica1(@PathVariable String terreno,
            @PathVariable Double duracion) {
        return sesionRepository.findByTerrenoAndDuracionTotalGreaterThan(terreno, duracion);

    }

    @GetMapping("/practica2/{fecha1}/{fecha2}")
    public Iterable<SesionEntrenamiento> visualizarPractica2(@PathVariable LocalDate fecha1,
            @PathVariable LocalDate fecha2) {
        return sesionRepository.findByIntervaloFechas(fecha1, fecha2);
    }

    @GetMapping("/ordenados")
    public Iterable<SesionEntrenamiento> visualizarPractica3() {
        return sesionRepository.findAllByOrderByFechaDesc();
    }

}
