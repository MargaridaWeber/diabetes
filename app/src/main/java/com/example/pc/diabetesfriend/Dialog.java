package com.example.pc.diabetesfriend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import alarmes.AddAlarmeFragment;
import alarmes.Alarme;
import alarmes.AlarmesActivity;

public class Dialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.activity_dialog);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.mipmap.icon);
        final Alarme alarme  = new Alarme();
        Button btnAdiar = (Button) findViewById(R.id.btnAdiar);
        Button btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //  alarme.cancel();
                Toast.makeText(Dialog.this, "cancelou", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
