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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        //mudar cor do titulo da action bar
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Diabetes Friend </font>"));


        Button btnAlame = (Button) findViewById(R.id.btnAlarmes);
        btnAlame.setOnClickListener(new View.OnClickListener() {
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


        Button btnNutricao = (Button) findViewById(R.id.btnNutricao);
        btnNutricao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nutricao = new Intent(getApplicationContext(), NutricaoActivity.class);
                startActivity(nutricao);
            }
        });

        Button btnGrafico = (Button)findViewById(R.id.btnGraficos);
        btnGrafico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent graficos = new Intent(getApplicationContext(), GraficosActivity.class);
                startActivity(graficos);
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        // Handle navigation dialoglimites item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {

            Intent conf = new Intent(getApplicationContext(), ConfiguracoesGeraisActivity.class);
            startActivity(conf);

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
