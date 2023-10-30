package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverForgotpasswordResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverPasswordVerificationResponse;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessapppwdOTPVerificationActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String passwordotp = "pwdotpkey";
    public static final String UserAccountNo = "UserAccountNoKey";
    Toast toast;
    @BindView(R.id.s_passwordotp)
    EditText potp;
    @BindView(R.id.s_newpassword)
    EditText newpwd;
    String mobNo;

    ImageView bgImageView;
    Button changeButton, resendButton, submitOTPButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_pwdotpverification_activity);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mobNo = prefs.getString(UserAccountNo, null);

        initUI();

        initActions();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_true, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        initToolbar();

        bgImageView = findViewById(R.id.bgImageView);
        int id = Utils.getDrawableInt(getApplicationContext(), "Password Verification");
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);

        changeButton = findViewById(R.id.changeButton);
        resendButton = findViewById(R.id.resendButton);
        submitOTPButton = findViewById(R.id.submitOTPButton);
        newpwd = findViewById(R.id.s_newpassword);
        potp = findViewById(R.id.s_passwordotp);

    }

    private void initActions(){
        changeButton.setOnClickListener((View v) ->{
           Toast.makeText(getApplicationContext(),"Clicked Change Email.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+"email_to"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "email_subject");
            intent.putExtra(Intent.EXTRA_TEXT, "email_body");
            startActivity(intent);
        });

        resendButton.setOnClickListener((View v) ->{
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("UserAccountNo", mobNo);
            Forgotpassword(jsonObject);
            Toast.makeText(getApplicationContext(),"OTP is Resent.",Toast.LENGTH_SHORT).show();
        });

        submitOTPButton.setOnClickListener((View v) ->{
            if(potp.getText().toString().matches("")){
                Toast.makeText(getApplicationContext(),"Please Enter OTP",Toast.LENGTH_SHORT).show();
            }
            else
            {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("UserAccountNo",mobNo);
                jsonObject.addProperty("Password",newpwd.getText().toString());
                jsonObject.addProperty("Passwordotp",potp.getText().toString());
                PWDOTPVerification(jsonObject);
            }
        });
    }


    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Password Verification");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        } catch (Exception e) {
            Log.e("TEAMPS", "Can't set color.");
        }

        try {
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set display home as up enabled.");
        }

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
                        Intent intent = new Intent(businessapppwdOTPVerificationActivity.this, businessapppwdOTPVerificationActivity.class);
                        intent.putExtra("Email", response.getemail());
                        intent.putExtra("passowordotp", response.getPasswordotp());
                        startActivity(intent);
                        editor.commit();

                        //startActivity(new Intent(businessappForgotPasswordActivity.this, businessapppwdOTPVerificationActivity.class));
                        finish();
                    }
                });
    }

    public void PWDOTPVerification(JsonObject jsonObject){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .BusinessappForgotpassword(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverPasswordVerificationResponse>>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Verification Successfully");
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
                    public void onNext(List<DriverPasswordVerificationResponse> responselist) {
                        DriverPasswordVerificationResponse response=responselist.get(0);
                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        Intent intent = new Intent(businessapppwdOTPVerificationActivity.this, login_activity.class);
                        editor.putString(UserAccountNo, response.getUserAccountNo());
                        editor.commit();
                        startActivity(intent);
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
}
