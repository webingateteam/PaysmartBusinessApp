package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.adapter.businessappAssigningVehicleAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverListAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AssignDriverResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetVehicleListResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
//import com.webingate.paysmartbusinessapp.pa;

public class businessappNewAssignDriverActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
    public static final String VehicleID = "VehicleIdKey";
    public static final String DId = "didKey";
    public static final String RegistrationNo = "RegistrationNoKey";
    public static final String Name = "NAmeKey";
    public static final String Phone = "phoneKey";
    public static final String VehicleGroup = "VehicleGroupKey";
    public static final String VehicleType = "VehicleTypeKey";

    Toast toast;

    String regno,vgroup,cmpid,mbno,id;
    int vechid;
    String[] fruits;
    ArrayAdapter<String> adapter2;
    ArrayList<String> list;
    ArrayList<GetVehicleListResponse> VehicleList;
    businessappAssigningVehicleAdapter adapter;

    ArrayList<DrivermasterResponse> DriverList;
    businessappDriverListAdapter adapter1;
    RecyclerView recyclerView;
    Spinner spinner;
    List<String> values = new ArrayList<String>();

    private List<String> dlist = new ArrayList<String>();
    @BindView(R.id.fab)
    Button submit;
    String vgrp,vtype;
    int DriverId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_newallocateddriver_activity);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        regno = prefs.getString(RegistrationNo, null);
        id = prefs.getString(ID, null);
        mbno = prefs.getString(Phone, null);
        DriverId = prefs.getInt(DId, 0);
        vgrp = prefs.getString(VehicleGroup, null);
        vtype = prefs.getString(VehicleType, null);
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
        int ctryid = ApplicationConstants.countryid;
        int fid = ApplicationConstants.fid;
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
                        //DisplayToast("Successfully onCompleted");
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
                             startActivity(new Intent(businessappNewAssignDriverActivity.this, businessappAssignDriverActivity.class));
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
                    public void onNext(List<GetVehicleListResponse> responselist) {
                        VehicleList= (ArrayList <GetVehicleListResponse>) responselist;

//                        if(AllocatedDriverList.getCode()!=null){
//                            DisplayToast(AllocatedDriverList.getDescription());
//                        }else {
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

                                    //Toast.makeText(getApplicationContext(), "Selected : " + obj.getRegistrationNo(), Toast.LENGTH_LONG).show();

                                    SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putInt(VehicleID, obj.getId());
                                    editor.putString(RegistrationNo, obj.getRegistrationNo());
                                    editor.putString(VehicleGroup, obj.getVehicleGroup());
                                    editor.putString(VehicleType, obj.getVehicleType());
                                    editor.commit();

                                    String fid = String.valueOf(ApplicationConstants.fid);
                                    GetDriversList(fid,109);
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
//        Intent intent = new Intent(this, businessappNewAssignDriverActivity.class);
//        startActivity(intent);
    }

    public void GetDriversList(String acct,int uit){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetDriverList_usertype(acct,uit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DrivermasterResponse>>() {

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
                    public void onNext(List<DrivermasterResponse> responselist) {
                        DriverList= (ArrayList <DrivermasterResponse>) responselist;
                        for(int i=0;i<=responselist.size()-1;i++){
                            dlist.add(responselist.get(i).getNAme());
                        }
                           SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                           SharedPreferences.Editor editor = sharedpreferences.edit();
                           editor.putInt(DId, responselist.get(0).getId());
                           editor.putString(Phone, responselist.get(0).getMobilenumber());
//                        if(dlist!=null || !dlist.isEmpty()) {
//                        for (int i=0;i<=dlist.size()-1;i++){
//                            if(dlist.get(i)!=null || !dlist.get(i).isEmpty())
//                            values.add(dlist.get(i));
//                        }
//                        }

//                           SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                           SharedPreferences.Editor editor = sharedpreferences.edit();
//                          editor.putString(Name, DriverList.getNAme().toString());
//                            editor.commit();


                        View view = getLayoutInflater().inflate(R.layout.businessapp_bottomdialog, null);

                        BottomSheetDialog dialog = new BottomSheetDialog(businessappNewAssignDriverActivity.this);

                        //list = new ArrayList<DrivermasterResponse>(Arrays.asList(DriverList));
                        adapter2 = new ArrayAdapter<>(businessappNewAssignDriverActivity.this, android.R.layout.simple_list_item_1, dlist);
                        ListView listView = view.findViewById(R.id.bsDialogListView);

                        listView.setAdapter(adapter2);

                        dialog.setContentView(view);
                        dialog.show();
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView <?> adapter, View v, int position, long id) {

                            DrivermasterResponse selItem = DriverList.get(position);

                            SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            regno = prefs.getString(RegistrationNo, null);
                            vechid = prefs.getInt(VehicleID, 0);
                            vgrp = prefs.getString(VehicleGroup, null);
                            vtype = prefs.getString(VehicleType, null);
                            //String value = selItem.;
                            JsonObject  jsonObject = new JsonObject();
                            jsonObject.addProperty("flag","I");
                            jsonObject.addProperty("Id","");
                            jsonObject.addProperty("DriverName",selItem.getNAme());
                            jsonObject.addProperty("RegistrationNo",regno);
                            jsonObject.addProperty("VechID",vechid);
                            jsonObject.addProperty("VehicleGroupId",vgrp);
                            jsonObject.addProperty("VehicleType",vtype);
                            jsonObject.addProperty("DriverId",selItem.getId());
                            jsonObject.addProperty("PhoneNo",selItem.getMobilenumber());
                            jsonObject.addProperty("fleetId",ApplicationConstants.fid);
                            RegisterAllocatedDriver(jsonObject);
                            //DisplayToast(selItem.getNAme());
                        }
                    });
//                        view.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Context cxt = view.getContext();
//                                AssignDriverDetails(cxt.getPackageName().toString());
//
//                            }
//                        });
                        //listView.setOnItemClickListener((this,view,position) ->
//                        {
//                            AssignDriverDetails(view);
//                        });
//                        listView.setOnItemClickListener((view1, obj, position) ->
//                                {
//                                    //Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();
//                                    JsonObject jsonObject = new JsonObject();
//                                    jsonObject.addProperty("Email", E_email.toString());
//                                    jsonObject.addProperty("EVerificationCode", etop.getText().toString());
//                                    jsonObject.addProperty("userId", E_uid);
//                                    EOTPVerification(jsonObject);
//                                    AssignDriverDetails(obj);
//                                });
                    }
                });
    }

    public void AssignDriverDetails(String str)
    {
       // Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(.this);
//        startActivity(intent);
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
