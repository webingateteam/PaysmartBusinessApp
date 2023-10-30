package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.webingate.paysmartbusinessapp.R;


public class businessappPaymentModeActivity extends AppCompatActivity {

    private Button confirmButton;
    private LinearLayout addcards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_paymentmode_activity);
        addcards=(LinearLayout)findViewById(R.id.addcards);
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

    private void initData() {

    }

    private void initUI() {

        // Init Toolbar
        initToolbar();

        confirmButton = findViewById(R.id.confirmButton);

    }

    private void initDataBinding() {

    }

    private void initActions() {

        confirmButton.setOnClickListener(v -> Toast.makeText(this, "Clicked Confirm and Checkout.", Toast.LENGTH_SHORT).show());
        addcards.setOnClickListener(v ->{
            Toast.makeText(this, "clicked one Add Card", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(businessappPaymentModeActivity.this,businessappPaymentCardDetails.class));

        });
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Select payment mode");

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

    public void onItemClick(View view) {
        Toast.makeText(this, "list item clicked.", Toast.LENGTH_SHORT).show();
    }
}
