package alarmes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;

import java.util.Calendar;
import java.util.HashMap;

import modelo.DiabetesFriend;
import modelo.SessionManager;
import modelo.Utilizador;

public class DialogInsulina extends Activity {

    DiabetesFriend diabetes;
    SessionManager session;
     Alarme alarme;
    Calendar calendar;
    int  intervalo=0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.activity_dialog_insulina);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.mipmap.icon);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getApplicationContext());

        //Ir buscar utilizador logado
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        Utilizador u = diabetes.pesquisarUtilizador(email);

        alarme = u.getAlarme();
        final String[] hm= alarme.getHora().split(":");

        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hm[0]));
        calendar.set(Calendar.MINUTE,  Integer.parseInt(hm[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Button btnAdiar = (Button) findViewById(R.id.btnAdiar);

         Button btnCancelar = (Button) findViewById(R.id.btnCancelar);

                btnAdiar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        criarAdiar();
                    }
                });
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent gli = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(gli);
                        alarme.cancel();
                        finish();
                    }
                });
            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {

                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

    //opcoes adiar
    AlertDialog tempos;
    private void criarAdiar(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(DialogInsulina.this);
        dialog . setTitle("Adiar tempo de alarme");
        dialog.setIcon(R.drawable.ic_alarm_orange);
        dialog.setSingleChoiceItems(R.array.adiar_alarmes, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                if (selectedPosition == 0) {
                    intervalo = 10;
                    alarme.adiarAlarme(calendar,intervalo);
                    Toast.makeText(DialogInsulina.this, "Lembraremos novamente daqui a 10 minutos", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(selectedPosition == 1){
                    intervalo = 20;
                    alarme.adiarAlarme(calendar,intervalo);
                    Toast.makeText(DialogInsulina.this, "Lembraremos novamente daqui a 20 minutos", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (selectedPosition == 2){
                    intervalo = 60;
                    alarme.adiarAlarme(calendar,intervalo);
                    Toast.makeText(DialogInsulina.this, "Lembraremos novamente daqui a 1 hora", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (selectedPosition == 3){
                    intervalo = 120;
                    alarme.adiarAlarme(calendar,intervalo);
                    Toast.makeText(DialogInsulina.this, "Lembraremos novamente daqui a 2 horas", Toast.LENGTH_SHORT).show();
                    finish();
                }
                tempos.dismiss(); //Para sair logo
            }
        });
        tempos = dialog.create();
        tempos.show();

    }
        }
