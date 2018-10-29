package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class DriverDetailsTable1Item {

	@SerializedName("DocTypeId")
	private String docTypeId;

	@SerializedName("PastDue")
	private String pastDue;

	@SerializedName("IsExpired")
	private String isExpired;

	@SerializedName("DocumentNo")
	private String documentNo;

	@SerializedName("UpdatedById")
	private String updatedById;

	@SerializedName("docType")
	private String docType;

	@SerializedName("dueDate")
	private String dueDate;

	@SerializedName("DocumentNo2")
	private String documentNo2;

	@SerializedName("IsApproved")
	private String isApproved;

	@SerializedName("expiryDate")
	private String expiryDate;

	@SerializedName("DriverId")
	private String driverId;

	@SerializedName("docName")
	private String docName;

	@SerializedName("CreatedById")
	private String createdById;

	@SerializedName("uploaded")
	private String uploaded;

	@SerializedName("Id")
	private String id;

	@SerializedName("daysLeft")
	private String daysLeft;

	@SerializedName("IsVerified")
	private String isVerified;

	public void setDocTypeId(String docTypeId){
		this.docTypeId = docTypeId;
	}

	public String getDocTypeId(){
		return docTypeId;
	}

	public void setPastDue(String pastDue){
		this.pastDue = pastDue;
	}

	public String getPastDue(){
		return pastDue;
	}

	public void setIsExpired(String isExpired){
		this.isExpired = isExpired;
	}

	public String getIsExpired(){
		return isExpired;
	}

	public void setDocumentNo(String documentNo){
		this.documentNo = documentNo;
	}

	public String getDocumentNo(){
		return documentNo;
	}

	public void setUpdatedById(String updatedById){
		this.updatedById = updatedById;
	}

	public String getUpdatedById(){
		return updatedById;
	}

	public void setDocType(String docType){
		this.docType = docType;
	}

	public String getDocType(){
		return docType;
	}

	public void setDueDate(String dueDate){
		this.dueDate = dueDate;
	}

	public String getDueDate(){
		return dueDate;
	}

	public void setDocumentNo2(String documentNo2){
		this.documentNo2 = documentNo2;
	}

	public String getDocumentNo2(){
		return documentNo2;
	}

	public void setIsApproved(String isApproved){
		this.isApproved = isApproved;
	}

	public String getIsApproved(){
		return isApproved;
	}

	public void setExpiryDate(String expiryDate){
		this.expiryDate = expiryDate;
	}

	public String getExpiryDate(){
		return expiryDate;
	}

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}

	public String getDriverId(){
		return driverId;
	}

	public void setDocName(String docName){
		this.docName = docName;
	}

	public String getDocName(){
		return docName;
	}

	public void setCreatedById(String createdById){
		this.createdById = createdById;
	}

	public String getCreatedById(){
		return createdById;
	}

	public void setUploaded(String uploaded){
		this.uploaded = uploaded;
	}

	public String getUploaded(){
		return uploaded;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDaysLeft(String daysLeft){
		this.daysLeft = daysLeft;
	}

	public String getDaysLeft(){
		return daysLeft;
	}

	public void setIsVerified(String isVerified){
		this.isVerified = isVerified;
	}

	public String getIsVerified(){
		return isVerified;
	}

	@Override
 	public String toString(){
		return 
			"DriverDetailsTable1Item{" +
			"docTypeId = '" + docTypeId + '\'' + 
			",pastDue = '" + pastDue + '\'' + 
			",isExpired = '" + isExpired + '\'' + 
			",documentNo = '" + documentNo + '\'' + 
			",updatedById = '" + updatedById + '\'' + 
			",docType = '" + docType + '\'' + 
			",dueDate = '" + dueDate + '\'' + 
			",documentNo2 = '" + documentNo2 + '\'' + 
			",isApproved = '" + isApproved + '\'' + 
			",expiryDate = '" + expiryDate + '\'' + 
			",driverId = '" + driverId + '\'' + 
			",docName = '" + docName + '\'' + 
			",createdById = '" + createdById + '\'' + 
			",uploaded = '" + uploaded + '\'' + 
			",id = '" + id + '\'' + 
			",daysLeft = '" + daysLeft + '\'' + 
			",isVerified = '" + isVerified + '\'' + 
			"}";
		}
}