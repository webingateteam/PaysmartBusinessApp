package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverDetailsTable4Item {

	@SerializedName("IsMandatory")
	private int isMandatory;

	@SerializedName("uploaded")
	private int uploaded;

	@SerializedName("Id")
	private int id;

	@SerializedName("Name")
	private String name;

	public void setIsMandatory(int isMandatory){
		this.isMandatory = isMandatory;
	}

	public int getIsMandatory(){
		return isMandatory;
	}

	public void setUploaded(int uploaded){
		this.uploaded = uploaded;
	}

	public int getUploaded(){
		return uploaded;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"DriverDetailsTable4Item{" +
			"isMandatory = '" + isMandatory + '\'' + 
			",uploaded = '" + uploaded + '\'' + 
			",id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}