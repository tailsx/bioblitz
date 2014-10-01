package com.example.bioblitz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        ListView listView = (ListView) findViewById(R.id.listView1);
        final Intent intent = new Intent(this, EventsActivity.class);
        
        listView.setOnItemClickListener(new OnItemClickListener() {
      	  @Override
      	  public void onItemClick(AdapterView<?> parent, View view,
      	    int position, long id) {
      	    Toast.makeText(getApplicationContext(),
      	      "Click ListItem Number " + position, Toast.LENGTH_LONG)
      	      .show();
      	  }
      	});
        
        String[] values = new String[] { "Humber Watershed", "Don River Watershed", "Rouge Park",
        		"Another place", "Another place2", "These are all strings", "Just want to show",
        		"That it scrolls"};
        

            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < values.length; ++i) {
              list.add(values[i]);
            }
            final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, final View view,
                  int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                intent.putExtra("event", item);
                adapter.notifyDataSetChanged();
                startActivity(intent);
              }

            });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
            List<String> objects) {
          super(context, textViewResourceId, objects);
          for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
          }
        }

        @Override
        public long getItemId(int position) {
          String item = getItem(position);
          return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
          return true;
        }

      }

}
