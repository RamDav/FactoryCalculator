package de.ramunno.mylittlefactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import de.ramunno.mylittlefactory.factory.Item;
import de.ramunno.mylittlefactory.factory.ItemFactory;

public class MainActivity extends AppCompatActivity {

    private class ItemAdapter extends BaseAdapter{
        List<Item> Items;

        ItemAdapter(){
            Items = new ArrayList<Item>();
        }

        public void Update(List<Item> NewItems)
        {
            for (Item item : NewItems){
                Items.add(item);
            }
            notifyDataSetChanged();
        }

        public void Clear(){
            Items.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getCount()
        {
            return Items.size();
        }

        @Override
        public Object getItem(int position){
            if(position < Items.size())
                return Items.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row;
            Item item = (Item) getItem(position);
            row = inflater.inflate(R.layout.list_view_item, parent, false);
            TextView textView = (TextView) row.findViewById(R.id.list_view_items_id);

            textView.setText(Integer.toString(item.ID));

            textView = (TextView) row.findViewById(R.id.list_view_items_name);

            textView.setText(item.Name);

            return row;
        }
    }

    private ItemAdapter itemAdapter = new ItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set adapter to listview
        ListView lv = (ListView) findViewById(R.id.item_list);
        lv.setAdapter(itemAdapter);

        ItemFactory itemFactory = new ItemFactory();
        List<Item> items = itemFactory.generate(MainActivity.this);

        if(null != items)
            itemAdapter.Update(items);


    }
}
