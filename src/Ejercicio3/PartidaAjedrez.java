package Ejercicio3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PartidaAjedrez {
    private String jugadorBlancas;
    private String jugadorNegras;
    private LinkedList<String> movimientos;
    private Map<String, Piezas> tablero;

    public PartidaAjedrez() {
        movimientos = new LinkedList<>();
        tablero = new HashMap<>();
    }

    public void inicioPartida(String jugadorBlancas, String jugadorNegras) {
        this.jugadorBlancas = jugadorBlancas;
        this.jugadorNegras = jugadorNegras;
        movimientos.clear();
        tablero.clear();
        System.out.println("Se inicia partida entre " + jugadorBlancas + " (Blancas), " + jugadorNegras + " (Negras)");
    }

    public void realizarMovimiento(String movimiento) {
        movimientos.add(movimiento);
        System.out.println("Movimiento realizado: " + movimiento);
    }

    public Piezas obtenerPiezaEnPosiscion(String posicion) {
        return tablero.get(posicion);
    }

    public boolean esJaqueMate() {
        return false;
    }

    public boolean esEmpate() {
        return false;
    }

    public LinkedList<String> obtenerMovimientosPosibles(String posicion) {
        return new LinkedList<>();
    }

    public LinkedList<String> obtenerMovimientos() {
        return movimientos;
    }

    public void mostrarTablero() {
        System.out.println("El tablero es:");
        for (String posicion : tablero.keySet()) {
            System.out.println(posicion + ": " + tablero.get(posicion));
        }
    }
}
