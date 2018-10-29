package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class UpdateUserResponse{

	@SerializedName("Mobileotp")
	private String mobileotp;

	@SerializedName("Email")
	private String email;

	@SerializedName("AccountNo")
	private Object accountNo;

	@SerializedName("Amount")
	private int amount;

	@SerializedName("Gender")
	private int gender;

	@SerializedName("AuthTypeId")
	private int authTypeId;

	@SerializedName("lastname")
	private String lastname;

	@SerializedName("Firstname")
	private String firstname;

	@SerializedName("AltPhonenumber")
	private String altPhonenumber;

	@SerializedName("UserPhoto")
	private String userPhoto;

	@SerializedName("Altemail")
	private String altemail;

	@SerializedName("Username")
	private String username;

	@SerializedName("Emailotp")
	private String emailotp;

	@SerializedName("Id")
	private int id;

	@SerializedName("Mobilenumber")
	private String mobilenumber;

	@SerializedName("Password")
	private String password;

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

	public void setAccountNo(Object accountNo){
		this.accountNo = accountNo;
	}

	public Object getAccountNo(){
		return accountNo;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setGender(int gender){
		this.gender = gender;
	}

	public int getGender(){
		return gender;
	}

	public void setAuthTypeId(int authTypeId){
		this.authTypeId = authTypeId;
	}

	public int getAuthTypeId(){
		return authTypeId;
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

	public void setUserPhoto(String userPhoto){
		this.userPhoto = userPhoto;
	}

	public String getUserPhoto(){
		return userPhoto;
	}

	public void setAltemail(String altemail){
		this.altemail = altemail;
	}

	public String getAltemail(){
		return altemail;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setEmailotp(String emailotp){
		this.emailotp = emailotp;
	}

	public String getEmailotp(){
		return emailotp;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setMobilenumber(String mobilenumber){
		this.mobilenumber = mobilenumber;
	}

	public String getMobilenumber(){
		return mobilenumber;
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
			"UpdateUserResponse{" + 
			"mobileotp = '" + mobileotp + '\'' + 
			",email = '" + email + '\'' + 
			",accountNo = '" + accountNo + '\'' + 
			",amount = '" + amount + '\'' + 
			",gender = '" + gender + '\'' + 
			",authTypeId = '" + authTypeId + '\'' + 
			",lastname = '" + lastname + '\'' + 
			",firstname = '" + firstname + '\'' + 
			",altPhonenumber = '" + altPhonenumber + '\'' + 
			",userPhoto = '" + userPhoto + '\'' + 
			",altemail = '" + altemail + '\'' + 
			",username = '" + username + '\'' + 
			",emailotp = '" + emailotp + '\'' + 
			",id = '" + id + '\'' + 
			",mobilenumber = '" + mobilenumber + '\'' + 
			",password = '" + password + '\'' + 
			"}";
		}
}