package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.RegisterBusinessUsers;
import com.webingate.paysmartbusinessapp.adapter.businessappAllocatedDriverListAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessappAssigningVehicleAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverListAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessappVehicleListAdapter;
import com.webingate.paysmartbusinessapp.adapter.uicollection.CustomSpinnerAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AllocatedDriverListResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AssignDriverResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetVehicleListResponse;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverUserInfoFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppUploadDocsFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
//import com.webingate.paysmartbusinessapp.pa;

public class businessappNewAssignDriverActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
    public static final String RegistrationNo = "RegistrationNoKey";
    public static final String Phone = "phoneKey";

    Toast toast;
    String regno,vgroup,id,cmpid,mbno;

    ArrayList<GetVehicleListResponse> VehicleList;
    businessappAssigningVehicleAdapter adapter;

    ArrayList<DrivermasterResponse> DriverList;
    businessappDriverListAdapter adapter1;
    RecyclerView recyclerView;
    Spinner spinner;


    @BindView(R.id.fab)
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_newallocateddriver_activity);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        regno = prefs.getString(RegistrationNo, null);
        id = prefs.getString(ID, null);
        mbno = prefs.getString(Phone, null);
//        mobileOTP = prefs.getString(Mobileotp, null);
//        ApplicationConstants.username = prefs.getString(Name, null);

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
        //GetVehiclesList();
        int ctryid = -1;
        int fid = -1;
        int vgid = -1;
        GetVehiclesList(ctryid,fid,vgid);

    }

    private void initUI() {

        initToolbar();

        adapter = new businessappAssigningVehicleAdapter(null);

        // get recycler view
        recyclerView = findViewById(R.id.placeList1RecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        submit = findViewById(R.id.fab);

    }



    private void initDataBinding() {

        recyclerView.setAdapter(adapter);
        //recyclerView.setAdapter(adapter1);

    }



    private void initActions() {
//        submit.setOnClickListener(
//
//                v ->
//                {
//                    String ctryId = "91";
//                    GetDriversList(ctryId);
//
//                    Toast.makeText(getApplicationContext(), "Assign New Driver", Toast.LENGTH_SHORT).show();
//                });
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

    ArrayList<GetVehicleListResponse>  response;
    public void GetVehiclesList(int ctryid,int fid,int vgid){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetVehiclesList(ctryid,fid,vgid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetVehicleListResponse>>() {

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
                    public void onNext(List<GetVehicleListResponse> responselist) {
                        VehicleList= (ArrayList <GetVehicleListResponse>) responselist;
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
                        adapter = new businessappAssigningVehicleAdapter(VehicleList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener((view, obj, position) ->
                                {

                                    Toast.makeText(getApplicationContext(), "Selected : " + obj.getRegistrationNo(), Toast.LENGTH_LONG).show();

                                    GetDriversList("1");
                                }
                        );
                        //}
                        // adapter.notifyDataSetChanged();
                        // finish();
                    }
                });


    }

    public  void GoToDetails(GetVehicleListResponse obj)
    {
        Toast.makeText(this, "Selected : " + obj.getRegistrationNo(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, businessappNewAssignDriverActivity.class);
        startActivity(intent);
    }

    public void GetDriversList(String ctryId){
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
                        DriverList= (ArrayList <DrivermasterResponse>) responselist;
                        //   SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        //   SharedPreferences.Editor editor = sharedpreferences.edit();
                        //  editor.putString(Emailotp, response.getEmail());
                        //    editor.commit();
                        //startActivity(new Intent(businessappEOTPVerificationActivity.this, login_activity.class));
                        // DriverList
//                        adapter1 = new businessappDriverListAdapter(DriverList);
//                        recyclerView.setAdapter(adapter1);
//
//                        adapter1.setOnItemClickListener((view, obj, position) ->
//                                {
//                                    //Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();
//
//                                    //GoToDetails(obj);
//                                }
//                        );
                        // adapter.notifyDataSetChanged();
                        // finish();
                    }
                });


    }


//    public void GetVehiclesList(){
//        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
//                .GetVehiclesList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<GetVehicleListResponse>>() {
//
//                    @Override
//                    public void onCompleted() {
//                        DisplayToast("Successfully Registered");
//                        //StopDialogue();
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        try {
//                            //Log.d("OnError ", e.getMessage());
//                            DisplayToast("Error");
//                            //StopDialogue();
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onNext(List<GetVehicleListResponse> responselist) {
//                        VehiclesList= (ArrayList<GetVehicleListResponse>) responselist;
//                        //   SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                        //   SharedPreferences.Editor editor = sharedpreferences.edit();
//                        //  editor.putString(Emailotp, response.getEmail());
//                        //    editor.commit();
//                        //startActivity(new Intent(businessappEOTPVerificationActivity.this, login_activity.class));
//                        // DriverList
//                        adapter = new businessappDriverListAdapter(VehiclesList);
//                        recyclerView.setAdapter(adapter);
//
//                        adapter.setOnItemClickListener((view, obj, position) ->
//                                {
//                                    //Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();
//
//                                    GoToDetails(obj);
//                                }
//                        );
//                        // adapter.notifyDataSetChanged();
//                        // finish();
//                    }
//                });
//
//
//    }


    //region Init Toolbar
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("New Assign Driver");

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
