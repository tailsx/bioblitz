package com.example.bioblitz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class NewEntryActivity extends Activity {

	static final int REQUEST_IMAGE_CAPTURE = 1;
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_entry);
		
		imageView = (ImageView)findViewById(R.id.speciesImage);
		Intent intent = getIntent();
		Bitmap image = (Bitmap) intent.getParcelableExtra("photo");
		
		imageView.setImageBitmap(image);
	}

	
	public void saveRecord(View view) {
		EditText commonName = (EditText) findViewById(R.id.commonNameField);
		Intent intent = new Intent(this, DataActivity.class);
		intent.putExtra("common", commonName.getText().toString());
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
