package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.RegisterBusinessUsers;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AssignDriverResponse;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverUserInfoFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppUploadDocsFragment;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
//import com.webingate.paysmartbusinessapp.pa;

public class businessappNewAssignDriverActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String RegistrationNo = "RegistrationNoKey";
    public static final String Phone = "phoneKey";

    Toast toast;

    @BindView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_newallocateddriver_activity);

        initData();

        initUI();

        initDataBinding();

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


    //region Init Functions
    private void initData() {

    }

    private void initUI() {

        initToolbar();

        submit = findViewById(R.id.submit);

    }



    private void initDataBinding() {


    }

    private void initActions() {

        submit.setOnClickListener((View v) ->{
            Toast.makeText(getApplicationContext(),"Details Of Assign Driver.",Toast.LENGTH_SHORT).show();
            JsonObject object = new JsonObject();
            object.addProperty("flag", "I");
            object.addProperty("Id", "");
            object.addProperty("CompanyId", "1");
            object.addProperty("PhoneNo", "7893890990");
            object.addProperty("VehicleGroupId", "Hailing Car");
            object.addProperty("RegistrationNo", "Ts01235");
            object.addProperty("DriverName", "Krish");
            object.addProperty("DriverId", "1");
            object.addProperty("VechID", "1");
            object.addProperty("EffectiveDate", "");
            object.addProperty("EffectiveTill", "");
            RegisterAllocatedDriver(object);
        });


    }

    public void RegisterAllocatedDriver(JsonObject jsonObject){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(businessappNewAssignDriverActivity.this).getrestadapter()
                .AssignDriver(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<AssignDriverResponse>>() {
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
                    public void onNext(List<AssignDriverResponse> responseList) {
//                        DisplayToast("Successfully onNext");
                        AssignDriverResponse response=responseList.get(0);
                        if(response.getCode()!=null){
                            DisplayToast(response.getDescription());
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
                            finish();
                        }
                    }
                });
    }


    //region Init Toolbar
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("New driver creation");

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
