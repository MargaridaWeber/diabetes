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
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import modelo.Utilizador;

public class Registo extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button button;
    private TextView resultText;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        //Action Bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Registo</font>")); //Cor do titulo
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4"))); //Cor da action bar

        //Receber valores dos objectos
        Button btnRegistar = (Button) findViewById(R.id.btnRegistar);
        final Spinner spnGenero = (Spinner) findViewById(R.id.spinner);
        final Spinner spnAntec = (Spinner) findViewById(R.id.spinnercardiaco);
        EditText etNome = (EditText) findViewById(R.id.etNome);
        EditText etDataNasc = (EditText) findViewById(R.id.etDataNasc);
        EditText etPeso = (EditText) findViewById(R.id.etPeso);
        EditText etAltura = (EditText) findViewById(R.id.etAltura);
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final String nome = etNome.getText().toString();
        final String dataNasc = etDataNasc.getText().toString();
        final String peso = etPeso.getText().toString();
        final String altura = etAltura.getText().toString();
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        //Spinner genero
        ArrayAdapter adapter =  ArrayAdapter.createFromResource(this,R.array.genero,android.R.layout.simple_list_item_single_choice);
        spnGenero.setAdapter(adapter);
        spnGenero.setOnItemSelectedListener(this);

        //Spinner antecedentes
        ArrayAdapter adapterantecedentes =  ArrayAdapter.createFromResource(this,R.array.confirmacao,android.R.layout.simple_list_item_single_choice);
        spnAntec.setAdapter(adapterantecedentes);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(nome.matches("") || dataNasc.matches("") || peso.matches("") || altura.matches("") || email.matches("") || password.matches("")){
                    Toast.makeText(Registo.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }
                else{
                    String genero = spnGenero.getSelectedItem().toString();
                    String antencedentes = spnGenero.getSelectedItem().toString();
                    char gen = genero=="Masculino" ? 'M' : 'F';
                    char ant = antencedentes=="Sim" ? 'S' : 'N';

                    Utilizador user = new Utilizador(nome, gen, ant, Float.parseFloat(peso), Integer.parseInt(altura), email, password);
                }
            }
        });


        etDataNasc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dataPickerDialog();
            }
        });

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
                        EditText data = (EditText) findViewById(R.id.etDataNasc);
                        data.setText(Integer.toString(dayOfMonth)+"/"+Integer.toString(monthOfYear+1)+"/"+Integer.toString(year));
                    }

                }, mYear, mMonth, mDay);

        dpd.show();
    }
    @Override 
    
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    


}

