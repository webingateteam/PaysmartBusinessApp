package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverChangePwdResponse{

	@SerializedName("Email")
	private String email;

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
			"DriverChangePwdResponse{" + 
			"email = '" + email + '\'' + 
			",pMobNo = '" + pMobNo + '\'' + 
			",nAme = '" + nAme + '\'' + 
			"}";
		}
}