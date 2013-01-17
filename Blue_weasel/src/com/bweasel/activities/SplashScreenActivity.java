package com.bweasel.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;

/** First Activity to be started. Welcomes the user to Blue Weasel. */
public class SplashScreenActivity extends Activity {

	private static final int STARTHOME = 0;
	private static final long TIMER = 2000;
	/** The Handler will enable us to start the HomeActivity when it receives a certain Message */
	private Handler startHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case STARTHOME:
				Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
				startActivity(intent);
				finish();
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	/** Creates the layout of the activity using its XML description */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		Message msg = new Message();
	    msg.what = STARTHOME;
	    startHandler.sendMessageDelayed(msg, TIMER);
	}
	
	/** User CAN NOT exit the splash screen activity mouahahahah (except if he presses "home") */
	@Override
	public void onBackPressed() {
		//do nothing
	}
}
