package com.webingate.paysmartbusinessapp.customerapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.webingate.paysmartbusinessapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dialogue_Payment extends Dialog {

    public Activity c;
    @BindView(R.id.bank_account)
    RadioButton bankAccount;
    @BindView(R.id.debit_creditcard)
    RadioButton debit_creditcard;
    @BindView(R.id.dialog_payment_type)
    RadioGroup paymentType;
    @BindView(R.id.button_payment_submit)
    AppCompatButton submit;

    public Dialogue_Payment(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogue_payments);
        ButterKnife.bind(this);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.button_payment_submit:
                        if (bankAccount.isChecked()) {
                            c.startActivity(new Intent(c, Payment_Account.class));
                        } else if (debit_creditcard.isChecked()) {
                            c.startActivity(new Intent(c, Payment_Card.class));
                        }
                        dismiss();
                        break;


                }
            }
        };
        paymentType.setOnClickListener(onClickListener);
        submit.setOnClickListener(onClickListener);
        bankAccount.setOnClickListener(onClickListener);
        debit_creditcard.setOnClickListener(onClickListener);

    }
    //Toast.makeText(c,v.get, Toast.LENGTH_SHORT).show();


}