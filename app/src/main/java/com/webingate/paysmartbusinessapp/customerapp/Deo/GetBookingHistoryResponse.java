package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetBookingHistoryResponse implements Serializable{

	@SerializedName("DepartureTime")
	private String departureTime;

	@SerializedName("DestLongitude")
	private String destLongitude;

	@SerializedName("CancelledBy")
	private String cancelledBy;

	@SerializedName("BookedDate")
	private String bookedDate;

	@SerializedName("Src")
	private String src;

	@SerializedName("SrcLatitude")
	private String srcLatitude;

	@SerializedName("Rating")
	private String rating;

	@SerializedName("RatedBy")
	private String ratedBy;

	@SerializedName("VechId")
	private String vechId;

	@SerializedName("NoofVehicles")
	private String noofVehicles;

	@SerializedName("UpdatedBy")
	private String updatedBy;

	@SerializedName("Pricing")
	private String pricing;

	@SerializedName("BookingStatus")
	private String bookingStatus;

	@SerializedName("DepartueDate")
	private String departueDate;

	@SerializedName("CancelledOn")
	private String cancelledOn;

	@SerializedName("BookingType")
	private String bookingType;

	@SerializedName("PaymentStatus")
	private String paymentStatus;

	@SerializedName("BNo")
	private String bNo;

	@SerializedName("Dest")
	private String dest;

	@SerializedName("CustomerPhoneNo")
	private String customerPhoneNo;

	@SerializedName("ClosingTime")
	private String closingTime;

	@SerializedName("CompanyId")
	private String companyId;

	@SerializedName("Comments")
	private String comments;

	@SerializedName("Amount")
	private String amount;

	@SerializedName("NoofSeats")
	private String noofSeats;

	@SerializedName("Reasons")
	private String reasons;

	@SerializedName("CustomerId")
	private String customerId;

	@SerializedName("BookedTime")
	private String bookedTime;

	@SerializedName("SrcLongitude")
	private String srcLongitude;

	@SerializedName("DriverId")
	private String driverId;

	@SerializedName("DestId")
	private String destId;

	@SerializedName("BookingChannel")
	private String bookingChannel;

	@SerializedName("DriverPhoneNo")
	private String driverPhoneNo;

	@SerializedName("SrcId")
	private String srcId;

	@SerializedName("PackageId")
	private String packageId;

	@SerializedName("ClosingDate")
	private String closingDate;

	@SerializedName("UpdatedUserId")
	private String updatedUserId;

	@SerializedName("Id")
	private String id;

	@SerializedName("DestLatitude")
	private String destLatitude;

	public void setDepartureTime(String departureTime){
		this.departureTime = departureTime;
	}

	public String getDepartureTime(){
		return departureTime;
	}

	public void setDestLongitude(String destLongitude){
		this.destLongitude = destLongitude;
	}

	public String getDestLongitude(){
		return destLongitude;
	}

	public void setCancelledBy(String cancelledBy){
		this.cancelledBy = cancelledBy;
	}

	public String getCancelledBy(){
		return cancelledBy;
	}

	public void setBookedDate(String bookedDate){
		this.bookedDate = bookedDate;
	}

	public String getBookedDate(){
		return bookedDate;
	}

	public void setSrc(String src){
		this.src = src;
	}

	public String getSrc(){
		return src;
	}

	public void setSrcLatitude(String srcLatitude){
		this.srcLatitude = srcLatitude;
	}

	public String getSrcLatitude(){
		return srcLatitude;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setRatedBy(String ratedBy){
		this.ratedBy = ratedBy;
	}

	public String getRatedBy(){
		return ratedBy;
	}

	public void setVechId(String vechId){
		this.vechId = vechId;
	}

	public String getVechId(){
		return vechId;
	}

	public void setNoofVehicles(String noofVehicles){
		this.noofVehicles = noofVehicles;
	}

	public String getNoofVehicles(){
		return noofVehicles;
	}

	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy(){
		return updatedBy;
	}

	public void setPricing(String pricing){
		this.pricing = pricing;
	}

	public String getPricing(){
		return pricing;
	}

	public void setBookingStatus(String bookingStatus){
		this.bookingStatus = bookingStatus;
	}

	public String getBookingStatus(){
		return bookingStatus;
	}

	public void setDepartueDate(String departueDate){
		this.departueDate = departueDate;
	}

	public String getDepartueDate(){
		return departueDate;
	}

	public void setCancelledOn(String cancelledOn){
		this.cancelledOn = cancelledOn;
	}

	public String getCancelledOn(){
		return cancelledOn;
	}

	public void setBookingType(String bookingType){
		this.bookingType = bookingType;
	}

	public String getBookingType(){
		return bookingType;
	}

	public void setPaymentStatus(String paymentStatus){
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentStatus(){
		return paymentStatus;
	}

	public void setBNo(String bNo){
		this.bNo = bNo;
	}

	public String getBNo(){
		return bNo;
	}

	public void setDest(String dest){
		this.dest = dest;
	}

	public String getDest(){
		return dest;
	}

	public void setCustomerPhoneNo(String customerPhoneNo){
		this.customerPhoneNo = customerPhoneNo;
	}

	public String getCustomerPhoneNo(){
		return customerPhoneNo;
	}

	public void setClosingTime(String closingTime){
		this.closingTime = closingTime;
	}

	public String getClosingTime(){
		return closingTime;
	}

	public void setCompanyId(String companyId){
		this.companyId = companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public void setComments(String comments){
		this.comments = comments;
	}

	public String getComments(){
		return comments;
	}

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}

	public void setNoofSeats(String noofSeats){
		this.noofSeats = noofSeats;
	}

	public String getNoofSeats(){
		return noofSeats;
	}

	public void setReasons(String reasons){
		this.reasons = reasons;
	}

	public String getReasons(){
		return reasons;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setBookedTime(String bookedTime){
		this.bookedTime = bookedTime;
	}

	public String getBookedTime(){
		return bookedTime;
	}

	public void setSrcLongitude(String srcLongitude){
		this.srcLongitude = srcLongitude;
	}

	public String getSrcLongitude(){
		return srcLongitude;
	}

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}

	public String getDriverId(){
		return driverId;
	}

	public void setDestId(String destId){
		this.destId = destId;
	}

	public String getDestId(){
		return destId;
	}

	public void setBookingChannel(String bookingChannel){
		this.bookingChannel = bookingChannel;
	}

	public String getBookingChannel(){
		return bookingChannel;
	}

	public void setDriverPhoneNo(String driverPhoneNo){
		this.driverPhoneNo = driverPhoneNo;
	}

	public String getDriverPhoneNo(){
		return driverPhoneNo;
	}

	public void setSrcId(String srcId){
		this.srcId = srcId;
	}

	public String getSrcId(){
		return srcId;
	}

	public void setPackageId(String packageId){
		this.packageId = packageId;
	}

	public String getPackageId(){
		return packageId;
	}

	public void setClosingDate(String closingDate){
		this.closingDate = closingDate;
	}

	public String getClosingDate(){
		return closingDate;
	}

	public void setUpdatedUserId(String updatedUserId){
		this.updatedUserId = updatedUserId;
	}

	public String getUpdatedUserId(){
		return updatedUserId;
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
			"GetBookingHistoryResponse{" + 
			"departureTime = '" + departureTime + '\'' + 
			",destLongitude = '" + destLongitude + '\'' + 
			",cancelledBy = '" + cancelledBy + '\'' + 
			",bookedDate = '" + bookedDate + '\'' + 
			",src = '" + src + '\'' + 
			",srcLatitude = '" + srcLatitude + '\'' + 
			",rating = '" + rating + '\'' + 
			",ratedBy = '" + ratedBy + '\'' + 
			",vechId = '" + vechId + '\'' + 
			",noofVehicles = '" + noofVehicles + '\'' + 
			",updatedBy = '" + updatedBy + '\'' + 
			",pricing = '" + pricing + '\'' + 
			",bookingStatus = '" + bookingStatus + '\'' + 
			",departueDate = '" + departueDate + '\'' + 
			",cancelledOn = '" + cancelledOn + '\'' + 
			",bookingType = '" + bookingType + '\'' + 
			",paymentStatus = '" + paymentStatus + '\'' + 
			",bNo = '" + bNo + '\'' + 
			",dest = '" + dest + '\'' + 
			",customerPhoneNo = '" + customerPhoneNo + '\'' + 
			",closingTime = '" + closingTime + '\'' + 
			",companyId = '" + companyId + '\'' + 
			",comments = '" + comments + '\'' + 
			",amount = '" + amount + '\'' + 
			",noofSeats = '" + noofSeats + '\'' + 
			",reasons = '" + reasons + '\'' + 
			",customerId = '" + customerId + '\'' + 
			",bookedTime = '" + bookedTime + '\'' + 
			",srcLongitude = '" + srcLongitude + '\'' + 
			",driverId = '" + driverId + '\'' + 
			",destId = '" + destId + '\'' + 
			",bookingChannel = '" + bookingChannel + '\'' + 
			",driverPhoneNo = '" + driverPhoneNo + '\'' + 
			",srcId = '" + srcId + '\'' + 
			",packageId = '" + packageId + '\'' + 
			",closingDate = '" + closingDate + '\'' + 
			",updatedUserId = '" + updatedUserId + '\'' + 
			",id = '" + id + '\'' + 
			",destLatitude = '" + destLatitude + '\'' + 
			"}";
		}
}