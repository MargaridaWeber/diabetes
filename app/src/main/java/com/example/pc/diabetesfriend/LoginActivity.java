package com.example.pc.diabetesfriend;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

import alarmes.Alarme;
import modelo.DiabetesFriend;
import modelo.Utilizador;
import modelo.SessionManager;

public class LoginActivity extends AppCompatActivity{

    DiabetesFriend diabetes;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getApplicationContext());

        int hiperglicemia = 145;
        int hipoglicemia = 70;
        String[] glicemiaDesejada = {"70-110","110-145"};
        String tipo = "Tipo 2";

        //Cria um utilizador
        Utilizador u = new Utilizador("Mónica", new Date(), 'F', 'N', Float.parseFloat("50"), Integer.parseInt("160"), "monica.francisco@hotmail.com", "monica");
        diabetes.adicionarUtilizador(u);
        u.setHiperglicemia(hiperglicemia);
        u.setHipoglicemia(hipoglicemia);
        u.setGlicemiaDesejada(glicemiaDesejada);
        u.setTipoDiabetes(tipo);

        //Se o utilizador estiver logado quando inicia a aplicação redirectiona para o main
        if(session.isLoggedIn()){
            Intent main = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(main);
        }

        //Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.icon);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        actionBar.hide();

        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        TextView tvRegistar = (TextView) this.findViewById(R.id.tvRegistar);

        tvRegistar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent registo = new Intent(getApplicationContext(), Registo.class);
                startActivity(registo);

            }
        });

        btnEntrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(email.isEmpty()){
                    etEmail.setError("O campo não está preenchido");
                }
                if(password.isEmpty()){
                    etPassword.setError("O campo não está preenchido");
                }

                if(!email.isEmpty() && !password.isEmpty())
                {
                    if (diabetes.verificarLogin(email, password)) { //Se o email e password estiverem correctos
                        session.createLoginSession(email, password);
                        Intent conf = new Intent(getApplicationContext(), ConfiguracoesIniciaisActivity.class);
                        startActivity(conf);

                    } else if (!diabetes.verificarUtilizadorExiste(email)) { //Se o utilizador não existir
                        etEmail.setError("O utilizador não está registado.");
                    } else {
                        etPassword.setError("A password está incorrecta.");
                    }
                }
            }
        });
    }
}

