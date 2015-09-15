package com.example.madproject;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

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

public class SignUpActivity extends Activity {

	
	
	Button btnCancel;
	String name;
	String email;
	String password;
	String confirmPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);		
		if(isConnectionOnline(this)){
			Button btnSignUp = (Button) findViewById (R.id.buttonSignup);
			
			btnSignUp.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {				
					EditText nameText = (EditText) findViewById(R.id.editTextUserName);
					EditText emailText = (EditText) findViewById(R.id.editTextEmail);
					EditText pwdText = (EditText) findViewById(R.id.editTextPassword);
					EditText confPwdText = (EditText) findViewById(R.id.editTextPasswordConfirm);							
					name= nameText.getText().toString();
					email = emailText.getText().toString();
					password = pwdText.getText().toString();
					confirmPassword = confPwdText.getText().toString();
					
					boolean login = true;
					if(name.isEmpty()){	
						Toast.makeText(SignUpActivity.this, "Please enter the Name", Toast.LENGTH_LONG).show();
						login = false;
					}
					else if(email.isEmpty()){
						Toast.makeText(SignUpActivity.this, "Please enter the Email", Toast.LENGTH_LONG).show();
						login = false;					
					}
					else if(password.isEmpty()){
						Toast.makeText(SignUpActivity.this, "Please enter the Password", Toast.LENGTH_LONG).show();
						login = false;					
					}
					else if(!confirmPassword.equals(password)){
						Toast.makeText(SignUpActivity.this, "Both the Passwords do no match", Toast.LENGTH_LONG).show();
						login = false;					
					}
					if(login){
						ParseUser user = new ParseUser();
						user.setUsername(email);
						user.setEmail(email);					
						user.setPassword(password);
						user.signUpInBackground(new SignUpCallback() {
							@Override
							public void done(ParseException e) {
								if(e == null) {
									Toast.makeText(SignUpActivity.this, "Signup Successfull", Toast.LENGTH_LONG).show();
									Intent intent = new Intent(SignUpActivity.this, MyTripsActivity.class);
									startActivity(intent);
									finish();
								} else {
									Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
								}
							}
						});
					}
				}
			});
			btnCancel = (Button) findViewById(R.id.buttonCancel);
			btnCancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
					startActivity(intent);
					finish();
				}
			});
		} else {
			Toast.makeText(this, "Network Not available", Toast.LENGTH_SHORT)
			.show();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
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
