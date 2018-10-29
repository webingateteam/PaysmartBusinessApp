package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

public class DirectoryHome9PopularVO {

    @SerializedName("id")
    private String id;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    @SerializedName("place")
    private String place;

    public DirectoryHome9PopularVO(String id, String image, String name, String place) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }
}
