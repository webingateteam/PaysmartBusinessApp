package com.webingate.paysmartbusinessapp.customerapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;
public class Payments_Dialoguebox extends Dialog {


    public Activity c;
    RadioButton paymentSelected;

    @BindView(R.id.estimatedPrice)
    TextView estPrice;
    @BindView(R.id.radio_wallet)
    RadioButton radioWallet;
    @BindView(R.id.radio_net_banking)
    RadioButton radioNetBanking;
    @BindView(R.id.radio_debit_card)
    RadioButton radioDebitCard;
    @BindView(R.id.radio_credit_card)
    RadioButton radioCreditCard;
    @BindView(R.id.radio_cash)
    RadioButton radioCash;
    @BindView(R.id.payment_type)
    RadioGroup payments;
    @BindView(R.id.submit)
    Button confirm;
    String price;
    PaymentDetails paymentDetails;
    public interface PaymentDetails{
        public void PaymentDetails(String paymentType);
    }


    public Payments_Dialoguebox(Activity a,String price) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.price=price;
        paymentDetails= (PaymentDetails) a;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_options_dialogue);
        ButterKnife.bind(this);
        estPrice.setText("Estimated Price Is " + price);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = payments.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                paymentSelected = (RadioButton) findViewById(selectedId);
                switch (paymentSelected.getId()) {
                    case R.id.radio_wallet:
                        paymentDetails.PaymentDetails("93");
                        dismiss();
                        break;
                    case R.id.radio_net_banking:
                        paymentDetails.PaymentDetails("91");
                        dismiss();
                        break;
                    case R.id.radio_debit_card:
                        paymentDetails.PaymentDetails("123");
                        dismiss();
                        break;
                    case R.id.radio_credit_card:
                        paymentDetails.PaymentDetails("122");
                        dismiss();
                        break;
                    case R.id.radio_cash:
                        paymentDetails.PaymentDetails("90");
                        dismiss();
                        break;

                }

            }
        });
    }
}