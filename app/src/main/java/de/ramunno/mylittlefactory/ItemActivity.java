package de.ramunno.mylittlefactory;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import de.ramunno.mylittlefactory.adapters.RecipeAdapter;
import de.ramunno.mylittlefactory.factory.Item;
import de.ramunno.mylittlefactory.factory.Recipe;

public class ItemActivity extends AppCompatActivity {

    private RecipeAdapter recipeAdapter = new RecipeAdapter();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        // set adapter to listview
        ListView lv = (ListView) findViewById(R.id.activity_item__recipes);
        lv.setAdapter(recipeAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg){
                Intent intent = new Intent(ItemActivity.this, RecipeActivity.class);
                intent.putExtra("recipe_id", ((Recipe)recipeAdapter.getItem(position)).ID);

                startActivity(intent);
            }
        });
        Intent intent = getIntent();

        Integer item_id = intent.getIntExtra("item_id", -1);
        MyApplication App = (MyApplication) this.getApplication();
        App.Update();

        if (-1 != item_id) {
            Item item = App.Items.get(item_id);

            TextView textView = (TextView) findViewById(R.id.activity_item__name);
            textView.setText(item.Name);
            textView = (TextView) findViewById(R.id.activity_item__id);
            textView.setText(Integer.toString(item.ID));

            recipeAdapter.Update(item.recipes);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("itemDetail Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
