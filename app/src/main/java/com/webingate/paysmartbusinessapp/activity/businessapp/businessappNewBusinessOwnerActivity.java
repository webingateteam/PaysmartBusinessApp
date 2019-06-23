package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.RegisterBusinessUsers;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppBusinessOwnerInfoFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverDocsListFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppDriverUserInfoFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppEditBusinessOwnerInfoFragment;
import com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppUploadDocsFragment;
import com.webingate.paysmartbusinessapp.utils.Utils;

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

public class businessappNewBusinessOwnerActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Phone = "phoneKey";

    private int position = 1;
    private int maxPosition = 5;
    private Button nextButton, prevButton;
    private TextView imageNoTextView;

    ImageView profileImageView;



    EditText email;
    EditText name;
    EditText address;

    EditText city;

    EditText mno;

    EditText postal;
    EditText state;
    Toast toast;
    RegisterBusinessUsers rlist;
    businessAppBusinessOwnerInfoFragment userInfoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_newbusinessowner_activity);

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


        updatePositionTextView();
        setupFragment(new businessAppBusinessOwnerInfoFragment());

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
                    Toast.makeText(this, "Step 1.", Toast.LENGTH_SHORT).show();
                    userInfoFragment =      new businessAppBusinessOwnerInfoFragment();

                    setupFragment(userInfoFragment);

                }
                if(position == 2)
                {
                    //EditText name = (EditText)findViewById(R.id.s_name);
                    name = findViewById(R.id.s_name);
                    email = findViewById(R.id.s_email);
                    mno = findViewById(R.id.s_mobileno);
                    address = findViewById(R.id.s_address);
                    city = findViewById(R.id.s_city);
                    postal = findViewById(R.id.s_postal);
                    state = findViewById(R.id.s_state);
                    profileImageView = findViewById(R.id.profileImageView);

                    JsonObject object = new JsonObject();
                    object.addProperty("flag", "I");
                    object.addProperty("Firstname",name.getText().toString());
                    //object.addProperty("lastname","kumar");
                    object.addProperty("AuthTypeId", "");
                    object.addProperty("Password", "123");
                    object.addProperty("Mobilenumber",mno.getText().toString());
                    object.addProperty("Email",email.getText().toString());
                    object.addProperty("CountryId","91");
                    object.addProperty("VehicleGroupId","");
                    object.addProperty("UserAccountNo","15191"+mno.getText().toString());
                    object.addProperty("usertypeid","151");
                    object.addProperty("isDriverOwned","0");
                    object.addProperty("DPhoto","data:" + ApplicationConstants.document_format + ";base64," +  ApplicationConstants.picdata);
                    RegisterDriver(object);

                    Toast.makeText(this, "Step 2.", Toast.LENGTH_SHORT).show();
                    //setupFragment(new businessAppUploadDocsFragment());
                    //setupFragment(new businessAppDocCheckingFragment());
                    setupFragment(new businessAppDriverDocsListFragment());

                }


                if(position == 3) {
                    Toast.makeText(this, "Step 3.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppBusinessOwnerInfoFragment());
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
                    Toast.makeText(this, "Step 1.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppBusinessOwnerInfoFragment());
                }
                if(position == 2)
                {
                    Toast.makeText(this, "Step 2.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppUploadDocsFragment());
                }


                if(position == 3) {
                    Toast.makeText(this, "Step 3.", Toast.LENGTH_SHORT).show();
                    setupFragment(new businessAppBusinessOwnerInfoFragment());
                }

            } else {
                Toast.makeText(this, "No More Step.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RegisterDriver(JsonObject jsonObject){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(businessappNewBusinessOwnerActivity.this).getrestadapter()
                .Savebusinessappusers(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RegisterBusinessUsers>>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully onCompleted");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            DisplayToast("Successfully onError");
                            //DisplayToast("Unable to Register");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<RegisterBusinessUsers> responseList) {
//                        DisplayToast("Successfully onNext");
                        RegisterBusinessUsers response=responseList.get(0);
                        if(response.getCode()!=null){
                            DisplayToast(response.getDescription());
                        }else {
                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            //editor.putString(Username, response.getusername());
//                            Intent intent = new Intent(businessappNewDriverActivity.this, customerEOTPVerificationActivity.class);
//                            intent.putExtra("eotp", response.getemailotp());
//                            intent.putExtra("uid", response.getusreid());
//                            intent.putExtra("email", response.getemail());
//                            intent.putExtra("username", response.getusreid());
//                            intent.putExtra("motp", response.getmotp());
//                            intent.putExtra("mno", response.getmnumber());
//                            startActivity(intent);
//                        editor.putString(Phone, response.getPMobNo());
//                        editor.putString(Email, response.getEmail());
//                        editor.putString(Password, response.getPassword());
//                        editor.putString(Mobileotp, response.getMobileotp());
//                        editor.putString(Emailotp, response.getEmailotp());
//                        editor.putString(DRIVERID, response.getDriverId());
//                        editor.putString(VEHICLEID, response.getVehicleId());
                            List<RegisterBusinessUsers> rlist=responseList;
                            editor.commit();

                            // startActivity(new Intent(customerSignUpActivity.this, customerEOTPVerificationActivity.class));
                            finish();
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

        toolbar.setTitle("New Business Owner");

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
                    ApplicationConstants.document_format = "image/jpeg";

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
                    ApplicationConstants.picdata = Base64.encodeToString(imageBytes, Base64.DEFAULT);
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
