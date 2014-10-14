package com.example.bioblitz;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DataActivity extends FragmentActivity {
	
	private static final String TAG = "DataActivity";
	static final int REQUEST_IMAGE_CAPTURE = 1;
	ImageView imgFavorite;
	private static Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static ArrayList<Record> listRecords;
    final ArrayList<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
				
		ListView listView = (ListView) findViewById(R.id.listView1);
		final Intent intent = new Intent(this, SpeciesEntryActivity.class);

		Bundle data = getIntent().getExtras();
        if (data == null){
        	listRecords = new ArrayList<Record>();
        }
		else{
			listRecords = data.getParcelableArrayList("listRecords");
		}
		

			
		for (Record r : listRecords){
			list.add(r.getCommonName());
		}
		final StableArrayAdapter adapter = new StableArrayAdapter(this,
	            android.R.layout.simple_list_item_1, list);
	        listView.setAdapter(adapter);
	        
	        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        	Record selected;
	          @Override
	          public void onItemClick(AdapterView<?> parent, final View view,
	              int position, long id) {
	            final String item = (String) parent.getItemAtPosition(position);
	            
	            
	            for (Record r : listRecords){
	            	if (r.getCommonName() == item){
	            		selected = r;
	            	}
	            }
	            
	            intent.putExtra("species", selected);
	            intent.putParcelableArrayListExtra("listRecords", listRecords);
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
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", listRecords);
        super.onSaveInstanceState(outState);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data, menu);
		return true;
	}
	
	public void dispatchTakePictureIntent(View view) {
		Bundle test = getIntent().getExtras();
        if (test == null){
        	listRecords = new ArrayList<Record>();
        }
		else{
			listRecords = test.getParcelableArrayList("listRecords");
			Log.d(TAG, "HELLLO221323 " + listRecords.toString());
		}
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        takePictureIntent.putParcelableArrayListExtra("listRecords", listRecords);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
    
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	ArrayList<String> f = new ArrayList<String>();// list of file paths
	    	File[] listFile;
	    	//Log.d(TAG, String.valueOf(requestCode));
	    	if (requestCode == REQUEST_IMAGE_CAPTURE) {
	            if (resultCode == RESULT_OK) {
	                // Image captured and saved to fileUri specified in the Intent
	            	File file= new File(Environment.getExternalStoragePublicDirectory(
	                        Environment.DIRECTORY_PICTURES), "Bioblitz");

	                if (file.isDirectory())
	                {
	                    listFile = file.listFiles();


	                    for (int i = 0; i < listFile.length; i++)
	                    {

	                        f.add(listFile[i].getAbsolutePath());

	                    }
	                    
	/*                    Bitmap myBitmap = BitmapFactory.decodeFile(listFile[0].getAbsolutePath());
	                    
	                    ImageView myImage = (ImageView) findViewById(R.id.test);
	                    Log.d(TAG, myImage.toString());
	                    myImage.setImageBitmap(myBitmap);*/
	                    
	                    Intent intent = new Intent(this, NewEntryActivity.class);
	                    intent.putParcelableArrayListExtra("listRecords", listRecords);
	                    intent.putExtra("imagePath", listFile[0].getAbsolutePath());
	                    startActivity(intent);
	                    
	                }
	                else{
	                	Log.e(TAG, "directory lost");
	                }
	               
	            } else if (resultCode == RESULT_CANCELED) {
	                // User cancelled the image capture
	            		Log.e(TAG, "canceled");
	 	       	}
	        } 
	    	else {
	                // Image capture failed, advise user
	        }
	        
	    	
	   	
	    }
    
    public void toData (View view){
    	Intent intent = new Intent(this, DataActivity.class);
    	intent.putParcelableArrayListExtra("listRecords", listRecords);
    	startActivity(intent);
    }
    
    public void toEvents (View view){
    	Intent intent = new Intent(this, MainActivity.class);
    	intent.putParcelableArrayListExtra("listRecords", listRecords);
    	startActivity(intent);
    }
    
    public void toMore (View view){
    	Intent intent = new Intent(this, MoreActivity.class);
    	intent.putParcelableArrayListExtra("listRecords", listRecords);
    	startActivity(intent);
    }
    
    public void listRecords (View view){
    	for (Record r : listRecords){
    		System.out.println(r.getCommonName());
        }
    	System.out.println(listRecords.toString());
    }
    
    public void startSomething(View View) {
        DialogFragment newFragment = new newEntryPopUp ();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }
    
    public static class newEntryPopUp extends DialogFragment {
 
    	
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.choiceTitle)
            	   .setItems(R.array.pictureChoices, new DialogInterface.OnClickListener() {
			               public void onClick(DialogInterface dialog, int which) {
			               // The 'which' argument contains the index position
			               // of the selected item
			            	   if(which==0){

			            		   Log.d(TAG, "Value of list: " + listRecords.toString());
			            		   Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			            	        
			            	        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
			            	        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
			            	        // start the image capture Intent
			            	        getActivity().startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			            		   
			            	   }
			            	   else{

			            		   
			            		   Intent intent = new Intent(((Dialog) dialog).getContext(), NewEntryActivity.class);
			            		   intent.putParcelableArrayListExtra("listRecords", listRecords);
			            		   startActivity(intent);
			            	   }
			           }
            	   });
            
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    /** Create a file Uri for saving an image or video */
    public static Uri getOutputMediaFileUri(int type){
          return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    public static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                  Environment.DIRECTORY_PICTURES), "Bioblitz");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("Bioblitz", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
            "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
            "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


}
