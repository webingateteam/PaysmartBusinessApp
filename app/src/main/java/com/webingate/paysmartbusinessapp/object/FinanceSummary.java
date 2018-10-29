package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/17/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class FinanceSummary {

    @SerializedName("title")
    public String title;

    @SerializedName("amount")
    public String amount;

    public FinanceSummary(String title, String amount) {
        this.title = title;
        this.amount = amount;
    }
}
