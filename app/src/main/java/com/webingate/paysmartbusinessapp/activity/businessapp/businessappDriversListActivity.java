package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverListAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.utils.Utils;

import org.joda.time.convert.Converter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappDriversListActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
    public static final String VehicleID = "VehicleIdKey";
    public static final String DId = "didKey";
    public static final String RegistrationNo = "RegistrationNoKey";
    public static final String Name = "NAmeKey";
    public static final String Phone = "phoneKey";
    public static final String Profilepic = "profilepic";
    public static final String VehicleGroup = "VehicleGroupKey";
    public static final String VehicleType = "VehicleTypeKey";
    public static final String CountryId = "countryKey";
    public static final String Email = "emailKey";

    Toast toast;
    ArrayList<DrivermasterResponse> DriverList;
    businessappDriverListAdapter adapter;
    // RecyclerView
    RecyclerView recyclerView;

    private boolean twist = false;
    String uaccountno;
    int typid;
    private LinearLayout linearPhoto;
    private LinearLayout linearVideo;
    private LinearLayout linearCamera;
    int id,countryid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_driverslist_activity);

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        countryid = prefs.getInt(CountryId, 0);
        id = prefs.getInt(ID, 0);
        ApplicationConstants.fid = id;
        ApplicationConstants.countryid = countryid;

        initData();
        initUI();
        initDataBindings();
        initActions();

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

        fab.setOnClickListener(

                v ->
                {
                    // Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, businessappNewDriverActivity.class);
                    startActivity(intent);
                }

                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()

        );

        fabVideo.setOnClickListener(

                v ->
                {
                    //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, businessappNewVehicleActivity.class);
                    startActivity(intent);
                }

                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()

        );

        fabCamera.setOnClickListener(v ->
        {
            //Toast.makeText(getApplicationContext(), "Open Camera clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, businessappNewStaffActivity.class);
            startActivity(intent);
        });

        fabPhoto.setOnClickListener(v ->
        {
            //Toast.makeText(getApplicationContext(), "View Photos clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, businessappNewDriverActivity.class);
            startActivity(intent);

        });
    }

    private void initData()
    {
         String fid = String.valueOf(ApplicationConstants.fid);
        GetDriversList(fid,109);
    }

    private void initUI()
    {
        initToolbar();

        // get list adapter

        adapter = new businessappDriverListAdapter(null);
        // get recycler view
        recyclerView = findViewById(R.id.placeList1RecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //recyclerView.setAdapter(adapter);
    }
    private void initDataBindings()
    {
      //  DriverList = response;
                // bind adapter to recycler
      //  recyclerView.setAdapter(adapter);
    }
    private void initActions()
    {

    }


    ArrayList<DrivermasterResponse>  response;

    public void GetDriversList(String acct,int uit){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetDriverList_usertype(acct,uit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DrivermasterResponse>>() {

                    @Override
                    public void onCompleted() {
                       // DisplayToast("Successfully Registered");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            //DisplayToast("Error");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DrivermasterResponse> responselist) {
                        DriverList= (ArrayList <DrivermasterResponse>) responselist;
                     //   SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                     //   SharedPreferences.Editor editor = sharedpreferences.edit();
                      //  editor.putString(Emailotp, response.getEmail());
                    //    editor.commit();
                        //startActivity(new Intent(businessappEOTPVerificationActivity.this, login_activity.class));
                       // DriverList
                        adapter = new businessappDriverListAdapter(DriverList);
                        recyclerView.setAdapter(adapter);

                        adapter.setOnItemClickListener((view, obj, position) ->
                                {
                                    //Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();

                                    GoToDetails(obj);
                                }
                        );
                       // adapter.notifyDataSetChanged();
                       // finish();
                    }
                });


    }

    public  void GoToDetails(DrivermasterResponse obj)
    {
        //Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();
           SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
           SharedPreferences.Editor editor = sharedpreferences.edit();
           editor.putString(Profilepic, (obj.getUserPhoto()!=null?obj.getUserPhoto():null));
           editor.putString(Email,obj.getEmail());
           editor.putString(Phone,obj.getMobilenumber());
           editor .putString(Name,obj.getNAme());
           editor.commit();

           ApplicationConstants.drivername=obj.getNAme();
           ApplicationConstants.drivermno=obj.getMobilenumber();
           ApplicationConstants.driveremail=obj.getEmail();
           ApplicationConstants.driverpic=(obj.getUserPhoto()!=null?obj.getUserPhoto():null);
//           ApplicationConstants.driverid=Integer.toString(obj.getDId());
        ApplicationConstants.driverid=obj.getUserAccountNo();
        Intent intent = new Intent(this, businessappDriverDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast= Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }

    //region Init Toolbar
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Drivers");

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //endregion
}
