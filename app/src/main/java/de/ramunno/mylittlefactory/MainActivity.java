package de.ramunno.mylittlefactory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import de.ramunno.mylittlefactory.adapters.ItemAdapter;
import de.ramunno.mylittlefactory.factory.Item;

public class MainActivity extends AppCompatActivity {


    private ItemAdapter itemAdapter = new ItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set adapter to listview
        ListView lv = (ListView) findViewById(R.id.item_list);
        lv.setAdapter(itemAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg){
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                intent.putExtra("item_id", ((Item)itemAdapter.getItem(position)).ID);

                startActivity(intent);
            }
        });
        MyApplication App = (MyApplication)this.getApplication();
        App.Update();

        if (null != App.Items) {
            itemAdapter.Update(App.Items);
        }


    }
}
