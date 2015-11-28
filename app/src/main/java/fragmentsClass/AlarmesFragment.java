package fragmentsClass;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pc.diabetesfriend.AlarmesActivity;
import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;

public class AlarmesFragment extends ListFragment implements AdapterView.OnItemClickListener {

    List<String[]> listaAlarmes;
    ArrayAdapter<String[]> adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarmes, container, false);

        listaAlarmes = new LinkedList<String[]>();
        listaAlarmes.add(new String[]{"08:00", "Glicemia"});
        listaAlarmes.add(new String[]{"09:00", "Insulina"});


        Button btnAdd = (Button) view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openDialog();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        adaptador = new ArrayAdapter<String[]>(getActivity(), android.R.layout.simple_list_item_2,android.R.id.text1, listaAlarmes){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String[]entrey = listaAlarmes.get(position);

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

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.adicionar:
                Toast.makeText(getActivity(), "teste", Toast.LENGTH_SHORT).show();
                return false;
            default:
                break;
        }

        return false;
    }

    View v;
    private void openDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Definir alarme");

        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.dialog_alarme, null);
        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Spinner
                Spinner spinnerTipo = (Spinner) v.findViewById(R.id.spinnerTipo);
                ArrayAdapter adapterTipo = ArrayAdapter.createFromResource(getActivity(), R.array.tipo_alarmes, android.R.layout.simple_list_item_single_choice);
                spinnerTipo.setAdapter(adapterTipo);
                //spinner.setOnItemSelectedListener(this);

                Spinner spinnerDias = (Spinner) v.findViewById(R.id.spinnerDias);
                ArrayAdapter adapterDias = ArrayAdapter.createFromResource(getActivity(), R.array.dias_da_semana, android.R.layout.simple_list_item_multiple_choice);
                spinnerDias.setAdapter(adapterDias);

                TimePicker tp = (TimePicker) v.findViewById(R.id.timePicker);
                int hora = tp.getCurrentHour();
                int minuto = tp.getCurrentMinute();

                String teste = spinnerTipo.getItemAtPosition(spinnerTipo.getSelectedItemPosition()).toString();
                String text = spinnerTipo.getSelectedItem().toString();

                listaAlarmes.add(new String[]{String.valueOf(hora + ":" + minuto), text});
                setListAdapter(adaptador);
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dialog.show();

    }

}