package com.webingate.paysmartbusinessapp.customerapp;

import android.app.Activity;
import android.app.Dialog;
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
public class RatingBarDialogue extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    private static final int GETFEEDBACK = 3;

    @BindView(R.id.ratingid)
    TextView ratingid;
    @BindView(R.id.dialog_ratingbar)
    RatingBar feedback;
    @BindView(R.id.input_comments)
    EditText comments;
    @BindView(R.id.submit)
    Button submit;
    Ratingfinished ratingfinished;
    public interface Ratingfinished{
        public void Rating(String rating,String comments);
    }
    public RatingBarDialogue(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        ratingfinished= (Ratingfinished) a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ratingbar);
        ButterKnife.bind(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (comments.getText().toString().matches("")) {
            Toast.makeText(getContext(), "Please Provide Your Comments", Toast.LENGTH_SHORT).show();
        } else {
          //  ApplicationConstants.rating = feedback.getRating() + "";
          //  ApplicationConstants.comments = comments.getText().toString();
            ratingfinished.Rating(comments.getText().toString(),feedback.getRating() + "");
          //  CurrentTrip.tripFlag = GETFEEDBACK;
            dismiss();
        }
    }
}