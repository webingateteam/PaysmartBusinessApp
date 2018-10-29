package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("todate")
	private Object todate;

	@SerializedName("VehicleId")
	private int vehicleId;

	@SerializedName("Id")
	private int id;

	@SerializedName("IsVerified")
	private Object isVerified;

	@SerializedName("did")
	private int did;

	@SerializedName("fromdate")
	private String fromdate;

	@SerializedName("IsApproved")
	private int isApproved;

	public void setTodate(Object todate){
		this.todate = todate;
	}

	public Object getTodate(){
		return todate;
	}

	public void setVehicleId(int vehicleId){
		this.vehicleId = vehicleId;
	}

	public int getVehicleId(){
		return vehicleId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIsVerified(Object isVerified){
		this.isVerified = isVerified;
	}

	public Object getIsVerified(){
		return isVerified;
	}

	public void setDid(int did){
		this.did = did;
	}

	public int getDid(){
		return did;
	}

	public void setFromdate(String fromdate){
		this.fromdate = fromdate;
	}

	public String getFromdate(){
		return fromdate;
	}

	public void setIsApproved(int isApproved){
		this.isApproved = isApproved;
	}

	public int getIsApproved(){
		return isApproved;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"todate = '" + todate + '\'' + 
			",vehicleId = '" + vehicleId + '\'' + 
			",id = '" + id + '\'' + 
			",isVerified = '" + isVerified + '\'' + 
			",did = '" + did + '\'' + 
			",fromdate = '" + fromdate + '\'' + 
			",isApproved = '" + isApproved + '\'' + 
			"}";
		}
}