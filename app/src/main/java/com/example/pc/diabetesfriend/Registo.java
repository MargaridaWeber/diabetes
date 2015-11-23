package com.example.pc.diabetesfriend;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

public class Registo extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button button;
    private TextView resultText;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.icon);

        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Registo</font>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        actionBar.show();


        //mete o icon do lado direito
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

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

