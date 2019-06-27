package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.RegisterBusinessUsers;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverListAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.PendingDocsResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.PendingDocsTable1Item;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.PendingDocsTableItem;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.UserInformationResponse;
import com.webingate.paysmartbusinessapp.driverapplication.MainActivity;
import com.webingate.paysmartbusinessapp.utils.Utils;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cropper.CropImage;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class customerappUserprofileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Photo = "photoKey";
    public static final String Email = "emailKey";
    public static final String UserAccountNumber = "UserAccountNo";
    public static final String usertypeid = "usertypeid";
    public static final String gender = "GenderKey";

    Toast toast;

    String acntno;
    String em,us,ph,firstname,lastname;
    int utype;
     Toolbar toolbar;

     @BindView(R.id.emailTextView)
     TextView email;
     @BindView(R.id.phoneTextView)
     TextView pphone;
     @BindView(R.id.UsernameTextView)
     TextView username;
     @BindView(R.id.FirstnameTextView)
     TextView fname;
     @BindView(R.id.phoneno)
     TextView nhphone;


     @BindView(R.id.userImageView) ImageView upict;
     private int serverrequestFlag;
     @BindView(R.id.userImageView1) ImageView pimage;
     ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerapp_userprofile_activity);
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(UserAccountNumber, Email);
        editor.commit();

       SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        em= prefs.getString(Email, null);
       ph= prefs.getString(Phone, null);
       us= prefs.getString(Username, null);
       firstname = prefs.getString(Username, null);


        initUI();
        initData();
        //initActions();




        FloatingActionButton fab = findViewById(R.id.editFAB);

        fab.setOnClickListener(

                v ->
                {
                    ApplicationConstants.email=Email;
                    ApplicationConstants.username=Username;
                    ApplicationConstants.mobileNo=Phone;
                    ApplicationConstants.pic=Photo;
                    Toast.makeText(getApplicationContext(), "User Details", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, businessappUserDetailsActivity.class);
                    startActivity(intent);
                }

                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()

        );
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            //startActivity(new Intent(this,businessappFleetownerDashboardActivity.class));
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_profile) {
            Toast.makeText(this, "Clicked nav_profile.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_paymentmode) {
          //  Toast.makeText(this, "Clicked nav_paymentmode.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, businessappPaymentModeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_bookings) {
            //Toast.makeText(this, "Clicked nav_bookings.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, customerappBookingsMainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_ewallet) {
            Toast.makeText(this, "Clicked nav_ewallet.", Toast.LENGTH_SHORT).show();
            startActivity( new Intent(customerappUserprofileActivity.this, businessappEwalletActivity.class));
        } else if (id == R.id.nav_notification) {
            Toast.makeText(this, "Clicked nav_notification.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_alerts) {
            Toast.makeText(this, "Clicked nav_alerts.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,businessappDriverDocList.class));
        } else if (id == R.id.nav_preferences) {
            Toast.makeText(this, "Clicked nav_preferences.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_coupons) {
        Toast.makeText(this, "Clicked nav_coupons.", Toast.LENGTH_SHORT).show();

    } else if (id == R.id.nav_sos) {
       // Toast.makeText(this, "Clicked nav_sos.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, customerappSOSListActivity.class);
            startActivity(intent);

    }
        else if (id == R.id.nav_faq) {
            //Toast.makeText(this, "Clicked nav_faq.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, customerappFAQMainMenuActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            //Toast.makeText(this, "Clicked Logout.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, login_activity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about_us) {
           // Toast.makeText(this, "Clicked About Us.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, customerappaboutusactivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_contact_us) {
            //Toast.makeText(this, "Clicked Contact Us.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, customerAppContactUs.class);
            startActivity(intent);
        } else if (id == R.id.nav_tticket) {
            //Toast.makeText(this, "Clicked nav_tticket.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, customerappTroubleTicketListActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

        private void initUI() {
        initToolbar();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        if(Utils.isRTL()) {
//            navigationView.setTextDirection(View.TEXT_DIRECTION_RTL);
//        }else {
//            navigationView.setTextDirection(View.TEXT_DIRECTION_LTR);
//        }


        View headerLayout = navigationView.getHeaderView(0);
        ImageView userImageView = headerLayout.findViewById(R.id.userImageView);
        upict=headerLayout.findViewById(R.id.userImageView);
//        Utils.setCircleImageToImageView(this, userImageView, R.drawable.profile1, 0, 0);

//        ImageView userImageView1 = findViewById(R.id.userImageView1);
//        Utils.setCircleImageToImageView(this, userImageView1, R.drawable.profile1, 0, 0);

        TextView tt = findViewById(R.id.emailTextView);
        TextView pht = findViewById(R.id.phoneTextView);
        TextView username = findViewById(R.id.UsernameTextView);
        TextView fname = findViewById(R.id.FirstnameTextView);
        TextView pht1= findViewById(R.id.textView228);
        pimage=findViewById(R.id.userImageView1);
        nhphone=findViewById(R.id.phoneno);

        tt.setText(em);
        pht.setText(ph);
        username.setText(us);
        fname.setText(firstname);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    private void initData(){

        GetDriverDetails( ApplicationConstants.userAccountNo, ApplicationConstants.usertypeid);

    }

    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }

//    private void initActions(){
//
//        editFAB.setOnClickListener(
//
//                v ->
//                {
//                    Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(this, businessappNewDriverActivity.class);
//                    startActivity(intent);
//                }
//
//                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()
//
//        );
//
//        }
    private void initToolbar() {

        toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("User profile");

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
    public void GetDriverDetails(String acct,int uit ){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetUserInformation(acct,uit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UserInformationResponse>>() {

                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully Registered");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            //Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<UserInformationResponse> responselist) {
                        UserInformationResponse res = responselist.get(0);
                        //DisplayToast("Successfully Registered");
                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(Username, res.getUsername());
                        editor.putString(Email, res.getEmail());
                        editor.commit();


                        email=(TextView)findViewById(R.id.emailTextView);
                        pphone=(TextView)findViewById(R.id.phoneTextView);
                        username=(TextView)findViewById(R.id.UsernameTextView);
                        fname=(TextView)findViewById(R.id.FirstnameTextView);
                        //joindate=(TextView)findViewById(R.id.textView31);
                        nhphone=(TextView)findViewById(R.id.phoneno);
                        TextView pht1= findViewById(R.id.textView228);
                         nhphone.setText(res.getMobilenumber());
                         pphone.setText(res.getMobilenumber());
                         username.setText(res.getUsername());
                         fname.setText(res.getUsername());
                        pht1.setText(res.getMobilenumber());
                        // joindate.setText(res.getUserAccountNo());
                         email.setText(res.getEmail());


                         // to check profile pic null or not
                        if(ApplicationConstants.upic==null){
                            Utils.setCircleImageToImageView(getApplicationContext(), upict, R.drawable.profile1, 0, 0);
                            Utils.setCircleImageToImageView(getApplicationContext(), pimage, R.drawable.profile1, 0, 0);
                        }
                        else
                        {
                            byte[] decodedString= Base64.decode( ApplicationConstants.upic.substring( ApplicationConstants.upic.indexOf(",")+1), Base64.DEFAULT);
                            Bitmap image1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            pimage.setImageBitmap(image1);
                            upict.setImageBitmap(Utils.getCircularBitmapWithBorder(image1,0,0));
                        }
                        DrawerLayout drawer = findViewById(R.id.drawer_layout);
                        drawer.openDrawer(GravityCompat.START);
                    }
                });


    }

}
