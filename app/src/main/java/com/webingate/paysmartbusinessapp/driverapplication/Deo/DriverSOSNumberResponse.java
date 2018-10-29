package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverSOSNumberResponse{

	@SerializedName("MobiOrder")
	private int mobiOrder;

	@SerializedName("UserTypeId")
	private int userTypeId;

	@SerializedName("MobileNumber")
	private String mobileNumber;

	@SerializedName("Active")
	private int active;

	@SerializedName("UserName")
	private Object userName;

	@SerializedName("UserId")
	private int userId;

	@SerializedName("Id")
	private int id;

	@SerializedName("CreatedOn")
	private String createdOn;

	public void setMobiOrder(int mobiOrder){
		this.mobiOrder = mobiOrder;
	}

	public int getMobiOrder(){
		return mobiOrder;
	}

	public void setUserTypeId(int userTypeId){
		this.userTypeId = userTypeId;
	}

	public int getUserTypeId(){
		return userTypeId;
	}

	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber(){
		return mobileNumber;
	}

	public void setActive(int active){
		this.active = active;
	}

	public int getActive(){
		return active;
	}

	public void setUserName(Object userName){
		this.userName = userName;
	}

	public Object getUserName(){
		return userName;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCreatedOn(String createdOn){
		this.createdOn = createdOn;
	}

	public String getCreatedOn(){
		return createdOn;
	}

	@Override
 	public String toString(){
		return 
			"DriverSOSNumberResponse{" + 
			"mobiOrder = '" + mobiOrder + '\'' + 
			",userTypeId = '" + userTypeId + '\'' + 
			",mobileNumber = '" + mobileNumber + '\'' + 
			",active = '" + active + '\'' + 
			",userName = '" + userName + '\'' + 
			",userId = '" + userId + '\'' + 
			",id = '" + id + '\'' + 
			",createdOn = '" + createdOn + '\'' + 
			"}";
		}
}