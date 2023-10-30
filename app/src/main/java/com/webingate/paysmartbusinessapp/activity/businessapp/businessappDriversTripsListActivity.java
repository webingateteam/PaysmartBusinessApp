package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverListAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverTripslistAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappDriversTripsListActivity extends AppCompatActivity {

    Toast toast;
    ArrayList<GetdriverTripsResponse> DrivertripList;

    // RecyclerView
    RecyclerView recyclerView;
    businessappDriverTripslistAdapter adapter;
    private boolean twist = false;
    String dno;
    int typid;

    private void initData()
    {

//        JsonObject object = new JsonObject();
//        object.addProperty("flag", "I");
//        object.addProperty("Firstname", "");
//        object.addProperty("lastname", "");
//        object.addProperty("AuthTypeId", "2");
//        object.addProperty("Password", "");
//        object.addProperty("Mobilenumber", "");
//        object.addProperty("Email", "");
//        object.addProperty("Email", "");

//        Intent intent =getIntent();
//        dno=intent.getStringExtra("Driverno");
//
//        GetDriverTrips(dno);
    }

    private void initUI()
    {
        initToolbar();

        // get list adapter

        adapter = new businessappDriverTripslistAdapter(null);
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_driverstripslist_activity);
        initData();
        initUI();
        initDataBindings();
        initActions();


    }

    ArrayList<GetdriverTripsResponse>  response;

    public void GetDriverTrips( String driverNo){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .Getdrivertrips(driverNo,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetdriverTripsResponse>>() {
                    @Override
                    public void onCompleted() {
                        // DisplayToast("Successfully get Drivers Trip list");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                           // DisplayToast("Unable to Register");
//                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<GetdriverTripsResponse> trips) {
                        DrivertripList= (ArrayList <GetdriverTripsResponse>) trips;
                        //   SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        //   SharedPreferences.Editor editor = sharedpreferences.edit();
                        //  editor.putString(Emailotp, response.getEmail());
                        //    editor.commit();
                        //startActivity(new Intent(businessappEOTPVerificationActivity.this, login_activity.class));
                        // DriverList
                        adapter = new businessappDriverTripslistAdapter(DrivertripList);
                        recyclerView.setAdapter(adapter);

                        adapter.setOnItemClickListener((view, obj, position) ->
                                {
                                    //Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();

                                    GoToDetails(obj);
                                }
                        );
                    }
                });
    }

    public  void GoToDetails(GetdriverTripsResponse obj)
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

        toolbar.setTitle("Trips");

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
