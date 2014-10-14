package com.example.bioblitz;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class NewEntryActivity extends Activity {

	static final int REQUEST_IMAGE_CAPTURE = 1;
	private static final String TAG = "NewEntryActivity";
	ImageView imageView;
	Record currentRecord;
	public static ArrayList<Record> listRecords;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_entry);
		
		Bundle data = getIntent().getExtras();
		listRecords = data.getParcelableArrayList("listRecords");
		
		imageView = (ImageView)findViewById(R.id.speciesImage);
		Intent intent = getIntent();
		Log.d(TAG,intent.toString());
		String imagePath = intent.getStringExtra("imagePath");
		if (imagePath != null){
			currentRecord = new Record(imagePath, null, null, null, null);
			
			Bitmap myBitmap = BitmapFactory.decodeFile(imagePath);
           
            Log.d(TAG, imageView.toString());
            imageView.setImageBitmap(myBitmap);
		}
		else{
			currentRecord = new Record(null, null, null, null, null);
		}
	}

	
	public void saveRecord(View view) {
		EditText commonName = (EditText) findViewById(R.id.commonNameField);
		EditText scientificName = (EditText) findViewById(R.id.scientificNameField);
		EditText date = (EditText) findViewById(R.id.dateField);
		EditText recorder = (EditText) findViewById(R.id.recorderField);
		
		currentRecord.setCommonName(commonName.getText().toString());
		currentRecord.setScientificName(scientificName.getText().toString());
		currentRecord.setDateRecorded(date.getText().toString());
		currentRecord.setRecorder(recorder.getText().toString());
		
		Intent intent = new Intent(this, DataActivity.class);
		intent.putParcelableArrayListExtra("listRecords", listRecords);
		intent.putExtra("record", currentRecord);
		startActivity(intent);
	}
	
	public void cancel(View view) {
		Intent intent = new Intent(this, DataActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_entry, menu);
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
