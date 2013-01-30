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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bweasel.classes.SharedContent;
import com.bweasel.game.DistributionWait;


/**
 * 
 * 
 * @author Andréi Broussillon, Bastien Carré, Boris Leng, Lyvia Louisius
 * @version 1.0
 */
public class PlayersListActivity extends Activity {

	SharedContent sc;
	String gameId;
	String[] playersList;
	TextView playerTop, playerLeft, playerBottom, playerRight, playersListTitle;
	Button viewButton, playerTopButton, playerLeftButton, playerBottomButton, playerRightButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_players_list);
		playerTop = (TextView) findViewById(R.id.player_top);
		playerLeft = (TextView) findViewById(R.id.player_left);
		playerBottom = (TextView) findViewById(R.id.player_bottom);
		playerRight = (TextView) findViewById(R.id.player_right);
		playersListTitle = (TextView) findViewById(R.id.players_list_title);
		viewButton = (Button) findViewById(R.id.view_button);
		sc = (SharedContent) getApplication();
		playersList = sc.getPlayersList();
		if (playersList == null){
			Toast.makeText(PlayersListActivity.this, "no list", Toast.LENGTH_SHORT).show();
		}
		playerTop.setText(playersList[0]);
		playerLeft.setText(playersList[1]);
		playerBottom.setText(playersList[2]);
		playerRight.setText(playersList[3]);
		if(playerTop.getText().toString().equals("free")||playerLeft.getText().toString().equals("free")
				||playerBottom.getText().toString().equals("free")||playerRight.getText().toString().equals("free")){
			if(playerTop.getText().toString().equals("free")){
				playerTopButton = (Button) findViewById(R.id.player_top_button);
				playerTopButton.setVisibility(View.VISIBLE);
				playerTop.setText("");
			}
			if(playerLeft.getText().toString().equals("free")){
				playerLeftButton = (Button) findViewById(R.id.player_left_button);
				playerLeftButton.setVisibility(View.VISIBLE);
				playerLeft.setText("");
			}
			if(playerBottom.getText().toString().equals("free")){
				playerBottomButton = (Button) findViewById(R.id.player_bottom_button);
				playerBottomButton.setVisibility(View.VISIBLE);
				playerBottom.setText("");
			}
			if(playerRight.getText().toString().equals("free")){
				playerRightButton = (Button) findViewById(R.id.player_right_button);
				playerRightButton.setVisibility(View.VISIBLE);
				playerRight.setText("");
			}
			viewButton.setVisibility(View.INVISIBLE);
			playersListTitle.setText("Awaiting player");
		}
	}
	
	public void viewGame (View view){
		Toast.makeText(PlayersListActivity.this, "\"View Game\" feature coming soon", Toast.LENGTH_SHORT).show();
		return;
	}
	public void joinTop (View view){
		HashMap<String, String> data = new HashMap<String, String>();
		gameId = sc.getGameId();
		data.put("gameid", gameId);
		data.put("position", "top");
		AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
		asyncHttpPost.execute("http://10.2.203.4:8080/Blue_Weasel_Server/belot/join_game/");
		return;
	}
	
	public void jointLeft (View view){
		HashMap<String, String> data = new HashMap<String, String>();
		gameId = sc.getGameId();
		data.put("gameid", gameId);
		data.put("position", "left");
		AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
		asyncHttpPost.execute("http://10.2.203.4:8080/Blue_Weasel_Server/belot/join_game/");
		return;
	}
	
	public void joinBottom (View view){
		HashMap<String, String> data = new HashMap<String, String>();
		gameId = sc.getGameId();
		data.put("gameid", gameId);
		data.put("position", "bottom");
		AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
		asyncHttpPost.execute("http://10.2.203.4:8080/Blue_Weasel_Server/belot/join_game/");
		return;
	}
	
	public void joinRight (View view){
		HashMap<String, String> data = new HashMap<String, String>();
		gameId = sc.getGameId();
		data.put("gameid", gameId);
		data.put("position", "right");
		AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
		asyncHttpPost.execute("http://10.2.203.4:8080/Blue_Weasel_Server/belot/join_game/");
		return;
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
			SharedContent sc = (SharedContent) getApplication();
			HttpClient client = sc.getClient();
			HttpPost post = new HttpPost(urls[0]);
			try {
				ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
				Iterator<String> it = mData.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					nameValuePair.add(new BasicNameValuePair(key, mData.get(key)));
				}
				post.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
				HttpContext localContext = sc.getLocalContext();
				HttpResponse response = client.execute(post, localContext);
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
				Toast.makeText(PlayersListActivity.this, "Cannot connect to the server, please check your Wifi connection", Toast.LENGTH_SHORT).show();
			}
			else{
				if (result.contains("joined the game")){
					Toast.makeText(PlayersListActivity.this, "Start Game", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(PlayersListActivity.this, DistributionWait.class);
					startActivity (intent);
					finish();
				}
				else {
					Toast.makeText(PlayersListActivity.this, "Problems on the server side, sorry for the inconvenience", Toast.LENGTH_SHORT).show();
				}	
			}
		}
	}
}
