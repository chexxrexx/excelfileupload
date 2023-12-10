package com.example.hello;
public class Entity {
	private long ID;
	private String Country;

	public Entity(long ID, String Country) {
		// TODO Auto-generated constructor stub
		this.setID(ID);
		this.setCountry(Country);
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}

}