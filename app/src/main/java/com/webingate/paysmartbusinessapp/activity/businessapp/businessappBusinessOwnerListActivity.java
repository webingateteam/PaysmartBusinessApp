package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
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
import com.webingate.paysmartbusinessapp.adapter.businessappBusinessOwnerListAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverListAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessappVehicleListAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.object.Place;
import com.webingate.paysmartbusinessapp.repository.BusinessOwnerListRepository;
import com.webingate.paysmartbusinessapp.repository.DriverListRepository;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappBusinessOwnerListActivity extends AppCompatActivity {

    ArrayList<DrivermasterResponse> BusinessownerList;
    businessappBusinessOwnerListAdapter adapter;
    // RecyclerView
    RecyclerView recyclerView;
    Toast toast;
    private boolean twist = false;

    private LinearLayout linearPhoto;
    private LinearLayout linearVideo;
    private LinearLayout linearCamera;

    private void initData()
    {
        // get place list
        //placeArrayList = BusinessOwnerListRepository.getPlaceList();
        GetBusinessOwnerlist("0");
    }

    private void initUI()
    {
        initToolbar();

        // get list adapter
        adapter = new businessappBusinessOwnerListAdapter(null);

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
//                    Intent intent = new Intent(this, businessappBusinessOwnerDetailsActivity.class);
//                    startActivity(intent);
//
//                }
//
//        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_businessownerlist_activity);

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
                    Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, businessappNewBusinessOwnerActivity.class);
                    startActivity(intent);
                }

                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()

        );

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
    ArrayList<DrivermasterResponse>  response;
    public void GetBusinessOwnerlist(String ctryId ){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetDriverList(ctryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DrivermasterResponse>>() {

                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Registered");
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
                    public void onNext(List<DrivermasterResponse> responselist) {
                        BusinessownerList= (ArrayList <DrivermasterResponse>) responselist;
                        //   SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        //   SharedPreferences.Editor editor = sharedpreferences.edit();
                        //  editor.putString(Emailotp, response.getEmail());
                        //    editor.commit();
                        //startActivity(new Intent(businessappEOTPVerificationActivity.this, login_activity.class));
                        // DriverList
                        adapter = new businessappBusinessOwnerListAdapter(BusinessownerList);
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
        Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, businessappBusinessOwnerDetailsActivity.class);
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

        toolbar.setTitle("Business Owner");

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
