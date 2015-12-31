package modelo;

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

    /*public Utilizador pesquisarUtilizador(){
        for(Utilizador u : listaUtilizadores){
            if()
        }
    }*/
}
