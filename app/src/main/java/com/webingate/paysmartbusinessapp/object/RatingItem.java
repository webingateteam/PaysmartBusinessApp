package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 22/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class RatingItem {

    @SerializedName("user_name")
    public String userName;

    @SerializedName("total_rating")
    public String totalRating;

    @SerializedName("title")
    public String title;

    @SerializedName("comment")
    public String comment;

    @SerializedName("region")
    public String region;

    @SerializedName("added")
    public String added;


    @SerializedName("ago")
    public String ago;

    @SerializedName("user_image")
    public String userImage;

    public RatingItem(String user_name, String total_rating, String title, String comment, String region, String added, String ago, String user_image) {
        this.userName = user_name;
        this.totalRating = total_rating;
        this.title = title;
        this.comment = comment;
        this.region = region;
        this.added = added;
        this.ago = ago;
        this.userImage = user_image;
    }
}
