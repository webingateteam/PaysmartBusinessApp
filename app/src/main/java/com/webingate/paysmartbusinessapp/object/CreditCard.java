package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/18/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class CreditCard {

    @SerializedName("card_name")
    public String cardName;

    @SerializedName("card_number")
    public String cardNumber;

    @SerializedName("card_expiry_date")
    public String cardExpiryDate;

    @SerializedName("card_cvv")
    public String cardCVV;

    @SerializedName("card_type")
    public String cardType;

    @SerializedName("card_bg")
    public String cardBg;

    public CreditCard(String cardName, String cardNumber, String cardExpiryDate, String cardCVV, String cardType, String cardBg) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardCVV = cardCVV;
        this.cardType = cardType;
        this.cardBg = cardBg;
    }
}
