package com.example.madproject;

import java.util.List;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class MyTripsActivity extends FragmentActivity {

	ListView plannedTripsList;
	ListView completedTrips;
	List<ParseObject> ob;
	ArrayAdapter<String> listAdapter;

	// Declaring our tabs and the corresponding fragments.
	ActionBar.Tab plannedTripsTab, complTripsTab;
	Fragment plannedTripsFragmentTab = new PlannedTripsFragment();
	Fragment completeTripsFragmentTab = new CompleteTripsFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_trips);

		if(isConnectionOnline(this)){
			ActionBar actionBar = getActionBar();
	
			// Screen handling while hiding ActionBar icon.
			actionBar.setDisplayShowHomeEnabled(false);
	
			// Screen handling while hiding Actionbar title.
			actionBar.setDisplayShowTitleEnabled(false);
	
			// Creating ActionBar tabs.
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	
			// Setting custom tab icons.
			plannedTripsTab = actionBar.newTab().setText(R.string.tripTextView);
			complTripsTab = actionBar.newTab().setText(R.string.tripCmpltdTextView);
	
			// Setting tab listeners.
			plannedTripsTab.setTabListener(new TabListener(plannedTripsFragmentTab));
			complTripsTab.setTabListener(new TabListener(completeTripsFragmentTab));
	
			// Adding tabs to the ActionBar.
			actionBar.addTab(plannedTripsTab);
			actionBar.addTab(complTripsTab);
		} else {
			Toast.makeText(this, "Network Not available", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_trips, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.addNewTrip) {
			Intent intent = new Intent(MyTripsActivity.this,AddTripActivity.class);
			startActivityForResult(intent, 100);
		}
		if (id == R.id.logout) {
			ParseUser.logOut();
			Intent intent = new Intent(MyTripsActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static boolean isConnectionOnline(Context con) {
		ConnectivityManager cm = (ConnectivityManager) con
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) {
			return true;
		}
		return false;
	}
}
