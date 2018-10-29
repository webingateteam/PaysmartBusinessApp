package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class VehiclePositionResponse{

	@SerializedName("Amount")
	private String amount;

	@SerializedName("Latitude")
	private double latitude;

	@SerializedName("BookingStatus")
	private String bookingStatus;

	@SerializedName("PaymentTypeId")
	private String paymentTypeId;

	@SerializedName("Longitude")
	private double longitude;

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setBookingStatus(String bookingStatus){
		this.bookingStatus = bookingStatus;
	}

	public String getBookingStatus(){
		return bookingStatus;
	}

	public void setPaymentTypeId(String paymentTypeId){
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentTypeId(){
		return paymentTypeId;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}


}