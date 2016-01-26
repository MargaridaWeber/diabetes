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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.diabetesfriend.R;

import java.util.HashMap;

import modelo.DiabetesFriend;
import modelo.Plano;
import modelo.SessionManager;
import modelo.Utilizador;

public class NutricaoActivity extends AppCompatActivity {

    DiabetesFriend diabetes;
    SessionManager session;
    TextView tvPeqAlmoco;
    TextView tvMeioManha;
    TextView tvAlmoco;
    TextView tvLanche;
    TextView tvJantar;
    TextView tvCeia;
    boolean menuAdd = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricao);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getApplicationContext());

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

        //Tab Plano alimentar
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

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        final Utilizador u = diabetes.pesquisarUtilizador(email);

        //Tab o meu plano
        if (u.getPlano() != null) { //Se receber o plano

            //LinearLayout ly = (LinearLayout)findViewById(R.id.tabAdd);
            //ly.setVisibility(LinearLayout.VISIBLE);

            tvPeqAlmoco = (TextView) findViewById(R.id.tvPeqAlmoco);
            tvMeioManha = (TextView) findViewById(R.id.tvMeioManha);
            tvAlmoco = (TextView) findViewById(R.id.tvAlmoco);
            tvLanche = (TextView) findViewById(R.id.tvLanche);
            tvJantar = (TextView) findViewById(R.id.tvJantar);
            tvCeia = (TextView) findViewById(R.id.tvCeia);

            //Coloca nas textView o plano
            tvPeqAlmoco.setText(u.getPlano().getPeqAlmoco());
            tvMeioManha.setText(u.getPlano().getMeioManha());
            tvAlmoco.setText(u.getPlano().getAlmoco());
            tvLanche.setText(u.getPlano().getLanche());
            tvJantar.setText(u.getPlano().getJantar());
            tvCeia.setText(u.getPlano().getCeia());

            TabHost.TabSpec tabAdd = tabHost.newTabSpec("adicionar");
            tabAdd.setContent(R.id.tabAdd);
            tabAdd.setIndicator("O meu plano");
            tabHost.addTab(tabAdd);

            menuAdd = true;
        }


        final Button btnEditarGuardar = (Button) findViewById(R.id.btnEditarGuardar);
        btnEditarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                EditText etPeqAlmoco = (EditText) findViewById(R.id.etPeqAlmoco);
                EditText etMeioManha = (EditText) findViewById(R.id.etMeioManha);
                EditText etAlmoco = (EditText) findViewById(R.id.etAlmoco);
                EditText etLanche = (EditText) findViewById(R.id.etLanche);
                EditText etJantar = (EditText) findViewById(R.id.etJantar);
                EditText etCeia = (EditText) findViewById(R.id.etCeia);

                if(btnEditarGuardar.getText().toString().equals("Editar")){

                    //Esconde as textView
                    tvPeqAlmoco.setVisibility(View.GONE);
                    tvMeioManha.setVisibility(View.GONE);
                    tvAlmoco.setVisibility(View.GONE);
                    tvLanche.setVisibility(View.GONE);
                    tvJantar.setVisibility(View.GONE);
                    tvCeia.setVisibility(View.GONE);

                    //Mostra as editText
                    etPeqAlmoco.setVisibility(View.VISIBLE);
                    etMeioManha.setVisibility(View.VISIBLE);
                    etAlmoco.setVisibility(View.VISIBLE);
                    etLanche.setVisibility(View.VISIBLE);
                    etJantar.setVisibility(View.VISIBLE);
                    etCeia.setVisibility(View.VISIBLE);

                    //Coloca nas editText o conteúdo das textView
                    etPeqAlmoco.setText(tvPeqAlmoco.getText().toString());
                    etMeioManha.setText(tvMeioManha.getText().toString());
                    etAlmoco.setText(tvAlmoco.getText().toString());
                    etLanche.setText(tvLanche.getText().toString());
                    etJantar.setText(tvJantar.getText().toString());
                    etCeia.setText(tvCeia.getText().toString());

                    //Mudar o botão para Guardar
                    btnEditarGuardar.setText("Guardar");
                }
                else
                {
                    String peqAlmoco = etPeqAlmoco.getText().toString();
                    String meioManha = etMeioManha.getText().toString();
                    String almoco = etAlmoco.getText().toString();
                    String lanche = etLanche.getText().toString();
                    String jantar = etJantar.getText().toString();
                    String ceia = etCeia.getText().toString();

                    //Validar se os campos estão preenchidos
                    if(peqAlmoco.isEmpty()){
                        etPeqAlmoco.setError("O campo não está preenchido.");
                    }

                    if(meioManha.isEmpty()){
                        etMeioManha.setError("O campo não está preenchido.");
                    }

                    if(almoco.isEmpty()){
                        etAlmoco.setError("O campo não está preenchido.");
                    }

                    if(lanche.isEmpty()){
                        etLanche.setError("O campo não está preenchido.");
                    }

                    if(jantar.isEmpty()){
                        etJantar.setError("O campo não está preenchido.");
                    }

                    if(ceia.isEmpty()){
                        etCeia.setError("O campo não está preenchido.");
                    }

                    //Se todos os campos estiveres preenchidos
                    if(!peqAlmoco.isEmpty() && !meioManha.isEmpty() && !almoco.isEmpty() && !lanche.isEmpty() && !jantar.isEmpty() && !ceia.isEmpty() )
                    {
                        //Esconde as editText
                        etPeqAlmoco.setVisibility(View.GONE);
                        etMeioManha.setVisibility(View.GONE);
                        etAlmoco.setVisibility(View.GONE);
                        etLanche.setVisibility(View.GONE);
                        etJantar.setVisibility(View.GONE);
                        etCeia.setVisibility(View.GONE);

                        //Mostra as textView
                        tvPeqAlmoco.setVisibility(View.VISIBLE);
                        tvMeioManha.setVisibility(View.VISIBLE);
                        tvAlmoco.setVisibility(View.VISIBLE);
                        tvLanche.setVisibility(View.VISIBLE);
                        tvJantar.setVisibility(View.VISIBLE);
                        tvCeia.setVisibility(View.VISIBLE);

                        //Altera
                        Plano p = new Plano(peqAlmoco, meioManha, almoco, lanche, jantar, ceia);
                        u.setPlano(p);

                        tvPeqAlmoco.setText(peqAlmoco);
                        tvMeioManha.setText(meioManha);
                        tvAlmoco.setText(almoco);
                        tvLanche.setText(lanche);
                        tvJantar.setText(jantar);
                        tvCeia.setText(ceia);

                        Toast.makeText(NutricaoActivity.this, "O seu plano foi alterado com sucesso!", Toast.LENGTH_SHORT).show();

                        btnEditarGuardar.setText("Editar");
                    }
                }

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

}
