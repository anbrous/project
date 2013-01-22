package com.bweasel.activities;

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

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class CreateAccountActivity extends Activity {

	private EditText usernameEdit, emailEdit, password1Edit, password2Edit;
	private String username, email, password1, password2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		usernameEdit = (EditText) findViewById(R.id.username);
		emailEdit = (EditText) findViewById(R.id.email_address);
		password1Edit = (EditText) findViewById(R.id.password);
		password2Edit = (EditText) findViewById(R.id.password_again);
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
				Toast.makeText(CreateAccountActivity.this, "Cannot connect to the server, please check your Wifi connection", Toast.LENGTH_SHORT).show();
			}
			else{
				String confirmationMessage;
				int startIndex = result.lastIndexOf("<body>");
				int endIndex = result.lastIndexOf("</body>");
				confirmationMessage = result.substring(startIndex + 6, endIndex);
				if (confirmationMessage.contains("created")){
					Toast.makeText(CreateAccountActivity.this, "Your account has been created", Toast.LENGTH_SHORT).show();
					finish();
				}
				else {
					if (confirmationMessage.contains("username already used")){
						Toast.makeText(CreateAccountActivity.this, "This username is already used, please choose another one", Toast.LENGTH_SHORT).show();
						usernameEdit.setText("");
					}
					else if (confirmationMessage.contains("email mistake")){
						Toast.makeText(CreateAccountActivity.this, "This e-mail address is not correct", Toast.LENGTH_SHORT).show();
						emailEdit.setText("");
					}
					else if (confirmationMessage.contains("email already used")){
						Toast.makeText(CreateAccountActivity.this, "This e-mail address is already used, please choose another one", Toast.LENGTH_SHORT).show();
						emailEdit.setText("");
					}
					else if (confirmationMessage.contains("password mistake")){
						Toast.makeText(CreateAccountActivity.this, "The second password doesn't match the first one", Toast.LENGTH_SHORT).show();
						password1Edit.setText("");
						password2Edit.setText("");
					}
				}	
			}
		}
	}
	
	public void createAccountInDatabase(View view){
		
		username = usernameEdit.getText().toString();
		email = emailEdit.getText().toString();
		password1 = password1Edit.getText().toString();
		password2 = password2Edit.getText().toString();
		if (username == null || username.replaceAll(" ", "") == "" || email.replaceAll(" ", "") == "" || email == null
				|| password1 == null || password1.replaceAll(" ", "") == "" || password2 == null || password2.replaceAll(" ", "") == ""){
			Toast.makeText(CreateAccountActivity.this, "Some fields of the form are empty, please fill them", Toast.LENGTH_SHORT).show();
		}
		else {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("action", "signup");
			data.put("username", username);
			data.put("email", email);
			data.put("password1", password1);
			data.put("password2", password2);
			AsyncHttpPost asyncHttpPost = new AsyncHttpPost (data);
			asyncHttpPost.execute("http://10.2.203.4:8080/Blue_Weasel_Server/bw/connection/");
		}
	}
}
