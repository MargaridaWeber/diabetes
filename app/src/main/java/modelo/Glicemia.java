package modelo;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mónica Francisco on 14/12/2015.
 */
public class Glicemia {
    private Date data;
    private String hora;
    private int valor;
    private String momento;
    private String refeicao;
    private String notas;

    public Glicemia(Date data, String hora, int valor,  String momento, String refeicao, String notas) {
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.momento = momento;
        this.refeicao = refeicao;
        this.notas = notas;
    }

    public Date getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public String getNotas() {
        return notas;
    }

    public String getMomento() {
        return momento;
    }

    public String getRefeicao() {
        return refeicao;
    }

    public int getValor() {
        return valor;
    }

    public String getDataString(){
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(data);
    }

}
