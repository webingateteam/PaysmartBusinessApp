/*
package com.webingate.paysmartbusinessapp.customerapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;


public class DriverDetails extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.mobileno)
    TextView mobileNo;
    @BindView(R.id.bookingotp)
    TextView bookingOTP;
    @BindView(R.id.registrationno)
    TextView regdNo;
    @BindView(R.id.vehiclemodel)
    TextView vehicelModel;


    public DriverDetails(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.driverdetailsdialogue);

        name.setText("DriverName : " + ApplicationConstants.driverName);
        mobileNo.setText("MobileNo : " + ApplicationConstants.mobNo);
        bookingOTP.setText("OTP : " + ApplicationConstants.booKingOTP);
        regdNo.setText("Vehicle NO : " + ApplicationConstants.registrationNo);
        vehicelModel.setText("Model : " + ApplicationConstants.vModel);

    }


    @Override
    public void onClick(View v) {

    }
}*/
