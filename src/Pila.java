public class Pila {
    private int[] elementos;
    private int tope;
    private static final int TAM_POR_DEF = 10;

    public Pila(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0");
        }
        elementos = new int[capacidad];
        tope = -1;
    }
    public Pila() {
        this(TAM_POR_DEF);
    }
}
