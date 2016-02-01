package estatisticas;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pc.diabetesfriend.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import modelo.DiabetesFriend;
import modelo.Glicemia;
import modelo.Peso;
import modelo.PressaoArterial;
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

        DataPoint[] dpGlicemia = new DataPoint[u.getGlicemias().size()];
        DataPoint[] dpPeso = new DataPoint[u.getPesos().size()];
        DataPoint[] dpPressao= new DataPoint[u.getPressoesArteriais().size()];

        //Glicemia
        int i=0;
        for(Glicemia gli : u.getGlicemias()){
            String[] data = gli.getData().split("/");
            dpGlicemia[i]= new DataPoint(Integer.parseInt(data[0]),Integer.parseInt(gli.getValor()));
            i++;
        }

        //Peso
        i=0;
        for(Peso peso : u.getPesos()){
            String[] data = peso.getData().split("/");
            dpPeso[i] = new DataPoint(Integer.parseInt(data[0]),peso.getValor());
            i++;
        }

        //Pressao arterial
        i=0;
        for(PressaoArterial pa : u.getPressoesArteriais()){
            String[] data = pa.getData().split("/");
            dpPressao[i] = new DataPoint(Integer.parseInt(data[0]),pa.getValor());
            i++;
        }


        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        graph.setTitle("Evolução");
        LineGraphSeries<DataPoint> seriesGlicemia = new LineGraphSeries<DataPoint>(dpGlicemia);
        LineGraphSeries<DataPoint> seriesPeso = new LineGraphSeries<DataPoint>(dpPeso);
        LineGraphSeries<DataPoint> seriesPressao = new LineGraphSeries<DataPoint>(dpPressao);

        graph.addSeries(seriesGlicemia);
        graph.addSeries(seriesPeso);
        graph.addSeries(seriesPressao);

        //Muda a cor das linhas
        seriesGlicemia.setColor(Color.rgb(243, 122, 7)); //Laranja
        seriesPeso.setColor(Color.rgb(18, 111, 189)); //Azul
        seriesPressao.setColor(Color.rgb(138, 140, 129)); //Cinzento

        //Legenda
        seriesPeso.setTitle("Peso");
        seriesGlicemia.setTitle("Glicemia");
        seriesPressao.setTitle("Pressão Arterial");

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setBackgroundColor(Color.rgb(232, 232, 232));
        graph.getLegendRenderer().setTextSize(20);

        return v;
    }
}
