package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class SaveDriverApprovalResponse{

	@SerializedName("Email")
	private String email;

	@SerializedName("description")
	private Object description;

	@SerializedName("Code")
	private Object code;

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setCode(Object code){
		this.code = code;
	}

	public Object getCode(){
		return code;
	}

	@Override
 	public String toString(){
		return 
			"SaveDriverApprovalResponse{" + 
			"email = '" + email + '\'' + 
			",description = '" + description + '\'' + 
			",code = '" + code + '\'' + 
			"}";
		}
}