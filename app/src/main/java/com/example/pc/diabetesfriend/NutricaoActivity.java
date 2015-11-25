package com.example.pc.diabetesfriend;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.widget.TabHost;

public class NutricaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricao);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));

        //mudar cor do titulo da action bar
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Nutrição </font>"));

        TabHost tabhost = (TabHost) findViewById(R.id.tabHost);

        tabhost.setup();
        TabHost.TabSpec tabsec = tabhost.newTabSpec("Plano");
        tabsec.setContent(R.id.tab1);
        tabsec.setIndicator("Plano de Alimentação");
        tabhost.addTab(tabsec);

        TabHost.TabSpec tabsec1 = tabhost.newTabSpec("Dicas");
        tabsec1.setContent(R.id.tab2);
        tabsec1.setIndicator("Dicas");
        tabhost.addTab(tabsec1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
