

// ###########################################################
// IMPLEMENTACIÓN DEL TAD - PILA
// ###########################################################
public class Pila {
    //********************************************************
    // INTERNO
    //********************************************************
    private int[] elementos;
    private int tope;
    private static final int TAM_DEF = 10;

    //********************************************************
    // CONSTRUCTORES
    //********************************************************

    //Construir una pila vacía con un tamaño por defecto
    public Pila() {
        this.elementos = new int[TAM_DEF];
        this.tope = -1;
    }

    //Construir una pila vacía con tamaño predeterminado
    public Pila(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Capacidad inválida");
        }
        this.elementos = new int[capacidad];
        this.tope = -1;
    }

    //********************************************************
    // MÉTODOS ACCESO
    //********************************************************

    //Devolver la capacidad de la pila
    public int capacidad() {
        return elementos.length;
    }

    //Conocer si la pila está vacía
    public boolean estaVacia() {
        return tope == -1;
    }

    //Obtener elementos de la cima
    public int cima(){
        if (estaVacia()) {
            throw new IllegalArgumentException("Pila vacía");
        }
        return elementos[tope];
    }

    //********************************************************
    // MÉTODOS MODIFICACIÓN
    //********************************************************

    //Insertar un elemento en la cima de la pila
    public void apilar(int elemento) {
        if (tope == elementos.length -1) {
            throw new IllegalStateException("Desborde de pila");
        }
        tope++;
        elementos[tope] = elemento;
    }

    //Eliminar el elemento de la cima de la pila
    public void desapilar() {
        if (estaVacia()) {
            throw new IllegalStateException("Pila vacía");
        }
        tope--;
    }

    //********************************************************
    // MÉTODOS ENTRADA/SALIDA
    //********************************************************

    //Mostrar los elementos de la pila desde la cima al fondo.
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La pila está vacía");
        } else {
            System.out.println("Contenido de la pila");
            for (int i = tope; i >= 0; i--) {
                System.out.println(elementos[i]);
            }
        }
    }
}