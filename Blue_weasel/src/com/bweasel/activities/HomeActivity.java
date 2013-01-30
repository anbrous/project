package com.bweasel.activities;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.bweasel.classes.SharedContent;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends Activity{

	private EditText emailEdit, passwordEdit;
	private String email, password;
	SharedContent sc;
	
	/** Creates the layout of the Main Activity using its xml description */
	@Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_home);
	    emailEdit = (EditText) findViewById(R.id.email_address);
	    passwordEdit = (EditText) findViewById(R.id.password);
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
			sc = (SharedContent) getApplication();
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
	
	public class AsyncHttpPost extends AsyncTask<String, String, String> {
		
		private HashMap<String, String> mData = null;
		CookieStore cookieStore;
		HttpContext localContext;
		HttpClient client;
		
		public AsyncHttpPost(HashMap<String, String> data) {
			mData = data;
		}
		
		@Override
		protected String doInBackground(String... urls){
			byte[] result = null;
			String str = "";
			client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urls[0]);
			try {
				ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
				Iterator<String> it = mData.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					nameValuePair.add(new BasicNameValuePair(key, mData.get(key)));
				}
				post.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
				cookieStore = new BasicCookieStore();
				localContext = new BasicHttpContext();
				localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
				HttpResponse response = client.execute(post, localContext);
				sc.setCookieStore(cookieStore);
				sc.setLocalContext(localContext);
				sc.setClient(client);
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
				if (result.contains("connected")){
						int startIndex = result.lastIndexOf("connected, ");
						int endIndex = result.lastIndexOf(" !!!");
						String username = result.substring(startIndex + 11, endIndex);
						SharedContent sc = (SharedContent) getApplication();
						sc.setUsername(username);
						Intent intent = new Intent(HomeActivity.this, ConnectedActivity.class);
						startActivity (intent);
						emailEdit.setText("");
						passwordEdit.setText("");
				}
				else {
					if (result.contains("Email is not registered")){
						Toast.makeText(HomeActivity.this, "This account does not exist", Toast.LENGTH_SHORT).show();
						emailEdit.setText("");
					}
					else if (result.contains("Incorrect password")){
						Toast.makeText(HomeActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
						passwordEdit.setText("");
					}
					else{
						Toast.makeText(HomeActivity.this, "Problems on the server side, sorry for the inconvenience", Toast.LENGTH_SHORT).show();
					}
				}
			}
		}
	}
}