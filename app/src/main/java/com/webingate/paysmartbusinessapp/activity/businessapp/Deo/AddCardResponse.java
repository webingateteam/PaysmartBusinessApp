package com.webingate.paysmartbusinessapp.activity.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class AddCardResponse {

	@SerializedName("Customer")
	private String Customer;
	public String getCustomer(){return Customer;}

	@Override
 	public String toString(){
		return 
			"AddCardResponse{" +
					",Customer = '" + Customer + '\'' +
					"}";
		}

}