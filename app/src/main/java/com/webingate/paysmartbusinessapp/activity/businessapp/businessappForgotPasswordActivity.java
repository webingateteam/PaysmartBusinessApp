package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.rilixtech.CountryCodePicker;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.BusinessEOTPVerificationResponse;
import com.webingate.paysmartbusinessapp.adapter.uicollection.CustomSpinnerAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.ActiveCountries;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverForgotpasswordResponse;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappForgotPasswordActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String passwordotp = "pwdotpkey";
    public static final String UserAccountNo = "UserAccountNoKey";

    Toast toast;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Button resetButton;
    TextView signInTextView;
    ImageView bgImageView;
    int loginasOption = -1;

    @BindView(R.id.resetButton)
    Button sbtn;
    Spinner spinner;

    @BindView(R.id.s_mobileno)
    EditText mbno;

    String mno;
    CountryCodePicker ccp;

    String[] fruits = {"Driver", "Fleet owner", "Ticket Agent", "Brand ambassador","Business Owner"};
    int[] icons = {R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24
            , R.drawable.baseline_person_outline_black_24,R.drawable.baseline_person_outline_black_24};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_forgotpassword_activity);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mno = prefs.getString(UserAccountNo, null);
        initUI();

        initDataBindings();

        initActions();
        initData();
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        list = new ArrayList<>(Arrays.asList(fruits));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        CustomSpinnerAdapter uiloginasCustomSpinnerAdapter =new CustomSpinnerAdapter(getApplicationContext(),icons,fruits);
        spinner.setAdapter(uiloginasCustomSpinnerAdapter);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);

    }

    //region Init Functions
    private void initUI() {
        signInTextView = findViewById(R.id.signinTextView);
        resetButton = findViewById(R.id.resetButton);
        bgImageView = findViewById(R.id.bgImageView);
        mbno = findViewById(R.id.s_mobileno);

    }

    private void initDataBindings() {
        int id = R.drawable.login_background;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initData(){
        GetActiveCountries(1);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loginasOption = position;
    }

    @Override
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
            if(mbno.getText().toString().matches("")){
                Toast.makeText(getApplicationContext(),"Please Enter Mobile Number",Toast.LENGTH_SHORT).show();
            }
            else
            {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("UserAccountNo", selectype()+ccp.getSelectedCountryCode()+mbno.getText().toString());
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
                        DriverForgotpasswordResponse response = responselist.get(0);
                        if (response.getCode() != null) {
                            DisplayToast(response.getDescription());
                        } else {
                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            Intent intent = new Intent(businessappForgotPasswordActivity.this, businessapppwdOTPVerificationActivity.class);
                            editor.putString(UserAccountNo, response.getUserAccountNo());
                            //editor.putString(passwordotp, response.getPasswordotp());

//                            intent.putExtra("Email", response.getemail());
//                            intent.putExtra("passowordotp", response.getPasswordotp());
                            startActivity(intent);
                            editor.commit();

                            //startActivity(new Intent(businessappForgotPasswordActivity.this, businessapppwdOTPVerificationActivity.class));
                            finish();
                        }
                    }
                });
    }

    public void GetActiveCountries(int active){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(businessappForgotPasswordActivity.this).getrestadapter()
                .GetActiveCountry(active)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ActiveCountries>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully Registered");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<ActiveCountries> list) {

                        List<ActiveCountries> response= list ;
                        int countrycount = response.size();
                        if (countrycount == 0) {
                            DisplayToast("Please configure countries of operation.");
                        } else {

                            String countriesList = "";
                            for(int i=0; i < countrycount ; i++){
                                if(i == countrycount-1)
                                    countriesList += response.get(i).getISOCode();
                                else
                                    countriesList += response.get(i).getISOCode()+ ",";
                            }

                            ccp.setCustomMasterCountries(countriesList);

//                            ccp = (CountryCodePicker) findViewById(R.id.ccp);
//                            ccp.setCustomMasterCountries(response.getISOCode());
//                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedpreferences.edit();
//                            editor.putString(Isocode, response.getISOCode());
//                            editor.commit();
                        }
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
