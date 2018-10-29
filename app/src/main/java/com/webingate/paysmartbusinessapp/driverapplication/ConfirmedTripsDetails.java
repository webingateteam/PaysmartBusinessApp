package com.webingate.paysmartbusinessapp.driverapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;

public class ConfirmedTripsDetails extends AppCompatActivity implements OnMapReadyCallback {
    static GoogleMap mMap;
    Marker src, dest;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.driver_pic)
    ImageView driverPic;
    @BindView(R.id.driverdetails)
    TextView driverdetails;
    @BindView(R.id.comments)
    TextView comments;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.vehiclepic)
    ImageView vehiclepic;
    @BindView(R.id.vehicle_details)
    TextView vehicleDetails;
    @BindView(R.id.starttime)
    TextView starttime;
    @BindView(R.id.source)
    TextView source;
    @BindView(R.id.endtime)
    TextView endtime;
    @BindView(R.id.destination)
    TextView destination;
    @BindView(R.id.payment_method)
    TextView paymentMethod;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.payment_id)
    TextView paymentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_confirmed_trips_details);
        ButterKnife.bind(this);
        // adding toolbar
        setSupportActionBar(toolbar);
        // you can title and subtitle dynamically
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        driverdetails.setText(intent.getStringExtra("ddetails"));
        comments.setText(intent.getStringExtra("comments"));
        starttime.setText(intent.getStringExtra("starttime"));
        endtime.setText(intent.getStringExtra("endtime"));
        source.setText(intent.getStringExtra("src"));
        destination.setText(intent.getStringExtra("dest"));
        amount.setText(intent.getStringExtra("amount"));
        vehicleDetails.setText(intent.getStringExtra("vdetails"));
        String paymentmethod = intent.getStringExtra("paymenttypeid");
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
        paymentMethod.setText(paymentmethod);
        paymentId.setText(intent.getStringExtra("gatewattransid"));
        byte[] decodedString = Base64.decode(ApplicationConstants.vehiclepic.substring(ApplicationConstants.vehiclepic.indexOf(",") + 1), Base64.DEFAULT);
        Bitmap image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        vehiclepic.setImageBitmap(image);
        decodedString = Base64.decode(ApplicationConstants.driverpic.substring(ApplicationConstants.driverpic.indexOf(",") + 1), Base64.DEFAULT);
        image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        driverPic.setImageBitmap(image);
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
        LatLng latLng = new LatLng(Double.parseDouble(getIntent().getStringExtra("sourcelat")), Double.parseDouble(getIntent().getStringExtra("sourcelong")));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("source");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        src = mMap.addMarker(markerOptions);
        latLng = new LatLng(Double.parseDouble(getIntent().getStringExtra("destlat")), Double.parseDouble(getIntent().getStringExtra("destlong")));
        markerOptions.position(latLng);
        markerOptions.title("Destination");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        dest = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.5f));
    }
}
