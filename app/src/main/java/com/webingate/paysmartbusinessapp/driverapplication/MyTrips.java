package com.webingate.paysmartbusinessapp.driverapplication;

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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AcceptRejectBookingResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverRateTheRideResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.EndtripResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.StartTripResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.TrackvehicleResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

import org.joda.time.DateTime;
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
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
public class MyTrips extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,RatingBarDialogue.RatingBarInterface {
    String serverUrl = "", otp = "";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.trip)
    AppCompatButton trip;
    @BindView(R.id.payments)
    AppCompatButton payments;
    @BindView(R.id.call)
    AppCompatButton call;
    private GoogleMap mMap;
    private static final int CHECKBOOKINGS = 3;
    private static final int ACCEPTBOOKING = 4;
    private static final int REJECTBOOKING = 5;
    private static final int STARTTRIP = 1;
    private static final int ENDTRIP = 2;
    private static final int PAYMENTS = 8;
    private static final int RATETHERIDE = 9;
    static Marker marker, markerDest;
    double latitude = 0.0, longitude = 0.0, destlat, destlong;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private static GoogleApiClient mGoogleApiClient;
    private static final int ACCESS_FINE_LOCATION_INTENT_ID = 3;
    private static final String BROADCAST_ACTION = "android.location.PROVIDERS_CHANGED";
    final Handler handler = new Handler();
   // TripsRequest tripsRequest;
    DirectionsResult result;
    boolean updatemarkers = false;
    private static final int TIMEINTERVAL = 5;

    Toast toast;
    ProgressDialog dialog ;
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private LocationRequest mLocationRequest;
    ActionBarDrawerToggle toggle;
    private LatLng latLng, latlngnew;
    private String response;
    private Polyline line;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytrips);
        ButterKnife.bind(this);
//        dialog =  new ProgressDialog.Builder(MyTrips.this)
//                .setTitle("Loading...")
//                .setTitleColorRes(R.color.gray)
//                .build();
        initGoogleAPIClient();//Init Google API Client
        checkPermissions();//Check Permission
