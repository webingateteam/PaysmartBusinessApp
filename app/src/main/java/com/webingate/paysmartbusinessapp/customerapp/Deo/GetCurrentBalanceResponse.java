package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class GetCurrentBalanceResponse{

	@SerializedName("Amount")
	private String amount;

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}

	@Override
 	public String toString(){
		return 
			"GetCurrentBalanceResponse{" + 
			"amount = '" + amount + '\'' + 
			"}";
		}
}