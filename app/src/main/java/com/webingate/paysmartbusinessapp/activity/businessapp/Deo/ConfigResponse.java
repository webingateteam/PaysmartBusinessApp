package com.webingate.paysmartbusinessapp.activity.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class ConfigResponse {
    @SerializedName("Name")
    private String Name;

    @SerializedName("Description")
    private String Description;

    @SerializedName("Active")
    private String Active;

    @SerializedName("Code")
    private String Code;

    @SerializedName("description")
    private String description;

    @SerializedName("Id")
    private int Id;

    public String getName(){
        return Name;
    }
    public String getDescription(){
        return Description;
    }
    public String getActive(){
        return Active;
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
                "ConfigResponse{" +
                        "Name = '" + Name + '\'' +
                        "Description='" + Description + '\'' +
                        "Active='" + Active + '\'' +
                        "}";

    }
}
