package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.RegisterBusinessUsers;
import com.webingate.paysmartbusinessapp.adapter.uicollection.CustomSpinnerAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RegisterDriverResponse;
import com.webingate.paysmartbusinessapp.driverapplication.RegisterActivity;
import com.webingate.paysmartbusinessapp.driverapplication.VerificationActivity;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class customerSignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
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
    @BindView(R.id.mobileNo)
    EditText S_mobileNo;
    TextView forgotTextView, signUpTextView;
   //Button registerButton;
    ImageView bgImageView;
    CountryCodePicker ccp;

    Spinner spinner;
    TextView textView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    int loginasOption = -1;

    String[] fruits = {"Driver", "Fleet owner", "Ticket Agent", "Brand ambassador","Business Owner"};
    int[] icons = {R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24
            , R.drawable.baseline_person_outline_black_24,R.drawable.baseline_person_outline_black_24};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_signup_activity);

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

        registerButton = findViewById(R.id.registerButton);
        S_username=findViewById(R.id.s_username);
        S_password=findViewById(R.id.s_password);
        S_email=findViewById(R.id.s_email);
        S_mobileNo=findViewById(R.id.mobileNo);
        bgImageView = findViewById(R.id.bgImageView);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background_3;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {
        forgotTextView.setOnClickListener(view -> {
           // Toast.makeText(getApplicationContext(), "Clicked Forgot Password.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, businessappForgotPasswordActivity.class);
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
            object.addProperty("Usename",S_username.getText().toString());
//            object.addProperty("lastname", "kumar");
            object.addProperty("AuthTypeId", "");
            object.addProperty("Password", S_password.getText().toString());
            object.addProperty("Mobilenumber",S_mobileNo.getText().toString());
            object.addProperty("Email", S_email.getText().toString());
            object.addProperty("CountryId",ccp.getSelectedCountryCode());
            object.addProperty("CCode","91");
            object.addProperty("UserAccountNo",selectype()+ccp.getSelectedCountryCode()+S_mobileNo.getText().toString());
            object.addProperty("usertypeid",selectype());
            RegisterDriver(object);
//            Intent intent = new Intent(this, customerEOTPVerificationActivity.class);
//            startActivity(intent);
        });

    }
    public void RegisterDriver(JsonObject jsonObject){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(customerSignUpActivity.this).getrestadapter()
                .Savebusinessappusers(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RegisterBusinessUsers>>() {
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
                    public void onNext(List<RegisterBusinessUsers> responseList) {
                        DisplayToast("Successfully onNext");
                        RegisterBusinessUsers response=responseList.get(0);
                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        //editor.putString(Username, response.getusername());
                        Intent intent = new Intent(customerSignUpActivity.this, customerEOTPVerificationActivity.class);
                        intent.putExtra("eotp", response.getemailotp());
                        intent.putExtra("uid", response.getusreid());
                        intent.putExtra("email", response.getemail());
                        intent.putExtra("username", response.getusreid());
                        intent.putExtra("motp", response.getmotp());
                        intent.putExtra("mno", response.getmnumber());
                        startActivity(intent);
//                        editor.putString(Phone, response.getPMobNo());
//                        editor.putString(Email, response.getEmail());
//                        editor.putString(Password, response.getPassword());
//                        editor.putString(Mobileotp, response.getMobileotp());
//                        editor.putString(Emailotp, response.getEmailotp());
//                        editor.putString(DRIVERID, response.getDriverId());
//                        editor.putString(VEHICLEID, response.getVehicleId());
                        editor.commit();
                       // startActivity(new Intent(customerSignUpActivity.this, customerEOTPVerificationActivity.class));
                        finish();
                    }
                });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loginasOption = position;
    }

    public void onNothingSelected(AdapterView<?> parent) {
        loginasOption = -1;
    }
    private int selectype(){
        if(loginasOption==0){
            return 109;
        }else if(loginasOption==1){
            return 110;
        }
        else if(loginasOption==2){
            return 149;
        }else if(loginasOption==3){
            return 150;
        }else if(loginasOption==4){
            return 151;
        }
        else {
            return 200;
        }
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
