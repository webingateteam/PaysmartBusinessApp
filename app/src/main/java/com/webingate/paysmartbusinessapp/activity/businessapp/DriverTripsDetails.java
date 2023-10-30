package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.businessapp.Deo.CustomerRideDetailsResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DriverTripsDetails extends AppCompatActivity implements OnMapReadyCallback {
    static GoogleMap mMap;
    Marker src, dest;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.map_source)
    TextView mapSource;
    @BindView(R.id.source_gps_location)
    Button sourceGpsLocation;
    @BindView(R.id.table_row)
    TableRow tableRow;
    @BindView(R.id.map_destination)
    TextView mapDestination;

    @BindView(R.id.driver_pic)
    ImageView driverPic;
    @BindView(R.id.driverdetails)
    TextView driverdetails;
    @BindView(R.id.comments)
    TextView comment;
    @BindView(R.id.ratingBar)
    RatingBar rating;
    @BindView(R.id.vehiclepic)
    ImageView vehiclePic;
    @BindView(R.id.vehicle_details)
    TextView vehicleDetails;
    @BindView(R.id.starttime)
    TextView startTime;
    @BindView(R.id.source)
    TextView source;
    @BindView(R.id.endtime)
    TextView endTime;
    @BindView(R.id.destination)
    TextView destination;
    @BindView(R.id.payment_method)
    TextView paymentMethod;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.payment_id)
    TextView paymentId;
    CustomerRideDetailsResponse tripdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_confirmed_trips_details);
        ButterKnife.bind(this);
        Intent tripdetails = getIntent();

       // tripdetails= (CustomerRideDetailsResponse) getIntent().getSerializableExtra("details");
        // adding toolbar
        setSupportActionBar(toolbar);
        // you can title and subtitle dynamically
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapDestination.setVisibility(View.GONE);
        tableRow.setVisibility(View.GONE);
        driverdetails.setText(tripdetails.getStringExtra("id"));
        comment.setText(tripdetails.getStringExtra("comment"));
        startTime.setText(tripdetails.getStringExtra("startTime"));
        endTime.setText(tripdetails.getStringExtra("endTime"));
        source.setText(tripdetails.getStringExtra("source"));
        destination.setText(tripdetails.getStringExtra("destination"));
        amount.setText(tripdetails.getStringExtra("amount"));
        //vehicleDetails.setText(tripdetails.getRegistrationNo());
//        String paymentmethod = tripdetails.getPaymentModeId();
//        switch (paymentmethod) {
//            case "90":
//                paymentmethod = "Cash";
//                break;
//            case "91":
//                paymentmethod = "Net Banking";
//                break;
//            case "93":
//                paymentmethod = "E-Wallet";
//                break;
//            case "122":
//                paymentmethod = "Credit Card";
//                break;
//            case "123":
//                paymentmethod = "Debit Card";
//                break;
//        }
//        paymentMethod.setText(paymentmethod);
        paymentMethod.setText("Cash");
       // paymentId.setText(tripdetails.getGatewayTransId());
        //byte[] decodedString = Base64.decode(tripdetails.getVPhoto().substring(tripdetails.getVPhoto().indexOf(",") + 1), Base64.DEFAULT);
        //Bitmap image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        //vehiclePic.setImageBitmap(image);
        //decodedString = Base64.decode(tripdetails.getDPhoto().substring(tripdetails.getDPhoto().indexOf(",") + 1), Base64.DEFAULT);
        //image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
      //  driverPic.setImageBitmap(image);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(Double.parseDouble(getIntent().getStringExtra("srclat")), Double.parseDouble(getIntent().getStringExtra("srclogt")));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("source");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        if (src != null)
            src.remove();
        src = mMap.addMarker(markerOptions);
        latLng = new LatLng(Double.parseDouble(getIntent().getStringExtra("destlat")), Double.parseDouble(getIntent().getStringExtra("destlogt")));
        markerOptions.position(latLng);
        markerOptions.title("Destination");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        if (dest != null)
            dest.remove();
        dest = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.5f));

    }
}
