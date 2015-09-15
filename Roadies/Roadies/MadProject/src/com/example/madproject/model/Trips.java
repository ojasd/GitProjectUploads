package com.example.madproject.model;

import java.io.Serializable;

import com.parse.ParseObject;

public class Trips implements Serializable {
	
	private String objectId;
	private String tripName;
	private String startPoint;
	private String destPoint;
	private String startDate;
	private String startTime;
	private boolean tripStarted;
	private boolean tripCompleted;
	
	private String addDestn1;
	private String addDestn2;
	private String addDestn3;
	private String addDestn4;
	private String addDestn5;
	
	public Trips(ParseObject obj) {
		super();
		this.objectId = obj.getObjectId();
		this.tripName = obj.getString("tripname");
		this.startPoint = obj.getString("startpoint");
		this.destPoint = obj.getString("destination");
		this.startDate = obj.getString("startDate");
		this.startTime = obj.getString("startTime");
		this.tripStarted = obj.getBoolean("tripstarted");
		this.tripCompleted = obj.getBoolean("tripcompleted");
		this.addDestn1 = obj.getString("addDestn1");
		this.addDestn2 = obj.getString("addDestn2");
		this.addDestn3 = obj.getString("addDestn3");
		this.addDestn4 = obj.getString("addDestn4");
		this.addDestn5 = obj.getString("addDestn5");
		
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public String getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	public String getDestPoint() {
		return destPoint;
	}
	public void setDestPoint(String destPoint) {
		this.destPoint = destPoint;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public boolean isTripStarted() {
		return tripStarted;
	}
	public void setTripStarted(boolean tripStarted) {
		this.tripStarted = tripStarted;
	}
	public boolean isTripCompleted() {
		return tripCompleted;
	}
	public void setTripCompleted(boolean tripCompleted) {
		this.tripCompleted = tripCompleted;
	}
	public String getAddDestn1() {
		return addDestn1;
	}
	public void setAddDestn1(String addDestn1) {
		this.addDestn1 = addDestn1;
	}
	public String getAddDestn2() {
		return addDestn2;
	}
	public void setAddDestn2(String addDestn2) {
		this.addDestn2 = addDestn2;
	}
	public String getAddDestn3() {
		return addDestn3;
	}
	public void setAddDestn3(String addDestn3) {
		this.addDestn3 = addDestn3;
	}
	public String getAddDestn4() {
		return addDestn4;
	}
	public void setAddDestn4(String addDestn4) {
		this.addDestn4 = addDestn4;
	}
	public String getAddDestn5() {
		return addDestn5;
	}
	public void setAddDestn5(String addDestn5) {
		this.addDestn5 = addDestn5;
	}
	
}
