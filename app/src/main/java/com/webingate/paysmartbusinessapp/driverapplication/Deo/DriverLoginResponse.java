package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverLoginResponse{

	@SerializedName("status")
	private String status;

	@SerializedName("Code")
	private String Code;

	@SerializedName("description")
	private String description;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
	public String getCode(){
		return Code;
	}
	public String getdescription(){
		return description;
	}

	@Override
 	public String toString(){
		return
			"DriverLoginResponse{" +
			"status = '" + status + '\'' +
			"}";
		}
}