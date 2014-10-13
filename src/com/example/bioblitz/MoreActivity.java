package com.example.bioblitz;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
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
}
