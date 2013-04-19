package com.androidbegin.sdimagetutorial;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {

	// Declare variables
	private Activity activity;
	private String[] data;
	private String[] name;

	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;

	public LazyAdapter(Activity a, String[] d, String[] n) {
		activity = a;
		data = d;
		name = n;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return data.length;

	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.gridview_item, null);
		// Locate the TextView in item.xml
		TextView text = (TextView) vi.findViewById(R.id.text);
		// Locate the ImageView in item.xml
		ImageView image = (ImageView) vi.findViewById(R.id.image);
		// Capture position and set to the TextView
		text.setText(name[position]);
		// Capture position and set to the ImageView
		imageLoader.DisplayImage(data[position], image);
		return vi;
	}
}