package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.utils.Utils;

public class businessappDriverDetailsActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String photo= "pphoto";
    private ImageView profileImageView;
    private TextView emailTextView;
    private TextView phoneTextView;
    private TextView websiteTextView;
    private FloatingActionButton editFAB;
    String pt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_driverdetails_activity);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        pt= prefs.getString(photo, null);
        initUI();
        initData();
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

    //endregion

    //region Init Functions
    private void initData() {
        if(pt!=null){
            profileImageView = findViewById(R.id.profileImageView);
            byte[] decodedString= Base64.decode(pt.substring(pt.indexOf(",")+1), Base64.DEFAULT);
            Bitmap image1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profileImageView.setImageBitmap(image1);
        }
        else{
            profileImageView = findViewById(R.id.profileImageView);
            int id = R.drawable.profile2;
            Utils.setCornerRadiusImageToImageView(getApplicationContext(), profileImageView, id, 20, 2,  R.color.md_white_1000);
        }

    }

    private void initUI() {
        initToolbar();

//        profileImageView = findViewById(R.id.profileImageView);
//        int id = R.drawable.profile2;
//        Utils.setCornerRadiusImageToImageView(getApplicationContext(), profileImageView, id, 20, 2,  R.color.md_white_1000);

      //  ImageView coverUserImageView = findViewById(R.id.coverUserImageView);
       // Utils.setImageToImageView(getApplicationContext(), coverUserImageView, id);

        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        websiteTextView = findViewById(R.id.websiteTextView);

        editFAB = findViewById(R.id.editFAB);

    }

    private void initActions() {
        emailTextView.setOnClickListener(view -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "Hello" + "&body=" + "paysmart admin and team");
                intent.setData(data);
                intent.putExtra(Intent.EXTRA_EMAIL, emailTextView.getText().toString());
                startActivity(intent);

            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        });

        phoneTextView.setOnClickListener(view -> {
            try {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +  phoneTextView.getText().toString()));
                startActivity(intent);

            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        });

        websiteTextView.setOnClickListener(view -> {
            try {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteTextView.getText().toString()));
                startActivity(myIntent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        });

        editFAB.setOnClickListener(view -> {

            Toast.makeText(getApplicationContext(), "Click Edit FAB", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, businessappNewDriverActivity.class);
            startActivity(intent);

        });
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Driver Details");

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
    //endregion
}
