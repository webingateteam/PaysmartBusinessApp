package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class WalletBalanceResponse{

	@SerializedName("Amount")
	private String amount;

	@SerializedName("StatusId")
	private String statusId;

	@SerializedName("Mobilenumber")
	private String mobilenumber;

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}

	public void setStatusId(String statusId){
		this.statusId = statusId;
	}

	public String getStatusId(){
		return statusId;
	}

	public void setMobilenumber(String mobilenumber){
		this.mobilenumber = mobilenumber;
	}

	public String getMobilenumber(){
		return mobilenumber;
	}

	@Override
 	public String toString(){
		return 
			"WalletBalanceResponse{" + 
			"amount = '" + amount + '\'' + 
			",statusId = '" + statusId + '\'' + 
			",mobilenumber = '" + mobilenumber + '\'' + 
			"}";
		}
}