package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class CustomerPayResponse{

	@SerializedName("status")
	private int status;

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"CustomerPayResponse{" + 
			"status = '" + status + '\'' + 
			"}";
		}
}