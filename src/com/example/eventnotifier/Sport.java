package com.example.eventnotifier;

public class Sport {
	private int id;
	private String description;
	private int colour;
	
	public Sport(){
		
	}
	
	public Sport(int id, String description, int colour) {
		super();
		this.id = id;
		this.description = description;
		this.colour = colour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getColour() {
		return colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	@Override
	public String toString() {
		return "Sport [id=" + id + ", description=" + description + ", colour="
				+ colour + "]";
	}
	
	
	
	

}
