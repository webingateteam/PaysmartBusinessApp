package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 8/18/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class Course {
    @SerializedName("title")
    public String title;

    @SerializedName("desc")
    public String desc;

    @SerializedName("category")
    public String category;

    @SerializedName("length")
    public String length;

    @SerializedName("image")
    public String image;

    @SerializedName("viewCount")
    public String viewCount;

    @SerializedName("status")
    public String status;

    @SerializedName("percent")
    public String percent;

    public Course(String title, String desc, String category, String length, String image, String viewCount, String status, String percent) {
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.length = length;
        this.image = image;
        this.viewCount = viewCount;
        this.status = status;
        this.percent = percent;
    }
}
