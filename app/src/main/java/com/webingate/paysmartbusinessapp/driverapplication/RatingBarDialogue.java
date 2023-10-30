package com.webingate.paysmartbusinessapp.driverapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.businessappDriverDashboardActivity;

public class RatingBarDialogue extends Dialog implements
        View.OnClickListener {
    private static final int RATETHERIDE = 9;
    public Activity c;
    public Dialog d;
    TextView ratingid;
    RatingBar dialogRatingbar;
    EditText inputComments;
    Button submit;

    private boolean isOnline;

    RatingBarInterface ratingbar;

    public interface RatingBarInterface{

        public void Rating(String rating,String comments);

    }

    public RatingBarDialogue(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        ratingbar= (RatingBarInterface) a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ratingbar);

        ratingid = findViewById(R.id.ratingid);
        dialogRatingbar = findViewById(R.id.dialog_ratingbar);
        inputComments = findViewById(R.id.input_comments);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(this);

        submit.setOnClickListener(v->{
            if (inputComments.getText().toString().matches("")) {
                Toast.makeText(getContext(), "Please Provide Your Comments", Toast.LENGTH_SHORT).show();
            } else {
                //ratingbar.Rating(dialogRatingbar.getRating() + "", inputComments.getText().toString());
                ApplicationConstants.rating = dialogRatingbar.getRating() + "";
                ApplicationConstants.comments = inputComments.getText().toString();
                MyTrips obj = new MyTrips();
                obj.Rating1();
                isOnline=false;
                getOwnerActivity().startActivity(new Intent(getContext(),businessappDriverDashboardActivity.class));
                getOwnerActivity().finish();
                dismiss();


            }
        });
    }

    @Override
    public void onClick(View v) {
        if (inputComments.getText().toString().matches("")) {
            Toast.makeText(getContext(), "Please Provide Your Comments", Toast.LENGTH_SHORT).show();
        } else {
            ratingbar.Rating(dialogRatingbar.getRating() + "",inputComments.getText().toString());
//            ApplicationConstants.rating = dialogRatingbar.getRating() + "";
//            ApplicationConstants.comments = inputComments.getText().toString();
           // ApplicationConstants.tripflag = RATETHERIDE;
            dismiss();
        }
    }


}