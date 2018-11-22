package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class BusinessResendOTPResponse {

	@SerializedName("UserAccountNo")
	private String UserAccountNo;

	@SerializedName("Email")
	private String Email;

	@SerializedName("Emailotp")
	private String Emailotp;

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

	public void setEmailotp(String Emailotp){
		this.Emailotp = Emailotp;
	}

	public String getEmailotpp(){
		return Emailotp;
	}

	public void setUserAccountNo(String UserAccountNo){
		this.UserAccountNo = UserAccountNo;
	}
	public String getUserAccountNo(){
		return UserAccountNo;
	}

	public void setEmail(String UserAccountNo){
		this.Email = Email;
	}
	public String getEmail(){
		return Email;
	}

	@Override
 	public String toString(){
		return 
			"BusinessResendOTPResponse{" +
			"Emailotp = '" + Emailotp + '\'' +
			",UserAccountNo = '" + UserAccountNo + '\''	+
			",Email = '" + Email + '\''+

			"}";
		}
}