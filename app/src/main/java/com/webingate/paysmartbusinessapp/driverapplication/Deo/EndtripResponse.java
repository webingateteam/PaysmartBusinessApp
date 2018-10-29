package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class EndtripResponse{

	@SerializedName("BNo")
	private String bNo;

	@SerializedName("DriverPhoneNo")
	private String driverPhoneNo;

	@SerializedName("Amount")
	private String amount;

	@SerializedName("BookingStatus")
	private String bookingStatus;

	@SerializedName("PaymentTypeId")
	private String paymentTypeId;

	public void setBNo(String bNo){
		this.bNo = bNo;
	}

	public String getBNo(){
		return bNo;
	}

	public void setDriverPhoneNo(String driverPhoneNo){
		this.driverPhoneNo = driverPhoneNo;
	}

	public String getDriverPhoneNo(){
		return driverPhoneNo;
	}

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
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

	@Override
 	public String toString(){
		return 
			"EndtripResponse{" + 
			"bNo = '" + bNo + '\'' + 
			",driverPhoneNo = '" + driverPhoneNo + '\'' + 
			",amount = '" + amount + '\'' + 
			",bookingStatus = '" + bookingStatus + '\'' + 
			",paymentTypeId = '" + paymentTypeId + '\'' + 
			"}";
		}
}