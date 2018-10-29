package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class UpdateBookingstatusResponse{

	@SerializedName("UpdatedBy")
	private int updatedBy;

	@SerializedName("BNo")
	private String bNo;

	@SerializedName("BookingStatus")
	private String bookingStatus;

	@SerializedName("UpdatedUserId")
	private int updatedUserId;

	public void setUpdatedBy(int updatedBy){
		this.updatedBy = updatedBy;
	}

	public int getUpdatedBy(){
		return updatedBy;
	}

	public void setBNo(String bNo){
		this.bNo = bNo;
	}

	public String getBNo(){
		return bNo;
	}

	public void setBookingStatus(String bookingStatus){
		this.bookingStatus = bookingStatus;
	}

	public String getBookingStatus(){
		return bookingStatus;
	}

	public void setUpdatedUserId(int updatedUserId){
		this.updatedUserId = updatedUserId;
	}

	public int getUpdatedUserId(){
		return updatedUserId;
	}

	@Override
 	public String toString(){
		return 
			"UpdateBookingstatusResponse{" + 
			"updatedBy = '" + updatedBy + '\'' + 
			",bNo = '" + bNo + '\'' + 
			",bookingStatus = '" + bookingStatus + '\'' + 
			",updatedUserId = '" + updatedUserId + '\'' + 
			"}";
		}
}