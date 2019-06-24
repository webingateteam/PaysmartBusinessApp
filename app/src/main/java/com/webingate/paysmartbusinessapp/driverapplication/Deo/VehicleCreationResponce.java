package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class VehicleCreationResponce {

	@SerializedName("RegistrationNo")
	private String RegistrationNo;

	@SerializedName("VehicleGroup")
	private String VehicleGroup;

	@SerializedName("VehicleType")
	private String VehicleType;

	@SerializedName("Code")
	private String Code;

	@SerializedName("description")
	private String description;
	@SerializedName("Photo")
	private String Photo;
	@SerializedName("Id")
	private int Id;

	public void setRegistrationNo(String RegistrationNo){
		this.RegistrationNo = RegistrationNo;
	}

	public String getRegistrationNo(){
		return RegistrationNo;
	}

	public void setVehicleGroup(String VehicleGroup){
		this.VehicleGroup = VehicleGroup;
	}

	public String getVehicleGroup(){
		return VehicleGroup;
	}

	public void setVehicleType(String VehicleType){
		this.VehicleType = VehicleType;
	}

	public String getVehicleType(){
		return VehicleType;
	}

	public void setCode(String code){
		this.Code = Code;
	}

	public String getCode(){
		return Code;
	}
	public String getPhoto(){
		return Photo;
	}
	public int getid(){
		return Id;
	}

	public void setdescription(String description){
		this.description = description;
	}

	public String getdescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"VehicleCreationResponse{" +
			"RegistrationNo = '" + RegistrationNo + '\'' +
			",VehicleGroup = '" + VehicleGroup + '\'' +
			",VehicleType = '" + VehicleType + '\'' +
					",Photo = '" + Photo + '\'' +
					",Id = '" + Id + '\'' +
			"}";
		}
}