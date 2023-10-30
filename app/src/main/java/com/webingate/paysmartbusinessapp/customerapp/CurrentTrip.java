package com.webingate.paysmartbusinessapp.customerapp;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
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
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.JsonObject;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.SphericalUtil;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import com.webingate.paysmartbusinessapp.businessapp.Deo.CustomerBookingStatusResponse;
import com.webingate.paysmartbusinessapp.businessapp.Deo.CustomerRateTheRideResponse;
import com.webingate.paysmartbusinessapp.businessapp.Deo.MakepaymentResponse;
import com.webingate.paysmartbusinessapp.businessapp.Deo.UpdateBookingstatusResponse;
import com.webingate.paysmartbusinessapp.businessapp.Deo.VehiclePositionResponse;


import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

import static com.google.android.gms.location.LocationRequest.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CurrentTrip extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,RatingBarDialogue.Ratingfinished {
    String serverUrl = "", otp = "";
    static GoogleMap mMap;
    static Marker marker, markerDriver;
    private static final int GETDRIVELLOCATION = 1;
    private static final int PAYMENTS = 2;
    private static final int GETFEEDBACK = 3;
    private static final int CANCELBOOKING = 4;
    double latitude = 0.0, longitude = 0.0, driverLatitude, driverLongitude;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private static GoogleApiClient mGoogleApiClient;
    private static final int ACCESS_FINE_LOCATION_INTENT_ID = 3;
    private static final String BROADCAST_ACTION = "android.location.PROVIDERS_CHANGED";
    DirectionsResult result;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drivertimedistance)
    TextView driverTimeDistance;
    @BindView(R.id.driverimage)
    ImageView driverimage;
    @BindView(R.id.driverdetails)
    TextView driverDetails;
    @BindView(R.id.vehicleimage)
    ImageView vehicleImage;
    @BindView(R.id.cancel_ride)
    AppCompatButton cancelRide;
    @BindView(R.id.call)
    AppCompatButton callCustomer;
    private LocationRequest mLocationRequest;
    private LatLng latLng, latlngnew;
    private String response;
    private Polyline line;
    private Timer timer;
    Handler handler = new Handler();
   // TripsRequest tripsRequest;
    public static int tripFlag = 0;
    private int updatemarkers = 0;
    Toast toast;
    ProgressDialog dialog ;
    CustomerBookingStatusResponse bookingDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_trip);
        ButterKnife.bind(this);
        bookingDetails= (CustomerBookingStatusResponse) getIntent().getSerializableExtra("details");
        dialog =  new ProgressDialog.Builder(CurrentTrip.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        initGoogleAPIClient();//Init Google API Client
        checkPermissions();//Check Permission
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tripFlag = 0;
        latitude = getIntent().getDoubleExtra("lat", 0.0);
        longitude = getIntent().getDoubleExtra("lon", 0.0);
        latLng = new LatLng(latitude, longitude);


        //  byte[] decodedString= Base64.decode(ApplicationConstants.driverimage.substring(ApplicationConstants.driverimage.indexOf(",")+1), Base64.DEFAULT);
        // Bitmap image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        // driverimage.setImageBitmap(image);
        // decodedString= Base64.decode(ApplicationConstants.vehicleimage.substring(ApplicationConstants.vehicleimage.indexOf(",")+1), Base64.DEFAULT);
        // image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        //  vehicleImage.setImageBitmap(image);
        driverDetails.setText("Booking OTP : " + bookingDetails.getBooKingOTP() + "\n" + "Name : " + bookingDetails.getName() + "\n" + "VehicleNo : " + bookingDetails.getRegistrationNo() + "\n" + "Vehicle Model : " + bookingDetails.getVModel());
        cancelRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CurrentTrip.this, R.style.Theme_AppCompat_DayNight_Dialog);
                alertDialog.setTitle("Cancel Ride");
                alertDialog.setMessage("Are You Sure You want to Cancel Ride");
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //   tripFlag=CANCELBOOKING;
                                JsonObject object=new JsonObject();
                                object.addProperty("BNo", ApplicationConstants.bookingNo);
                                object.addProperty("BookingStatus", "Cancelled");
                                object.addProperty("UpdatedBy", "1");
                                object.addProperty("UpdatedUserId", "21");
                              //  UpdateBookingStatus(object);
                            }
                        });
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                alertDialog.show();

            }


        });

        callCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + bookingDetails.getPMobNo()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                getApplicationContext().startActivity(intent);
            }
        });
        VehiclePosition();
    }

    //toolbar button click
    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        return true;
    }

    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3)
                .setApiKey(getString(R.string.google_maps_key))
                .setConnectTimeout(10, TimeUnit.SECONDS)
                .setReadTimeout(10, TimeUnit.SECONDS)
                .setWriteTimeout(10, TimeUnit.SECONDS);
    }

    // this method will provide distance and time between two places
    private String getEndLocationTitle() {
        return "EstimatedTime :" + result.routes[0].legs[0].duration.humanReadable + " Distance :" + result.routes[0].legs[0].distance.humanReadable;
    }

    @Override
    public void Rating(String rating, String comments) {
        JsonObject object = new JsonObject();
        object.addProperty("CustomerPhoneNo", ApplicationConstants.mobileNo);
        object.addProperty("BNo", ApplicationConstants.bookingNo);
        object.addProperty("Rating", rating);
        object.addProperty("RatedBy", "2");
        object.addProperty("Comments", comments);
        RateTheRide(object);
    }

    // This task will provide directions and path
    private class DirectionsTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                DateTime now = new DateTime();
                // Fetching the data from web service
                Log.i("Directions", "Waiting For directions");
                result = DirectionsApi.newRequest(getGeoContext())
                        .mode(TravelMode.DRIVING).origin(new com.google.maps.model.LatLng(driverLatitude, driverLongitude))
                        .destination(new com.google.maps.model.LatLng(latitude, longitude)).departureTime(now).await();
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);
            Log.i("Directions", "Got directions");
            if (result != null) {
                if (updatemarkers > 0 && updatemarkers < 3) {
                    MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(result.routes[0]
                            .legs[0].startLocation.lat, result.routes[0]
                            .legs[0].startLocation.lng))
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_car))
                            .title("Driver :" + result.routes[0].legs[0].startAddress);
                    if (markerDriver != null)
                        markerDriver.remove();
                    markerDriver = mMap.addMarker(markerOptions);
                    markerOptions = new MarkerOptions().position(new LatLng(result.routes[0]
                            .legs[0].endLocation.lat, result.routes[0]
                            .legs[0].endLocation.lng))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                            .title("My Location :" + result.routes[0].legs[0].endAddress);
                    if (marker != null)
                        marker.remove();
                    marker = mMap.addMarker(markerOptions);
                }
                List<LatLng> decodedPath = PolyUtil.decode(result.routes[0].overviewPolyline.getEncodedPath());
                PolylineOptions polylineOptions = new PolylineOptions().addAll(decodedPath);
                if (line != null)
                    line.remove();
                line = mMap.addPolyline(polylineOptions);
                driverTimeDistance.setText(getEndLocationTitle());
            }
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //   mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

            }
        } else {

        }
        mMap.setMyLocationEnabled(true);
        // mMap.getUiSettings().setZoomControlsEnabled(true);
        // mMap.getUiSettings().setZoomGesturesEnabled(true);


        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        marker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16.5f));
       /* float rotation = (float) SphericalUtil.computeHeading(latLng, latlngnew);
        rotateMarker(marker, latlngnew, rotation);
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlngnew));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16.5f));*/


    }

    /* Initiate Google API Client  */
    private void initGoogleAPIClient() {
        //Without Google API Client Auto Location Dialog will not work
        mGoogleApiClient = new GoogleApiClient.Builder(CurrentTrip.this)
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
            if (ContextCompat.checkSelfPermission(CurrentTrip.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
                requestLocationPermission();
            else
                showSettingDialog();
        } else
            showSettingDialog();

    }

    /*  Show Popup to access User Permission  */
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(CurrentTrip.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(CurrentTrip.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(CurrentTrip.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);
        }
    }

    /* Show Location Access Dialog */
    private void showSettingDialog() {
        LocationRequest locationRequest = create();
        locationRequest.setPriority(PRIORITY_HIGH_ACCURACY);//Setting priotity of Location request to high
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
                        updateGPSStatus("GPS is Enabled in your device");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(CurrentTrip.this, REQUEST_CHECK_SETTINGS);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case RESULT_OK:

                        //startLocationUpdates();
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(gpsLocationReceiver, new IntentFilter(BROADCAST_ACTION));//Register broadcast receiver to check the status of GPS
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Unregister receiver on destroy
        if (gpsLocationReceiver != null)
            unregisterReceiver(gpsLocationReceiver);
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
                    updateGPSStatus("GPS is Enabled in your device");
                } else {
                    //If GPS turned OFF show Location Dialog
                    new Handler().postDelayed(sendUpdatesToUI, 10);
                    // showSettingDialog();
                    updateGPSStatus("GPS is Disabled in your device");
                    Log.e("About GPS", "GPS is Disabled in your device");
                }

            }
        }
    };

    //Method to update GPS status text
    private void updateGPSStatus(String status) {
        //  GetLocationTask getLocationTask=new GetLocationTask();
        //  getLocationTask.execute();
        //  handler.postDelayed(updateMarker,2000);
        //  updateMyLocation(toLatLng(oldLocation), toLatLng(mCurrentLocation));
    }


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
                    updateGPSStatus("Location Permission denied.");
                    Toast.makeText(CurrentTrip.this, "Location Permission denied.", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000 * 30);
        mLocationRequest.setFastestInterval(1000 * 5);
        mLocationRequest.setPriority(PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18.5f));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        /*latlngnew = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlngnew);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        if (marker != null)
            marker.remove();
        marker = mMap.addMarker(markerOptions);
        if (latitude == 0.0 || longitude == 0.0) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlngnew));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(16.5f));
        }
        latitude = location.getLatitude();
        longitude = location.getLongitude();*/


    }


    private void rotateMarker(final Marker marker, final LatLng destination, final float rotation) {

        if (marker != null) {

            final LatLng startPosition = marker.getPosition();
            final float startRotation = marker.getRotation();

            final LatLngInterpolator latLngInterpolator = new LatLngInterpolator.Spherical();
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
            valueAnimator.setDuration(3000); // duration 3 second
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    try {
                        float v = animation.getAnimatedFraction();
                        LatLng newPosition = latLngInterpolator.interpolate(v, startPosition, destination);
                        float bearing = computeRotation(v, startRotation, rotation);

                        marker.setRotation(bearing);
                        marker.setPosition(newPosition);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            valueAnimator.start();
        }
    }

    private static float computeRotation(float fraction, float start, float end) {
        float normalizeEnd = end - start; // rotate start to 0
        float normalizedEndAbs = (normalizeEnd + 360) % 360;

        float direction = (normalizedEndAbs > 180) ? -1 : 1; // -1 = anticlockwise, 1 = clockwise
        float rotation;
        if (direction > 0) {
            rotation = normalizedEndAbs;
        } else {
            rotation = normalizedEndAbs - 360;
        }

        float result = fraction * rotation + start;
        return (result + 360) % 360;
    }

    private String GetAddress(double latitude, double longitude) {
        String address = "";
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            for (int i = 0; i <= addresses.get(0).getMaxAddressLineIndex(); i++) {
                address = address + ", " + addresses.get(0).getAddressLine(i);
            }
            // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            return address;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

   /* public void GetDriverStatus() {

        timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    public void run() {
                        try {
                            if (tripFlag != 0) {
                                if (tripsRequest == null) {
                                    tripsRequest = new TripsRequest();
                                    tripsRequest.execute();
                                } else if (tripsRequest.getStatus() == AsyncTask.Status.FINISHED) {
                                    Log.i("tripStatus ", "" + tripFlag);
                                    tripsRequest = new TripsRequest();
                                    tripsRequest.execute();
                                }
                                Log.i("Request Status", "" + tripsRequest.getStatus());

                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 50000 ms

    }*/
    public void VehiclePosition(){
        JsonObject object=new JsonObject();
        object.addProperty("BNo", ApplicationConstants.bookingNo);
        VehiclePosition(object);
    }

    public void VehiclePosition(JsonObject jsonObject){
        com.webingate.paysmartbusinessapp.customerapp.Utils.DataPrepare.get(CurrentTrip.this).getrestadapter()
                .VehiclePosition(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<VehiclePositionResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                       // StopDialogue();
                        VehiclePosition();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            //DisplayToast("Error");
                            //StopDialogue();
                            VehiclePosition();
                            Log.d("OnError ", e.getMessage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<VehiclePositionResponse> responselist) {
                        VehiclePositionResponse response=responselist.get(0);
                        if (response.getBookingStatus().contains("OnTrip")) {
                            Log.i("Trip status", " Ontrip : " + +driverLatitude + "," + driverLongitude);
                            latitude = getIntent().getDoubleExtra("destlat", 0.0);
                            longitude = getIntent().getDoubleExtra("destlon", 0.0);
                            latLng = latlngnew;
                            driverLatitude =response.getLatitude();
                            driverLongitude =response.getLongitude();
                            latlngnew = new LatLng(driverLatitude, driverLongitude);
                            // drawing path between customer and driver with updated location
                            DirectionsTask directionsTask = new DirectionsTask();
                            directionsTask.execute();
                            // moving driver vehicle
                            float rotation = (float) SphericalUtil.computeHeading(latLng, latlngnew);
                            rotateMarker(markerDriver, latlngnew, rotation);
                            updatemarkers++;
                        } else if (response.getBookingStatus().contains("Trip Completed")) {
                            tripFlag = 0;
                            String amount = response.getAmount();
                            String paymentmethod = response.getPaymentTypeId();
                            switch (paymentmethod) {
                                case "90":
                                    paymentmethod = "Cash";
                                    break;
                                case "91":
                                    paymentmethod = "Net Banking";
                                    break;
                                case "93":
                                    paymentmethod = "E-Wallet";
                                    break;
                                case "122":
                                    paymentmethod = "Credit Card";
                                    break;
                                case "123":
                                    paymentmethod = "Debit Card";
                                    break;
                            }
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(CurrentTrip.this, R.style.Theme_AppCompat_DayNight_Dialog);
                            alertDialog.setCancelable(false);
                            alertDialog.setTitle("Payment Mode - " + paymentmethod);
                            alertDialog.setMessage("Amount - " + amount);
                            alertDialog.setPositiveButton("Pay",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            MakePayment();
                                        }
                                    });
                            alertDialog.show();
                        } else if (driverLatitude == 0.0 || driverLongitude == 0.0) {
                            driverLatitude = response.getLatitude();
                            driverLongitude =response.getLongitude();
                            latlngnew = new LatLng(driverLatitude, driverLongitude);
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latlngnew);
                            markerOptions.title("Driver Position");
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_car));
                            markerDriver = mMap.addMarker(markerOptions);
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlngnew));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(16.5f));
                        } else {
                            latLng = latlngnew;
                            driverLatitude = response.getLatitude();
                            driverLongitude =response.getLongitude();
                            latlngnew = new LatLng(driverLatitude, driverLongitude);
                            // drawing path between customer and driver with updated location
                            DirectionsTask directionsTask = new DirectionsTask();
                            directionsTask.execute();
                            // moving driver vehicle
                            float rotation = (float) SphericalUtil.computeHeading(latLng, latlngnew);
                            rotateMarker(markerDriver, latlngnew, rotation);
                        }

                    }
                });
    }

    public void MakePayment(){
        JsonObject object = new JsonObject();
        object.addProperty("flag", "I");
        object.addProperty("BNo", ApplicationConstants.bookingNo);
        object.addProperty("Amount", ApplicationConstants.estPrice);
        object.addProperty("PaymentModeId", ApplicationConstants.paymenttype);
        object.addProperty("CustAccountId", "1");
        object.addProperty("AppUserId", ApplicationConstants.id);
        MakePayment(object);
    }
    public void MakePayment(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.customerapp.Utils.DataPrepare.get(CurrentTrip.this).getrestadapter()
                .MakePayment(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MakepaymentResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<MakepaymentResponse> responselist) {
                        MakepaymentResponse response=responselist.get(0);
                        if (response.getGatewayTransId()!=null) {
                            tripFlag = 0;
                            RatingBarDialogue cdd = new RatingBarDialogue(CurrentTrip.this);
                            cdd.setCanceledOnTouchOutside(false);
                            cdd.show();
                        }

                    }
                });
    }

    public void RateTheRide(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.customerapp.Utils.DataPrepare.get(CurrentTrip.this).getrestadapter()
                .RateTheRide(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CustomerRateTheRideResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<CustomerRateTheRideResponse> responselist) {
                     //   CustomerRateTheRideResponse response=responselist.get(0);
                        finish();
                    }
                });
    }
    public void UpdateBookingStatus(JsonObject jsonObject){
        StartDialogue();
        com.webingate.paysmartbusinessapp.customerapp.Utils.DataPrepare.get(CurrentTrip.this).getrestadapter()
                .UpdateBookingStatus(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UpdateBookingstatusResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<UpdateBookingstatusResponse> responselist) {

                       // finish();
                    }
                });
    }

    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
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




   /* class TripsRequest extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (dialog.isShowing()) {
                dialog.setTitle("Request In Process");
            } else {
                dialog.setMessage("Please Wait...");
                dialog.setCancelable(false);
                dialog.show();
            }
        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                ServerRequest();
                return new String[]{"Success"};
            } catch (Exception e) {
                return new String[]{"error"};
            }
        }

        @Override
        protected void onPostExecute(String... result) {
            try {
                if (response.matches("")) {
                    Toast.makeText(getApplicationContext(), "An error has occurred ", Toast.LENGTH_LONG).show();
                } else {
                    // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    JSONArray jsonObj = new JSONArray(response);
                    JSONObject c = jsonObj.getJSONObject(0);
                    switch (tripFlag) {
                        case GETDRIVELLOCATION:
                            if (c.getString("BookingStatus").contains("OnTrip")) {
                                Log.i("Trip status", " Ontrip : " + +driverLatitude + "," + driverLongitude);
                                latitude = getIntent().getDoubleExtra("destlat", 0.0);
                                longitude = getIntent().getDoubleExtra("destlon", 0.0);
                                latLng = latlngnew;
                                driverLatitude = Double.parseDouble(c.getString("Latitude"));
                                driverLongitude = Double.parseDouble(c.getString("Longitude"));
                                latlngnew = new LatLng(driverLatitude, driverLongitude);
                                // drawing path between customer and driver with updated location
                                DirectionsTask directionsTask = new DirectionsTask();
                                directionsTask.execute();
                                // moving driver vehicle
                                float rotation = (float) SphericalUtil.computeHeading(latLng, latlngnew);
                                rotateMarker(markerDriver, latlngnew, rotation);
                                updatemarkers++;
                                tripFlag = GETDRIVELLOCATION;
                            } else if (c.getString("BookingStatus").contains("Trip Completed")) {
                                Log.i("Trip status", " Completed : " + response);
                                tripFlag = 0;
                                String amount = c.getString("Amount");
                                String paymentmethod = c.getString("PaymentTypeId");
                                switch (paymentmethod) {
                                    case "90":
                                        paymentmethod = "Cash";
                                        break;
                                    case "91":
                                        paymentmethod = "Net Banking";
                                        break;
                                    case "93":
                                        paymentmethod = "E-Wallet";
                                        break;
                                    case "122":
                                        paymentmethod = "Credit Card";
                                        break;
                                    case "123":
                                        paymentmethod = "Debit Card";
                                        break;
                                }
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CurrentTrip.this, R.style.Theme_AppCompat_DayNight_Dialog);
                                alertDialog.setCancelable(false);
                                alertDialog.setTitle("Payment Mode - " + paymentmethod);
                                alertDialog.setMessage("Amount - " + amount);
                                alertDialog.setPositiveButton("Pay",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                tripFlag = PAYMENTS;
                                            }
                                        });
                                alertDialog.show();
                            } else if (driverLatitude == 0.0 || driverLongitude == 0.0) {
                                driverLatitude = Double.parseDouble(c.getString("Latitude"));
                                driverLongitude = Double.parseDouble(c.getString("Longitude"));
                                Log.i("Trip status", " Got DriverLocation : " + driverLatitude + "," + driverLongitude);
                                latlngnew = new LatLng(driverLatitude, driverLongitude);
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latlngnew);
                                markerOptions.title("Driver Position");
                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_car));
                                markerDriver = mMap.addMarker(markerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latlngnew));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(16.5f));
                                tripFlag = GETDRIVELLOCATION;
                            } else {
                                Log.i("Trip status", " Got DriverLocation : " + driverLatitude + "," + driverLongitude);
                                latLng = latlngnew;
                                driverLatitude = Double.parseDouble(c.getString("Latitude"));
                                driverLongitude = Double.parseDouble(c.getString("Longitude"));
                                latlngnew = new LatLng(driverLatitude, driverLongitude);
                                // drawing path between customer and driver with updated location
                                DirectionsTask directionsTask = new DirectionsTask();
                                directionsTask.execute();
                                // moving driver vehicle
                                float rotation = (float) SphericalUtil.computeHeading(latLng, latlngnew);
                                rotateMarker(markerDriver, latlngnew, rotation);
                                tripFlag = GETDRIVELLOCATION;
                            }
                            break;
                        case PAYMENTS:
                            Log.i("Trip status", "make Payments " + response);
                            if (c.has("GatewayTransId")) {
                                tripFlag = 0;
                                RatingBarDialogue cdd = new RatingBarDialogue(CurrentTrip.this);
                                cdd.setCanceledOnTouchOutside(false);
                                cdd.show();
                            }
                            break;
                        case GETFEEDBACK:
                            Log.i("Trip status", " Send Feedback " + response);
                            tripFlag = 0;
                            timer.cancel();
                            finish();
                            break;
                        case CANCELBOOKING:
                            jsonObj = new JSONArray(response);
                            c = jsonObj.getJSONObject(0);
                            Log.i("Booking status", "Booking cancelled : " + response);
                            //   Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            if (dialog.isShowing())
                                dialog.dismiss();
                            *//*AlertDialog alertDialog = new AlertDialog.Builder(CurrentTrip.this, R.style.Dialog_Theme).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("All Cabs Were Busy Please Try Again");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();*//*
                            tripFlag = PAYMENTS;
                            break;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void ServerRequest() {
        BufferedReader reader = null;
        response = "";
        try {
            JSONObject object = new JSONObject();
            switch (tripFlag) {
                case GETDRIVELLOCATION:
                    Log.i("Trip status", " Get DriverLocation");
                    serverUrl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_vehicleposition);
                    object.put("BNo", ApplicationConstants.bookingNo);
                    break;
                case PAYMENTS:
                    Log.i("Trip status", " Making Payment");
                    serverUrl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_customer_make_payment);
                    object = new JSONObject();
                    object.put("flag", "I");
                    object.put("BNo", ApplicationConstants.bookingNo);
                    object.put("Amount", ApplicationConstants.estPrice);
                    object.put("PaymentModeId", ApplicationConstants.paymenttype);
                    object.put("CustAccountId", "1");
                    object.put("AppUserId", ApplicationConstants.id);

                    break;
                case GETFEEDBACK:
                    Log.i("Trip status", " send  Feedback");
                    serverUrl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_custromer_ratingtoride);
                    object = new JSONObject();
                    object.put("CustomerPhoneNo", ApplicationConstants.mobileNo);
                    object.put("BNo", ApplicationConstants.bookingNo);
                    object.put("Rating", ApplicationConstants.rating);
                    object.put("RatedBy", "2");
                    object.put("Comments", ApplicationConstants.comments);
                    break;
                case CANCELBOOKING:
                    Log.i("Booking status", "cancelling booking");
                    object.put("BNo", ApplicationConstants.bookingNo);
                    object.put("BookingStatus", "Cancelled");
                    object.put("UpdatedBy", "1");
                    object.put("UpdatedUserId", "21");
                    serverUrl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_update_bookingstatus);
                    break;
            }
            //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
            // Defined URL  where to send data
            URL url = new URL(serverUrl);
            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Host", "android.schoolportal.gr");
            conn.connect();
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(object.toString());
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
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

    /*//drawing line between source and destination
    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    *//**
     * A method to download json data from url
     *//*
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    *//**
     * A class to download data from Google Directions URL
     *//*
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    */

    /**
     * A class to parse the Google Directions in JSON format
     *//*
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = new ArrayList<LatLng>();
            PolylineOptions lineOptions = new PolylineOptions();
            lineOptions.width(10);
            lineOptions.color(Color.GREEN);
            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }
                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
            }
            if (line != null)
                line.remove();
            line = mMap.addPolyline(lineOptions);
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
           *//* for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }*//*
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(driverLatitude, driverLongitude));
            markerOptions.title("Driver Location");
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_car));
            if (markerDriver != null)
                markerDriver.remove();
            markerDriver = mMap.addMarker(markerOptions);
            builder.include(marker.getPosition());
            builder.include(markerDriver.getPosition());
            LatLngBounds bounds = builder.build();
            int padding = 200; // offset from edges of the map in pixels
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.animateCamera(cu);

        }
    }*/
    public void CheckPermission() {
        if (ActivityCompat.checkSelfPermission(CurrentTrip.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(CurrentTrip.this, Manifest.permission.CALL_PHONE)) {
                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(CurrentTrip.this);
                builder.setTitle("Need Call Permission");
                builder.setMessage("This app needs PhoneCall permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(CurrentTrip.this, new String[]{Manifest.permission.CALL_PHONE}, 100);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(CurrentTrip.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
            }

        } else {

        }
    }

}


