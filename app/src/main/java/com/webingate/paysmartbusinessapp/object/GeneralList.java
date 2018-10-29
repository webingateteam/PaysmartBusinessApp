package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/17/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class GeneralList {

    @SerializedName("name")
    public String name;

    @SerializedName("caption")
    public String caption;

    @SerializedName("image")
    public String image;

    @SerializedName("detail")
    public String detail;

    public GeneralList(String name, String caption, String image, String detail) {
        this.name = name;
        this.caption = caption;
        this.image = image;
        this.detail = detail;
    }
}
