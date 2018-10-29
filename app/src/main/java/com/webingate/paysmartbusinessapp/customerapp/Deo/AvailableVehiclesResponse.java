package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class AvailableVehiclesResponse{

	@SerializedName("VehicleGroupId")
	private int vehicleGroupId;

	@SerializedName("Status")
	private String status;

	@SerializedName("RegistrationNo")
	private String registrationNo;

	@SerializedName("Latitude")
	private double latitude;

	@SerializedName("Longitude")
	private double longitude;

	public void setVehicleGroupId(int vehicleGroupId){
		this.vehicleGroupId = vehicleGroupId;
	}

	public int getVehicleGroupId(){
		return vehicleGroupId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setRegistrationNo(String registrationNo){
		this.registrationNo = registrationNo;
	}

	public String getRegistrationNo(){
		return registrationNo;
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

	@Override
 	public String toString(){
		return 
			"AvailableVehiclesResponse{" + 
			"vehicleGroupId = '" + vehicleGroupId + '\'' + 
			",status = '" + status + '\'' + 
			",registrationNo = '" + registrationNo + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}