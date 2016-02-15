package com.example.pc.diabetesfriend;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnviarRelatorioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_relatorio);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Enviar Relatório </font>")); //Titulo da action bar

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText etDestinatario = (EditText) findViewById(R.id.etDestinatario);
                EditText etAssunto= (EditText) findViewById(R.id.etAssunto);
                EditText etMensagem= (EditText) findViewById(R.id.etMensagem);

                /*Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("image/jpeg");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {etDestinatario.getText().toString()});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, etAssunto.getText().toString());
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, etMensagem.getText().toString());*/
                //Log.v(getClass().getSimpleName(), "sPhotoUri=" + Uri.parse("file:/" + sPhotoFileName));
                // emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:/" + sPhotoFileName));

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", etDestinatario.getText().toString(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        Html.fromHtml(new StringBuilder()
                                .append("<table><tr><td>data</td><td>hora</td><td>valor</td><td>refeição</td></tr></table>")
                              // .append("<tr><td>data</td><td>hora</td><td>valor</td><td>refeição</td></tr>")
                                .toString())
                );
                startActivity(Intent.createChooser(emailIntent, "Send email..."));

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
