package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class GetDriverBankingDetailsResponse{

	@SerializedName("BankName")
	private String bankName;

	@SerializedName("CountryId")
	private int countryId;

	@SerializedName("Accountnumber")
	private String accountnumber;

	@SerializedName("QRCode")
	private Object qRCode;

	@SerializedName("IsActive")
	private int isActive;

	@SerializedName("IsPrimary")
	private Object isPrimary;

	@SerializedName("BranchAddress")
	private String branchAddress;

	@SerializedName("Country")
	private String country;

	@SerializedName("BankCode")
	private String bankCode;

	@SerializedName("Id")
	private int id;

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return bankName;
	}

	public void setCountryId(int countryId){
		this.countryId = countryId;
	}

	public int getCountryId(){
		return countryId;
	}

	public void setAccountnumber(String accountnumber){
		this.accountnumber = accountnumber;
	}

	public String getAccountnumber(){
		return accountnumber;
	}

	public void setQRCode(Object qRCode){
		this.qRCode = qRCode;
	}

	public Object getQRCode(){
		return qRCode;
	}

	public void setIsActive(int isActive){
		this.isActive = isActive;
	}

	public int getIsActive(){
		return isActive;
	}

	public void setIsPrimary(Object isPrimary){
		this.isPrimary = isPrimary;
	}

	public Object getIsPrimary(){
		return isPrimary;
	}

	public void setBranchAddress(String branchAddress){
		this.branchAddress = branchAddress;
	}

	public String getBranchAddress(){
		return branchAddress;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}

	public String getBankCode(){
		return bankCode;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"GetDriverBankingDetailsResponse{" + 
			"bankName = '" + bankName + '\'' + 
			",countryId = '" + countryId + '\'' + 
			",accountnumber = '" + accountnumber + '\'' + 
			",qRCode = '" + qRCode + '\'' + 
			",isActive = '" + isActive + '\'' + 
			",isPrimary = '" + isPrimary + '\'' + 
			",branchAddress = '" + branchAddress + '\'' + 
			",country = '" + country + '\'' + 
			",bankCode = '" + bankCode + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}