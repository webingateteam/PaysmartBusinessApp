package com.webingate.paysmartbusinessapp.activity.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class RegisterBusinessUsers {
    @SerializedName("flag")
    private String flag;

    @SerializedName("Usename")
    private String Usename;

    @SerializedName("AuthTypeId")
    private String AuthTypeId;

    @SerializedName("Password")
    private String Password;

    @SerializedName("Mobilenumber")
    private String Mobilenumber;

    @SerializedName("Email")
    private String Email;

    @SerializedName("CountryId")
    private String CountryId;

    @SerializedName("CCode")
    private String CCode;

    @SerializedName("UserAccountNo")
    private String UserAccountNo;

    @SerializedName("UserTypeId")
    private String UserTypeId;


    public String getusername(){
        return Usename;
    }


    @Override
    public String toString(){
        return
                "RegisterBusinessAppusersResponse{" +
                        "Usename = '" + Usename + '\'' +
                        "}";
    }
}
