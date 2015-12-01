package com.example.pc.diabetesfriend;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GlicemiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glicemia);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));
        //mudar cor do titulo da action bar
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Registar Níveis Diários</font>"));

        final EditText dataAtual = (EditText) findViewById(R.id.etdata);
        final EditText horaAtual = (EditText) findViewById(R.id.etHora);
        final TextView iconData = (TextView) findViewById(R.id.icone_data);
        final TextView iconHora = (TextView) findViewById(R.id.iconhora);
        dataAtual.setText(getDate());
        horaAtual.setText(getTime());



        dataAtual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dataPickerDialog();
            }
        });
        iconData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dataPickerDialog();
            }
        });


        iconHora.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dataPickerDialog();
            }
        });

        horaAtual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePickerDialog();
                    }
                });

        }

    public String getDate() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(); return dateFormat.format(date);
    }

    public String getTime(){

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(); return dateFormat.format(date);
    }


    private void timePickerDialog(){

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                EditText horaatual = (EditText) findViewById(R.id.etHora);
                horaatual.setText(selectedHour+":"+ selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Selecione a hora");
        mTimePicker.show();

    }


    private void dataPickerDialog(){
        final Calendar c = Calendar.getInstance();
        int  mYear = c.get(Calendar.YEAR);
        int  mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        EditText data = (EditText) findViewById(R.id.etdata);
                        data.setText(Integer.toString(dayOfMonth)+"/"+Integer.toString(monthOfYear+1)+"/"+Integer.toString(year));
                    }

                }, mYear, mMonth, mDay);

        dpd.show();
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
