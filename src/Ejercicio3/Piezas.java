package Ejercicio3;

public class Piezas {
    private String tipoPieza;
    private String color;

    public Piezas(String tipoPieza, String color) {
        this.tipoPieza = tipoPieza;
        this.color = color;
    }

    public String getTipoPieza() {
        return tipoPieza;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return tipoPieza + " (" + color + ")";
    }
}
