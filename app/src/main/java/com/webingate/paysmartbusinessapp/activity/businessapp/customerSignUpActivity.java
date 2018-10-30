package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RegisterDriverResponse;
import com.webingate.paysmartbusinessapp.driverapplication.RegisterActivity;
import com.webingate.paysmartbusinessapp.driverapplication.VerificationActivity;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class customerSignUpActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordkey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String DRIVERID = "driverid";
    public static final String VEHICLEID = "vehicleid";
    Toast toast;
    @BindView(R.id.registerButton)
    Button registerButton;
    @BindView(R.id.s_username)
    EditText S_username;
    @BindView(R.id.s_password)
    EditText S_password;
    @BindView(R.id.s_email)
    EditText S_email;
    TextView forgotTextView, signUpTextView;
   //Button registerButton;
    ImageView bgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerapp_signup_activity);

        initUI();

        initDataBindings();

        initActions();

    }

    //region Init Functions
    private void initUI() {
        forgotTextView = findViewById(R.id.forgotTextView);
        signUpTextView = findViewById(R.id.signuptTextView);

        registerButton = findViewById(R.id.registerButton);
        S_username=findViewById(R.id.s_username);
        S_password=findViewById(R.id.s_password);
        S_username=findViewById(R.id.s_email);

        bgImageView = findViewById(R.id.bgImageView);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background_3;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {
        forgotTextView.setOnClickListener(view -> {
           // Toast.makeText(getApplicationContext(), "Clicked Forgot Password.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, customerForgotPasswordActivity.class);
            startActivity(intent);
        });

        signUpTextView.setOnClickListener(view -> {
          //  Toast.makeText(getApplicationContext(), "Clicked Sign In.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, login_activity.class);
            startActivity(intent);

        });

        registerButton.setOnClickListener(view -> {
            //Toast.makeText(getApplicationContext(), "Clicked Register.", Toast.LENGTH_SHORT).show();

            JsonObject object = new JsonObject();
            object.addProperty("flag", "I");
            object.addProperty("Firstname", "hnmisv");
            object.addProperty("lastname", "sv");
            object.addProperty("AuthTypeId", "2");
            object.addProperty("Password", "123");
            object.addProperty("Mobilenumber", "7893890990");
            object.addProperty("Email", "hnmisv@gmail.com");
            RegisterDriver(object);
//            Intent intent = new Intent(this, customerEOTPVerificationActivity.class);
//            startActivity(intent);
        });

    }
    public void RegisterDriver(JsonObject jsonObject){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(customerSignUpActivity.this).getrestadapter()
                .RegisterDriver(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RegisterDriverResponse>>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully onCompleted");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            DisplayToast("Successfully onError");
                            //DisplayToast("Unable to Register");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<RegisterDriverResponse> responseList) {
                        DisplayToast("Successfully onNext");
                        RegisterDriverResponse response=responseList.get(0);
                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(Name, response.getName());
                        editor.putString(Phone, response.getPMobNo());
                        editor.putString(Email, response.getEmail());
                        editor.putString(Password, response.getPassword());
                        editor.putString(Mobileotp, response.getMobileotp());
                        editor.putString(Emailotp, response.getEmailotp());
                        editor.putString(DRIVERID, response.getDriverId());
                        editor.putString(VEHICLEID, response.getVehicleId());
                        editor.commit();
                        startActivity(new Intent(customerSignUpActivity.this, customerEOTPVerificationActivity.class));
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
