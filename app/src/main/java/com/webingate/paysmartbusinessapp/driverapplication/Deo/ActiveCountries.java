package com.webingate.paysmartbusinessapp.driverapplication.Deo;


import com.google.gson.annotations.SerializedName;

public class ActiveCountries {

    @SerializedName("Name")
    private String Name;

    @SerializedName("Description")
    private String description;

    @SerializedName("ISOCode")
    private String ISOCode;

    @SerializedName("listkey")
    private String listkey;

    @SerializedName("listvalue")
    private String listvalue;

    @SerializedName("Code")
    private String code;

    @SerializedName("Id") private int Id;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setISOCode(String ISOCode){
        this.ISOCode = ISOCode;
    }

    public String getISOCode(){
        return ISOCode;
    }

    public int getId(){
        return Id;
    }

    public void setName(String Name){
        this.Name = Name;
    }


    public String getName(){
        return Name;
    }

    @Override
    public String toString(){
        return
                "ActiveCountriesResponse{" +
                        "Name = '" + Name + '\'' +
                        ",ISOCode = '" + ISOCode + '\'' +
                        "}";
    }

}