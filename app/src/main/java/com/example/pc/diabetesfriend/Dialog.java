package com.example.pc.diabetesfriend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

import alarmes.AddAlarmeFragment;
import alarmes.Alarme;
import alarmes.AlarmesActivity;
import modelo.DiabetesFriend;
import modelo.SessionManager;
import modelo.Utilizador;

public class Dialog extends Activity {
    DiabetesFriend diabetes;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.activity_dialog);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.mipmap.icon);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getApplicationContext());

        //Ir buscar utilizador logado
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        Utilizador u = diabetes.pesquisarUtilizador(email);

        final Alarme alarme  = u.getAlarme();
        final String[] hm= alarme.getHora().split(":");

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hm[0]));
        calendar.set(Calendar.MINUTE,  Integer.parseInt(hm[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Button btnAdiar = (Button) findViewById(R.id.btnAdiar);
        Button btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnAdiar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alarme.adiarAlarme(calendar);
                Toast.makeText(Dialog.this, "Lembraremos novamente daqui a 20 minutos", Toast.LENGTH_SHORT).show();
                finish();
            }});
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alarme.cancel();
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
