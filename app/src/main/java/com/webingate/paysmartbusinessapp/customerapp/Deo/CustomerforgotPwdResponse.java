package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class CustomerforgotPwdResponse{

	@SerializedName("Passwordotp")
	private String passwordotp;

	@SerializedName("Code")
	private String code;

	@SerializedName("description")
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPasswordotp(String passwordotp){
		this.passwordotp = passwordotp;
	}

	public String getPasswordotp(){
		return passwordotp;
	}

	@Override
 	public String toString(){
		return 
			"CustomerforgotPwdResponse{" + 
			"passwordotp = '" + passwordotp + '\'' + 
			"}";
		}
}