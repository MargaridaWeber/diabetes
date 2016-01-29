package alarmes;



import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pc.diabetesfriend.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import modelo.DiabetesFriend;
import modelo.SessionManager;
import modelo.Utilizador;

public class AddAlarmeFragment extends ListFragment implements AdapterView.OnItemClickListener {

    DiabetesFriend diabetes;
    SessionManager session;
    List<String[]> lista;
    ArrayAdapter<String[]> adaptador;
    String dias="Todos os dias";
    String tipo="Glicemia";
    int hora;
    int minutos;
    private PendingIntent pendingIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_alarme, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity().getApplicationContext());

        lista = new LinkedList<String[]>();

        lista.add(new String[]{"Tipo de alarme", "Glicemia"});
        lista.add(new String[]{"Dia(s) da semana", "Todos os dias"});

        Button btnAdicionar = (Button) view.findViewById(R.id.btnAdicionar);
        final TimePicker tpHora = (TimePicker) view.findViewById(R.id.tpHora);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                hora = tpHora.getCurrentHour();
                minutos = tpHora.getCurrentMinute();

                Alarme alarme = new Alarme(hora + ":" + minutos, dias, tipo);

                //Ir buscar utilizador logado
                HashMap<String, String> user = session.getUserDetails();
                String email = user.get(SessionManager.KEY_EMAIL);
                Utilizador u = diabetes.pesquisarUtilizador(email);

                u.adicionarAlarme(alarme);
                Toast.makeText(getActivity(), "O alarme foi adicionado com sucesso!", Toast.LENGTH_SHORT).show();

                Intent alarmes = new Intent(getActivity().getApplicationContext(), AlarmesActivity.class);
                startActivity(alarmes);

                 /* Retrieve a PendingIntent that will perform a broadcast */
               Intent alarmIntent = new Intent(getActivity(), AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, alarmIntent, 0);

                repetir(hora, minutos);


                //start(hora,minutos);


            }
        });

        return view;
    }

    public void start(int hora , int minutos) {
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minutos);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


       // manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(getActivity(), "Alarm Set", Toast.LENGTH_SHORT).show();
    }


    public void cancel() {
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(getActivity(), "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void repetir(int hora , int minutos) {
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 ;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minutos);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        DialogAlarme();
        /* repete de 1 em 1 minuto consoante a hora metida*/
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                interval, pendingIntent);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adaptador = new ArrayAdapter<String[]>(getActivity(), android.R.layout.simple_list_item_2,android.R.id.text1, lista){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String[]entrey =lista.get(position);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(entrey[0]);
                text2.setText(entrey[1]);
                return view;
            }

        };

        setListAdapter(adaptador);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int itemPosition = position;

        if(itemPosition==0)
            criarTipo();
        else if(itemPosition==1)
            criarDias();
    }


   AlertDialog alerta;
    public void DialogAlarme() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Despertador");
        builder.setMessage("Esta na hora");
        builder.setPositiveButton("Adiar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                repetir(hora, minutos);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancel();
            }
        });
        alerta = builder.create();
        alerta.show();
    }



    //Tipos
    AlertDialog tipos;
    private void criarTipo(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog . setTitle(R.string.tipo_titulo);
        dialog.setSingleChoiceItems(R.array.tipo_alarmes, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                if (selectedPosition == 0) {
                    lista.set(0, new String[]{"Tipo de alarme", "Glicemia"});
                    setListAdapter(adaptador);
                    tipo = "Glicemia";
                } else {
                    lista.set(0, new String[]{"Tipo de alarme", "Insulina"});
                    setListAdapter(adaptador);
                    tipo = "Insulina";
                }
                tipos.dismiss(); //Para sair logo
            }
        });
        tipos = dialog.create();
        tipos.show();

    }

    //Dias da semana
    final ArrayList<Integer> listaIndicesSeleccionados = new ArrayList();
    final boolean[] isSelectedArray = {false, false, false, false, false, false, false, false, false, false,false, false, false,false};
    AlertDialog diass;
    private void criarDias(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        dialog.setTitle(R.string.dias_titulo);
        dialog.setMultiChoiceItems(R.array.dias_da_semana, isSelectedArray, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                final AlertDialog alert = (AlertDialog) dialog;
                final ListView list = alert.getListView();

                //Se seleccionar a primeira opção
                if(isChecked && which==0){
                    for (int i=1;i< isSelectedArray.length;i++ ) {
                        list.setItemChecked(i, true);
                        listaIndicesSeleccionados.add(i);
                    }
                }

                if(!isChecked && which==0){ //Se deseleccionar a primeira opção
                    for (int i=1;i< isSelectedArray.length;i++ ) {
                        list.setItemChecked(i, false);
                        listaIndicesSeleccionados.remove(Integer.valueOf(i));
                    }
                }
                
                if(!isChecked && isSelectedArray[0]==true){ //Se desleccionar uma das opções e a primeira estiver seleccionada
                    list.setItemChecked(0, false);
                    //isSelectedArray[0]=false;
                    listaIndicesSeleccionados.remove(Integer.valueOf(which));
                }

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
                for (int index : listaIndicesSeleccionados) {
                    switch (index) {
                        case 1:
                            dias = dias + "Seg ";
                            break;
                        case 2:
                            dias = dias + "Ter ";
                            break;
                        case 3:
                            dias = dias + "Qua ";
                            break;
                        case 4:
                            dias = dias + "Qui ";
                            break;
                        case 5:
                            dias = dias + "Sex ";
                            break;
                        case 6:
                            dias = dias + "Sab ";
                            break;
                        case 7:
                            dias = dias + "Dom ";
                            break;
                    }
                }
                lista.set(1, new String[]{"Dia(s) da semana", dias});
                setListAdapter(adaptador);
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //Mete as checkboxs seleccionadas
        for (int i : listaIndicesSeleccionados) {
            isSelectedArray[i] = true;
        }

        diass = dialog.create();
        diass.show();
    }




}
