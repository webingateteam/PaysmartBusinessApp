package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/18/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class FinanceTransferTransaction {
    @SerializedName("user_to")
    public String userTo;

    @SerializedName("amount")
    public String amount;

    @SerializedName("date")
    public String date;

    @SerializedName("type")
    public String type;

    public FinanceTransferTransaction(String userTo, String amount, String date, String type) {
        this.userTo = userTo;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }
}
