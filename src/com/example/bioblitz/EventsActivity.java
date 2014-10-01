package com.example.bioblitz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EventsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		
		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra("event");

	    // Create the text view
	    TextView textView = (TextView) findViewById(R.id.textfield);
	    textView.setTextSize(40);
	    textView.setText(message);
	   
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

}
