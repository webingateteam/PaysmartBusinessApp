package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDashboardFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppticketagentFragment;
import com.webingate.paysmartbusinessapp.utils.Utils;

public class businessappticketagentDashboardActivity extends AppCompatActivity {

    private boolean twist = false;

    private LinearLayout linearPhoto;
    private LinearLayout linearVideo;
    private LinearLayout linearCamera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_ticketagentdashboard_activity);

        initData();

        initUI();

        initDataBinding();

        initAction();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
    }

    private void initUI() {
        initToolbar();

        BottomNavigationView bottomNavigationView = findViewById(R.id.home9BottomNavigation);
        Utils.removeShiftMode(bottomNavigationView);

        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badge = LayoutInflater.from(this)
                .inflate(R.layout.customerapp_notificationcount_item, bottomNavigationMenuView, false);
        TextView tv = badge.findViewById(R.id.notification_badge);
        tv.setText("8+");
        itemView.addView(badge);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
//                case R.id.searchMenu:
//                    loadFragment(new businessAppDashboardFragment());
//                    break;
                case R.id.orderMenu:
                    startActivity(new Intent(this,businessappCouponsListActivity.class));
                    break;
                case R.id.inboxMenu:
                    startActivity(new Intent(this,businessappNotificationListActivity.class));
                    break;
                case R.id.profileMenu:
                    //loadFragment(new AppDirectoryHome4Fragment());

                    Intent intent = new Intent(this, customerappUserprofileActivity.class);
                    startActivity(intent);
                    break;
                default:
                    loadFragment(new businessAppDashboardFragment());
                    break;
            }

            Toast.makeText(getApplicationContext(), "Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();

            return false;
        });

        loadFragment(new businessAppticketagentFragment());

    }

    private void initDataBinding() {
    }

    private void initAction() {
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Ticket Agent Dashboard");

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

    private void loadFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.home9Frame, fragment)
                .commitAllowingStateLoss();
    }
}
