package com.example.pc.diabetesfriend;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import dicas.TabsInformacoes;
import modelo.DiabetesFriend;
import modelo.Glicemia;
import modelo.Peso;
import modelo.PressaoArterial;
import modelo.SessionManager;
import modelo.Utilizador;

public class GlicemiaActivity extends AppCompatActivity {

    DiabetesFriend diabetes;
    SessionManager session;
    Utilizador u;
    EditText data;
    EditText hora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glicemia);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getApplicationContext());

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        u = diabetes.pesquisarUtilizador(email);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //setinha
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e4e4e4")));  //mudar cor do titulo da action bar
        actionBar.setTitle(Html.fromHtml("<font color='#0060a2'>Registar Níveis Diários</font>"));

        data= (EditText) findViewById(R.id.etdata);
        hora = (EditText) findViewById(R.id.etHora);
        final TextView iconData = (TextView) findViewById(R.id.icone_data);
        final TextView iconHora = (TextView) findViewById(R.id.iconhora);
        final EditText valorGli = (EditText) findViewById(R.id.etValor);
        final Spinner spRefeicao = (Spinner) findViewById(R.id.spRefeicao);
        final Spinner spMomento = (Spinner) findViewById(R.id.spmomento);
        final CheckBox chkPeso = (CheckBox) findViewById(R.id.chkPeso);
        final LinearLayout linearPeso = (LinearLayout) findViewById(R.id.linearPeso);
        final CheckBox chkPressao = (CheckBox) findViewById(R.id.chkPressaoArterial);
        final LinearLayout linearPressao = (LinearLayout) findViewById(R.id.linearPressao);
        final EditText etPeso = (EditText) findViewById(R.id.etPeso);
        final EditText etSistolica = (EditText) findViewById(R.id.etSistolica);
        final EditText etDiastolica = (EditText) findViewById(R.id.etDiastolica);
        final EditText etNotas = (EditText) findViewById(R.id.etNotas);
        Button btnRegistar = (Button) findViewById(R.id.btnRegistar);


        data.setText(getDate());
        hora.setText(getTime());
        etPeso.setText(Float.toString(u.getPeso()));

        data.setOnClickListener(new View.OnClickListener() {
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

        hora.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePickerDialog();
            }
        });


        chkPeso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if (isChecked == true)
                       linearPeso.setVisibility(View.VISIBLE);
                   else
                       linearPeso.setVisibility(View.GONE);
               }
           }
        );


        chkPressao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

              @Override
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  if (isChecked == true)
                      linearPressao.setVisibility(View.VISIBLE);
                  else
                      linearPressao.setVisibility(View.GONE);
              }
          }
        );


        btnRegistar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String dateString = data.getText().toString();
                String hour = hora.getText().toString();
                String glicemia = valorGli.getText().toString();
                String momento = spMomento.getSelectedItem().toString();
                String refeicao = spRefeicao.getSelectedItem().toString();
                String peso = etPeso.getText().toString();
                String sistolica =  etSistolica.getText().toString();
                String diastolica =  etDiastolica.getText().toString();
                String notas = etNotas.getText().toString();
                String[] vJejum = u.getGlicemiaDesejada()[0].split("-");
                String[] vRef = u.getGlicemiaDesejada()[1].split("-");

                int idade = u.getIdade();


                //Validar valor
                if (glicemia.isEmpty()) {
                    valorGli.setError("O campo não está preenchido.");
                } else {
                    int valorGlicemia = Integer.parseInt(glicemia);

                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = null;
                    try {
                        date = format.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Glicemia gli = new Glicemia(date, hour, valorGlicemia, momento, refeicao, notas);
                    u.adicionarGlicemia(gli);

                    //Se o utilizador inserir o peso ou a pressão
                    if(!peso.equals("")){
                        Peso p = new Peso(date, hour, Float.parseFloat(peso));
                        u.adicionarPeso(p);
                    }
                    if(!sistolica.equals("") && !diastolica.equals("")){
                        PressaoArterial pa = new PressaoArterial(date,hour, Integer.parseInt(sistolica), Integer.parseInt(diastolica));
                        u.adicionarPressaoArterial(pa);
                    }

                    boolean validar = true;

                    if (valorGlicemia < 70) {
                        DialogHipo();
                        validar = false;
                    }
                    else if (valorGlicemia > 180){
                        DialogHiper();
                        validar = false;
                    }
                    //valor da glicemia em jejum abaixo dos objetivos
                    else if(valorGlicemia < Integer.parseInt(vJejum[0]) && momento.equals("Antes")) {
                        DialogAvisoBaixo();
                        validar = false;
                    }
                    //valor da glicemia dps da refeicao maior que nos objetivos
                    else if(valorGlicemia > Integer.parseInt(vRef[1]) && momento.equals("Depois")){
                        DialogAvisoAlto();
                        validar = false;
                    }
                    //valor da glicemia em jejum acima dos objetivos
                    else if(valorGlicemia >Integer.parseInt(vJejum[1]) && momento.equals("Antes")){
                        DialogAvisoAlto();
                        validar = false;
                    }
                    //valor da glicemia dps da refeicao abaixo dos objetivos
                   else if(valorGlicemia < Integer.parseInt(vRef[0]) && momento.equals("Depois")){
                        DialogAvisoBaixo();
                        validar = false;
                    }


                    if (valorGlicemia > 200){
                        DialogCuidado();
                        validar = false;}


                    if(validar==true){

                        Intent main = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(main);
                    }
                }
            }
        });
    }
    //botao andar para tras android
    @Override
    public void onBackPressed()
    {   Intent main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(main);
    }
    private AlertDialog alerta;

    private void DialogHipo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Risco de Hipoglicemia!");
        builder.setMessage("Cuidado! Pode estar em risco de hipoglicemia\n\n• Deverá ingerir 1 a 2 pacotes de açúcar \n• Depois de 15 minutos voltar a medir\n• Consulte as nossas Dicas Hipoglicemia");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent principal = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(principal);
            }
        });
        alerta = builder.create();
        alerta.show();

        //Mudar cor do titulo
        int textViewId = alerta.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
        TextView tv = (TextView) alerta.findViewById(textViewId);
        tv.setTextColor(getResources().getColor(R.color.red));

        //Mudar cor do divisor
        int dividerId = alerta.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = alerta.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.red));

    }



    private void DialogAvisoBaixo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Valor de glicemia baixo!");
        builder.setMessage("Cuidado! Está abaixo dos seus objetivos glicemicos\n\n• Deverá comer algo e medir novamente os seus níveis");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent principal = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(principal);
            }
        });
        alerta = builder.create();
        alerta.show();

        //Mudar cor do titulo
        int textViewId = alerta.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
        TextView tv = (TextView) alerta.findViewById(textViewId);
        tv.setTextColor(getResources().getColor(R.color.orange));

        //Mudar cor do divisor
        int dividerId = alerta.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = alerta.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.orange));
    }



    private void DialogAvisoAlto() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Valor de glicemia alto!");
        builder.setMessage("Cuidado! Está acima dos seus objetivos glicemicos\n\n• Deverá beber água e medir novamente os seus níveis");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent principal = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(principal);
            }
        });
        alerta = builder.create();
        alerta.show();

        //Mudar cor do titulo
        int textViewId = alerta.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
        TextView tv = (TextView) alerta.findViewById(textViewId);
        tv.setTextColor(getResources().getColor(R.color.orange));

        //Mudar cor do divisor
        int dividerId = alerta.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = alerta.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.orange));
    }


    private void DialogHiper() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Risco de Hiperglicemia!");
        builder.setMessage("Cuidado! Pode estar em risco de hiperglicemia\n\n• Deverá beber muita água \n• Depois de 15 minutos voltar a medir\n• Se não baixar fale com um médico");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent principal = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(principal);
            }
        });
        alerta = builder.create();
        alerta.show();

        //Mudar cor do titulo
        int textViewId = alerta.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
        TextView tv = (TextView) alerta.findViewById(textViewId);
        tv.setTextColor(getResources().getColor(R.color.red));

        //Mudar cor do divisor
        int dividerId = alerta.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = alerta.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.red));
    }

    private void DialogCuidado() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cuidado!");
        builder.setMessage("Não pode realizar exercício físico nestas condições\n\n• Deverá beber muita água \n• Se não baixar fale com o seu médico");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        alerta = builder.create();
        alerta.show();

        //Mudar cor do titulo
        int textViewId = alerta.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
        TextView tv = (TextView) alerta.findViewById(textViewId);
        tv.setTextColor(getResources().getColor(R.color.red));

        //Mudar cor do divisor
        int dividerId = alerta.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = alerta.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.red));
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getTime() {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


    private void timePickerDialog() {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                EditText horaatual = (EditText) findViewById(R.id.etHora);
                horaatual.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Selecione a hora");
        mTimePicker.show();

    }


    private void dataPickerDialog() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        data.setText(Integer.toString(dayOfMonth) + "/" + Integer.toString(monthOfYear + 1) + "/" + Integer.toString(year));
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
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
