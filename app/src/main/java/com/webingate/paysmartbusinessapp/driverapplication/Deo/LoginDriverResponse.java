package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class LoginDriverResponse{

	@SerializedName("LogoutTime")
	private String logoutTime;

	@SerializedName("Status")
	private String status;

	@SerializedName("LoginTime")
	private String loginTime;

	@SerializedName("LoginDate")
	private String loginDate;

	@SerializedName("LogoutDate")
	private String logoutDate;

	@SerializedName("LoginLatitude")
	private int loginLatitude;

	@SerializedName("Reason")
	private String reason;

	@SerializedName("VId")
	private int vId;

	@SerializedName("LogoutLatitude")
	private double logoutLatitude;

	@SerializedName("LoginLongitude")
	private int loginLongitude;

	@SerializedName("Id")
	private int id;

	@SerializedName("DId")
	private int dId;

	@SerializedName("LogoutLongitude")
	private double logoutLongitude;

	public void setLogoutTime(String logoutTime){
		this.logoutTime = logoutTime;
	}

	public String getLogoutTime(){
		return logoutTime;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setLoginTime(String loginTime){
		this.loginTime = loginTime;
	}

	public String getLoginTime(){
		return loginTime;
	}

	public void setLoginDate(String loginDate){
		this.loginDate = loginDate;
	}

	public String getLoginDate(){
		return loginDate;
	}

	public void setLogoutDate(String logoutDate){
		this.logoutDate = logoutDate;
	}

	public String getLogoutDate(){
		return logoutDate;
	}

	public void setLoginLatitude(int loginLatitude){
		this.loginLatitude = loginLatitude;
	}

	public int getLoginLatitude(){
		return loginLatitude;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return reason;
	}

	public void setVId(int vId){
		this.vId = vId;
	}

	public int getVId(){
		return vId;
	}

	public void setLogoutLatitude(double logoutLatitude){
		this.logoutLatitude = logoutLatitude;
	}

	public double getLogoutLatitude(){
		return logoutLatitude;
	}

	public void setLoginLongitude(int loginLongitude){
		this.loginLongitude = loginLongitude;
	}

	public int getLoginLongitude(){
		return loginLongitude;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDId(int dId){
		this.dId = dId;
	}

	public int getDId(){
		return dId;
	}

	public void setLogoutLongitude(double logoutLongitude){
		this.logoutLongitude = logoutLongitude;
	}

	public double getLogoutLongitude(){
		return logoutLongitude;
	}

	@Override
 	public String toString(){
		return 
			"LoginDriverResponse{" + 
			"logoutTime = '" + logoutTime + '\'' + 
			",status = '" + status + '\'' + 
			",loginTime = '" + loginTime + '\'' + 
			",loginDate = '" + loginDate + '\'' + 
			",logoutDate = '" + logoutDate + '\'' + 
			",loginLatitude = '" + loginLatitude + '\'' + 
			",reason = '" + reason + '\'' + 
			",vId = '" + vId + '\'' + 
			",logoutLatitude = '" + logoutLatitude + '\'' + 
			",loginLongitude = '" + loginLongitude + '\'' + 
			",id = '" + id + '\'' + 
			",dId = '" + dId + '\'' + 
			",logoutLongitude = '" + logoutLongitude + '\'' + 
			"}";
		}
}