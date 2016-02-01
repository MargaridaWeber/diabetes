package dicas;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pc.diabetesfriend.R;

import dicas.DicasExFisico;
import dicas.DicasHiperGlicemia;
import dicas.DicasHipoGlicemia;
import dicas.DicasNutriActivity;

public class FragmentDicas extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dicas, container, false);



        Button nutricao = (Button) view.findViewById(R.id.btnNutricao);
        nutricao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nutri = new Intent(getActivity(), DicasNutriActivity.class);
                startActivity(nutri);

            }
        });


        Button hipo = (Button) view.findViewById(R.id.btnhipo);
        hipo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent hipo = new Intent(getActivity(), DicasHipoGlicemia.class);
                startActivity(hipo);
            }
        });

        Button hiper = (Button) view.findViewById(R.id.btnhiper);
        hiper.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent hiper = new Intent(getActivity(), DicasHiperGlicemia.class);
                startActivity(hiper);
            }
        });

        Button ex = (Button) view.findViewById(R.id.btnFisico);
        ex.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ex = new Intent(getActivity(), DicasExFisico.class);
                startActivity(ex);
            }
        });

        Button in = (Button) view.findViewById(R.id.btninsulina);
        in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent insulina = new Intent(getActivity(), DicasInsulina.class);
                startActivity(insulina);
            }
        });
        return view;
    }

}
