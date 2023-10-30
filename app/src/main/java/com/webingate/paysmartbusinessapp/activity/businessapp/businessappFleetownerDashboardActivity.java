package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.UserInformationResponse;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDashboardFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppFleetownerFragment;
import com.webingate.paysmartbusinessapp.object.Country;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappFleetownerDashboardActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String UserAccountNumber = "UserAccountNo";
    public static final String usertypeid = "usertypeid";
    public static final String CountryId = "countryKey";

    private boolean twist = false;

    private LinearLayout linearPhoto;
    private LinearLayout linearVideo;
    private LinearLayout linearCamera;
    String uan;
    int tid;

    String acntno;
    int utype;

    Toast toast;
    int countryid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerapp_dashboard_activity);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        acntno = prefs.getString(UserAccountNumber, null);
        utype = prefs.getInt(usertypeid, 0);
        countryid = prefs.getInt(CountryId,0);

        initData();

        initUI();

        initDataBinding();

        initAction();

//        FloatingActionButton fab = findViewById(R.id.fab);
//        FloatingActionButton fabVideo = findViewById(R.id.fab_video);
//        FloatingActionButton fabCamera = findViewById(R.id.fab_camera);
//        FloatingActionButton fabPhoto = findViewById(R.id.fab_photo);
//
//
//        linearVideo = findViewById(R.id.linearVideo);
//        linearPhoto = findViewById(R.id.linearPhoto);
//        linearCamera = findViewById(R.id.linearCamera);
//
//        Utils.hideFirstFab(linearVideo);
//        Utils.hideFirstFab(linearCamera);
//        Utils.hideFirstFab(linearPhoto);
//
//        fab.setOnClickListener(v -> {
//
//            twist = Utils.twistFab(v, !twist);
//
//            if (twist) {
//
//                Utils.showFab(linearVideo);
//                Utils.showFab(linearCamera);
//                Utils.showFab(linearPhoto);
//
//            } else {
//
//                Utils.hideFab(linearVideo);
//                Utils.hideFab(linearCamera);
//                Utils.hideFab(linearPhoto);
//
//            }
//        });
//
//        fabVideo.setOnClickListener(
//
//                v ->
//                {
//                    //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(this, businessappNewVehicleActivity.class);
//                    startActivity(intent);
//                }
//
//                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()
//
//        );
//
//        fabCamera.setOnClickListener(v ->
//                {
//                    //Toast.makeText(getApplicationContext(), "Open Camera clicked", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(this, businessappNewBusinessOwnerActivity.class);
//                    startActivity(intent);
//                }
//        );
//
//        fabPhoto.setOnClickListener(v ->
//        {
//            //Toast.makeText(getApplicationContext(), "View Photos clicked", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, businessappNewDriverActivity.class);
//            startActivity(intent);
//
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

    }

    private void initUI() {
        initToolbar();
        BottomNavigationView bottomNavigationView = findViewById(R.id.home9BottomNavigation);
        Utils.removeShiftMode(bottomNavigationView);

        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

//        View badge = LayoutInflater.from(this)
//                .inflate(R.layout.customerapp_notificationcount_item, bottomNavigationMenuView, false);
//        TextView tv = badge.findViewById(R.id.notification_badge);
//        tv.setText("8+");
//        itemView.addView(badge);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {

//                case R.id.searchMenu:
////                    loadFragment(new businessAppDashboardFragment());
////                    break;
                case R.id.orderMenu:
                    startActivity(new Intent(this,businessappCouponsListActivity.class));
                    break;
                case R.id.inboxMenu:
                    startActivity(new Intent(this,businessappNotificationListActivity.class));
                    break;
                case R.id.profileMenu:
                    //loadFragment(new AppDirectoryHome4Fragment());
                    startActivity(new Intent(businessappFleetownerDashboardActivity.this, customerappUserprofileActivity.class));

                    //GetUserInformation(acntno,utype);
//                    Intent intent = new Intent(this, customerappUserprofileActivity.class);
//                    startActivity(intent);
                    break;
                default:
                    loadFragment(new businessAppDashboardFragment());
                    break;
            }

           // Toast.makeText(getApplicationContext(), "Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();

            return false;
        });

        loadFragment(new businessAppFleetownerFragment());

    }

    private void initDataBinding() {
    }

    private void initAction() {
    }

    public void GetUserInformation(String acct,int uit){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetUserInformation(acct,uit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UserInformationResponse>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("User Information Succesfull");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            //DisplayToast("User Information Failed");
//                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
//[Id]
//                            ,[Username]
//                            ,[Email]
//                            ,[Mobilenumber]
//                            ,[StatusId]
//                            ,[Firstname]
//                            ,[lastname]
//                            ,[AuthTypeId]
//                            ,[UserAccountNo]
//                            ,[usertypeid]
//                            ,UserPhoto
                    @Override
                    public void onNext(List<UserInformationResponse> responseList) {
                        UserInformationResponse response=responseList.get(0);
                        if(response.getCode()!=null){
                            DisplayToast(response.getDescription());
                        }else {
                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(UserAccountNumber, response.getUserAccountNo());
                            editor.putString(Email, response.getEmail());
                            editor.putString(Username, response.getUsername());
                            editor.putString(Phone, response.getMobilenumber());
                            //Intent intent = new Intent(customerappUserprofileActivity.this,customerappUserprofileActivity.class);
                            editor.commit();
                            startActivity(new Intent(businessappFleetownerDashboardActivity.this, customerappUserprofileActivity.class));
                            finish();
                        }

                        //   SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        //   SharedPreferences.Editor editor = sharedpreferences.edit();
                        //  editor.putString(Emailotp, response.getEmail());
                        //    editor.commit();
                        //startActivity(new Intent(busianessappEOTPVerificationActivity.this, login_activity.class));
                        // DriverList
                        //adapter = new businessappDriverTripslistAdapter(DrivertripList);
                        //recyclerView.setAdapter(adapter);

//                       // adapter.setOnItemClickListener((view, obj, position) ->
//                                {
//                                    //Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();
//
//                                    GoToDetails(obj);
//                                }
//                        );
                    }
                });
    }

    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Fleet Owner Dashboard");

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

    private void loadFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.home9Frame, fragment)
                .commitAllowingStateLoss();
    }
}
