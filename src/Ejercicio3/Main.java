package Ejercicio3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PartidaAjedrez partida = new PartidaAjedrez();

        // Solicitar nombres de los jugadores
        System.out.print("Introduce el nombre del jugador de las piezas Blancas: ");
        String jugadorBlancas = scanner.nextLine();
        System.out.print("Introduce el nombre del jugador de las piezas Negras: ");
        String jugadorNegras = scanner.nextLine();

        partida.iniciarPartida(jugadorBlancas, jugadorNegras);

        // Ciclo de juego
        while (true) {
            partida.mostrarTablero();
            String jugadorActual = partida.turnoBlancas ? jugadorBlancas : jugadorNegras;
            System.out.print(jugadorActual + ", introduce tu movimiento de pieza indicando Inicio - Destino (Ej. e2-e4): ");
            String movimiento = scanner.nextLine();

            // Verificar si el jugador quiere salir
            if (movimiento.equalsIgnoreCase("salir")) {
                System.out.println("Partida terminada.");
                break;
            }

            partida.realizarMovimiento(movimiento);

            if (partida.esJaqueMate()) {
                System.out.println("¡Jaque mate! " + jugadorActual + " gana la partida.");
                break;
            } else if (partida.esEmpate()) {
                System.out.println("La partida ha terminado en empate.");
                break;
            }
        }

        scanner.close();
    }
}