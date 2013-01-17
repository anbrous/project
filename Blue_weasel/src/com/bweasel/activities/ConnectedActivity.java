package com.bweasel.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
public class DisplayMessageActivity extends Activity {
	
	Creates the layout of the DisplayMessage Activity*/
	/**
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Get the message from the intent
		Intent intent = getIntent();
		String message = intent.getStringExtra(HomeActivity.EXTRA_MESSAGE);
		
		//Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//Create the text view
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(message);
		
		//Set the text view as the activity layout
		setContentView(textView);
	}*/
	
	/** Creates the menu for the first screen using the xml description of the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_message, menu);
        return true;
    }
   */
	/** Enables the user to go back to the previous activity by pressing the "back" arrow.
	 * Or pressing the "Back" button 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.back:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
*/
