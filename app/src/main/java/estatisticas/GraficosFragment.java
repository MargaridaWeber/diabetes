package estatisticas;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pc.diabetesfriend.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import modelo.DiabetesFriend;
import modelo.Glicemia;
import modelo.SessionManager;
import modelo.Utilizador;

public class GraficosFragment extends Fragment {

    DiabetesFriend diabetes;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_graficos, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity());

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        Utilizador u = diabetes.pesquisarUtilizador(user.get(SessionManager.KEY_EMAIL));

        DataPoint[] dataPoint = new DataPoint[u.getGlicemias().size()];

        int i=0;
        for(Glicemia gli : u.getGlicemias()){
            String[] data = gli.getData().split("/");
            dataPoint[i]= new DataPoint(Integer.parseInt(data[0]),Integer.parseInt(gli.getValor()));
            i++;
        }

        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        graph.setTitle("Glicemia");
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoint);

        graph.addSeries(series);

        return v;
    }
}
