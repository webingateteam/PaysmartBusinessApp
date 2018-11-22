package com.webingate.paysmartbusinessapp.activity.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class WalletBalanceResponse {

	@SerializedName("Amount")
	private String amount;

	@SerializedName("amnt")
	private String amnt;

	@SerializedName("StatusId")
	private String statusId;

	@SerializedName("Mobilenumber")
	private String mobilenumber;

	@SerializedName("Date")
	private String Date;

	@SerializedName("Comments")
	private String Comments;

	@SerializedName("TransactionId")
	private String TransactionId;


	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}
	public String getAmt(){
		return amnt;
	}

	public void setStatusId(String statusId){
		this.statusId = statusId;
	}

	public String getStatusId(){
		return statusId;
	}

	public void setMobilenumber(String mobilenumber){
		this.mobilenumber = mobilenumber;
	}

	public String getMobilenumber(){
		return mobilenumber;
	}
	public String getDate(){return Date;}
	public String getComments(){return Comments;}
	public String getTransactionId(){return TransactionId;}

	@Override
 	public String toString(){
		return 
			"WalletBalanceResponse{" +
			"Amount = '" + amount + '\'' +
					"amnt = '" + amnt + '\'' +
			",statusId = '" + statusId + '\'' + 
			",mobilenumber = '" + mobilenumber + '\'' +
					",Date = '" + Date + '\'' +
					",Comments = '" + Comments + '\'' +
					",TransactionId = '" + TransactionId + '\'' +
					"}";
		}

}