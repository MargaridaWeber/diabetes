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
import android.widget.Toast;

import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import modelo.DiabetesFriend;
import modelo.SessionManager;
import modelo.Utilizador;


public class ConfIniciaisFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private List<String[]> listaConf;
    ArrayAdapter<String[]> adaptador;
    String tipoDiabetes = null;
    char tomaInsulina;
    char fazExercicio;
    int[] hiperglicemia = {0,0};
    String[] glicemiaDesejada = {"0-0","0-0"};
    int[] hipoglicemia = {0,0};
    String[] gdJejum;
    String[] gdRefeicao;
    DiabetesFriend diabetes;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conf_iniciais, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity().getApplicationContext());

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        final Utilizador u = diabetes.pesquisarUtilizador(email);
        int idade = u.getIdade();
        Toast.makeText(getActivity(), ""+idade, Toast.LENGTH_SHORT).show();



        //verificar os valores do utilizador
        if(idade<18 && u.getAntedecentes()=='N'){
            glicemiaDesejada[0]="70-200";
            glicemiaDesejada[1]="110-145 ";
            hipoglicemia[0]=70;
            hipoglicemia[1]=110;
            hiperglicemia[0]=110;
            hiperglicemia[1]=145;
        }
        gdJejum = glicemiaDesejada[0].split("-");
        gdRefeicao = glicemiaDesejada[1].split("-");


        listaConf = new LinkedList<String[]>();

        listaConf.add(new String[]{"Tipo de Diabetes", "Tipo 1"});
        listaConf.add(new String[]{"Toma Insulina", "Sim"});
        listaConf.add(new String[]{"Faz exercício", "Sim"});
        //mete as string em baixo
        listaConf.add(new String[]{"Hiperglicemia", "Jejum: "+hiperglicemia[0]+" mg/dl\nApós refeição: "+hiperglicemia[1]+" mg/dl"});
        listaConf.add(new String[]{"Glicemia desejada", "Jejum: "+gdJejum[0]+"-"+gdJejum[1]+" mg/dl\nApós refeição: "+glicemiaDesejada[1]+" mg/dl"});
        listaConf.add(new String[]{"Hipoglicemia", "Jejum: "+hipoglicemia[0]+" mg/dl\nApós refeição: "+hipoglicemia[1]+" mg/dl"});





        Button btnSeguinte = (Button) view.findViewById(R.id.btnSeguinte);

        btnSeguinte.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            u.setTipoDiabetes(tipoDiabetes);
            u.setInsulina(tomaInsulina);
            u.setExercicio(fazExercicio);

            //Validar se estão preenchidos
            u.setHiperglicemia(hiperglicemia);
            u.setGlicemiaDesejada(glicemiaDesejada);
            u.setHipoglicemia(hipoglicemia);

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
                String[]entrey = listaConf.get(position);

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
                    tipoDiabetes="Tipo 1";
                } else {
                    listaConf.set(0, new String[]{"Tipo de Diabetes", "Tipo 2"});
                    setListAdapter(adaptador);
                    tipoDiabetes="Tipo 2";
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
                    tomaInsulina = 'S';
                } else {
                    listaConf.set(1, new String[]{"Toma Insulina", "Não"});
                    setListAdapter(adaptador);
                    tomaInsulina = 'N';
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
                            fazExercicio = 'S';
                        } else {
                            listaConf.set(2, new String[]{"Faz exercício", "Não"});
                            setListAdapter(adaptador);
                            fazExercicio = 'N';
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
        dialog.setTitle("Hiperglicemia");
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialoglimites, null);

        dialog.setView(v);

        //Colocar valores nas editTexts
        final EditText editJejum = (EditText) v.findViewById(R.id.etjejum);
        final EditText editRefeicao = (EditText) v.findViewById(R.id.etrefeicao);
        editJejum.setText(hiperglicemia[0]+"");
        editRefeicao.setText(hiperglicemia[1]+"");

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                hiperglicemia[0] = Integer.parseInt(editJejum.getText().toString());
                hiperglicemia[1]  = Integer.parseInt(editRefeicao.getText().toString());

                listaConf.set(3, new String[]{"Hiperglicemia","Jejum: "+hiperglicemia[0]+" mg/dl\nApós refeição: "+hiperglicemia[1]+" mg/dl"});
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

        v = inflater.inflate(R.layout.dialoglimitesdesejada, null);

        dialog.setView(v);

        //Colocar valores nas editTexts
        final EditText editJejum = (EditText) v.findViewById(R.id.etjejum);
        final EditText editRefeicao = (EditText) v.findViewById(R.id.etrefeicao);
        final EditText editJejum2 = (EditText) v.findViewById(R.id.etjejum2);
        final EditText editRefeicao2 = (EditText) v.findViewById(R.id.etrefeicao2);
        editJejum.setText(gdJejum[0]+"");
        editJejum2.setText(gdJejum[1]+"" );
        editRefeicao.setText(gdRefeicao[0]+"");
        editRefeicao2.setText(gdRefeicao[1]+"");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                gdJejum[0] = editJejum.getText().toString();
                gdJejum[1] = editJejum2.getText().toString();
                gdRefeicao[0] = editRefeicao.getText().toString();
                gdRefeicao[1] = editRefeicao2.getText().toString();

                listaConf.set(4, new String[]{"Glicemia Desejada","Jejum: "+gdJejum[0]+"-"+gdJejum[1]+" mg/dl\nApós refeição: "+gdRefeicao[0]+"-"+gdRefeicao[1]+"mg/dl"});
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

        //Colocar valores nas editTexts
        final EditText editJejum = (EditText) v.findViewById(R.id.etjejum);
        final EditText editRefeicao = (EditText) v.findViewById(R.id.etrefeicao);
        editJejum.setText(hipoglicemia[0]+"");
        editRefeicao.setText(hipoglicemia[1]+"");

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                hipoglicemia[0] = Integer.parseInt(editJejum.getText().toString());
                hipoglicemia[1] = Integer.parseInt(editRefeicao.getText().toString());

                listaConf.set(5, new String[]{"Hipoglicemia","Jejum: "+hipoglicemia[0]+" mg/dl\nApós refeição: "+ hipoglicemia[1]+"mg/dl"});
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


}