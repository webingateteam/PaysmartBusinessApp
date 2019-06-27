package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.maps.model.VehicleType;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverListAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessappVehicleListAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetVehicleListResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleListResponse;
import com.webingate.paysmartbusinessapp.object.Place;
import com.webingate.paysmartbusinessapp.repository.DriverListRepository;
import com.webingate.paysmartbusinessapp.repository.VehicleListRepository;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappVehicleListActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String RegistrationNo = "RegNoKey";
    public static final String VehicleGroup = "vgrpKey";
    public static final String VehicleType = "vtypeKey";
    public static final String VehicleCode = "vcodeKey";
    public static final String Phone = "phoneKey";
    public static final String Photo = "PhotoKey";

    String regno,vimage,vgrp,vtype,vcode;

   // ArrayList<Place> placeArrayList;
    businessappVehicleListAdapter adapter;
    // RecyclerView
    RecyclerView recyclerView;
    @BindView(R.id.placeImageView)
     ImageView photo;
    ArrayList<GetVehicleListResponse> VehicleList;
    List<GetVehicleListResponse> VehicleList1;

    private boolean twist = false;
    Toast toast;
    private LinearLayout linearPhoto;
    private LinearLayout linearVideo;
    private LinearLayout linearCamera;

    private void initData()
    {
        // get place list
        //placeArrayList = VehicleListRepository.getPlaceList();
        int ctryid = ApplicationConstants.countryid;
        int fid = ApplicationConstants.fid;
        int vgid = -1;
        GetVehilcelist(ctryid,fid,vgid);
    }

    private void initUI()
    {
        initToolbar();

        // get list adapter
        adapter = new businessappVehicleListAdapter(null);

        // get recycler view
        recyclerView = findViewById(R.id.placeList1RecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    private void initDataBindings()
    {
        // bind adapter to recycler
        recyclerView.setAdapter(adapter);
    }
    private void initActions()
    {
//        adapter.setOnItemClickListener((view, obj, position) ->
//                {
//                    Toast.makeText(this, "Selected : " + obj.name, Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(this, businessappVehicleDetailsActivity.class);
//                    startActivity(intent);
//
//                }
//
//        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_vehicleslist_activity);
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(RegistrationNo, RegistrationNo);
        editor.commit();
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        ApplicationConstants.username= prefs.getString(Username, null);
//        ApplicationConstants.photo= prefs.getString(Photo, null);em= prefs.getString(Email, null);
        regno= prefs.getString(RegistrationNo, null);
        vgrp = prefs.getString(VehicleGroup,null);
        vtype = prefs.getString(VehicleType,null);
        vcode = prefs.getString(VehicleCode,null);
        initData();
        initUI();
        initDataBindings();
        initActions();
        initToolbar();

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
                    //Toast.makeText(getApplicationContext(), "Open fab clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, businessappNewVehicleActivity.class);
                    startActivity(intent);
                }

                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()

        );

        fabVideo.setOnClickListener(

                v ->
                {
                   // Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show();
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
                }
        );

        fabPhoto.setOnClickListener(v ->
        {
            //Toast.makeText(getApplicationContext(), "View Photos clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, businessappNewDriverActivity.class);
            startActivity(intent);

        });

    }
    ArrayList<GetVehicleListResponse>  response;
    public void GetVehilcelist(int ctryid,int fid,int vgid){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(businessappVehicleListActivity.this).getrestadapter()
                .GetVehiclesList(ctryid,fid,vgid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetVehicleListResponse>>() {

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
                    public void onNext(List<GetVehicleListResponse> responselist) {
                        VehicleList= (ArrayList <GetVehicleListResponse>) responselist;
//                           SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                           SharedPreferences.Editor editor = sharedpreferences.edit();
//                           editor.putString(RegistrationNo, responselist.get(0).getRegistrationNo());
//                           editor.putString(VehicleGroup, responselist.get(0).getVehicleGroup());
//                        editor.putString(VehicleType, responselist.get(0).getVehicleType());
//                        editor.putString(VehicleCode, responselist.get(0).getVehicleCode());
//                           editor.putString(Photo, responselist.get(0).getPhoto().toString());
//                           editor.commit();
//                           ApplicationConstants.profilepic = responselist.get(0).getPhoto().toString();
//                        ApplicationConstants.registrationNo= responselist.get(0).getRegistrationNo().toString();
//                        ApplicationConstants.vehiclegroup= responselist.get(0).getVehicleGroup().toString();
//                        ApplicationConstants.vehicleType= responselist.get(0).getVehicleType().toString();
//                        ApplicationConstants.vehiclecode= responselist.get(0).getVehicleCode().toString();
//                          byte[] decodedString= Base64.decode(ApplicationConstants.profilepic.substring(ApplicationConstants.profilepic.indexOf(",")+1), Base64.DEFAULT);
//                        Bitmap image1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        photo.setImageBitmap(image1);
                        //    editor.commit();
                        //startActivity(new Intent(businessappEOTPVerificationActivity.this, login_activity.class));
                        // DriverList
                        adapter = new businessappVehicleListAdapter(VehicleList);
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
    public  void GoToDetails(GetVehicleListResponse obj)
    {
        //Toast.makeText(this, "Selected : " + obj.getRegistrationNo(), Toast.LENGTH_LONG).show();
        ApplicationConstants.photo1=obj.getPhoto();
        ApplicationConstants.registrationNo = obj.getRegistrationNo();
        ApplicationConstants.vehiclegroup = obj.getVehicleGroup();
        ApplicationConstants.vehicleType = obj.getVehicleType();
        ApplicationConstants.vehiclecode = obj.getVehicleCode();
        ApplicationConstants.Vid=Integer.toString(obj.getId());
        ApplicationConstants.chasisNo = obj.getChasisNo();
        ApplicationConstants.engineNo = obj.getEngineno();
        ApplicationConstants.vgrp = String.valueOf(obj.getVehicleGroupId());
        ApplicationConstants.vtype = String.valueOf(obj.getVehicleTypeId());
        ApplicationConstants.country = obj.getCountry();
        Intent intent = new Intent(this, businessappVehicleDetailsActivity.class);
        startActivity(intent);
    }
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

        toolbar.setTitle("Vehicles");

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
