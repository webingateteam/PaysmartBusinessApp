package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class CalculatePriceResponse{

	@SerializedName("Price")
	private int Price;

	public void setPrice(int Price){
		this.Price = Price;
	}

	public int getPrice(){
		return Price;
	}
}