package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.BusinessEOTPVerificationResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverForgotpasswordResponse;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappForgotPasswordActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String passwordotp = "pwdotpkey";

    Toast toast;
    Button resetButton;
    TextView signInTextView;
    ImageView bgImageView;

    @BindView(R.id.resetButton)
    Button sbtn;

    @BindView(R.id.s_email)
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_forgotpassword_activity);

        initUI();

        initDataBindings();

        initActions();

    }

    //region Init Functions
    private void initUI() {
        signInTextView = findViewById(R.id.signinTextView);
        resetButton = findViewById(R.id.resetButton);
        bgImageView = findViewById(R.id.bgImageView);

        sbtn=findViewById(R.id.resetButton);
        email=findViewById(R.id.s_email);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background_3;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {

        signInTextView.setOnClickListener(view -> {
           // Toast.makeText(getApplicationContext(), "Clicked Sign In", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, login_activity.class);
            startActivity(intent);
        });


//        resetButton.setOnClickListener(view -> {
//            //Toast.makeText(getApplicationContext(), "Clicked Reset.", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, customerpwdOTPVerificationActivity.class);
//            startActivity(intent);
//        });

        resetButton.setOnClickListener((View v) ->{
            //Toast.makeText(getApplicationContext(),"OTP is Resent.",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, customerMOTPVerificationActivity.class);
//            startActivity(intent);
            if(email.getText().toString().matches("")){
                Toast.makeText(getApplicationContext(),"Please Enter Email Address",Toast.LENGTH_SHORT).show();
            }
            else
            {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("Email", email.getText().toString());
//                jsonObject.addProperty("Email", "109917893890990");
                Forgotpassword(jsonObject);
            }
        });

    }

    public void Forgotpassword(JsonObject jsonObject){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .Forgotpassword1(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverForgotpasswordResponse>>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Sent Email");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            //Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DriverForgotpasswordResponse> responselist) {
                        DriverForgotpasswordResponse response=responselist.get(0);
                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(passwordotp, response.getPasswordotp());
                        Intent intent = new Intent(businessappForgotPasswordActivity.this, businessapppwdOTPVerificationActivity.class);
                        intent.putExtra("Email", response.getemail());
                        intent.putExtra("passowordotp", response.getPasswordotp());
                        startActivity(intent);
                        editor.commit();

                        //startActivity(new Intent(businessappForgotPasswordActivity.this, businessapppwdOTPVerificationActivity.class));
                        finish();
                    }
                });
    }

    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast= Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }
    //endregion
}
