package com.example.pc.diabetesfriend;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

public class Registo extends Activity implements AdapterView.OnItemSelectedListener{

    private Button button;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        /*ActionBar actionBar = getActionBar();
        actionBar.show();*/

        //Receber valores dos objectos
        Button btnRegistar = (Button) findViewById(R.id.btnRegistar);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //Spinner
        ArrayAdapter adapter =  ArrayAdapter.createFromResource(this,R.array.genero,android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        btnRegistar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = spinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "Toast" + text, Toast.LENGTH_LONG).show();

            }
        });


    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(parent.getContext(), "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}

