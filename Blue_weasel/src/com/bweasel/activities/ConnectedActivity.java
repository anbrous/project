package com.bweasel.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConnectedActivity extends Activity {
	
	//Creates the layout of the DisplayMessage Activity
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connected);
		TextView greetings = (TextView) findViewById(R.id.greetings);
		
		//Get the message from the intent
		Intent intent = getIntent();
		String username = intent.getStringExtra(HomeActivity.EXTRA_USERNAME);
		greetings.setText(" Greetings " + username + ", what do you want to do ?");
	}
	
	public void seeGames (View view){
		
	}
	
	public void browseHistory (View view){
		
	}
}
