package com.webingate.paysmartbusinessapp.driverapplication;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

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
import com.google.android.gms.location.places.Places;
import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AcceptRejectBookingResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsTableItem;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverLoginResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.TrackvehicleResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleDetailsTableItem;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
public class Home extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,TripRequest.Triprequest {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    double latitude = 0.0, longitude = 0.0;
    private static final int ACTIVEDRIVER = 1;
    private static final int DEACTIVEDRIVER = 2;
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
    private static GoogleApiClient mGoogleApiClient;
    private static final int ACCESS_FINE_LOCATION_INTENT_ID = 3;
    private static final String BROADCAST_ACTION = "android.location.PROVIDERS_CHANGED";

    @BindView(R.id.status)
    AppCompatButton status;
    @BindView(R.id.btn_confirmed_trips)
    AppCompatButton btnConfirmedTrips;
    @BindView(R.id.btn_statastics)
    AppCompatButton btnStatastics;
    @BindView(R.id.btn_mylocation)
    AppCompatButton btnMylocation;
    @BindView(R.id.btn_assigned_taxi)
    AppCompatButton btnAssignedTaxi;
    @BindView(R.id.btn_ongoing_trips)
    AppCompatButton btnOngoingTrips;
    @BindView(R.id.btn_sos)
    AppCompatButton btnSos;
    @BindView(R.id.btn_myprofile)
    AppCompatButton btnMyprofile;
    @BindView(R.id.btn_logout)
    AppCompatButton btnLogout;
    @BindView(R.id.btn_shop)
    AppCompatButton btnShop;
    @BindView(R.id.btn_towing)
    AppCompatButton btnTowing;
    @BindView(R.id.btn_hotel)
    AppCompatButton btnHotel;
    @BindView(R.id.btn_wallet)
    AppCompatButton btnWallet;
    Unbinder unbinder;
    private LocationRequest mLocationRequest;
    private String response;
    private String serverUrl;
    private Timer timer;
    private Handler handler;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public final static int REQUEST_CODE = 10101;
    private boolean isOnline=true;
  //  DriverCallingRequest driverCallingRequest;
    Toast toast;
    ProgressDialog dialog ;
    public Home() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(gpsLocationReceiver, new IntentFilter(BROADCAST_ACTION));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(gpsLocationReceiver, new IntentFilter(BROADCAST_ACTION));//Register broadcast receiver to check the status of GPS
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, Home.this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Unregister receiver on destroy
        if (gpsLocationReceiver != null)
            getActivity().unregisterReceiver(gpsLocationReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, v);
        dialog =  new ProgressDialog.Builder(getContext())
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        initGoogleAPIClient();//Init Google API Client
        checkPermissions();//Check Permission
        ApplicationConstants.mapflag = 0;
        handler = new Handler();
        status.setTextColor(Color.GREEN);
        return v;
    }
    @OnClick({R.id.status,R.id.btn_confirmed_trips,R.id.btn_statastics,R.id.btn_mylocation,R.id.btn_assigned_taxi,R.id.btn_ongoing_trips,R.id.btn_sos,R.id.btn_logout,R.id.btn_myprofile})
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
                checkPermissions();
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
            case R.id.btn_confirmed_trips:
                GetDriverTrips(ApplicationConstants.mobileNo);

               /* ApplicationConstants.tripflag = GETTRIPS;
                DriverCallingRequest driverCallingRequest = new DriverCallingRequest();
                driverCallingRequest.execute();*/

                break;
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
            case R.id.btn_ongoing_trips:
                GetDriverTrips(ApplicationConstants.mobileNo);

               /* ApplicationConstants.tripflag = GETTRIPS;
                driverCallingRequest = new DriverCallingRequest();
                driverCallingRequest.execute();*/

                break;
            case R.id.btn_sos:

                startActivity(new Intent(getActivity(), SosContacts.class));

                       /* Sos_Dialoguebox dialoguebox=new Sos_Dialoguebox(getActivity());
                        dialoguebox.setCanceledOnTouchOutside(false);
                        dialoguebox.show();*/
                break;
            case R.id.btn_logout:
                if (status.getText().toString().matches("Go Offline")) {
                    JsonObject object = new JsonObject();
                    object.addProperty("loginlogout", "0");
                    object.addProperty("DriverNo", ApplicationConstants.mobileNo);
                    object.addProperty("LoginLatitude", latitude + "");
                    object.addProperty("LoginLongitude", longitude + "");
                    object.addProperty("Reason", "");
                    DriverGoOnline(object, LOGOUT);
                } else {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();
                    editor.putString(Phone, null);
                    editor.putString(Name, null);
                    editor.commit();
                    getActivity().finish();
                }
                break;
            case R.id.btn_myprofile:
                GetDriverDetails(ApplicationConstants.driverId);

              /*  ApplicationConstants.tripflag = GETDRIVERDETAILS;
                driverCallingRequest = new DriverCallingRequest();
                driverCallingRequest.execute();*/

                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, Home.this);

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
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();

    }

    /* Check Location Permission for Marshmallow Devices */
    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.SYSTEM_ALERT_WINDOW)
                    != PackageManager.PERMISSION_GRANTED)
                checkDrawOverlayPermission();
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED)
                requestcallPermission();
            if (ContextCompat.checkSelfPermission(getActivity(),
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
        if (!Settings.canDrawOverlays(getActivity())) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getActivity().getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /*  Show Popup to access User Permission  */
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(getActivity(),
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
                            status.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);
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
                    Toast.makeText(getActivity(), "Location Permission denied.", Toast.LENGTH_SHORT).show();
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
   /* public void KeepTracking() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            if (ApplicationConstants.tripflag != 0) {
                                if (driverCallingRequest == null) {
                                    driverCallingRequest = new DriverCallingRequest();
                                    driverCallingRequest.execute();
                                } else if (driverCallingRequest.getStatus() == AsyncTask.Status.FINISHED) {
                                    Log.i("BookingStatus ", "" + ApplicationConstants.tripflag);
                                    driverCallingRequest = new DriverCallingRequest();
                                    // PerformBackgroundTask this class is the class that extends AsynchTask
                                    driverCallingRequest.execute();
                                }
                                Log.i("Request Status", "" + driverCallingRequest.getStatus());

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 5000);
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }







    public void DriverGoOnline(JsonObject jsonObject, final int flag){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .DriverLogin(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverLoginResponse>>() {
                    @Override
                    public void onCompleted() {
                      //  DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ","  "+ e.getLocalizedMessage());
                            DisplayToast("Unable to Login/Logoff");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DriverLoginResponse> responselist) {
                        DriverLoginResponse response=responselist.get(0);
                        if(flag==ACTIVEDRIVER){
                            if (response.getStatus().matches("1")) {
                                status.setTextColor(Color.RED);
                                status.setText("Go Offline");
                                ApplicationConstants.tripflag = CHECKBOOKINGS;
                                // ApplicationConstants.bookingList=new ArrayList();
                                isOnline=true;
                                TrackVehicle();
                               // KeepTracking();
                            } else {
                                DisplayToast("Unable to Login");
                            }
                        }else if(flag==DEACTIVEDRIVER) {
                            if (response.getStatus().matches("1")) {
                                status.setTextColor(Color.GREEN);
                                status.setText("Go Online");
                                isOnline=false;
                               // timer.cancel();
                               // ApplicationConstants.tripflag = 0;
                            } else {
                                DisplayToast("Unable to LogOff");
                            }
                        }else if(flag==LOGOUT){
                            if (response.getStatus().matches("1")) {
                                status.setTextColor(Color.GREEN);
                                status.setText("Go Online");
                                ApplicationConstants.tripflag = 0;
                                Log.i("Driver status", "loggedout");
                                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();
                                editor.putString(Phone, null);
                                editor.putString(Name, null);
                                editor.commit();
                                getActivity().finish();
                            }
                        }

                    }
                });
    }

    public void TrackVehicle(JsonObject jsonObject){

       // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getActivity()).getrestadapter()
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
//                            TripRequest cdd = new TripRequest(getActivity(),Home.this);
//                            cdd.setCanceledOnTouchOutside(false);
//                            cdd.show();
                        }

                    }
                });
    }

    public void AcceptRejectBooking(JsonObject jsonObject, final boolean isAccepted){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .AcceptRejectBooking(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<AcceptRejectBookingResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            TrackVehicle();
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<AcceptRejectBookingResponse> responselist) {
                        AcceptRejectBookingResponse response=responselist.get(0);
                        if(isAccepted){
                                ApplicationConstants.customerMobileNo = response.getCustomerPhoneNo();
                              //  ApplicationConstants.mapflag = 0;
                              //  timer.cancel();
                                startActivityForResult(new Intent(getActivity(), MyTrips.class), MY_TRIP_ACTIVITY);
                        }else {
                                TrackVehicle();
                        }
                    }
                });
    }

    public void GetDriverTrips( String driverNo){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .Getdrivertrips(driverNo,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetdriverTripsResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<GetdriverTripsResponse> trips) {
                        ApplicationConstants.confirmedTrips= trips;
                        startActivity(new Intent(getActivity(), ConfirmedTrips.class));
                        ApplicationConstants.tripflag = 0;
                    }
                });
    }

    public void GetDriverDetails(String did){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .GetDriverDetails(did)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DriverDetailsResponse>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                            StopDialogue();
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
                        startActivity(new Intent(getContext(), DriverDetails.class));


                    }
                });
    }

    public void GetVehicleDetails(String vid){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .GetVehicleDetails(vid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VehicleDetailsResponse>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                            StopDialogue();
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
                        startActivity(new Intent(getContext(), VehicleDetails.class));


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


  /*  class DriverCallingRequest extends AsyncTask<String, Void, String[]> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setTitle("Request in process");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                DriverRequest();
                return new String[]{"Success"};
            } catch (Exception e) {
                return new String[]{"error"};
            }
        }

        @Override
        protected void onPostExecute(String... result) {
            try {
                if (response.matches("")) {
                    Toast.makeText(getContext(), "An Error Has Occurred ", Toast.LENGTH_LONG).show();
                } else {
                    switch (ApplicationConstants.tripflag) {
                        case DEACTIVEDRIVER:
                            JSONArray jsonObj = new JSONArray(response);
                            JSONObject c = jsonObj.getJSONObject(0);
                            if (c.getString("status").matches("1")) {
                                Log.i("Driver status", "Driver is offline");
                                status.setTextColor(Color.GREEN);
                                status.setText("Go Online");
                                timer.cancel();
                                ApplicationConstants.tripflag = 0;
                            } else {
                                Toast.makeText(getContext(), c.getString("status"), Toast.LENGTH_LONG).show();
                            }
                            break;
                        case ACTIVEDRIVER:
                            jsonObj = new JSONArray(response);
                            c = jsonObj.getJSONObject(0);
                            if (c.has("Code")) {
                                Toast.makeText(getContext(), c.getString("description"), Toast.LENGTH_LONG).show();
                            } else if (c.getString("status").matches("1")) {
                                Log.i("Driver status", "Driver is online");
                                status.setTextColor(Color.RED);
                                status.setText("Go Offline");
                                ApplicationConstants.tripflag = CHECKBOOKINGS;
                                // ApplicationConstants.bookingList=new ArrayList();
                                KeepTracking();
                            } else {
                                Toast.makeText(getContext(), c.getString("status"), Toast.LENGTH_LONG).show();
                            }
                            break;
                        case CHECKBOOKINGS:
                            jsonObj = new JSONArray(response);
                            if (jsonObj.isNull(0)) {
                                Log.i("Driver status", "No Bookings Found");
                            } else {
                                c = jsonObj.getJSONObject(0);
                                ApplicationConstants.bookingId = c.getString("BookingId");
                                Log.i("Driver status", "Got Booking id" + ApplicationConstants.bookingId);
                                ApplicationConstants.bNo = c.getString("BNo");
                                ApplicationConstants.vid = c.getString("Vid");
                                ApplicationConstants.driverId = c.getString("DriverId");
                                ApplicationConstants.sourcelatitude = c.getString("SrcLatitude");
                                ApplicationConstants.sourcelongitude = c.getString("SrcLongitude");
                                ApplicationConstants.destlatitude = c.getString("DestLatitude");
                                ApplicationConstants.destlongitude = c.getString("DestLongitude");
                                ApplicationConstants.tripflag = 0;
                                TripRequest cdd = new TripRequest(getActivity());
                                cdd.setCanceledOnTouchOutside(false);
                                cdd.show();
                            }
                            // Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            break;
                        case ACCEPTBOOKING:
                            jsonObj = new JSONArray(response);
                            c = jsonObj.getJSONObject(0);
                            Log.i("Driver status", "Booking accepted" + ApplicationConstants.bookingId);
                            ApplicationConstants.customerMobileNo = c.getString("CustomerPhoneNo");
                            //saving booking details into list
                            *//*BookingModel bookingModel=new BookingModel();
                            bookingModel.setBookingId(ApplicationConstants.bookingId);
                            bookingModel.setbNo(ApplicationConstants.bNo);
                            bookingModel.setSourcelatitude(ApplicationConstants.sourcelatitude);
                            bookingModel.setSourcelongitude(ApplicationConstants.sourcelongitude);
                            bookingModel.setDestlatitude(ApplicationConstants.destlatitude);
                            bookingModel.setDestlongitude(ApplicationConstants.destlongitude);
                            bookingModel.setCustomerMobileNo(ApplicationConstants.customerMobileNo);
                            ApplicationConstants.bookingList.add(bookingModel);*//*
                            ApplicationConstants.mapflag = 0;
                            timer.cancel();
                            startActivityForResult(new Intent(getActivity(), MyTrips.class), MY_TRIP_ACTIVITY);
                            break;
                        case REJECTBOOKING:
                            Log.i("Driver status", "Booking rejected" + ApplicationConstants.bookingId);
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            ApplicationConstants.tripflag = CHECKBOOKINGS;
                            break;
                        //logout process
                        case LOGOUT:
                            jsonObj = new JSONArray(response);
                            c = jsonObj.getJSONObject(0);
                            if (c.getString("status").matches("1")) {
                                status.setTextColor(Color.GREEN);
                                status.setText("Go Online");
                                ApplicationConstants.tripflag = 0;
                                Log.i("Driver status", "loggedout");
                                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();
                                editor.putString(Phone, null);
                                editor.putString(Name, null);
                                editor.commit();
                                getActivity().finish();
                            }
                            break;
                        case GETTRIPS:
                            Log.i("Driver status", "Got trips");
                            jsonObj = new JSONArray(response);
                            ApplicationConstants.confirmedTrips = new ArrayList();
                            for (int i = 0; i < jsonObj.length(); i++) {
                                c = jsonObj.getJSONObject(i);
                                TripModel tripModel = new TripModel();
                                tripModel.setId(c.getString("BNo"));
                                tripModel.setSource(c.getString("Src"));
                                tripModel.setDestination(c.getString("Dest"));
                                tripModel.setDate(c.getString("BookedDate"));
                                tripModel.setAmount(c.getString("Amount"));
                                ApplicationConstants.confirmedTrips.add(tripModel);

                            }
                            startActivity(new Intent(getActivity(), ConfirmedTrips.class));
                            ApplicationConstants.tripflag = 0;
                            break;

                        case GETDRIVERDETAILS:
                            Log.i("Driver status", "got driver Details");
                            // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Table");
                            c = jsonArray.getJSONObject(0);
                            ApplicationConstants.username = c.getString("NAme");
                            ApplicationConstants.address = c.getString("Address");
                            ApplicationConstants.mobileNo = c.getString("PMobNo");
                            ApplicationConstants.profilepic = c.getString("Photo");
                            jsonArray = jsonObject.getJSONArray("Table1");
                            ApplicationConstants.documentslist = new ArrayList();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                c = jsonArray.getJSONObject(i);
                                DocumentsModel documentsModel = new DocumentsModel();
                                documentsModel.setId(c.getString("DocTypeId"));
                                documentsModel.setName(c.getString("docName"));
                                documentsModel.setExpireDate(c.getString("expiryDate"));
                                documentsModel.setNumber(c.getString("DocumentNo"));
                                // documentsModel.setIsuploaded(c.getString("IsExpired"));
                                ApplicationConstants.documentslist.add(documentsModel);
                            }
                            startActivity(new Intent(getActivity(), DriverDetails.class));
                            break;
                        case GETVEHICLEDETAILS:
                            Log.i("Driver status", "got vehicle Details");
                            jsonObject = new JSONObject(response);
                            // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("Table");
                            c = jsonArray.getJSONObject(0);
                            ApplicationConstants.registrationNo = c.getString("RegistrationNo");
                            ApplicationConstants.vehicleType = c.getString("VehicleType");
                            ApplicationConstants.vehiclepic = c.getString("Photo");
                            jsonArray = jsonObject.getJSONArray("Table1");
                            ApplicationConstants.documentslist = new ArrayList();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                c = jsonArray.getJSONObject(i);
                                DocumentsModel documentsModel = new DocumentsModel();
                                documentsModel.setId(c.getString("DocTypeId"));
                                documentsModel.setName(c.getString("docName"));
                                documentsModel.setExpireDate(c.getString("expiryDate"));
                                documentsModel.setNumber(c.getString("DocumentNo"));
                                // documentsModel.setIsuploaded(c.getString("IsExpired"));
                                ApplicationConstants.documentslist.add(documentsModel);
                            }
                            startActivity(new Intent(getActivity(), VehicleDetails.class));
                            break;

                    }
                    //    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void DriverRequest() {
        BufferedReader reader = null;
        response = "";
        URL url = null;
        JSONObject object = null;
        try {
            switch (ApplicationConstants.tripflag) {
                case DEACTIVEDRIVER:
                    Log.i("Driver status", "Driver go offline");
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_driver_goonline));
                    object = new JSONObject();
                    object.put("loginlogout", "0");
                    object.put("DriverNo", ApplicationConstants.mobileNo);
                    object.put("LoginLatitude", latitude + "");
                    object.put("LoginLongitude", longitude + "");
                    object.put("Reason", "");
                    //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
                    // Defined URL  where to send data
                    break;
                case ACTIVEDRIVER:
                    Log.i("Driver status", "Driver go online " + latitude + " " + longitude);
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_driver_goonline));
                    object = new JSONObject();
                    object.put("loginlogout", "1");
                    object.put("DriverNo", ApplicationConstants.mobileNo);
                    object.put("LoginLatitude", latitude + "");
                    object.put("LoginLongitude", longitude + "");
                    object.put("Reason", "");
                    //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
                    // Defined URL  where to send data
                    break;
                case CHECKBOOKINGS:
                    Log.i("Driver status", "Checking Bookings");
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_trackvehicle));
                    object = new JSONObject();
                    object.put("PMobNo", ApplicationConstants.mobileNo);
                    object.put("latitude", latitude + "");
                    object.put("longitude", longitude + "");
                    break;
                case ACCEPTBOOKING:
                    Log.i("Driver status", "accepting booking");
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_vehicle_booking));
                    object = new JSONObject();
                    object.put("BookingId", ApplicationConstants.bookingId);
                    object.put("BookingStatus", "accepted");
                    object.put("VID", ApplicationConstants.vid);
                    object.put("DriverId", ApplicationConstants.driverId);
                    object.put("Reasons", "");
                    break;
                case REJECTBOOKING:
                    Log.i("Driver status", "rejecting booking");
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_vehicle_booking));
                    object = new JSONObject();
                    object.put("BookingId", ApplicationConstants.bookingId);
                    object.put("BookingStatus", "rejected");
                    object.put("VID", ApplicationConstants.vid);
                    object.put("DriverId", ApplicationConstants.driverId);
                    object.put("Reasons", "");
                    break;
                case LOGOUT:
                    Log.i("Driver status", "calling logout");
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_driver_goonline));
                    object = new JSONObject();
                    object.put("loginlogout", "0");
                    object.put("DriverNo", ApplicationConstants.mobileNo);
                    object.put("LoginLatitude", latitude + "");
                    object.put("LoginLongitude", longitude + "");
                    object.put("Reason", "");
                    //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
                    // Defined URL  where to send data
                    break;
                case GETTRIPS:
                    Log.i("Driver status", "getting trips");
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_getdrivertrips) + ApplicationConstants.mobileNo);
                    break;
                case GETDRIVERDETAILS:
                    Log.i("Driver status", "getting driver details");
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_getdriver_details) + ApplicationConstants.driverId);
                    break;
                case GETVEHICLEDETAILS:
                    Log.i("Driver status", "getting vehicle details");
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_getvehicle_details) + ApplicationConstants.vid);
                    break;

                // Send POST data request
            }
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Host", "android.schoolportal.gr");
            if (ApplicationConstants.tripflag >= GETTRIPS) {
                conn.connect();
            } else {
                conn.setDoOutput(true);
                conn.connect();
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(object.toString());
                wr.flush();
            }
            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server DriverDetailsResponse
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }
            response = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // response = response + e.getMessage();
            //e.printStackTrace();
        } catch (IOException e) {
            //  response = response + e.getMessage();
        } catch (JSONException e) {
            //  e.printStackTrace();
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

    }*/


    private void requestcallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    ACCESS_FINE_LOCATION_INTENT_ID);
        }
    }
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(getContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }
    public void StartDialogue(){
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    public void StopDialogue(){
        if(dialog.isShowing()){
            dialog.cancel();
        }

    }
}
