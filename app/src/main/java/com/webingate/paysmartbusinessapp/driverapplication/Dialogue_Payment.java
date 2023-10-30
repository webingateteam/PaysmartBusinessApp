package com.webingate.paysmartbusinessapp.driverapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.widget.AppCompatButton;

import butterknife.BindView;
import butterknife.OnClick;
import com.webingate.paysmartbusinessapp.R;
public class Dialogue_Payment extends Dialog {

    @BindView(R.id.bank_account)
    RadioButton bankAccount;
    @BindView(R.id.debit_creditcard)
    RadioButton debitCreditcard;
    @BindView(R.id.dialog_payment_type)
    RadioGroup dialogPaymentType;
    @BindView(R.id.button_payment_submit)
    AppCompatButton buttonPaymentSubmit;

    public Activity c;


    public Dialogue_Payment(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogue_payments);
        setTitle(R.string.add_payment_method_title);

    }
    @OnClick({R.id.button_payment_submit,R.id.payment_type,R.id.bank_account,R.id.debit_creditcard})
    void Onclick(View v){
        switch (v.getId()){
            case R.id.button_payment_submit:
                if (bankAccount.isChecked()) {
                    c.startActivityForResult(new Intent(c, Payment_Account.class), 42);
                } else if (debitCreditcard.isChecked()) {
                    c.startActivityForResult(new Intent(c, Payment_Card.class), 42);
                }
                dismiss();
                break;
        }
    }
    //Toast.makeText(c,v.get, Toast.LENGTH_SHORT).show();


}