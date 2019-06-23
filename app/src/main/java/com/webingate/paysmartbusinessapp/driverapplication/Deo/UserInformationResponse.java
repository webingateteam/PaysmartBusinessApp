package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class UserInformationResponse {

	@SerializedName("Username")
	private String Username;

	@SerializedName("Email")
	private String Email;

	@SerializedName("Mobilenumber")
	private String Mobilenumber;

	@SerializedName("Password")
	private String Password;

	@SerializedName("Firstname")
	private String Firstname;

	@SerializedName("lastname")
	private String lastname;

	@SerializedName("UserPhoto")
	private String UserPhoto;

	@SerializedName("CountryId")
	private String CountryId;

	@SerializedName("PaymentModeId")
	private String PaymentModeId;

	@SerializedName("UserAccountNo")
	private String UserAccountNo;

	@SerializedName("Code")
	private String code;

	@SerializedName("Description")
	private String description;


	public void setUsername(String Username){
		this.Username = Username;
	}

	public String getUsername(){
		return Username;
	}

	public void setEmail(String Email){
		this.Email = Email;
	}

	public String getEmail(){
		return Email;
	}

	public void setMobilenumber(String Mobilenumber){
		this.Mobilenumber = Mobilenumber;
	}

	public String getMobilenumber(){
		return Mobilenumber;
	}

	public void setPassword(String Password){
		this.Password = Password;
	}

	public String getPassword(){
		return Password;
	}

	public void setFirstname(String Firstname){
		this.Firstname = Firstname;
	}

	public String getFirstname(){
		return Firstname;
	}

	public void setlastname(String lastname){
		this.lastname = lastname;
	}

	public String getlastname(){
		return lastname;
	}

	public void setUserPhoto(String UserPhoto){
		this.UserPhoto = UserPhoto;
	}

	public String getUserPhoto(){
		return UserPhoto;
	}

	public void setCountryId(String CountryId){
		this.CountryId = CountryId;
	}

	public String getCountryId(){
		return CountryId;
	}

	public void setPaymentModeId(String PaymentModeId){
		this.PaymentModeId = PaymentModeId;
	}

	public String getPaymentModeId(){
		return PaymentModeId;
	}

	public void setUserAccountNo(String UserAccountNo){
		this.UserAccountNo = UserAccountNo;
	}

	public String getUserAccountNo(){
		return UserAccountNo;
	}

	public void setCode(String Code){
		this.code = Code;
	}

	public String getCode(){
		return code;
	}

	public void setDescription(String Description){
		this.description = Description;
	}

	public String getDescription(){
		return description;
	}



	@Override
 	public String toString(){
		return
			"UserInformationResponse{" +
			"UserName = '" + Username + '\'' +
			"Email = '" + Email + '\'' +
			"Mobilenumber = '" + Mobilenumber + '\''+
			"Password = '" + Password + '\''	+
			"Firstname = '" + Firstname + '\''	+
			"lastname = '" + lastname + '\''	+
			"UserPhoto = '" + UserPhoto + '\''	+
			"CountryId = '" + CountryId + '\''	+
			"PaymentModeId = '" + PaymentModeId + '\'' +
			"UserAccountNo = '" + UserAccountNo + '\'' +
			"}";
		}
}