package fragmentsClass;


import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;
import java.util.LinkedList;
import java.util.List;


public class ConfIniciaisFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private List<String[]> listaConf;
    ArrayAdapter<String[]> adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conf_iniciais, container, false);

        listaConf = new LinkedList<String[]>();

        listaConf.add(new String[]{"Tipo de Diabetes", "Tipo 1"});
        listaConf.add(new String[]{"Toma Insulina", "Sim"});
        listaConf.add(new String[]{"Faz exercício", "Sim"});
        listaConf.add(new String[]{"HiperGlicemia", ""});
        listaConf.add(new String[]{"Glicemia desejada", ""});
        listaConf.add(new String[]{"HipoGlicemia", ""});


        Button btnseguinte = (Button) view.findViewById(R.id.btnSeguinte);

        btnseguinte.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent conf = new Intent(getActivity(), MainActivity.class);
                startActivity(conf);
            }
        });

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int itemPosition = position;

        if(itemPosition==0)
            criarTipos();
        else if(itemPosition==1)
            insulina();
        else if(itemPosition==2)
            exercicio();
        else if(itemPosition==3)
            openEditHiper();
        else if(itemPosition==4)
            openEditGliDesejada();
        else if(itemPosition==5)
            openEditHipo();
    }



    //Tipos
    AlertDialog tipos;
    private void criarTipos(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog . setTitle(R.string.tipos_titulo);
        dialog.setSingleChoiceItems(R.array.tipo, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                if (selectedPosition == 0) {
                    listaConf.set(0, new String[]{"Tipo de Diabetes", "Tipo 1"});
                    setListAdapter(adaptador);
                } else {
                    listaConf.set(0, new String[]{"Tipo de Diabetes", "Tipo 2"});
                    setListAdapter(adaptador);
                }
                tipos.dismiss(); //Para sair logo
            }
        });
        tipos = dialog.create();
        tipos.show();

    }

    //Insulina
    AlertDialog insulina;
    private void insulina(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog . setTitle ( R . string.insulina_titulo);
        dialog.setSingleChoiceItems(R.array.confirmacao, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                if (selectedPosition == 0) {
                    listaConf.set(1, new String[]{"Toma Insulina", "Sim"});
                    setListAdapter(adaptador);
                } else {
                    listaConf.set(1, new String[]{"Toma Insulina", "Não"});
                    setListAdapter(adaptador);
                }
                insulina.dismiss(); //Para sair logo
            }
        });
        insulina = dialog.create();
        insulina.show();


    }

    AlertDialog exercicio;
    private void exercicio(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog . setTitle ( R . string.exercicio_titulo)
                . setSingleChoiceItems(R.array.confirmacao, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                        if (selectedPosition == 0) {
                            listaConf.set(2, new String[]{"Faz exercício", "Sim"});
                            setListAdapter(adaptador);
                        } else {
                            listaConf.set(2, new String[]{"Faz exercício", "Não"});
                            setListAdapter(adaptador);
                        }
                        exercicio.dismiss(); //Para sair logo
                    }
                });
        exercicio = dialog.create();
        exercicio.show();
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

                listaConf.set(3, new String[]{"HiperGlicemia","Jejum: "+editJejum.getText().toString()+" mg/dl\nApós refeição:"+editRefeicao.getText().toString()+" mg/dl"});
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

                listaConf.set(4, new String[]{"Glicemia Desejada","Jejum: "+editJejum.getText().toString()+" mg/dl\nApós refeição:"+editRefeicao.getText().toString()+"mg/dl"});
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


                listaConf.set(5, new String[]{"HipoGlicemia","Jejum: "+editJejum.getText().toString()+" mg/dl\nApós refeição:"+editRefeicao.getText().toString()+"mg/dl"});
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