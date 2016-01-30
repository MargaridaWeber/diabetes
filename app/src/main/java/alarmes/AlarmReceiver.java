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

public class AlarmReceiver extends BroadcastReceiver {

    private android.app.AlertDialog alerta;
    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer player;
        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        player = MediaPlayer.create( context, R.raw.tone);
        player.start();
        Intent i = new Intent(context, Dialog.class);
        i .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); context.startActivity(i);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        //.setSmallIcon(R.drawable.iconn)
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