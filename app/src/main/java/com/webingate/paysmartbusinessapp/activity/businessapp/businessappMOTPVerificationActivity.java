package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.MOTPVerification;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappMOTPVerificationActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    Toast toast;
    String M_numbers;
    int M_uid;
    @BindView(R.id.mpassword)
    EditText M_password;
    @BindView(R.id.submitOTPButton)
    Button M_submit;

    ImageView bgImageView;
    Button changeButton, resendButton, submitOTPButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_motpverification_activity);
        Intent intent = getIntent();
        M_numbers=intent.getStringExtra("Mnumber");
        M_uid=intent.getIntExtra("Uid",0);
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
        int id = Utils.getDrawableInt(getApplicationContext(), "verification3");
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);

        changeButton = findViewById(R.id.changeButton);

        resendButton = findViewById(R.id.resendButton);

        M_password=findViewById(R.id.mpassword);
        M_submit=findViewById(R.id.submitOTPButton);

        submitOTPButton = findViewById(R.id.submitOTPButton);
    }

    private void initActions(){
        changeButton.setOnClickListener((View v) ->{
           Toast.makeText(getApplicationContext(),"Clicked Change Email.",Toast.LENGTH_SHORT).show();

        });

        resendButton.setOnClickListener((View v) ->{
            Toast.makeText(getApplicationContext(),"OTP is Resent.",Toast.LENGTH_SHORT).show();
        });

        submitOTPButton.setOnClickListener((View v) ->{
            //Toast.makeText(getApplicationContext(),"OTP is Resent.",Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, login_activity.class);
//            Intent intent = new Intent(this, customerappPaymentModeActivity.class);
//            startActivity(intent);
            JsonObject jsonObject = new JsonObject();
           jsonObject.addProperty("Mobilenumber", M_numbers);
            jsonObject.addProperty("userId", M_uid);
            jsonObject.addProperty("MVerificationCode", M_password.getText().toString());
            MOTPVerification(jsonObject);
        });
    }


    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("MOTP Verification");

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
    public void MOTPVerification(JsonObject jsonObject){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .MOTPVerifications1(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MOTPVerification>>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Registered");
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
                    public void onNext(List<MOTPVerification> responselist) {
                        MOTPVerification response=responselist.get(0);
//                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedpreferences.edit();
//                        editor.putString(Emailotp, null);
//                        editor.commit();
                        startActivity(new Intent(businessappMOTPVerificationActivity.this, login_activity.class));
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
