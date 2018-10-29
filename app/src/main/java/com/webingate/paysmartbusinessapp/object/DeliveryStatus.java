package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/20/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class DeliveryStatus {

    @SerializedName("date")
    public String date;

    @SerializedName("time")
    public String time;

    @SerializedName("icon")
    public String icon;

    @SerializedName("location")
    public String location;

    @SerializedName("remark")
    public String remark;

    public DeliveryStatus(String date, String time, String icon, String location, String remark) {
        this.date = date;
        this.time = time;
        this.icon = icon;
        this.location = location;
        this.remark = remark;
    }
}
