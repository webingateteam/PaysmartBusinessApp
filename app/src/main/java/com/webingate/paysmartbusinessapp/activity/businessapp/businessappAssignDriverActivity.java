package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.webingate.paysmartbusinessapp.adapter.businessappAllocatedDriverListAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverListAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AllocatedDriverListResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappAssignDriverActivity extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
    public static final String RegistrationNo = "RegistrationNoKey";
    public static final String Phone = "phoneKey";

    Toast toast;
    ArrayList<AllocatedDriverListResponse> AllocatedDriverList;
    businessappAllocatedDriverListAdapter adapter;
    // RecyclerView
    RecyclerView recyclerView;

    private boolean twist = false;
    String uaccountno;
    int typid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_assigndriverlist_activity);

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ApplicationConstants.mobileNo = prefs.getString(Phone, null);
        ApplicationConstants.fid = prefs.getInt(ID, 0);

        initData();
        initUI();
        initDataBindings();
        initActions();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(

                v ->
                {
                    //Toast.makeText(getApplicationContext(), "Assign New Driver", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, businessappNewAssignDriverActivity.class);
                    startActivity(intent);
                    finish();
                }

                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()

        );
    }

    private void initData()
    {
//        Intent intent =getIntent();
//        uaccountno=intent.getStringExtra("UserAccountNo");
//        typid=intent.getIntExtra("usertypeid",0);
        GetAllocatedDriverList(ApplicationConstants.fid);
    }


    private void initUI()
    {
        initToolbar();

        // get list adapter

        adapter = new businessappAllocatedDriverListAdapter(null);
        // get recycler view
        recyclerView = findViewById(R.id.placeList1RecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //recyclerView.setAdapter(adapter);
    }
    private void initDataBindings()
    {
//        DriverList = response;
//                 bind adapter to recycler
//        recyclerView.setAdapter(adapter);
    }
    private void initActions()
    {

    }


    ArrayList<AllocatedDriverListResponse>  response;

    public void GetAllocatedDriverList(int VID){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .Getallocatedriver(VID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<AllocatedDriverListResponse>>() {

                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully Registered");
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
                    public void onNext(List<AllocatedDriverListResponse> responselist) {
                        AllocatedDriverList= (ArrayList <AllocatedDriverListResponse>) responselist;
//                        if(AllocatedDriverList.getCode()!=null){
//                            DisplayToast(AllocatedDriverList.getDescription());
//                        }else {
//                            SharedPreferences sharedPref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPref.edit();
////                            SharedPreferences pref = getApplicationContext().getSharedPreferences(MyPREFERENCES, 0);
////                            Editor editor = pref.edit();
//                            editor.putString(RegistrationNo, r);
//                            editor.putInt(Phone, AllocatedDriverList.getusertypeid());
                            //   SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            //   SharedPreferences.Editor editor = sharedpreferences.edit();
                            //  editor.putString(Emailotp, response.getEmail());
                            //    editor.commit();
                            //startActivity(new Intent(businessappEOTPVerificationActivity.this, login_activity.class));
                            // DriverList
                            adapter = new businessappAllocatedDriverListAdapter(AllocatedDriverList);
                            recyclerView.setAdapter(adapter);

                            adapter.setOnItemClickListener((view, obj, position) ->
                                    {
                                        //Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();

                                        GoToDetails(obj);
                                    }
                            );
                        //}
                       // adapter.notifyDataSetChanged();
                       // finish();
                    }
                });


    }

    public  void GoToDetails(AllocatedDriverListResponse obj)
    {
        //Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();
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

        toolbar.setTitle("Assigned Driver List");

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
