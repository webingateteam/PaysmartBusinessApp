package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverTripslistAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;
import com.webingate.paysmartbusinessapp.utils.common_adapter.ViewPagerAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class businessappCurrenttripListActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";

    RecyclerView recyclerView;
    businessappDriverTripslistAdapter adapter;
    ArrayList <GetdriverTripsResponse> completetripList;

    private android.app.ProgressDialog pd;

    String mbno,mb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_currenttrips_activity);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mbno = prefs.getString(Phone, null);
        ApplicationConstants.mobileNo = mbno;

        initUI();

        initData();

//        ViewPager viewPager = findViewById(R.id.viewPager);
//        setupViewPager(viewPager);
//
//        TabLayout tabLayout = findViewById(R.id.tabLayout);
//        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         //getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new businessapptripslistFragment(), "Current & in progress");
        adapter.addFragment(new businessapptripscompletedlistFragment(), "Completed");
        adapter.addFragment(new businessapptripscancellistFragment(), "Cancelled");
       //adapter.addFragment(new UiContainerTabLayoutTab3Fragment(), "Tab 3");
        viewPager.setAdapter(adapter);
    }
  private void checkingtitle(int position){
      ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
      adapter.getItem(position);
  }

  private void initUI(){

        initToolbar();

      adapter=new businessappDriverTripslistAdapter(null);

      recyclerView = findViewById(R.id.RecyclerView);
      RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
      recyclerView.setLayoutManager(mLayoutManager);
      recyclerView.setItemAnimator(new DefaultItemAnimator());
  }

  private void initData(){

      mb= ApplicationConstants.mobileNo;
      int tt=1;
      GetDrivercompleteTrips(mb,tt);
  }
    //region Init Toolbar

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Current Trips");

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


    public void GetDrivercompleteTrips( String driverNo, int status){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .Getdrivertrips(driverNo,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetdriverTripsResponse>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully get Drivers Trip list");
                           //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            //DisplayToast("Unable to Register");
                           // StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<GetdriverTripsResponse> trips) {
                        completetripList= (ArrayList<GetdriverTripsResponse>) trips;
//                        DisplayToast("Next to Register");
//                        displayListView1();
                        //   SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        //   SharedPreferences.Editor editor = sharedpreferences.edit();
                        //  editor.putString(Emailotp, response.getEmail());
                        //    editor.commit();
                        //startActivity(new Intent(busianessappEOTPVerificationActivity.this, login_activity.class));
                        // DriverList
                        adapter = new businessappDriverTripslistAdapter(completetripList);
                        recyclerView.setAdapter(adapter);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();
        int iid = item.getItemId();
        if (iid == android.R.id.home) {
            finish();
        }
        else if(iid == R.menu.menu_edit)
        {
           Intent i = new Intent(this, customerappTroubleTicketCreation.class);
           startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }

    public void StartDialogue(){
        pd=new android.app.ProgressDialog(this);
        pd.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Please wait.....");

        pd.incrementProgressBy(50);
        pd.show();
    }
    public void StopDialogue(){

        pd.dismiss();
    }
}