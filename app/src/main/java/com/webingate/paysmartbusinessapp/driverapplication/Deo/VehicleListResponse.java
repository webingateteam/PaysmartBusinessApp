package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class VehicleListResponse{

	@SerializedName("ChasisNo")
	private String chasisNo;

	@SerializedName("VehicleTypeId")
	private int vehicleTypeId;

	@SerializedName("RegistrationNo")
	private String registrationNo;

	@SerializedName("Photo")
	private Object photo;

	@SerializedName("ContactNo1")
	private Object contactNo1;

	@SerializedName("VehicleModelId")
	private int vehicleModelId;

	@SerializedName("IsDriverOwned")
	private int isDriverOwned;

	@SerializedName("IsVerified")
	private int isVerified;

	@SerializedName("VehicleCode")
	private String vehicleCode;

	@SerializedName("CountryId")
	private int countryId;

	@SerializedName("Status")
	private Object status;

	@SerializedName("EmailId")
	private Object emailId;

	@SerializedName("CompanyId")
	private int companyId;

	@SerializedName("FirstName")
	private Object firstName;

	@SerializedName("VehicleType")
	private String vehicleType;

	@SerializedName("VehicleGroup")
	private String vehicleGroup;

	@SerializedName("VID")
	private int vID;

	@SerializedName("VehicleGroupId")
	private int vehicleGroupId;

	@SerializedName("fleetowner")
	private Object fleetowner;

	@SerializedName("DriverId")
	private Object driverId;

	@SerializedName("EntryDate")
	private String entryDate;

	@SerializedName("HasAC")
	private int hasAC;

	@SerializedName("StatusId")
	private int statusId;

	@SerializedName("ModelYear")
	private String modelYear;

	@SerializedName("Id")
	private int id;

	@SerializedName("Engineno")
	private String engineno;

	public void setChasisNo(String chasisNo){
		this.chasisNo = chasisNo;
	}

	public String getChasisNo(){
		return chasisNo;
	}

	public void setVehicleTypeId(int vehicleTypeId){
		this.vehicleTypeId = vehicleTypeId;
	}

	public int getVehicleTypeId(){
		return vehicleTypeId;
	}

	public void setRegistrationNo(String registrationNo){
		this.registrationNo = registrationNo;
	}

	public String getRegistrationNo(){
		return registrationNo;
	}

	public void setPhoto(Object photo){
		this.photo = photo;
	}

	public Object getPhoto(){
		return photo;
	}

	public void setContactNo1(Object contactNo1){
		this.contactNo1 = contactNo1;
	}

	public Object getContactNo1(){
		return contactNo1;
	}

	public void setVehicleModelId(int vehicleModelId){
		this.vehicleModelId = vehicleModelId;
	}

	public int getVehicleModelId(){
		return vehicleModelId;
	}

	public void setIsDriverOwned(int isDriverOwned){
		this.isDriverOwned = isDriverOwned;
	}

	public int getIsDriverOwned(){
		return isDriverOwned;
	}

	public void setIsVerified(int isVerified){
		this.isVerified = isVerified;
	}

	public int getIsVerified(){
		return isVerified;
	}

	public void setVehicleCode(String vehicleCode){
		this.vehicleCode = vehicleCode;
	}

	public String getVehicleCode(){
		return vehicleCode;
	}

	public void setCountryId(int countryId){
		this.countryId = countryId;
	}

	public int getCountryId(){
		return countryId;
	}

	public void setStatus(Object status){
		this.status = status;
	}

	public Object getStatus(){
		return status;
	}

	public void setEmailId(Object emailId){
		this.emailId = emailId;
	}

	public Object getEmailId(){
		return emailId;
	}

	public void setCompanyId(int companyId){
		this.companyId = companyId;
	}

	public int getCompanyId(){
		return companyId;
	}

	public void setFirstName(Object firstName){
		this.firstName = firstName;
	}

	public Object getFirstName(){
		return firstName;
	}

	public void setVehicleType(String vehicleType){
		this.vehicleType = vehicleType;
	}

	public String getVehicleType(){
		return vehicleType;
	}

	public void setVehicleGroup(String vehicleGroup){
		this.vehicleGroup = vehicleGroup;
	}

	public String getVehicleGroup(){
		return vehicleGroup;
	}

	public void setVID(int vID){
		this.vID = vID;
	}

	public int getVID(){
		return vID;
	}

	public void setVehicleGroupId(int vehicleGroupId){
		this.vehicleGroupId = vehicleGroupId;
	}

	public int getVehicleGroupId(){
		return vehicleGroupId;
	}

	public void setFleetowner(Object fleetowner){
		this.fleetowner = fleetowner;
	}

	public Object getFleetowner(){
		return fleetowner;
	}

	public void setDriverId(Object driverId){
		this.driverId = driverId;
	}

	public Object getDriverId(){
		return driverId;
	}

	public void setEntryDate(String entryDate){
		this.entryDate = entryDate;
	}

	public String getEntryDate(){
		return entryDate;
	}

	public void setHasAC(int hasAC){
		this.hasAC = hasAC;
	}

	public int getHasAC(){
		return hasAC;
	}

	public void setStatusId(int statusId){
		this.statusId = statusId;
	}

	public int getStatusId(){
		return statusId;
	}

	public void setModelYear(String modelYear){
		this.modelYear = modelYear;
	}

	public String getModelYear(){
		return modelYear;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEngineno(String engineno){
		this.engineno = engineno;
	}

	public String getEngineno(){
		return engineno;
	}

	@Override
 	public String toString(){
		return 
			"VehicleListResponse{" + 
			"chasisNo = '" + chasisNo + '\'' + 
			",vehicleTypeId = '" + vehicleTypeId + '\'' + 
			",registrationNo = '" + registrationNo + '\'' + 
			",photo = '" + photo + '\'' + 
			",contactNo1 = '" + contactNo1 + '\'' + 
			",vehicleModelId = '" + vehicleModelId + '\'' + 
			",isDriverOwned = '" + isDriverOwned + '\'' + 
			",isVerified = '" + isVerified + '\'' + 
			",vehicleCode = '" + vehicleCode + '\'' + 
			",countryId = '" + countryId + '\'' + 
			",status = '" + status + '\'' + 
			",emailId = '" + emailId + '\'' + 
			",companyId = '" + companyId + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",vehicleType = '" + vehicleType + '\'' + 
			",vehicleGroup = '" + vehicleGroup + '\'' + 
			",vID = '" + vID + '\'' + 
			",vehicleGroupId = '" + vehicleGroupId + '\'' + 
			",fleetowner = '" + fleetowner + '\'' + 
			",driverId = '" + driverId + '\'' + 
			",entryDate = '" + entryDate + '\'' + 
			",hasAC = '" + hasAC + '\'' + 
			",statusId = '" + statusId + '\'' + 
			",modelYear = '" + modelYear + '\'' + 
			",id = '" + id + '\'' + 
			",engineno = '" + engineno + '\'' + 
			"}";
		}
}