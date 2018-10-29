package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

public class DirectoryHome9PromotionsVO {

    @SerializedName("id")
    private String id;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    public DirectoryHome9PromotionsVO(String id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
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
}
