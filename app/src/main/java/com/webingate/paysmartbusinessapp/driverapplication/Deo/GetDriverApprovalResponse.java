package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class GetDriverApprovalResponse{

	@SerializedName("PMobNo")
	private String pMobNo;

	@SerializedName("IsApproved")
	private int isApproved;

	public void setPMobNo(String pMobNo){
		this.pMobNo = pMobNo;
	}

	public String getPMobNo(){
		return pMobNo;
	}

	public void setIsApproved(int isApproved){
		this.isApproved = isApproved;
	}

	public int getIsApproved(){
		return isApproved;
	}

	@Override
 	public String toString(){
		return 
			"GetDriverApprovalResponse{" + 
			"pMobNo = '" + pMobNo + '\'' + 
			",isApproved = '" + isApproved + '\'' + 
			"}";
		}
}