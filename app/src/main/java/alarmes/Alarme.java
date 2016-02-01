package alarmes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;

import java.util.Calendar;


public class Alarme {
    MediaPlayer player;
    static int id = 1;
    private String hora;
    private String dias;
    private String tipo;
    private Boolean modo;
    private Context context;
    private PendingIntent pendingIntent;

    public Alarme(String hora, String dias, String tipo, Context context) {
        super();

        this.hora = hora;
        this.dias = dias;
        this.tipo = tipo;
        this.modo = true;
        this.context = context;

        final String[] hm= hora.split(":");

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hm[0]));
        calendar.set(Calendar.MINUTE,  Integer.parseInt(hm[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, id, alarmIntent, 0);



        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20 ;
        /* repete de 1 em 1 minuto consoante a hora metida*/
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                interval, pendingIntent);
        id++;

        System.out.println(id);

    }


    public int getId() {
        return id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora
    ) {
        this.hora = hora;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getModo() {
        return modo;
    }

    public void setModo(Boolean modo) {
        this.modo = modo;
    }


    public void cancel() {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        this.setModo(false);
        player.stop();
    }


    public void adiarAlarme(Calendar calendar,int intervalo) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * intervalo ;

        /* repete 20 min depois da hora que adiamos , reptindo sempre esse intervalo*/
        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(),
                interval, pendingIntent);
        player.stop();

    }
    public void StartMediaplayerGli(){

        player = MediaPlayer.create(context, R.raw.som);
        player.start();
    }

    public void StartMediaplayerInsulina(){

        player = MediaPlayer.create(context, R.raw.tone);
        player.start();
    }


}
