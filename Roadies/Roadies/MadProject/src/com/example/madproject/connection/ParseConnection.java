package com.example.madproject.connection;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;

public class ParseConnection extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "BPEweGbzuhvbrk6R4F1GEyV10i8ZHCNmo2QBqP6s", "4GBW6NUaq9qxzo7qqdDoBbDUp0nQCqCQJlxxK68Y");
		ParseFacebookUtils.initialize(this);
		ParseACL defaultACL = new ParseACL();		 
        defaultACL.setPublicReadAccess(true);        
        ParseACL.setDefaultACL(defaultACL, true);        
	}
}
