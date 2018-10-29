package com.webingate.paysmartbusinessapp.driverapplication.Deo;

import com.google.gson.annotations.SerializedName;

public class CurrentStateResponse{

	@SerializedName("CurrentState")
	private int currentState;

	public void setCurrentState(int currentState){
		this.currentState = currentState;
	}

	public int getCurrentState(){
		return currentState;
	}

	@Override
 	public String toString(){
		return 
			"CurrentStateResponse{" + 
			"currentState = '" + currentState + '\'' + 
			"}";
		}
}