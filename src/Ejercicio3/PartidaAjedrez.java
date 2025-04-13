package Ejercicio3;

import java.util.*;

public class PartidaAjedrez {
    private String jugadorBlancas;
    private String jugadorNegras;
    private LinkedList<String> movimientos;
    private Map<String, Piezas> tablero;
    public boolean turnoBlancas;
    private Map<String, Integer> historialEstados;
    private int contador;



    public PartidaAjedrez() {
        movimientos = new LinkedList<>();
        tablero = new HashMap<>();
        historialEstados = new HashMap<>();
        turnoBlancas = true; // Blancas comienzan
        contador = 0;
    }

    public void iniciarPartida(String jugadorBlancas, String jugadorNegras) {
        this.jugadorBlancas = jugadorBlancas;
        this.jugadorNegras = jugadorNegras;
        movimientos.clear();
        tablero.clear();
        historialEstados.clear();
        turnoBlancas = true; // Reinicia el turno
        contador = 0;
        inicializarTablero();
    }

    private void inicializarTablero() {
        tablero.put("a1", new Piezas("Torre", "Blanco"));
        tablero.put("b1", new Piezas("Caballo", "Blanco"));
        tablero.put("c1", new Piezas("Alfil", "Blanco"));
        tablero.put("d1", new Piezas("Reina", "Blanco"));
        tablero.put("e1", new Piezas("Rey", "Blanco"));
        tablero.put("f1", new Piezas("Alfil", "Blanco"));
        tablero.put("g1", new Piezas("Caballo", "Blanco"));
        tablero.put("h1", new Piezas("Torre", "Blanco"));

        for (char c = 'a'; c <= 'h'; c++) {
            tablero.put(c + "2", new Piezas("Peón", "Blanco"));
            tablero.put(c + "7", new Piezas("Peón", "Negro"));
        }

        tablero.put("a8", new Piezas("Torre", "Negro"));
        tablero.put("b8", new Piezas("Caballo", "Negro"));
        tablero.put("c8", new Piezas("Alfil", "Negro"));
        tablero.put("d8", new Piezas("Reina", "Negro"));
        tablero.put("e8", new Piezas("Rey", "Negro"));
        tablero.put("f8", new Piezas("Alfil", "Negro"));
        tablero.put("g8", new Piezas("Caballo", "Negro"));
        tablero.put("h8", new Piezas("Torre", "Negro"));
    }

    // Método para obtener la pieza en una posición específica
    public Piezas obtenerPiezaEnPosicion(String posicion) {
        if (!tablero.containsKey(posicion)) {
            System.out.println("No hay ninguna pieza en la posición: " + posicion);
            return null;
        }
        return tablero.get(posicion); // Devuelve la pieza en la posición especificada
    }

    // Método para obtener todos los movimientos realizados durante la partida
    public LinkedList<String> obtenerMovimientos() {
        return new LinkedList<>(movimientos); // Devuelve una copia de la lista de movimientos
    }

    public void realizarMovimiento(String movimiento) {
        try{
            String[] partes = movimiento.split("-");
            String origen = partes[0];
            String destino = partes[1];

            if (!tablero.containsKey(origen)) {
                System.out.println("Movimiento inválido: no hay Piezas en la posición origen.");
                return;
            }

            Piezas Piezas = tablero.get(origen);

            // Verificar turno
            if ((turnoBlancas && !Piezas.getColor().equals("Blanco")) ||
                    (!turnoBlancas && !Piezas.getColor().equals("Negro"))) {
                System.out.println("Movimiento inválido: no es el turno de " + Piezas.getColor());
                return;
            }

            // Validar movimiento según el tipo de Piezas
            LinkedList<String> movimientosPosibles = obtenerMovimientosPosibles(origen);
            if (!movimientosPosibles.contains(destino)) {
                System.out.println("Movimiento inválido para " + Piezas.getTipo());
                return;
            }

            // Verificar si se hizo una captura o se movió un peón
            boolean fueCaptura = tablero.containsKey(destino);
            boolean fueMovimientoPeon = Piezas.getTipo().equals("Peón");

            tablero.remove(origen);
            tablero.put(destino, Piezas);

            // Actualizar contador de 50 movimientos
            if (fueCaptura || fueMovimientoPeon) {
                contador = 0;
            } else {
                contador++;
            }

            movimientos.add(movimiento);

            // Guardar el estado del tablero actual en el historial
            String estado = obtenerEstadoTablero();
            historialEstados.put(estado, historialEstados.getOrDefault(estado, 0) + 1);

            turnoBlancas = !turnoBlancas; // Cambiar turno

        } catch (Exception e) {
            System.out.println("Comando introducido inválido: Por favor, verifica el formato del movimiento e inténtalo de nuevo.");
        }
    }

