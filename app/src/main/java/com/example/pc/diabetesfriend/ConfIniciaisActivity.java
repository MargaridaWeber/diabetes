package com.example.pc.diabetesfriend;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ConfIniciaisActivity extends ListActivity {
    private List<String[]> listaConf;
    ArrayAdapter<String[]> adaptador;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_iniciais);

        //getActionBar().show();

        listaConf = new LinkedList<String[]>();

        listaConf.add(new String[]{"Tipo de Diabetes", "tipo"});
        listaConf.add(new String[]{"Toma Insulina", "sim"});
        listaConf.add(new String[]{"Faz exercicio", "sim"});
        listaConf.add(new String[]{"Limites Glicemia", "Hipoglicemia","Glicemia desejada","Hipoglicemia" });



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

        //String  itemValue = (String) listView.getItemAtPosition(position);

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


    private void criarTipos(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog . setTitle(R.string.tipos_titulo);
                dialog.setSingleChoiceItems(R.array.tipo,-1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog tipos = dialog.create();
        tipos.show();

    }


    private void exercicio(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog . setTitle ( R . string.exercicio_titulo)
                . setSingleChoiceItems (R.array.confirmacao,-1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog tipos = dialog.create();
        tipos.show();

    }


    private void insulina(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog . setTitle ( R . string.insulina_titulo)
                . setSingleChoiceItems (R.array.confirmacao,-1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog tipos = dialog.create();
        tipos.show();

    }



    private void openDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Limites de Glicemia");
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.view, null);

        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

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
