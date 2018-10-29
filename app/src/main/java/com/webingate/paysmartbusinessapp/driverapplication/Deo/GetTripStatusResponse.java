package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class GetTripStatusResponse{

	@SerializedName("DepartureTime")
	private String departureTime;

	@SerializedName("DriverRated")
	private Object driverRated;

	@SerializedName("DestLongitude")
	private double destLongitude;

	@SerializedName("CancelledBy")
	private String cancelledBy;

	@SerializedName("BookedDate")
	private String bookedDate;

	@SerializedName("Src")
	private String src;

	@SerializedName("SrcLatitude")
	private double srcLatitude;

	@SerializedName("Rating")
	private Object rating;

	@SerializedName("RatedBy")
	private Object ratedBy;

	@SerializedName("DriverRating")
	private Object driverRating;

	@SerializedName("BooKingOTP")
	private Object booKingOTP;

	@SerializedName("VechId")
	private int vechId;

	@SerializedName("NoofVehicles")
	private int noofVehicles;

	@SerializedName("UpdatedBy")
	private Object updatedBy;

	@SerializedName("Pricing")
	private int pricing;

	@SerializedName("BookingStatus")
	private String bookingStatus;

	@SerializedName("DepartueDate")
	private Object departueDate;

	@SerializedName("CancelledOn")
	private Object cancelledOn;

	@SerializedName("BookingType")
	private String bookingType;

	@SerializedName("PaymentStatus")
	private Object paymentStatus;

	@SerializedName("Distance")
	private Object distance;

	@SerializedName("TripCount")
	private Object tripCount;

	@SerializedName("BNo")
	private String bNo;

	@SerializedName("Dest")
	private String dest;

	@SerializedName("CustomerPhoneNo")
	private String customerPhoneNo;

	@SerializedName("ClosingTime")
	private Object closingTime;

	@SerializedName("CompanyId")
	private int companyId;

	@SerializedName("Comments")
	private Object comments;

	@SerializedName("Amount")
	private int amount;

	@SerializedName("NoofSeats")
	private int noofSeats;

	@SerializedName("Reasons")
	private String reasons;

	@SerializedName("CustomerId")
	private int customerId;

	@SerializedName("BookedTime")
	private String bookedTime;

	@SerializedName("VehicleGroupId")
	private Object vehicleGroupId;

	@SerializedName("SrcLongitude")
	private double srcLongitude;

	@SerializedName("DriverId")
	private int driverId;

	@SerializedName("DriverComments")
	private Object driverComments;

	@SerializedName("DestId")
	private int destId;

	@SerializedName("SeatsOccupied")
	private Object seatsOccupied;

	@SerializedName("BookingChannel")
	private String bookingChannel;

	@SerializedName("DriverPhoneNo")
	private String driverPhoneNo;

	@SerializedName("SrcId")
	private int srcId;

	@SerializedName("PackageId")
	private int packageId;

	@SerializedName("ClosingDate")
	private Object closingDate;

	@SerializedName("UpdatedUserId")
	private Object updatedUserId;

	@SerializedName("Id")
	private int id;

	@SerializedName("DestLatitude")
	private double destLatitude;

	public void setDepartureTime(String departureTime){
		this.departureTime = departureTime;
	}

	public String getDepartureTime(){
		return departureTime;
	}

	public void setDriverRated(Object driverRated){
		this.driverRated = driverRated;
	}

	public Object getDriverRated(){
		return driverRated;
	}

	public void setDestLongitude(double destLongitude){
		this.destLongitude = destLongitude;
	}

	public double getDestLongitude(){
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

	public void setSrcLatitude(double srcLatitude){
		this.srcLatitude = srcLatitude;
	}

	public double getSrcLatitude(){
		return srcLatitude;
	}

	public void setRating(Object rating){
		this.rating = rating;
	}

	public Object getRating(){
		return rating;
	}

	public void setRatedBy(Object ratedBy){
		this.ratedBy = ratedBy;
	}

	public Object getRatedBy(){
		return ratedBy;
	}

	public void setDriverRating(Object driverRating){
		this.driverRating = driverRating;
	}

	public Object getDriverRating(){
		return driverRating;
	}

	public void setBooKingOTP(Object booKingOTP){
		this.booKingOTP = booKingOTP;
	}

	public Object getBooKingOTP(){
		return booKingOTP;
	}

	public void setVechId(int vechId){
		this.vechId = vechId;
	}

	public int getVechId(){
		return vechId;
	}

	public void setNoofVehicles(int noofVehicles){
		this.noofVehicles = noofVehicles;
	}

	public int getNoofVehicles(){
		return noofVehicles;
	}

	public void setUpdatedBy(Object updatedBy){
		this.updatedBy = updatedBy;
	}

	public Object getUpdatedBy(){
		return updatedBy;
	}

	public void setPricing(int pricing){
		this.pricing = pricing;
	}

	public int getPricing(){
		return pricing;
	}

	public void setBookingStatus(String bookingStatus){
		this.bookingStatus = bookingStatus;
	}

	public String getBookingStatus(){
		return bookingStatus;
	}

	public void setDepartueDate(Object departueDate){
		this.departueDate = departueDate;
	}

	public Object getDepartueDate(){
		return departueDate;
	}

	public void setCancelledOn(Object cancelledOn){
		this.cancelledOn = cancelledOn;
	}

	public Object getCancelledOn(){
		return cancelledOn;
	}

	public void setBookingType(String bookingType){
		this.bookingType = bookingType;
	}

	public String getBookingType(){
		return bookingType;
	}

	public void setPaymentStatus(Object paymentStatus){
		this.paymentStatus = paymentStatus;
	}

	public Object getPaymentStatus(){
		return paymentStatus;
	}

	public void setDistance(Object distance){
		this.distance = distance;
	}

	public Object getDistance(){
		return distance;
	}

	public void setTripCount(Object tripCount){
		this.tripCount = tripCount;
	}

	public Object getTripCount(){
		return tripCount;
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

	public void setClosingTime(Object closingTime){
		this.closingTime = closingTime;
	}

	public Object getClosingTime(){
		return closingTime;
	}

	public void setCompanyId(int companyId){
		this.companyId = companyId;
	}

	public int getCompanyId(){
		return companyId;
	}

	public void setComments(Object comments){
		this.comments = comments;
	}

	public Object getComments(){
		return comments;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setNoofSeats(int noofSeats){
		this.noofSeats = noofSeats;
	}

	public int getNoofSeats(){
		return noofSeats;
	}

	public void setReasons(String reasons){
		this.reasons = reasons;
	}

	public String getReasons(){
		return reasons;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}

	public int getCustomerId(){
		return customerId;
	}

	public void setBookedTime(String bookedTime){
		this.bookedTime = bookedTime;
	}

	public String getBookedTime(){
		return bookedTime;
	}

	public void setVehicleGroupId(Object vehicleGroupId){
		this.vehicleGroupId = vehicleGroupId;
	}

	public Object getVehicleGroupId(){
		return vehicleGroupId;
	}

	public void setSrcLongitude(double srcLongitude){
		this.srcLongitude = srcLongitude;
	}

	public double getSrcLongitude(){
		return srcLongitude;
	}

	public void setDriverId(int driverId){
		this.driverId = driverId;
	}

	public int getDriverId(){
		return driverId;
	}

	public void setDriverComments(Object driverComments){
		this.driverComments = driverComments;
	}

	public Object getDriverComments(){
		return driverComments;
	}

	public void setDestId(int destId){
		this.destId = destId;
	}

	public int getDestId(){
		return destId;
	}

	public void setSeatsOccupied(Object seatsOccupied){
		this.seatsOccupied = seatsOccupied;
	}

	public Object getSeatsOccupied(){
		return seatsOccupied;
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

	public void setSrcId(int srcId){
		this.srcId = srcId;
	}

	public int getSrcId(){
		return srcId;
	}

	public void setPackageId(int packageId){
		this.packageId = packageId;
	}

	public int getPackageId(){
		return packageId;
	}

	public void setClosingDate(Object closingDate){
		this.closingDate = closingDate;
	}

	public Object getClosingDate(){
		return closingDate;
	}

	public void setUpdatedUserId(Object updatedUserId){
		this.updatedUserId = updatedUserId;
	}

	public Object getUpdatedUserId(){
		return updatedUserId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDestLatitude(double destLatitude){
		this.destLatitude = destLatitude;
	}

	public double getDestLatitude(){
		return destLatitude;
	}

	@Override
 	public String toString(){
		return 
			"GetTripStatusResponse{" + 
			"departureTime = '" + departureTime + '\'' + 
			",driverRated = '" + driverRated + '\'' + 
			",destLongitude = '" + destLongitude + '\'' + 
			",cancelledBy = '" + cancelledBy + '\'' + 
			",bookedDate = '" + bookedDate + '\'' + 
			",src = '" + src + '\'' + 
			",srcLatitude = '" + srcLatitude + '\'' + 
			",rating = '" + rating + '\'' + 
			",ratedBy = '" + ratedBy + '\'' + 
			",driverRating = '" + driverRating + '\'' + 
			",booKingOTP = '" + booKingOTP + '\'' + 
			",vechId = '" + vechId + '\'' + 
			",noofVehicles = '" + noofVehicles + '\'' + 
			",updatedBy = '" + updatedBy + '\'' + 
			",pricing = '" + pricing + '\'' + 
			",bookingStatus = '" + bookingStatus + '\'' + 
			",departueDate = '" + departueDate + '\'' + 
			",cancelledOn = '" + cancelledOn + '\'' + 
			",bookingType = '" + bookingType + '\'' + 
			",paymentStatus = '" + paymentStatus + '\'' + 
			",distance = '" + distance + '\'' + 
			",tripCount = '" + tripCount + '\'' + 
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
			",vehicleGroupId = '" + vehicleGroupId + '\'' + 
			",srcLongitude = '" + srcLongitude + '\'' + 
			",driverId = '" + driverId + '\'' + 
			",driverComments = '" + driverComments + '\'' + 
			",destId = '" + destId + '\'' + 
			",seatsOccupied = '" + seatsOccupied + '\'' + 
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