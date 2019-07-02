package com.webingate.paysmartbusinessapp.activity.businessapp.Deo;

import com.google.gson.annotations.SerializedName;

public class MOTPVerification {

    @SerializedName("Mobileotp")
    private String Mobileotp;

    @SerializedName("Code")
    private String code;

    @SerializedName("description")
    private String description;

    public String getCode() {
        return code;
    }
    public String getMobileotp() {
        return Mobileotp;
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
}
