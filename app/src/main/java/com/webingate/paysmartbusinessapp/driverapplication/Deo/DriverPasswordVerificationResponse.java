package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverPasswordVerificationResponse{

	@SerializedName("Email")
	private String email;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	@SerializedName("Code")
	private String Code;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@SerializedName("Description")
	private String  Description;

	@SerializedName("Username")
	private String Username;

	@SerializedName("MobileNumber")
	private String MobileNumber;

	@SerializedName("UserAccountNo")
	private String UserAccountNo;

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String Username){
		this.Username = Username;
	}

	public String getUsername(){
		return Username;
	}

	public void setMobileNumber(String MobileNumber){
		this.MobileNumber = MobileNumber;
	}

	public String getMobileNumber(){
		return MobileNumber;
	}

	public void setUserAccountNo(String UserAccountNo){
		this.UserAccountNo = UserAccountNo;
	}

	public String getUserAccountNo(){
		return UserAccountNo;
	}

	@Override
 	public String toString(){
		return 
			"DriverPasswordVerificationResponse{" + 
			"Email = '" + email + '\'' +
			",Username = '" + Username + '\'' +
			",MobileNumber = '" + MobileNumber + '\'' +
			",UserAccountNo = '" + UserAccountNo + '\'' +
			"}";
		}
}