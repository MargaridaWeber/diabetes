package nutricao;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pc.diabetesfriend.R;

public class PlanoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_plano, container, false);

        //Tab Plano alimentar
        ImageView imgPequenoAlmoco = (ImageView) v.findViewById(R.id.imgPequenoAlmoco);
        imgPequenoAlmoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent pequenoAlmoco = new Intent(getActivity(), PequenoAlmocoActivity.class);
                startActivity(pequenoAlmoco);
            }
        });

        ImageView imgMeioManha = (ImageView) v.findViewById(R.id.imgMeioManha);
        imgMeioManha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent meioManha = new Intent(getActivity(), MeioManhaActivity.class);
                startActivity(meioManha);
            }
        });

        ImageView imgAlmoco = (ImageView) v.findViewById(R.id.imgAlmoco);
        imgAlmoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent almoco = new Intent(getActivity(), AlmocoActivity.class);
                startActivity(almoco);
            }
        });

        ImageView imgLanche = (ImageView) v.findViewById(R.id.imgLanche);
        imgLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent lanche = new Intent(getActivity(), LancheActivity.class);
                startActivity(lanche);
            }
        });

        ImageView imgJantar = (ImageView) v.findViewById(R.id.imgJantar);
        imgJantar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent jantar = new Intent(getActivity(), JantarActivity.class);
                startActivity(jantar);
            }
        });

        ImageView imgCeia = (ImageView) v.findViewById(R.id.imgCeia);
        imgCeia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent ceia = new Intent(getActivity(), CeiaActivity.class);
                startActivity(ceia);
            }
        });

        return v;
    }
}
