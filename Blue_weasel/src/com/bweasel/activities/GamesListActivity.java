package com.bweasel.activities;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.bweasel.classes.Game;
import com.bweasel.classes.GameAdapter;
import com.bweasel.classes.SharedContent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class GamesListActivity extends Activity {

	ListView gamesListView;
	SharedContent sc;
	List<Game> gamesList;
	String[] playersList;
	GameAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_games_list);
		gamesListView = (ListView) findViewById(R.id.games_list);
		sc = (SharedContent) getApplication();
		gamesList = sc.getGamesList();
		if (gamesList == null){
			Toast.makeText(GamesListActivity.this, "no list", Toast.LENGTH_SHORT).show();
		}
		else {
			adapter = new GameAdapter(this, gamesList);
			gamesListView.setAdapter(adapter);
			gamesListView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String gameId = ((TextView) view.findViewById(R.id.game_id)).getText().toString();
					sc.setGameId(gameId);
					if(sc.getPlayersList() != null){
						sc.setPlayersList(new String[4]);
					}
					HashMap<String, String> data = new HashMap<String, String>();
					data.put("gameid", gameId);
					AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
					asyncHttpPost.execute("http://10.2.203.4:8080/Blue_Weasel_Server/belot/game_available_seats/");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Intent intent = new Intent(GamesListActivity.this, PlayersListActivity.class);
					startActivity (intent);
				}
			});
		}
	}
	
	public void seeGames (View view){
		getGames();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Intent intent = new Intent(GamesListActivity.this, GamesListActivity.class);
		startActivity (intent);
		finish();
		return;
	}
	
	public void getGames(){
		if(sc.getGamesList() != null){
			sc.getGamesList().clear();
		}
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("status", "all");
		AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
		asyncHttpPost.execute("http://10.2.203.4:8080/Blue_Weasel_Server/belot/gamelist/");
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
				Toast.makeText(GamesListActivity.this, "Cannot connect to the server, please check your Wifi connection", Toast.LENGTH_SHORT).show();
			}
			else{
				if (result.contains("Games")){
					if(result.contains("Id")){
						gamesList = new ArrayList<Game>();
						String line, id, name, status;
						while (result.contains("Id")){
							line = result.substring(result.lastIndexOf("<li>"), result.lastIndexOf("</li>") + 5);
							id = line.substring(line.lastIndexOf("Id: ") + 4, line.lastIndexOf(" / Name"));
							name = line.substring(line.lastIndexOf("Name: ") + 6, line.lastIndexOf(" / Status"));
							status = line.substring(line.lastIndexOf("Status: ") + 8);
							Game game;
							if (status.contains("started")){
								game = new Game(id, name, "Started");
								gamesList.add(game);
							}
							else if (status.contains("awaiting")){
								game = new Game(id, name, "Awaiting player");
								gamesList.add(0, game);
							}
							else {
								Toast.makeText(GamesListActivity.this, "Request problem", Toast.LENGTH_SHORT).show();
							}
							result = result.replace(line, "");
						}
						sc.setGamesList(gamesList);
					}
					else {
						Toast.makeText(GamesListActivity.this, "No games available", Toast.LENGTH_SHORT).show();
					}
				}
				else if (result.contains("Players")){
					if(result.contains("<li>")){
						playersList = new String[4];
						String player,line;
						int i;
						while (result.contains("<li>")){
							line = result.substring(result.lastIndexOf("<li>"), result.lastIndexOf("</li>") + 5);
							i = Integer.valueOf(line.substring(line.lastIndexOf("<li>") + 4, line.lastIndexOf(" / ")));
							player = line.substring(line.lastIndexOf(" / ") + 3, line.lastIndexOf("</li>"));
							if (!player.equals("")){
								playersList[i] = player;
							}
							else {
								Toast.makeText(GamesListActivity.this, "Request problem", Toast.LENGTH_SHORT).show();
							}
							result = result.replace(line, "");
							i++;
						}
						if (playersList.length == 4){
							sc.setPlayersList(playersList);
						}
						else{
							Toast.makeText(GamesListActivity.this, "Not the good number of players", Toast.LENGTH_SHORT).show();
						}
					}
					else {
						Toast.makeText(GamesListActivity.this, "No players in this game", Toast.LENGTH_SHORT).show();
					}
				}
				else {
					Toast.makeText(GamesListActivity.this, "Problems on the server side, sorry for the inconvenience", Toast.LENGTH_SHORT).show();
				}	
			}
		}
	}
}
