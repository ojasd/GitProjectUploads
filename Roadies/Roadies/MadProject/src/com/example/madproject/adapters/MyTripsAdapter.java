package com.example.madproject.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MyTripsAdapter extends ArrayAdapter<String> {

	public MyTripsAdapter(Context context, int textViewResourceId,
			List<String> objects) {
		super(context, textViewResourceId, objects);
	}

	private int[] colors = new int[] {
			Color.parseColor("#00F0F0F0"),
			Color.parseColor("#00D2E4FC") 
		};

	/**
	 * Display rows in alternating colors
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		int colorPos = position % colors.length;
		view.setBackgroundColor(colors[colorPos]);
		return view;
	}
}