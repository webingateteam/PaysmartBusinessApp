package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverRateTheRideResponse{

	@SerializedName("BNo")
	private String bNo;

	@SerializedName("DriverRated")
	private int driverRated;

	@SerializedName("DriverPhoneNo")
	private String driverPhoneNo;

	@SerializedName("DriverRating")
	private double driverRating;

	public void setBNo(String bNo){
		this.bNo = bNo;
	}

	public String getBNo(){
		return bNo;
	}

	public void setDriverRated(int driverRated){
		this.driverRated = driverRated;
	}

	public int getDriverRated(){
		return driverRated;
	}

	public void setDriverPhoneNo(String driverPhoneNo){
		this.driverPhoneNo = driverPhoneNo;
	}

	public String getDriverPhoneNo(){
		return driverPhoneNo;
	}

	public void setDriverRating(double driverRating){
		this.driverRating = driverRating;
	}

	public double getDriverRating(){
		return driverRating;
	}

	@Override
 	public String toString(){
		return 
			"DriverRateTheRideResponse{" + 
			"bNo = '" + bNo + '\'' + 
			",driverRated = '" + driverRated + '\'' + 
			",driverPhoneNo = '" + driverPhoneNo + '\'' + 
			",driverRating = '" + driverRating + '\'' + 
			"}";
		}
}