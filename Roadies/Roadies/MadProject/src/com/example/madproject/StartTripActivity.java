package com.example.madproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madproject.Parser.DirectionsJSONParser;
import com.example.madproject.model.Trips;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.ParseUser;

public class StartTripActivity extends FragmentActivity {

	GoogleMap map;
	ArrayList<LatLng> markerPoints;
	TextView tvDistanceDuration;
	MapFragment mapFragment;

	Trips trip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		if(isConnectionOnline(this)){
			Bundle bundle = new Bundle();
			bundle = getIntent().getExtras();
			trip = (Trips)getIntent().getExtras().get("tripObj");
			String startPoint = getIntent().getExtras().getString("Trip_Start_Point");
			String endPoint = getIntent().getExtras().getString("Trip_End_Point");
			
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
					.getMap();
			map.setMyLocationEnabled(true);
			map.getUiSettings().setZoomControlsEnabled(true);
	
			this.tvDistanceDuration = (TextView) this
					.findViewById(R.id.tv_distance_time);
			// Initializing
			this.markerPoints = new ArrayList<LatLng>();

			// Setting onclick event listener for the map
			//this.map.setOnMapClickListener(new OnMapClickListener() {
				//@Override
				//public void onMapClick(LatLng point) {
				// Already two locations
				if (StartTripActivity.this.markerPoints.size() > 1) {
					StartTripActivity.this.markerPoints.clear();
					StartTripActivity.this.map.clear();
				}

				String url1 = StartTripActivity.this
						.getGeoCodeUrl(startPoint);
				
				DownloadTask1 downloadTask2 = new DownloadTask1();

				// Start downloading json data from Google Directions API
				downloadTask2.execute(url1);
				
				url1 = StartTripActivity.this
						.getGeoCodeUrl(endPoint);

				DownloadTask1 downloadTask1 = new DownloadTask1();

				// Start downloading json data from Google Directions API
				downloadTask1.execute(url1);
			}else{
				Toast.makeText(this, "No Network Available", Toast.LENGTH_SHORT).show();
			}
				
