package de.ramunno.mylittlefactory.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ramunno.mylittlefactory.R;
import de.ramunno.mylittlefactory.factory.Part;
import de.ramunno.mylittlefactory.factory.Part;

/**
 * Created by MitarbeiterISZ on 20.02.2017.
 */

public class PartAdapter extends BaseAdapter{
    List<Part> Parts;

    public PartAdapter(){
        Parts = new ArrayList<Part>();
    }

    public void Update(HashMap<Integer, Part> NewParts)
    {
        for (Map.Entry<Integer, Part> entry: NewParts.entrySet()){
            Parts.add(entry.getValue());
        }
        notifyDataSetChanged();
    }
    public void Update(List<Part> NewParts)
    {
        for (Part part: NewParts){
            Parts.add(part);
        }
        notifyDataSetChanged();
    }

    public void Clear(){
        Parts.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return Parts.size();
    }

    @Override
    public Object getItem(int position){
        if(position < Parts.size())
            return Parts.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        Part Part = (Part) getItem(position);
        row = inflater.inflate(R.layout.lv__part, parent, false);
        TextView textView = (TextView) row.findViewById(R.id.lv__part__name);

        textView.setText(Part.item.Name);

        textView = (TextView) row.findViewById(R.id.lv__part__amount);

        textView.setText(Integer.toString(Part.amount));

        return row;
    }
}

