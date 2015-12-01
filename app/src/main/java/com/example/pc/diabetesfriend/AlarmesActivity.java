package com.example.pc.diabetesfriend;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Handler;

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
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4"))); //Cor da action bar
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Alarmes </font>")); //Cor do titulo

        adapter = new MyAdapter(this, generateData());
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }

    private ArrayList<Item> generateData(){
        items = new ArrayList<Item>();
        items.add(new Item("8:00", "S T Q Q","Glicemia"));
        items.add(new Item("9:00","Todos os dias","Insulina"));
        return items;
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
                //openDialog();
                Intent addAlarme = new Intent(getApplicationContext(),AddAlarmeActivity.class);
                startActivity(addAlarme);
                return true;
            case android.R.id.home:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
