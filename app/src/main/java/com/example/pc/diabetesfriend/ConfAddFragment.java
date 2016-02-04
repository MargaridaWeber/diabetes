package com.example.pc.diabetesfriend;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import modelo.DiabetesFriend;
import modelo.SessionManager;
import modelo.Utilizador;

public class ConfAddFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private List<String[]> listaConf;
    ArrayAdapter<String[]> adaptador;
    DiabetesFriend diabetes;
    SessionManager session;
    Utilizador u;
    String tipoDiabetes = null;
    char tomaInsulina;
    char fazExercicio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.conf_add_fragment, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity().getApplicationContext());

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);

        u = diabetes.pesquisarUtilizador(email);

        listaConf = new LinkedList<String[]>();
        listaConf.add(new String[]{"Tipo de Diabetes", u.getTipoDiabetes()});
        listaConf.add(new String[]{"Toma insulina", u.getGenero() == 'S' ? "Sim" : "Nao"});
        listaConf.add(new String[]{"Faz exercício", u.getGenero() == 'S' ? "Sim" : "Nao"});

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adaptador = new ArrayAdapter<String[]>(getActivity(), android.R.layout.simple_list_item_2,android.R.id.text1, listaConf){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String[]entrey = listaConf.get(position);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(entrey[0]);
                text2.setText(entrey[1]);
                return view;
            }
        };
        setListAdapter(adaptador);
        getListView().setOnItemClickListener(this);
    }

    //Tipos
    AlertDialog tipos;
    private void criarTipos(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog . setTitle(R.string.tipos_titulo);
        dialog.setSingleChoiceItems(R.array.tipo, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                if (selectedPosition == 0) {
                    listaConf.set(0, new String[]{"Tipo de Diabetes", "Tipo 1"});
                    setListAdapter(adaptador);
                    tipoDiabetes = "Tipo 1";
                } else {
                    listaConf.set(0, new String[]{"Tipo de Diabetes", "Tipo 2"});
                    setListAdapter(adaptador);
                    tipoDiabetes = "Tipo 2";
                }
                tipos.dismiss(); //Para sair logo
            }
        });
        tipos = dialog.create();
        tipos.show();
    }

    //Insulina
    AlertDialog insulina;
    private void insulina(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog . setTitle(R.string.insulina_titulo);
        dialog.setSingleChoiceItems(R.array.confirmacao, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                if (selectedPosition == 0) {
                    listaConf.set(1, new String[]{"Toma Insulina", "Sim"});
                    setListAdapter(adaptador);
                    tomaInsulina = 'S';
                } else {
                    listaConf.set(1, new String[]{"Toma Insulina", "Não"});
                    setListAdapter(adaptador);
                    tomaInsulina = 'N';
                }
                insulina.dismiss(); //Para sair logo
            }
        });
        insulina = dialog.create();
        insulina.show();


    }

    AlertDialog exercicio;
    private void exercicio(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog . setTitle ( R . string.exercicio_titulo)
                . setSingleChoiceItems(R.array.confirmacao, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                        if (selectedPosition == 0) {
                            listaConf.set(2, new String[]{"Faz exercício", "Sim"});
                            setListAdapter(adaptador);
                            fazExercicio = 'S';
                        } else {
                            listaConf.set(2, new String[]{"Faz exercício", "Não"});
                            setListAdapter(adaptador);
                            fazExercicio = 'N';
                        }
                        exercicio.dismiss(); //Para sair logo
                    }
                });
        exercicio = dialog.create();
        exercicio.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int itemPosition = position;

        if(itemPosition==0)
            criarTipos();
        if(itemPosition==1)
            insulina();
        if (itemPosition==2)
            exercicio();

    }




}
