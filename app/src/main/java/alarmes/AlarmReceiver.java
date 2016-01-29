package alarmes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.pc.diabetesfriend.R;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer player;
        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        player = MediaPlayer.create( context, R.raw.tone);
        player.start();

    }
}