package Ejercicio3;

public class Piezas {
    private String tipo;
    private String color;

    public Piezas(String tipo, String color) {
        this.tipo = tipo;
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color + " " + tipo;
    }
}
