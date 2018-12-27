package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.UserInformationResponse;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppBusinessownerFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDashboardFragment;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappBusinessownerDashboardActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String UserAccountNumber = "UserAccountNo";
    public static final String usertypeid = "usertypeid";

    private boolean twist = false;

    String acntno;
    int utype;

    private LinearLayout linearPhoto;
    private LinearLayout linearVideo;
    private LinearLayout linearCamera;
    Toast toast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_busninessownerdashboard_activity);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        acntno = prefs.getString(UserAccountNumber, null);
        utype = prefs.getInt(usertypeid, 0);

        initData();

        initUI();

        initDataBinding();

        initAction();

        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fabVideo = findViewById(R.id.fab_video);
        FloatingActionButton fabCamera = findViewById(R.id.fab_camera);
        FloatingActionButton fabPhoto = findViewById(R.id.fab_photo);


        linearVideo = findViewById(R.id.linearVideo);
        linearPhoto = findViewById(R.id.linearPhoto);
        linearCamera = findViewById(R.id.linearCamera);

        Utils.hideFirstFab(linearVideo);
        Utils.hideFirstFab(linearCamera);
        Utils.hideFirstFab(linearPhoto);

        fab.setOnClickListener(v -> {

            twist = Utils.twistFab(v, !twist);

            if (twist) {

                Utils.showFab(linearVideo);
                Utils.showFab(linearCamera);
                Utils.showFab(linearPhoto);

            } else {

                Utils.hideFab(linearVideo);
                Utils.hideFab(linearCamera);
                Utils.hideFab(linearPhoto);

            }
        });

        fabVideo.setOnClickListener(

                v ->
                {
                    Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, businessappNewVehicleActivity.class);
                    startActivity(intent);
                }

                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()

        );

        fabCamera.setOnClickListener(v ->
                {
                    Toast.makeText(getApplicationContext(), "Open Camera clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, businessappNewBusinessOwnerActivity.class);
                    startActivity(intent);
                }
        );

        fabPhoto.setOnClickListener(v ->
        {
            Toast.makeText(getApplicationContext(), "View Photos clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, businessappNewDriverActivity.class);
            startActivity(intent);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
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

        View badge = LayoutInflater.from(this)
                .inflate(R.layout.customerapp_notificationcount_item, bottomNavigationMenuView, false);
        TextView tv = badge.findViewById(R.id.notification_badge);
        tv.setText("8+");
        itemView.addView(badge);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
//                case R.id.searchMenu:
//                    loadFragment(new businessAppDashboardFragment());
//                    break;
//                case R.id.listMenu:
//                    loadFragment(new AppDirectoryHome2Fragment());
//                    break;
//                case R.id.historyMenu:
//                    loadFragment(new AppDirectoryHome3Fragment());
//                    break;
                case R.id.profileMenu:
                    //loadFragment(new AppDirectoryHome4Fragment());
                    GetUserInformation(acntno,utype);
//                    Intent intent = new Intent(this, businessappBusinessOwnerprofileActivity.class);
//                    startActivity(intent);
                    break;
                default:
                    loadFragment(new businessAppDashboardFragment());
                    break;
            }

            Toast.makeText(getApplicationContext(), "Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();

            return false;
        });

        loadFragment(new businessAppBusinessownerFragment());

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
                        DisplayToast("User Information Succesfull");
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
                            startActivity(new Intent(businessappBusinessownerDashboardActivity.this, customerappUserprofileActivity.class));
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

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Business Owner dashboard");

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
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }
}
