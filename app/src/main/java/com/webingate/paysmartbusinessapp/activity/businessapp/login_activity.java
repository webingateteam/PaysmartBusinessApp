package com.webingate.paysmartbusinessapp.activity.businessapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.rilixtech.CountryCodePicker;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.adapter.uicollection.CustomSpinnerAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverValidateCredentialsResponse;

import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class login_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordkey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String Dateofbirth = "dateofbirth";
    public static final String Gender = "gender";
    public static final String Paymenttype = "paymenttype";
    public static final String Profilepic = "profilepic";
    public static final String DRIVERID = "driverid";
    public static final String VEHICLEID = "vehicleid";

    private String response;
    Toast toast;
    ProgressDialog dialog ;
    TextView forgotTextView, signUpTextView;


    @BindView(R.id.loginButton)
    Button loginButton;
    CardView facebookCardView, twitterCardView;
    CountryCodePicker ccp;
    Spinner spinner;
    TextView textView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    int loginasOption = -1;
    @BindView(R.id.input_mobile)
    EditText mobileNo;

    @BindView(R.id.textPassword)
    EditText textPassword;


    String[] fruits = {"Driver", "Fleet owner", "Ticket Agent", "Brand ambassador","Business Owner"};
    int[] icons = {R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24
            , R.drawable.baseline_person_outline_black_24,R.drawable.baseline_person_outline_black_24};

    ImageView bgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_login_activity);

        initUI();

        initDataBindings();

        initActions();

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        list = new ArrayList<>(Arrays.asList(fruits));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        CustomSpinnerAdapter uiloginasCustomSpinnerAdapter =new CustomSpinnerAdapter(getApplicationContext(),icons,fruits);
        spinner.setAdapter(uiloginasCustomSpinnerAdapter);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.setCustomMasterCountries("IN,ZW,AF");
    }

    //region Init Functions
    private void initUI() {
        forgotTextView = findViewById(R.id.forgotTextView);
        signUpTextView = findViewById(R.id.signuptTextView);

        loginButton = findViewById(R.id.loginButton);
        mobileNo = findViewById(R.id.mobileNo);
        textPassword = findViewById(R.id.textPassword);

//        facebookCardView = findViewById(R.id.facebookCardView);
//        twitterCardView = findViewById(R.id.twitterCardView);
        bgImageView = findViewById(R.id.bgImageView);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {
        forgotTextView.setOnClickListener(view -> {
            //Toast.makeText(getApplicationContext(), "Clicked Forgot Password.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, customerForgotPasswordActivity.class);
            startActivity(intent);
        });

        signUpTextView.setOnClickListener((View view) -> {
           // Toast.makeText(getApplicationContext(), "Clicked Sign Up.", Toast.LENGTH_SHORT).show();

            //Intent intent = new Intent(this, customerSignUpActivity.class);
            Intent intent = new Intent(this, customerSignUpActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(view -> {

//            if (mobileNo.getText().toString().matches("") || textPassword.getText().toString().matches("")) {
//                Toast.makeText(getApplicationContext(), "Please Enter details", Toast.LENGTH_SHORT).show();
//            } else {
//                JsonObject jsonObject = new JsonObject();
//                jsonObject.addProperty("Mobilenumber", mobileNo.getText().toString());
//                jsonObject.addProperty("Password", textPassword.getText().toString());
//                DriverLogin(jsonObject);
//            }
            GoToDashboard();
            //Toast.makeText(getApplicationContext(), "Clicked Login.", Toast.LENGTH_SHORT).show();


        });

//        facebookCardView.setOnClickListener(view -> {
//            Toast.makeText(getApplicationContext(), "Clicked Facebook.", Toast.LENGTH_SHORT).show();
//        });
//
//        twitterCardView.setOnClickListener(view -> {
//            Toast.makeText(getApplicationContext(), "Clicked Twitter.", Toast.LENGTH_SHORT).show();
//        });
    }

    private void GoToDashboard(){
        switch (this.loginasOption){
            case 0:
                Toast.makeText(getApplicationContext(), "Clicked option 0.", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(this, businessappDriverDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

                break;
            case 1:
                Toast.makeText(getApplicationContext(), "Clicked option 1.", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, businessappFleetownerDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;

            case 2:
                Toast.makeText(getApplicationContext(), "Clicked option 2.", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, businessappticketagentDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;
            case 3:
                Toast.makeText(getApplicationContext(), "Clicked option 3.", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, businessappBrandDashboardActivity.class);
                startActivity(intent);
                break;
            case 4:
                Toast.makeText(getApplicationContext(), "Clicked option 4.", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, businessappBusinessownerDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;
            default:
                //intent = new Intent(this, customerDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                //startActivity(intent);
                break;
        }

    }

    public void DriverLogin(JsonObject jsonObject){

       // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .ValidateDriver(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverValidateCredentialsResponse>>() {
                    @Override
                    public void onCompleted() {
                        //    DisplayToast("Successfully LoggedIn");
                     //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                            DisplayToast("Unable to Login");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DriverValidateCredentialsResponse> responce) {
                        DriverValidateCredentialsResponse credentialsResponse=responce.get(0);
                        if(credentialsResponse.getCode()!=null){
                            DisplayToast(credentialsResponse.getDescription());
                        }else {
                            SharedPreferences sharedPref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(DRIVERID, credentialsResponse.getDid());
                            editor.putString(VEHICLEID, credentialsResponse.getVehicleId());
                            editor.putString(Phone, mobileNo.getText().toString());
                            editor.putString(Emailotp, null);
                            editor.putString(Mobileotp, null);
                            editor.commit();
                            ApplicationConstants.mobileNo = mobileNo.getText().toString();
                            //startActivity(new Intent(this, MainActivity.class));
                            GoToDashboard();
                            finish();
                        }

                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //   accessTokenTracker.stopTracking();
        //   profileTracker.stopTracking();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loginasOption = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        loginasOption = -1;
    }


    //endregion

    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }
    public void StartDialogue(){
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    public void StopDialogue(){
        if(dialog.isShowing()){
            dialog.cancel();
        }

    }
}
