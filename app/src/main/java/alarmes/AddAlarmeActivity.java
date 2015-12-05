package alarmes;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pc.diabetesfriend.R;

public class AddAlarmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarme);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //mostra setinha de andar para tras
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4"))); //Cor da actionbar
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Adicionar Alarme</font>")); //Cor do titulo
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
