package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 1/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class ShopCategory {

    @SerializedName("name")
    public String name;

    @SerializedName("image")
    public String imageName;

    @SerializedName("count")
    public String count;

    public ShopCategory(String name, String imageName, String count) {
        this.name = name;
        this.imageName = imageName;
        this.count = count;
    }

}
