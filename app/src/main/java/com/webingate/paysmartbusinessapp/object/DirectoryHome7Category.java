package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 14/6/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class DirectoryHome7Category {

    @SerializedName("name")
    public String name;

    @SerializedName("image")
    public String image;

    @SerializedName("color")
    public String color;

    public DirectoryHome7Category(String name, String image, String color) {
        this.name = name;
        this.image = image;
        this.color = color;
    }
}
