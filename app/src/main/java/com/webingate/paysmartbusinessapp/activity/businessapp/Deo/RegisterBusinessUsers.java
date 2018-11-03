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

    @SerializedName("Emailotp")
    private String Emailotp;

    @SerializedName("Id")
    private int Id;
    @SerializedName("Mobileotp")
    private String Mobileotp;

    @SerializedName("Code")
    private String Code;

    @SerializedName("description")
    private String description;


    public String getemail(){
        return Email;
    }
    public int getusreid(){return Id;}
    public String getemailotp(){
        return Emailotp;
    }
    public String getmotp(){return Mobileotp;}
    public String getmnumber(){return Mobilenumber;}
    public String getCode() {
        return Code;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return
                "RegisterBusinessAppusersResponse{" +
                        "Id = '" + Id + '\'' +
                        "Email = '" + Email + '\'' +
                        "Usename = '" + Emailotp + '\'' +
                        "Usename = '" + Usename + '\'' +
                        "Mobileotp = '" + Mobileotp + '\'' +
                        "Mobileotp = '" + Mobilenumber + '\'' +
                        "}";
    }
}
