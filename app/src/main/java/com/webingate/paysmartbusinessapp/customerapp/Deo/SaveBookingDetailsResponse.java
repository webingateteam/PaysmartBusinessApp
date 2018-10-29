package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class SaveBookingDetailsResponse{

	@SerializedName("bookingNumber")
	private String bookingNumber;

	public void setBookingNumber(String bookingNumber){
		this.bookingNumber = bookingNumber;
	}

	public String getBookingNumber(){
		return bookingNumber;
	}

	@Override
 	public String toString(){
		return 
			"SaveBookingDetailsResponse{" + 
			"bookingNumber = '" + bookingNumber + '\'' + 
			"}";
		}
}