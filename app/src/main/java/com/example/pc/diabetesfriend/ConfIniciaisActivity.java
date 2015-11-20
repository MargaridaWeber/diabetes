package com.example.pc.diabetesfriend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ConfIniciaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_iniciais);

        //getActionBar().show();

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);

        //Spinner
        ArrayAdapter adapter =  ArrayAdapter.createFromResource(this,R.array.genero,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               setContentView(R.layout.activity_registo);
           }
        });


    }


}
