package com.example.pc.diabetesfriend;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

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
