package solitario;

import java.util.Scanner;

public class AppSolitario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        menuOpciones();
        // este creo que puedo ponerlo directamente en el repoPartida
        RepositorioMazo repoMazo = new RepositorioMazo();

        Partida partida = new Partida(repoMazo);

        while (opcion != 8) {
            System.out.print("Para comenzar seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:

                    break;
                case 2:
                    partida.verCartas();
                    break;

                case 3:
                    partida.drawOne();
                    break;
                case 4:

                    break;

                default:
                    System.out.println("Se ingreso una opcion no contemplada!!");

                    break;
            }
        }

        sc.close();

    }

    public static void menuOpciones() {
        System.out.println("Menu de opciones");
        System.out.println("Opcion 1) Reiniciar partida.");
        System.out.println("Opcion 2) Ver mis cartas.");
        System.out.println("Opcion 3) Robar una carta.");
        System.out.println("Opcion 8) Salir.");
    }
}
/*
 * tenes una mazo de cartas desde el as,2,3,4,5,6,7,8,9,j,q,k cada carta ademas
 * de su valor tiene su color/palo
 * una de 48 objetos cartas puede ser, el repositorio llamado mazo y una clase
 * llamada carta
 * un servicio que sea el que hace las jugadas y uno que guarde todos los datos
 * de la partida actual, otro repo para la partida?
 * los repos deberian tener un boton para reiniciar si el jugador quiere jugar
 * otra partida
 * es decir el boton ejecuta una funcion resetear()
 */