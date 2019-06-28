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
    public void setId(int Id){this.Id = Id;}
    public int getId(){return Id;}
    public void setUserAccountNo(String UserAccountNo){this.UserAccountNo = UserAccountNo;}
    public String getUserAccountNo() {
        return UserAccountNo;
    }

    @Override
    public String toString(){
        return
                "RegisterBusinessAppusersResponse{" +
                        "Id = '" + Id + '\'' +
                        "Email = '" + Email + '\'' +
                        "Emailotp = '" + Emailotp + '\'' +
                        "Usename = '" + Usename + '\'' +
                        "Mobileotp = '" + Mobileotp + '\'' +
                        "Mobilenumber = '" + Mobilenumber + '\'' +
                        "UserAccountNo = '" + UserAccountNo + '\'' +
                        "}";
    }
}
