package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class CustomerGetstopsResponse {

	@SerializedName("Active")
	private String active;

	@SerializedName("Description")
	private String description;

	@SerializedName("Latitude")
	private String latitude;

	@SerializedName("Id")
	private String id;

	@SerializedName("Code")
	private String code;

	@SerializedName("Longitude")
	private String longitude;

	@SerializedName("Name")
	private String name;

	public void setActive(String active){
		this.active = active;
	}

	public String getActive(){
		return active;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	
}