package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverValidateCredentialsResponse{

	@SerializedName("todate")
	private String todate;

	@SerializedName("VehicleId")
	private String vehicleId;

	@SerializedName("Id")
	private String id;

	@SerializedName("IsVerified")
	private String isVerified;

	@SerializedName("did")
	private String did;

	@SerializedName("fromdate")
	private String fromdate;

	@SerializedName("IsApproved")
	private String isApproved;


	@SerializedName("Code")
	private String Code;

	@SerializedName("description")
	private String description;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		this.Code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTodate(String todate){
		this.todate = todate;
	}

	public String getTodate(){
		return todate;
	}

	public void setVehicleId(String vehicleId){
		this.vehicleId = vehicleId;
	}

	public String getVehicleId(){
		return vehicleId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setIsVerified(String isVerified){
		this.isVerified = isVerified;
	}

	public String getIsVerified(){
		return isVerified;
	}

	public void setDid(String did){
		this.did = did;
	}

	public String getDid(){
		return did;
	}

	public void setFromdate(String fromdate){
		this.fromdate = fromdate;
	}

	public String getFromdate(){
		return fromdate;
	}

	public void setIsApproved(String isApproved){
		this.isApproved = isApproved;
	}

	public String getIsApproved(){
		return isApproved;
	}


}