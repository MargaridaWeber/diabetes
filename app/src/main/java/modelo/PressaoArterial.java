package modelo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mónica Francisco on 01/02/2016.
 */
public class PressaoArterial {
    private int sistolica;
    private int diastolica;
    private Date data;
    private String hora;

    public PressaoArterial(Date data, String hora, int sistolica, int diastolica) {
        this.data = data;
        this.hora = hora;
        this.sistolica = sistolica;
        this.diastolica = diastolica;
    }

    public Date getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public int getDiastolica() {
        return diastolica;
    }

    public int getSistolica() {
        return sistolica;
    }

    public String getDataString(){
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(data);
    }
}
