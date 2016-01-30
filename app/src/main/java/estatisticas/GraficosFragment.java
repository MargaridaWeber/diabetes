package estatisticas;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.diabetesfriend.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        Utilizador u = diabetes.pesquisarUtilizador(user.get(SessionManager.KEY_EMAIL));

        if(!u.getGlicemias().isEmpty())
        {
            GraphView graph = (GraphView) v.findViewById(R.id.graph);
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{

                    new DataPoint(0, 1),
                    new DataPoint(1, 5),
                    new DataPoint(2, 3),
                    new DataPoint(3, 2),
                    new DataPoint(4, 6)
            });

            graph.addSeries(series);
        }

        return v;
    }
}
