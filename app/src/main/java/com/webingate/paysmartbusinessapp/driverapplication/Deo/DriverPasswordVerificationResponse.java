package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverPasswordVerificationResponse{

	@SerializedName("Email")
	private String email;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	@SerializedName("Code")
	private String Code;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@SerializedName("Description")
	private String  Description;

	@SerializedName("PMobNo")
	private String pMobNo;

	@SerializedName("NAme")
	private String nAme;

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setPMobNo(String pMobNo){
		this.pMobNo = pMobNo;
	}

	public String getPMobNo(){
		return pMobNo;
	}

	public void setNAme(String nAme){
		this.nAme = nAme;
	}

	public String getNAme(){
		return nAme;
	}

	@Override
 	public String toString(){
		return 
			"DriverPasswordVerificationResponse{" + 
			"email = '" + email + '\'' + 
			",pMobNo = '" + pMobNo + '\'' + 
			",nAme = '" + nAme + '\'' + 
			"}";
		}
}