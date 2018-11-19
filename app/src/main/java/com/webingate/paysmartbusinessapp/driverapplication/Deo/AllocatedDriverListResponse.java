package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class AllocatedDriverListResponse {

	@SerializedName("RegistrationNo")
	private Object RegistrationNo;

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

	@SerializedName("photo")
	private String photo;

	@SerializedName("PAddress")
	private Object pAddress;

	@SerializedName("City")
	private Object city;

	@SerializedName("NAme")
	private String nAme;

	@SerializedName("OffMobileNo")
	private Object offMobileNo;

	@SerializedName("Pin")
	private Object pin;

	@SerializedName("Remarks")
	private Object remarks;

	@SerializedName("DOB")
	private Object dOB;

	@SerializedName("PCity")
	private Object pCity;

	@SerializedName("DId")
	private int dId;

	@SerializedName("DOJ")
	private Object dOJ;

	public void setRegistrationNo(Object RegistrationNo){
		this.RegistrationNo = RegistrationNo;
	}

	public Object getRegistrationNo(){
		return RegistrationNo;
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

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setPAddress(Object pAddress){
		this.pAddress = pAddress;
	}

	public Object getPAddress(){
		return pAddress;
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
			"AllocatedDriverListResponse{" +
			"RegistrationNo = '" + RegistrationNo + '\'' +
			",DriverCode = '" + driverCode + '\'' +
			",nAme = '" + nAme + '\'' +
			"}";
		}
}