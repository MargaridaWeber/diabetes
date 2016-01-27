package dicas;

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

public class FragmentInfUteis extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs_informacoes, container, false);


        Button btntele = (Button) view.findViewById(R.id.btntele);
        btntele.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri number = Uri.parse("tel:911077432");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });

        ImageView portal = (ImageView) view.findViewById(R.id.portal_diabetes);
        portal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://www.apdp.pt/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

return view;
    }
}
