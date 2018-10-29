package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class ConfigDataResponse{

	@SerializedName("Active")
	private int active;

	@SerializedName("Description")
	private Object description;

	@SerializedName("listvalue")
	private Object listvalue;

	@SerializedName("listkey")
	private Object listkey;

	@SerializedName("Id")
	private int id;

	@SerializedName("TypeGroupId")
	private int typeGroupId;

	@SerializedName("Name")
	private String name;

	public void setActive(int active){
		this.active = active;
	}

	public int getActive(){
		return active;
	}

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setListvalue(Object listvalue){
		this.listvalue = listvalue;
	}

	public Object getListvalue(){
		return listvalue;
	}

	public void setListkey(Object listkey){
		this.listkey = listkey;
	}

	public Object getListkey(){
		return listkey;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTypeGroupId(int typeGroupId){
		this.typeGroupId = typeGroupId;
	}

	public int getTypeGroupId(){
		return typeGroupId;
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
			"ConfigDataResponse{" + 
			"active = '" + active + '\'' + 
			",description = '" + description + '\'' + 
			",listvalue = '" + listvalue + '\'' + 
			",listkey = '" + listkey + '\'' + 
			",id = '" + id + '\'' + 
			",typeGroupId = '" + typeGroupId + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}