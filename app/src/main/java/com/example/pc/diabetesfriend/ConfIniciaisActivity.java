package com.example.pc.diabetesfriend;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;



public class ConfIniciaisActivity extends ListActivity {
    private List<String[]> listaConf;
    ArrayAdapter<String[]> adaptador;
    ListView listView ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_iniciais);

        listaConf = new LinkedList<String[]>();

        listaConf.add(new String[]{"Tipo de Diabetes", "Tipo 1"});
        listaConf.add(new String[]{"Toma Insulina", "Sim"});
        listaConf.add(new String[]{"Faz exercício", "Sim"});
        listaConf.add(new String[]{"Limites Glicemia", "" });

        adaptador = new ArrayAdapter<String[]>(this, android.R.layout.simple_list_item_2,android.R.id.text1, listaConf){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String[]entrey =listaConf.get(position);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(entrey[0]);
                text2.setText(entrey[1]);
                return view;
            }

        };
        setListAdapter(adaptador);


        Button btnseguinte = (Button) findViewById(R.id.btnSeguinte);
        btnseguinte.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent principal = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(principal);
            }
        });


    }


    //Quando selecionamos no item
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Object o = listaConf.get(position).toString();

        int itemPosition = position;

        if(itemPosition==0)
            criarTipos();
        else if(itemPosition==1)
            insulina();
        else if(itemPosition==2)
            exercicio();
        else if(itemPosition==3)
            openDialog();

    }


    //Tipos
    AlertDialog tipos;
    private void criarTipos(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog . setTitle(R.string.tipos_titulo);
                dialog.setSingleChoiceItems(R.array.tipo, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();

                        if(selectedPosition==0){
                            listaConf.set(0, new String[]{"Tipo de Diabetes", "Tipo 1"});
                            setListAdapter(adaptador);
                        }
                        else{
                            listaConf.set(0, new String[]{"Tipo de Diabetes", "Tipo 2"});
                            setListAdapter(adaptador);
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog . setTitle ( R . string.insulina_titulo);
               dialog.setSingleChoiceItems(R.array.confirmacao, -1, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();

                       if(selectedPosition==0){
                           listaConf.set(1, new String[]{"Toma Insulina", "Sim"});
                           setListAdapter(adaptador);
                       }
                       else{
                           listaConf.set(1, new String[]{"Toma Insulina", "Não"});
                           setListAdapter(adaptador);
                       }
                       insulina.dismiss(); //Para sair logo
                   }
               });
        insulina = dialog.create();
        insulina.show();


    }

    //Execício
    AlertDialog exercicio;
    private void exercicio(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog . setTitle ( R . string.exercicio_titulo)
                . setSingleChoiceItems(R.array.confirmacao, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                        if (selectedPosition == 0) {
                            listaConf.set(2, new String[]{"Faz exercício", "Sim"});
                            setListAdapter(adaptador);
                        } else {
                            listaConf.set(2, new String[]{"Faz exercício", "Não"});
                            setListAdapter(adaptador);
                        }
                        exercicio.dismiss(); //Para sair logo
                    }
            });
        exercicio = dialog.create();
        exercicio.show();
    }

    View v;

    private void openDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Limites de Glicemia");
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialoglimites, null);

        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText hipo = (EditText) v.findViewById(R.id.etHipo);
                EditText gli =(EditText) v.findViewById(R.id.editText4);
                EditText hiper = (EditText) v.findViewById(R.id.etHiper);

                listaConf.set(3, new String[]{"Limites Glicemia", "Hipoglicemia: "+hipo.getText().toString()+" mg/DL \nGlicemia Desejada: "+gli.getText().toString()+" mg/dL \nHiperglicemia: "+hiper.getText().toString()+" mg/dL"});
                setListAdapter(adaptador);
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();

    }

}
