package fragmentsClass;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pc.diabetesfriend.Item;
import com.example.pc.diabetesfriend.MyAdapter;
import com.example.pc.diabetesfriend.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AlarmesFragment extends ListFragment implements AdapterView.OnItemClickListener {

    List<String[]> listaAlarmes;
    ArrayAdapter<String[]> adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarmes, container, false);

        setHasOptionsMenu(true);

        /*listaAlarmes = new LinkedList<String[]>();
        listaAlarmes.add(new String[]{"08:00", "Glicemia"});
        listaAlarmes.add(new String[]{"09:00", "Insulina"});*/



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        /*adaptador = new ArrayAdapter<String[]>(getActivity(), android.R.layout.simple_list_item_2,android.R.id.text1, listaAlarmes){

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

        getListView().setOnItemClickListener(this);*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }



}