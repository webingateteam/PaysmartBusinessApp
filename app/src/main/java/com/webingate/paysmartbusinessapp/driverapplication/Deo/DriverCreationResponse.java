package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverCreationResponse{

	@SerializedName("PPin")
	private String pPin;

	@SerializedName("BloodGroup")
	private Object bloodGroup;

	@SerializedName("Email")
	private String email;

	@SerializedName("Address")
	private String address;

	@SerializedName("PMobNo")
	private String pMobNo;

	@SerializedName("AccountNo")
	private Object accountNo;

	@SerializedName("PAddress")
	private String pAddress;

	@SerializedName("Photo")
	private String photo;

	@SerializedName("City")
	private Object city;

	@SerializedName("AuthTypeId")
	private Object authTypeId;

	@SerializedName("NAme")
	private String nAme;

	@SerializedName("OffMobileNo")
	private Object offMobileNo;

	@SerializedName("lastname")
	private String lastname;

	@SerializedName("Firstname")
	private String firstname;

	@SerializedName("AltPhonenumber")
	private Object altPhonenumber;

	@SerializedName("Altemail")
	private Object altemail;

	@SerializedName("Pin")
	private String pin;

	@SerializedName("DOB")
	private Object dOB;

	@SerializedName("PCity")
	private Object pCity;

	@SerializedName("DId")
	private int dId;

	@SerializedName("DOJ")
	private Object dOJ;

	public void setPPin(String pPin){
		this.pPin = pPin;
	}

	public String getPPin(){
		return pPin;
	}

	public void setBloodGroup(Object bloodGroup){
		this.bloodGroup = bloodGroup;
	}

	public Object getBloodGroup(){
		return bloodGroup;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setPMobNo(String pMobNo){
		this.pMobNo = pMobNo;
	}

	public String getPMobNo(){
		return pMobNo;
	}

	public void setAccountNo(Object accountNo){
		this.accountNo = accountNo;
	}

	public Object getAccountNo(){
		return accountNo;
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

	public void setCity(Object city){
		this.city = city;
	}

	public Object getCity(){
		return city;
	}

	public void setAuthTypeId(Object authTypeId){
		this.authTypeId = authTypeId;
	}

	public Object getAuthTypeId(){
		return authTypeId;
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

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setAltPhonenumber(Object altPhonenumber){
		this.altPhonenumber = altPhonenumber;
	}

	public Object getAltPhonenumber(){
		return altPhonenumber;
	}

	public void setAltemail(Object altemail){
		this.altemail = altemail;
	}

	public Object getAltemail(){
		return altemail;
	}

	public void setPin(String pin){
		this.pin = pin;
	}

	public String getPin(){
		return pin;
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
			"DriverCreationResponse{" + 
			"pPin = '" + pPin + '\'' + 
			",bloodGroup = '" + bloodGroup + '\'' + 
			",email = '" + email + '\'' + 
			",address = '" + address + '\'' + 
			",pMobNo = '" + pMobNo + '\'' + 
			",accountNo = '" + accountNo + '\'' + 
			",pAddress = '" + pAddress + '\'' + 
			",photo = '" + photo + '\'' + 
			",city = '" + city + '\'' + 
			",authTypeId = '" + authTypeId + '\'' + 
			",nAme = '" + nAme + '\'' + 
			",offMobileNo = '" + offMobileNo + '\'' + 
			",lastname = '" + lastname + '\'' + 
			",firstname = '" + firstname + '\'' + 
			",altPhonenumber = '" + altPhonenumber + '\'' + 
			",altemail = '" + altemail + '\'' + 
			",pin = '" + pin + '\'' + 
			",dOB = '" + dOB + '\'' + 
			",pCity = '" + pCity + '\'' + 
			",dId = '" + dId + '\'' + 
			",dOJ = '" + dOJ + '\'' + 
			"}";
		}
}