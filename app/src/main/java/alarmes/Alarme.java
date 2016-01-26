package alarmes;

/**
 * Created by Mónica Francisco on 30/11/2015.
 */
public class Alarme {

    private String hora;
    private String dias;
    private String tipo;
    private Boolean modo;

    public Alarme(String hora, String dias, String tipo) {
        super();
        this.hora = hora;
        this.dias = dias;
        this.tipo = tipo;
        this.modo = true;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora
    ) {
        this.hora = hora;
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

    public Boolean getModo() {
        return modo;
    }

    public void setModo(Boolean modo) {
        this.modo = modo;
    }
}
