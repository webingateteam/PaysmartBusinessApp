package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.ConfirmedTrips;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AcceptRejectBookingResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsTableItem;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverLoginResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.TrackvehicleResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleDetailsTableItem;
import com.webingate.paysmartbusinessapp.driverapplication.DriverDetails;
import com.webingate.paysmartbusinessapp.driverapplication.Home;
import com.webingate.paysmartbusinessapp.driverapplication.MyTrips;
import com.webingate.paysmartbusinessapp.driverapplication.TripRequest;
import com.webingate.paysmartbusinessapp.driverapplication.VehicleDetails;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDashboardFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverDashboardFragment;
import com.webingate.paysmartbusinessapp.utils.Utils;

import com.google.android.gms.location.places.Places;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappDriverDashboardActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,TripRequest.Triprequest{
    @BindView(R.id.status)
    AppCompatButton status;

    private boolean isOnline=false;
    Toast toast;
    private static final int ACTIVEDRIVER = 1;
    private static final int DEACTIVEDRIVER = 2;
    private static GoogleApiClient mGoogleApiClient;
    Unbinder unbinder;
    private LocationRequest mLocationRequest;
    private String response;
    private String serverUrl;
    private Timer timer;
    double latitude = 17.456455, longitude = 78.412086;
    public final static int REQUEST_CODE = 10101;
    private static final int CHECKBOOKINGS = 3;
    private static final int ACCEPTBOOKING = 4;
    private static final int REJECTBOOKING = 5;
    private static final int RATETHERIDE = 6;
    private static final int LOGOUT = 7;
    private static final int GETTRIPS = 8;
    private static final int GETDRIVERDETAILS = 9;
    private static final int GETVEHICLEDETAILS = 10;
    private static final int MY_TRIP_ACTIVITY = 1;
    private static final int TIMEINTERVAL = 5;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private static final int ACCESS_FINE_LOCATION_INTENT_ID = 3;
    private static final String BROADCAST_ACTION = "android.location.PROVIDERS_CHANGED";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_driverdashboard_activity);

        initData();

        initUI();

        initDataBinding();

        initAction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
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
        initToolbar();
        ButterKnife.bind(this);

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

        //goonlineBtn = findViewById(R.id.goonlineBtn);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
//                case R.id.searchMenu:
//                    loadFragment(new businessAppDashboardFragment());
//                    break;
//                case R.id.listMenu:
//                    loadFragment(new AppDirectoryHome2Fragment());
//                    break;
//                case R.id.historyMenu:
//                    loadFragment(new AppDirectoryHome3Fragment());
//                    break;
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

        loadFragment(new businessAppDriverDashboardFragment());

    }

    private void initDataBinding() {
    }

    private void initAction() {

//        goonlineBtn.setOnClickListener((View v) ->{
//            //Toast.makeText(getApplicationContext(),"OTP is Resent.",Toast.LENGTH_SHORT).show();
//            //Intent intent = new Intent(this, login_activity.class);
////            Intent intent = new Intent(this, customerappPaymentModeActivity.class);
////            startActivity(intent);
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("Mobilenumber", M_numbers);
//            jsonObject.addProperty("userId", M_uid);
//            jsonObject.addProperty("MVerificationCode", M_password.getText().toString());
//            DriverGoOnline(jsonObject);
//        });
    }
   // R.id.btn_confirmed_trips,

   @Nullable
   @OnClick({R.id.status})
