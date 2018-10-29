package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 9/6/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class UserHistory {

    @SerializedName("place_name")
    public String placeName;

    @SerializedName("place_image")
    public String placeImage;

    @SerializedName("comment")
    public String comment;

    @SerializedName("region")
    public String region;

    @SerializedName("added")
    public String added;

    @SerializedName("ago")
    public String ago;

    @SerializedName("action")
    public String action;

    public UserHistory(String placeName, String placeImage, String comment, String region, String added, String ago, String action) {
        this.placeName = placeName;
        this.placeImage = placeImage;
        this.comment = comment;
        this.region = region;
        this.added = added;
        this.ago = ago;
        this.action = action;
    }





}
