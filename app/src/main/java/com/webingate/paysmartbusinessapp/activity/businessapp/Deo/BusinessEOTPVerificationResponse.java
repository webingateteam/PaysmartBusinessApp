package com.webingate.paysmartbusinessapp.activity.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class BusinessEOTPVerificationResponse {

    @SerializedName("Email")
    private String Email;

    @SerializedName("EVerificationCode")
    private String EVerificationCode;

    @SerializedName("userId")
    private String userId;


    public String getEmail(){
        return Email;
    }
    @Override
    public String toString(){
        return
                "BusinessETOPResponse{" +
                        "Email = '" + Email + '\'' +
                        "}";
    }
}
