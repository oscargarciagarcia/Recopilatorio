package com.example.tartanuk.recopilatoriooscar;

/**
 * Created by tartanuk on 7/1/16.
 */
public class ordenadores {

    private String tipo;
    private double precio;
    private int imagen;

    public ordenadores (String tipo, double precio, int imagen){
        this.tipo = tipo;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getTipo() { return tipo; }
    public double getPrecio() { return precio; }
    public int getImagen() { return imagen; }
}
