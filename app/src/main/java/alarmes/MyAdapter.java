package alarmes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.diabetesfriend.R;

import java.util.ArrayList;

/**
 * Created by Mónica Francisco on 30/11/2015.
 */
public class MyAdapter extends ArrayAdapter<Alarme> {

    private final Context context;
    private final ArrayList<Alarme> itemsArrayList;

    public MyAdapter(Context context, ArrayList<Alarme> itemsArrayList) {

        super(context, R.layout.row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        TextView tvHoras = (TextView) rowView.findViewById(R.id.tvHoras);
        TextView tvDias = (TextView) rowView.findViewById(R.id.tvDias);
        TextView tvTipo = (TextView) rowView.findViewById(R.id.tvTipo);
        Switch switchModo = (Switch) rowView.findViewById(R.id.switchModo);

        tvHoras.setText(itemsArrayList.get(position).getHora());
        tvDias.setText(itemsArrayList.get(position).getDias());
        tvTipo.setText(itemsArrayList.get(position).getTipo());


        /*switchModo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                }
            }
        });*/

        return rowView;
    }
}
