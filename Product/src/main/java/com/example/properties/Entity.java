package com.example.properties;
public class Entity {
	private long ID;
	private String Country;
	
	/* --------------------------
	 * @NOTE: in the event that an employee were to be added,
	 * the ID and country vars can be used in the product class
	 */

	public Entity(long ID, String Country) {
		// TODO Auto-generated constructor stub
		this.setID(ID);
		this.setCountry(Country);
	}

	// @todo: potentially allow for employees to later inherit these values
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