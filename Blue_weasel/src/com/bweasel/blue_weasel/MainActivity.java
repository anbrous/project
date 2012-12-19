package com.bweasel.blue_weasel;

/**
 * @author Andrei Broussillon, Bastien Carre, Boris Leng, Lyvia Louisius 
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity{

	/** Sets the key for the message sent to the DisplayMessageActivity	 */
	public final static String EXTRA_MESSAGE = "com.bweasel.blue_weasel";

	/** Creates the layout of the Main Activity using its xml description */
	@Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
    }
	
	/** Creates the menu for the first screen using the xml description of the menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /** 
     * Called when the user clicks the Send button.
     * Sends the text written by the user to another activity.
     */
    public void sendMessage (View view) {
    	
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    public void exit (MenuItem item) {
    	finish();
    }
}
