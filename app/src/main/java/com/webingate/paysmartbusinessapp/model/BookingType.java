package com.webingate.paysmartbusinessapp.model;

import android.graphics.drawable.Drawable;

public class BookingType {

    public int image;
    public Drawable imageDrw;
    public String name;
    public String email;
    public boolean section = false;

    public BookingType() {
    }

    public BookingType(String name, boolean section) {
        this.name = name;
        this.section = section;
    }

}
