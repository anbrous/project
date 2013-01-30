package com.bweasel.activities;

import java.util.List;

import com.bweasel.classes.Game;
import com.bweasel.classes.GameAdapter;
import com.bweasel.classes.SharedContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HistoryActivity extends Activity {

	ListView gamesPlayedListView;
	SharedContent sc;
	List<Game> gamesList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		gamesPlayedListView = (ListView) findViewById(R.id.games_played_list);
		sc = (SharedContent) getApplication();
		gamesList = sc.getGamesList();
		if (gamesList == null){
			Toast.makeText(HistoryActivity.this, "no list", Toast.LENGTH_SHORT).show();
		}
		else {
			GameAdapter adapter = new GameAdapter(this, gamesList);
			gamesPlayedListView.setAdapter(adapter);
			gamesPlayedListView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(HistoryActivity.this, "\"View History\" feature coming soon", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
}