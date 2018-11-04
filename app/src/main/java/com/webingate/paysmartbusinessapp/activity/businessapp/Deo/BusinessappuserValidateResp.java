package com.webingate.paysmartbusinessapp.activity.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class BusinessappuserValidateResp {

    @SerializedName("Id")
    private int Id;

    @SerializedName("Username")
    private String Username;

    @SerializedName("Email")
    private String Email;

    @SerializedName("Mobilenumber")
    private String Mobilenumber;

    @SerializedName("StatusId")
    private int StatusId;

    @SerializedName("Firstname")
    private String Firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("AuthTypeId")
    private String AuthTypeId;

    @SerializedName("Code")
    private String Code;

    @SerializedName("description")
    private String description;

    @SerializedName("UserAccountNo")
    private String UserAccountNo;

    @SerializedName("usertypeid")
    private int usertypeid;

    public String getusernamae() { return Username;}
    public String getEmail() {
        return Email;
    }
    public String getMobilenumber() {return Mobilenumber;}
    public int  getstatusid() { return StatusId;}
    public String getauthtypeid() {
        return AuthTypeId;
    }
    public String getCode() {
        return Code;
    }
    public String getuseraccountno() {
        return UserAccountNo;
    }
    public int getusertypeid() {
        return usertypeid;
    }
    public String getDescription() {
        return description;
    }
    @Override
    public String toString(){
        return
                "BusinessappuserValidateResp{" +
                        "Id = '" + Id + '\'' +
                        "Email = '" + Email + '\'' +
                        "Usename = '" + Username + '\'' +
                        "Mobilenumber = '" + Mobilenumber + '\'' +
                        "usertypeid = '" + usertypeid + '\'' +
                        "UserAccountNo = '" + UserAccountNo + '\'' +
                        "}";
    }
}
