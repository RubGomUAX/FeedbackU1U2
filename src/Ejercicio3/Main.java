package Ejercicio3;

public class Main {
    public static void main(String[] args) {
        PartidaAjedrez partidaAjedrez = new PartidaAjedrez();
        partidaAjedrez.inicioPartida("Rubén", "Heleia");

        partidaAjedrez.realizarMovimiento("e2-e4");
        partidaAjedrez.realizarMovimiento("e7-e5");

        partidaAjedrez.mostrarTablero();
        System.out.println("Los movimientos realizados son: ");
        for (String mov : partidaAjedrez.obtenerMovimientos()) {
            System.out.println(mov);
        }

        System.out.println("¿Se ha producido jaquemate? " + partidaAjedrez.esJaqueMate());
        System.out.println("¿Se ha producido empate? " + partidaAjedrez.esEmpate());
    }
}
