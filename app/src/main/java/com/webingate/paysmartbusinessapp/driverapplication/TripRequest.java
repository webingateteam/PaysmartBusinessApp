package com.webingate.paysmartbusinessapp.driverapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;

public class TripRequest extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.picklocation)
    TextView pickLocation;
    @BindView(R.id.droplocation)
    TextView destLocation;
    @BindView(R.id.minsec)
    TextView minsec;
    @BindView(R.id.displaytime)
    TextView timer;
    @BindView(R.id.time)
    TextView timerNew;
    @BindView(R.id.accept)
    AppCompatButton yes;
    @BindView(R.id.decline)
    AppCompatButton no;
    private CountDownTimer countDownTimer;
    private static final int ACCEPTBOOKING = 4;
    private static final int REJECTBOOKING = 5;
    Triprequest triprequest;
    public interface Triprequest{
        public void TripAccepted();
        public void TripRejected();

    }

   // public TripRequest(Activity a, Home fragment) {
   public TripRequest(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        triprequest= (Triprequest) c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alertdialogue);
        ButterKnife.bind(this);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        name.setText("BookingId - " + ApplicationConstants.bookingId);
        destLocation.setText(ApplicationConstants.destlatitude + "  " + ApplicationConstants.destlongitude);
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("00:" + (millisUntilFinished / 1000));
                timerNew.setText(millisUntilFinished / 1000 + " SECONDS LEFT TO ACCEPT");
            }

            @Override
            public void onFinish() {
                ApplicationConstants.tripflag = REJECTBOOKING;
                dismiss();
                // Toast.makeText(getContext(),"Ön finish",Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
        triprequest.TripRejected();
        ApplicationConstants.tripflag = REJECTBOOKING;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accept:
                ApplicationConstants.tripflag = ACCEPTBOOKING;
                triprequest.TripAccepted();
                countDownTimer.cancel();
                dismiss();
                break;
            case R.id.decline:
                ApplicationConstants.tripflag = REJECTBOOKING;
                triprequest.TripRejected();
                countDownTimer.cancel();
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}