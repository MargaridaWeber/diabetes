package modelo;

import android.provider.Settings;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import java.io.Console;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import alarmes.Alarme;

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
    private int hiperglicemia;
    private int hipoglicemia;
    String[] glicemiaDesejada;
    ArrayList<Glicemia> listaGlicemias;
    ArrayList<Alarme> listaAlarmes;
    ArrayList<Peso> listaPesos;
    ArrayList<PressaoArterial> listaPressoesArteriais;
    Plano plano;


    public Utilizador(String nome, Date dataNasc, char genero, char antedecentes, float peso, int altura, String email, String password) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.genero = genero;
        this.antedecentes = antedecentes;
        this.peso = peso;
        this.altura = altura;
        this.email = email;
        this.password = password;
        listaGlicemias = new ArrayList<Glicemia>();
        listaPesos = new ArrayList<Peso>();
        listaPressoesArteriais = new ArrayList<PressaoArterial>();
        listaAlarmes = new ArrayList<Alarme>();

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

    public String[] getGlicemiaDesejada() {
        return glicemiaDesejada;
    }

    public void setGlicemiaDesejada(String[] glicemiaDesejada) {
        this.glicemiaDesejada = glicemiaDesejada;
    }

    public int getHiperglicemia() {
        return hiperglicemia;
    }

    public void setHiperglicemia(int hiperglicemia) {
        this.hiperglicemia = hiperglicemia;
    }

    public int getHipoglicemia() {
        return hipoglicemia;
    }

    public void setHipoglicemia(int hipoglicemia) {
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


    public ArrayList<Glicemia> getGlicemias() {
        return listaGlicemias;
    }

    public void setGlicemias(ArrayList<Glicemia> glicemias) {
        this.listaGlicemias = glicemias;
    }

    public ArrayList<Alarme> getAlarmes() {
        return listaAlarmes;
    }

    public void setAlarmes(ArrayList<Alarme> alarmes) {
        this.listaAlarmes = alarmes;
    }

    public void adicionarAlarme(Alarme alarme){
        listaAlarmes.add(alarme);
    }

    public void removerAlarme(Alarme alarme){
        listaAlarmes.remove(alarme);
    }

    public void adicionarGlicemia(Glicemia glicemia){
        listaGlicemias.add(glicemia);
    }

    public ArrayList<Glicemia> getListaGlicemias() {
        return listaGlicemias;
    }

    public void setListaGlicemias(ArrayList<Glicemia> listaGlicemias) {
        this.listaGlicemias = listaGlicemias;
    }

    public void adicionarPeso(Peso peso){
        listaPesos.add(peso);
    }

    public ArrayList<Peso> getPesos() {
        return listaPesos;
    }

    public void adicionarPressaoArterial(PressaoArterial pressao){
        listaPressoesArteriais.add(pressao);
    }

    public ArrayList<PressaoArterial> getPressoesArteriais() {
        return listaPressoesArteriais;
    }



    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public float calcularIMC(){
        float IMC = peso/(altura*altura);
        return IMC;
    }

    public int getIdade(){
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);


        calendar.setTime(dataNasc);
        int anoNascim = calendar.get(Calendar.YEAR);
        int idade = (ano -anoNascim);
        System.out.println("ano" + ano + "anonascim"+anoNascim+"idade"+idade );
        return idade;
    }

    public int getMediaGlicemia(){
        int soma=0;
        int media=0;
        for (Glicemia gli: listaGlicemias) {
            soma = soma + gli.getValor();
        }
        if(listaGlicemias.size()!=0)
            media=soma/listaGlicemias.size();
        return media;
    }

    public int getHipos(){
        int nrHipo = 0;
        for (Glicemia gli : listaGlicemias){
            if(gli.getValor()<70)
                nrHipo++;
        }
        return nrHipo;
    }

    public int getHiper(){
        int nrHiper = 0;
        for (Glicemia gli : listaGlicemias){
            if(gli.getValor()>180)
                nrHiper++;
        }
        return nrHiper;
    }


    public Alarme getAlarme(){

        Calendar c = Calendar .getInstance();
        String[] dataHora = c.getTime().toString().split(" ");
        String[] hora =  dataHora[3].toString().split(":");
        String h =  hora[0] + ":"+ hora[1];

        for (Alarme alarme : listaAlarmes){

             if(alarme.getHora().equals(h))
                return alarme;
            System.out.println("nossa hora"+alarme.getHora()+"hora de agora"+h);

        }
        return null;
    }

    public List<Glicemia> getGlicemias7dias(){
        List<Glicemia> listaSeteDias = new ArrayList<Glicemia>();

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -8);
        Date dia = cal.getTime();

        for(Glicemia gli: listaGlicemias)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataGlicemia = dateFormat.parse(gli.getData());
                if(dataGlicemia.after(dia) && dataGlicemia.before(new Date()))
                    listaSeteDias.add(gli);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listaSeteDias;
    }

    public List<Glicemia> getGlicemias14dias(){
        List<Glicemia> listaCatorzeDias = new ArrayList<Glicemia>();

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -15);
        Date dia = cal.getTime();

        for(Glicemia gli: listaGlicemias)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataGlicemia = dateFormat.parse(gli.getData());
                if(dataGlicemia.after(dia) && dataGlicemia.before(new Date()))
                    listaCatorzeDias.add(gli);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listaCatorzeDias;
    }

    public List<Glicemia> getGlicemias30dias(){
        List<Glicemia> listaTrintaDias = new ArrayList<Glicemia>();

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -31);
        Date dia = cal.getTime();

        for(Glicemia gli: listaGlicemias)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataGlicemia = dateFormat.parse(gli.getData());
                if(dataGlicemia.after(dia) && dataGlicemia.before(new Date()))
                    listaTrintaDias.add(gli);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listaTrintaDias;
    }

    public List<Peso> getPesos7dias(){
        List<Peso> listaSeteDias = new ArrayList<Peso>();

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -8);
        Date dia = cal.getTime();

        for(Peso p: listaPesos)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataPeso = dateFormat.parse(p.getData());
                if(dataPeso.after(dia) && dataPeso.before(new Date()))
                    listaSeteDias.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listaSeteDias;
    }

    public List<Peso> getPesos14dias(){
        List<Peso> listaCatorzeDias = new ArrayList<Peso>();

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -15);
        Date dia = cal.getTime();

        for(Peso p: listaPesos)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataPesos = dateFormat.parse(p.getData());
                if(dataPesos.after(dia) && dataPesos.before(new Date()))
                    listaCatorzeDias.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listaCatorzeDias;
    }

    public List<Peso> getPesos30dias(){
        List<Peso> listaTrintaDias = new ArrayList<Peso>();

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -31);
        Date dia = cal.getTime();

        for(Peso p: listaPesos)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataPesos = dateFormat.parse(p.getData());
                if(dataPesos.after(dia) && dataPesos.before(new Date()))
                    listaTrintaDias.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listaTrintaDias;
    }

    public List<PressaoArterial> getPressoes7dias(){
        List<PressaoArterial> listaSeteDias = new ArrayList<PressaoArterial>();

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -8);
        Date dia = cal.getTime();

        for(PressaoArterial p: listaPressoesArteriais)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataPressao = dateFormat.parse(p.getData());
                if(dataPressao.after(dia) && dataPressao.before(new Date()))
                    listaSeteDias.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listaSeteDias;
    }

    public List<PressaoArterial> getPressoes14dias(){
        List<PressaoArterial> listaCatorzeDias = new ArrayList<PressaoArterial>();

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -15);
        Date dia = cal.getTime();

        for(PressaoArterial p: listaPressoesArteriais)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataPressao = dateFormat.parse(p.getData());
                if(dataPressao.after(dia) && dataPressao.before(new Date()))
                    listaCatorzeDias.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listaCatorzeDias;
    }

    public List<PressaoArterial> getPressoes30dias(){
        List<PressaoArterial> listaTrintaDias = new ArrayList<PressaoArterial>();

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -31);
        Date dia = cal.getTime();

        for(PressaoArterial p: listaPressoesArteriais)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataPressao = dateFormat.parse(p.getData());
                if(dataPressao.after(dia) && dataPressao.before(new Date()))
                    listaTrintaDias.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listaTrintaDias;
    }

}
