package com.example.madproject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.madproject.model.Trips;
import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class AddTripActivity extends Activity{

	EditText tripNameText;
	EditText startPointText;
	EditText endPointText;
	Button addTripStartDate;
	Button addTripStartTime;
	EditText addDestnText1;
	EditText addDestnText2;
	EditText addDestnText3;
	EditText addDestnText4;
	EditText addDestnText5;
	ImageView ivAddView;
	Button btnSave;
	Button btnClear;
	boolean dateFocus = false;
	boolean timeFocus = false;

	final Calendar myCalendar = Calendar.getInstance();

	private static final String LOG_TAG = "Google Places Autocomplete";
	private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
	private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
	private static final String OUT_JSON = "/json";
	private static final String API_KEY = "AIzaSyBRcYcPjOCb18IAvFsk0FK7Sfjtiv0rddg";

	Trips trip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_trip);

		if (isConnectionOnline(this)) {

			findViewById(R.id.editTextDestination).setOnFocusChangeListener(
					new View.OnFocusChangeListener() {

						@Override
						public void onFocusChange(View v, boolean hasFocus) {
							if (hasFocus) {
								ScrollView sv = (ScrollView) findViewById(R.id.scrollView1);
								findViewById(R.id.scrollView1).scrollBy(0,
										sv.getBottom() + 400);
							}

						}
					});

			final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {

					myCalendar.set(Calendar.YEAR, year);
					myCalendar.set(Calendar.MONTH, monthOfYear);
					myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
					updateDateLabel();
				}

			};

			final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
					myCalendar.set(Calendar.MINUTE, minute);
					updateTimeLabel();
				}
			};

			// Finding all the edit texts, image view and buttons
			
			AutoCompleteTextView autoCompView1 = (AutoCompleteTextView) findViewById(R.id.editTextStartngPoint);
			autoCompView1.setAdapter(new GooglePlacesAutocompleteAdapter(this,
					R.layout.listview_item));

			AutoCompleteTextView autoCompView2 = (AutoCompleteTextView) findViewById(R.id.editTextDestination);
			autoCompView2.setAdapter(new GooglePlacesAutocompleteAdapter(this,
					R.layout.listview_item));

			tripNameText = (EditText) findViewById(R.id.editTextTripName);
			startPointText = (EditText) autoCompView1;// findViewById(R.id.editTextStartngPoint);
			endPointText = (EditText) autoCompView2;// findViewById(R.id.editTextDestination);
			addTripStartDate = (Button) findViewById(R.id.editTripStartDate);
			addTripStartDate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					DatePickerDialog datePick = new DatePickerDialog(
							AddTripActivity.this, date, myCalendar
									.get(Calendar.YEAR), myCalendar
									.get(Calendar.MONTH), myCalendar
									.get(Calendar.DAY_OF_MONTH));
					datePick.setTitle("Select Date");
					datePick.getDatePicker().setMinDate(
							System.currentTimeMillis() - 1000);
					datePick.show();
				}
			});

			addTripStartTime = (Button) findViewById(R.id.editTripStartTime);
			addTripStartTime.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					TimePickerDialog mTimePicker;
					mTimePicker = new TimePickerDialog(AddTripActivity.this,
							time, myCalendar.get(Calendar.HOUR_OF_DAY),
							myCalendar.get(Calendar.MINUTE), true);// Yes 24
																	// hour time
					mTimePicker.setTitle("Select Time");
					mTimePicker.show();
				}
			});


			AutoCompleteTextView autoCompView3 = (AutoCompleteTextView) findViewById(R.id.etNewDest1);
			autoCompView3.setAdapter(new GooglePlacesAutocompleteAdapter(this,
					R.layout.listview_item));

			AutoCompleteTextView autoCompView4 = (AutoCompleteTextView) findViewById(R.id.etAddDest2);
			autoCompView4.setAdapter(new GooglePlacesAutocompleteAdapter(this,
					R.layout.listview_item));

			AutoCompleteTextView autoCompView5 = (AutoCompleteTextView) findViewById(R.id.etAddDest3);
			autoCompView5.setAdapter(new GooglePlacesAutocompleteAdapter(this,
					R.layout.listview_item));

			AutoCompleteTextView autoCompView6 = (AutoCompleteTextView) findViewById(R.id.etAddDest4);
			autoCompView6.setAdapter(new GooglePlacesAutocompleteAdapter(this,
					R.layout.listview_item));

			AutoCompleteTextView autoCompView7 = (AutoCompleteTextView) findViewById(R.id.etAddDest5);
			autoCompView7.setAdapter(new GooglePlacesAutocompleteAdapter(this,
					R.layout.listview_item));

			addDestnText1 = (EditText) autoCompView3;
			addDestnText2 = (EditText) autoCompView4;
			addDestnText3 = (EditText) autoCompView5;
			addDestnText4 = (EditText) autoCompView6;
			addDestnText5 = (EditText) autoCompView7;

			ivAddView = (ImageView) findViewById(R.id.ivAdd);

			btnSave = (Button) findViewById(R.id.btnSaveTrip);
			btnClear = (Button) findViewById(R.id.btnClearTrip);

			addDestnText1.setVisibility(View.INVISIBLE);
			addDestnText2.setVisibility(View.INVISIBLE);
			addDestnText3.setVisibility(View.INVISIBLE);
			addDestnText4.setVisibility(View.INVISIBLE);
			addDestnText5.setVisibility(View.INVISIBLE);

			// boolean addTripImage = false;

			// When the Save Button is clicked

			btnSave.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String tripName = tripNameText.getText().toString();
					String startPoint = startPointText.getText().toString();
					String endPoint = endPointText.getText().toString();
					String startDate = addTripStartDate.getText().toString();
					String startTime = addTripStartTime.getText().toString();
					String addDestn1 = addDestnText1.getText().toString();
					String addDestn2 = addDestnText2.getText().toString();
					String addDestn3 = addDestnText3.getText().toString();
					String addDestn4 = addDestnText4.getText().toString();
					String addDestn5 = addDestnText5.getText().toString();

					Boolean tripDetails = true;

					if (tripName.isEmpty()) {
						Toast.makeText(AddTripActivity.this,
								"Please Enter Trip Name", Toast.LENGTH_LONG)
								.show();
						tripDetails = false;
					}

					else if (startPoint.isEmpty()) {
						Toast.makeText(AddTripActivity.this,
								"Please Enter Starting Point Name",
								Toast.LENGTH_LONG).show();
						tripDetails = false;
					}

					else if (endPoint.isEmpty()) {
						Toast.makeText(AddTripActivity.this,
								"Please Enter Destination Point Name",
								Toast.LENGTH_LONG).show();
						tripDetails = false;
					} else if (startDate.isEmpty()) {
						Toast.makeText(AddTripActivity.this,
								"Please Enter Trip Start Date",
								Toast.LENGTH_LONG).show();
						tripDetails = false;
					} else if (startTime.isEmpty()) {
						Toast.makeText(AddTripActivity.this,
								"Please Enter Trip Start Time",
								Toast.LENGTH_LONG).show();
						tripDetails = false;
					}

					if (tripDetails) {

						// Intent intent = new
						// Intent(AddTripActivity.this,StartTripActivity.class);

						// startActivity(intent);
						saveTrip(tripName, startPoint, endPoint, startDate,
								startTime, addDestn1, addDestn2, addDestn3,
								addDestn4, addDestn5);
						Intent i = new Intent(AddTripActivity.this,
								MyTripsActivity.class);
						startActivity(i);
						finish();
					}
				}
			});

			/*
			 * When the image button is clicked, it should display another field
			 * to add destination.
			 */

			/* HAVE TO WORK ON THIS */

			ivAddView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					showFields();
				}
			});

			/*
			 * When the Clear Button is clicked. It will clear the whole form
			 */

			btnClear.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					resetFields();
				}

				/*
				 * Function to Reset all the fields of the View
				 */

				private void resetFields() {
					EditText temp;
					Button temp1;
					final int[] textID = new int[] { R.id.editTextTripName,
							R.id.editTextStartngPoint,
							R.id.editTextDestination, R.id.etNewDest1,
							R.id.etAddDest2, R.id.etAddDest3, R.id.etAddDest4,
							R.id.etAddDest5 };

					final int[] buttonID = new int[] { R.id.editTripStartDate,
							R.id.editTripStartTime };
					for (int i = 0; i < textID.length; i++) {
						temp = (EditText) findViewById(textID[i]);
						temp.setText(null);
					}
					for (int i = 0; i < buttonID.length; i++) {
						temp1 = (Button) findViewById(buttonID[i]);
						temp1.setText(null);
					}
				}
			});

			if(getIntent()!=null && getIntent().getExtras()!=null && getIntent().getExtras().get("tripObj")!=null){
				Trips trip = (Trips)getIntent().getExtras().get("tripObj");
				tripNameText.setText(trip.getTripName());
				startPointText.setText(trip.getStartPoint());
				endPointText.setText(trip.getDestPoint());
				addTripStartDate.setText(trip.getStartDate());
				addTripStartTime.setText(trip.getStartTime());
				
				if(trip.getAddDestn1()!=null && !trip.getAddDestn1().trim().equals("")){
					addDestnText1.setVisibility(View.VISIBLE);
					addDestnText1.setText(trip.getAddDestn1());
				}
				
				if(trip.getAddDestn2()!=null && !trip.getAddDestn2().trim().equals("")){
					addDestnText2.setVisibility(View.VISIBLE);
					addDestnText2.setText(trip.getAddDestn2());
				}
				
				if(trip.getAddDestn3()!=null && !trip.getAddDestn3().trim().equals("")){
					addDestnText3.setVisibility(View.VISIBLE);
					addDestnText3.setText(trip.getAddDestn3());
				}
				
				if(trip.getAddDestn4()!=null && !trip.getAddDestn4().trim().equals("")){
					addDestnText4.setVisibility(View.VISIBLE);
					addDestnText4.setText(trip.getAddDestn4());
				}
				
				if(trip.getAddDestn5()!=null && !trip.getAddDestn5().trim().equals("")){
					addDestnText5.setVisibility(View.VISIBLE);
					addDestnText5.setText(trip.getAddDestn5());
				}
			}
			
			
		} else {
			Toast.makeText(this, "Network Not available", Toast.LENGTH_SHORT)
					.show();
		}
	}

	/*
	 * 
	 * Function to Show fields when the imageview is clicked
	 */

	// HAVE TO WORK ON THIS

	public void saveTrip(String tripName, String startPoint, String endPoint,
			String startDate, String startTime, String addDestn1,
			String addDestn2, String addDestn3, String addDestn4,
			String addDestn5) {
		if(getIntent()!=null && getIntent().getExtras()!=null && getIntent().getExtras().get("tripObj")!=null){
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Trips");
			final Trips tripObj = (Trips)getIntent().getExtras().get("tripObj");
			// Retrieve the object by id
			query.getInBackground(tripObj.getObjectId(), new GetCallback<ParseObject>() {
			@Override
			public void done(ParseObject userTrip, ParseException e) {
				if (e == null) {
					userTrip.put("tripname", tripNameText.getText().toString());
					userTrip.put("startpoint", startPointText.getText().toString());
					userTrip.put("destination", endPointText.getText().toString());
					userTrip.put("startDate", addTripStartDate.getText().toString());
					userTrip.put("startTime", addTripStartTime.getText().toString());
					userTrip.put("addDestn1", addDestnText1.getText().toString());
					userTrip.put("addDestn2", addDestnText2.getText().toString());
					userTrip.put("addDestn3", addDestnText3.getText().toString());
					userTrip.put("addDestn4", addDestnText4.getText().toString());
					userTrip.put("addDestn5", addDestnText5.getText().toString());
					userTrip.put("tripstarted", tripObj.isTripStarted());
					userTrip.put("tripcompleted",tripObj.isTripCompleted());
					ParseACL pa = new ParseACL(ParseUser.getCurrentUser());
					pa.setPublicReadAccess(false);
					userTrip.setACL(pa);
					userTrip.saveInBackground();
					Toast.makeText(getApplicationContext(),
							"Trip Updated Succesfully", Toast.LENGTH_LONG)
							.show();
				}
			}
			});
		}
		else{
			ParseObject userTrips = new ParseObject("Trips");
			userTrips.put("tripname", tripName);
			userTrips.put("startpoint", startPoint);
			userTrips.put("destination", endPoint);
			userTrips.put("startDate", startDate);
			userTrips.put("startTime", startTime);
			userTrips.put("addDestn1", addDestn1);
			userTrips.put("addDestn2", addDestn2);
			userTrips.put("addDestn3", addDestn3);
			userTrips.put("addDestn4", addDestn4);
			userTrips.put("addDestn5", addDestn5);
			userTrips.put("tripstarted", false);
			userTrips.put("tripcompleted", false);
			ParseACL pa = new ParseACL(ParseUser.getCurrentUser());
			pa.setPublicReadAccess(false);
			userTrips.setACL(pa);
			userTrips.saveInBackground();
			Toast.makeText(getApplicationContext(),
					"New Trip Added Succesfully", Toast.LENGTH_LONG)
					.show();
		}
	}

	private void updateDateLabel() {
		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		addTripStartDate.setText(sdf.format(myCalendar.getTime()));

	}

	private void updateTimeLabel() {
		String myFormat = "HH:mm"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		addTripStartTime.setText(sdf.format(myCalendar.getTime()));

	}

	private void showFields() {
		EditText showFieldTemp;

		final int[] addDestinations = new int[] { R.id.etNewDest1,
				R.id.etAddDest2, R.id.etAddDest3, R.id.etAddDest4,
				R.id.etAddDest5 };

		boolean singleDone = false;
		for (int i = 0; i < addDestinations.length; i++) {
			showFieldTemp = (EditText) findViewById(addDestinations[i]);
			if (!singleDone && showFieldTemp.getVisibility() != View.VISIBLE) {
				showFieldTemp.setVisibility(View.VISIBLE);
				singleDone = true;
			}

		}
		singleDone = false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_trip, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.logout) {
			ParseUser.logOut();
			Intent intent = new Intent(AddTripActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class LocationViewHolderItem{
		TextView location;
		ImageView poweredByGoogle;
	}
	
	class GooglePlacesAutocompleteAdapter extends ArrayAdapter<String> implements
			Filterable {
		private ArrayList<String> resultList;

		private Context mContext;
		private int mResourceId;

		public GooglePlacesAutocompleteAdapter(Context context,
				int textViewResourceId) {
			super(context, textViewResourceId);
			mContext = context;
			mResourceId = textViewResourceId;
		}

		@Override
		public int getCount() {
			return resultList.size();
		}

		@Override
		public String getItem(int index) {
			return (String) resultList.get(index);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LocationViewHolderItem viewHolder;
			 
	        if (convertView == null) {
	 
	            // inflate the layout
	            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
	            convertView = inflater.inflate(mResourceId, parent, false);
	 
	            // well set up the ViewHolder
	            viewHolder = new LocationViewHolderItem();
	            //ID of the TextView in the android.R.layout.simple_list_item_1
	            viewHolder.location = (TextView) convertView.findViewById(R.id.activity_register_tv_location_item);
	            viewHolder.poweredByGoogle = (ImageView) convertView.findViewById(R.id.activity_register_iv_powered_by_google);
	            // store the holder with the view.
	            convertView.setTag(viewHolder);
	            
	        } else {
	            // we've just avoided calling findViewById() on resource every time
	            // just use the viewHolder
	            viewHolder = (LocationViewHolderItem) convertView.getTag();
	        }
	 
	        String location = resultList.get(position);
	 
	        if (location != null) {
	            if (location.equals("powered by Google")) {
	                viewHolder.location.setVisibility(View.GONE);
	                viewHolder.poweredByGoogle.setVisibility(View.VISIBLE);
	            } else {
	                viewHolder.poweredByGoogle.setVisibility(View.GONE);
	                viewHolder.location.setVisibility(View.VISIBLE);
	                viewHolder.location.setText(location);
	                
	                viewHolder.location.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							((TextView)getCurrentFocus()).setText(((TextView)v).getText());
							getCurrentFocus().clearFocus();
						}
					});
	            }
	        }
	 
	        return convertView;
		}

		

		@Override
		public Filter getFilter() {
			Filter filter = new Filter() {
				@Override
				protected FilterResults performFiltering(CharSequence constraint) {
					FilterResults filterResults = new FilterResults();
					if (constraint != null) {
						// Retrieve the autocomplete results.
						resultList = (ArrayList<String>) autocomplete(constraint
								.toString());

						// Powered by Google
						if(resultList.size()>0){
							resultList.add("powered by Google");
						}

						// Assign the data to the FilterResults
						filterResults.values = resultList;
						filterResults.count = resultList.size();
					}
					return filterResults;
				}

				@Override
				protected void publishResults(CharSequence constraint,
						FilterResults results) {
					if (results != null && results.count > 0) {
						notifyDataSetChanged();
					} else {
						notifyDataSetInvalidated();
					}
				}
			};
			return filter;
		}
	}

	public static ArrayList<String> autocomplete(String input) {
		ArrayList<String> resultList = null;

		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE
					+ TYPE_AUTOCOMPLETE + OUT_JSON);
			sb.append("?key=" + API_KEY);
			/* sb.append("&components=country:all"); */
			sb.append("&input=" + URLEncoder.encode(input, "utf8"));
			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			// Load the results into a StringBuilder
			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
		} catch (MalformedURLException e) {
			Log.e(LOG_TAG, "Error processing Places API URL", e);
			return null;
		} catch (IOException e) {
			Log.e(LOG_TAG, "Error connecting to Places API", e);
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		try {
			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

			// Extract the Place descriptions from the results
			resultList = new ArrayList<String>(predsJsonArray.length());
			for (int i = 0; i < predsJsonArray.length(); i++) {
				JSONArray predsTersmJsonArray = predsJsonArray.getJSONObject(i)
						.getJSONArray("terms");
				StringBuffer str = new StringBuffer();
				for (int j = 0; j < predsTersmJsonArray.length(); j++) {

					String val = predsTersmJsonArray.getJSONObject(j)
							.getString("value");
					if (val != null && !val.trim().equals("") && !val.trim().equalsIgnoreCase("us") && !val.trim().equalsIgnoreCase("United States")) {
						str.append(val);
						str.append(", ");
					}
					val = "";
				}
				if (str != null && str.toString().trim().length() > 0) {
					str.setLength(str.toString().trim().length()-1);
					resultList.add(str.toString().trim());
				}
				str.setLength(0);

			}
		} catch (JSONException e) {
			Log.e(LOG_TAG, "Cannot process JSON results", e);
		}
		return resultList;
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
