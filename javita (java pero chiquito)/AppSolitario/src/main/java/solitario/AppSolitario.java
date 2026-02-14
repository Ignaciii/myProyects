package solitario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppSolitario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        // este creo que puedo ponerlo directamente en el repoPartida
        Mazo mazo = new Mazo();
        PalosArmados palosArmados = new PalosArmados();
        Partida partida = new Partida(mazo, palosArmados);

        partida.llenarColumna();

        String espacio = "-------------------------------------------------------------------------------------------------------";
        System.out.println(espacio);
        menuOpciones();
        System.out.println(espacio);
        System.out.println(partida.verCartas());
        System.out.println(espacio);
        while (opcion != 8) {

            System.out.print("Seleccione una opcion: ");
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
                        System.out.print("Bien, cuantas cartas sacamos de ahi (1 o mas): ");
                        int cantidad = sc.nextInt();

                        int columnaOrigen = cargarColumnaUsuario(sc);
                        if (cantidad == 1) {

                            List<Carta> cartas = partida.sacarDeColumna(columnaOrigen, cantidad);

                            if (cartas != null) {

                                System.out.println("Carta seleccionada [" + cartas.getLast().toString() + "]");
                                System.out.println("Bien, ahora a que columna la agregamos");
                                int columnaDestino = cargarColumnaUsuario(sc);

                                Boolean resultado = partida.agregarCartaColumna(cartas, columnaDestino, cantidad);
                                if (!resultado) {
                                    rollbackColumna(cartas, partida, columnaOrigen);
                                }
                            }
                        } else if (cantidad > 1) {
                            List<Carta> cartas = partida.sacarDeColumna(columnaOrigen, cantidad);
                            if (cartas != null) {
                                String str = "";
                                for (Carta c : cartas) {
                                    str += "[ " + c.toString() + " ]";
                                }
                                System.out.println("Joya las cartas seleccionadas son: " + str);
                                int columnaDestino = cargarColumnaUsuario(sc);

                                Boolean resultado = partida.agregarCartaColumna(cartas, columnaDestino, cantidad);
                                if (!resultado) {
                                    rollbackColumna(cartas, partida, columnaOrigen);
                                }

                            }
                        }

                    } else if (eleccion == 2) {
                        List<Carta> cartas = new ArrayList<>();
                        cartas.add(partida.usarCartaRobada());

                        if (!cartas.isEmpty()) {
                            System.out.println("Joya sacamos de las disponibles");
                            int columnaDestino = cargarColumnaUsuario(sc);

                            if (columnaDestino != -1) {
                                Boolean resultado = partida.agregarCartaColumna(cartas, columnaDestino, 1);
                                if (!resultado) {
                                    rollbackRobadas(cartas.getLast(), partida);
                                }
                            } else {
                                System.out.println("Che, la columna destino tiene que ir entre 1-7");
                            }

                        }

                        else {
                            System.out.println("Upa, no hay cartas robadas che.");
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
                        List<Carta> carta = partida.sacarDeColumna(columnaOrigen, 1);
                        if (carta != null && columnaOrigen != -1) {
                            Boolean resultado = partida.agregarCartaSolucion(carta.getLast());
                            if (!resultado) {
                                rollbackColumna(carta, partida, columnaOrigen);
                            }
                            if (validarVictoria(partida)) {
                                System.out.println("Genio genio genio gol gol gol!!!!");
                                System.out.println("Jugar de nuevo 1) | Salir 8)");
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
                            if (validarVictoria(partida)) {
                                System.out.println("Genio genio genio gol gol gol!!!!");
                                System.out.println("Jugar de nuevo 1) | Salir 8)");
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

            sc.nextLine();
            System.out.println(espacio);
            menuOpciones();
            System.out.println(espacio);
            System.out.println(partida.verCartas());
            System.out.println(espacio);

        }
        sc.close();
    }

    public static void menuOpciones() {
        System.out.println("Menu de opciones");
        System.out.println("Opcion 1) Reiniciar partida.");
        System.out.println("Opcion 2) Robar una carta del mazo.");
        System.out.println("Opcion 3) Agregar una carta o cartas en una columna.");
        System.out.println("Opcion 4) Agregar una carta a la solucion.");
        System.out.println("Opcion 5) Ver palos armados.");
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

    public static void rollbackColumna(List<Carta> cartas, Partida partida, int columnaOrigen) {
        partida.rollbackColumna(cartas, columnaOrigen);
    }

    public static Boolean validarVictoria(Partida partida) {
        return partida.validarVictoria();
    }

}
