package com.example.bioblitz;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EventsActivity extends Activity {
	
	static final int REQUEST_IMAGE_CAPTURE = 1;
	ImageView imgFavorite;
	public static ArrayList<Record> listRecords;
	static final String TAG = "EventsActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		/*
		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra("event");

	    // Create the text view
	    TextView textView = (TextView) findViewById(R.id.textfield);
	    textView.setTextSize(40);
	    textView.setText(message);
	    */
		Bundle data = getIntent().getExtras();
        if (data == null){
        	listRecords = new ArrayList<Record>();
        }
		else{
			listRecords = data.getParcelableArrayList("listRecords");
		}
        
        for (Record r : listRecords){
			Log.d(TAG,r.getCommonName());
		}
	   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.events, menu);
		return true;
	}
	
	public void toMain(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
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
