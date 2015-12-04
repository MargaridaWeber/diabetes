package com.example.pc.diabetesfriend;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class GraficosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Estatisticas </font>")); //Cor do titulo



        TabHost tabhost = (TabHost) findViewById(R.id.tabHost2);

        tabhost.setup();
        TabHost.TabSpec tabsec = tabhost.newTabSpec("Estatisticas");
        tabsec.setContent(R.id.tabEstatistica);
        tabsec.setIndicator("Estatistica");
        tabhost.addTab(tabsec);

        TabHost.TabSpec tabsec1 = tabhost.newTabSpec("Tabela");
        tabsec1.setContent(R.id.tabTabela);
        tabsec1.setIndicator("Tabela");
        tabhost.addTab(tabsec1);

        TabHost.TabSpec tabsec2 = tabhost.newTabSpec("Gráficos");
        tabsec2.setContent(R.id.tabGrafico);
        tabsec2.setIndicator("Gráfico");
        tabhost.addTab(tabsec2);


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
