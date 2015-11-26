package com.example.pc.diabetesfriend;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class ConfDados extends ListActivity {
    View v;
    private List<String[]> listaConf;
    ArrayAdapter<String[]> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_dados);

        listaConf = new LinkedList<String[]>();

        listaConf.add(new String[]{"Nome", ""});
        listaConf.add(new String[]{"Data de Nascimento", ""});
        listaConf.add(new String[]{"Género", ""});
        listaConf.add(new String[]{"Email", ""});
        listaConf.add(new String[]{"Password", ""});


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
            openEditNome();
        if(itemPosition==1)
            dataPickerDialog();
        if (itemPosition==2)
            criarGenero();
        if(itemPosition==3)
            openEditEmail();
        if(itemPosition==4)
            openEditPass();


    }


    //genero
    AlertDialog genero;
    private void criarGenero(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog . setTitle("Genero");
        dialog.setSingleChoiceItems(R.array.genero, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();

                if(selectedPosition==0){
                    listaConf.set(2, new String[]{"Género", "Feminino"});
                    setListAdapter(adaptador);
                }
                else{
                    listaConf.set(2, new String[]{"Género", "Masculino"});
                    setListAdapter(adaptador);
                }
                genero.dismiss(); //Para sair logo
            }
        });
        genero = dialog.create();
        genero.show();

    }



    private void dataPickerDialog(){
        final Calendar c = Calendar.getInstance();
       int  mYear = c.get(Calendar.YEAR);
       int  mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                    }
                }, mYear, mMonth, mDay);
        dpd.show();


    }


    private void openEditNome() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Nome");
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialogedit, null);

        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText edit = (EditText) v.findViewById(R.id.editText3);

            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();

    }
    private void openEditEmail() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Email");
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialogedit, null);

        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText edit = (EditText) v.findViewById(R.id.editText3);

            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();

    }

    private void openEditPass() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Password");
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialogedit, null);

        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText edit = (EditText) v.findViewById(R.id.editText3);

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
