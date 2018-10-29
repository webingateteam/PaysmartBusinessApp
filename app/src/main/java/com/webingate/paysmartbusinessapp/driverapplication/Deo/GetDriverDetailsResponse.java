package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class GetDriverDetailsResponse{

	@SerializedName("PPin")
	private Object pPin;

	@SerializedName("CountryId")
	private int countryId;

	@SerializedName("Status")
	private Object status;

	@SerializedName("BloodGroup")
	private Object bloodGroup;

	@SerializedName("DriverCode")
	private String driverCode;

	@SerializedName("Address")
	private Object address;

	@SerializedName("CompanyId")
	private Object companyId;

	@SerializedName("PMobNo")
	private String pMobNo;

	@SerializedName("PAddress")
	private Object pAddress;

	@SerializedName("Photo")
	private String photo;

	@SerializedName("City")
	private Object city;

	@SerializedName("NAme")
	private String nAme;

	@SerializedName("OffMobileNo")
	private Object offMobileNo;

	@SerializedName("lastname")
	private String lastname;

	@SerializedName("IsApproved")
	private Object isApproved;

	@SerializedName("Firstname")
	private String firstname;

	@SerializedName("VehicleGroupId")
	private int vehicleGroupId;

	@SerializedName("Pin")
	private Object pin;

	@SerializedName("Remarks")
	private Object remarks;

	@SerializedName("DOB")
	private Object dOB;

	@SerializedName("PCity")
	private Object pCity;

	@SerializedName("PaymentTypeId")
	private Object paymentTypeId;

	@SerializedName("DId")
	private int dId;

	@SerializedName("DOJ")
	private Object dOJ;

	public void setPPin(Object pPin){
		this.pPin = pPin;
	}

	public Object getPPin(){
		return pPin;
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

	public void setBloodGroup(Object bloodGroup){
		this.bloodGroup = bloodGroup;
	}

	public Object getBloodGroup(){
		return bloodGroup;
	}

	public void setDriverCode(String driverCode){
		this.driverCode = driverCode;
	}

	public String getDriverCode(){
		return driverCode;
	}

	public void setAddress(Object address){
		this.address = address;
	}

	public Object getAddress(){
		return address;
	}

	public void setCompanyId(Object companyId){
		this.companyId = companyId;
	}

	public Object getCompanyId(){
		return companyId;
	}

	public void setPMobNo(String pMobNo){
		this.pMobNo = pMobNo;
	}

	public String getPMobNo(){
		return pMobNo;
	}

	public void setPAddress(Object pAddress){
		this.pAddress = pAddress;
	}

	public Object getPAddress(){
		return pAddress;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setCity(Object city){
		this.city = city;
	}

	public Object getCity(){
		return city;
	}

	public void setNAme(String nAme){
		this.nAme = nAme;
	}

	public String getNAme(){
		return nAme;
	}

	public void setOffMobileNo(Object offMobileNo){
		this.offMobileNo = offMobileNo;
	}

	public Object getOffMobileNo(){
		return offMobileNo;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	public void setIsApproved(Object isApproved){
		this.isApproved = isApproved;
	}

	public Object getIsApproved(){
		return isApproved;
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setVehicleGroupId(int vehicleGroupId){
		this.vehicleGroupId = vehicleGroupId;
	}

	public int getVehicleGroupId(){
		return vehicleGroupId;
	}

	public void setPin(Object pin){
		this.pin = pin;
	}

	public Object getPin(){
		return pin;
	}

	public void setRemarks(Object remarks){
		this.remarks = remarks;
	}

	public Object getRemarks(){
		return remarks;
	}

	public void setDOB(Object dOB){
		this.dOB = dOB;
	}

	public Object getDOB(){
		return dOB;
	}

	public void setPCity(Object pCity){
		this.pCity = pCity;
	}

	public Object getPCity(){
		return pCity;
	}

	public void setPaymentTypeId(Object paymentTypeId){
		this.paymentTypeId = paymentTypeId;
	}

	public Object getPaymentTypeId(){
		return paymentTypeId;
	}

	public void setDId(int dId){
		this.dId = dId;
	}

	public int getDId(){
		return dId;
	}

	public void setDOJ(Object dOJ){
		this.dOJ = dOJ;
	}

	public Object getDOJ(){
		return dOJ;
	}

	@Override
 	public String toString(){
		return 
			"GetDriverDetailsResponse{" + 
			"pPin = '" + pPin + '\'' + 
			",countryId = '" + countryId + '\'' + 
			",status = '" + status + '\'' + 
			",bloodGroup = '" + bloodGroup + '\'' + 
			",driverCode = '" + driverCode + '\'' + 
			",address = '" + address + '\'' + 
			",companyId = '" + companyId + '\'' + 
			",pMobNo = '" + pMobNo + '\'' + 
			",pAddress = '" + pAddress + '\'' + 
			",photo = '" + photo + '\'' + 
			",city = '" + city + '\'' + 
			",nAme = '" + nAme + '\'' + 
			",offMobileNo = '" + offMobileNo + '\'' + 
			",lastname = '" + lastname + '\'' + 
			",isApproved = '" + isApproved + '\'' + 
			",firstname = '" + firstname + '\'' + 
			",vehicleGroupId = '" + vehicleGroupId + '\'' + 
			",pin = '" + pin + '\'' + 
			",remarks = '" + remarks + '\'' + 
			",dOB = '" + dOB + '\'' + 
			",pCity = '" + pCity + '\'' + 
			",paymentTypeId = '" + paymentTypeId + '\'' + 
			",dId = '" + dId + '\'' + 
			",dOJ = '" + dOJ + '\'' + 
			"}";
		}
}