package com.grabwork;

public class WorkDetail {
	private int workDetailId;
	private int workId;
	private String workName;
	private double workPrice;
	private int placeId;
	private String workDes;
	private String workTime;
	private Place place;
	public WorkDetail(int workDetailId,int workId, String workName, double workPrice, int placeId,
			String workDes, String workTime, Place place) {
		super();
		this.workDetailId = workDetailId;
		this.workId = workId;
		this.workName = workName;
		this.workPrice = workPrice;
		this.placeId = placeId;
		this.workDes = workDes;
		this.workTime = workTime;
		this.place = place;
	}
	
	public int getWorkDetailId() {
		return workDetailId;
	}

	public void setWorkDetailId(int workDetailId) {
		this.workDetailId = workDetailId;
	}

	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public double getWorkPrice() {
		return workPrice;
	}
	public void setWorkPrice(double workPrice) {
		this.workPrice = workPrice;
	}
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public String getWorkDes() {
		return workDes;
	}
	public void setWorkDes(String workDes) {
		this.workDes = workDes;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	
	
	
	
}
