package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class GetCustomerAccountResponce {

	@SerializedName("code")
	private String code;

	@SerializedName("Otp")
	private String otp;

	@SerializedName("AccountType")
	private String accountType;

	@SerializedName("OtpVerfied")
	private String otpVerfied;

	@SerializedName("AccountNumber")
	private String accountNumber;

	@SerializedName("ExpMonth")
	private String expMonth;

	@SerializedName("Name")
	private String name;

	@SerializedName("HolderName")
	private String holderName;

	@SerializedName("Type")
	private String type;

	@SerializedName("UserTypeId")
	private String userTypeId;

	@SerializedName("UserId")
	private String userId;

	@SerializedName("AccountCode")
	private String accountCode;

	@SerializedName("IsPrimary")
	private String isPrimary;

	@SerializedName("UpdatedOn")
	private String updatedOn;

	@SerializedName("Country")
	private String country;

	@SerializedName("Id")
	private String id;

	@SerializedName("IsVerified")
	private String isVerified;

	@SerializedName("CreatedOn")
	private String createdOn;

	@SerializedName("ExpYear")
	private String expYear;

	@SerializedName("paymenttype")
	private String paymenttype;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setOtp(String otp){
		this.otp = otp;
	}

	public String getOtp(){
		return otp;
	}

	public void setAccountType(String accountType){
		this.accountType = accountType;
	}

	public String getAccountType(){
		return accountType;
	}

	public void setOtpVerfied(String otpVerfied){
		this.otpVerfied = otpVerfied;
	}

	public String getOtpVerfied(){
		return otpVerfied;
	}

	public void setAccountNumber(String accountNumber){
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber(){
		return accountNumber;
	}

	public void setExpMonth(String expMonth){
		this.expMonth = expMonth;
	}

	public String getExpMonth(){
		return expMonth;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setHolderName(String holderName){
		this.holderName = holderName;
	}

	public String getHolderName(){
		return holderName;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setUserTypeId(String userTypeId){
		this.userTypeId = userTypeId;
	}

	public String getUserTypeId(){
		return userTypeId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setAccountCode(String accountCode){
		this.accountCode = accountCode;
	}

	public String getAccountCode(){
		return accountCode;
	}

	public void setIsPrimary(String isPrimary){
		this.isPrimary = isPrimary;
	}

	public String getIsPrimary(){
		return isPrimary;
	}

	public void setUpdatedOn(String updatedOn){
		this.updatedOn = updatedOn;
	}

	public String getUpdatedOn(){
		return updatedOn;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setIsVerified(String isVerified){
		this.isVerified = isVerified;
	}

	public String getIsVerified(){
		return isVerified;
	}

	public void setCreatedOn(String createdOn){
		this.createdOn = createdOn;
	}

	public String getCreatedOn(){
		return createdOn;
	}

	public void setExpYear(String expYear){
		this.expYear = expYear;
	}

	public String getExpYear(){
		return expYear;
	}

	public void setPaymenttype(String paymenttype){
		this.paymenttype = paymenttype;
	}

	public String getPaymenttype(){
		return paymenttype;
	}

	@Override
 	public String toString(){
		return 
			"GetCustomerAccountResponce{" + 
			"code = '" + code + '\'' + 
			",otp = '" + otp + '\'' + 
			",accountType = '" + accountType + '\'' + 
			",otpVerfied = '" + otpVerfied + '\'' + 
			",accountNumber = '" + accountNumber + '\'' + 
			",expMonth = '" + expMonth + '\'' + 
			",name = '" + name + '\'' + 
			",holderName = '" + holderName + '\'' + 
			",type = '" + type + '\'' + 
			",userTypeId = '" + userTypeId + '\'' + 
			",userId = '" + userId + '\'' + 
			",accountCode = '" + accountCode + '\'' + 
			",isPrimary = '" + isPrimary + '\'' + 
			",updatedOn = '" + updatedOn + '\'' + 
			",country = '" + country + '\'' + 
			",id = '" + id + '\'' + 
			",isVerified = '" + isVerified + '\'' + 
			",createdOn = '" + createdOn + '\'' + 
			",expYear = '" + expYear + '\'' + 
			",paymenttype = '" + paymenttype + '\'' + 
			"}";
		}
}