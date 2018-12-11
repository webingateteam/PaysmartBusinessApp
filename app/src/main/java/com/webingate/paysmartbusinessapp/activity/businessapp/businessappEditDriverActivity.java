package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.RegisterBusinessUsers;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverDocsListFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverEditUserInfoFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverUserInfoFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppUploadDocsFragment;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
//import com.webingate.paysmartbusinessapp.pa;

public class businessappEditDriverActivity extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String photo= "pphoto";
    public static final String email1="email";
    public static final String mobileno= "mobileno";
    public static final String name1="name";
    private int position = 1;
    private int maxPosition = 5;
    private Button nextButton, prevButton;
    private TextView imageNoTextView;

    ImageView profileImageView;



    EditText email;
    EditText name;
    EditText address;
    EditText city;
    EditText mno;
    EditText postal;
    EditText state;
    Toast toast;

    String pt,em,mo,dname;
    RegisterBusinessUsers rlist;

    businessAppDriverEditUserInfoFragment   userInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_newdriver_activity);

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        pt= prefs.getString(photo, null);
        em= prefs.getString(email1, null);
        mo=prefs.getString(mobileno,null);
        dname=prefs.getString(name1,null);

        Bundle bundle = new Bundle();
        bundle.putString("photo", pt);
        bundle.putString("Email", em);
        bundle.putString("Mobileno", mo);
        bundle.putString("Drivername", dname);
         //set Fragmentclass Arguments
        businessAppDriverEditUserInfoFragment fragobj = new businessAppDriverEditUserInfoFragment();
        fragobj.setArguments(bundle);

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

        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        imageNoTextView = findViewById(R.id.imageNoTextView);




        updatePositionTextView();
        setupFragment(new businessAppDriverEditUserInfoFragment());

    }

    private void updatePositionTextView() {
        imageNoTextView.setText(position + " of " + maxPosition);
    }

    private void setupFragment(Fragment fragment) {
        try {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentLayout, fragment)
                    .commitAllowingStateLoss();
            TextView tt=findViewById(R.id.s_name);
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDataBinding() {

    }

    private void initActions() {
        nextButton.setOnClickListener(v -> {

            if (position < maxPosition) {
                position++;



                updatePositionTextView();
                if(position == 1) {
                    Toast.makeText(this, "Step 1.", Toast.LENGTH_SHORT).show();
                    userInfoFragment =      new businessAppDriverEditUserInfoFragment();

                    setupFragment(userInfoFragment);

                }
                if(position == 2)
                {
                    //EditText name = (EditText)findViewById(R.id.s_name);
                    name = findViewById(R.id.s_name);
                    email = findViewById(R.id.s_email);
                    mno = findViewById(R.id.s_mobileno);
                    address = findViewById(R.id.s_address);
                    city = findViewById(R.id.s_city);
                    postal = findViewById(R.id.s_postal);
                    state = findViewById(R.id.s_state);
                    profileImageView = findViewById(R.id.profileImageView);

                    JsonObject object = new JsonObject();

                    object.addProperty("flag", "U");
                    object.addProperty("Firstname",name.getText().toString());
                    //object.addProperty("lastname","kumar");
                    object.addProperty("AuthTypeId", "");
                    object.addProperty("Password", "123");
                    object.addProperty("Mobilenumber",mno.getText().toString());
                    object.addProperty("Email",email.getText().toString());
                    object.addProperty("CountryId","91");
                    object.addProperty("VehicleGroupId","");
                    object.addProperty("UserAccountNo",ApplicationConstants.driverid);
                    object.addProperty("usertypeid","109");
                    object.addProperty("isDriverOwned","0");
                    object.addProperty("UserPhoto","data:" + ApplicationConstants.document_format + ";base64," +  ApplicationConstants.picdata);
                    RegisterDriver(object);
                    Toast.makeText(this, "Step 2.", Toast.LENGTH_SHORT).show();
                    //setupFragment(new businessAppUploadDocsFragment());
                    //setupFragment(new businessAppDocCheckingFragment());
                    setupFragment(new businessAppDriverDocsListFragment());
                }


                if(position == 3) {
                    Toast.makeText(this, "Step 3.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverUserInfoFragment());
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
                    Toast.makeText(this, "Step 1.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverUserInfoFragment());
                }
                if(position == 2)
                {
                    Toast.makeText(this, "Step 2.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppUploadDocsFragment());
                }


                if(position == 3) {
                    Toast.makeText(this, "Step 3.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverUserInfoFragment());
                }

            } else {
                Toast.makeText(this, "No More Step.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RegisterDriver(JsonObject jsonObject){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(businessappEditDriverActivity.this).getrestadapter()
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
//                        DisplayToast("Successfully onNext");
                        RegisterBusinessUsers response=responseList.get(0);
                        if(response.getCode()!=null){
                            DisplayToast(response.getDescription());
                        }else {
                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            //editor.putString(Username, response.getusername());
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
                             List<RegisterBusinessUsers> rlist=responseList;
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

        toolbar.setTitle("Edit Diver Details");

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