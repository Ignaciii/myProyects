package ignacio.appCorrer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ignacio.appCorrer.Models.SesionEntrenamiento;

public class SesionEntrenamientoTest {
    @Test
    public void testCrearSesion() {
        SesionEntrenamiento sesion = new SesionEntrenamiento();
        sesion.setTerreno("LLANO");
        sesion.setDistancia(11.69);
        sesion.setDuracionTotal(101.0);
        sesion.setFecha(LocalDate.of(2026, 02, 21));
        assertEquals("LLANO", sesion.getTerreno(), "el terreno tiene que coincidir");
        assertEquals(11.69, sesion.getDistancia(), "la distancia tiene que coincidir");
        assertEquals(101.0, sesion.getDuracionTotal(), "la duracion debe coincidir");

    }
}
