package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class AssignDriverResponse {

	@SerializedName("Status")
	private String status;

	@SerializedName("RegistrationNo")
	private String RegistrationNo;

	@SerializedName("DriverName")
	private String DriverName;

	@SerializedName("VehicleId")
	private String vehicleId;

	@SerializedName("DriverId")
	private String driverId;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@SerializedName("Code")
	private String code;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@SerializedName("description")
	private String description;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setRegistrationNo(String RegistrationNo){
		this.RegistrationNo = RegistrationNo;
	}

	public String getRegistrationNo(){
		return RegistrationNo;
	}

	public void setDriverName(String DriverName){
		this.DriverName = DriverName;
	}

	public String getDriverName(){
		return DriverName;
	}

	public void setVehicleId(String vehicleId){
		this.vehicleId = vehicleId;
	}

	public String getVehicleId(){
		return vehicleId;
	}

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}

	public String getDriverId(){
		return driverId;
	}


	@Override
 	public String toString(){
		return 
			"AssignDriverResponse{" +
			",RegistrationNo = '" + RegistrationNo + '\'' +
			",DriverName = '" + DriverName + '\'' +
			",driverId = '" + driverId + '\'' +
			"}";
		}
}