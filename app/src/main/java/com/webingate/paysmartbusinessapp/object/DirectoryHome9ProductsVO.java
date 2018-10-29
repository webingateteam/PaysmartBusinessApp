package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

public class DirectoryHome9ProductsVO {

    @SerializedName("id")
    private String id;

    @SerializedName("icon")
    private String icon;

    @SerializedName("name")
    private String name;

    public DirectoryHome9ProductsVO(String id, String icon, String name) {
        this.id = id;
        this.icon = icon;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }
}
