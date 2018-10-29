package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 6/15/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class UserReview {

    @SerializedName("user_image")
    public String userImage;

    @SerializedName("user_name")
    public String userName;

    @SerializedName("total_rating")
    public String totalRating;

    @SerializedName("comment")
    public String comment;

    @SerializedName("region")
    public String region;

    @SerializedName("added")
    public String added;

    @SerializedName("ago")
    public String ago;

    public UserReview(String userImage, String userName, String totalRating, String comment, String region, String added, String ago) {
        this.userImage = userImage;
        this.userName = userName;
        this.totalRating = totalRating;
        this.comment = comment;
        this.region = region;
        this.added = added;
        this.ago = ago;
    }
}
