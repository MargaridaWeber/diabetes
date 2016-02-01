package alarmes;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.example.pc.diabetesfriend.Dialog;
import com.example.pc.diabetesfriend.GlicemiaActivity;
import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;

import java.util.HashMap;

import modelo.DiabetesFriend;
import modelo.SessionManager;
import modelo.Utilizador;

public class AlarmReceiver extends BroadcastReceiver {
    DiabetesFriend diabetes;
    SessionManager session;
    private android.app.AlertDialog alerta;

    @Override
    public void onReceive(Context context, Intent intent) {
        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(context);

        Intent i = new Intent(context, Dialog.class);
        i .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); context.startActivity(i);

        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        Utilizador u = diabetes.pesquisarUtilizador(email);

        final Alarme alarme  = u.getAlarme();

        alarme.StartMediaplayer();

        //notificaocao
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.icon)
                        .setContentTitle("Alarme")
                        .setContentText("Não se esqueça de controlar os seus níveis!")
                        .setAutoCancel(true); //para ela desaparecer quando se clica

        Intent resultIntent = new Intent(context, GlicemiaActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(GlicemiaActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(9011, mBuilder.build());

    }


}