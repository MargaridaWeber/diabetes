package estatisticas;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.diabetesfriend.MainActivity;
import com.example.pc.diabetesfriend.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.DiabetesFriend;
import modelo.Glicemia;
import modelo.SessionManager;
import modelo.Utilizador;

public class GlicemiaFragment extends Fragment {

    DiabetesFriend diabetes;
    SessionManager session;
    Utilizador u;
    List<Glicemia> listaSeteDias;
    TableLayout tabela;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_glicemia, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity());

        Spinner spTempo = (Spinner) v.findViewById(R.id.spTempo);
        String tempo = spTempo.getSelectedItem().toString();

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        u = diabetes.pesquisarUtilizador(user.get(SessionManager.KEY_EMAIL));

        tabela = (TableLayout) v.findViewById(R.id.tabela);
        tabela.setStretchAllColumns(true);

        if (tempo.equals("7 dias")){
            int i=0;
            for (Glicemia gli : u.getGlicemias7dias()) {
                TableRow row = addRow(gli.getData(), gli.getHora(), gli.getValor(), gli.getRefeicao(), i);
                tabela.addView(row,i);

                i++;
            }
        }

        spTempo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position==0){
                    tabela.removeAllViews();
                    int i=0;
                    for (Glicemia gli : u.getGlicemias7dias()) {
                        TableRow row = addRow(gli.getData(), gli.getHora(), gli.getValor(), gli.getRefeicao(), i);
                        tabela.addView(row,i);
                        i++;
                    }
                }
                else if(position==1){
                    tabela.removeAllViews();
                    int i=0;
                    for (Glicemia gli : u.getGlicemias14dias()) {
                        TableRow row = addRow(gli.getData(), gli.getHora(), gli.getValor(), gli.getRefeicao(), i);
                        tabela.addView(row,i);
                        i++;
                    }
                }
                else if(position==2){
                    tabela.removeAllViews();
                    int i=0;
                    for (Glicemia gli : u.getGlicemias30dias()) {
                        TableRow row = addRow(gli.getData(), gli.getHora(), gli.getValor(), gli.getRefeicao(), i);
                        tabela.addView(row,i);
                        i++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


        if(!u.getGlicemias().isEmpty()){
            //Cabeçalho da tabela
            TableLayout head = (TableLayout) v.findViewById(R.id.head);
            head.setStretchAllColumns(true);

            TableRow row = new TableRow(getActivity());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(lp);

            TextView tvData = new TextView(getActivity());
            tvData.setText("Data");
            tvData.setBackgroundResource(R.drawable.row_head);
            tvData.setWidth(135);
            tvData.setPadding(10, 10, 10, 10);
            tvData.setTextColor(Color.WHITE);
            tvData.setTypeface(null, Typeface.BOLD);
            tvData.setGravity(Gravity.CENTER);
            row.addView(tvData);

            TextView tvHora = new TextView(getActivity());
            tvHora.setText("Hora");
            tvHora.setBackgroundResource(R.drawable.row_head);
            tvHora.setWidth(80);
            tvHora.setPadding(10, 10, 10, 10);
            tvHora.setTextColor(Color.WHITE);
            tvHora.setTypeface(null, Typeface.BOLD);
            tvHora.setGravity(Gravity.CENTER);
            row.addView(tvHora);

            TextView tvValor = new TextView(getActivity());
            tvValor.setText("Valor");
            tvValor.setBackgroundResource(R.drawable.row_head);
            tvValor.setWidth(80);
            tvValor.setPadding(10, 10, 10, 10);
            tvValor.setTextColor(Color.WHITE);
            tvValor.setTypeface(null, Typeface.BOLD);
            tvValor.setGravity(Gravity.CENTER);
            row.addView(tvValor);

            TextView tvRefeicao = new TextView(getActivity());
            tvRefeicao.setText("Refeicao");
            tvRefeicao.setBackgroundResource(R.drawable.row_head);
            tvRefeicao.setWidth(150);
            tvRefeicao.setPadding(10, 10, 10, 10);
            tvRefeicao.setTextColor(Color.WHITE);
            tvRefeicao.setTypeface(null, Typeface.BOLD);
            tvRefeicao.setGravity(Gravity.CENTER);
            row.addView(tvRefeicao);

            head.addView(row);
        }

        return v;
    }

    public TableRow addRow(String data, String hora, int valor, String refeicao, int i){

        TableRow row = new TableRow(getActivity());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        row.setLayoutParams(lp);

        TextView tvData = new TextView(getActivity());
        tvData.setText(data);
        tvData.setWidth(135);
        tvData.setPadding(10, 10, 10, 10);
        tvData.setTextColor(Color.BLACK);

        String[] horas = hora.split(":");
        TextView tvHora = new TextView(getActivity());
        tvHora.setText(horas[0] + ":" + horas[1]);
        tvHora.setWidth(80);
        tvHora.setPadding(10, 10, 10, 10);
        tvHora.setTextColor(Color.BLACK);

        TextView tvValor = new TextView(getActivity());
        tvValor.setText(String.valueOf(valor));
        tvValor.setWidth(80);
        tvValor.setPadding(10, 10, 10, 10);
        tvValor.setTextColor(Color.BLACK);

        TextView tvRefeicao = new TextView(getActivity());
        tvRefeicao.setText(refeicao);
        tvRefeicao.setWidth(150);
        tvRefeicao.setPadding(10, 10, 10, 10);
        tvRefeicao.setTextColor(Color.BLACK);

        if(i%2==0){
            tvData.setBackgroundResource(R.drawable.row_border);
            tvHora.setBackgroundResource(R.drawable.row_border);
            tvValor.setBackgroundResource(R.drawable.row_border);
            tvRefeicao.setBackgroundResource(R.drawable.row_border);
        }
        else{
            tvData.setBackgroundResource(R.drawable.row_border2);
            tvHora.setBackgroundResource(R.drawable.row_border2);
            tvValor.setBackgroundResource(R.drawable.row_border2);
            tvRefeicao.setBackgroundResource(R.drawable.row_border2);
        }

        row.addView(tvData);
        row.addView(tvHora);
        row.addView(tvValor);
        row.addView(tvRefeicao);

        return row;
    }
}
