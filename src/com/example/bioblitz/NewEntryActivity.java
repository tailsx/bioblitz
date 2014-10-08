package com.example.bioblitz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

public class NewEntryActivity extends Activity {

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

}
