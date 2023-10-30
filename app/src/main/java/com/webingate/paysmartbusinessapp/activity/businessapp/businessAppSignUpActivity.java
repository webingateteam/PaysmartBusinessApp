package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.rilixtech.CountryCodePicker;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.ConfigResponse;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.RegisterBusinessUsers;
import com.webingate.paysmartbusinessapp.adapter.uicollection.CustomSpinnerAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.ActiveCountries;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessAppSignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
    public static final String Username = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordkey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String DRIVERID = "driverid";
    public static final String VEHICLEID = "vehicleid";
    public static final String UserAccountNumber = "UserAccountNo";
    public static final String ISOCode = "ISOCodeKey";

    Toast toast;
    private  ProgressDialog pd;
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
    EditText vehicle_regno;
    CheckBox chkIos;
    String ischecked=null;
    Spinner spinner,vehicle_group,vehicle_type;
    TextView textView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    int loginasOption = -1;
    int countryid;
    String ctry;
    View view69,view91;
    int grp,type,ct;
    List<ConfigResponse> res,res1;
    List<ActiveCountries> res2;

    ArrayAdapter arlist,groupadapter,countries;

    List<String> vtypes = new ArrayList<String>();
    List<String> typegroup = new ArrayList<String>();
    List<String> ctry1 = new ArrayList<String>();

    String[] fruits = {"Driver", "Fleet owner", "Ticket Agent", "Brand ambassador","Business Owner"};
    int[] icons = {R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24
            , R.drawable.baseline_person_outline_black_24,R.drawable.baseline_person_outline_black_24};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_signup_activity);

        vehicle_group = findViewById(R.id.vehicle_group);
        vehicle_group.setOnItemSelectedListener(this);
        vehicle_type = findViewById(R.id.vehicle_type);
        vehicle_type.setOnItemSelectedListener(this);

        vehicle_group=findViewById(R.id.vehicle_group);
        vehicle_type=findViewById(R.id.vehicle_type);
        chkIos=findViewById(R.id.chkIos);
        vehicle_regno=findViewById(R.id.vehicle_regno);
        view69=findViewById(R.id.view69);
        view91=findViewById(R.id.view91);
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

        chkIos.setOnClickListener(view -> {

                if (((CheckBox) view).isChecked()) {
                    ischecked="okay";
                    //vehicle_group.setVisibility(view.getVisibility());
                    vehicle_type.setVisibility(view.getVisibility());
                    vehicle_regno.setVisibility(view.getVisibility());
                    view69.setVisibility(view.getVisibility());
                    view91.setVisibility(view.getVisibility());
                }else{
                    ischecked=null;
                   // vehicle_group.setVisibility(view.GONE);
                    vehicle_type.setVisibility(view.GONE);
                    vehicle_regno.setVisibility(view.GONE);
                    view69.setVisibility(view.GONE);
                    view91.setVisibility(view.GONE);
                }

        });

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
        int id = R.drawable.login_background;
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


            if(ischecked==null){
                JsonObject object = new JsonObject();
                object.addProperty("flag", "I");
                object.addProperty("Firstname",S_username.getText().toString());
                // object.addProperty("lastname", "");
                object.addProperty("AuthTypeId", "");
                object.addProperty("Password", S_password.getText().toString());
                object.addProperty("Mobilenumber",S_mobileNo.getText().toString());
                object.addProperty("Email", S_email.getText().toString());
                if(ccp.getSelectedCountryCode().matches("91")){
                    ctry = "101";
                }
                else if (ccp.getSelectedCountryCode().matches("263")){
                    ctry = "245";
                }
                else{
                    ctry = "";
                }
                object.addProperty("CountryId",ctry);
                object.addProperty("CCode",ccp.getSelectedCountryCode());
                object.addProperty("UserAccountNo",selectype()+ccp.getSelectedCountryCode()+S_mobileNo.getText().toString());
                object.addProperty("usertypeid",selectype());
                object.addProperty("change",selectype());
                object.addProperty("type","1");
                object.addProperty("RegistrationNo","");
                object.addProperty("VehicleTypeId","");
                object.addProperty("isDriverOwned","");
                object.addProperty("VPhoto","");
                object.addProperty("VehicleGroupId",ApplicationConstants.vgrp);
                RegisterDriver(object);
            }
            else{


            JsonObject object = new JsonObject();
            object.addProperty("flag", "I");
            object.addProperty("Firstname",S_username.getText().toString());
           // object.addProperty("lastname", "");
            object.addProperty("AuthTypeId", "");
            object.addProperty("Password", S_password.getText().toString());
            object.addProperty("Mobilenumber",S_mobileNo.getText().toString());
            object.addProperty("Email", S_email.getText().toString());
                if(ccp.getSelectedCountryCode().matches("91")){
                    ctry = "101";
                }
                else if (ccp.getSelectedCountryCode().matches("263")){
                    ctry = "245";
                }
                else{
                    ctry = "";
                }
            object.addProperty("CountryId",ctry);
            object.addProperty("CCode",ccp.getSelectedCountryCode());
            object.addProperty("UserAccountNo",selectype()+ccp.getSelectedCountryCode()+S_mobileNo.getText().toString());
            object.addProperty("usertypeid",selectype());
            object.addProperty("change",selectype());
            object.addProperty("type","1");
            object.addProperty("RegistrationNo",vehicle_regno.getText().toString());
            object.addProperty("VehicleTypeId",ApplicationConstants.vtype);
            object.addProperty("isDriverOwned",chkIos.toString());
            object.addProperty("VPhoto","");
            object.addProperty("VehicleGroupId",ApplicationConstants.vgrp);
            RegisterDriver(object);
//            Intent intent = new Intent(this, customerEOTPVerificationActivity.class);
//            startActivity(intent);
            }
        });

    }

    private void initData(){
        GetActiveCountries(1);
        GetVgrplist(4);
        GetVTypeslist(12);
    }

    public void RegisterDriver(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(businessAppSignUpActivity.this).getrestadapter()
                .Savebusinessappusers(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RegisterBusinessUsers>>() {
                    @Override
                    public void onCompleted() {
                        StopDialogue();
                        //DisplayToast("Successfully onCompleted");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            StopDialogue();
                            //DisplayToast("Unable to Register");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<RegisterBusinessUsers> responseList) {
                       // DisplayToast("Successfully onNext");
                        RegisterBusinessUsers response=responseList.get(0);
                        if(response.getCode()!=null){
                            DisplayToast(response.getDescription());
                        }else {
                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            Intent intent = new Intent(businessAppSignUpActivity.this, businessappEOTPVerificationActivity.class);
                            editor.putString(UserAccountNumber, response.getUserAccountNo());
                            editor.putInt(ID, response.getId());
                            editor.putString(Phone, response.getmnumber());
                            editor.putString(Email, response.getemail());
                            editor.putString(Emailotp, response.getemailotp());

//                            intent.putExtra("eotp", response.getemailotp());
//                            intent.putExtra("uid", response.getusreid());
//                            intent.putExtra("email", response.getemail());
//                            intent.putExtra("username", response.getusreid());
//                            intent.putExtra("motp", response.getmotp());
//                            intent.putExtra("mno", response.getmnumber());
                            editor.commit();
                            startActivity(intent);
//                        editor.putString(Phone, response.getPMobNo());
//                        editor.putString(Email, response.getEmail());
//                        editor.putString(Password, response.getPassword());
//                        editor.putString(Mobileotp, response.getMobileotp());
//                        editor.putString(Emailotp, response.getEmailotp());
//                        editor.putString(DRIVERID, response.getDriverId());
//                        editor.putString(VEHICLEID, response.getVehicleId());

                            // startActivity(new Intent(businessAppSignUpActivity.this, customerEOTPVerificationActivity.class));
                            finish();
                        }
                    }
                });
    }

    public void GetActiveCountries(int active){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(businessAppSignUpActivity.this).getrestadapter()
                .GetActiveCountry(active)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ActiveCountries>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            StopDialogue();
                           // DisplayToast("Error");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<ActiveCountries> list) {
                        res2 = list;
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


//                            for (int i = 0; i < res2.size(); i++) {
//                                ctry1.add(res2.get(i).getName());
//                            }
//
//                            countries = new ArrayAdapter(businessAppSignUpActivity.this, android.R.layout.simple_spinner_item, ctry1);
//                            countries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                            ccp.setAdapter(countries);

//                            ccp = (CountryCodePicker) findViewById(R.id.ccp);
//                            ccp.setCustomMasterCountries(response.getISOCode());
//                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedpreferences.edit();
//                            editor.putString(ISOCode, response.getISOCode());
//                            editor.commit();
                        }
                    }
                });
    }

    public void GetVgrplist(int Id){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetGroups(Id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ConfigResponse>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully SignUp");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<ConfigResponse> responce) {
                        res = responce;

                        for (int i = 0; i < res.size(); i++) {
                            typegroup.add(res.get(i).getName());
                        }
                        groupadapter=new ArrayAdapter(businessAppSignUpActivity.this,android.R.layout.simple_spinner_item,typegroup);
                        groupadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        vehicle_group.setAdapter(groupadapter);
                    }

                });
    }

    public void GetVTypeslist(int Id){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetGroups(Id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ConfigResponse>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully SignUp");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<ConfigResponse> responce) {
                        res1 = responce;

                        for (int i = 0; i < res1.size(); i++) {
                            vtypes.add(res1.get(i).getName());
                        }

                        arlist=new ArrayAdapter(businessAppSignUpActivity.this,android.R.layout.simple_spinner_item,vtypes);
                        arlist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        vehicle_type.setAdapter(arlist);
                        //spinnergametype.setAdapter(arlist);
                    }
                });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loginasOption = position;
        if(loginasOption!=0){
            //vehicle_group.setVisibility(view.GONE);
            vehicle_type.setVisibility(view.GONE);
            vehicle_regno.setVisibility(view.GONE);
            view69.setVisibility(view.GONE);
            view91.setVisibility(view.GONE);
            chkIos.setVisibility(view.GONE);
        }else if(loginasOption==0){
            if(ischecked!=null){
                //vehicle_group.setVisibility(view.getVisibility());
                vehicle_type.setVisibility(view.getVisibility());
                vehicle_regno.setVisibility(view.getVisibility());
                view69.setVisibility(view.getVisibility());
                view91.setVisibility(view.getVisibility());
            }else{
                //vehicle_group.setVisibility(view.GONE);
                vehicle_type.setVisibility(view.GONE);
                vehicle_regno.setVisibility(view.GONE);
                view69.setVisibility(view.GONE);
                view91.setVisibility(view.GONE);
            }

            chkIos.setVisibility(view.getVisibility());
        }

        Spinner vehicle_group = (Spinner)parent;
        Spinner vehicle_type = (Spinner)parent;
//        Spinner country = (Spinner)parent;
//        if(country.getId() == R.id.spinner)
//        {
//            // Toast.makeText(this, "Your are selected :" + res.get(position).getName(),Toast.LENGTH_SHORT).show();
//            ct=position;
//            ApplicationConstants.countryid=res2.get(ct).getId();
//
//        }

        if(vehicle_group.getId() == R.id.vehicle_group)
        {
            // Toast.makeText(this, "Your are selected :" + res.get(position).getName(),Toast.LENGTH_SHORT).show();
            grp=position;
            ApplicationConstants.vgrp=String.valueOf(res.get(grp).getId());
        }
        if(vehicle_type.getId() == R.id.vehicle_type)
        {
            //  Toast.makeText(this, "Your are selected :" + res1.get(position).getName(),Toast.LENGTH_SHORT).show();
            type=position;
            ApplicationConstants.vtype=String.valueOf(res1.get(type).getId());
        }
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
    public void StartDialogue(){
        pd=new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Please wait.....");

        pd.incrementProgressBy(50);
        pd.show();
    }
    public void StopDialogue(){

        pd.dismiss();
    }
    //endregion
}
