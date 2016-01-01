package com.example.pc.diabetesfriend;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import modelo.DiabetesFriend;
import modelo.Utilizador;

public class Registo extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button button;
    private TextView resultText;
    DiabetesFriend diabetes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        diabetes = DiabetesFriend.getInstance();

        //Action Bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //Setinha de andar para trás
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Registo</font>")); //Cor do titulo
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4"))); //Cor da action bar

        //Receber valores dos objectos
        Button btnRegistar = (Button) findViewById(R.id.btnRegistar);
        final Spinner spnGenero = (Spinner) findViewById(R.id.spinner);
        final Spinner spnAntec = (Spinner) findViewById(R.id.spinnercardiaco);
        final EditText etNome = (EditText) findViewById(R.id.etNome);
        final EditText etDataNasc = (EditText) findViewById(R.id.etDataNasc);
        final EditText etPeso = (EditText) findViewById(R.id.etPeso);
        final EditText etAltura = (EditText) findViewById(R.id.etAltura);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        //Spinner genero
        ArrayAdapter adapter =  ArrayAdapter.createFromResource(this,R.array.genero,android.R.layout.simple_list_item_single_choice);
        spnGenero.setAdapter(adapter);
        spnGenero.setOnItemSelectedListener(this);

        //Spinner antecedentes
        ArrayAdapter adapterantecedentes =  ArrayAdapter.createFromResource(this,R.array.confirmacao,android.R.layout.simple_list_item_single_choice);
        spnAntec.setAdapter(adapterantecedentes);


        btnRegistar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean valido = true;
                String nome = etNome.getText().toString();
                String dataNasc = etDataNasc.getText().toString();
                String peso = etPeso.getText().toString();
                String altura = etAltura.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                Date dataNascimento = converterDataDate(dataNasc);

                //Validar nome
                if (TextUtils.isEmpty(nome)) {
                    etNome.setError("O campo não está preenchido.");
                    valido=false;
                }

                //Validar data de nascimento
                if (TextUtils.isEmpty(dataNasc)) {
                    etDataNasc.setError("O campo não está preenchido.");
                    valido=false;
                }
                else if (dataNascimento.after(new Date())) { //Se a data de nascimento for superior a data actual
                    etDataNasc.setError("A data é inválida.");
                    valido=false;
                }

                //Validar peso e altura
                if (TextUtils.isEmpty(peso)) {
                    etPeso.setError("O campo não está preenchido.");
                    valido=false;
                }
                if (TextUtils.isEmpty(altura)) {
                    etAltura.setError("O campo não está preenchido.");
                    valido=false;
                }

                //Validar e-mail
                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("O campo não está preenchido.");
                    valido=false;
                }
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.setError("E-mail inválido.");
                    valido=false;
                }
                else if(diabetes.verificarUtilizadorExiste(email)){
                    etEmail.setError("O e-mail já se encontra registado.");
                    valido=false;
                }

                //Validar password
                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("O campo não está preenchido.");
                    valido=false;
                }
                else if(password.length() < 6 ){
                    etPassword.setError("Mínimo de 6 caracteres.");
                    valido=false;
                }

                if(valido==true) {
                    String genero = spnGenero.getSelectedItem().toString();
                    String antencedentes = spnGenero.getSelectedItem().toString();
                    char gen = genero == "Masculino" ? 'M' : 'F';
                    char ant = antencedentes == "Sim" ? 'S' : 'N';

                    Toast.makeText(Registo.this, "O seu registo foi efectuado com sucesso!", Toast.LENGTH_SHORT).show();

                    Utilizador user = new Utilizador(nome, dataNascimento, gen, ant, Float.parseFloat(peso), Integer.parseInt(altura), email, password);
                    diabetes.adicionarUtilizador(user);

                    Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(login);
                }
            }
        });

        etDataNasc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dataPickerDialog();
            }
        });

    }

    //Converter string para date
    public Date converterDataDate(String data){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date=null;
        try {
            date = formatter.parse(data);
            formatter.format(date);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item); }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    


}

