package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/4/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class UserProfile {
    @SerializedName("name")
    public String name;

    @SerializedName("image")
    public String imageName;

    public UserProfile(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }
}
