package com.webingate.paysmartbusinessapp.customerapp;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;
import com.webingate.paysmartbusinessapp.businessapp.Deo.CustomerGetstopsResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;
public class MeteredTaxi extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    String serverUrl = "";
    int bookingId = 0;
    int dest = 0;
    static GoogleMap mMap;
    static Marker marker, markerCar1, markerCar2, markerBus, markerAmbulance, markerDesst, markersource;
    double latitude = 0.0, longitude = 0.0, sourceLatitude = 0.0, sourceLongitude = 0.0, destLatitude = 0.0, destLongitude = 0.0;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 2;
    private static final int CURRENT_TRIP_ACTIVITY = 1;
    private static GoogleApiClient mGoogleApiClient;
    private static final int ACCESS_FINE_LOCATION_INTENT_ID = 3;
    private static final String BROADCAST_ACTION = "android.location.PROVIDERS_CHANGED";
    final Handler handler = new Handler();
    ProgressDialog dialog;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.map_source)
    TextView selectsource;
    @BindView(R.id.source_gps_location)
    Button sourceGpsLocation;
    @BindView(R.id.table_row)
    TableRow tableRow;
    @BindView(R.id.map_destination)
    TextView selectDestination;

    @BindView(R.id.taxi)
    AppCompatButton taxi;
    @BindView(R.id.meteredtaxi)
    AppCompatButton meteredtaxi;
    @BindView(R.id.bus)
    AppCompatButton bus;
    @BindView(R.id.ridenow)
    AppCompatButton rideNow;
    @BindView(R.id.ridelater)
    AppCompatButton rideLater;
    private Toast toast = null;
    List<CustomerGetstopsResponse> list;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //timer.cancel();

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
    private Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hirevehicle);
        ButterKnife.bind(this);
        list= (List<CustomerGetstopsResponse>) getIntent().getSerializableExtra("details");
        initGoogleAPIClient();//Init Google API Client
        checkPermissions();//Check Permission
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        taxi.setVisibility(View.GONE);
        meteredtaxi.setVisibility(View.GONE);
        bus.setVisibility(View.GONE);
        sourceGpsLocation.setVisibility(View.GONE);
        dialog = new ProgressDialog(MeteredTaxi.this);
        selectsource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationConstants.source = "";
                dest = 1;
                startActivityForResult(new Intent(MeteredTaxi.this, TaxiStops.class), PLACE_AUTOCOMPLETE_REQUEST_CODE);
            }
        });

        selectDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dest = 2;
                startActivityForResult(new Intent(MeteredTaxi.this, TaxiStops.class), PLACE_AUTOCOMPLETE_REQUEST_CODE);
            }
        });
        rideNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectDestination.getText().toString().matches("")) {
                    DisplayToast("Please Select Destination");
                } else {
                    ApplicationConstants.tripFlag = 1;
                    GetBookingStatus();

                }
            }
        });
        rideLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectDestination.getText().toString().matches("")) {
                    DisplayToast("Please Select Destination");
                } else {
                    ApplicationConstants.tripFlag = 0;
                    GetBookingStatus();
                    RideLater_Dialoguebox rideLater_dialoguebox = new RideLater_Dialoguebox(MeteredTaxi.this);
                    rideLater_dialoguebox.setCanceledOnTouchOutside(false);
                    rideLater_dialoguebox.show();
                }
            }
        });


        //  GetBookingStatus();
       /* PlaceAutocompleteFragment places= (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Toast.makeText(getApplicationContext(),place.getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });*/
    }

    //toolbar button click
    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        return true;
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


        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        latlngnew = new LatLng(0.0, 0.0);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlngnew);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(ApplicationConstants.marker));
        markerCar1 = mMap.addMarker(markerOptions);
    }

    /* Initiate Google API Client  */
    private void initGoogleAPIClient() {
        //Without Google API Client Auto Location Dialog will not work
        mGoogleApiClient = new GoogleApiClient.Builder(MeteredTaxi.this)
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
            if (ContextCompat.checkSelfPermission(MeteredTaxi.this,
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED)
                requestcallPermission();
            if (ContextCompat.checkSelfPermission(MeteredTaxi.this,
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
        if (ActivityCompat.shouldShowRequestPermissionRationale(MeteredTaxi.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(MeteredTaxi.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(MeteredTaxi.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);
        }
    }

    private void requestcallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MeteredTaxi.this, Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(MeteredTaxi.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(MeteredTaxi.this,
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
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(MeteredTaxi.this, REQUEST_CHECK_SETTINGS);
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
        switch (requestCode) {
            case PLACE_AUTOCOMPLETE_REQUEST_CODE:
                if (dest == 1) {
                    CustomerGetstopsResponse stopsModel = list.get(ApplicationConstants.sourceid);
                    ApplicationConstants.source = stopsModel.getName();
                    ApplicationConstants.sourceid = Integer.parseInt(stopsModel.getId());
                    sourceLatitude = Double.parseDouble(stopsModel.getLatitude());
                    sourceLongitude = Double.parseDouble(stopsModel.getLongitude());
                    selectsource.setText("Source :" + ApplicationConstants.source);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(sourceLatitude, sourceLongitude));
                    markerOptions.title("Source Position");
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                    if (marker != null)
                        marker.remove();
                    marker = mMap.addMarker(markerOptions);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(sourceLatitude, sourceLongitude)));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(16.5f));
                    if (!selectDestination.getText().toString().matches("")) {
                        String url = getDirectionsUrl(new LatLng(sourceLatitude, sourceLongitude), new LatLng(destLatitude, destLongitude));
                        DownloadTask downloadTask = new DownloadTask();
                        // Start downloading json data from Google Directions API
                        downloadTask.execute(url);
                    }
                    //call book trip api
                    // Getting URL to the Google Directions API
                    dest = 0;
                } else if (dest == 2) {
                    CustomerGetstopsResponse stopsModel = list.get(ApplicationConstants.destinationid);
                    ApplicationConstants.destination = stopsModel.getName();
                    ApplicationConstants.destinationid = Integer.parseInt(stopsModel.getId());
                    destLatitude = Double.parseDouble(stopsModel.getLatitude());
                    destLongitude = Double.parseDouble(stopsModel.getLongitude());
                    selectDestination.setText("Destination :" + ApplicationConstants.destination);
                    //call book trip api
                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(new LatLng(sourceLatitude, sourceLongitude), new LatLng(destLatitude, destLongitude));
                    DownloadTask downloadTask = new DownloadTask();
                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                    dest = 0;
                }

                break;
            case CURRENT_TRIP_ACTIVITY:
                RatingBarDialogue cdd = new RatingBarDialogue(MeteredTaxi.this);
                cdd.setCanceledOnTouchOutside(false);
                cdd.show();
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(gpsLocationReceiver, new IntentFilter(BROADCAST_ACTION));//Register broadcast receiver to check the status of GPS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
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
                    Toast.makeText(MeteredTaxi.this, "Location Permission denied.", Toast.LENGTH_SHORT).show();
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
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
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
        //  mLastLocation = location;
       /* if (marker != null) {
            marker.remove();
        }*/
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        latLng = latlngnew;
        latlngnew = new LatLng(location.getLatitude(), location.getLongitude());
        //Place current location marker
        latlngnew = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlngnew);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        if (marker != null)
            marker.remove();
        marker = mMap.addMarker(markerOptions);

        /*latLng = new LatLng(location.getLatitude()+0.001225, location.getLongitude());
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Deepak");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.taxi_small));
        markerCar1 = mMap.addMarker(markerOptions);*/

        latLng = new LatLng(location.getLatitude(), location.getLongitude() - 0.001225);
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("taxi");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(ApplicationConstants.marker));
        if (markerCar2 != null)
            markerCar2.remove();
        markerCar2 = mMap.addMarker(markerOptions);

        latLng = new LatLng(location.getLatitude() + 0.002225, location.getLongitude());
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("taxi");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(ApplicationConstants.marker));
        if (markerBus != null)
            markerBus.remove();
        markerBus = mMap.addMarker(markerOptions);

        latLng = new LatLng(location.getLatitude() + 0.001225, location.getLongitude());
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("taxi");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(ApplicationConstants.marker));
        if (markerAmbulance != null)
            markerAmbulance.remove();
        markerAmbulance = mMap.addMarker(markerOptions);

        float rotation = (float) SphericalUtil.computeHeading(latLng, latlngnew);
        rotateMarker(markerCar1, latlngnew, rotation);
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlngnew));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16.5f));
        //stop location updates
        //  if (mGoogleApiClient != null)
        // if (!selectDestination.getText().toString().matches(""))
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        //  }
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

    public void GetBookingStatus() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            DisplayToast("Timer Running " + ApplicationConstants.tripFlag);
                            if (ApplicationConstants.tripFlag != 0) {
                                // Toast.makeText(getApplicationContext(), ApplicationConstants.tripFlag + "", Toast.LENGTH_SHORT).show();
                                HirevehicleRequest hirevehicleRequest = new HirevehicleRequest();
                                // PerformBackgroundTask this class is the class that extends AsynchTask
                                hirevehicleRequest.execute();

                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };

        timer.schedule(timerTask, 0, 5000); //execute in every 50000 ms
    }

    class HirevehicleRequest extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (ApplicationConstants.tripFlag != 4) {
                dialog.setMessage("Please Wait..." + ApplicationConstants.tripFlag);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }

        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                if (ApplicationConstants.tripFlag != 3)
                    Hirevehicle();
                return new String[]{"Success"};
            } catch (Exception e) {
                return new String[]{"error"};
            }
        }

        @Override
        protected void onPostExecute(String... result) {
            try {
                if (response.matches("")) {
                    DisplayToast("An error has occurred ");
                } else {
                    //Toast.makeText(getApplicationContext(), response.substring(0,10), Toast.LENGTH_LONG).show();
                    JSONArray jsonObj = new JSONArray(response);
                    JSONObject c = jsonObj.getJSONObject(0);
                    switch (ApplicationConstants.tripFlag) {
                        case 1:
                            ApplicationConstants.tripFlag = 0;
                            ApplicationConstants.estPrice = c.getString("Amount");
                            Payments_Dialoguebox payments_dialoguebox = new Payments_Dialoguebox(MeteredTaxi.this,ApplicationConstants.estPrice);
                            payments_dialoguebox.setCanceledOnTouchOutside(false);
                            payments_dialoguebox.show();

                            break;
                        case 3:
                            DisplayToast("bookingNumber " + c.getString("bookingNumber"));
                            ApplicationConstants.tripFlag++;
                            ApplicationConstants.bookingNo = c.getString("bookingNumber");
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            CheckingCabsDialogue checkingCabsDialogue = new CheckingCabsDialogue(MeteredTaxi.this);
                            checkingCabsDialogue.setCanceledOnTouchOutside(false);
                            checkingCabsDialogue.show();
                            break;
                        case 4:
                            if (!c.getString("BooKingOTP").matches("null")) {
                                ApplicationConstants.booKingOTP = c.getString("BooKingOTP");
                                ApplicationConstants.driverName = c.getString("Name");
                                ApplicationConstants.mobNo = c.getString("PMobNo");
                                ApplicationConstants.driverimage = c.getString("img");
                                // ApplicationConstants.vehicleimage = c.getString("VPhoto");
                                ApplicationConstants.registrationNo = c.getString("RegistrationNo");
                                ApplicationConstants.vModel = c.getString("VModel");
                                ApplicationConstants.tripFlag = 0;
                                // Thread.sleep(3000);
                                startActivityForResult(new Intent(MeteredTaxi.this, CurrentTrip.class), CURRENT_TRIP_ACTIVITY);
                                timer.cancel();
                            } else {
                                DisplayToast(response);
                            }
                            break;
                        case 5:
                            //   Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            if (dialog.isShowing())
                                dialog.dismiss();
                            AlertDialog alertDialog = new AlertDialog.Builder(MeteredTaxi.this, R.style.Dialog_Theme).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("All Cabs Were Busy Please Try Again");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                            ApplicationConstants.tripFlag = 0;
                            break;
                        case 7:
                            DisplayToast(response);
                            ApplicationConstants.tripFlag = 0;
                            timer.cancel();
                            break;

                    }
                }

                if (dialog.isShowing())
                    dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void Hirevehicle() {
        BufferedReader reader = null;
        response = "";
        try {
            JSONObject object = new JSONObject();
            switch (ApplicationConstants.tripFlag) {
                case 1:
                    serverUrl = getResources().getString(R.string.url_server) + "/api/MeteredTaxi/TaxiPrice?SrcId=" + ApplicationConstants.sourceid + "&DestId=" + ApplicationConstants.destinationid;
                    break;
                case 2:
                    DateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
                    dateFormatter.setLenient(false);
                    Date today = new Date();
                    String datetime = dateFormatter.format(today);
                    object.put("flag", "i");
                    object.put("Id", "");
                    object.put("CompanyId", "2");
                    object.put("BNo", ApplicationConstants.bookingNo);
                    object.put("BookedDate", datetime.substring(0, 11));
                    object.put("BookedTime", datetime.substring(11));
                    object.put("DepartueDate", datetime.substring(0, 11));
                    object.put("DepartureTime", datetime.substring(11));
                    object.put("BookingType", "currentbooking");
                    object.put("Src", selectsource.getText().toString());
                    object.put("Dest", selectDestination.getText().toString());
                    object.put("SrcId", "15");
                    object.put("DestId", "35");
                    object.put("SrcLatitude", latitude + "");
                    object.put("SrcLongitude", longitude + "");
                    object.put("DestLatitude", destLatitude + "");
                    object.put("DestLongitude", destLongitude + "");
                    object.put("VechId", "12");
                    object.put("PackageId", "101");
                    object.put("Pricing", "300");
                    object.put("DriverId", "");
                    object.put("DriverPhoneNo", "");
                    object.put("CustomerPhoneNo", ApplicationConstants.mobileNo);
                    object.put("CustomerId", "568");
                    object.put("BookingStatus", "New");
                    object.put("NoofVehicles", "1");
                    object.put("NoofSeats", "1");
                    object.put("ClosingDate", "");
                    object.put("ClosingTime", "");
                    object.put("CancelledOn", "");
                    object.put("CancelledBy", "");
                    object.put("BookingChannel", "app");
                    object.put("Reasons", "");
                    serverUrl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_save_bookingdetails);
                    ApplicationConstants.tripFlag++;
                    break;
                case 4:
                    object.put("BNo", ApplicationConstants.bookingNo);
                    serverUrl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_bookingstatus);
                    break;
                case 5:
                    object.put("BNo", ApplicationConstants.bookingNo);
                    object.put("BookingStatus", "Cancelled");
                    object.put("UpdatedBy", "1");
                    object.put("UpdatedUserId", "21");
                    serverUrl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_update_bookingstatus);
                    break;
                case 7:
                    dateFormatter = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
                    dateFormatter.setLenient(false);
                    today = new Date();
                    datetime = dateFormatter.format(today);
                    object.put("flag", "U");
                    object.put("Id", "");
                    object.put("CompanyId", "1");
                    object.put("BNo", "00");
                    object.put("BookedDate", datetime.substring(0, 11));
                    object.put("BookedTime", datetime.substring(11));
                    object.put("DepartueDate", ApplicationConstants.bookingDate);
                    object.put("DepartureTime", ApplicationConstants.bookingTime);
                    object.put("BookingType", "Advancebooking");
                    object.put("Src", selectsource.getText().toString());
                    object.put("Dest", selectDestination.getText().toString());
                    object.put("SrcId", "15");
                    object.put("DestId", "35");
                    object.put("SrcLatitude", sourceLatitude + "");
                    object.put("SrcLongitude", sourceLongitude + "");
                    object.put("DestLatitude", destLatitude + "");
                    object.put("DestLongitude", destLongitude + "");
                    object.put("VechId", "");
                    object.put("PackageId", "250");
                    object.put("Pricing", "300");
                    object.put("DriverId", "");
                    object.put("DriverPhoneNo", "");
                    object.put("CustomerPhoneNo", ApplicationConstants.mobileNo);
                    object.put("CustomerId", "568");
                    object.put("BookingStatus", "New");
                    object.put("NoofVehicles", "1");
                    object.put("NoofSeats", "1");
                    object.put("ClosingDate", "");
                    object.put("ClosingTime", "");
                    object.put("CancelledOn", "");
                    object.put("CancelledBy", "");
                    object.put("BookingChannel", "app");
                    object.put("Reasons", "");
                    serverUrl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_advance_bookingdetails);
                    break;


            }
            URL url = new URL(serverUrl);
            // Send POST data request
            URLConnection conn = url.openConnection();
            if (ApplicationConstants.tripFlag == 1) {
                conn.setDoInput(true);
                // conn.setDoOutput (true);
                conn.setUseCaches(false);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Host", "android.schoolportal.gr");
                conn.connect();
                // OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                // wr.write(object.toString());
                // wr.flush();
            } else {
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Host", "android.schoolportal.gr");
                conn.connect();
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(object.toString());
                wr.flush();
            }
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
            e.printStackTrace();
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

    }

    //drawing line between source and destination
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

    /**
     * A method to download json data from url
     */
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
            Log.d("Eror downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    /**
     * A class to download data from Google Directions URL
     */
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

    /**
     * A class to parse the Google Directions in JSON format
     */
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
            ;
            PolylineOptions lineOptions = new PolylineOptions();
            ;
            lineOptions.width(10);
            lineOptions.color(Color.BLACK);
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
           /* for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }*/
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(sourceLatitude, sourceLongitude));
            markerOptions.title("Source");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            if (markersource != null)
                markersource.remove();
            markersource = mMap.addMarker(markerOptions);
            markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(destLatitude, destLongitude));
            markerOptions.title("Destination");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            if (markerDesst != null)
                markerDesst.remove();
            markerDesst = mMap.addMarker(markerOptions);

            builder.include(marker.getPosition());
            builder.include(markerDesst.getPosition());
            LatLngBounds bounds = builder.build();
            int padding = 200; // offset from edges of the map in pixels
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.animateCamera(cu);
        }
    }

    private void DisplayToast(String msg) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this, msg, toast.LENGTH_SHORT);
        toast.show();
    }
}


