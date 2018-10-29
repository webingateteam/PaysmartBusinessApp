package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class SOSMessagePostResponse{

	@SerializedName("Message")
	private String message;

	@SerializedName("UserId")
	private int userId;

	@SerializedName("Otp")
	private String otp;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setOtp(String otp){
		this.otp = otp;
	}

	public String getOtp(){
		return otp;
	}

	@Override
 	public String toString(){
		return 
			"SOSMessagePostResponse{" + 
			"message = '" + message + '\'' + 
			",userId = '" + userId + '\'' + 
			",otp = '" + otp + '\'' + 
			"}";
		}
}