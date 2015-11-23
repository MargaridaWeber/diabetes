package com.example.pc.diabetesfriend;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

        listaConf.add(new String[]{"Tipo de Diabetes", "tipo"});
        listaConf.add(new String[]{"Toma Insulina", "sim"});
        listaConf.add(new String[]{"Faz exercicio", "sim"});
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


    AlertDialog tipos;

    private void criarTipos(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog . setTitle(R.string.tipos_titulo);
                dialog.setSingleChoiceItems(R.array.tipo, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ConfIniciaisActivity.this, "" + which, Toast.LENGTH_SHORT).show();
                        //para ela sair logo
                        tipos.dismiss();
                    }
                });
         tipos = dialog.create();
        tipos.show();

    }



    private void exercicio(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog . setTitle ( R . string.exercicio_titulo)
                . setSingleChoiceItems (R.array.confirmacao,-1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        Toast.makeText(ConfIniciaisActivity.this, ""+ selectedPosition, Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog tipos = dialog.create();
        tipos.show();

    }


    private void insulina(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog . setTitle ( R . string.insulina_titulo)
                . setSingleChoiceItems(R.array.confirmacao, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog tipos = dialog.create();
        tipos.show();

    }

    View v;

    private void openDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Limites de Glicemia");
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.view, null);

        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText hipo = (EditText) v.findViewById(R.id.etHipo);
                EditText gli =(EditText) v.findViewById(R.id.editText4);
                EditText hiper = (EditText) v.findViewById(R.id.etHiper);

                TextView mostrahipo = (TextView) findViewById(R.id.hipo);
                TextView mostraGlidesejada = (TextView) findViewById(R.id.gliDes);
                TextView mostrahiper = (TextView) findViewById(R.id.hiper);





                //passar valores para a TextView
                String vHipo = hipo.getText().toString();
                mostrahipo.append(vHipo);
                String vGli = gli.getText().toString();
                mostraGlidesejada.append(vGli);
                String vHiper= hiper.getText().toString();
                mostrahiper.append(vHiper);
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
