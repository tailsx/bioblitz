package com.example.bioblitz;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;


public class MoreActivity extends Activity {
	private final static String TAG = "MoreActivity";
	public static ArrayList<Record> listRecords;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		
		Bundle data = getIntent().getExtras();
        if (data == null){
        	listRecords = new ArrayList<Record>();
        }
		else{
			listRecords = data.getParcelableArrayList("listRecords");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.more, menu);
		return true;
	}

	public void deleteAllPictures(View view){
		File[] listFile;
		
		File file= new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Bioblitz");
		
		if (file.isDirectory())
        {
            listFile = file.listFiles();

            for (int i = 0; i < listFile.length; i++)
            {

            	if(listFile[i].delete()){
        			System.out.println(listFile[i].getName() + " is deleted!");
        		}else{
        			System.out.println("Delete operation is failed.");
        		}

            }
            
        }
	}
	
	public void listAllPictures(View view){
		File[] listFile;
		
		File file= new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Bioblitz");
		
		if (file.isDirectory())
        {
            listFile = file.listFiles();

            for (int i = 0; i < listFile.length; i++)
            {

            	Log.d(TAG, listFile[i].getName());

            }
            
        }
	}
	
	public void displayRecords(View view){
		for (Record r : listRecords){
			Log.d(TAG,r.getImagePath());
		}
	}
	
	public void toEvents (View view){
    	Intent intent = new Intent(this, MainActivity.class);
    	intent.putParcelableArrayListExtra("listRecords", listRecords);
    	startActivity(intent);
    }
	
	public void addRecord (View view){
    	Record r = new Record("af","fwef","fweef","efw","sdf");
    	listRecords.add(r);
    }
	
	public void playAudio (View view){
		MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.canary);
		mp.start();
	}
}
