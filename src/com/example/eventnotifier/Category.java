package com.example.eventnotifier;

public class Category {
	private String name;
	private String description;
	private String debut;
	private String picture;
	
	public Category() {
		this.name = new String();
		this.description = new String();
		this.debut = new String();
		this.picture = new String();
	}
	
	public Category(String name, String description, String debut, String picture) {
		this.name = name;
		this.description = description;
		this.debut = debut;
		this.picture = picture;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setdescription(String description) {
		this.description = description;
	}
	
	public String getDebut() {
		return debut;
	}
	
	public void setDebut(String debut) {
		this.debut = debut;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture; 
	}
	
	public String get(String attribute) {
		if (attribute.equals(Constants.NAME_TAG))
			return name;
		else if (attribute.equals(Constants.DESCRIPTION_TAG))
			return description;
		else if (attribute.equals(Constants.DEBUT_TAG))
			return debut;
		else if (attribute.equals(Constants.PICTURE_TAG))
			return picture;		
		return null;		
	}
	
	public void set(String attribute, String value) {
		if (attribute.equals(Constants.NAME_TAG))
			setName(value);
		else if (attribute.equals(Constants.DESCRIPTION_TAG))
			setdescription(value);
		else if (attribute.equals(Constants.DEBUT_TAG))
			setDebut(value);
		else if (attribute.equals(Constants.PICTURE_TAG))
			setPicture(value);
	}
	
	public String toString() {
		return "Name: "+getName()+"description: "+getDescription()+"Debut: "+getDebut()+"Picture: "+getPicture();
	}

}
