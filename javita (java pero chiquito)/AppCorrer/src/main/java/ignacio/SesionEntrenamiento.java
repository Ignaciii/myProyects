package ignacio;

public class SesionEntrenamiento {
    public SesionEntrenamiento(int id, String terreno, Double distancia, Double duracionTotal) {

        this.terreno = terreno;
        this.distancia = distancia;
        this.duracionTotal = duracionTotal;
        this.id = id;

    }

    public SesionEntrenamiento() {
    }

    private String terreno;
    private Double distancia;
    private Double duracionTotal;

    private int id;

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

        return "\n==============================================================" +
                "\n  REPORTE DE SESIÓN Nº: " + id +
                "\n==============================================================" +
                "\n> Terreno: " + terreno +
                "\n> Distancia: " + distancia + " km" +
                "\n> Duración App: " + duracionTotal + " hs" +
                "\n--------------------------------------------------------------";
    }
}
