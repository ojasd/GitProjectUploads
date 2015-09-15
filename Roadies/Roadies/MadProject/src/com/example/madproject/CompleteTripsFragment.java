package com.example.madproject;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.madproject.adapters.MyTripsAdapter;
import com.example.madproject.model.Trips;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CompleteTripsFragment extends Fragment{
	
	ListView completedTripsList;
	List<ParseObject> ob;
	ArrayAdapter<String> listAdapter;
	List<Trips> completedTripDetailObjects = new ArrayList<Trips>();
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.completed_trips_fragment, container, false);
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayList<String> plannedTrips = new ArrayList<String>();
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Trips");
		query.whereEqualTo("tripstarted", true);
		query.whereEqualTo("tripcompleted", true);
		query.orderByDescending("_created_at");
		try {
			ob = query.find();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		listAdapter = new MyTripsAdapter(getActivity().getBaseContext(),
				R.layout.singleitemview, plannedTrips);
		completedTripsList = (ListView) getActivity().findViewById(R.id.listView1);
		completedTripsList.setAdapter(listAdapter);
		// Retrieve object "name" from Parse.com database

		for (ParseObject Trips : ob) {
			// list1Strings22[iii]=(String) country.get("name");
			listAdapter.add((String) Trips.get("tripname"));
			completedTripDetailObjects.add(new Trips(Trips));
		}
		
		completedTripsList.setClickable(true);
		completedTripsList.setLongClickable(true);
		
		completedTripsList.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Trips trip = completedTripDetailObjects.get(position);
				Intent intent = new Intent(getActivity().getBaseContext(), TripDetailsActivity.class);
				intent.putExtra("tripObj", trip);
				startActivity(intent);
			}
		});
		
		completedTripsList.setOnItemLongClickListener( new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				ParseQuery<ParseObject> query = ParseQuery.getQuery("Trips");
				query.whereEqualTo("tripname", completedTripsList.getItemAtPosition(position).toString());
				query.findInBackground(new FindCallback<ParseObject>() {
					public void done(List<ParseObject> tripList, ParseException e) {
				        if (e == null) {
				        	for(ParseObject obj:tripList){
				        		listAdapter.remove((String)obj.get("tripname"));
				        		completedTripDetailObjects.remove(new Trips(obj));
				        		obj.deleteInBackground();
				        		listAdapter.notifyDataSetChanged();
				        	}
				        	Toast.makeText(getActivity()," Trip Deleted Succussfully", Toast.LENGTH_SHORT).show();
				        } else {
				        	Toast.makeText(getActivity(), "Error while Delete", Toast.LENGTH_SHORT).show();
				        }
				    }
				});
				return true;
			}
			
			
		});
	}
}
