package modelo;

import java.util.ArrayList;
import java.util.Date;
/**
 * Created by Mónica Francisco on 14/12/2015.
 */
public class Utilizador {
    private String nome;
    private Date dataNasc;
    private char genero;
    private char antedecentes;
    private float peso;
    private int altura;
    private String email;
    private String password;
    private String tipoDiabetes;
    private char insulina;
    private char exercicio;
    int[] hiperglicemia;
    int[] glicemiaDesejada;
    int[] hipoglicemia;
    ArrayList<Glicemia> glicemias;


    public Utilizador(String nome, Date dataNasc, char genero, char antedecentes, float peso, int altura, String email, String password) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.genero = genero;
        this.antedecentes = antedecentes;
        this.peso = peso;
        this.altura = altura;
        this.email = email;
        this.password = password;
        glicemias = new ArrayList<Glicemia>();
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public char getAntedecentes() {
        return antedecentes;
    }

    public void setAntedecentes(char antedecentes) {
        this.antedecentes = antedecentes;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getExercicio() {
        return exercicio;
    }

    public void setExercicio(char exercicio) {
        this.exercicio = exercicio;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int[] getGlicemiaDesejada() {
        return glicemiaDesejada;
    }

    public void setGlicemiaDesejada(int[] glicemiaDesejada) {
        this.glicemiaDesejada = glicemiaDesejada;
    }

    public int[] getHiperglicemia() {
        return hiperglicemia;
    }

    public void setHiperglicemia(int[] hiperglicemia) {
        this.hiperglicemia = hiperglicemia;
    }

    public int[] getHipoglicemia() {
        return hipoglicemia;
    }

    public void setHipoglicemia(int[] hipoglicemia) {
        this.hipoglicemia = hipoglicemia;
    }

    public char getInsulina() {
        return insulina;
    }

    public void setInsulina(char insulina) {
        this.insulina = insulina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getTipoDiabetes() {
        return tipoDiabetes;
    }

    public void setTipoDiabetes(String tipoDiabetes) {
        this.tipoDiabetes = tipoDiabetes;
    }

    public float calcularIMC(){
        float IMC = peso/(altura*altura);
        return IMC;
    }
}
