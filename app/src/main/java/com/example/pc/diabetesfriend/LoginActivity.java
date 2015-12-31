package com.example.pc.diabetesfriend;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import modelo.DiabetesFriend;
import modelo.Utilizador;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity{

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "email";
    public static final String Password = "password";
    SharedPreferences sharedpreferences;
    DiabetesFriend diabetes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        diabetes = DiabetesFriend.getInstance();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Utilizador u = new Utilizador("Mónica", new Date(), 'F', 'N', Float.parseFloat("50"), Integer.parseInt("160"), "monica.francisco@hotmail.com", "monica");
        diabetes.adicionarUtilizador(u);

        //Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.icon);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        actionBar.hide();

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
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

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    etEmail.setError("O campo não está preenchido");
                }
                if(TextUtils.isEmpty(password)){
                    etPassword.setError("O campo não está preenchido");
                }

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(email))
                {
                    if (diabetes.verificarLogin(email, password)) { //Se o email e password estiverem correctos
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(Password, password);
                        editor.putString(Email, email);
                        editor.commit();

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

