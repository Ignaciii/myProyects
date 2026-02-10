package ignacio;

public class SesionEntrenamiento {
    public SesionEntrenamiento(int sesion, String terreno, Double distancia, Double duracionTotal, int intervalosIda,
            int intervalosVuelta, Double tiempoTroteIda, Double tiempoCaminataIda, Double tiempoTroteVuelta,
            Double tiempoCaminataVuelta, String sensacionFinal,
            String anotaciones) {

        this.terreno = terreno;
        this.distancia = distancia;
        this.duracionTotal = duracionTotal;
        this.intervalosIda = intervalosIda;
        this.intervalosVuelta = intervalosVuelta;
        this.tiempoTroteIda = tiempoTroteIda;
        this.tiempoCaminataIda = tiempoCaminataIda;
        this.tiempoTroteVuelta = tiempoTroteVuelta;
        this.tiempoCaminataVuelta = tiempoCaminataVuelta;
        this.sensacionFinal = sensacionFinal;
        this.anotaciones = anotaciones;
        this.sesion = sesion;

    }

    private String terreno;
    private Double distancia;
    private Double duracionTotal;
    private int intervalosIda;
    private int intervalosVuelta;
    private Double tiempoTroteIda;
    private Double tiempoCaminataIda;
    private Double tiempoTroteVuelta;
    private Double tiempoCaminataVuelta;
    private String sensacionFinal;
    private String anotaciones;
    private int sesion;

    public int getSesion() {
        return sesion;
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

    public int getIntervalosIda() {
        return intervalosIda;
    }

    public int getIntervalosVuelta() {
        return intervalosVuelta;
    }

    public Double getTiempoTroteIda() {
        return tiempoTroteIda;
    }

    public Double getTiempoCaminataIda() {
        return tiempoCaminataIda;
    }

    public Double getTiempoTroteVuelta() {
        return tiempoTroteVuelta;
    }

    public Double getTiempoCaminataVuelta() {
        return tiempoCaminataVuelta;
    }

    public String getSensacionFinal() {
        return sensacionFinal;
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public String toString() {

        return "\n==============================================================" +
                "\n  REPORTE DE SESIÓN Nº: " + this.sesion +
                "\n==============================================================" +
                "\n> Terreno: " + this.terreno +
                "\n> Distancia: " + this.distancia + " km" +
                "\n> Duración App: " + this.duracionTotal + " hs" +
                "\n> Intervalos de ida: " + this.intervalosIda +
                "\n> Intervalos de vuelta: " + this.intervalosVuelta +
                "\n> Tiempo de trote de ida: " + this.tiempoTroteIda + " min " +
                "\n> Tiempo caminata de ida: " + this.tiempoCaminataIda + " min " +
                "\n> Tiempo trote de vuelta: " + this.tiempoTroteVuelta + " min " +
                "\n> Tiempo caminata de vuelta: " + this.tiempoCaminataVuelta + " min " +
                "\n> Sensación: " + this.sensacionFinal +
                "\n> Anotaciones: " + this.anotaciones +
                "\n--------------------------------------------------------------";
    }
}
