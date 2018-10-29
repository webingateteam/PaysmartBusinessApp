package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DriverDetailsTableItem implements Serializable {

	@SerializedName("PPin")
	private String pPin;

	@SerializedName("CountryId")
	private String countryId;

	@SerializedName("Status")
	private String status;

	@SerializedName("BloodGroup")
	private String bloodGroup;

	@SerializedName("DriverCode")
	private String driverCode;

	@SerializedName("Address")
	private String address;

	@SerializedName("CompanyId")
	private String companyId;

	@SerializedName("PMobNo")
	private String pMobNo;

	@SerializedName("PAddress")
	private String pAddress;

	@SerializedName("Photo")
	private String photo;

	@SerializedName("City")
	private String city;

	@SerializedName("NAme")
	private String nAme;

	@SerializedName("OffMobileNo")
	private String offMobileNo;

	@SerializedName("lastname")
	private String lastname;

	@SerializedName("IsApproved")
	private String isApproved;

	@SerializedName("Firstname")
	private String firstname;

	@SerializedName("VehicleGroupId")
	private String vehicleGroupId;

	@SerializedName("Pin")
	private String pin;

	@SerializedName("Remarks")
	private String remarks;

	@SerializedName("DOB")
	private String dOB;

	@SerializedName("PCity")
	private String pCity;

	@SerializedName("PaymentTypeId")
	private String paymentTypeId;

	@SerializedName("DId")
	private String dId;

	@SerializedName("DOJ")
	private String dOJ;

	public void setPPin(String pPin){
		this.pPin = pPin;
	}

	public String getPPin(){
		return pPin;
	}

	public void setCountryId(String countryId){
		this.countryId = countryId;
	}

	public String getCountryId(){
		return countryId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setBloodGroup(String bloodGroup){
		this.bloodGroup = bloodGroup;
	}

	public String getBloodGroup(){
		return bloodGroup;
	}

	public void setDriverCode(String driverCode){
		this.driverCode = driverCode;
	}

	public String getDriverCode(){
		return driverCode;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setCompanyId(String companyId){
		this.companyId = companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public void setPMobNo(String pMobNo){
		this.pMobNo = pMobNo;
	}

	public String getPMobNo(){
		return pMobNo;
	}

	public void setPAddress(String pAddress){
		this.pAddress = pAddress;
	}

	public String getPAddress(){
		return pAddress;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setNAme(String nAme){
		this.nAme = nAme;
	}

	public String getNAme(){
		return nAme;
	}

	public void setOffMobileNo(String offMobileNo){
		this.offMobileNo = offMobileNo;
	}

	public String getOffMobileNo(){
		return offMobileNo;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	public void setIsApproved(String isApproved){
		this.isApproved = isApproved;
	}

	public String getIsApproved(){
		return isApproved;
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setVehicleGroupId(String vehicleGroupId){
		this.vehicleGroupId = vehicleGroupId;
	}

	public String getVehicleGroupId(){
		return vehicleGroupId;
	}

	public void setPin(String pin){
		this.pin = pin;
	}

	public String getPin(){
		return pin;
	}

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

	public String getRemarks(){
		return remarks;
	}

	public void setDOB(String dOB){
		this.dOB = dOB;
	}

	public String getDOB(){
		return dOB;
	}

	public void setPCity(String pCity){
		this.pCity = pCity;
	}

	public String getPCity(){
		return pCity;
	}

	public void setPaymentTypeId(String paymentTypeId){
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentTypeId(){
		return paymentTypeId;
	}

	public void setDId(String dId){
		this.dId = dId;
	}

	public String getDId(){
		return dId;
	}

	public void setDOJ(String dOJ){
		this.dOJ = dOJ;
	}

	public String getDOJ(){
		return dOJ;
	}

	@Override
 	public String toString(){
		return 
			"DriverDetailsTableItem{" +
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