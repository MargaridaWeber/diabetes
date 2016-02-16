package com.example.pc.diabetesfriend;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.FileOutputStream;
import java.util.HashMap;

import modelo.DiabetesFriend;
import modelo.Glicemia;
import modelo.SessionManager;
import modelo.Utilizador;

public class EnviarRelatorioActivity extends AppCompatActivity {


    DiabetesFriend diabetes;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_relatorio);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getApplicationContext());

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        final Utilizador u = diabetes.pesquisarUtilizador(email);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Enviar Relatório </font>")); //Titulo da action bar

        CheckBox chkTabela = (CheckBox) findViewById(R.id.chkTabela);
        CheckBox chkGrafico = (CheckBox) findViewById(R.id.chkGrafico);
        final RadioGroup rgTabela = (RadioGroup) findViewById(R.id.rgTabela);
        final RadioGroup rgGrafico = (RadioGroup) findViewById(R.id.rgGrafico);


        chkTabela.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if (isChecked == true)
                       rgTabela.setVisibility(View.VISIBLE);
                   else
                       rgTabela.setVisibility(View.GONE);
               }
           }
        );

        chkGrafico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                 @Override
                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     if (isChecked == true)
                         rgGrafico.setVisibility(View.VISIBLE);
                     else
                         rgGrafico.setVisibility(View.GONE);
                 }
             }
        );

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText etDestinatario = (EditText) findViewById(R.id.etDestinatario);
                EditText etCC = (EditText) findViewById(R.id.etCC);
                EditText etAssunto= (EditText) findViewById(R.id.etAssunto);
                EditText etMensagem= (EditText) findViewById(R.id.etMensagem);

                //Log.v(getClass().getSimpleName(), "sPhotoUri=" + Uri.parse("file:/" + sPhotoFileName));
                // emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:/" + sPhotoFileName));

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", etDestinatario.getText().toString(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");

                StringBuilder sb = new StringBuilder();
                for(Glicemia gli: u.getGlicemias7dias()){
                    sb.append(etMensagem.getText().toString()+"<p>Data: "+gli.getDataString() + " Hora: " + gli.getHora() + " Valor: " + gli.getValor() + " Refeição: " + gli.getMomento() + " " + gli.getRefeicao()+"</p><hr>");
                }

                emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(sb.toString()));
                startActivity(Intent.createChooser(emailIntent, "A enviar e-mail..."));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true; default: return super.onOptionsItemSelected(item); }

    }
}