//    @OnClick({R.id.status,R.id.btn_statastics,R.id.btn_mylocation,R.id.btn_assigned_taxi,R.id.btn_ongoing_trips,R.id.btn_sos,R.id.btn_logout,R.id.btn_myprofile})
    void OnClick(View v){
        switch (v.getId()) {
            case R.id.status:
                if (isOnline) {
                    checkPermissions();
                    JsonObject object=new JsonObject();
                    object.addProperty("loginlogout", "0");
                    object.addProperty("DriverNo", ApplicationConstants.mobileNo);
                    object.addProperty("LoginLatitude", latitude + "");
                    object.addProperty("LoginLongitude", longitude + "");
                    object.addProperty("Reason", "");
                    DriverGoOnline(object,DEACTIVEDRIVER);
                }else {
                    //checkPermissions();
                    Log.i("Driver status", "Driver go offline");
                    JsonObject object=new JsonObject();
                    object.addProperty("loginlogout", "1");
                    object.addProperty("DriverNo", ApplicationConstants.mobileNo);
                    object.addProperty("LoginLatitude", latitude + "");
                    object.addProperty("LoginLongitude", longitude + "");
                    object.addProperty("Reason", "");
                    DriverGoOnline(object,ACTIVEDRIVER);
                }

                break;
//            case R.id.btn_confirmed_trips:
//                Toast.makeText(getApplicationContext(), "Clicked Btn clickec ", Toast.LENGTH_SHORT).show();
//                //GetDriverTrips(ApplicationConstants.mobileNo);
//
//               /* ApplicationConstants.tripflag = GETTRIPS;
//                DriverCallingRequest driverCallingRequest = new DriverCallingRequest();
//                driverCallingRequest.execute();*/
//
//                break;
            case R.id.btn_statastics:

                break;
            case R.id.btn_mylocation:
                ApplicationConstants.mapflag = 1;
                // startActivity(new Intent(getActivity(), MyTrips.class));
                break;
            case R.id.btn_assigned_taxi:
                GetVehicleDetails(ApplicationConstants.vid);

                /*ApplicationConstants.tripflag = GETVEHICLEDETAILS;
                driverCallingRequest = new DriverCallingRequest();
                driverCallingRequest.execute();*/

                break;
//            case R.id.trip:
//
//                break;
            case R.id.btn_ongoing_trips:
                GetDriverTrips(ApplicationConstants.mobileNo);

               /* ApplicationConstants.tripflag = GETTRIPS;
                driverCallingRequest = new DriverCallingRequest();
                driverCallingRequest.execute();*/

                break;
//            case R.id.btn_sos:
//
//                startActivity(new Intent(getActivity(), SosContacts.class));
//
//                       /* Sos_Dialoguebox dialoguebox=new Sos_Dialoguebox(getActivity());
//                        dialoguebox.setCanceledOnTouchOutside(false);
//                        dialoguebox.show();*/
//                break;
//            case R.id.btn_logout:
//                if (status.getText().toString().matches("Go Offline")) {
//                    JsonObject object = new JsonObject();
//                    object.addProperty("loginlogout", "0");
//                    object.addProperty("DriverNo", ApplicationConstants.mobileNo);
//                    object.addProperty("LoginLatitude", latitude + "");
//                    object.addProperty("LoginLongitude", longitude + "");
//                    object.addProperty("Reason", "");
//                    DriverGoOnline(object, LOGOUT);
//                } else {
//                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();
//                    editor.putString(Phone, null);
//                    editor.putString(Name, null);
//                    editor.commit();
//                    getActivity().finish();
//                }
//                break;
//            case R.id.btn_myprofile:
//                GetDriverDetails(ApplicationConstants.driverId);
//
//              /*  ApplicationConstants.tripflag = GETDRIVERDETAILS;
//                driverCallingRequest = new DriverCallingRequest();
//                driverCallingRequest.execute();*/
//
//                break;
        }
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Driver dashboard");

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
    public void DriverGoOnline(JsonObject jsonObject, final int flag){

     //   StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .DriverLogin(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverLoginResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                     //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ","  "+ e.getLocalizedMessage());
                           // DisplayToast("Unable to Login/Logoff");
                         //   StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<DriverLoginResponse> responselist) {
                        DriverLoginResponse response = responselist.get(0);
                        if (response.getCode() != null) {
                            DisplayToast(response.getdescription());
                        } else {
                            int greenColorValue = Color.parseColor("#00ff00");
                            if (flag == ACTIVEDRIVER) {
                                if (response.getStatus().matches("1")) {
                                    status.setTextColor(Color.RED);
                                    status.setBackgroundColor(Color.green(greenColorValue));
                                    status.setText("Go Offline");
                                    ApplicationConstants.tripflag = CHECKBOOKINGS;
                                    // ApplicationConstants.bookingList=new ArrayList();
                                    isOnline = true;
                                    TrackVehicle();
                                    // KeepTracking();
                                } else {
                                    DisplayToast("Unable to Login");
                                }
                            } else if (flag == DEACTIVEDRIVER) {
                                if (response.getStatus().matches("1")) {
                                    status.setTextColor(Color.GREEN);
                                    status.setText("Go Online");
                                    isOnline = false;
                                    // timer.cancel();
                                    // ApplicationConstants.tripflag = 0;
                                } else {
                                    DisplayToast("Unable to LogOff");
                                }
                            } else if (flag == LOGOUT) {
                                if (response.getStatus().matches("1")) {
                                    status.setTextColor(Color.GREEN);
                                    status.setText("Go Online");
                                    ApplicationConstants.tripflag = 0;
                                    Log.i("Driver status", "loggedout");

                                    finish();
                                }
                            }

                        }
                    }
                });
    }

    public void TrackVehicle(JsonObject jsonObject){

        // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .TrackVehicle(jsonObject).delay(TIMEINTERVAL, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<TrackvehicleResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            TrackVehicle();
                            Log.d("OnError ", e.getMessage());
                            //    DisplayToast("Unable to Register");
                            //     StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<TrackvehicleResponse> response) {
                        if(response.get(0).getCode()!=null){
                            TrackVehicle();
                            Log.i("Driver status", response.get(0).getDescription());
                        }else{
                            TrackvehicleResponse c=response.get(0);
                            ApplicationConstants.bookingId =c.getBookingId();
                            ApplicationConstants.bNo = c.getBNo();
                            ApplicationConstants.vid = c.getVid();
                            ApplicationConstants.driverId = c.getDriverId();
                            ApplicationConstants.sourcelatitude = c.getSrcLatitude();
                            ApplicationConstants.sourcelongitude = c.getSrcLongitude();
                            ApplicationConstants.destlatitude = c.getDestLatitude();
                            ApplicationConstants.destlongitude = c.getDestLongitude();
                            ApplicationConstants.tripflag = 0;
                            TripRequest cdd = new TripRequest(businessappDriverDashboardActivity.this);
                            cdd.setCanceledOnTouchOutside(false);
                            cdd.show();
                        }

                    }
                });
    }

    public void AcceptRejectBooking(JsonObject jsonObject, final boolean isAccepted){

       // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .AcceptRejectBooking(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<AcceptRejectBookingResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                       // StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            TrackVehicle();
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                          //  StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<AcceptRejectBookingResponse> responselist) {
                        AcceptRejectBookingResponse response=responselist.get(0);
                        if(isAccepted){
                            ApplicationConstants.customerMobileNo = response.getCustomerPhoneNo();
                              ApplicationConstants.mapflag = 0;
                           // timer.cancel();
                            startActivityForResult(new Intent(businessappDriverDashboardActivity.this, MyTrips.class), MY_TRIP_ACTIVITY);
                        }else {
                            TrackVehicle();
                        }
                    }
                });
    }



    public void GetDriverTrips( String driverNo){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .Getdrivertrips(driverNo,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetdriverTripsResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                     //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
//                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<GetdriverTripsResponse> trips) {
                        ApplicationConstants.confirmedTrips= trips;
                  //     startActivity(new Intent(this, ConfirmedTrips.class));
                        ApplicationConstants.tripflag = 0;
                    }
                });
    }

    public void GetDriverDetails(String did){

//        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetDriverDetails(did)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DriverDetailsResponse>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Registered");
                      //  StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(DriverDetailsResponse response) {
                        List<DriverDetailsTableItem> tableItems= response.getTable();
                        DriverDetailsTableItem tableItem= tableItems.get(0);
                        ApplicationConstants.username = tableItem.getNAme();
                        ApplicationConstants.address = tableItem.getAddress();
                        ApplicationConstants.mobileNo = tableItem.getPMobNo();
                        ApplicationConstants.profilepic = tableItem.getPhoto();
                        ApplicationConstants.documentslist = response.getTable1();
                        startActivity(new Intent(getApplicationContext(), DriverDetails.class));


                    }
                });
    }

    public void GetVehicleDetails(String vid){

       // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetVehicleDetails(vid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VehicleDetailsResponse>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Registered");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(VehicleDetailsResponse response) {
                        List<VehicleDetailsTableItem> tableItems= response.getTable();
                        VehicleDetailsTableItem tableItem= tableItems.get(0);
                        ApplicationConstants.registrationNo =tableItem.getRegistrationNo();
                        ApplicationConstants.vehicleType = tableItem.getVehicleType();
                        ApplicationConstants.vehiclepic = tableItem.getPhoto();
                        ApplicationConstants.documentslist = response.getTable1();
                        startActivity(new Intent(getApplicationContext(), VehicleDetails.class));


                    }
                });
    }

    @Override
    public void TripAccepted() {
        Log.i("Driver status", "accepting booking");
        JsonObject object = new JsonObject();
        object.addProperty("BookingId", ApplicationConstants.bookingId);
        object.addProperty("BookingStatus", "accepted");
        object.addProperty("VID", ApplicationConstants.vid);
        object.addProperty("DriverId", ApplicationConstants.driverId);
        object.addProperty("Reasons", "");
        AcceptRejectBooking(object,true);
    }

    @Override
    public void TripRejected() {
        Log.i("Driver status", "accepting booking");
        JsonObject object = new JsonObject();
        object.addProperty("BookingId", ApplicationConstants.bookingId);
        object.addProperty("BookingStatus", "accepted");
        object.addProperty("VID", ApplicationConstants.vid);
        object.addProperty("DriverId", ApplicationConstants.driverId);
        object.addProperty("Reasons", "");
        AcceptRejectBooking(object,false);
    }

    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast= Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }

