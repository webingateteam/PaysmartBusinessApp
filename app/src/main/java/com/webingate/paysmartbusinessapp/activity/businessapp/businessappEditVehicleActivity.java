package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleCreationResponce;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverDocsFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverUserInfoFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppVehicleDocsFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppVehicleEditInfoFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppVehicleInfoFragment;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import cropper.CropImage;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessappEditVehicleActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String RegistrationNo = "RegistrationNoKey";

    private int position = 1;
    private int maxPosition = 5;
    private Button nextButton, prevButton;
    private TextView imageNoTextView;
    ImageView profileImageView;



    EditText RegNo;
    EditText chasisno;
    EditText engineno;
    Spinner vgroup;
    Spinner vtype;
    EditText modelyear;
    EditText state;
    Toast toast;

    businessAppVehicleEditInfoFragment userInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_editvehicle_activity);

        initData();

        initUI();

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

    }

    private void initUI() {
        initToolbar();


        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        imageNoTextView = findViewById(R.id.imageNoTextView);
//        if(ApplicationConstants.registrationNo!=null){
//        RegNo = findViewById(R.id.s_Regno);
//        RegNo.setText(ApplicationConstants.registrationNo);
//        }


        updatePositionTextView();
        setupFragment(new businessAppVehicleEditInfoFragment());

//        profileImageView = findViewById(R.id.profileImageView);
//        int id = R.drawable.profile2;
//        Utils.setCornerRadiusImageToImageView(getApplicationContext(), profileImageView, id, 20, 2,  R.color.md_white_1000);

        //ImageView coverUserImageView = findViewById(R.id.coverUserImageView);
        //Utils.setImageToImageView(getApplicationContext(), coverUserImageView, id);

//        emailTextView = findViewById(R.id.emailTextView);
//        phoneTextView = findViewById(R.id.phoneTextView);
//        websiteTextView = findViewById(R.id.websiteTextView);

        //editFAB = findViewById(R.id.editFAB);

    }

    private void updatePositionTextView() {
        imageNoTextView.setText(position + " of " + maxPosition);
    }

    private void setupFragment(Fragment fragment) {
        try {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentLayout, fragment)
                    .commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initActions() {

        nextButton.setOnClickListener(v -> {

            if (position < maxPosition) {
                position++;



                updatePositionTextView();
                if(position == 1) {
                   // Toast.makeText(this, "Step 1.", Toast.LENGTH_SHORT).show();
                    userInfoFragment =  new businessAppVehicleEditInfoFragment();

                    setupFragment(userInfoFragment);
//                    RegNo = findViewById(R.id.s_Regno);
//                    RegNo.setText(ApplicationConstants.registrationNo);

                }
                if(position == 2)
                {
                    //EditText name = (EditText)findViewById(R.id.s_name);
                    RegNo = findViewById(R.id.s_Regno);
                    chasisno = findViewById(R.id.s_chasisno);
                    engineno = findViewById(R.id.s_engineno);
                    vgroup = findViewById(R.id.s_vgroup);
                    vtype = findViewById(R.id.s_vtype);
                    modelyear = findViewById(R.id.s_modelyear);
                    state = findViewById(R.id.s_state);

                    JsonObject object = new JsonObject();
                    object.addProperty("flag", "U");
                    object.addProperty("Id",Integer.parseInt(ApplicationConstants.Vid));
                    object.addProperty("VID", "");
                    object.addProperty("CompanyId", "");
                    object.addProperty("RegistrationNo",RegNo.getText().toString());
                    object.addProperty("ChasisNo",chasisno.getText().toString());
                    object.addProperty("Engineno",engineno.getText().toString());
                    object.addProperty("FleetOwnerCode","");
                    object.addProperty("VehicleTypeId",ApplicationConstants.vtype);
                    object.addProperty("VehicleModelId","");
                    object.addProperty("VehicleGroupId",ApplicationConstants.vgrp);
                    object.addProperty("ModelYear",modelyear.getText().toString());
                    object.addProperty("Photo", "data:" + ApplicationConstants.document_vformat + ";base64," +  ApplicationConstants.vpicdata);
                    object.addProperty("VehicleCode","12345");
                    object.addProperty("CountryId",ApplicationConstants.countryid);
                    object.addProperty("change","2");
                    object.addProperty("type","1");
                    VehicleCreation(object);

                    //Toast.makeText(this, "Step 2.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppVehicleDocsFragment());
                }


                if(position == 3) {
                    //Toast.makeText(this, "Step 3.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppVehicleInfoFragment());
                }

            } else {
                Toast.makeText(this, "No More Step.", Toast.LENGTH_SHORT).show();
            }
        });

        prevButton.setOnClickListener(v -> {

            if (position > 1) {
                position--;

                updatePositionTextView();
                if(position == 1) {
                    //Toast.makeText(this, "Step 1.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppVehicleInfoFragment());
                }
                if(position == 2)
                {
                   // Toast.makeText(this, "Step 2.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverDocsFragment());
                }


                if(position == 3) {
                    //Toast.makeText(this, "Step 3.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppDriverUserInfoFragment());
                }

            } else {
                Toast.makeText(this, "No More Step.", Toast.LENGTH_SHORT).show();
            }
        });
//
    }

    public void VehicleCreation(JsonObject jsonObject){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(businessappEditVehicleActivity.this).getrestadapter()
                .VehicleCreationverification(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<VehicleCreationResponce>>() {
                    @Override
                    public void onCompleted() {
                       // DisplayToast("Vehicle Created Successfully");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<VehicleCreationResponce> responseList) {
//                        DisplayToast("Successfully onNext");
                        VehicleCreationResponce response=responseList.get(0);
                        if(response.getCode()!=null){
                            DisplayToast(response.getdescription());
                        }else {
                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(RegistrationNo, response.getRegistrationNo());
                            editor.commit();
//                             startActivity(new Intent(businessappEditVehicleActivity.this, businessappVehicleListActivity.class));
//                            finish();
                        }
                    }
                });
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Edit Vehicle");

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Bitmap bitmap=null;
            if (resultCode == -1) {
                try {
                    Uri uri = result.getUri();
                    Uri uri1=data.getData();
                    bitmap = BitmapFactory.decodeFile(uri.getPath());
                    ApplicationConstants.document_vformat = "image/jpeg";

                    //getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    inputStream.close();
                    String encodedImage = Base64.encodeToString(stringBuilder.toString().getBytes(), Base64.DEFAULT);
//                    ApplicationConstants.pic_data = encodedImage;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    ApplicationConstants.vpicdata = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG)
                            .show();

                    Toast.makeText(this, "Cropping successful, URI: " + result.getUri(), Toast.LENGTH_LONG)
                            .show();
//                ephoto=(ImageView) findViewById(R.id.Edituserphoto);
//                ephoto.setImageURI(result.getUri());

                    profileImageView = findViewById(R.id.profileImageView);
                    //ephoto.setImageURI(result.getUri());
                    profileImageView.setImageBitmap(bitmap);
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }


        }
    }
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast= Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }
    //endregion
}
