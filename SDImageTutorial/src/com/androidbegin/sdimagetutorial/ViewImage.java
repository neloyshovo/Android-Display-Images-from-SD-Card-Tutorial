package com.androidbegin.sdimagetutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewImage extends Activity {
	// Declare Variable
	TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_image);
		// Retrieve data from MainActivity on item click event
		Intent i = getIntent();
		// Get a single position
		int position = i.getExtras().getInt("position");
		// Get the list of filepaths
		String[] d = i.getStringArrayExtra("filepath");
		// Get the list of filenames
		String[] n = i.getStringArrayExtra("filename");
		
		// Calls the ImageLoader Class 
		ImageLoader imageLoader = new ImageLoader(getApplication());
		
		// Locate the ImageView in view_image.xml
		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		// Load the image using ImageLoader class followed by the position
		imageLoader.DisplayImage(d[position], imageView);
		
		// Locate the TextView in view_image.xml
		text = (TextView) findViewById(R.id.imagetext);
		// Load the text into the TextView followed by the position
		text.setText(n[position]);
	}
}