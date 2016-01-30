package modelo;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Mónica Francisco on 14/12/2015.
 */
public class Glicemia {
    private String data;
    private String hora;
    private String valor;
    private String refeicao;
    private String notas;
    private float peso;
    private float pressaoArterial;

    public Glicemia(String data, String hora, String valor,  String refeicao, String notas) {
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.refeicao = refeicao;
        this.notas = notas;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
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
