package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class VehicleDetailsTable3Item {

	@SerializedName("Address")
	private String address;

	@SerializedName("Username")
	private String username;

	@SerializedName("ContactNo1")
	private String contactNo1;

	@SerializedName("FleetOwnerCode")
	private String fleetOwnerCode;

	@SerializedName("CreatedOn")
	private Object createdOn;

	@SerializedName("IsEmailVerified")
	private Object isEmailVerified;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setContactNo1(String contactNo1){
		this.contactNo1 = contactNo1;
	}

	public String getContactNo1(){
		return contactNo1;
	}

	public void setFleetOwnerCode(String fleetOwnerCode){
		this.fleetOwnerCode = fleetOwnerCode;
	}

	public String getFleetOwnerCode(){
		return fleetOwnerCode;
	}

	public void setCreatedOn(Object createdOn){
		this.createdOn = createdOn;
	}

	public Object getCreatedOn(){
		return createdOn;
	}

	public void setIsEmailVerified(Object isEmailVerified){
		this.isEmailVerified = isEmailVerified;
	}

	public Object getIsEmailVerified(){
		return isEmailVerified;
	}

	@Override
 	public String toString(){
		return 
			"VehicleDetailsTable3Item{" +
			"address = '" + address + '\'' + 
			",username = '" + username + '\'' + 
			",contactNo1 = '" + contactNo1 + '\'' + 
			",fleetOwnerCode = '" + fleetOwnerCode + '\'' + 
			",createdOn = '" + createdOn + '\'' + 
			",isEmailVerified = '" + isEmailVerified + '\'' + 
			"}";
		}
}