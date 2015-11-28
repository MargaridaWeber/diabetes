package com.example.pc.diabetesfriend;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class Configuracoes extends ListActivity {

    private List<String[]> listaConf;
    ArrayAdapter<String[]> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_configuracoes);

        listaConf = new LinkedList<String[]>();
        listaConf.add(new String[]{"Dados Pessoais", "Nome,Data de Nascimento,Genero, Email,PassWord"});
        listaConf.add(new String[]{"Limites da Glicemia", "Hiperglicemia ,Glicemia desejada, Hipoglicemia "});

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

    }



    //Quando selecionamos no item
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Object o = listaConf.get(position).toString();

        int itemPosition = position;

        if(itemPosition==0){

            Intent confdados = new Intent(getApplicationContext(), ConfDados.class);
            startActivity(confdados);
        }
        else{}

       }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
