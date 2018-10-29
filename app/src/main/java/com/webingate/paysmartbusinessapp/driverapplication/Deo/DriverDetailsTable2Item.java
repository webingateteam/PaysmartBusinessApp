package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverDetailsTable2Item {

	@SerializedName("Status")
	private String status;

	@SerializedName("AltPhoneNo")
	private String altPhoneNo;

	@SerializedName("VehicleType")
	private String vehicleType;

	@SerializedName("DriverName")
	private String driverName;

	@SerializedName("RegistrationNo")
	private String registrationNo;

	@SerializedName("VehicleGroup")
	private String vehicleGroup;

	@SerializedName("VehicleGroupId")
	private String vehicleGroupId;

	@SerializedName("VechID")
	private int vechID;

	@SerializedName("VehicleModel")
	private String vehicleModel;

	@SerializedName("PhoneNo")
	private String phoneNo;

	@SerializedName("VehicleModelId")
	private String vehicleModelId;


	@SerializedName("Package")
	private String Package;

	@SerializedName("IsVerified")
	private int isVerified;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setAltPhoneNo(String altPhoneNo){
		this.altPhoneNo = altPhoneNo;
	}

	public String getAltPhoneNo(){
		return altPhoneNo;
	}

	public void setVehicleType(String vehicleType){
		this.vehicleType = vehicleType;
	}

	public String getVehicleType(){
		return vehicleType;
	}

	public void setDriverName(String driverName){
		this.driverName = driverName;
	}

	public String getDriverName(){
		return driverName;
	}

	public void setRegistrationNo(String registrationNo){
		this.registrationNo = registrationNo;
	}

	public String getRegistrationNo(){
		return registrationNo;
	}

	public void setVehicleGroup(String vehicleGroup){
		this.vehicleGroup = vehicleGroup;
	}

	public String getVehicleGroup(){
		return vehicleGroup;
	}

	public void setVehicleGroupId(String vehicleGroupId){
		this.vehicleGroupId = vehicleGroupId;
	}

	public String getVehicleGroupId(){
		return vehicleGroupId;
	}

	public void setVechID(int vechID){
		this.vechID = vechID;
	}

	public int getVechID(){
		return vechID;
	}

	public void setVehicleModel(String vehicleModel){
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleModel(){
		return vehicleModel;
	}

	public void setPhoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}

	public String getPhoneNo(){
		return phoneNo;
	}

	public void setVehicleModelId(String vehicleModelId){
		this.vehicleModelId = vehicleModelId;
	}

	public String getVehicleModelId(){
		return vehicleModelId;
	}

	public void setPackage(String Package){
		this.Package = Package;
	}

	public String getPackage(){
		return Package;
	}

	public void setIsVerified(int isVerified){
		this.isVerified = isVerified;
	}

	public int getIsVerified(){
		return isVerified;
	}

	@Override
 	public String toString(){
		return 
			"DriverDetailsTable2Item{" +
			"status = '" + status + '\'' + 
			",altPhoneNo = '" + altPhoneNo + '\'' + 
			",vehicleType = '" + vehicleType + '\'' + 
			",driverName = '" + driverName + '\'' + 
			",registrationNo = '" + registrationNo + '\'' + 
			",vehicleGroup = '" + vehicleGroup + '\'' + 
			",vehicleGroupId = '" + vehicleGroupId + '\'' + 
			",vechID = '" + vechID + '\'' + 
			",vehicleModel = '" + vehicleModel + '\'' + 
			",phoneNo = '" + phoneNo + '\'' + 
			",vehicleModelId = '" + vehicleModelId + '\'' + 
			",package = '" + Package + '\'' +
			",isVerified = '" + isVerified + '\'' + 
			"}";
		}
}