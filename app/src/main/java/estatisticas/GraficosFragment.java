package estatisticas;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.pc.diabetesfriend.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import modelo.DiabetesFriend;
import modelo.Glicemia;
import modelo.Peso;
import modelo.PressaoArterial;
import modelo.SessionManager;
import modelo.Utilizador;

public class GraficosFragment extends Fragment {

    DiabetesFriend diabetes;
    SessionManager session;
    Utilizador u;
    GraphView graph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_graficos, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity());

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        u = diabetes.pesquisarUtilizador(user.get(SessionManager.KEY_EMAIL));

        Spinner spTempo = (Spinner) v.findViewById(R.id.spTempo);
        String tempo = spTempo.getSelectedItem().toString();

        graph = (GraphView) v.findViewById(R.id.graph);
        graph.setTitle("Evolução");


        if(tempo.equals("7 dias")){
            criarGrafico7dias();
            definirDiasEscala(8);
        }


        //Mudar o formato da label da data
        graph.getGridLabelRenderer().setLabelFormatter(new
            DateAsXAxisLabelFormatter(getActivity()) {

               @Override
               public String formatLabel(double value, boolean isValueX) {
                   if (isValueX) {
                       Calendar c = Calendar.getInstance();

                       c.setTimeInMillis(((long) value)); //Set time in milliseconds
                       int mYear = c.get(Calendar.YEAR);
                       int mMonth = c.get(Calendar.MONTH);
                       int mDay = c.get(Calendar.DAY_OF_MONTH);

                       SimpleDateFormat month_date = new SimpleDateFormat("MMM");
                       String month_name = month_date.format(c.getTime());

                       return mDay + " " + (month_name);
                   } else {
                       return super.formatLabel(value, isValueX);
                   }
               }
            });

        graph.getGridLabelRenderer().setNumHorizontalLabels(4);


        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setBackgroundColor(Color.rgb(232, 232, 232));
        graph.getLegendRenderer().setTextSize(20);


        spTempo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    graph.removeAllSeries();
                    criarGrafico7dias();
                    definirDiasEscala(8);

                    graph.setDrawingCacheEnabled(true);
                    Bitmap bm = graph.getDrawingCache();

                } else if (position == 1) {
                    graph.removeAllSeries();
                    criarGrafico14dias();
                    definirDiasEscala(15);

                } else if (position == 2) {
                    graph.removeAllSeries();
                    criarGrafico30dias();
                    definirDiasEscala(31);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


        return v;
    }

    public void criarGrafico7dias(){

        //Cria vectores com as coordenadas
        DataPoint[] dpGlicemia7dias = new DataPoint[u.getGlicemias7dias().size()];
        DataPoint[] dpPeso7dias = new DataPoint[u.getPesos7dias().size()];
        DataPoint[] dpSistolica7dias = new DataPoint[u.getPressoes7dias().size()];
        DataPoint[] dpDiastolica7dias = new DataPoint[u.getPressoes7dias().size()];

        int i=0;
        for(Glicemia gli : u.getGlicemias7dias()){
            dpGlicemia7dias[i]= new DataPoint(gli.getData(),gli.getValor());
            i++;
        }

        i=0;
        for(Peso peso : u.getPesos7dias()){
            dpPeso7dias[i] = new DataPoint(peso.getData(),peso.getValor());
            i++;
        }

        i=0;
        for(PressaoArterial p : u.getPressoes7dias()){
            dpSistolica7dias[i] = new DataPoint(p.getData(),p.getSistolica());
            i++;
        }

        i=0;
        for(PressaoArterial p : u.getPressoes7dias()){
            dpDiastolica7dias[i] = new DataPoint(p.getData(),p.getDiastolica());
            i++;
        }

        //Cria as linhas consoante as coordenadas do vector
        LineGraphSeries<DataPoint> seriesGlicemia = new LineGraphSeries<DataPoint>(dpGlicemia7dias);
        //PointsGraphSeries<DataPoint> series = new PointsGraphSeries<DataPoint>(dpGlicemia7dias);
        //graph.addSeries(series);
        //series.setShape(PointsGraphSeries.Shape.POINT);

        LineGraphSeries<DataPoint> seriesPeso = new LineGraphSeries<DataPoint>(dpPeso7dias);
        LineGraphSeries<DataPoint> seriesDiastolica = new LineGraphSeries<DataPoint>(dpSistolica7dias);
        LineGraphSeries<DataPoint> seriesSistolica = new LineGraphSeries<DataPoint>(dpDiastolica7dias);

        //Adiciona as linhas aos gráficos
        graph.addSeries(seriesGlicemia);
        graph.addSeries(seriesPeso);
        graph.addSeries(seriesSistolica);
        graph.addSeries(seriesDiastolica);

        //Muda a cor das linhas
        seriesGlicemia.setColor(Color.rgb(243, 122, 7)); //Laranja
        seriesPeso.setColor(Color.rgb(18, 111, 189)); //Azul
        seriesSistolica.setColor(Color.rgb(138, 140, 129)); //Cinzento
        seriesDiastolica.setColor(Color.rgb(138, 140, 129)); //Cinzento

        //Legenda
        seriesGlicemia.setTitle("Glicemia");
        seriesPeso.setTitle("Peso");
        seriesSistolica.setTitle("Sistólica");
        seriesDiastolica.setTitle("Diastólica");

    }

    public void criarGrafico14dias(){

        //Cria vectores com as coordenadas
        DataPoint[] dpGlicemia14dias = new DataPoint[u.getGlicemias14dias().size()];
        DataPoint[] dpPeso14dias = new DataPoint[u.getPesos14dias().size()];
        DataPoint[] dpSistolica14dias = new DataPoint[u.getPressoes14dias().size()];
        DataPoint[] dpDiastolica14dias = new DataPoint[u.getPressoes14dias().size()];

        //Cria as linhas consoante as coordenadas do vector
        int i=0;
        for(Glicemia gli : u.getGlicemias14dias()){
            dpGlicemia14dias[i]= new DataPoint(gli.getData(),gli.getValor());
            i++;
        }

        i =0;
        for(Peso peso : u.getPesos14dias()){
            dpPeso14dias[i] = new DataPoint(peso.getData(),peso.getValor());
            i++;
        }

        i=0;
        for(PressaoArterial p : u.getPressoes14dias()){
            dpSistolica14dias[i] = new DataPoint(p.getData(),p.getSistolica());
            i++;
        }

        i=0;
        for(PressaoArterial p : u.getPressoes14dias()){
            dpDiastolica14dias[i] = new DataPoint(p.getData(),p.getDiastolica());
            i++;
        }


        //Adiciona as linhas aos gráficos
        LineGraphSeries<DataPoint> seriesGlicemia14 = new LineGraphSeries<DataPoint>(dpGlicemia14dias);
        LineGraphSeries<DataPoint> seriesPeso14 = new LineGraphSeries<DataPoint>(dpPeso14dias);
        LineGraphSeries<DataPoint> seriesDiastolica14 = new LineGraphSeries<DataPoint>(dpSistolica14dias);
        LineGraphSeries<DataPoint> seriesSistolica14 = new LineGraphSeries<DataPoint>(dpDiastolica14dias);

        graph.addSeries(seriesGlicemia14);
        graph.addSeries(seriesPeso14);
        graph.addSeries(seriesSistolica14);
        graph.addSeries(seriesDiastolica14);

        //Muda a cor das linhas
        seriesGlicemia14.setColor(Color.rgb(243, 122, 7)); //Laranja
        seriesPeso14.setColor(Color.rgb(18, 111, 189)); //Azul
        seriesSistolica14.setColor(Color.rgb(138, 140, 129)); //Cinzento
        seriesDiastolica14.setColor(Color.rgb(138, 140, 129)); //Cinzento

        //Legenda
        seriesGlicemia14.setTitle("Glicemia");
        seriesPeso14.setTitle("Peso");
        seriesSistolica14.setTitle("Sistólica");
        seriesDiastolica14.setTitle("Diastólica");

    }

    public void criarGrafico30dias(){

        //Cria vectores com as coordenadas
        DataPoint[] dpGlicemia30dias = new DataPoint[u.getGlicemias30dias().size()];
        DataPoint[] dpPeso30dias = new DataPoint[u.getPesos30dias().size()];
        DataPoint[] dpSistolica30dias = new DataPoint[u.getPressoes30dias().size()];
        DataPoint[] dpDiastolica30dias = new DataPoint[u.getPressoes30dias().size()];

        //Cria as linhas consoante as coordenadas do vector
        int i=0;
        for(Glicemia gli : u.getGlicemias30dias()){
            dpGlicemia30dias[i]= new DataPoint(gli.getData(),gli.getValor());
            i++;
        }

        i=0;
        for(Peso peso : u.getPesos30dias()){
            dpPeso30dias[i] = new DataPoint(peso.getData(),peso.getValor());
            i++;
        }

        i=0;
        for(PressaoArterial p : u.getPressoes30dias()){
            dpSistolica30dias[i] = new DataPoint(p.getData(),p.getSistolica());
            i++;
        }

        i=0;
        for(PressaoArterial p : u.getPressoes30dias()){
            dpDiastolica30dias[i] = new DataPoint(p.getData(),p.getDiastolica());
            i++;
        }


        //Adiciona as linhas aos gráficos
        LineGraphSeries<DataPoint> seriesGlicemia30 = new LineGraphSeries<DataPoint>(dpGlicemia30dias);
        LineGraphSeries<DataPoint> seriesPeso30 = new LineGraphSeries<DataPoint>(dpPeso30dias);
        LineGraphSeries<DataPoint> seriesDiastolica30 = new LineGraphSeries<DataPoint>(dpSistolica30dias);
        LineGraphSeries<DataPoint> seriesSistolica30 = new LineGraphSeries<DataPoint>(dpDiastolica30dias);

        graph.addSeries(seriesGlicemia30);
        graph.addSeries(seriesPeso30);
        graph.addSeries(seriesSistolica30);
        graph.addSeries(seriesDiastolica30);

        //Muda a cor das linhas
        seriesGlicemia30.setColor(Color.rgb(243, 122, 7)); //Laranja
        seriesPeso30.setColor(Color.rgb(18, 111, 189)); //Azul
        seriesSistolica30.setColor(Color.rgb(138, 140, 129)); //Cinzento
        seriesDiastolica30.setColor(Color.rgb(138, 140, 129)); //Cinzento

        //Legenda
        seriesGlicemia30.setTitle("Glicemia");
        seriesPeso30.setTitle("Peso");
        seriesSistolica30.setTitle("Sistólica");
        seriesDiastolica30.setTitle("Diastólica");

    }

    public void definirDiasEscala(int periodoTempo){
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -periodoTempo);
        Date dia = cal.getTime();

        graph.getViewport().setMinX(dia.getTime());
        graph.getViewport().setMaxX(new Date().getTime());
        graph.getViewport().setXAxisBoundsManual(true);
    }
}
