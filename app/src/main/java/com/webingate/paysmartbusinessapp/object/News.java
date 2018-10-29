package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 9/8/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class News {

    @SerializedName("title")
    public String title;

    @SerializedName("desc")
    public String desc;

    @SerializedName("date")
    public String date;

    @SerializedName("category")
    public String category;

    @SerializedName("ago")
    public String ago;

    @SerializedName("news_image")
    public String newsImage;

    @SerializedName("publisher")
    public String publisher;

    @SerializedName("publisher_image")
    public String publisherImage;

    @SerializedName("total_like")
    public String totalLike;

    public News(String title, String desc, String date, String category, String ago, String newsImage, String publisher, String publisherImage, String totalLike) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.category = category;
        this.ago = ago;
        this.newsImage = newsImage;
        this.publisher = publisher;
        this.publisherImage = publisherImage;
        this.totalLike = totalLike;
    }
}
