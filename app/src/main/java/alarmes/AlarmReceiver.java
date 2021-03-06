package alarmes;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;

import java.util.HashMap;

import modelo.DiabetesFriend;
import modelo.SessionManager;
import modelo.Utilizador;

public class AlarmReceiver extends BroadcastReceiver {
    DiabetesFriend diabetes;
    SessionManager session;


    @Override
    public void onReceive(Context context, Intent intent) {
        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(context);
        final Context c = context;

        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        Utilizador u = diabetes.pesquisarUtilizador(email);

        final Alarme alarme  = u.getAlarme();

        if(alarme.getTipo().equals("Glicemia")){
            Intent i = new Intent(context, Dialog.class);
            i .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            alarme.StartMediaplayerGli();

            Uri sound = RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_NOTIFICATION);
            Intent resultIntent = new Intent(context, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


            //notificaocao
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.iconn)
                            .setContentTitle("Alarme")
                            .setContentIntent(resultPendingIntent)
                            .setSound(sound)
                            .setContentText("Não se esqueça de controlar os seus níveis!")
                            .setAutoCancel(true); //para ela desaparecer quando se clica

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(9011, mBuilder.build());

        }
        else {
            Intent i = new Intent(context, DialogInsulina.class);
            i .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            alarme.StartMediaplayerInsulina();

            //notificaocao
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.iconn)
                            .setContentTitle("Alarme")
                            .setContentText("Não se esqueça de aplicar insulina!")
                            .setAutoCancel(true); //para ela desaparecer quando se clica

            Intent resultIntent = new Intent(context, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(0, mBuilder.build());



        }





    }


}