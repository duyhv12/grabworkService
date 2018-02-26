package com.grabwork;

public class Place {

	private String numberOfHouse;
	private String street;
	private String ward;
	private String country;
	private String city;
	public Place(String numberOfHouse, String street, String ward,
			String country, String city) {
		super();
		this.numberOfHouse = numberOfHouse;
		this.street = street;
		this.ward = ward;
		this.country = country;
		this.city = city;
	}
	public String getNumberOfHouse() {
		return numberOfHouse;
	}
	public void setNumberOfHouse(String numberOfHouse) {
		this.numberOfHouse = numberOfHouse;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	

}
