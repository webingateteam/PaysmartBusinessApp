package com.webingate.paysmartbusinessapp.activity.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class ApprovalResponse {
    @SerializedName("VehicleCode")
    private String VehicleCode;

    @SerializedName("Email")
    private String Email;

    @SerializedName("Code")
    private String Code;

    @SerializedName("description")
    private String description;

    @SerializedName("Id")
    private int Id;

    public String getVehicleCode(){
        return VehicleCode;
    }
    public String getCode() {
        return Code;
    }
    public String getdescription() {
        return description;
    }
    public int getId() {
        return Id;
    }
    public String toString(){
        return
                "ApprovalResponse{" +
                        "VehicleCode = '" + VehicleCode + '\'' +
                        "}";

    }
}
