package com.webingate.paysmartbusinessapp.activity.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class BusinessEOTPVerificationResponse {

    @SerializedName("Email")
    private String Email;

    @SerializedName("Emailotp")
    private String Emailotp;

    @SerializedName("userId")
    private String userId;


    public String getEmail(){
        return Email;
    }
    public String getEmailotp(){
        return Emailotp;
    }
    @Override
    public String toString(){
        return
                "BusinessETOPResponse{" +
                        "Email = '" + Email + '\'' +
                        "}";
    }
}
