package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.utils.common_adapter.ViewPagerAdapter;


public class businessappDriverTripListActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";

    String mobno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_drivertrips_activity);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mobno = prefs.getString(Phone, null);
        ApplicationConstants.mobileNo = mobno;

        initToolbar();

        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

         //getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }
//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        //adapter.addFragment(new businessapptripslistFragment(), "Current & in progress");
//        adapter.addFragment(new businessapptripscompletedlistFragment(), "Completed");
//        adapter.addFragment(new businessapptripscancellistFragment(), "Cancelled");
//       //adapter.addFragment(new UiContainerTabLayoutTab3Fragment(), "Tab 3");
//        viewPager.setAdapter(adapter);
//    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        businessapptripscompletedlistFragment deliveryFragment = new businessapptripscompletedlistFragment();
        adapter.addFragment(deliveryFragment, "COMPLETED");

        businessapptripscancellistFragment pendingFragment = new businessapptripscancellistFragment();
        adapter.addFragment(pendingFragment, "CANCELLED");

        viewPager.setAdapter(adapter);
    }
  private void checkingtitle(int position){
      ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
      adapter.getItem(position);
  }
    //region Init Toolbar

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Trips List");

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
}