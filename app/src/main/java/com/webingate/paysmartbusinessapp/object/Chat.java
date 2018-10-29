package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 9/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class Chat {

    @SerializedName("id")
    public String ID;

    @SerializedName("name")
    public String Name;

    @SerializedName("text")
    public String Message;

    @SerializedName("image")
    public String Image;

    @SerializedName("time")
    public String Time;

    @SerializedName("count")
    public String Count;


    public Chat(String ID, String name, String description, String image, String count,String date) {
        this.ID = ID;
        this.Name = name;
        this.Message = description;
        this.Image = image;
        this.Count = count;
        this.Time = date;
    }
}
