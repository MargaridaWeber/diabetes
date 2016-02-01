package com.example.pc.diabetesfriend;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import alarmes.AlarmesActivity;
import dicas.TabsInformacoes;
import estatisticas.EstatisticasActivity;
import modelo.DiabetesFriend;
import modelo.Utilizador;
import nutricao.NutriActivity;
import modelo.SessionManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DiabetesFriend diabetes;
    SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close );

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Diabetes Friend </font>")); //Cor do titulo

        Button btnAlarme = (Button) findViewById(R.id.btnAlarmes);
        btnAlarme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent principal = new Intent(getApplicationContext(), AlarmesActivity.class);
                startActivity(principal);
            }
        });


        Button btngli = (Button) findViewById(R.id.btnGlicemia);
        btngli.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent principal = new Intent(getApplicationContext(), GlicemiaActivity.class);
                startActivity(principal);
            }
        });


        Button btnGrafico = (Button)findViewById(R.id.btnGraficos);
        btnGrafico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent estatisticas = new Intent(getApplicationContext(), EstatisticasActivity.class);
                startActivity(estatisticas);
            }
        });

        Button btnEnviar = (Button) findViewById(R.id.btnRelatorio);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent enviar = new Intent(getApplicationContext(), EnviarRelatorioActivity.class);
                startActivity(enviar);
            }
        });

        Button btnNutricao = (Button) findViewById(R.id.btnNutricao);
        btnNutricao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nutricao = new Intent(getApplicationContext(), NutriActivity.class);
                startActivity(nutricao);
            }
        });

        Button btnInf = (Button) findViewById(R.id.btnInf);
        btnInf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent inf = new Intent(getApplicationContext(), TabsInformacoes.class);
                startActivity(inf);
            }
        });

        session.checkLogin();

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        Utilizador u = diabetes.pesquisarUtilizador(email);

        TextView tvUtilizador = (TextView) findViewById(R.id.tvUtilizador);
        if(u.getGenero()=='M')
            tvUtilizador.setText("Seja Bem-Vindo " + u.getNome());
        else
            tvUtilizador.setText("Seja Bem-Vinda " + u.getNome());

        //Toast.makeText(MainActivity.this, ""+u.getMediaGlicemia(), Toast.LENGTH_SHORT).show();

        if (!u.getGlicemias().isEmpty()) {
            //Mostra a média
            LinearLayout linearMedia = (LinearLayout) findViewById(R.id.linearMedia);
            TextView tvMedia = (TextView) findViewById(R.id.tvMedia);
            tvMedia.setText(String.valueOf(u.getMediaGlicemia()));

            linearMedia.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
         //   super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            Intent conf = new Intent(getApplicationContext(), ConfiguracoesGeraisActivity.class);
            startActivity(conf);
        }
        else if(id == R.id.sair){
            session.logoutUser();
        }
        else if(id==R.id.agenda) {
            Intent anotacoes = new Intent(getApplicationContext(), CaledarioActivity.class);
            startActivity(anotacoes);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