    public LinkedList<String> obtenerMovimientosPosibles(String posicion) {
        LinkedList<String> posibles = new LinkedList<>();

        if (!tablero.containsKey(posicion)) {
            return posibles; // No hay ninguna Piezas en la posición
        }

        Piezas Piezas = tablero.get(posicion);
        String tipo = Piezas.getTipo();
        String color = Piezas.getColor();

        int filaOrigen = Integer.parseInt(posicion.substring(1));
        char columnaOrigen = posicion.charAt(0);

        switch (tipo) {
            case "Peón":
                posibles.addAll(obtenerMovimientosPeon(filaOrigen, columnaOrigen, color));
                break;

            case "Torre":
                posibles.addAll(obtenerMovimientosLinea(filaOrigen, columnaOrigen, color, true, false));
                break;

            case "Caballo":
                posibles.addAll(obtenerMovimientosCaballo(filaOrigen, columnaOrigen, color));
                break;

            case "Alfil":
                posibles.addAll(obtenerMovimientosLinea(filaOrigen, columnaOrigen, color, false, true));
                break;

            case "Reina":
                posibles.addAll(obtenerMovimientosLinea(filaOrigen, columnaOrigen, color, true, true));
                break;

            case "Rey":
                posibles.addAll(obtenerMovimientosRey(filaOrigen, columnaOrigen, color));
                break;

            default:
                break;
        }

        return posibles;
    }

    private LinkedList<String> obtenerMovimientosPeon(int filaOrigen, char columnaOrigen, String color) {
        LinkedList<String> posibles = new LinkedList<>();
        int direccion = color.equals("Blanco") ? 1 : -1;

        String adelante = columnaOrigen + "" + (filaOrigen + direccion);
        if (!tablero.containsKey(adelante)) {
            posibles.add(adelante);
        }

        if ((color.equals("Blanco") && filaOrigen == 2) || (color.equals("Negro") && filaOrigen == 7)) {
            String dobleAdelante = columnaOrigen + "" + (filaOrigen + 2 * direccion);
            if (!tablero.containsKey(dobleAdelante) && !tablero.containsKey(adelante)) {
                posibles.add(dobleAdelante);
            }
        }

        char[] diagonales = {(char) (columnaOrigen - 1), (char) (columnaOrigen + 1)};
        for (char diagonal : diagonales) {
            String diagonalDestino = diagonal + "" + (filaOrigen + direccion);
            if (tablero.containsKey(diagonalDestino) && !tablero.get(diagonalDestino).getColor().equals(color)) {
                posibles.add(diagonalDestino);
            }
        }

        return posibles;
    }

