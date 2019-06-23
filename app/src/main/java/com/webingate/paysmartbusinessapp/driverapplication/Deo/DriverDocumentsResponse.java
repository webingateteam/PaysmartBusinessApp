package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverDocumentsResponse {

	@SerializedName("docname")
	private String docname;

	@SerializedName("DocumentNo")
	private int DocumentNo;

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

	public String getDocname(){
		return docname;
	}
	public int getDocumentNo(){
		return DocumentNo;
	}

	@Override
 	public String toString(){
		return 
			"DriverDocumentsResponse{" +
			"docname = '" + docname + '\'' +
					"DocumentNo = '" + DocumentNo + '\'' +
					"}";
		}
}