package nutricao;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;
public class AdicionarPlano extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_plano);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Plano de Alimentação</font>")); //mudar cor do titulo da action bar

        Button btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //Buscar conteúdo das editTexts
                EditText etPeqAlmoco = (EditText) findViewById(R.id.etPeqAlmoco);
                EditText etMeioManha = (EditText) findViewById(R.id.etMeioManha);
                EditText etAlmoco = (EditText) findViewById(R.id.etAlmoco);
                EditText etLanche = (EditText) findViewById(R.id.etLanche);
                EditText etJantar = (EditText) findViewById(R.id.etJantar);
                EditText etCeia = (EditText) findViewById(R.id.etCeia);

                //Enviar para a activity Nutrição
                Intent plano = new Intent(getApplicationContext(), NutricaoActivity.class);
                plano.putExtra("pequenoAlmoco", etPeqAlmoco.getText().toString());
                plano.putExtra("meioManha", etMeioManha.getText().toString());
                plano.putExtra("almoco", etAlmoco.getText().toString());
                plano.putExtra("lanche", etLanche.getText().toString());
                plano.putExtra("jantar", etJantar.getText().toString());
                plano.putExtra("ceia", etCeia.getText().toString());

                startActivity(plano);
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
