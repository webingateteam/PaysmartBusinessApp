package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.webingate.paysmartbusinessapp.R;
//import com.webingate.paysmartbusinessapp.fragment.uicollection.container.tablayout.UiContainerTabLayoutTab1Fragment;
//import com.webingate.paysmartbusinessapp.fragment.uicollection.container.tablayout.UiContainerTabLayoutTab2Fragment;
//import com.webingate.paysmartbusinessapp.fragment.uicollection.container.tablayout.UiContainerTabLayoutTab3Fragment;

import com.webingate.paysmartbusinessapp.utils.common_adapter.ViewPagerAdapter;

public class customerappTroubleTicketListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerapp_troubleticketlist_activity);

        initToolbar();

        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new customerappOpenTicketsFragment(), "Open tickets");
        adapter.addFragment(new customerappClosedTicketsFragment(), "Closed tickets");
       //adapter.addFragment(new UiContainerTabLayoutTab3Fragment(), "Tab 3");
        viewPager.setAdapter(adapter);
    }

    //region Init Toolbar

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Trouble tickets");

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