package nutricao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import com.example.pc.diabetesfriend.ConfiguracoesGeraisActivity;
import com.example.pc.diabetesfriend.R;
public class PlanoAlimentacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_alimentacao);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        //mudar cor do titulo da action bar
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Plano de Alimentação</font>"));

/*   cria uma tab dinamicamente
        Button btnGuardarplano = (Button) findViewById(R.id.btnguardarplano);
        btnGuardarplano.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TabHost tabhost = (TabHost) findViewById(R.id.tabHost);
                TabHost.TabSpec spec = tabhost.newTabSpec("tag1");

                spec.setContent(new TabHost.TabContentFactory() {
                    public View createTabContent(String tag ) {
                        return (new TabLayout(NutricaoActivity));
                    }
                });
                spec.setIndicator("Clock");
                tabhost.addTab(spec);
            }
        });*/
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
