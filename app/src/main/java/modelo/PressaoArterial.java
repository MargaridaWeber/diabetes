package modelo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mónica Francisco on 01/02/2016.
 */
public class PressaoArterial {
    private float valor;
    private Date data;
    private String hora;

    public PressaoArterial(Date data, String hora, float valor) {
        this.data = data;
        this.hora = hora;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public float getValor() {
        return valor;
    }

    public String getDataString(){
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(data);
    }
}
