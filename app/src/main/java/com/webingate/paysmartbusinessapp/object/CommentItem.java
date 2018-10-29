package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 22/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class CommentItem {

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

    @SerializedName("user_image")
    public String userImage;

    @SerializedName("total_like")
    public String totalLike;

    @SerializedName("total_share")
    public String totalShare;

    public CommentItem(String userName, String totalRating, String comment, String region, String added, String ago, String userImage, String totalLike, String totalShare) {
        this.userName = userName;
        this.totalRating = totalRating;
        this.comment = comment;
        this.region = region;
        this.added = added;
        this.ago = ago;
        this.userImage = userImage;
        this.totalLike = totalLike;
        this.totalShare = totalShare;
    }
}
