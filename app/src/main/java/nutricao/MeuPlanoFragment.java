package nutricao;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.diabetesfriend.R;

import java.util.HashMap;

import modelo.DiabetesFriend;
import modelo.Plano;
import modelo.SessionManager;
import modelo.Utilizador;

public class MeuPlanoFragment extends Fragment {

    DiabetesFriend diabetes;
    SessionManager session;
    TextView tvPeqAlmoco;
    TextView tvMeioManha;
    TextView tvAlmoco;
    TextView tvLanche;
    TextView tvJantar;
    TextView tvCeia;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_meu_plano, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity());

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(SessionManager.KEY_EMAIL);
        final Utilizador u = diabetes.pesquisarUtilizador(email);


        if (u.getPlano() != null) { //Se receber o plano

            tvPeqAlmoco = (TextView) v.findViewById(R.id.tvPeqAlmoco);
            tvMeioManha = (TextView) v.findViewById(R.id.tvMeioManha);
            tvAlmoco = (TextView) v.findViewById(R.id.tvAlmoco);
            tvLanche = (TextView) v.findViewById(R.id.tvLanche);
            tvJantar = (TextView) v.findViewById(R.id.tvJantar);
            tvCeia = (TextView) v.findViewById(R.id.tvCeia);

            //Coloca nas textView o plano
            tvPeqAlmoco.setText(u.getPlano().getPeqAlmoco());
            tvMeioManha.setText(u.getPlano().getMeioManha());
            tvAlmoco.setText(u.getPlano().getAlmoco());
            tvLanche.setText(u.getPlano().getLanche());
            tvJantar.setText(u.getPlano().getJantar());
            tvCeia.setText(u.getPlano().getCeia());
        }

        final Button btnEditarGuardar = (Button) v.findViewById(R.id.btnEditarGuardar);
        btnEditarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                EditText etPeqAlmoco = (EditText) v.findViewById(R.id.etPeqAlmoco);
                EditText etMeioManha = (EditText) v.findViewById(R.id.etMeioManha);
                EditText etAlmoco = (EditText) v.findViewById(R.id.etAlmoco);
                EditText etLanche = (EditText) v.findViewById(R.id.etLanche);
                EditText etJantar = (EditText) v.findViewById(R.id.etJantar);
                EditText etCeia = (EditText) v.findViewById(R.id.etCeia);

                if(btnEditarGuardar.getText().toString().equals("Editar")){

                    //Esconde as textView
                    tvPeqAlmoco.setVisibility(View.GONE);
                    tvMeioManha.setVisibility(View.GONE);
                    tvAlmoco.setVisibility(View.GONE);
                    tvLanche.setVisibility(View.GONE);
                    tvJantar.setVisibility(View.GONE);
                    tvCeia.setVisibility(View.GONE);

                    //Mostra as editText
                    etPeqAlmoco.setVisibility(View.VISIBLE);
                    etMeioManha.setVisibility(View.VISIBLE);
                    etAlmoco.setVisibility(View.VISIBLE);
                    etLanche.setVisibility(View.VISIBLE);
                    etJantar.setVisibility(View.VISIBLE);
                    etCeia.setVisibility(View.VISIBLE);

                    //Coloca nas editText o conteúdo das textView
                    etPeqAlmoco.setText(tvPeqAlmoco.getText().toString());
                    etMeioManha.setText(tvMeioManha.getText().toString());
                    etAlmoco.setText(tvAlmoco.getText().toString());
                    etLanche.setText(tvLanche.getText().toString());
                    etJantar.setText(tvJantar.getText().toString());
                    etCeia.setText(tvCeia.getText().toString());

                    //Mudar o botão para Guardar
                    btnEditarGuardar.setText("Guardar");
                }
                else
                {
                    String peqAlmoco = etPeqAlmoco.getText().toString();
                    String meioManha = etMeioManha.getText().toString();
                    String almoco = etAlmoco.getText().toString();
                    String lanche = etLanche.getText().toString();
                    String jantar = etJantar.getText().toString();
                    String ceia = etCeia.getText().toString();

                    //Validar se os campos estão preenchidos
                    if(peqAlmoco.isEmpty()){
                        etPeqAlmoco.setError("O campo não está preenchido.");
                    }

                    if(meioManha.isEmpty()){
                        etMeioManha.setError("O campo não está preenchido.");
                    }

                    if(almoco.isEmpty()){
                        etAlmoco.setError("O campo não está preenchido.");
                    }

                    if(lanche.isEmpty()){
                        etLanche.setError("O campo não está preenchido.");
                    }

                    if(jantar.isEmpty()){
                        etJantar.setError("O campo não está preenchido.");
                    }

                    if(ceia.isEmpty()){
                        etCeia.setError("O campo não está preenchido.");
                    }

                    //Se todos os campos estiveres preenchidos
                    if(!peqAlmoco.isEmpty() && !meioManha.isEmpty() && !almoco.isEmpty() && !lanche.isEmpty() && !jantar.isEmpty() && !ceia.isEmpty() )
                    {
                        //Esconde as editText
                        etPeqAlmoco.setVisibility(View.GONE);
                        etMeioManha.setVisibility(View.GONE);
                        etAlmoco.setVisibility(View.GONE);
                        etLanche.setVisibility(View.GONE);
                        etJantar.setVisibility(View.GONE);
                        etCeia.setVisibility(View.GONE);

                        //Mostra as textView
                        tvPeqAlmoco.setVisibility(View.VISIBLE);
                        tvMeioManha.setVisibility(View.VISIBLE);
                        tvAlmoco.setVisibility(View.VISIBLE);
                        tvLanche.setVisibility(View.VISIBLE);
                        tvJantar.setVisibility(View.VISIBLE);
                        tvCeia.setVisibility(View.VISIBLE);

                        //Altera
                        Plano p = new Plano(peqAlmoco, meioManha, almoco, lanche, jantar, ceia);
                        u.setPlano(p);

                        tvPeqAlmoco.setText(peqAlmoco);
                        tvMeioManha.setText(meioManha);
                        tvAlmoco.setText(almoco);
                        tvLanche.setText(lanche);
                        tvJantar.setText(jantar);
                        tvCeia.setText(ceia);

                        Toast.makeText(getActivity(), "O seu plano foi alterado com sucesso!", Toast.LENGTH_SHORT).show();

                        btnEditarGuardar.setText("Editar");
                    }
                }

            }
        });


        return v;
    }
}
