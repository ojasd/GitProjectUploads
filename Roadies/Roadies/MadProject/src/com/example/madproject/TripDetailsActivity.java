package com.example.madproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madproject.model.Trips;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@SuppressWarnings("deprecation")
public class TripDetailsActivity extends Activity {

	TextView tripStart;
	TextView endTrip;

	String tripObjectId;
	String tripStartPoint;
	String tripEndPoint;
	String tripDestination1;
	String tripDestination2;
	String tripDestination3;
	String tripDestination4;
	String tripDestination5;
	Button startTripButton;
	Button inProgessTripButton;
	Button verifyTripCompleteButton;

	Trips trip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_details);

		if(isConnectionOnline(this)){
			trip = (Trips) getIntent().getExtras().get("tripObj");
			((TextView) findViewById(R.id.textViewTripName)).setText(trip
					.getTripName());
		
			// To paas Starting point to Maps
		
			tripObjectId = trip.getObjectId();
			tripStart = ((TextView) findViewById(R.id.textViewStartPointText));
			tripStart.setText(trip.getStartPoint());
			tripStartPoint = tripStart.getText().toString();
		
			// To paas End point to Maps
		
			endTrip = ((TextView) findViewById(R.id.textViewDestinationText));
			endTrip.setText(trip.getDestPoint());
			tripEndPoint = endTrip.getText().toString();
		
			((TextView) findViewById(R.id.textViewStartDateText)).setText(trip
					.getStartDate());
			((TextView) findViewById(R.id.textViewStartTimeText)).setText(trip
					.getStartTime());
			boolean tripStarted = trip.isTripStarted();
			boolean tripCompleted = trip.isTripCompleted();
		
			if (trip.getAddDestn1() != null
					&& trip.getAddDestn1().trim().length() > 0) {
				findViewById(R.id.LinearLayout7).setVisibility(View.VISIBLE);
				((TextView) findViewById(R.id.textViewDest1Text)).setText(trip
						.getAddDestn1());
				tripDestination1 = trip.getAddDestn1();
			}
		
			if (trip.getAddDestn2() != null
					&& trip.getAddDestn2().trim().length() > 0) {
				findViewById(R.id.LinearLayout8).setVisibility(View.VISIBLE);
				((TextView) findViewById(R.id.textViewDest2Text)).setText(trip
						.getAddDestn2());
				tripDestination2 = trip.getAddDestn2();
			}
		
			if (trip.getAddDestn3() != null
					&& trip.getAddDestn3().trim().length() > 0) {
				findViewById(R.id.LinearLayout9).setVisibility(View.VISIBLE);
				((TextView) findViewById(R.id.textViewDest3Text)).setText(trip
						.getAddDestn3());
				tripDestination3 = trip.getAddDestn3();
			}
		
			if (trip.getAddDestn4() != null
					&& trip.getAddDestn4().trim().length() > 0) {
				findViewById(R.id.LinearLayout10).setVisibility(View.VISIBLE);
				((TextView) findViewById(R.id.textViewDest4Text)).setText(trip
						.getAddDestn4());
				tripDestination4 = trip.getAddDestn4();
			}
		
			if (trip.getAddDestn5() != null && trip.getAddDestn5().trim().length() > 0) {
				findViewById(R.id.LinearLayout11).setVisibility(View.VISIBLE);
				((TextView) findViewById(R.id.textViewDest5Text)).setText(trip.getAddDestn5());
				tripDestination5 = trip.getAddDestn5();
			}
			
			startTripButton = ((Button) findViewById(R.id.buttonStartTrip));
			inProgessTripButton = ((Button) findViewById(R.id.buttonGoBackToTrip));
			verifyTripCompleteButton = ((Button) findViewById(R.id.buttonVerifyOnCompleteTrip));
			
			if (tripStarted && tripCompleted) {
				((TextView) findViewById(R.id.textViewCompletedTripText)).setVisibility(View.VISIBLE);
			} else if (tripStarted && !tripCompleted) {
				inProgessTripButton.setVisibility(View.VISIBLE);
				verifyTripCompleteButton.setVisibility(View.VISIBLE);
				inProgessTripButton.setOnClickListener(new View.OnClickListener() {
		
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(TripDetailsActivity.this,StartTripActivity.class);
						trip.setTripStarted(true);
						intent.putExtra("tripObj", trip);
						intent.putExtra("Trip_Start_Point", tripStartPoint);
						intent.putExtra("Trip_End_Point", tripEndPoint);
						intent.putExtra("Additional Destination 1",tripDestination1);
						intent.putExtra("Additional Destination 2",tripDestination2);
						intent.putExtra("Additional Destination 3",tripDestination3);
						intent.putExtra("Additional Destination 4",tripDestination4);
						intent.putExtra("Additional Destination 5",tripDestination5);
						startActivity(intent);
					}
				});
		
				verifyTripCompleteButton.setOnClickListener(new View.OnClickListener() {
		
					@Override
					public void onClick(View v) {
						ParseQuery<ParseObject> query = ParseQuery.getQuery("Trips");
						query.getInBackground(tripObjectId, new GetCallback<ParseObject>() {
							@Override
							public void done(ParseObject tripObj, ParseException e) {
								tripObj.put("tripstarted", true);
								tripObj.put("tripcompleted", true);
								tripObj.saveInBackground();
								trip.setTripStarted(true);
								trip.setTripCompleted(true);
								inProgessTripButton.setVisibility(View.GONE);
								verifyTripCompleteButton.setVisibility(View.GONE);
								//((TextView) findViewById(R.id.textViewCompletedTripText)).setVisibility(View.VISIBLE);
								Toast.makeText(TripDetailsActivity.this, "Trip Completed", Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(TripDetailsActivity.this,MyTripsActivity.class);
								startActivity(intent);
								finish();
							}
						});
					}
				});
			} else if (!tripStarted && !tripCompleted) {
				startTripButton.setVisibility(View.VISIBLE);
				startTripButton.setOnClickListener(new View.OnClickListener() {
		
					@Override
					public void onClick(View v) {
						ParseQuery<ParseObject> query = ParseQuery.getQuery("Trips");
						query.getInBackground(tripObjectId, new GetCallback<ParseObject>() {
							@Override
							public void done(ParseObject tripObj, ParseException e) {
								tripObj.put("tripstarted", true);
								tripObj.saveInBackground();
								startTripButton.setVisibility(View.GONE);
								inProgessTripButton.setVisibility(View.VISIBLE);
								verifyTripCompleteButton.setVisibility(View.VISIBLE);
							}
						});
		
						Intent intent = new Intent(TripDetailsActivity.this,StartTripActivity.class);
						trip.setTripStarted(true);
						intent.putExtra("tripObj", trip);
						intent.putExtra("Trip_Start_Point", tripStartPoint);
						intent.putExtra("Trip_End_Point", tripEndPoint);
						intent.putExtra("Additional Destination 1",tripDestination1);
						intent.putExtra("Additional Destination 2",tripDestination2);
						intent.putExtra("Additional Destination 3",tripDestination3);
						intent.putExtra("Additional Destination 4",tripDestination4);
						intent.putExtra("Additional Destination 5",tripDestination5);
						startActivity(intent);
		
					}
				});
			}
		}
		else {
			Toast.makeText(this, "Network Not available", Toast.LENGTH_SHORT)
			.show();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(TripDetailsActivity.this,MyTripsActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trip_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_edit_trip) {
			Intent intent=new Intent(TripDetailsActivity.this,AddTripActivity.class);
			intent.putExtra("tripObj",trip);
			startActivity(intent);
			return true;
		}
		else if(id==R.id.logout){
			ParseUser.logOut();
			Intent intent = new Intent(TripDetailsActivity.this, LoginActivity.class);
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
