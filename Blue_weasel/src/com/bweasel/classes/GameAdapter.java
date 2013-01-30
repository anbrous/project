package com.bweasel.classes;

import java.util.List;

import com.bweasel.activities.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GameAdapter extends BaseAdapter {

	List<Game> gamesList;
	LayoutInflater inflater;
	
	public GameAdapter(Context context, List<Game> gamesList){
		inflater = LayoutInflater.from(context);
		this.gamesList = gamesList;
	}
	
	public int getCount() {
		return gamesList.size();
	}

	public Object getItem(int position) {
		return gamesList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		TextView gameId;
		TextView gameName;
		TextView gameStatus;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_game,  null);
			holder.gameId = (TextView)convertView.findViewById(R.id.game_id);
			holder.gameName = (TextView)convertView.findViewById(R.id.game_name);
			holder.gameStatus = (TextView)convertView.findViewById(R.id.game_status);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.gameId.setText(gamesList.get(position).getId());
		holder.gameName.setText(gamesList.get(position).getName());
		holder.gameStatus.setText(gamesList.get(position).getStatus());
		return convertView;
	}
}
