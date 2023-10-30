package com.webingate.paysmartbusinessapp.customerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.webingate.paysmartbusinessapp.R;
@SuppressLint("NewApi")
public class SosContacts extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listview1)
    ListView listview1;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    @SuppressLint("NewApi")
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // you can title and subtitle dynamically
        //getSupportActionBar().setTitle("Payments");
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SosContacts.this, ContactsList.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        return true;
    }


}
