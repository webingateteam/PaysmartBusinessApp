package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 8/25/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class EducationCourseCollection {
    @SerializedName("name")
    public String name;

    @SerializedName("status")
    public String status;

    @SerializedName("image")
    public String image;

    @SerializedName("progress")
    public String progress;

    @SerializedName("type")
    public String type;

    @SerializedName("color")
    public String color;

    public EducationCourseCollection(String name, String status, String image, String progress, String type, String color) {
        this.name = name;
        this.status = status;
        this.image = image;
        this.progress = progress;
        this.type = type;
        this.color = color;
    }
}
