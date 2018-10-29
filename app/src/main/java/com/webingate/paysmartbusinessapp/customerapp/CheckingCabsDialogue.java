package com.webingate.paysmartbusinessapp.customerapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;

public class CheckingCabsDialogue extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.minsec)
    TextView minsec;
    @BindView(R.id.displaytime)
    TextView timer;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    private CountDownTimer countDownTimer;
    private ProgressBar pb;
    checkingcabsDialogue checkingcabsDialogue;
    public interface checkingcabsDialogue{
        public void DialogueCancelled();
    }

    public CheckingCabsDialogue(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        checkingcabsDialogue= (CheckingCabsDialogue.checkingcabsDialogue) a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.checking_cabs);
        ButterKnife.bind(this);
        name.setText("Checking Cabs\n Please Wait....");
        countDownTimer = new CountDownTimer(240000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                    timer.setText((millisUntilFinished / 60000) + ":" + String.format("%02d", (millisUntilFinished / 1000) % 60));

            }

            @Override
            public void onFinish() {

                checkingcabsDialogue.DialogueCancelled();
                dismiss();
                // Toast.makeText(getContext(),"Ön finish",Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
        //cancelling trip
        checkingcabsDialogue.DialogueCancelled();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}