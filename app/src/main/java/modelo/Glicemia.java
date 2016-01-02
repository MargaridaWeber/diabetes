package modelo;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Mónica Francisco on 14/12/2015.
 */
public class Glicemia {
    private Date data;
    private Time hora;
    private String valor;
    private String refeicao;
    private String notas;
    private float peso;
    private float pressaoArterial;

    public Glicemia(Date data, Time hora, String notas, String refeicao, String valor) {
        this.data = data;
        this.hora = hora;
        this.notas = notas;
        this.refeicao = refeicao;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public Time getHora() {
        return hora;
    }

    public String getNotas() {
        return notas;
    }

    public float getPeso() {
        return peso;
    }

    public float getPressaoArterial() {
        return pressaoArterial;
    }

    public String getRefeicao() {
        return refeicao;
    }

    public String getValor() {
        return valor;
    }
}