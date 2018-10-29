package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerBookingStatusResponse implements Serializable{

	@SerializedName("img")
	private String img;

	@SerializedName("VModel")
	private String vModel;

	@SerializedName("PMobNo")
	private String pMobNo;

	@SerializedName("VPhoto")
	private String vPhoto;

	@SerializedName("RegistrationNo")
	private String registrationNo;

	@SerializedName("Id")
	private int id;

	@SerializedName("BooKingOTP")
	private String booKingOTP;

	@SerializedName("Name")
	private String name;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setVModel(String vModel){
		this.vModel = vModel;
	}

	public String getVModel(){
		return vModel;
	}

	public void setPMobNo(String pMobNo){
		this.pMobNo = pMobNo;
	}

	public String getPMobNo(){
		return pMobNo;
	}

	public void setVPhoto(String vPhoto){
		this.vPhoto = vPhoto;
	}

	public String getVPhoto(){
		return vPhoto;
	}

	public void setRegistrationNo(String registrationNo){
		this.registrationNo = registrationNo;
	}

	public String getRegistrationNo(){
		return registrationNo;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setBooKingOTP(String booKingOTP){
		this.booKingOTP = booKingOTP;
	}

	public String getBooKingOTP(){
		return booKingOTP;
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
			"CustomerBookingStatusResponse{" + 
			"img = '" + img + '\'' + 
			",vModel = '" + vModel + '\'' + 
			",pMobNo = '" + pMobNo + '\'' + 
			",vPhoto = '" + vPhoto + '\'' + 
			",registrationNo = '" + registrationNo + '\'' + 
			",id = '" + id + '\'' + 
			",booKingOTP = '" + booKingOTP + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}