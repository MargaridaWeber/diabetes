package fragmentsClass;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pc.diabetesfriend.ConfDadosActivity;
import com.example.pc.diabetesfriend.ConfLimitesActivity;
import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;

import java.util.LinkedList;
import java.util.List;

public class Configuracoes extends ListFragment implements AdapterView.OnItemClickListener {

    private List<String[]> listaConf;
    ArrayAdapter<String[]> adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracoesgerais, container, false);


        listaConf = new LinkedList<String[]>();
        listaConf.add(new String[]{"Dados Pessoais", "Nome,Data de Nascimento,Género, Email,Password"});
        listaConf.add(new String[]{"Limites da Glicemia", "Hiperglicemia ,Glicemia desejada, Hipoglicemia "});

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

    //Quando selecionamos no item


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int itemPosition = position;

        if(itemPosition==0){
            Intent conf = new Intent(getActivity(),ConfDadosActivity.class);
            startActivity(conf);

        }
        if (itemPosition==1){

            Intent configuracoes = new Intent(getActivity(),ConfLimitesActivity.class);
            startActivity(configuracoes);
        }




    }



}
