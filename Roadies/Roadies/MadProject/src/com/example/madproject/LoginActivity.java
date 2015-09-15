package com.example.madproject;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

	EditText emailText;
	EditText pwdText;
	Button btnSignup;
	Button btnLogin;
	LoginButton btnFbLogin;
	ParseUser user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if(isConnectionOnline(this)){
		
			emailText = (EditText) findViewById(R.id.editTextEmail);
			pwdText = (EditText) findViewById(R.id.editTextPassword);
			btnLogin = (Button) findViewById(R.id.buttonLogin);
			btnSignup = (Button) findViewById(R.id.buttonCreateNewAccount);
	
			user = ParseUser.getCurrentUser();
	
			if (user != null) {
				Intent i = new Intent(LoginActivity.this, MyTripsActivity.class);
				startActivity(i);
			}
	
			btnLogin.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(emailText.getText().toString()==null || emailText.getText().toString().trim().equals("")){
						Toast.makeText(LoginActivity.this, "Please Enter Correct Email Id", Toast.LENGTH_SHORT).show();
					}
					else if(pwdText.getText().toString() == null || pwdText.getText().toString().trim().equals("")){
						Toast.makeText(LoginActivity.this, "Please Enter Correct Password", Toast.LENGTH_SHORT).show();
					}
					else{
						ParseUser.logInInBackground(emailText.getText().toString(), pwdText.getText().toString(), new LogInCallback() {
							  public void done(ParseUser user, ParseException e) {
							    if (user != null) {
							      Intent intent  = new Intent(LoginActivity.this, MyTripsActivity.class);
							      startActivity(intent);
							    } else {
							      Toast.makeText(LoginActivity.this, "Login Failed. Please check User credentials",
							    		  Toast.LENGTH_SHORT).show();
							    }
							  }
							});
					}
					
				}
			});
			btnSignup.setOnClickListener(new View.OnClickListener() {
	
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
					startActivity(intent);
					finish();
				}
			});
	
			btnFbLogin = (LoginButton) findViewById(R.id.facebook_login_button);
			final List<String> fbPermissionList = Arrays.asList("public_profile",
					"email", "user_friends");
			// btnFbLogin.setReadPermissions(fbPermissionList);
	
			btnFbLogin.setOnClickListener(new View.OnClickListener() {
	
				@Override
				public void onClick(View v) {
	
					ParseFacebookUtils.logInWithReadPermissionsInBackground(LoginActivity.this, fbPermissionList,new LogInCallback() {
						@Override
						public void done(ParseUser user, ParseException err) {
							if (user == null) {
								Toast.makeText(LoginActivity.this, "The user cancelled the Facebook login.", Toast.LENGTH_SHORT).show();
							} else {
								Intent i = new Intent(LoginActivity.this, MyTripsActivity.class);
								startActivity(i);
							}
						}
					});
				}
			});
		} else {
				Toast.makeText(this, "Network Not available", Toast.LENGTH_SHORT)
						.show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
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
