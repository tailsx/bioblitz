package com.example.bioblitz;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SpeciesEntryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_species_entry);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.species_entry, menu);
		return true;
	}

}
