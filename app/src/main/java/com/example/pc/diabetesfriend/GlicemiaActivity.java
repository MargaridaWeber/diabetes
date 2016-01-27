package com.example.pc.diabetesfriend;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import modelo.DiabetesFriend;
import modelo.SessionManager;
import modelo.Utilizador;

public class GlicemiaActivity extends AppCompatActivity {

    DiabetesFriend diabetes;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glicemia);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getApplicationContext());

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
        final EditText valorGli = (EditText) findViewById(R.id.etValor);
        Button btnadd = (Button) findViewById(R.id.btnadd);
        Button btnGuardar = (Button) findViewById(R.id.btnguardar);

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

        btnadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                criarinfAdicional();
            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String valorGlicemia = valorGli.getText().toString();
                String hora = horaAtual.getText().toString();
                String data = dataAtual.getText().toString();

                // Obtem dados da sessão
                HashMap<String, String> user = session.getUserDetails();
                String email = user.get(SessionManager.KEY_EMAIL);
                Utilizador u = diabetes.pesquisarUtilizador(email);
                int idade = u.getIdade();


                //Validar valor
                if (valorGli.getText().toString().isEmpty()) {
                    valorGli.setError("O campo não está preenchido.");
                }

                if(idade<18 && u.getAntedecentes()=='N' && Integer.parseInt(valorGlicemia)>100 )



                if(Integer.parseInt(valorGlicemia)>100){
                    Toast.makeText(GlicemiaActivity.this, "valor alto", Toast.LENGTH_SHORT).show();
                }


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



     int mSelected = -1;
    boolean check[] ={false,false};
    final ArrayList<Integer> listaIndicesSeleccionados = new ArrayList();
    final boolean[] isSelectedArray = {false, false, false, false};
    AlertDialog adicional;
    private void criarinfAdicional(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Informações Adicionais");
        dialog.setMultiChoiceItems(R.array.adicionais, isSelectedArray, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                final AlertDialog alert = (AlertDialog) dialog;
                final ListView list = alert.getListView();

                if (isChecked) {
                    listaIndicesSeleccionados.add(which); //Adiciona a lista
                } else if (listaIndicesSeleccionados.contains(which)) {
                    listaIndicesSeleccionados.remove(Integer.valueOf(which)); //Se já existe remove
                }
            }
        });


        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                for (int index :listaIndicesSeleccionados){

                    if (index==0 && check[0] ==false) {
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llpeso);
                        TextView textView1 = new TextView(GlicemiaActivity.this);
                        textView1.setTextAppearance(getApplicationContext(), R.style.normalText);
                        textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        textView1.setText("Peso");
                        textView1.setWidth(250);

                        EditText edit = new EditText(GlicemiaActivity.this);
                        edit.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        edit.setInputType(InputType.TYPE_CLASS_NUMBER);
                        edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
                        edit.setWidth(115);

                        TextView kg = new TextView(GlicemiaActivity.this);
                        kg.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        kg.setText("Kg");

                        linearLayout.addView(textView1);
                        linearLayout.addView(edit);
                        linearLayout.addView(kg);
                        check[0]=true;

                    }
                    if (index == 1 && check[1]==false) {

                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llpressao);
                        TextView textView1 = new TextView(GlicemiaActivity.this);
                        textView1.setTextAppearance(getApplicationContext(), R.style.normalText);
                        textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        textView1.setText("Pressão Arterial");
                        textView1.setWidth(250);

                        EditText edit = new EditText(GlicemiaActivity.this);
                        edit.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        edit.setInputType(InputType.TYPE_CLASS_NUMBER);
                        edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
                        edit.setWidth(115);

                        TextView mmHg = new TextView(GlicemiaActivity.this);
                        mmHg.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        mmHg.setText("mmHg");

                        linearLayout.addView(textView1);
                        linearLayout.addView(edit);
                        linearLayout.addView(mmHg);
                        check[1]=true;


                    }
                }

            }
        });
        //Mete as checkboxs seleccionadas
        for (int i : listaIndicesSeleccionados) {
            isSelectedArray[i] = true;
        }
        adicional = dialog.create();
        adicional.show();
    }



}
