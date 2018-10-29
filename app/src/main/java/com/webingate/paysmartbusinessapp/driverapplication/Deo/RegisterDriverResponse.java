package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class RegisterDriverResponse{

	@SerializedName("Status")
	private String status;

	@SerializedName("Mobileotp")
	private String mobileotp;

	@SerializedName("Email")
	private String email;

	@SerializedName("PMobNo")
	private String pMobNo;

	@SerializedName("Passwordotp")
	private String passwordotp;

	@SerializedName("AccountNo")
	private String accountNo;

	@SerializedName("VehicleId")
	private String vehicleId;

	@SerializedName("noofattempts")
	private String noofattempts;

	@SerializedName("AuthTypeId")
	private String authTypeId;

	@SerializedName("Name")
	private String name;

	@SerializedName("lastname")
	private String lastname;

	@SerializedName("Firstname")
	private String firstname;

	@SerializedName("AltPhonenumber")
	private String altPhonenumber;

	@SerializedName("Mobileotpsenton")
	private String mobileotpsenton;

	@SerializedName("Altemail")
	private String altemail;

	@SerializedName("DriverId")
	private String driverId;

	@SerializedName("Emailotp")
	private String emailotp;

	@SerializedName("emailotpsenton")
	private String emailotpsenton;

	@SerializedName("CreatedOn")
	private String createdOn;

	@SerializedName("Password")
	private String password;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setMobileotp(String mobileotp){
		this.mobileotp = mobileotp;
	}

	public String getMobileotp(){
		return mobileotp;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setPMobNo(String pMobNo){
		this.pMobNo = pMobNo;
	}

	public String getPMobNo(){
		return pMobNo;
	}

	public void setPasswordotp(String passwordotp){
		this.passwordotp = passwordotp;
	}

	public String getPasswordotp(){
		return passwordotp;
	}

	public void setAccountNo(String accountNo){
		this.accountNo = accountNo;
	}

	public String getAccountNo(){
		return accountNo;
	}

	public void setVehicleId(String vehicleId){
		this.vehicleId = vehicleId;
	}

	public String getVehicleId(){
		return vehicleId;
	}

	public void setNoofattempts(String noofattempts){
		this.noofattempts = noofattempts;
	}

	public String getNoofattempts(){
		return noofattempts;
	}

	public void setAuthTypeId(String authTypeId){
		this.authTypeId = authTypeId;
	}

	public String getAuthTypeId(){
		return authTypeId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setAltPhonenumber(String altPhonenumber){
		this.altPhonenumber = altPhonenumber;
	}

	public String getAltPhonenumber(){
		return altPhonenumber;
	}

	public void setMobileotpsenton(String mobileotpsenton){
		this.mobileotpsenton = mobileotpsenton;
	}

	public String getMobileotpsenton(){
		return mobileotpsenton;
	}

	public void setAltemail(String altemail){
		this.altemail = altemail;
	}

	public String getAltemail(){
		return altemail;
	}

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}

	public String getDriverId(){
		return driverId;
	}

	public void setEmailotp(String emailotp){
		this.emailotp = emailotp;
	}

	public String getEmailotp(){
		return emailotp;
	}

	public void setEmailotpsenton(String emailotpsenton){
		this.emailotpsenton = emailotpsenton;
	}

	public String getEmailotpsenton(){
		return emailotpsenton;
	}

	public void setCreatedOn(String createdOn){
		this.createdOn = createdOn;
	}

	public String getCreatedOn(){
		return createdOn;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	@Override
 	public String toString(){
		return 
			"RegisterDriverResponse{" + 
			"status = '" + status + '\'' + 
			",mobileotp = '" + mobileotp + '\'' + 
			",email = '" + email + '\'' + 
			",pMobNo = '" + pMobNo + '\'' + 
			",passwordotp = '" + passwordotp + '\'' + 
			",accountNo = '" + accountNo + '\'' + 
			",vehicleId = '" + vehicleId + '\'' + 
			",noofattempts = '" + noofattempts + '\'' + 
			",authTypeId = '" + authTypeId + '\'' + 
			",name = '" + name + '\'' + 
			",lastname = '" + lastname + '\'' + 
			",firstname = '" + firstname + '\'' + 
			",altPhonenumber = '" + altPhonenumber + '\'' + 
			",mobileotpsenton = '" + mobileotpsenton + '\'' + 
			",altemail = '" + altemail + '\'' + 
			",driverId = '" + driverId + '\'' + 
			",emailotp = '" + emailotp + '\'' + 
			",emailotpsenton = '" + emailotpsenton + '\'' + 
			",createdOn = '" + createdOn + '\'' + 
			",password = '" + password + '\'' + 
			"}";
		}
}