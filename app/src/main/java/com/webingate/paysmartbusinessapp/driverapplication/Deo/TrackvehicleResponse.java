package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class TrackvehicleResponse{

	@SerializedName("Status")
	private String status;

	@SerializedName("BNo")
	private String bNo;

	@SerializedName("DestLongitude")
	private String destLongitude;

	@SerializedName("SrcLatitude")
	private String srcLatitude;

	@SerializedName("Time")
	private String time;

	@SerializedName("EXpirationTime")
	private String eXpirationTime;

	@SerializedName("Date")
	private String date;

	@SerializedName("Vid")
	private String vid;

	@SerializedName("DriverId")
	private String driverId;

	@SerializedName("SrcLongitude")
	private String srcLongitude;

	@SerializedName("BookingId")
	private String bookingId;

	@SerializedName("Id")
	private String id;

	@SerializedName("DestLatitude")
	private String destLatitude;

	@SerializedName("Code")
	private String code;

	@SerializedName("description")
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setBNo(String bNo){
		this.bNo = bNo;
	}

	public String getBNo(){
		return bNo;
	}

	public void setDestLongitude(String destLongitude){
		this.destLongitude = destLongitude;
	}

	public String getDestLongitude(){
		return destLongitude;
	}

	public void setSrcLatitude(String srcLatitude){
		this.srcLatitude = srcLatitude;
	}

	public String getSrcLatitude(){
		return srcLatitude;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setEXpirationTime(String eXpirationTime){
		this.eXpirationTime = eXpirationTime;
	}

	public String getEXpirationTime(){
		return eXpirationTime;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setVid(String vid){
		this.vid = vid;
	}

	public String getVid(){
		return vid;
	}

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}

	public String getDriverId(){
		return driverId;
	}

	public void setSrcLongitude(String srcLongitude){
		this.srcLongitude = srcLongitude;
	}

	public String getSrcLongitude(){
		return srcLongitude;
	}

	public void setBookingId(String bookingId){
		this.bookingId = bookingId;
	}

	public String getBookingId(){
		return bookingId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDestLatitude(String destLatitude){
		this.destLatitude = destLatitude;
	}

	public String getDestLatitude(){
		return destLatitude;
	}

	@Override
 	public String toString(){
		return 
			"TrackvehicleResponse{" + 
			"status = '" + status + '\'' + 
			",bNo = '" + bNo + '\'' + 
			",destLongitude = '" + destLongitude + '\'' + 
			",srcLatitude = '" + srcLatitude + '\'' + 
			",time = '" + time + '\'' + 
			",eXpirationTime = '" + eXpirationTime + '\'' + 
			",date = '" + date + '\'' + 
			",vid = '" + vid + '\'' + 
			",driverId = '" + driverId + '\'' + 
			",srcLongitude = '" + srcLongitude + '\'' + 
			",bookingId = '" + bookingId + '\'' + 
			",id = '" + id + '\'' + 
			",destLatitude = '" + destLatitude + '\'' + 
			"}";
		}
}