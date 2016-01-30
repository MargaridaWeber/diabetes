package alarmes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import com.example.pc.diabetesfriend.MainActivity;

import java.util.Calendar;

/**
 * Created by Mónica Francisco on 30/11/2015.
 */
public class Alarme {

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

          /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
    }

    public Alarme(){

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
        Toast.makeText(context, "Alarm", Toast.LENGTH_SHORT).show();
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(context, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void repetir(int hora , int minutos) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20 ;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minutos);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        /* repete de 1 em 1 minuto consoante a hora metida*/
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                interval, pendingIntent);


    }


}
