package modelo;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Mónica Francisco on 14/12/2015.
 */
public class DiabetesFriend {
    private static DiabetesFriend diabetes;
    ArrayList<Utilizador> listaUtilizadores;

    public static DiabetesFriend getInstance() {
        if (diabetes == null) {
            diabetes = new DiabetesFriend();
        }
        return diabetes;
    }

    public DiabetesFriend(){
        listaUtilizadores = new ArrayList<>();
    }

    public void adicionarUtilizador(Utilizador u){
        listaUtilizadores.add(u);
    }

    public boolean verificarUtilizadorExiste(String email){
        for(Utilizador u : listaUtilizadores){
            if(u.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public boolean verificarLogin(String email, String password){
        for(Utilizador u : listaUtilizadores){
            if(u.getEmail().equals(email) && u.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public Utilizador pesquisarUtilizador(String email){
        for(Utilizador u : listaUtilizadores){
            if(email.equals(u.getEmail()))
                return u;
        }
        return null;
    }

}
