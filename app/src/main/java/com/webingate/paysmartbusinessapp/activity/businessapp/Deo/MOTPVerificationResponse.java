package com.webingate.paysmartbusinessapp.activity.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class MOTPVerificationResponse {
    @SerializedName("Mobilenumber")
    private String Mobilenumber;

    @SerializedName("MVerificationCode")
    private String MVerificationCode;

    @SerializedName("userId")
    private String userId;

    @SerializedName("Status")
    private int Status;

    @SerializedName("Code")
    private String Code;
    @SerializedName("Mobileotp")
    private String Mobileotp;
    @SerializedName("Email")
    private String Email;

    @SerializedName("description")
    private String description;

    public int getstatus(){
        return Status;
    }
    public String getMobilenumber(){
        return Mobilenumber;
    }
    public String getMobilotp(){
        return Mobileotp;
    }
    public String getemail(){
        return Email;
    }
    public String getCode() {
        return Code;
    }
    public String getDescription() {
        return description;
    }
    public String getMVerificationCode() {
        return MVerificationCode;
    }
    public String toString(){
        return
                "CustomerMOTPResponse{" +
                        "Mobilenumber = '" + Mobilenumber + '\'' +
                        "MOTP='" + MVerificationCode + '\'' +
                        "Status='" + Status + '\'' +
                        "Mobileotp='" + Mobileotp + '\'' +
                        "Email='" + Email + '\'' +
                        "}";

    }
}
