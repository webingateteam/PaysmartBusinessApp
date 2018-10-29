package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class GetSOSDriverMessageResponse{

	@SerializedName("template")
	private String template;

	@SerializedName("Status")
	private String status;

	@SerializedName("Message")
	private String message;

	@SerializedName("Latitude")
	private double latitude;

	@SerializedName("Longitude")
	private double longitude;

	@SerializedName("UserTypeId")
	private int userTypeId;

	@SerializedName("SentOn")
	private String sentOn;

	@SerializedName("SentTime")
	private String sentTime;

	@SerializedName("Username")
	private String username;

	@SerializedName("SentTo")
	private String sentTo;

	@SerializedName("StatusId")
	private int statusId;

	@SerializedName("Id")
	private int id;

	@SerializedName("UserType")
	private String userType;

	@SerializedName("MessageId")
	private int messageId;

	public void setTemplate(String template){
		this.template = template;
	}

	public String getTemplate(){
		return template;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}

	public void setUserTypeId(int userTypeId){
		this.userTypeId = userTypeId;
	}

	public int getUserTypeId(){
		return userTypeId;
	}

	public void setSentOn(String sentOn){
		this.sentOn = sentOn;
	}

	public String getSentOn(){
		return sentOn;
	}

	public void setSentTime(String sentTime){
		this.sentTime = sentTime;
	}

	public String getSentTime(){
		return sentTime;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setSentTo(String sentTo){
		this.sentTo = sentTo;
	}

	public String getSentTo(){
		return sentTo;
	}

	public void setStatusId(int statusId){
		this.statusId = statusId;
	}

	public int getStatusId(){
		return statusId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUserType(String userType){
		this.userType = userType;
	}

	public String getUserType(){
		return userType;
	}

	public void setMessageId(int messageId){
		this.messageId = messageId;
	}

	public int getMessageId(){
		return messageId;
	}

	@Override
 	public String toString(){
		return 
			"GetSOSDriverMessageResponse{" + 
			"template = '" + template + '\'' + 
			",status = '" + status + '\'' + 
			",message = '" + message + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",longitude = '" + longitude + '\'' + 
			",userTypeId = '" + userTypeId + '\'' + 
			",sentOn = '" + sentOn + '\'' + 
			",sentTime = '" + sentTime + '\'' + 
			",username = '" + username + '\'' + 
			",sentTo = '" + sentTo + '\'' + 
			",statusId = '" + statusId + '\'' + 
			",id = '" + id + '\'' + 
			",userType = '" + userType + '\'' + 
			",messageId = '" + messageId + '\'' + 
			"}";
		}
}