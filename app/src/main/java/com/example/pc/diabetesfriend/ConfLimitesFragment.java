package com.example.pc.diabetesfriend;

import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

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
    Utilizador u;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conf_limites, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity().getApplicationContext());

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);

        u = diabetes.pesquisarUtilizador(email);

        listaConf = new LinkedList<String[]>();
        listaConf.add(new String[]{"Hiperglicemia", "Superior a "+u.getHiperglicemia()+" mg/dl"});
        listaConf.add(new String[]{"Glicemia Desejada", "Jejum: "+u.getGlicemiaDesejada()[0]+" mg/dl\nApós refeição:"+u.getGlicemiaDesejada()[1]+" mg/dl"});
        listaConf.add(new String[]{"Hipoglicemia", "Inferior a "+u.getHipoglicemia()+" mg/dl"});

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
        dialog.setTitle("Hiperglicemia");
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialog_limites_hiper, null);

        dialog.setView(v);

        //Coloca o  valor na editText
        final EditText etValor = (EditText) v.findViewById(R.id.etValor);
        etValor.setText(String.valueOf(u.getHiperglicemia()));

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                String valor = etValor.getText().toString();

                //Altera
                listaConf.set(0, new String[]{"Hiperglicemia","Superior a "+valor+" mg/dl"});
                setListAdapter(adaptador);

                u.setHiperglicemia(Integer.parseInt(valor));
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

        v = inflater.inflate(R.layout.dialoglimitesdesejada, null);

        dialog.setView(v);
        final String[] gdJejum = u.getGlicemiaDesejada()[0].split("-");
        final String[] gdRefeicao = u.getGlicemiaDesejada()[1].split("-");

        //Colocar valores nas editTexts
        final EditText editJejum = (EditText) v.findViewById(R.id.etjejum);
        final EditText editRefeicao = (EditText) v.findViewById(R.id.etrefeicao);
        final EditText editJejum2 = (EditText) v.findViewById(R.id.etjejum2);
        final EditText editRefeicao2 = (EditText) v.findViewById(R.id.etrefeicao2);

        editJejum.setText(gdJejum[0] + "");
        editJejum2.setText(gdJejum[1]+"");
        editRefeicao.setText(gdRefeicao[0] + "");
        editRefeicao2.setText(gdRefeicao[1] + "");


        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                gdJejum[0] = editJejum.getText().toString();
                gdJejum[1] = editJejum2.getText().toString();
                gdRefeicao[0] = editRefeicao.getText().toString();
                gdRefeicao[1] = editRefeicao2.getText().toString();

                //Altera
                listaConf.set(1, new String[]{"Glicemia Desejada","Jejum: "+gdJejum[0]+"-"+gdJejum[1]+" mg/dl\nApós refeição: "+gdRefeicao[0]+"-"+gdRefeicao[1]+"mg/dl"});
                setListAdapter(adaptador);

                String[] glicemiaDesejada = {gdJejum[0]+"-"+gdJejum[1],gdRefeicao[0]+"-"+gdRefeicao[1]};
                u.setGlicemiaDesejada(glicemiaDesejada);
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
        dialog.setTitle("Hipoglicemia");
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialog_limites_hipo, null);

        dialog.setView(v);

        //Coloca o valor na editText
        final EditText etValor = (EditText) v.findViewById(R.id.etValor);
        etValor.setText(String.valueOf(u.getHipoglicemia()));

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String valor = etValor.getText().toString();

                listaConf.set(2, new String[]{"Hipoglicemia","Inferior a "+valor+" mg/dl"});
                setListAdapter(adaptador);

                u.setHiperglicemia(Integer.parseInt(valor));
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
