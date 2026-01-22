import java.util.InputMismatchException;
import java.util.Scanner;

public class AppTarea {

    public AppTarea() {
    }

    public static void main(String[] args) {
        double[][] notas = cargarNotas();

        // OJO ACÁ: Primero preguntamos si NO es null, y RECIÉN AHÍ calculamos.
        // Si lo calculás afuera y notas es null, te explota el programa.
        if (notas != null) {
            double[] promedios = calcularPromedio(notas);

            System.out.println(
                    "Para el primer alumno (ID: " + notas[0][0] + ") sus notas son: " + notas[0][1] + " y "
                            + notas[0][2]
                            + ", alcanzando un promedio de: " + promedios[0]);

            System.out.println("Para el segundo alumno (ID: " + notas[1][0] + ") sus notas son: " + notas[1][1] + " y "
                    + notas[1][2] + ", alcanzando un promedio de: " + promedios[1]);

            System.out.println(
                    "Para el tercer alumno (ID " + notas[2][0] + ") sus notas son: " + notas[2][1] + " y " + notas[2][2]
                            + ", alcanzando un promedio de: " + promedios[2]);
        } else {
            // Acá te faltaba el punto y coma, culiau!
            System.out.println("Error: Se intentó ingresar un caracter no numérico y se canceló la carga.");
        }
    }

    // ACÁ ESTABA EL LÍO GRANDE: Habías borrado la firma del método
    public static double[][] cargarNotas() {
        Scanner scanner = new Scanner(System.in);
        double[][] notas = new double[3][3];

        try {
            for (int i = 0; i < 3; i++) {
                System.out.print("Cargando Alumno: " + (i + 1) + ":"); // Un mensajito para guiar
                for (int j = 0; j < 3; j++) {
                    notas[i][j] = scanner.nextDouble();
                }
            }
            return notas; // Si sale todo bien, devolvemos la matriz llena

        } catch (InputMismatchException e) {
            System.out.println("¡Ingreso erróneo de datos! Metiste texto en vez de número.");
            return null; // Si falla, devolvemos null para que el main sepa
        } finally {
            System.out.println("--- Fin del proceso de carga ---");
            scanner.close();
        }
    }

    public static double[] calcularPromedio(double[][] notas) {
        double promedioUno = 0;
        double promedioDos = 0;
        double promedioTres = 0;
        double[] listaPromedios = new double[3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0 && j > 0) {
                    promedioUno += notas[i][j];
                } else if (i == 1 && j > 0) {
                    promedioDos += notas[i][j];
                } else if (i == 2 && j > 0) {
                    promedioTres += notas[i][j];
                }
            }
        }
        listaPromedios[0] = promedioUno / 2;
        listaPromedios[1] = promedioDos / 2;
        listaPromedios[2] = promedioTres / 2;
        return listaPromedios;
    }
}