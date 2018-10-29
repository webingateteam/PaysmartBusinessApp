package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("name")
    public String name;

    @SerializedName("desc")
    public String desc;

    @SerializedName("image")
    public String imageName;

    @SerializedName("flag")
    public String flagName;

    public boolean isGroupHeader = false;

    public Country(String name) {
        this.name = name;
        this.isGroupHeader = true;
    }

    public Country(String name, String desc, String imageName, String flagName) {
        this.name = name;
        this.desc = desc;
        this.imageName = imageName;
        this.flagName = flagName;
    }
}