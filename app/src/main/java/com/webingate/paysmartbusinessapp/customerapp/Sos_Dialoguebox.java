package com.webingate.paysmartbusinessapp.customerapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;
public class Sos_Dialoguebox extends Dialog {

    public Activity c;

    @BindView(R.id.ambulance)
    RadioButton ambulance;
    @BindView(R.id.driver_misbehaviour)
    RadioButton driver_misbehave;
    @BindView(R.id.forgot_belongings)
    RadioButton forgot_belongings;
    @BindView(R.id.other)
    RadioButton other;
    @BindView(R.id.payment_type)
    RadioGroup payments;
    @BindView(R.id.text)
    EditText text;
    @BindView(R.id.submit)
    Button confirm;

    public Sos_Dialoguebox(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sos_options_dialogue);
        ButterKnife.bind(this);
        text.setVisibility(View.GONE);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.other:
                        text.setVisibility(View.VISIBLE);
                        //  Toast.makeText(c, "Others", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ambulance:
                        text.setVisibility(View.GONE);
                        //  Toast.makeText(c, "Others", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.driver_misbehaviour:
                        text.setVisibility(View.GONE);
                        //  Toast.makeText(c, "Others", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.forgot_belongings:
                        text.setVisibility(View.GONE);
                        //  Toast.makeText(c, "Others", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.submit:
                        dismiss();
                        break;

                }
            }
        };
        other.setOnClickListener(onClickListener);
        ambulance.setOnClickListener(onClickListener);
        driver_misbehave.setOnClickListener(onClickListener);
        forgot_belongings.setOnClickListener(onClickListener);
        confirm.setOnClickListener(onClickListener);

    }
    //Toast.makeText(c,v.get, Toast.LENGTH_SHORT).show();


}