package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class PendingDocsTableItem {

	@SerializedName("DocTypeId")
	private int docTypeId;

	@SerializedName("IsMandatory")
	private int isMandatory;

	@SerializedName("Name")
	private String name;

	public void setDocTypeId(int docTypeId){
		this.docTypeId = docTypeId;
	}

	public int getDocTypeId(){
		return docTypeId;
	}

	public void setIsMandatory(int isMandatory){
		this.isMandatory = isMandatory;
	}

	public int getIsMandatory(){
		return isMandatory;
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
			"PendingDocsTableItem{" +
			"docTypeId = '" + docTypeId + '\'' + 
			",isMandatory = '" + isMandatory + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}