package com.androidbegin.sdimagetutorial;

import java.io.File;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	// Declare variables
	private String[] FilePathStrings;
	private String[] FileNameStrings;
	private File[] listFile;
	GridView grid;
	LazyAdapter adapter;
	File file;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_main);

		// Check for SD Card
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
					.show();
		} else {
			// Locate the image folder in your SD Card
			file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "SDImageTutorial");
			// Create a new folder if no folder named SDImageTutorial exist
			file.mkdirs();
		}

		if (file.isDirectory()) {
			listFile = file.listFiles();
			// List file path for Images
			FilePathStrings = new String[listFile.length];
			// List file path for Image file names
			FileNameStrings = new String[listFile.length];

			for (int i = 0; i < listFile.length; i++) {
				// Get the path
				FilePathStrings[i] = listFile[i].getAbsolutePath();
				// Get the name
				FileNameStrings[i] = listFile[i].getName();
			}
		}

		// Locate the GridView in main.xml
		grid = (GridView) findViewById(R.id.gridview);
		// Pass results to LazyAdapter Class
		adapter = new LazyAdapter(this, FilePathStrings, FileNameStrings);
		// Binds the Adapter to the GridView
		grid.setAdapter(adapter);

		// Capture button clicks on gridview items
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Send single item click data to ViewImage Class
				Intent i = new Intent(getApplicationContext(), ViewImage.class);
				// Pass all the filepaths
				i.putExtra("filepath", FilePathStrings);
				// Pass all the filenames
				i.putExtra("filename", FileNameStrings);
				// Pass a single position
				i.putExtra("position", position);
				// Open ViewImage.java Activity
				startActivity(i);
			}

		});
	}

	// Create an Actionbar menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Menu Title
		menu.add("Clear Cache")
				.setOnMenuItemClickListener(this.ClearCacheButtonClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return super.onCreateOptionsMenu(menu);
	}

	// Capture Button Click
	OnMenuItemClickListener ClearCacheButtonClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Calls the clearCache function in ImageLoader Class
			adapter.imageLoader.clearCache();
			// Reload the GridView
			adapter.notifyDataSetChanged();
			return false;

		}
	};
}
