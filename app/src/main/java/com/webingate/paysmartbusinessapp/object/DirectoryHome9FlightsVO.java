package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

public class DirectoryHome9FlightsVO {

    @SerializedName("id")
    private String id;

    @SerializedName("image")
    private String image;

    @SerializedName("country")
    private String country;

    @SerializedName("price")
    private String price;

    @SerializedName("duration")
    private String duration;

    @SerializedName("description")
    private String description;

    public DirectoryHome9FlightsVO(String id, String image, String country, String price, String duration, String description) {
        this.id = id;
        this.image = image;
        this.country = country;
        this.price = price;
        this.duration = duration;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getCountry() {
        return country;
    }

    public String getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }
}
