package com.webingate.paysmartbusinessapp.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class MakepaymentResponse{

	@SerializedName("PaymentModeId")
	private int paymentModeId;

	@SerializedName("Id")
	private int id;

	@SerializedName("GatewayTransId")
	private String gatewayTransId;

	public void setPaymentModeId(int paymentModeId){
		this.paymentModeId = paymentModeId;
	}

	public int getPaymentModeId(){
		return paymentModeId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setGatewayTransId(String gatewayTransId){
		this.gatewayTransId = gatewayTransId;
	}

	public String getGatewayTransId(){
		return gatewayTransId;
	}

	@Override
 	public String toString(){
		return 
			"MakepaymentResponse{" + 
			"paymentModeId = '" + paymentModeId + '\'' + 
			",id = '" + id + '\'' + 
			",gatewayTransId = '" + gatewayTransId + '\'' + 
			"}";
		}
}