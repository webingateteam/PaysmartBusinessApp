package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Panacea-Soft on 6/29/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class SectionGallery {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("images")
    public List<Image> imageList;

    public SectionGallery(String id, String name, List<Image> imageList) {
        this.id = id;
        this.name = name;
        this.imageList = imageList;
    }

    public class Image {

        @SerializedName("image_id")
        public String imageId;

        @SerializedName("image")
        public String image;
    }


}