			//}
		//});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent =  new Intent(StartTripActivity.this,TripDetailsActivity.class);
		intent.putExtra("tripObj",trip);
		startActivity(intent);
		finish();
	}

	private String getGeoCodeUrl(String input) {
		// Sensor enabled
		String sensor = "sensor=false";

		// Output format
		String output = "json";

		String parameters = "AIzaSyBRcYcPjOCb18IAvFsk0FK7Sfjtiv0rddg";
		try {
			parameters = "&address="+URLEncoder.encode(input,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/geocode/"
				+ output + "?" + "key="+parameters;

		return url;
	}

	// Fetches data from url passed
		private class DownloadTask1 extends AsyncTask<String, Void, LatLng> {
			// Downloading data in non-ui thread
			@Override
			protected LatLng doInBackground(String... url) {

				// For storing data from web service
				LatLng data = null;

				try {
					// Fetching the data from web service
					data = StartTripActivity.this.downloadUrl1(url[0]);
				} catch (Exception e) {
					Log.d("Background Task", e.toString());
				}
				return data;
			}

			// Executes in UI thread, after the execution of
			// doInBackground()
			@Override
			protected void onPostExecute(LatLng result) {
				super.onPostExecute(result);
				LatLng point = result;
				// Adding new item to the ArrayList
				StartTripActivity.this.markerPoints.add(point);

				// Creating MarkerOptions
				MarkerOptions options = new MarkerOptions();

				// Setting the position of the marker
				options.position(point);
				
				/**
				 * For the start location, the color of marker is GREEN and for
				 * the end location, the color of marker is RED.
				 */
				if (StartTripActivity.this.markerPoints.size() == 1) {
					options.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				} else if (StartTripActivity.this.markerPoints.size() == 2) {
					options.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				}

				// Add new marker to the Google Map Android API V2
				StartTripActivity.this.map.addMarker(options);

				// Checks, whether start and end locations are captured
				if (StartTripActivity.this.markerPoints.size() >= 2) {
					LatLng origin = StartTripActivity.this.markerPoints.get(0);
					LatLng dest = StartTripActivity.this.markerPoints.get(1);

					// Getting URL to the Google Directions API
					String url = StartTripActivity.this
							.getDirectionsUrl(origin, dest);

					DownloadTask downloadTask = new DownloadTask();

					// Start downloading json data from Google Directions API
					downloadTask.execute(url);
					LatLngBounds.Builder builder = new LatLngBounds.Builder();
					for (LatLng marker : StartTripActivity.this.markerPoints) {
					    builder.include(marker);
					}
					LatLngBounds bounds = builder.build();
					int padding = 30; // offset from edges of the map in pixels
					CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
					map.moveCamera(cu);
				}
				
			}
		}
	
	private String getDirectionsUrl(LatLng origin, LatLng dest) {
		// Origin of route
		String str_origin = "origin=" + origin.latitude + ","
				+ origin.longitude;

		// Destination of route
		String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

		// Sensor enabled
		String sensor = "sensor=false";

		// Building the parameters to the web service
		String parameters = str_origin + "&" + str_dest + "&" + sensor;

		// Output format
		String output = "json";

		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + parameters;

		return url;
	}

	/* A method to download json data from url */
	private String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	private LatLng downloadUrl1(String strUrl) throws IOException {
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		LatLng ltLn = null;
		try {
			
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			// Load the results into a StringBuilder
			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
			
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONArray results = jsonObj.getJSONArray("results");
			if(results!=null && results.length()>0){
				JSONObject jsonInsideResults = results.getJSONObject(0);
				if(jsonInsideResults!=null && jsonInsideResults.length()>0){
					JSONObject geomtry = jsonInsideResults.getJSONObject("geometry");
					if(geomtry!=null && geomtry.length()>0){
						JSONObject loc = geomtry.getJSONObject("location");
						if(loc!=null && loc.length()>0){
							double latitude = loc.getDouble("lat");
							double longitude = loc.getDouble("lng");
							ltLn = new LatLng(latitude,longitude);
						}
					}
				}	
			}
		}  catch (JSONException e) {
			Log.e("demo", "Cannot process JSON results", e);
		}
		catch (MalformedURLException e) {
			Log.e("demo", "Error processing Places API URL", e);
			return null;
		} catch (IOException e) {
			Log.e("demo", "Error connecting to Places API", e);
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return ltLn;
	}

	
	// Fetches data from url passed
	private class DownloadTask extends AsyncTask<String, Void, String> {
		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {

			// For storing data from web service
			String data = "";

			try {
				// Fetching the data from web service
				data = StartTripActivity.this.downloadUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			ParserTask parserTask = new ParserTask();

			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);

		}
	}

	/** A class to parse the Google Places in JSON format */
	private class ParserTask extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		// Parsing the data in non-ui thread
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {
			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);
				DirectionsJSONParser parser = new DirectionsJSONParser();

				// Starts parsing data
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;
			MarkerOptions markerOptions = new MarkerOptions();
			String distance = "";
			String duration = "";

			if (result.size() < 1) {
				Toast.makeText(StartTripActivity.this.getBaseContext(), "No Points",
						Toast.LENGTH_SHORT).show();
				return;
			}

			// Traversing through all the routes
			for (int i = 0; i < result.size(); i++) {
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();

				// Fetching i-th route
				List<HashMap<String, String>> path = result.get(i);

				// Fetching all the points in i-th route
				for (int j = 0; j < path.size(); j++) {
					HashMap<String, String> point = path.get(j);

					if (j == 0) { // Get distance from the list
						distance = point.get("distance");
						continue;
					} else if (j == 1) { // Get duration from the list
						duration = point.get("duration");
						continue;
					}
					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);
					points.add(position);
				}

				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(5);
				lineOptions.color(Color.BLUE);
			}

			StartTripActivity.this.tvDistanceDuration.setText("Distance:" + distance
					+ ", Duration:" + duration);

			// Drawing polyline in the Google Map for the i-th route
			StartTripActivity.this.map.addPolyline(lineOptions);
		}

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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.start_trip, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if(id == R.id.action_weather){
			Intent intent = new Intent(StartTripActivity.this, WeatherActivity.class);
			intent.putExtra("tripObj", trip);
			startActivity(intent);
		}
		if (id == R.id.logout) {
			ParseUser.logOut();
			Intent intent = new Intent(StartTripActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
