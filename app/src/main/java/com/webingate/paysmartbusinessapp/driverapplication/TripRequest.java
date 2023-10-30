package com.webingate.paysmartbusinessapp.driverapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;

public class TripRequest extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;

    TextView name;
    TextView pickLocation;
    TextView destLocation;
    TextView minsec;
    TextView timer;
    TextView timerNew;
    AppCompatButton yes;
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
        //ButterKnife.bind(this);

        name = findViewById(R.id.name);
        pickLocation = findViewById(R.id.picklocation);
        destLocation = findViewById(R.id.droplocation);
        minsec = findViewById(R.id.minsec);
        timer = findViewById(R.id.displaytime);
        timerNew=findViewById(R.id.time);
        yes = findViewById(R.id.accept);
        no = findViewById(R.id.decline);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        name.setText("Booking No - " + ApplicationConstants.bNo);
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