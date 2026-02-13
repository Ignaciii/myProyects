package solitario;

import java.util.Scanner;

public class AppSolitario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        menuOpciones();
        // este creo que puedo ponerlo directamente en el repoPartida
        Mazo mazo = new Mazo();
        PalosArmados palosArmados = new PalosArmados();
        Partida partida = new Partida(mazo, palosArmados);

        partida.llenarColumna();
        partida.verCartas();

        while (opcion != 8) {
            System.out.print("Para comenzar seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    partida.reiniciarPartida();

                    break;
                case 2:
                    partida.drawOne();
                    break;

                case 3:
                    System.out.print("Escribi 1 para sacar de una columna y 2 para sacar de las disponibles: ");
                    int eleccion = sc.nextInt();
                    if (eleccion == 1) {
                        System.out.print("Joya sacamos de una columna, ahora ingresa cual en numero: ");
                        int columnaOrigen = sc.nextInt();
                        if (columnaOrigen > 0 && columnaOrigen < 8) {

                            System.out.println("Bien, ahora a que columna la agregamos: ");
                            int columnaDestino = sc.nextInt();

                            if (columnaDestino > 0 && columnaDestino < 8) {

                                Carta carta = partida.sacarDeColumna(columnaOrigen);
                                partida.agregarCartaColumna(carta, columnaDestino);

                            } else {
                                System.out
                                        .println("Che hubo un problema, acordate que solo hay columnas de la 1 a la 7");
                            }

                        } else {
                            System.out.println("Che hubo un problema, acordate que solo hay columnas de la 1 a la 7");
                        }

                    } else if (eleccion == 2) {
                        System.out.println("Joya sacamos de las disponibles");
                        System.out.print("En que columna la agregamos: ");
                        int columnaDestino = sc.nextInt();

                        if (columnaDestino > 0 && columnaDestino < 8) {
                            Carta carta = partida.usarCartaRobada();
                            if (carta != null) {
                                partida.agregarCartaColumna(carta, columnaDestino);
                            } else {
                                System.out.println("Che no hay cartas de esas para usar");
                            }
                        }
                    } else {
                        System.out.println("Che me perdi, esa opcion no la tengo.");
                    }

                    break;
                case 4:
                    System.out.print("Dale, si es de una columna coloca 1 y si es de las usables coloca 2: ");
                    int opcionIngresada = sc.nextInt();
                    if (opcionIngresada == 1) {
                        System.out.print("De que columna la sacamos: ");
                        int columnaOrigen = sc.nextInt();
                        if (columnaOrigen > 0 && columnaOrigen < 8) {
                            Carta carta = partida.sacarDeColumna(columnaOrigen);
                            if (carta != null) {
                                partida.agregarCartaSolucion(carta);
                            } else {
                                System.out.println("Che no habia cartas en esa columna");
                            }

                        } else {
                            System.out.println("Upa nos pasamos de rosca con el numerito");
                        }
                    }

                    else if (opcionIngresada == 2) {
                    } else {
                        System.out.println("El valor era entre 1 y 2 che.");
                    }

                    break;

                default:
                    System.out.println("Se ingreso una opcion no contemplada!!");

                    break;

            }
            System.out.println("--------------------------------------------------------");
            partida.verCartas();
            System.out.println("--------------------------------------------------------");
            sc.nextLine();
        }

        sc.close();

    }

    public static void menuOpciones() {
        System.out.println("Menu de opciones");
        System.out.println("Opcion 1) Reiniciar partida.");
        System.out.println("Opcion 2) Robar una carta del mazo.");
        System.out.println("Opcion 3) Agregar una carta en una columna.");
        System.out.println("Opcion 4) Agregar una carta a la solucion.");
        System.out.println("Opcion 8) Salir.");
    }
}
/*
 * tenes una mazo de cartas desde el as,2,3,4,5,6,7,8,9,j,q,k cada carta ademas
 * de su valor tiene su color/palo
 * una de 52 objetos cartas puede ser, el repositorio llamado mazo y una clase
 * llamada carta
 * un servicio que sea el que hace las jugadas y uno que guarde todos los datos
 * de la partida actual, otro repo para la partida?
 * los repos deberian tener un boton para reiniciar si el jugador quiere jugar
 * otra partida
 * es decir el boton ejecuta una funcion resetear()
 */