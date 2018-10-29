package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/21/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class TimelineSocial {

    @SerializedName("date")
    public String date;

    @SerializedName("name")
    public String name;

    @SerializedName("user_image")
    public String user_image;

    @SerializedName("detail")
    public String detail;

    @SerializedName("icon")
    public String icon;

    @SerializedName("image")
    public String image;

    @SerializedName("type")
    public String type;

    public TimelineSocial(String date, String name, String user_image, String detail, String icon, String image, String type) {
        this.date = date;
        this.name = name;
        this.user_image = user_image;
        this.detail = detail;
        this.icon = icon;
        this.image = image;
        this.type = type;
    }
}
