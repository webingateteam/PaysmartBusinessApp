package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class GetVehicleApprovalResponse{

	@SerializedName("RegistrationNo")
	private String registrationNo;

	@SerializedName("IsApproved")
	private int isApproved;

	public void setRegistrationNo(String registrationNo){
		this.registrationNo = registrationNo;
	}

	public String getRegistrationNo(){
		return registrationNo;
	}

	public void setIsApproved(int isApproved){
		this.isApproved = isApproved;
	}

	public int getIsApproved(){
		return isApproved;
	}

	@Override
 	public String toString(){
		return 
			"GetVehicleApprovalResponse{" + 
			"registrationNo = '" + registrationNo + '\'' + 
			",isApproved = '" + isApproved + '\'' + 
			"}";
		}
}