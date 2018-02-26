package com.grabwork;

public class User {
//	ID, TEN, DIACHI, SDT, MK, CMND, HINHANH,LAT,LNG,ID_VAITRO,SODU
	private int id;
	private String name;
	private String pass;
	private String address;
	private String phone;
	private long cardId;
	private String icon;
	private double lat;
	private double lng;
	private int roleId;
	private double total;
	public User(int id, String name, String pass, String address, String phone,
			long cardId, String icon, double lat, double lng, int roleId,
			double total) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.address = address;
		this.phone = phone;
		this.cardId = cardId;
		this.icon = icon;
		this.lat = lat;
		this.lng = lng;
		this.roleId = roleId;
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public long getCardId() {
		return cardId;
	}
	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
