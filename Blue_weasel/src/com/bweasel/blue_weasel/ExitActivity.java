package com.bweasel.blue_weasel;

import java.util.Random;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;

public class ExitActivity extends Activity {

	private static final int STOPAPP = 0;
	private static final long TIMER = 1500;
	/** The Handler will enable us to finish the ExitActivity when it receives a certain Message */
	private Handler exitHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STOPAPP:
                	/**Ends the activity */
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    
    /**Sends a message to finish the Activity the handler after 1,5 second and plays a sound*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exit);
		Random randomGenerator = new Random();		
		int randomInt = randomGenerator.nextInt(12);
		int sound;
		switch (randomInt){
			case 0:
				sound = R.raw.i_dont_blame_you;
				break;
			case 1:
				sound = R.raw.i_dont_hate_you;
				break;	
			case 2:
				sound = R.raw.no_hard_feelings;
				break;
			case 3:
				sound = R.raw.shutting_down;
				break;
			case 4:
				sound = R.raw.sorry_we_re_closed;
				break;
			case 5:
				sound = R.raw.goodbye;
				break;
			case 6:
				sound = R.raw.goodnight;
				break;
			case 7:
				sound = R.raw.hibernating;
				break;
			case 8:
				sound = R.raw.nap_time;
				break;
			case 9:
				sound = R.raw.resting;
				break;
			case 10:
				sound = R.raw.sleep_mode_activated;
				break;
			case 11:
				sound = R.raw.why;
				break;
			default:
				sound = 0;
		}
		if (sound != 0){
			MediaPlayer mp;
			mp = MediaPlayer.create(ExitActivity.this, sound);
			mp.start();
		}
		Message msg = new Message();
	    msg.what = STOPAPP;
	    exitHandler.sendMessageDelayed(msg, TIMER);
	}
}
