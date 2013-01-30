package com.bweasel.classes;

import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.protocol.HttpContext;

import android.app.Application;

public class SharedContent extends Application {
	
	private String username;
	private CookieStore cookieStore;
	private HttpContext localContext;
	private HttpClient client;
	private List<Game> gamesList;
	private String[] playersList;
	private String gameId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public HttpContext getLocalContext() {
		return localContext;
	}

	public void setLocalContext(HttpContext localContext) {
		this.localContext = localContext;
	}

	public HttpClient getClient() {
		return client;
	}

	public void setClient(HttpClient client) {
		this.client = client;
	}

	public List<Game> getGamesList() {
		return gamesList;
	}

	public void setGamesList(List<Game> gamesList) {
		this.gamesList = gamesList;
	}

	public String[] getPlayersList() {
		return playersList;
	}

	public void setPlayersList(String[] playersList) {
		this.playersList = playersList;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
}