    private LinkedList<String> obtenerMovimientosCaballo(int filaOrigen, char columnaOrigen, String color) {
        LinkedList<String> posibles = new LinkedList<>();
        int[] movimientosFilas = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] movimientosColumnas = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < movimientosFilas.length; i++) {
            int filaDestino = filaOrigen + movimientosFilas[i];
            char columnaDestino = (char) (columnaOrigen + movimientosColumnas[i]);
            String destino = columnaDestino + "" + filaDestino;

            if (filaDestino >= 1 && filaDestino <= 8 && columnaDestino >= 'a' && columnaDestino <= 'h') {
                if (!tablero.containsKey(destino) || !tablero.get(destino).getColor().equals(color)) {
                    posibles.add(destino);
                }
            }
        }

        return posibles;
    }

    private LinkedList<String> obtenerMovimientosLinea(int filaOrigen, char columnaOrigen, String color, boolean horizontalVertical, boolean diagonal) {
        LinkedList<String> posibles = new LinkedList<>();
        int[] direccionesFilas = {1, -1, 0, 0, 1, -1, 1, -1};   // Direcciones fila: arriba, abajo, derecha, izquierda, diagonales.
        int[] direccionesColumnas = {0, 0, 1, -1, 1, -1, -1, 1}; // Direcciones columna correspondientes.

        for (int i = 0; i < 8; i++) {
            if ((i < 4 && horizontalVertical) || (i >= 4 && diagonal)) {
                int fila = filaOrigen;
                char columna = columnaOrigen;

                while (true) {
                    fila += direccionesFilas[i];
                    columna += direccionesColumnas[i];
                    String destino = columna + "" + fila;

                    // Verifica que el destino esté dentro del tablero.
                    if (fila < 1 || fila > 8 || columna < 'a' || columna > 'h') {
                        break; // Salimos del tablero.
                    }

                    // Si no hay una pieza en la casilla, el movimiento es válido.
                    if (!tablero.containsKey(destino)) {
                        posibles.add(destino);
                    } else {
                        // Si hay una pieza enemiga, el movimiento para capturar es válido.
                        if (!tablero.get(destino).getColor().equals(color)) {
                            posibles.add(destino);
                        }
                        break; // Detenemos el recorrido porque hay una pieza (propia o enemiga).
                    }
                }
            }
        }
        return posibles;
    }

    private LinkedList<String> obtenerMovimientosRey(int filaOrigen, char columnaOrigen, String color) {
        LinkedList<String> posibles = new LinkedList<>();
        int[] movimientosFilas = {1, 1, 1, 0, -1, -1, -1, 0};
        int[] movimientosColumnas = {0, 1, -1, -1, 0, 1, -1, 1};

        for (int i = 0; i < movimientosFilas.length; i++) {
            int filaDestino = filaOrigen + movimientosFilas[i];
            char columnaDestino = (char) (columnaOrigen + movimientosColumnas[i]);
            String destino = columnaDestino + "" + filaDestino;

            if (filaDestino >= 1 && filaDestino <= 8 && columnaDestino >= 'a' && columnaDestino <= 'h') {
                if (!tablero.containsKey(destino) || !tablero.get(destino).getColor().equals(color)) {
                    posibles.add(destino);
                }
            }
        }

        return posibles;
    }

    public boolean esJaqueMate() {
        String colorDelRey = turnoBlancas ? "Blanco" : "Negro";
        String posicionRey = null;

        // Buscar la posición del rey
        for (Map.Entry<String, Piezas> entry : tablero.entrySet()) {
            Piezas pieza = entry.getValue();
            if (pieza.getTipo().equals("Rey") && pieza.getColor().equals(colorDelRey)) {
                posicionRey = entry.getKey();
                break;
            }
        }

        if (posicionRey == null) {
            System.out.println("No se encontró al rey en el tablero.");
            return false;
        }

        // Verificar si el rey está en jaque
        if (!estaEnJaque(posicionRey, colorDelRey)) {
            return false;
        }

        // Comprobar si el rey puede moverse a una casilla segura
        LinkedList<String> movimientosRey = obtenerMovimientosPosibles(posicionRey);
        for (String destino : movimientosRey) {
            if (!estaEnJaque(destino, colorDelRey)) {
                return false; // El rey puede escapar del jaque
            }
        }

        // Simular movimientos de otras piezas para bloquear el jaque
        for (Map.Entry<String, Piezas> entry : tablero.entrySet()) {
            String posicion = entry.getKey();
            Piezas pieza = entry.getValue();

            if (pieza.getColor().equals(colorDelRey)) {
                LinkedList<String> movimientosPosibles = obtenerMovimientosPosibles(posicion);
                for (String destino : movimientosPosibles) {
                    // Simular movimiento
                    Piezas piezaCapturada = tablero.get(destino);
                    tablero.put(destino, pieza);
                    tablero.remove(posicion);

                    boolean sigueEnJaque = estaEnJaque(posicionRey, colorDelRey);

                    // Restaurar el estado original
                    tablero.put(posicion, pieza);
                    if (piezaCapturada != null) {
                        tablero.put(destino, piezaCapturada);
                    } else {
                        tablero.remove(destino);
                    }

                    if (!sigueEnJaque) {
                        return false; // No es jaque mate
                    }
                }
            }
        }

        return true; // Es jaque mate
    }


    public boolean estaEnJaque(String posicionRey, String colorDelRey) {
        String colorEnemigo = colorDelRey.equals("Blanco") ? "Negro" : "Blanco";

        for (Map.Entry<String, Piezas> entry : tablero.entrySet()) {
            String posicion = entry.getKey();
            Piezas pieza = entry.getValue();

            if (pieza.getColor().equals(colorEnemigo)) {
                LinkedList<String> movimientosEnemigos = obtenerMovimientosPosibles(posicion);

                // Depuración adicional
                System.out.println("Movimientos del enemigo desde " + posicion + ": " + movimientosEnemigos);

                // Verificar amenazas al rey en la posición actual
                if (movimientosEnemigos.contains(posicionRey)) {
                    System.out.println("El rey en " + posicionRey + " está en jaque por la pieza en " + posicion);
                    return true; // Jaque detectado
                }
            }
        }

        System.out.println("El rey en " + posicionRey + " no está en jaque.");
        return false; // No hay jaque
    }

    public boolean esEmpate() {
        if (insuficienciaDeMaterial()) {
            return true;
        }

        if (repeticionDePosiciones()) {
            return true;
        }

        if (reglaDeCincuentaMovimientos()) {
            return true;
        }

        return false; // No es empate
    }

    private boolean insuficienciaDeMaterial() {
        int cantidadBlancas = 0;
        int cantidadNegras = 0;

        for (Piezas Piezas : tablero.values()) {
            if (Piezas.getTipo().equals("Peón") || Piezas.getTipo().equals("Torre") ||
                    Piezas.getTipo().equals("Reina")) {
                return false; // Hay suficiente material para ganar
            }
            if (Piezas.getColor().equals("Blanco")) {
                cantidadBlancas++;
            } else {
                cantidadNegras++;
            }
        }

        return (cantidadBlancas <= 1 && cantidadNegras <= 1); // Empate por insuficiencia de material
    }

    private boolean repeticionDePosiciones() {
        for (int repeticiones : historialEstados.values()) {
            if (repeticiones >= 3) {
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para obtener el estado actual del tablero como una cadena
    private String obtenerEstadoTablero() {
        StringBuilder estado = new StringBuilder();
        for (Map.Entry<String, Piezas> entry : tablero.entrySet()) {
            estado.append(entry.getKey()).append(":").append(entry.getValue().toString()).append(";");
        }
        return estado.toString();
    }

    private boolean reglaDeCincuentaMovimientos() {
        if (movimientos.size() < 50) {
            return false;
        }

        for (int i = movimientos.size() - 50; i < movimientos.size(); i++) {
            String movimiento = movimientos.get(i);
            if (movimiento.contains("x") || movimiento.contains("Peón")) {
                return false; // Se realizó una captura o un movimiento de peón
            }
        }

        return true; // Cumple la regla de los 50 movimientos
    }

    public void mostrarTablero() {
        System.out.println("Estado actual del tablero:");
        for (Map.Entry<String, Piezas> entry : tablero.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}