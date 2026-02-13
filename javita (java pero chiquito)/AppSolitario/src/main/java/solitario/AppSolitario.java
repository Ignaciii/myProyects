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
                    System.out.print("Sacamos de las columnas 1) | Sacamos de las disponibles 2): ");
                    int eleccion = sc.nextInt();
                    if (eleccion == 1) {
                        int columnaOrigen = cargarColumnaUsuario(sc);
                        Carta carta = partida.sacarDeColumna(columnaOrigen);

                        if (carta != null) {
                            System.out.println("Carta seleccionada " + carta.toString());
                            System.out.println("Bien, ahora a que columna la agregamos");
                            int columnaDestino = cargarColumnaUsuario(sc);

                            Boolean resultado = partida.agregarCartaColumna(carta, columnaDestino);
                            if (!resultado) {
                                rollbackColumna(carta, partida, columnaOrigen);
                            }
                        }

                    } else if (eleccion == 2) {
                        System.out.println("Joya sacamos de las disponibles");
                        int columnaDestino = cargarColumnaUsuario(sc);
                        Carta carta = partida.usarCartaRobada();
                        if (carta != null && columnaDestino != -1) {
                            Boolean resultado = partida.agregarCartaColumna(carta, columnaDestino);
                            if (!resultado) {
                                rollbackRobadas(carta, partida);
                            }
                        } else {
                            System.out.println("Error: no se pudo cargar esa carta alli.");
                        }

                    } else {
                        System.out.println("Che me perdi, esa opcion no la tengo.");
                    }

                    break;
                case 4:
                    System.out.print("Sacamos de una columna 1) | Sacamos de las usables 2): ");
                    int opcionIngresada = sc.nextInt();
                    if (opcionIngresada == 1) {
                        int columnaOrigen = cargarColumnaUsuario(sc);
                        Carta carta = partida.sacarDeColumna(columnaOrigen);
                        if (carta != null && columnaOrigen != -1) {
                            Boolean resultado = partida.agregarCartaSolucion(carta);
                            if (!resultado) {
                                rollbackColumna(carta, partida, columnaOrigen);
                            }
                        }

                        else {
                            System.out.println("Error: no se pudo cargar esa carta alli.");
                        }
                    }

                    else if (opcionIngresada == 2) {
                        Carta carta = partida.usarCartaRobada();
                        if (carta != null) {
                            Boolean resultado = partida.agregarCartaSolucion(carta);
                            if (!resultado) {
                                rollbackRobadas(carta, partida);
                            }
                        }
                    } else {
                        System.out.println("El valor era entre 1 y 2 che.");
                    }

                    break;
                case 5:
                    partida.verSolucion();
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
        System.out.println("Opcion 5) Ver palos armados");
        System.out.println("Opcion 8) Salir.");
    }

    public static int cargarColumnaUsuario(Scanner sc) {
        System.out.print("Ingrese una columna (1-7): ");
        int columna = sc.nextInt();
        if (columna > 0 && columna < 8) {
            return columna;
        } else {
            System.out.println("Upa se cargo un valor de columna invalido");
            return -1;
        }
    }

    public static void rollbackRobadas(Carta carta, Partida partida) {
        partida.devolverCartaRobada(carta);
    }

    public static void rollbackColumna(Carta carta, Partida partida, int columnaOrigen) {
        partida.rollbackColumna(carta, columnaOrigen);
    }

}
