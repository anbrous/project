package com.bweasel.blue_weasel;

/**
 * @author Andrei Broussillon, Bastien Carre, Boris Leng, Lyvia Louisius 
 */
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class HomeActivity extends Activity{

	/** Sets the key for the User Name sent to the ConnectedActivity
	public final static String EXTRA_MESSAGE = "com.bweasel.blue_weasel";
	*/

	/** Creates the layout of the Main Activity using its xml description */
	@Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_home);
    }
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		
	}
    
    /** 
     * Called when the user clicks the Send button.
     * Sends the text written by the user to another activity.
     
    public void sendMessage (View view) {	
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    */
	
	/** Goes to the Exit Screen when the back arrow is pressed */
	public void onBackPressed(){
	   	 Intent intent = new Intent(HomeActivity.this, ExitActivity.class);
	   	 startActivity(intent);
	   	 finish();
	   	 return;
	}
}