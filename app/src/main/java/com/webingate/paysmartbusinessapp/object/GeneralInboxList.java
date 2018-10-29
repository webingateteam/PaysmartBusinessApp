package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/21/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class GeneralInboxList {

    @SerializedName("name")
    public String name;

    @SerializedName("caption")
    public String caption;

    @SerializedName("image")
    public String image;

    @SerializedName("detail")
    public String detail;

    @SerializedName("time")
    public String time;

    public GeneralInboxList(String name, String caption, String image, String detail, String time) {
        this.name = name;
        this.caption = caption;
        this.image = image;
        this.detail = detail;
        this.time = time;
    }
}
