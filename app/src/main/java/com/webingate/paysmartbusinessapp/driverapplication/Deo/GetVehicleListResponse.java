package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class GetVehicleListResponse {

	@SerializedName("RegistrationNo")
	private Object RegistrationNo;

	@SerializedName("VehicleGroup")
	private String VehicleGroup;

	@SerializedName("VehicleCode")
	private String VehicleCode;

	@SerializedName("VehicleType")
	private String VehicleType;

	@SerializedName("CompanyId")
	private Object companyId;

	@SerializedName("photo")
	private String photo;



	public void setRegistrationNo(Object RegistrationNo){
		this.RegistrationNo = RegistrationNo;
	}

	public Object getRegistrationNo(){
		return RegistrationNo;
	}

	public void setVehicleGroup(String VehicleGroup){
		this.VehicleGroup = VehicleGroup;
	}

	public String getVehicleGroup(){
		return VehicleGroup;
	}

	public void setVehicleCode(String VehicleCode){
		this.VehicleCode = VehicleCode;
	}

	public String getVehicleCode(){
		return VehicleCode;
	}

	public void setVehicleType(String VehicleType){
		this.VehicleType = VehicleType;
	}

	public String getVehicleType(){
		return VehicleType;
	}

	public void setCompanyId(Object companyId){
		this.companyId = companyId;
	}

	public Object getCompanyId(){
		return companyId;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}


	@Override
 	public String toString(){
		return 
			"AllocatedDriverListResponse{" +
			"RegistrationNo = '" + RegistrationNo + '\'' +
			",VehicleCode = '" + VehicleCode + '\'' +
			",VehicleGroup = '" + VehicleGroup +
		    ",VehicleType = '" + VehicleType + '\''+
		    ",Photo = '" + photo + '\''+

			"}";
		}
}