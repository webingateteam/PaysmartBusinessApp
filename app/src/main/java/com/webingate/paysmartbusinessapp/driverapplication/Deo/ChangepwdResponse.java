package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class ChangepwdResponse {

	@SerializedName("Email")
	private String email;

	@SerializedName("PMobNo")
	private String pMobNo;

	@SerializedName("NAme")
	private String nAme;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@SerializedName("Code")
	private String code;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@SerializedName("description")
	private String description;

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
			"ChangepwdResponse{" +
			"email = '" + email + '\'' + 
			",pMobNo = '" + pMobNo + '\'' + 
			",nAme = '" + nAme + '\'' + 
			"}";
		}
}