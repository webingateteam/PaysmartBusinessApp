package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/17/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class FinancePersonalLog {

    @SerializedName("title")
    public String title;

    @SerializedName("amount")
    public String amount;

    @SerializedName("date")
    public String date;

    @SerializedName("type")
    public String type;

    @SerializedName("icon")
    public String icon;

    public FinancePersonalLog(String title, String amount, String date, String type, String icon) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.icon = icon;
    }
}
