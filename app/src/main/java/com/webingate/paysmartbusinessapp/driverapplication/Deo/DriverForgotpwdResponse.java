package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverForgotpwdResponse{

	@SerializedName("Passwordotp")
	private String passwordotp;

	public void setPasswordotp(String passwordotp){
		this.passwordotp = passwordotp;
	}

	public String getPasswordotp(){
		return passwordotp;
	}

	@Override
 	public String toString(){
		return 
			"DriverForgotpwdResponse{" + 
			"passwordotp = '" + passwordotp + '\'' + 
			"}";
		}
}