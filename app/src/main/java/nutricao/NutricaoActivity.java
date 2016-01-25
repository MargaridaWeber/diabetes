package nutricao;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.diabetesfriend.R;

public class NutricaoActivity extends AppCompatActivity {

    boolean menuAdd= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricao);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Nutrição </font>")); //mudar cor do titulo da action bar

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabPlano = tabHost.newTabSpec("Plano");
        tabPlano.setContent(R.id.tabPlano);
        tabPlano.setIndicator("Plano de Alimentação");
        tabHost.addTab(tabPlano);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            //LinearLayout ly = (LinearLayout)findViewById(R.id.tabAdd);
            //ly.setVisibility(LinearLayout.VISIBLE);

            TextView tvPeqAlmoco = (TextView) findViewById(R.id.tvPeqAlmoco);
            TextView tvMeioManha = (TextView) findViewById(R.id.tvMeioManha);
            TextView tvAlmoco = (TextView) findViewById(R.id.tvAlmoco);
            TextView tvLanche = (TextView) findViewById(R.id.tvLanche);
            TextView tvJantar = (TextView) findViewById(R.id.tvJantar);
            TextView tvCeia = (TextView) findViewById(R.id.tvCeia);

            //Coloca nas textView o conteúdo recebido da activity Adicionar
            tvPeqAlmoco.setText(extras.getString("pequenoAlmoco"));
            tvMeioManha.setText(extras.getString("meioManha"));
            tvAlmoco.setText(extras.getString("almoco"));
            tvLanche.setText(extras.getString("lanche"));
            tvJantar.setText(extras.getString("jantar"));
            tvCeia.setText(extras.getString("ceia"));

            TabHost.TabSpec tabAdd = tabHost.newTabSpec("adicionar");
            tabAdd.setContent(R.id.tabAdd);
            tabAdd.setIndicator("O meu plano");
            tabHost.addTab(tabAdd);

            menuAdd = true;
        }

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
        subMenu.add(0, 1, Menu.NONE, "Adicionar o meu Plano");

        getMenuInflater().inflate(R.menu.menu_main, menu);

        if(menuAdd==true)
            menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true; default: return super.onOptionsItemSelected(item);
            case 1: //quando carrego no submenu adicionar meu plano
                Intent plano = new Intent(getApplicationContext(), AdicionarPlano.class );
                startActivity(plano);
            return true;
        }
    }


    public void teste(Menu menu) {

    }


}
