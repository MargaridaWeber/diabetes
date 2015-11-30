package com.example.pc.diabetesfriend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fragmentsClass.AlarmesFragment;

/**
 * Created by Mónica Francisco on 30/11/2015.
 */
public class MyAdapter extends ArrayAdapter<Item> {

    private final Context context;
    private final ArrayList<Item> itemsArrayList;

    public MyAdapter(Context context, ArrayList<Item> itemsArrayList) {

        super(context, R.layout.row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // 3. Get the two text view from the rowView
        TextView tvHoras = (TextView) rowView.findViewById(R.id.tvHoras);
        TextView tvDias = (TextView) rowView.findViewById(R.id.tvDias);
        TextView tvTipo = (TextView) rowView.findViewById(R.id.tvTipo);

        // 4. Set the text for textView
        tvHoras.setText(itemsArrayList.get(position).getHoras());
        tvDias.setText(itemsArrayList.get(position).getDias());
        tvTipo.setText(itemsArrayList.get(position).getTipo());

        // 5. retrn rowView
        return rowView;
    }
}
