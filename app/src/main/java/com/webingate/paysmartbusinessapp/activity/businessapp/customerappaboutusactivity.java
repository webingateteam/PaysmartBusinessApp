package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;

public class customerappaboutusactivity extends AppCompatActivity {

    private Button contactButton;
    private TextView facebookTextView, twitterTextView, linkedInTextView, googlePlusTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerapp_aboutus);

        initData();

        initUI();

        initDataBinding();

        initActions();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //region Init Functions
    private void initData() {

    }

    private void initUI() {
        initToolbar();

        contactButton = findViewById(R.id.contactButton);

        facebookTextView  = findViewById(R.id.facebookTextView);
        twitterTextView = findViewById(R.id.twitterTextView);
        linkedInTextView  = findViewById(R.id.linkedinTextView);
        googlePlusTextView = findViewById(R.id.googlePlusTextView);

    }

    private void initDataBinding() {

    }

    private void initActions() {
        contactButton.setOnClickListener(v -> {

//            String mailto = "mailto:paysmart@gmail.com" +
//                    "&subject=" + "Paysmart Contact from App!" +
//                    "&body=" + "";
//
//            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//            emailIntent.setData(Uri.parse(mailto));
//
//            try {
//                startActivity(emailIntent);
//            } catch (ActivityNotFoundException e) {
//                e.printStackTrace();
//            }

            Intent intent = new Intent(this, customerAppContactUs.class);
            startActivity(intent);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }

        });

        facebookTextView.setOnClickListener(v -> Toast.makeText(this, "Clicked Facebook.", Toast.LENGTH_SHORT).show());
        twitterTextView.setOnClickListener(v -> Toast.makeText(this, "Clicked Twitter.", Toast.LENGTH_SHORT).show());
        linkedInTextView.setOnClickListener(v -> Toast.makeText(this, "Clicked Linked In.", Toast.LENGTH_SHORT).show());
        googlePlusTextView.setOnClickListener(v -> Toast.makeText(this, "Clicked Google Plus.", Toast.LENGTH_SHORT).show());

    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("About Us");

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
}
