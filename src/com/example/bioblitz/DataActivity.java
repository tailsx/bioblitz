package com.example.bioblitz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends Activity {
	
	static final int REQUEST_IMAGE_CAPTURE = 1;
	ImageView imgFavorite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		final Intent intent = new Intent(this, SpeciesEntryActivity.class);
		
		
		String[] values;
        
		Intent intentdata = getIntent();
	    String message = intentdata.getStringExtra("common");
	    if (message != null){
	    	values = new String[]{message};
	    }
	    else{
	    	values = new String[]{"None are available"};
	    }

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
            intent.putExtra("species", item);
            adapter.notifyDataSetChanged();
            startActivity(intent);
          }

        });
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data, menu);
		return true;
	}
	
	public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // TODO Auto-generated method stub
       super.onActivityResult(requestCode, resultCode, data);
       if(resultCode != RESULT_CANCELED){
	       if (requestCode == 0){
	    	   TextView textView = (TextView) findViewById(R.id.secondLine);
	    	   textView.setText("cancel	requestCode: " + requestCode + " resultCode: " + resultCode);
	       }
	       else{
		       Bitmap bp = (Bitmap) data.getExtras().get("data");
		       Intent recordNewEntryIntent = new Intent(this, NewEntryActivity.class);
		       recordNewEntryIntent.putExtra("photo", bp);
		       startActivity(recordNewEntryIntent);
		       
		       /*
		       imgFavorite.setImageBitmap(bp);
		       TextView textView = (TextView) findViewById(R.id.secondLine);
	    	   textView.setText("requestCode: " + requestCode + " resultCode: " + resultCode);
	    	   */
	       }
       }
    }
    
    public void toData (View view){
    	Intent intent = new Intent(this, DataActivity.class);
    	startActivity(intent);
    }
    
    public void toEvents (View view){
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }


}
