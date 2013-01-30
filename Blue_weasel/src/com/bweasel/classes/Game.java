package com.bweasel.classes;

public class Game {
	
	private String name;
	private String status;
	private String id;
	
	public Game(String id, String name, String status){
		this.setId(id);
		this.setName(name);
		this.setStatus(status);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
