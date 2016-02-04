package estatisticas;

import android.app.Activity;
import android.app.Fragment;
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
    DataPoint[] dpGlicemia7dias;
    DataPoint[] dpPeso7dias;
    DataPoint[] dpPressao7dias;
    DataPoint[] dpGlicemia14dias;
    DataPoint[] dpPeso14dias;
    DataPoint[] dpPressao14dias;

    Utilizador u;
    int periodoTempo=8;
    GraphView graph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_graficos, container, false);

        diabetes = DiabetesFriend.getInstance();
        session = new SessionManager(getActivity());

        Spinner spTempo = (Spinner) v.findViewById(R.id.spTempo);
        String tempo = spTempo.getSelectedItem().toString();

        graph = (GraphView) v.findViewById(R.id.graph);
        graph.setTitle("Evolução");

        // Obtem dados da sessão
        HashMap<String, String> user = session.getUserDetails();
        u = diabetes.pesquisarUtilizador(user.get(SessionManager.KEY_EMAIL));

        dpGlicemia7dias = new DataPoint[u.getGlicemias7dias().size()];
        dpPeso7dias = new DataPoint[u.getPesos7dias().size()];
        dpPressao7dias = new DataPoint[u.getPesos7dias().size()];

        if(tempo.equals("7 dias")){

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
            for(PressaoArterial pa : u.getPressoes7dias()){
                dpPressao7dias[i] = new DataPoint(pa.getData(),pa.getValor());
                i++;
            }

            LineGraphSeries<DataPoint> seriesGlicemia = new LineGraphSeries<DataPoint>(dpGlicemia7dias);
            LineGraphSeries<DataPoint> seriesPeso = new LineGraphSeries<DataPoint>(dpPeso7dias);
            LineGraphSeries<DataPoint> seriesPressao = new LineGraphSeries<DataPoint>(dpPressao7dias);

            graph.addSeries(seriesGlicemia);
            graph.addSeries(seriesPeso);
            graph.addSeries(seriesPressao);

            //Muda a cor das linhas
            seriesGlicemia.setColor(Color.rgb(243, 122, 7)); //Laranja
            seriesPeso.setColor(Color.rgb(18, 111, 189)); //Azul
            seriesPressao.setColor(Color.rgb(138, 140, 129)); //Cinzento

            //Legenda
            seriesGlicemia.setTitle("Glicemia");
            seriesPeso.setTitle("Peso");
            seriesPressao.setTitle("Pressão Arterial");
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -periodoTempo);
        Date dia = cal.getTime();

        // set manual x bounds to have nice steps
        graph.getViewport().setMinX(dia.getTime());
        graph.getViewport().setMaxX(new Date().getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        //Mudar o formatao da label da data
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
                       // show currency for y values
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
                    periodoTempo = 8;

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
                    for(PressaoArterial pa : u.getPressoes7dias()){
                        dpPressao7dias[i] = new DataPoint(pa.getData(),pa.getValor());
                        i++;
                    }


                } else if (position == 1) {
                    graph.removeAllSeries();

                    periodoTempo = 15;

                    Calendar cal = GregorianCalendar.getInstance();
                    cal.setTime(new Date());
                    cal.add(Calendar.DAY_OF_YEAR, -periodoTempo);
                    Date dia = cal.getTime();

                    // set manual x bounds to have nice steps
                    graph.getViewport().setMinX(dia.getTime());
                    graph.getViewport().setMaxX(new Date().getTime());
                    graph.getViewport().setXAxisBoundsManual(true);

                    dpGlicemia14dias = new DataPoint[u.getGlicemias14dias().size()];
                    dpPeso14dias = new DataPoint[u.getPesos14dias().size()];
                    dpPressao14dias = new DataPoint[u.getPesos14dias().size()];

                    int i=0;
                    for(Glicemia gli : u.getGlicemias14dias()){
                        dpGlicemia14dias[i]= new DataPoint(gli.getData(),gli.getValor());
                        i++;
                    }

                    i=0;
                    for(Peso peso : u.getPesos14dias()){
                        dpPeso14dias[i] = new DataPoint(peso.getData(),peso.getValor());
                        i++;
                    }

                    i=0;
                    for(PressaoArterial pa :u.getPressoes14dias()){
                        dpPressao14dias[i] = new DataPoint(pa.getData(),pa.getValor());
                        i++;
                    }

                    LineGraphSeries<DataPoint> seriesGlicemia14Dias = new LineGraphSeries<DataPoint>(dpGlicemia14dias);
                    LineGraphSeries<DataPoint> seriesPeso14Dias = new LineGraphSeries<DataPoint>(dpPeso14dias);
                    LineGraphSeries<DataPoint> seriesPressao14Dias = new LineGraphSeries<DataPoint>(dpPressao14dias);

                    graph.addSeries(seriesGlicemia14Dias);
                    graph.addSeries(seriesPeso14Dias);
                    graph.addSeries(seriesPressao14Dias);

                    //Muda a cor das linhas
                    seriesGlicemia14Dias.setColor(Color.rgb(243, 122, 7)); //Laranja
                    seriesPeso14Dias.setColor(Color.rgb(18, 111, 189)); //Azul
                    seriesPressao14Dias.setColor(Color.rgb(138, 140, 129)); //Cinzento

                    //Legenda
                    seriesGlicemia14Dias.setTitle("Glicemia");
                    seriesPeso14Dias.setTitle("Peso");
                    seriesPressao14Dias.setTitle("Pressão Arterial");


                } else if (position == 2) {
                    periodoTempo = 31;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


        return v;
    }



}
