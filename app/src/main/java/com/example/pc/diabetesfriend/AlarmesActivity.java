package com.example.pc.diabetesfriend;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class AlarmesActivity extends AppCompatActivity {

    ArrayList<Item> items;
    ListView listView;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmes);

        //Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4"))); //Cor da action bar
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Alarmes </font>")); //Cor do titulo

        // 1. pass context and data to the custom adapter
         adapter= new MyAdapter(this, generateData());

        // 2. Get ListView from activity_main.xml
        listView = (ListView) findViewById(R.id.listview);

        //2. setListAdapter
        listView.setAdapter(adapter);
    }

    private ArrayList<Item> generateData(){
        items = new ArrayList<Item>();
        items.add(new Item("8:00", "S T Q Q","Glicemia"));
        items.add(new Item("9:00","Todos os dias","Insulina"));

        return items;
    }

    View v;
    private void openDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Definir alarme");

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.dialog_alarme, null);
        dialog.setView(v);

        //Spinner
        final Spinner spinnerTipo = (Spinner) v.findViewById(R.id.spinnerTipo);
        ArrayAdapter adapterTipo = ArrayAdapter.createFromResource(this, R.array.tipo_alarmes, android.R.layout.simple_list_item_single_choice);
        spinnerTipo.setAdapter(adapterTipo);

        //spinnerTipo.setOnItemSelectedListener(AlarmesFragment.this);

        Spinner spinnerDias = (Spinner) v.findViewById(R.id.spinnerDias);
        ArrayAdapter adapterDias = ArrayAdapter.createFromResource(this, R.array.dias_da_semana, android.R.layout.simple_list_item_multiple_choice);
        spinnerDias.setAdapter(adapterDias);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                TimePicker tp = (TimePicker) v.findViewById(R.id.timePicker);
                int hora = tp.getCurrentHour();
                int minuto = tp.getCurrentMinute();
                String horas = String.valueOf(hora)+":"+String.valueOf(minuto);


                String spTipo = spinnerTipo.getSelectedItem().toString();

                items.add(new Item(horas,"teste",spTipo));

                listView.setAdapter(adapter);
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarmes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.adicionar:
                openDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
