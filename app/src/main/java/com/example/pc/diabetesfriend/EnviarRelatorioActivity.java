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
import android.widget.EditText;

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

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText etDestinatario = (EditText) findViewById(R.id.etDestinatario);
                EditText etAssunto= (EditText) findViewById(R.id.etAssunto);
                EditText etMensagem= (EditText) findViewById(R.id.etMensagem);

                /*Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("image/jpeg");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {etDestinatario.getText().toString()});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, etAssunto.getText().toString());
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, etMensagem.getText().toString());*/
                //Log.v(getClass().getSimpleName(), "sPhotoUri=" + Uri.parse("file:/" + sPhotoFileName));
                // emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:/" + sPhotoFileName));

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", etDestinatario.getText().toString(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");

                StringBuilder sb = new StringBuilder();
                for(Glicemia gli: u.getGlicemias()){
                    sb.append("<p>"+gli.getDataString() + " " + gli.getHora() + " " + gli.getValor() + " " + gli.getRefeicao()+"</p>");
                }

                emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(sb.toString()));

                //emailIntent.putExtra(Intent.EXTRA_STREAM, filelocation);

                startActivity(Intent.createChooser(emailIntent, "Send email..."));
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
