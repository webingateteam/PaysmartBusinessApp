package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class AcceptRejectBookingResponse {

	@SerializedName("Firstname")
	private String firstname;

	@SerializedName("CustomerPhoneNo")
	private String customerPhoneNo;

	@SerializedName("img")
	private Object img;

	@SerializedName("Username")
	private String username;

	@SerializedName("lastname")
	private String lastname;

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setCustomerPhoneNo(String customerPhoneNo){
		this.customerPhoneNo = customerPhoneNo;
	}

	public String getCustomerPhoneNo(){
		return customerPhoneNo;
	}

	public void setImg(Object img){
		this.img = img;
	}

	public Object getImg(){
		return img;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	@Override
 	public String toString(){
		return 
			"DriverAcceptRejectBookingResponse{" + 
			"firstname = '" + firstname + '\'' + 
			",customerPhoneNo = '" + customerPhoneNo + '\'' + 
			",img = '" + img + '\'' + 
			",username = '" + username + '\'' + 
			",lastname = '" + lastname + '\'' + 
			"}";
		}
}