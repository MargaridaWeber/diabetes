package modelo;

/**
 * Created by Mónica Francisco on 01/02/2016.
 */
public class PressaoArterial {
    private float valor;
    private String data;
    private String hora;

    public PressaoArterial(String data, String hora, float valor) {
        this.data = data;
        this.hora = hora;
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public float getValor() {
        return valor;
    }
}