//        destlat = Double.parseDouble(ApplicationConstants.sourcelatitude);
//        destlong = Double.parseDouble(ApplicationConstants.sourcelongitude);
         destlat=17.456455;
         destlong=78.412086;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (ApplicationConstants.mapflag == 1) {
            trip.setVisibility(View.GONE);
            payments.setVisibility(View.GONE);
            call.setVisibility(View.GONE);
        }
        trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trip.getText().toString().matches("End Trip")) {
                    JsonObject object=new JsonObject();
                    object.addProperty("BNo", ApplicationConstants.bNo);
                    object.addProperty("DriverPhoneNo", ApplicationConstants.mobileNo);
                    EndTrip(object);
                    //ApplicationConstants.tripflag = ENDTRIP;


                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyTrips.this, R.style.Theme_AppCompat_DayNight_Dialog);
                    alertDialog.setTitle("TRIP OTP");
                    alertDialog.setMessage("Please Enter OTP");
                    final EditText input = new EditText(MyTrips.this);
                    //  input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                    InputFilter[] FilterArray = new InputFilter[1];
                    FilterArray[0] = new InputFilter.LengthFilter(4);
                    input.setFilters(FilterArray);
                    alertDialog.setView(input);
                    alertDialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (input.getText().toString().matches("")) {
                                        Toast.makeText(getApplicationContext(), "Please Enter OTP", Toast.LENGTH_SHORT).show();

                                    } else {
                                        otp = input.getText().toString();

                                        JsonObject object=new JsonObject();
                                        object.addProperty("BNo", ApplicationConstants.bNo);
                                        object.addProperty("DriverPhoneNo", ApplicationConstants.mobileNo);
                                        object.addProperty("BVerificationCode", otp);
                                        StartTrip(object);
                                      //  ApplicationConstants.tripflag = STARTTRIP;
                                    }
                                }
                            });


                    alertDialog.show();
                }
            }


        });
        payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationConstants.tripflag = PAYMENTS;
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + ApplicationConstants.customerMobileNo));
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

       /* ApplicationConstants.tripflag = CHECKBOOKINGS;
        GetBookingStatus();*/

        TrackVehicle();

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

    @Override
    public void Rating(String rating, String comments) {
        JsonObject object = new JsonObject();
        object.addProperty("DriverPhoneNo", ApplicationConstants.mobileNo);
        object.addProperty("BNo", ApplicationConstants.bNo);
        object.addProperty("DriverRating", ApplicationConstants.rating);
        object.addProperty("DriverRated", ApplicationConstants.rating);
        object.addProperty("DriverComments", ApplicationConstants.comments);
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
                        .mode(TravelMode.DRIVING).origin(new com.google.maps.model.LatLng(latitude, longitude))
                        .destination(new com.google.maps.model.LatLng(destlat, destlong)).departureTime(now).await();
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
            try {
                if (result != null) {
                    if (updatemarkers) {
                        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(result.routes[0]
                                .legs[0].startLocation.lat, result.routes[0]
                                .legs[0].startLocation.lng))
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_taxi))
                                .title("My Location :" + result.routes[0].legs[0].startAddress);
                        if (marker != null)
                            marker.remove();
                        marker = mMap.addMarker(markerOptions);
                        markerOptions = new MarkerOptions().position(new LatLng(result.routes[0]
                                .legs[0].endLocation.lat, result.routes[0]
                                .legs[0].endLocation.lng))
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                                .title("Customer :" + result.routes[0].legs[0].endAddress);
                        if (markerDest != null)
                            markerDest.remove();
                        markerDest = mMap.addMarker(markerOptions);
                        updatemarkers = false;
                    }
                    List<LatLng> decodedPath = PolyUtil.decode(result.routes[0].overviewPolyline.getEncodedPath());
                    PolylineOptions polylineOptions = new PolylineOptions().addAll(decodedPath);
                    if (line != null)
                        line.remove();
                    line = mMap.addPolyline(polylineOptions);
                }

            } catch (Exception e) {
                Log.d("Exception", e.getLocalizedMessage());

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
        }
        mMap.setMyLocationEnabled(true);
        // mMap.getUiSettings().setZoomControlsEnabled(true);
        // mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        latlngnew = new LatLng( destlat, destlong);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlngnew);
        markerOptions.title("My Position");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_taxi));
        marker = mMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlngnew));
        if (ApplicationConstants.mapflag == 1) {

        } else {
//            markerOptions.position(new LatLng(destlat, destlong));
//            markerOptions.title("Customer ");
//            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//            markerDest = mMap.addMarker(markerOptions);
        }

    }

    /* Initiate Google API Client  */
    private void initGoogleAPIClient() {
        //Without Google API Client Auto Location Dialog will not work
        mGoogleApiClient = new GoogleApiClient.Builder(MyTrips.this)
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
            if (ContextCompat.checkSelfPermission(MyTrips.this,
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
        if (ActivityCompat.shouldShowRequestPermissionRationale(MyTrips.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(MyTrips.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(MyTrips.this,
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
                        updateGPSStatus("GPS is Enabled in your device");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(MyTrips.this, REQUEST_CHECK_SETTINGS);
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
                    Toast.makeText(MyTrips.this, "Location Permission denied.", Toast.LENGTH_SHORT).show();
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
        // Toast.makeText(getApplicationContext(), latitude+"   "+longitude, Toast.LENGTH_SHORT).show();
        latLng = latlngnew;
        latlngnew = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlngnew);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));


        if (ApplicationConstants.mapflag != 1) {
            DirectionsTask directionsTask = new DirectionsTask();
            directionsTask.execute();
            float rotation = (float) SphericalUtil.computeHeading(latLng, latlngnew);
            rotateMarker(marker, latlngnew, rotation);
        }
        // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        //stop location updates
        //  if (mGoogleApiClient != null) {
        /*if(ApplicationConstants.mapflag==0){
            String url = getDirectionsUrl(latlngnew, new LatLng(Double.parseDouble(ApplicationConstants.destlatitude), Double.parseDouble(ApplicationConstants.destlongitude)));
            DownloadTask downloadTask = new DownloadTask();
            downloadTask.execute(url);
        }*/
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
                address = address + ", " + addresses.get(0).getAddressLine(i);
            }
            // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            return address;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

    /*public void GetBookingStatus() {

        timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            if (ApplicationConstants.tripflag != 0) {
                                if (tripsRequest == null) {
                                    tripsRequest = new TripsRequest();
                                    tripsRequest.execute();
                                } else if (tripsRequest.getStatus() == AsyncTask.Status.FINISHED) {
                                    Log.i("BookingStatus ", "" + ApplicationConstants.tripflag);
                                    tripsRequest = new TripsRequest();
                                    // PerformBackgroundTask this class is the class that extends AsynchTask
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
    public void TrackVehicle(){
        JsonObject object = new JsonObject();
        object.addProperty("PMobNo", ApplicationConstants.mobileNo);
        object.addProperty("latitude", destlat+ "");
        object.addProperty("longitude", destlong + "");
        TrackVehicle(object);
    }

    public void StartTrip(JsonObject jsonObject){

      //  StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(MyTrips.this).getrestadapter()
                .StartTrip(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<StartTripResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                   //     StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {

                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                          //  StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<StartTripResponse> responseList) {
                        StartTripResponse response=responseList.get(0);
                        if (response.getStatus()!=null) {
                            trip.setText("End Trip");
                            MarkerOptions markerOptions = new MarkerOptions();
                            destlat = Double.parseDouble(ApplicationConstants.destlatitude);
                            destlong = Double.parseDouble(ApplicationConstants.destlongitude);
                            markerOptions.position(new LatLng(destlat, destlong));
                            markerOptions.title("Destination");
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                            if (markerDest != null)
                                if (markerDest != null)
                                    markerDest.remove();
                            markerDest = mMap.addMarker(markerOptions);
                            updatemarkers = true;
                        } else if (response.getCode()!=null) {
                            DisplayToast(response.getDescription());
                        }
                        ApplicationConstants.tripflag = CHECKBOOKINGS;
                    }
                });
    }

    public void EndTrip(JsonObject jsonObject){

      //  StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(MyTrips.this).getrestadapter()
                .EndTrip(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<EndtripResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                     //  StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {

                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                          //  StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<EndtripResponse> responseList) {
                        EndtripResponse response=responseList.get(0);
                        if (response.getAmount()!=null) {
                            ApplicationConstants.tripflag = 0;
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

                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyTrips.this, R.style.Theme_AppCompat_DayNight_Dialog);
                            alertDialog.setCancelable(false);
                            alertDialog.setTitle("Payment Mode - " + paymentmethod);
                            alertDialog.setMessage("Amount - " + amount);
                            alertDialog.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            RatingBarDialogue cdd = new RatingBarDialogue(MyTrips.this);
                                            RatingBar ratebar = findViewById(R.id.dialog_ratingbar);
                                            EditText comm = findViewById(R.id.input_comments);
                                            ApplicationConstants.rating = ratebar.toString();
                                            ApplicationConstants.comments = comm.getText().toString();
                                            cdd.setCancelable(false);
                                            cdd.show();
                                        }
                                    });
                            alertDialog.show();
                        }
                    }
                });
    }

    public void TrackVehicle(JsonObject jsonObject){

      //  StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(MyTrips.this).getrestadapter()
                .TrackVehicle(jsonObject).delay(TIMEINTERVAL, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<TrackvehicleResponse>>() {
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
                        //    DisplayToast("Unable to Register");
                        //    StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<TrackvehicleResponse> response) {
                        TrackVehicle();
                       /* if(response.get(0).getCode()!=null){
                            Log.i("Driver status", response.get(0).getDescription());
                        }*/

                    }
                });
    }

    public void RateTheRide(JsonObject jsonObject){

       // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(MyTrips.this).getrestadapter()
                .DriverRatingToRide(jsonObject).timeout(TIMEINTERVAL, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverRateTheRideResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                      //  StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {

                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                          // StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DriverRateTheRideResponse> responseList) {
                        DriverRateTheRideResponse response=responseList.get(0);
                        ApplicationConstants.tripflag = 0;
                        if (line != null)
                            line.remove();
                        mMap.clear();
                        Intent intent = new Intent();
                        setResult(1, intent);
                        finish();
                    }
                });
    }
   /* class TripsRequest extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
                JSONArray jsonObj;
                JSONObject c = null;
                if (response.matches("")) {
                    Toast.makeText(getApplicationContext(), "error has occurred ", Toast.LENGTH_LONG).show();
                } else {
                    switch (ApplicationConstants.tripflag) {
                        case STARTTRIP:
                            Log.i("Driver status", "Start Trip Responce" + response);
                            jsonObj = new JSONArray(response);
                            c = jsonObj.getJSONObject(0);
                            if (c.has("Status")) {
                                trip.setText("End Trip");
                                MarkerOptions markerOptions = new MarkerOptions();
                                destlat = Double.parseDouble(ApplicationConstants.destlatitude);
                                destlong = Double.parseDouble(ApplicationConstants.destlongitude);
                                markerOptions.position(new LatLng(destlat, destlong));
                                markerOptions.title("Destination");
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                                if (markerDest != null)
                                    if (markerDest != null)
                                        markerDest.remove();
                                markerDest = mMap.addMarker(markerOptions);
                                updatemarkers = true;
                            } else if (c.has("Code")) {
                                Toast.makeText(getApplicationContext(), c.getString("description"), Toast.LENGTH_LONG).show();
                            }
                            ApplicationConstants.tripflag = CHECKBOOKINGS;
                            break;
                        case ENDTRIP:
                            Log.i("Driver status", "End Trip Responce" + response);
                            jsonObj = new JSONArray(response);
                            c = jsonObj.getJSONObject(0);
                            if (c.has("Amount")) {
                                ApplicationConstants.tripflag = 0;
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
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyTrips.this, R.style.Theme_AppCompat_DayNight_Dialog);
                                alertDialog.setCancelable(false);
                                alertDialog.setTitle("Payment Mode - " + paymentmethod);
                                alertDialog.setMessage("Amount - " + amount);
                                alertDialog.setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {

                                                RatingBarDialogue cdd = new RatingBarDialogue(MyTrips.this);
                                                cdd.setCancelable(false);
                                                cdd.show();
                                            }
                                        });


                                alertDialog.show();

                            }
                            break;
                        case CHECKBOOKINGS:
                            // jsonObj = new JSONArray(response);
                            Log.i("Driver status", "Checking Bookings");
                            *//*if (jsonObj.isNull(0)) {
                            } else {
                                c= jsonObj.getJSONObject(0);
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
                                TripRequest cdd = new TripRequest(MyTrips.this);
                                cdd.setCanceledOnTouchOutside(false);
                                cdd.show();
                            }*//*
                            // Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            break;
                        case ACCEPTBOOKING:
                            jsonObj = new JSONArray(response);
                            c = jsonObj.getJSONObject(0);
                            Log.i("Driver status", "Booking accepted" + ApplicationConstants.bookingId);
                            ApplicationConstants.customerMobileNo = c.getString("CustomerPhoneNo");
                            //Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            //startActivity(new Intent(getActivity(), MyTrips.class));
                            break;
                        case REJECTBOOKING:
                            Log.i("Driver status", "Booking rejected" + ApplicationConstants.bookingId);
                            ApplicationConstants.tripflag = CHECKBOOKINGS;
                            break;
                        case RATETHERIDE:
                            Log.i("Driver status", " Feedback response" + response);
                            ApplicationConstants.tripflag = 0;
                            timer.cancel();
                            if (line != null)
                                line.remove();
                            mMap.clear();
                            Intent intent = new Intent();
                            setResult(1, intent);
                            finish();
                            break;
                    }
                    //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
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
        JSONObject object = new JSONObject();
        URL url = null;
        try {
            switch (ApplicationConstants.tripflag) {
                case STARTTRIP:
                    Log.i("Driver status", " Start Trip");
                    object.put("BNo", ApplicationConstants.bNo);
                    object.put("DriverPhoneNo", ApplicationConstants.mobileNo);
                    object.put("BVerificationCode", otp);
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_starttrip));
                    break;
                case ENDTRIP:
                    Log.i("Driver status", " End Trip");
                    object.put("BNo", ApplicationConstants.bNo);
                    object.put("DriverPhoneNo", ApplicationConstants.mobileNo);
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_endtrip));
                    break;
                case PAYMENTS:
                    object.put("BNo", ApplicationConstants.bNo);
                    object.put("Amount", "320");
                    serverUrl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_process_payment);
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
                case RATETHERIDE:
                    Log.i("Driver status", " send  Feedback");
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_driver_ratingtoride));
                    object = new JSONObject();
                    object.put("DriverPhoneNo", ApplicationConstants.mobileNo);
                    object.put("BNo", ApplicationConstants.bNo);
                    object.put("DriverRating", ApplicationConstants.rating);
                    object.put("DriverRated", "2");
                    object.put("DriverComments", ApplicationConstants.comments);
                    break;
            }

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

   /* //drawing line between source and destination
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

    *//**
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
            markerOptions.position(new LatLng(latitude, longitude));
            markerOptions.title("Driver Location");
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_taxi));
            if (marker != null)
                marker.remove();
            marker = mMap.addMarker(markerOptions);
            builder.include(marker.getPosition());
            builder.include(markerDest.getPosition());
            LatLngBounds bounds = builder.build();
            int padding = 200; // offset from edges of the map in pixels
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.animateCamera(cu);

        }
    }*/



    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(MyTrips.this,text,Toast.LENGTH_SHORT);
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


