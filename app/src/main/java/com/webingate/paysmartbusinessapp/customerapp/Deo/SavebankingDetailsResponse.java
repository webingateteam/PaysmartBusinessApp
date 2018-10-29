package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class SavebankingDetailsResponse{

	@SerializedName("BankName")
	private String bankName;

	@SerializedName("CountryId")
	private int countryId;

	@SerializedName("Accountnumber")
	private String accountnumber;

	@SerializedName("DriverId")
	private int driverId;

	@SerializedName("QRCode")
	private Object qRCode;

	@SerializedName("IsActive")
	private int isActive;

	@SerializedName("BranchAddress")
	private String branchAddress;

	@SerializedName("BankCode")
	private String bankCode;

	@SerializedName("Id")
	private int id;

	@SerializedName("Code")
	private String code;

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

	public void setDriverId(int driverId){
		this.driverId = driverId;
	}

	public int getDriverId(){
		return driverId;
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

	public void setBranchAddress(String branchAddress){
		this.branchAddress = branchAddress;
	}

	public String getBranchAddress(){
		return branchAddress;
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

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	@Override
 	public String toString(){
		return 
			"SavebankingDetailsResponse{" + 
			"bankName = '" + bankName + '\'' + 
			",countryId = '" + countryId + '\'' + 
			",accountnumber = '" + accountnumber + '\'' + 
			",driverId = '" + driverId + '\'' + 
			",qRCode = '" + qRCode + '\'' + 
			",isActive = '" + isActive + '\'' + 
			",branchAddress = '" + branchAddress + '\'' + 
			",bankCode = '" + bankCode + '\'' + 
			",id = '" + id + '\'' + 
			",code = '" + code + '\'' + 
			"}";
		}
}