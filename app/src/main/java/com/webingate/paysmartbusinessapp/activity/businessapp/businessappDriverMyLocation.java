package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
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
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
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
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.customerapp.ApplicationConstants;
import com.webingate.paysmartbusinessapp.customerapp.CheckingCabsDialogue;
import com.webingate.paysmartbusinessapp.customerapp.LatLngInterpolator;


import org.joda.time.DateTime;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappDriverMyLocation extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID ="idKey";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordkey";
    public static final String UserAccountNo = "UserAccountNokey";

    @BindView(R.id.toolbar)
    Toolbar toolbar;




   // AppCompatButton bus;
  //  @BindView(R.id.ridenow)
   // AppCompatButton rideNow;
   // @BindView(R.id.ridelater)
   // AppCompatButton rideLater;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    String serverUrl = "";
    int bookingId = 0;
    int dest = 0;
    Place destination, source;
    static GoogleMap mMap;
    static Marker marker, markerDesst;
    private Marker cabs[] = new Marker[5];
    double sourceLatitude = 0.0, sourceLongitude = 0.0, destLatitude = 0.0, destLongitude = 0.0;
    private static final int CHECKPRICE = 1;
    private static final int BOOKCAB = 2;
    private static final int GETBOOKINGSTATUS = 3;
    private static final int CANCELBOOKING = 4;
    private static final int RIDELATER = 5;
    private static final int GETDIRECTIONS = 6;
    private static final int GETNEARESTVEHICLES = 7;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 2;
    private static final int CURRENT_TRIP_ACTIVITY = 1;

    private static GoogleApiClient mGoogleApiClient;
    private static final int ACCESS_FINE_LOCATION_INTENT_ID = 3;
    private static final String BROADCAST_ACTION = "android.location.PROVIDERS_CHANGED";
    final Handler handler = new Handler();
    // HirevehicleRequest hirevehicleRequest;

    DirectionsResult result;
    boolean isBookingStarted=true;
    Toast toast;

    Double lat1,log1,dlat,dlog;
    //TODO: this is to test then scroll view navigation

    RecyclerView rvProduct;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* if (timer != null)
            ApplicationConstants.tripFlag = 0;
        timer.cancel();*/

    }

    /*Handler handler = new Handler();
     Runnable updateMarker = new Runnable() {
         @Override
         public void run() {

             Toast.makeText(getApplicationContext(),latitude+" "+longitude,Toast.LENGTH_SHORT).show();
                startPosition=new LatLng(latitude,longitude);
                 mMap.moveCamera(CameraUpdateFactory.newLatLng(startPosition));
                 marker.setPosition(startPosition);

                 // Repeat till progress is complete.
         }
     };*/
    private LocationRequest mLocationRequest;
    ActionBarDrawerToggle toggle;
    private LatLng latLng, latlngnew;
    private String response;
    private Polyline line;
    // private Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_driver_mylocation);
        ButterKnife.bind(this);

        //SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        initGoogleAPIClient();//Init Google API Client
        checkPermissions();//Check Permission
        //  configureCameraIdle();//cofigure drag destionatio selection
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        taxi.setVisibility(View.GONE);
//        meteredtaxi.setVisibility(View.GONE);
//        bus.setVisibility(View.GONE);


       ;






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
    private String getEndLocationTitle(DirectionsResult results) {
        return "Time :" + results.routes[0].legs[0].duration.humanReadable + " Distance :" + results.routes[0].legs[0].distance.humanReadable;
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
                        .mode(TravelMode.DRIVING).origin(new com.google.maps.model.LatLng(sourceLatitude, sourceLongitude))
                        .destination(new com.google.maps.model.LatLng(destLatitude, destLongitude)).departureTime(now).await();
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
                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(result.routes[0]
                        .legs[0].startLocation.lat, result.routes[0]
                        .legs[0].startLocation.lng))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                       .title("Source" + result.routes[0].legs[0].startAddress);
                if (marker != null)
                    marker.remove();
                marker = mMap.addMarker(markerOptions);
                markerOptions = new MarkerOptions().position(new LatLng(result.routes[0]
                        .legs[0].endLocation.lat, result.routes[0]
                        .legs[0].endLocation.lng))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                        .title("Destination" + result.routes[0].legs[0].endAddress);
                if (markerDesst != null)
                    markerDesst.remove();
                markerDesst = mMap.addMarker(markerOptions);
                //selectsource.setText("Source :" + result.routes[0].legs[0].startAddress);
                //selectDestination.setText("Destination :" + result.routes[0].legs[0].endAddress);
                List<LatLng> decodedPath = PolyUtil.decode(result.routes[0].overviewPolyline.getEncodedPath());
                PolylineOptions polylineOptions = new PolylineOptions().addAll(decodedPath);
                if (line != null)
                    line.remove();
                line = mMap.addPolyline(polylineOptions);
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
                requestLocationPermission();
            }
        } else {
            showSettingDialog();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //User has previously accepted this permission
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            //Not in api-23, no need to prompt
            mMap.setMyLocationEnabled(true);
        }
        // mMap.getUiSettings().setZoomControlsEnabled(true);
        // mMap.getUiSettings().setZoomGesturesEnabled(true);

        // mMap.setOnCameraIdleListener(onCameraIdleListener);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        latlngnew = new LatLng(0.0, 0.0);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlngnew);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(ApplicationConstants.marker));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                destLatitude = latLng.latitude;
                destLongitude = latLng.longitude;
                dlat=destLatitude;
                dlog=destLongitude;
                //selectDestination.setText("Destination : " + (destLatitude + "").substring(0, 10) + " , " + (destLongitude + "").substring(0, 10));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(destLatitude, destLongitude));
                markerOptions.title("Destination");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                if (markerDesst != null)
                    markerDesst.remove();
                markerDesst = mMap.addMarker(markerOptions);
                // Getting Directions from source to destination
                businessappDriverMyLocation.DirectionsTask downloadTask2 = new businessappDriverMyLocation.DirectionsTask();
                downloadTask2.execute();
                dest = 0;
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, businessappDriverMyLocation.this);
            }
        });

    }

    /* Initiate Google API Client  */
    private void initGoogleAPIClient() {
        //Without Google API Client Auto Location Dialog will not work
        mGoogleApiClient = new GoogleApiClient.Builder(businessappDriverMyLocation.this)
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
            if (ContextCompat.checkSelfPermission(businessappDriverMyLocation.this,
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED)
                requestcallPermission();
            if (ContextCompat.checkSelfPermission(businessappDriverMyLocation.this,
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
        if (ActivityCompat.shouldShowRequestPermissionRationale(businessappDriverMyLocation.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(businessappDriverMyLocation.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(businessappDriverMyLocation.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);
        }
    }

    private void requestcallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(businessappDriverMyLocation.this, Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(businessappDriverMyLocation.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(businessappDriverMyLocation.this,
                    new String[]{Manifest.permission.CALL_PHONE},
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
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            mMap.setMyLocationEnabled(true);
                        }
                        updateGPSStatus("GPS is Enabled in your device");
                            try {
                                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, businessappDriverMyLocation.this);
                               // AvailableVehiclesTest(0);
                            }catch (Exception ex){
                              Log.println(Log.ASSERT,"error",ex.getMessage().toString());
                            }
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(businessappDriverMyLocation.this, REQUEST_CHECK_SETTINGS);
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

    @SuppressLint("MissingPermission")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case CURRENT_TRIP_ACTIVITY:
                //selectsource.setText("");
               // selectDestination.setText("");
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
                mMap.clear();
                onMapReady(mMap);
                finish();
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(gpsLocationReceiver, new IntentFilter(BROADCAST_ACTION));//Register broadcast receiver to check the status of GPS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && mGoogleApiClient.isConnected()) {
            //  LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
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

        initGoogleAPIClient();
        showSettingDialog();

//        switch (requestCode) {
//            case ACCESS_FINE_LOCATION_INTENT_ID: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    //If permission granted show location dialog if APIClient is not null
//                    if (mGoogleApiClient == null) {
//                        initGoogleAPIClient();
//                        showSettingDialog();
//                    } else
//                        showSettingDialog();
//
//
//                } else {
//                    updateGPSStatus("Location Permission denied.");
//                    Toast.makeText(businessappDriverMyLocation.this, "Location Permission denied.", Toast.LENGTH_SHORT).show();
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//        }
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
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        // mMap.animateCamera(CameraUpdateFactory.zoomTo(18.5f));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        //  mLastLocation = location;
       /* if (marker != null) {
            marker.remove();
        }*/
        sourceLatitude = location.getLatitude();
        sourceLongitude = location.getLongitude();
        lat1=sourceLatitude;
        log1=sourceLongitude;
        //  DisplayToast(sourceLatitude + " " + sourceLongitude);
        latLng = latlngnew;
        //Place current location marker
        //below statement commenting to test for zimbzbwe location
        latlngnew = new LatLng(sourceLatitude, sourceLongitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlngnew);
        markerOptions.title("Source");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        if (marker != null)
            marker.remove();
        marker = mMap.addMarker(markerOptions);
       // selectsource.setText("Source : " + sourceLatitude + " , " + sourceLongitude);
        if (GetAddress(sourceLatitude, sourceLongitude) != null)
           // selectsource.setText("Source :" + GetAddress(sourceLatitude, sourceLongitude));


        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlngnew));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16.5f));
        //stop location updates
        //  if (mGoogleApiClient != null)
//        if (!selectDestination.getText().toString().matches("")) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//            businessappDriverMyLocation.DirectionsTask downloadTask = new businessappDriverMyLocation.DirectionsTask();
//            downloadTask.execute();
//        }

//        latLng = new LatLng(location.getLatitude(), location.getLongitude() - 0.001225);
//        markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Seshu");
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_car));
//        markerCar2 = mMap.addMarker(markerOptions);
//
//        latLng = new LatLng(location.getLatitude() + 0.002225, location.getLongitude());
//        markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Seshu");
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_bus));
//        markerBus = mMap.addMarker(markerOptions);
//
//        latLng = new LatLng(location.getLatitude() + 0.001225, location.getLongitude());
//        markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Ambulance");
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_ambulance));
//        markerAmbulance = mMap.addMarker(markerOptions);
//
//        float rotation = (float) SphericalUtil.computeHeading(latLng, latlngnew);
//        rotateMarker(markerCar1, latlngnew, rotation);
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlngnew));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16.5f));
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
                address = address + addresses.get(0).getAddressLine(i) + ",";
            }
            // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            return address;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   /* public void GetBookingStatus() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (ApplicationConstants.tripFlag != 0) {
                                if (hirevehicleRequest == null) {
                                    hirevehicleRequest = new HirevehicleRequest();
                                    hirevehicleRequest.execute();
                                } else if (hirevehicleRequest.getStatus() == AsyncTask.Status.FINISHED) {
                                    Log.i("BookingStatus ", "" + ApplicationConstants.tripFlag);
                                    hirevehicleRequest = new HirevehicleRequest();
                                    // PerformBackgroundTask this class is the class that extends AsynchTask
                                    hirevehicleRequest.execute();
                                }
                                Log.i("Request Status", "" + hirevehicleRequest.getStatus());

                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };

        timer.schedule(timerTask, 0, 5000); //execute in every 50000 ms
    }*/





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Unregister receiver on destroy
        if (gpsLocationReceiver != null)
            unregisterReceiver(gpsLocationReceiver);
    }
}