//    public void StartDialogue(){
//
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//    }
//    public void StopDialogue(){
//        if(dialog.isShowing()){
//            dialog.cancel();
//        }
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.SYSTEM_ALERT_WINDOW)
                    != PackageManager.PERMISSION_GRANTED)
                checkDrawOverlayPermission();
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED)
                requestcallPermission();
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
                requestLocationPermission();
            else
                showSettingDialog();
        } else
            showSettingDialog();

    }

    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + this.getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /*  Show Popup to access User Permission  */
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);
        }
    }

    /* Show Location Access Dialog */
    private void showSettingDialog() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);//Setting priotity of Location request to high
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);//5 sec Time interval for location update
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient to show dialog always when GPS is off

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        // updateGPSStatus("GPS is Enabled in your device");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(businessappDriverDashboardActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case MY_TRIP_ACTIVITY:
                ApplicationConstants.tripflag = CHECKBOOKINGS;
                TrackVehicle();
                //  KeepTracking();
                break;

        }
    }


    //Run on UI
    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            showSettingDialog();
        }
    };
    /* Broadcast receiver to check status of GPS */
    private BroadcastReceiver gpsLocationReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            //If Action is Location
            if (intent.getAction().matches(BROADCAST_ACTION)) {
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                //Check if GPS is turned ON or OFF
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Log.e("About GPS", "GPS is Enabled in your device");
                    // updateGPSStatus("GPS is Enabled in your device");
                } else {
                    //If GPS turned OFF show Location Dialog
                    new Handler().postDelayed(sendUpdatesToUI, 10);
                    // showSettingDialog();
                    //updateGPSStatus("GPS is Disabled in your device");
                    Log.e("About GPS", "GPS is Disabled in your device");
                }

            }
        }
    };

    /* On Request permission method to check the permisison is granted or not for Marshmallow+ Devices  */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case ACCESS_FINE_LOCATION_INTENT_ID: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //If permission granted show location dialog if APIClient is not null
                    if (mGoogleApiClient == null) {
                        initGoogleAPIClient();
                        showSettingDialog();
                    } else
                        showSettingDialog();


                } else {
                    //  updateGPSStatus("Location Permission denied.");
                    Toast.makeText(this, "Location Permission denied.", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    public void TrackVehicle(){
        if(isOnline) {
            JsonObject object = new JsonObject();
            object.addProperty("PMobNo", ApplicationConstants.mobileNo);
            object.addProperty("latitude", latitude + "");
            object.addProperty("longitude", longitude + "");
            TrackVehicle(object);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, businessappDriverDashboardActivity.this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        // Toast.makeText(getContext(), latitude + "  " + longitude, Toast.LENGTH_SHORT).show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    /* Initiate Google API Client  */
    private void initGoogleAPIClient() {
        //Without Google API Client Auto Location Dialog will not work
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();

    }

    private void requestcallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    ACCESS_FINE_LOCATION_INTENT_ID);
        }
    }

}
