package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/19/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class MediaMp3Obj {

    @SerializedName("title")
    public String title;

    @SerializedName("image")
    public String image;

    @SerializedName("singer")
    public String singer;

    @SerializedName("song")
    public String song;

    public MediaMp3Obj(String title, String image, String singer, String song) {
        this.title = title;
        this.image = image;
        this.singer = singer;
        this.song = song;
    }
}
