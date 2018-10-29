package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 8/24/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class TimelineTodo {
    @SerializedName("time")
    public String time;

    @SerializedName("interval")
    public String interval;

    @SerializedName("note")
    public String note;

    @SerializedName("color")
    public String color;

    @SerializedName("is_important")
    public Boolean isImportant;

    public TimelineTodo(String time, String interval, String note, String color, Boolean isImportant) {
        this.time = time;
        this.interval = interval;
        this.note = note;
        this.color = color;
        this.isImportant = isImportant;
    }
}
