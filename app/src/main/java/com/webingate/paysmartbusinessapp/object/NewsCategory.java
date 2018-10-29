package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 8/9/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class NewsCategory {

    @SerializedName("category")
    public String category;

    @SerializedName("category_image")
    public String categoryImage;

    @SerializedName("is_check")
    public String isCheck;

    public NewsCategory(String category, String categoryImage, String isCheck) {
        this.category = category;
        this.categoryImage = categoryImage;
        this.isCheck = isCheck;
    }
}
