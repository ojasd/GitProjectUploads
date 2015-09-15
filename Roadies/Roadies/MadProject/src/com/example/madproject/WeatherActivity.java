package com.example.madproject;

import java.util.ArrayList;

import org.json.JSONException;

import com.example.madproject.Parser.JSONWeatherParser;
import com.example.madproject.connection.WeatherHttpClient;
import com.example.madproject.model.Trips;
import com.example.madproject.weather.adapter.DailyForecastPageAdapter;
import com.example.madproject.weather.model.Weather;
import com.example.madproject.weather.model.WeatherForecast;
import com.parse.ParseUser;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherActivity extends FragmentActivity {

	private TextView cityText;
	private TextView condDescr;
	private TextView temp;
	// private TextView press;
	// private TextView windSpeed;
	// private TextView windDeg;
	private TextView unitTemp;

	// private TextView hum;
	private ImageView imgView;

	private static String forecastDaysNum = "3";
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		if(isConnectionOnline(this)){
			
			Trips trip = (Trips) getIntent().getExtras().get("tripObj");
			String[] destArray = trip.getDestPoint().split(",");
			if (destArray.length <= 1) {
				Toast.makeText(WeatherActivity.this,
						"Could not find Weather Details", Toast.LENGTH_SHORT)
						.show();
				finish();
			}
			ArrayList<String> strb = new ArrayList<String>();
			for (int i = destArray.length - 1; i > destArray.length - 3; i--) {
				if (destArray[i].trim().length() > 2
						&& !destArray[i].trim().startsWith("United States")
						&& !destArray[i].trim().startsWith("USA")) {
					strb.add(destArray[i].trim());
				} else if (destArray[i].length() == 2
						&& !destArray[i].equalsIgnoreCase("US")
						|| !destArray[i].equalsIgnoreCase("US")) {
					strb.add(destArray[i].trim());
				} else {
					strb.add(destArray[i].trim());
				}
			}
			StringBuffer s = new StringBuffer();
			for (int j = strb.size() - 1; j > -1; j--) {
				s.append(strb.get(j));
				s.append(", ");
			}
	
			if (s != null && s.toString().trim().length() > 0) {
				s.setLength(s.toString().trim().length() - 1);
			}
	
			String city = s.toString().trim();
			if (city.split(",").length <= 1) {
				Toast.makeText(WeatherActivity.this,
						"Could not find Weather Details", Toast.LENGTH_SHORT)
						.show();
				finish();
			}
			String lang = "en";
	
			cityText = (TextView) findViewById(R.id.cityText);
			cityText.setTextColor(Color.parseColor("#ffffff"));
			temp = (TextView) findViewById(R.id.temp);
			unitTemp = (TextView) findViewById(R.id.unittemp);
			unitTemp.setText("°C");
			unitTemp.setTextColor(Color.parseColor("#ffffff"));
			unitTemp.setTextSize(16);
			condDescr = (TextView) findViewById(R.id.skydesc);
	
			pager = (ViewPager) findViewById(R.id.pager);
			imgView = (ImageView) findViewById(R.id.condIcon);
			/*
			 * condDescr = (TextView) findViewById(R.id.condDescr);
			 * 
			 * hum = (TextView) findViewById(R.id.hum); press = (TextView)
			 * findViewById(R.id.press); windSpeed = (TextView)
			 * findViewById(R.id.windSpeed); windDeg = (TextView)
			 * findViewById(R.id.windDeg);
			 */
	
			JSONWeatherTask task = new JSONWeatherTask();
			task.execute(new String[] { city, lang });
	
			JSONForecastWeatherTask task1 = new JSONForecastWeatherTask();
			task1.execute(new String[] { city, lang, forecastDaysNum });
		}
		else{
			Toast.makeText(this, "Network Not available", Toast.LENGTH_SHORT)
			.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.logout) {
			ParseUser.logOut();
			Intent intent = new Intent(WeatherActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

		@Override
		protected Weather doInBackground(String... params) {
			Weather weather = new Weather();
			String data = ((new WeatherHttpClient()).getWeatherData(params[0],
					params[1]));

			try {
				weather = JSONWeatherParser.getWeather(data);
				System.out.println("Weather [" + weather + "]");
				// Let's retrieve the icon
				weather.iconData = ((new WeatherHttpClient())
						.getImage(weather.currentCondition.getIcon()));

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return weather;

		}

		@Override
		protected void onPostExecute(Weather weather) {
			super.onPostExecute(weather);

			if (weather.iconData != null && weather.iconData.length > 0) {
				Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0,
						weather.iconData.length);
				imgView.setImageBitmap(img);
			}

			cityText.setText(weather.location.getCity() + ","
					+ weather.location.getCountry());
			temp.setText(""
					+ Math.round((weather.temperature.getTemp() - 275.15)));
			// condDescr.setText(weather.currentCondition.getCondition() + "(" +
			// weather.currentCondition.getDescr() + ")");
			/*
			 * 
			 * temp.setText("" + Math.round((weather.temperature.getTemp() -
			 * 275.15)) + "°C"); hum.setText("" +
			 * weather.currentCondition.getHumidity() + "%"); press.setText("" +
			 * weather.currentCondition.getPressure() + " hPa");
			 * windSpeed.setText("" + weather.wind.getSpeed() + " mps");
			 * windDeg.setText("" + weather.wind.getDeg() + "°");
			 */
		}

	}

	private class JSONForecastWeatherTask extends
			AsyncTask<String, Void, WeatherForecast> {

		@Override
		protected WeatherForecast doInBackground(String... params) {

			String data = ((new WeatherHttpClient()).getForecastWeatherData(
					params[0], params[1], params[2]));
			WeatherForecast forecast = new WeatherForecast();
			try {
				forecast = JSONWeatherParser.getForecastWeather(data);
				System.out.println("Weather [" + forecast + "]");
				// Let's retrieve the icon
				// weather.iconData = ( (new
				// WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return forecast;

		}

		@Override
		protected void onPostExecute(WeatherForecast forecastWeather) {
			super.onPostExecute(forecastWeather);
			DailyForecastPageAdapter adapter = new DailyForecastPageAdapter(
					Integer.parseInt(forecastDaysNum),
					getSupportFragmentManager(), forecastWeather);
			pager.setAdapter(adapter);
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
}