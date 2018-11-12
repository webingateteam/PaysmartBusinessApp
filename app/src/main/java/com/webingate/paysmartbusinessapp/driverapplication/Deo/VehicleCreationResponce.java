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
	private String code;

	@SerializedName("Description")
	private String description;

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
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"VehicleCreationResponse{" +
			"RegistrationNo = '" + RegistrationNo + '\'' +
			",VehicleGroup = '" + VehicleGroup + '\'' +
			",VehicleType = '" + VehicleType + '\'' +
			"}";
		}
}