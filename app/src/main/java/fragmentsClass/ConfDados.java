package fragmentsClass;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.diabetesfriend.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import modelo.DiabetesFriend;
import modelo.SessionManager;
import modelo.Utilizador;

public class ConfDados extends ListFragment implements AdapterView.OnItemClickListener {
    View v;
    private List<String[]> listaConf;
    ArrayAdapter<String[]> adaptador;

    DiabetesFriend diabetes;
    SessionManager session;
    Utilizador u;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conf_dados, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity().getApplicationContext());

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);

        u = diabetes.pesquisarUtilizador(email);

        listaConf = new LinkedList<String[]>();
        listaConf.add(new String[]{"Nome", u.getNome()});
        listaConf.add(new String[]{"Data de Nascimento", converterDataString(u.getDataNasc())});
        listaConf.add(new String[]{"Género", u.getGenero() == 'M' ? "Masculino" : "Feminino"});
        listaConf.add(new String[]{"E-mail", u.getEmail()});
        listaConf.add(new String[]{"Password", "Nova password"});

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

    private void openEditNome() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Nome");
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialogedit, null);

        dialog.setView(v);

        //Coloca nome na editText
        final EditText edit = (EditText) v.findViewById(R.id.editText3);
        edit.setText(u.getNome());

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                String nome = edit.getText().toString();
                if (TextUtils.isEmpty(nome)) {
                    edit.setError("O campo não está preenchido.");
                } else {
                    //Altera nome
                    u.setNome(nome);
                    listaConf.set(0, new String[]{"Nome", nome});
                    setListAdapter(adaptador);
                }
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();
    }


    private void dataPickerDialog(){
        final Calendar c = Calendar.getInstance();
        int  mYear = c.get(Calendar.YEAR);
        int  mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        String date = converterDataString(u.getDataNasc());

        //Coloca a data no dataPickerDialog
        String[] split = date.split("/");
        mYear = Integer.parseInt(split[2]);
        mMonth =  Integer.parseInt(split[1])-1;
        mDay =  Integer.parseInt(split[0]);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String data = Integer.toString(dayOfMonth)+"/"+Integer.toString(monthOfYear+1)+"/"+Integer.toString(year);

                        //Altera a data
                        u.setDataNasc(converterDataDate(data));
                        listaConf.set(1, new String[]{"Nome", data});
                        setListAdapter(adaptador);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    //genero
    AlertDialog genero;
    private void criarGenero(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Género");
        dialog.setSingleChoiceItems(R.array.genero, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();

                if(selectedPosition==0){
                    listaConf.set(2, new String[]{"Género", "Feminino"});
                    setListAdapter(adaptador);
                    u.setGenero(selectedPosition == 0 ? 'F' : 'M');
                }
                else{
                    listaConf.set(2, new String[]{"Género", "Masculino"});
                    setListAdapter(adaptador);
                    u.setGenero(selectedPosition == 0 ? 'F' : 'M');
                }
                genero.dismiss(); //Para sair logo
            }
        });
        genero = dialog.create();
        genero.show();
    }

    private void openEditEmail() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Email");
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialogedit, null);

        dialog.setView(v);

        //Coloca email na editText
        final EditText edit = (EditText) v.findViewById(R.id.editText3);
        edit.setText(u.getEmail());

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                String email = edit.getText().toString();
                //Valida e-mail
                if (TextUtils.isEmpty(email)) {
                    edit.setError("O campo não está preenchido.");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    edit.setError("E-mail inválido.");
                } else if (diabetes.verificarUtilizadorExiste(email)) {
                    edit.setError("O e-mail já se encontra registado.");
                } else {
                    //Altera e-mail
                    u.setEmail(email);
                    listaConf.set(3, new String[]{"E-mail", email});
                    setListAdapter(adaptador);
                }
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();

    }

    private void openEditPass() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Nova password");
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.dialogedit, null);

        dialog.setView(v);

        final EditText edit = (EditText) v.findViewById(R.id.editText3);
        edit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = edit.getText().toString();
                //Validar password
                if (TextUtils.isEmpty(password)) {
                    edit.setError("O campo não está preenchido.");
                }
                else if(password.length() < 6 ){
                    edit.setError("Mínimo de 6 caracteres.");
                }
                else{
                    //Altera password
                    u.setPassword(password);
                }
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();

    }

    //Converter date para string
    public String converterDataString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String data = formatter.format(date);
        return data;
    }

    //Converter string para date
    public Date converterDataDate(String data){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date=null;
        try {
            date = formatter.parse(data);
            formatter.format(date);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Quando selecionamos no item
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int itemPosition = position;

        if(itemPosition==0)
            openEditNome();
        if(itemPosition==1)
            dataPickerDialog();
        if (itemPosition==2)
            criarGenero();
        if(itemPosition==3)
            openEditEmail();
        if(itemPosition==4)
            openEditPass();
    }
}
