package com.example.pc.diabetesfriend;

/**
 * Created by Mónica Francisco on 30/11/2015.
 */
public class Item {

    private String horas;
    private String dias;
    private String tipo;

    public Item(String horas, String dias, String tipo) {
        super();
        this.horas = horas;
        this.dias = dias;
        this.tipo = tipo;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
