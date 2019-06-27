package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.RegisterBusinessUsers;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleCreationResponce;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverDocsFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverUserInfoFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppVehicleDocsFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppVehicleInfoFragment;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants.photo1;

public class businessappNewVehicleActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String RegistrationNo = "RegistrationNoKey";

    private int position = 1;
    private int maxPosition = 5;
    private Button nextButton, prevButton;
    private TextView imageNoTextView;
    ImageView profileImageView;



    EditText RegNo;
    EditText chasisno;
    EditText engineno;
    Spinner vgroup;
    Spinner vtype;
    EditText modelyear;
    EditText state;
    Toast toast;
    String type,grp;
    Spinner country;
    String ctry;

    businessAppVehicleInfoFragment userInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_newvehicle_activity);

        initData();

        initUI();

        initActions();

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

    //endregion

    //region Init Functions
    private void initData() {

    }

    private void initUI() {
        initToolbar();


        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        imageNoTextView = findViewById(R.id.imageNoTextView);
//        if(ApplicationConstants.registrationNo!=null){
//        RegNo = findViewById(R.id.s_Regno);
//        RegNo.setText(ApplicationConstants.registrationNo);
//        }


        updatePositionTextView();
        setupFragment(new businessAppVehicleInfoFragment());

//        profileImageView = findViewById(R.id.profileImageView);
//        int id = R.drawable.profile2;
//        Utils.setCornerRadiusImageToImageView(getApplicationContext(), profileImageView, id, 20, 2,  R.color.md_white_1000);

        //ImageView coverUserImageView = findViewById(R.id.coverUserImageView);
        //Utils.setImageToImageView(getApplicationContext(), coverUserImageView, id);

//        emailTextView = findViewById(R.id.emailTextView);
//        phoneTextView = findViewById(R.id.phoneTextView);
//        websiteTextView = findViewById(R.id.websiteTextView);

        //editFAB = findViewById(R.id.editFAB);

    }

    private void updatePositionTextView() {
        imageNoTextView.setText(position + " of " + maxPosition);
    }

    private void setupFragment(Fragment fragment) {
        try {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentLayout, fragment)
                    .commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initActions() {

        nextButton.setOnClickListener(v -> {

            if (position < maxPosition) {
                position++;



                updatePositionTextView();
                if(position == 1) {
                    //Toast.makeText(this, "Step 1.", Toast.LENGTH_SHORT).show();
                    userInfoFragment =  new businessAppVehicleInfoFragment();

                    setupFragment(userInfoFragment);
//                    RegNo = findViewById(R.id.s_Regno);
//                    RegNo.setText(ApplicationConstants.registrationNo);

                }
                if(position == 2)
                {
                    //EditText name = (EditText)findViewById(R.id.s_name);
                    RegNo = findViewById(R.id.s_Regno);
                    chasisno = findViewById(R.id.s_chasisno);
                    engineno = findViewById(R.id.s_engineno);
                    vgroup = findViewById(R.id.s_vgroup);
                    vtype = findViewById(R.id.s_vtype);
                    modelyear = findViewById(R.id.s_modelyear);
                    state = findViewById(R.id.s_state);
                    country = findViewById(R.id.s_country);

                    JsonObject object = new JsonObject();
                    object.addProperty("flag", "I");
                    object.addProperty("Id","");
                    object.addProperty("VID", "");
                    object.addProperty("CompanyId", "");
                    object.addProperty("RegistrationNo",RegNo.getText().toString());
                    object.addProperty("ChasisNo",chasisno.getText().toString());
                    object.addProperty("Engineno",engineno.getText().toString());
                    object.addProperty("FleetOwnerCode",ApplicationConstants.fid);
                    object.addProperty("VehicleTypeId",ApplicationConstants.vtype);
                    object.addProperty("VehicleModelId","");
                    object.addProperty("VehicleGroupId",ApplicationConstants.vgrp);
                    object.addProperty("ModelYear",modelyear.getText().toString());
                    object.addProperty("VehicleCode","12345");
                    object.addProperty("CountryId", ApplicationConstants.countryid);
                    object.addProperty("change","2");
                    object.addProperty("type","1");
                    VehicleCreation(object);

                    //Toast.makeText(this, "Step 2.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppVehicleDocsFragment());
                }


                if(position == 3) {
                    //Toast.makeText(this, "Step 3.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppVehicleInfoFragment());
                }

            } else {
                Toast.makeText(this, "No More Step.", Toast.LENGTH_SHORT).show();
            }
        });

        prevButton.setOnClickListener(v -> {

            if (position > 1) {
                position--;

                updatePositionTextView();
                if(position == 1) {
                    //Toast.makeText(this, "Step 1.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppVehicleInfoFragment());
                }
                if(position == 2)
                {
                    //Toast.makeText(this, "Step 2.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverDocsFragment());
                }


                if(position == 3) {
                    //Toast.makeText(this, "Step 3.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverUserInfoFragment());
                }

            } else {
                Toast.makeText(this, "No More Step.", Toast.LENGTH_SHORT).show();
            }
        });
//
    }

    public void VehicleCreation(JsonObject jsonObject){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(businessappNewVehicleActivity.this).getrestadapter()
                .VehicleCreationverification(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<VehicleCreationResponce>>() {
                    @Override
                    public void onCompleted() {
                       // DisplayToast("Vehicle Created Successfully");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            //DisplayToast("Successfully onError");
                            //DisplayToast("Unable to Register");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<VehicleCreationResponce> responseList) {
//                        DisplayToast("Successfully onNext");
                        VehicleCreationResponce response=responseList.get(0);
                        if(response.getCode()!=null){
                            DisplayToast(response.getdescription());
                        }else {
                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(RegistrationNo, response.getRegistrationNo());
//                            Intent intent = new Intent(businessappNewDriverActivity.this, customerEOTPVerificationActivity.class);
//                            intent.putExtra("eotp", response.getemailotp());
//                            intent.putExtra("uid", response.getusreid());
//                            intent.putExtra("email", response.getemail());
//                            intent.putExtra("username", response.getusreid());
//                            intent.putExtra("motp", response.getmotp());
//                            intent.putExtra("mno", response.getmnumber());
//                            startActivity(intent);
//                        editor.putString(Phone, response.getPMobNo());
//                        editor.putString(Email, response.getEmail());
//                        editor.putString(Password, response.getPassword());
//                        editor.putString(Mobileotp, response.getMobileotp());
//                        editor.putString(Emailotp, response.getEmailotp());
//                        editor.putString(DRIVERID, response.getDriverId());
//                        editor.putString(VEHICLEID, response.getVehicleId());
                            editor.commit();
                            // startActivity(new Intent(customerSignUpActivity.this, customerEOTPVerificationActivity.class));
                            //finish();
                        }
                    }
                });
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("New Vehicle");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        }catch (Exception e){
            Log.e("TEAMPS","Can't set color.");
        }

        try {
            setSupportActionBar(toolbar);
        }catch (Exception e) {
            Log.e("TEAMPS","Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }catch (Exception e) {
            Log.e("TEAMPS","Error in set display home as up enabled.");
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
