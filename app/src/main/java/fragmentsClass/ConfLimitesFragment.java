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
import android.widget.EditText;
import android.widget.TextView;

//import com.example.pc.diabetesfriend.ConfDadosActivity;
import com.example.pc.diabetesfriend.R;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import modelo.DiabetesFriend;
import modelo.SessionManager;
import modelo.Utilizador;

public class ConfLimitesFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private List<String[]> listaConf;
    ArrayAdapter<String[]> adaptador;
    DiabetesFriend diabetes;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conf_limites, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity().getApplicationContext());

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);

        Utilizador u = diabetes.pesquisarUtilizador(email);

        listaConf = new LinkedList<String[]>();
        listaConf.add(new String[]{"Hiperglicemia", "Jejum: "+u.getHiperglicemia()[0]+" mg/dl\nApós refeição:"+u.getHiperglicemia()[1]+" mg/dl"});
        listaConf.add(new String[]{"Glicemia Desejada", "Jejum: "+u.getGlicemiaDesejada()[0]+" mg/dl\nApós refeição:"+u.getGlicemiaDesejada()[1]+" mg/dl"});
        listaConf.add(new String[]{"Hipoglicemia", "Jejum: "+u.getHipoglicemia()[0]+" mg/dl\nApós refeição:"+u.getHipoglicemia()[1]+" mg/dl"});

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adaptador = new ArrayAdapter<String[]>(getActivity(), android.R.layout.simple_list_item_2,android.R.id.text1, listaConf){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String[]entrey =listaConf.get(position);

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

    View v;

    private void openEditHiper() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(getActivity());
        dialog.setTitle("HiperGlicemia");
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialoglimites, null);

        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText editJejum = (EditText) v.findViewById(R.id.etjejum);
                EditText editRefeicao = (EditText) v.findViewById(R.id.etrefeicao);


                listaConf.set(0, new String[]{"HiperGlicemia","Jejum: "+editJejum.getText().toString()+" mg/dl\nApós refeição:"+editRefeicao.getText().toString()+" mg/dl"});
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

    private void openEditGliDesejada() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(getActivity());
        dialog.setTitle("Glicemia Desejada");
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialoglimites, null);

        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText editJejum = (EditText) v.findViewById(R.id.etjejum);
                EditText editRefeicao = (EditText) v.findViewById(R.id.etrefeicao);

                listaConf.set(1, new String[]{"Glicemia Desejada","Jejum: "+editJejum.getText().toString()+" mg/dl\nApós refeição:"+editRefeicao.getText().toString()+"mg/dl"});
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
    private void openEditHipo() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(getActivity());
        dialog.setTitle("HipoGlicemia");
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialoglimites, null);

        dialog.setView(v);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText editJejum = (EditText) v.findViewById(R.id.etjejum);
                EditText editRefeicao = (EditText) v.findViewById(R.id.etrefeicao);


                listaConf.set(2, new String[]{"HipoGlicemia","Jejum: "+editJejum.getText().toString()+" mg/dl\nApós refeição:"+editRefeicao.getText().toString()+"mg/dl"});
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int itemPosition = position;
        if(itemPosition==0)
            openEditHiper();
        if (itemPosition==1)
            openEditGliDesejada();
        if (itemPosition==2)
            openEditHipo();

    }
}
