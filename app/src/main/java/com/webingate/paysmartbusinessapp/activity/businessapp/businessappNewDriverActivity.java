package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverDocsFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverUserInfoFragment;
//import com.webingate.paysmartbusinessapp.pa;

public class businessappNewDriverActivity extends AppCompatActivity {

    private int position = 1;
    private int maxPosition = 5;
    private Button nextButton, prevButton;
    private TextView imageNoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_newdriver_activity);

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

    }

    private void initUI() {

        initToolbar();

        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        imageNoTextView = findViewById(R.id.imageNoTextView);

        updatePositionTextView();
        setupFragment(new businessAppDriverUserInfoFragment());

    }

    private void updatePositionTextView() {
        imageNoTextView.setText(position + " of " + maxPosition);
    }

    private void setupFragment(Fragment fragment) {
        try {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentLayout, fragment)
                    .commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDataBinding() {

    }

    private void initActions() {
        nextButton.setOnClickListener(v -> {

            if (position < maxPosition) {
                position++;

                updatePositionTextView();
                if(position == 1) {
                    Toast.makeText(this, "Step 1.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverUserInfoFragment());
                }
                if(position == 2)
                {
                    Toast.makeText(this, "Step 2.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverDocsFragment());
                }


                if(position == 3) {
                    Toast.makeText(this, "Step 3.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverUserInfoFragment());
                }

            } else {
                Toast.makeText(this, "No More Step.", Toast.LENGTH_SHORT).show();
            }
        });

        prevButton.setOnClickListener(v -> {

            if (position > 1) {
                position--;

                updatePositionTextView();
                if(position == 1) {
                    Toast.makeText(this, "Step 1.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverUserInfoFragment());
                }
                if(position == 2)
                {
                    Toast.makeText(this, "Step 2.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverDocsFragment());
                }


                if(position == 3) {
                    Toast.makeText(this, "Step 3.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverUserInfoFragment());
                }

            } else {
                Toast.makeText(this, "No More Step.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    //region Init Toolbar
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("New driver creation");

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

    //endregion
}
