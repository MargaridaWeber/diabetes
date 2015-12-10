package nutricao;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.example.pc.diabetesfriend.R;

import nutricao.PlanoAlimentacao;

public class NutricaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricao);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
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


        ImageView imgPequenoAlmoco = (ImageView) findViewById(R.id.imgPequenoAlmoco);
        imgPequenoAlmoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent pequenoAlmoco = new Intent(getApplicationContext(), PequenoAlmocoActivity.class);
                startActivity(pequenoAlmoco);
            }
        });

        ImageView imgMeioManha = (ImageView) findViewById(R.id.imgMeioManha);
        imgMeioManha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent meioManha = new Intent(getApplicationContext(), MeioManhaActivity.class);
                startActivity(meioManha);
            }
        });

        ImageView imgAlmoco = (ImageView) findViewById(R.id.imgAlmoco);
        imgAlmoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent almoco = new Intent(getApplicationContext(), AlmocoActivity.class);
                startActivity(almoco);
            }
        });

        ImageView imgLanche = (ImageView) findViewById(R.id.imgLanche);
        imgLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent lanche = new Intent(getApplicationContext(), LancheActivity.class);
                startActivity(lanche);
            }
        });

        ImageView imgJantar = (ImageView) findViewById(R.id.imgJantar);
        imgJantar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent jantar = new Intent(getApplicationContext(), JantarActivity.class);
                startActivity(jantar);
            }
        });

        ImageView imgCeia = (ImageView) findViewById(R.id.imgCeia);
        imgCeia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent ceia = new Intent(getApplicationContext(), CeiaActivity.class);
                startActivity(ceia);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SubMenu subMenu = menu.addSubMenu(0, Menu.NONE, 0, "Menu title");
        subMenu.getItem().setIcon(R.mipmap.ic_add);
        subMenu.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        subMenu.add(0,1, Menu.NONE, "Adicionar o meu Plano");

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true; default: return super.onOptionsItemSelected(item);
        case 1: //quando carrego no submenu adicionar meu plano
            Intent plano = new Intent(getApplicationContext(), PlanoAlimentacao.class );
            startActivity(plano);
            return true;
        }
    }


}
