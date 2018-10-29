package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class CustomerChangePwdResponse{

	@SerializedName("Email")
	private String email;

	@SerializedName("Username")
	private String username;

	@SerializedName("Mobilenumber")
	private String mobilenumber;

	@SerializedName("Code")
	private String code;

	@SerializedName("description")
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setMobilenumber(String mobilenumber){
		this.mobilenumber = mobilenumber;
	}

	public String getMobilenumber(){
		return mobilenumber;
	}

	@Override
 	public String toString(){
		return 
			"CustomerChangePwdResponse{" + 
			"email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			",mobilenumber = '" + mobilenumber + '\'' + 
			"}";
		}
}