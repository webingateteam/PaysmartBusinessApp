package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class GetVehicleListResponse {

	@SerializedName("Id")
	private int Id;

	@SerializedName("ModelYear")
	private String modelyear;

	@SerializedName("RegistrationNo")
	private String RegistrationNo;

	@SerializedName("VehicleGroup")
	private String VehicleGroup;

	@SerializedName("VehicleCode")
	private String VehicleCode;

	@SerializedName("VehicleType")
	private String VehicleType;

	@SerializedName("CompanyId")
	private Object companyId;

	@SerializedName("Photo")
	private String Photo;



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

	public void setPhoto(String Photo){
		this.Photo = Photo;
	}

	public String getPhoto(){
		return Photo;
	}

	public void setId(int Id){
		this.Id = Id;
	}

	public int getId(){
		return Id;
	}

	public void setmodelyear(String modelyear){
		this.modelyear = modelyear;
	}

	public String getmodelyear(){
		return modelyear;
	}


	@Override
 	public String toString(){
		return 
			"AllocatedDriverListResponse{" +
			"RegistrationNo = '" + RegistrationNo + '\'' +
			",VehicleCode = '" + VehicleCode + '\'' +
			",VehicleGroup = '" + VehicleGroup +
		    ",VehicleType = '" + VehicleType + '\''+
		    ",Photo = '" + Photo + '\''+
		    ",Id = '" + Id + '\''+
			",ModelYear = '" + modelyear + '\''+
			"}";
		}
}