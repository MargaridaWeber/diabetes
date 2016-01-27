package com.example.pc.diabetesfriend;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;

import dicas.DicasExFisico;
import dicas.DicasHiperGlicemia;
import dicas.DicasHipoGlicemia;
import dicas.DicasNutriActivity;


public class Informacoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));

        //mudar cor do titulo da action bar
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Informações</font>"));


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        TabHost tabhost = (TabHost) findViewById(R.id.tabHost);

        tabhost.setup();

        TabHost.TabSpec tabsec = tabhost.newTabSpec("Inf Uteis");
        tabsec.setContent(R.id.tabInf);
        tabsec.setIndicator("Inf Uteis");
        tabhost.addTab(tabsec);



        TabHost.TabSpec tabsec1 = tabhost.newTabSpec("Dicas");
        tabsec1.setContent(R.id.tabDicas);
        tabsec1.setIndicator("Dicas");
        tabhost.addTab(tabsec1);


        Button btntele = (Button) findViewById(R.id.btntele);
        btntele.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri number = Uri.parse("tel:911077432");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });

        ImageView portal = (ImageView) findViewById(R.id.portal_diabetes);
        portal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://www.apdp.pt/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        Button nutricao = (Button) findViewById(R.id.btnNutricao);
        nutricao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nutri = new Intent(getApplicationContext(), DicasNutriActivity.class);
                startActivity(nutri);
            }
        });


        Button hipo = (Button) findViewById(R.id.btnhipo);
        hipo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent hipo = new Intent(getApplicationContext(), DicasHipoGlicemia.class);
                startActivity(hipo);
            }
        });

        Button hiper = (Button) findViewById(R.id.btnhiper);
        hiper.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent hiper = new Intent(getApplicationContext(), DicasHiperGlicemia.class);
                startActivity(hiper);
            }
        });

        Button ex = (Button) findViewById(R.id.btnFisico);
        ex.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ex = new Intent(getApplicationContext(), DicasExFisico.class);
                startActivity(ex);
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
