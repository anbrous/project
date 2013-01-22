package com.bweasel.activities;

/**
 * @author Andrei Broussillon, Bastien Carre, Boris Leng, Lyvia Louisius 
 */
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends Activity{

	public final static String EXTRA_USERNAME = "com.bweasel.activities";
	
	private EditText emailEdit, passwordEdit;
	private String email, password;
	
	/** Creates the layout of the Main Activity using its xml description */
	@Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_home);
	    emailEdit = (EditText) findViewById(R.id.email_address);
	    passwordEdit = (EditText) findViewById(R.id.password);
    }
	
	public class AsyncHttpPost extends AsyncTask<String, String, String> {
		
		private HashMap<String, String> mData = null;
		
		public AsyncHttpPost(HashMap<String, String> data) {
			mData = data;
		}
		
		@Override
		protected String doInBackground(String... urls){
			byte[] result = null;
			String str = "";
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urls[0]);
			try {
				ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
				Iterator<String> it = mData.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					nameValuePair.add(new BasicNameValuePair(key, mData.get(key)));
				}
				post.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
				HttpResponse response = client.execute(post);
				StatusLine statusLine = response.getStatusLine();
					if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
						result = EntityUtils.toByteArray(response.getEntity());
						str = new String (result, "UTF-8");
					}
				}
			catch (Exception e){
				e.printStackTrace();
			}
	        return str;
		}
		
		@Override
		protected void onPostExecute (String result){
			if(result == null || result == ""){
				Toast.makeText(HomeActivity.this, "Cannot connect to the server, please check your Wifi connection", Toast.LENGTH_SHORT).show();
			}
			else{
				String confirmationMessage;
				int startIndex = result.lastIndexOf("<body>");
				int endIndex = result.lastIndexOf("</body>");
				confirmationMessage = result.substring(startIndex + 6, endIndex);
				if (confirmationMessage.contains("connected")){
					startIndex = confirmationMessage.lastIndexOf("player");
					endIndex = confirmationMessage.lastIndexOf(" is");
					String username = confirmationMessage.substring(startIndex + 7, endIndex);
					Intent intent = new Intent(HomeActivity.this, ConnectedActivity.class);
					intent.putExtra(EXTRA_USERNAME, username);
					startActivity (intent);
					emailEdit.setText("");
					passwordEdit.setText("");
				}
				else {
					if (confirmationMessage.contains("Email is not registered")){
						Toast.makeText(HomeActivity.this, "This account does not exist", Toast.LENGTH_SHORT).show();
						emailEdit.setText("");
					}
					else if (confirmationMessage.contains("Incorrect password")){
						Toast.makeText(HomeActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
						passwordEdit.setText("");
					}
				}	
			}
		}
	}
	
	public void signIn(View view){
		
		email = emailEdit.getText().toString();
		password = passwordEdit.getText().toString();
		if (email.replaceAll(" ", "") == "" || email == null || password == null || password.replaceAll(" ", "") == ""){
			Toast.makeText(HomeActivity.this, "Some fields are empty, please fill them", Toast.LENGTH_SHORT).show();
		}
		else {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("action", "signin");
			data.put("email", email);
			data.put("password1", password);
			data.put("password2", "password2");
			data.put("username", "username");
			AsyncHttpPost asyncHttpPost = new AsyncHttpPost (data);
			asyncHttpPost.execute("http://10.2.203.4:8080/Blue_Weasel_Server/bw/connection/");
		}
	}
	
	/** If the "Create an account" button is pressed, launches the CreateAccountActivity. */
	public void createAccount(View view){
		Intent intent = new Intent(HomeActivity.this, CreateAccountActivity.class);
		startActivity (intent);
		emailEdit.setText("");
		passwordEdit.setText("");
		return;
	}

	/** Goes to the Exit Screen when the back arrow is pressed */
	public void onBackPressed(){
	   	 Intent intent = new Intent(HomeActivity.this, ExitActivity.class);
	   	 startActivity(intent);
	   	 finish();
	   	 return;
	}
}