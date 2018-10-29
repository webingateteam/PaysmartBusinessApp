package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class VehicleDetailsTable1Item {

	@SerializedName("DocTypeId")
	private int docTypeId;

	@SerializedName("PastDue")
	private int pastDue;

	@SerializedName("IsExpired")
	private int isExpired;

	@SerializedName("DocumentNo")
	private Object documentNo;

	@SerializedName("UpdatedById")
	private Object updatedById;

	@SerializedName("docType")
	private String docType;

	@SerializedName("dueDate")
	private Object dueDate;

	@SerializedName("VehicleId")
	private int vehicleId;

	@SerializedName("DocumentNo2")
	private Object documentNo2;

	@SerializedName("Column1")
	private Object column1;

	@SerializedName("IsApproved")
	private int isApproved;

	@SerializedName("expiryDate")
	private Object expiryDate;

	@SerializedName("docName")
	private String docName;

	@SerializedName("CreatedById")
	private Object createdById;

	@SerializedName("uploaded")
	private int uploaded;

	@SerializedName("Id")
	private int id;

	@SerializedName("IsVerified")
	private Object isVerified;

	public void setDocTypeId(int docTypeId){
		this.docTypeId = docTypeId;
	}

	public int getDocTypeId(){
		return docTypeId;
	}

	public void setPastDue(int pastDue){
		this.pastDue = pastDue;
	}

	public int getPastDue(){
		return pastDue;
	}

	public void setIsExpired(int isExpired){
		this.isExpired = isExpired;
	}

	public int getIsExpired(){
		return isExpired;
	}

	public void setDocumentNo(Object documentNo){
		this.documentNo = documentNo;
	}

	public Object getDocumentNo(){
		return documentNo;
	}

	public void setUpdatedById(Object updatedById){
		this.updatedById = updatedById;
	}

	public Object getUpdatedById(){
		return updatedById;
	}

	public void setDocType(String docType){
		this.docType = docType;
	}

	public String getDocType(){
		return docType;
	}

	public void setDueDate(Object dueDate){
		this.dueDate = dueDate;
	}

	public Object getDueDate(){
		return dueDate;
	}

	public void setVehicleId(int vehicleId){
		this.vehicleId = vehicleId;
	}

	public int getVehicleId(){
		return vehicleId;
	}

	public void setDocumentNo2(Object documentNo2){
		this.documentNo2 = documentNo2;
	}

	public Object getDocumentNo2(){
		return documentNo2;
	}

	public void setColumn1(Object column1){
		this.column1 = column1;
	}

	public Object getColumn1(){
		return column1;
	}

	public void setIsApproved(int isApproved){
		this.isApproved = isApproved;
	}

	public int getIsApproved(){
		return isApproved;
	}

	public void setExpiryDate(Object expiryDate){
		this.expiryDate = expiryDate;
	}

	public Object getExpiryDate(){
		return expiryDate;
	}

	public void setDocName(String docName){
		this.docName = docName;
	}

	public String getDocName(){
		return docName;
	}

	public void setCreatedById(Object createdById){
		this.createdById = createdById;
	}

	public Object getCreatedById(){
		return createdById;
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

	public void setIsVerified(Object isVerified){
		this.isVerified = isVerified;
	}

	public Object getIsVerified(){
		return isVerified;
	}

	@Override
	public String toString(){
		return
				"PendingDocsResponce{" +
						"docTypeId = '" + docTypeId + '\'' +
						",pastDue = '" + pastDue + '\'' +
						",isExpired = '" + isExpired + '\'' +
						",documentNo = '" + documentNo + '\'' +
						",updatedById = '" + updatedById + '\'' +
						",docType = '" + docType + '\'' +
						",dueDate = '" + dueDate + '\'' +
						",vehicleId = '" + vehicleId + '\'' +
						",documentNo2 = '" + documentNo2 + '\'' +
						",column1 = '" + column1 + '\'' +
						",isApproved = '" + isApproved + '\'' +
						",expiryDate = '" + expiryDate + '\'' +
						",docName = '" + docName + '\'' +
						",createdById = '" + createdById + '\'' +
						",uploaded = '" + uploaded + '\'' +
						",id = '" + id + '\'' +
						",isVerified = '" + isVerified + '\'' +
						"}";
	}
}