package fragmentsClass;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pc.diabetesfriend.Item;
import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddAlarmeFragment extends ListFragment implements AdapterView.OnItemClickListener {

    List<String[]> lista;
    ArrayAdapter<String[]> adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_alarme, container, false);

        lista = new LinkedList<String[]>();

        lista.add(new String[]{"Tipo de alarme", "Glicemia"});
        lista.add(new String[]{"Dia(s) da semana", "Todos os dias"});

        Button btnAdicionar = (Button) view.findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        return view;
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
                } else {
                    lista.set(0, new String[]{"Tipo de alarme", "Insulina"});
                    setListAdapter(adaptador);
                }
                tipos.dismiss(); //Para sair logo
            }
        });
        tipos = dialog.create();
        tipos.show();

    }

    //Dias da semana
    AlertDialog dias;
    private void criarDias(){
        final ArrayList<Integer> selectedItemsIndexList = new ArrayList();
        boolean[] isSelectedArray = {false, false, false, false,false, false, false, false, false, false,false, false, false,false};
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.dias_titulo);
        dialog.setMultiChoiceItems(R.array.dias_da_semana, isSelectedArray, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    //adiciona a lista
                    selectedItemsIndexList.add(which);
                } else if(selectedItemsIndexList.contains(which)){
                    //se ja existe remove
                    selectedItemsIndexList.remove(Integer.valueOf(which));
                }
            }
        });

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dias = dialog.create();
        dias.show();
    }


    /*View v;
    private void openDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Dias da semana");

        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.dialog_alarme, null);
        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dialog.show();

    }*/
}
