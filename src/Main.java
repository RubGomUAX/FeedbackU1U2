public class Main {
    public static void main(String[] args) {
        Pila pila = new Pila(5);

        pila.apilar(30);
        pila.apilar(50);

        pila.mostrar();

        //pila.desapilar();
        System.out.println("Después de desapilar");
        pila.mostrar();
    }
}