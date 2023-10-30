package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.webingate.paysmartbusinessapp.R;

public class customerappTroubleTicketCreation extends AppCompatActivity {
    private Button confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerapp_troubleticketcreation_activity);

        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New trouble ticket");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(v -> Toast.makeText(this, "Trouble ticket is created.", Toast.LENGTH_SHORT).show());
      //  Tools.setSystemBarColor(this, R.color.amber_600);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_done, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
