package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 8/19/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class EducationVideo {

    @SerializedName("title")
    public String title;

    @SerializedName("desc")
    public String desc;

    @SerializedName("image")
    public String image;

    @SerializedName("category")
    public String category;

    @SerializedName("length")
    public String length;

    @SerializedName("size")
    public String size;

    public EducationVideo(String title, String desc, String image, String category, String length, String size) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.category = category;
        this.length = length;
        this.size = size;
    }
}
