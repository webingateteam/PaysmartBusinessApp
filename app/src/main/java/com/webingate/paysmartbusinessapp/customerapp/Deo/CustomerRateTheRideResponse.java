package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class CustomerRateTheRideResponse{

	@SerializedName("BNo")
	private String bNo;

	@SerializedName("CustomerPhoneNo")
	private String customerPhoneNo;

	@SerializedName("Rating")
	private double rating;

	@SerializedName("RatedBy")
	private int ratedBy;

	public void setBNo(String bNo){
		this.bNo = bNo;
	}

	public String getBNo(){
		return bNo;
	}

	public void setCustomerPhoneNo(String customerPhoneNo){
		this.customerPhoneNo = customerPhoneNo;
	}

	public String getCustomerPhoneNo(){
		return customerPhoneNo;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setRatedBy(int ratedBy){
		this.ratedBy = ratedBy;
	}

	public int getRatedBy(){
		return ratedBy;
	}

	@Override
 	public String toString(){
		return 
			"CustomerRateTheRideResponse{" + 
			"bNo = '" + bNo + '\'' + 
			",customerPhoneNo = '" + customerPhoneNo + '\'' + 
			",rating = '" + rating + '\'' + 
			",ratedBy = '" + ratedBy + '\'' + 
			"}";
		}
}