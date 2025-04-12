package Ejercicio1_2;

public class Main {
    public static void main(String[] args) {
        Pila pila = new Pila(5); // Capacidad dada (por defecto o predeterminada si se quita elemento)

/*        while (!pila.estaVacia()) { //Descomentar para vaciar la pila y mostrar errores controlados
            pila.desapilar();
        }    */

        System.out.println("Capacidad de la Ejercicio1_2.Pila: " + pila.capacidad()); // Muestra la capacidad de la pila
                                                                         // Si se incluye más elementos de la capacidad
                                                                         // de la pila, dará error de desborde.
        pila.apilar(30); // Introduce elemento en la cima de la pila
        pila.apilar(50); // Introduce elemento en la cima de la pila
        pila.apilar(25); // Introduce elemento en la cima de la pila
        pila.mostrar(); //Muestra el contenido de la pila después de introducir 3 elementos

        System.out.println("Elemento de la cima: " + pila.cima()); // Muestra elemento de la cima de la pila

        pila.desapilar(); // Elimina el elemento de la cima de la pila

        System.out.println("Después de desapilar: "); // Contenido de la pila después de quitar elemento de la cima
                pila.mostrar();

    }
}