package com.webingate.paysmartbusinessapp.driverapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RegisterDriverResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cropper.CropImage;
import cropper.CropImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
public class RegisterActivity extends FragmentActivity {
    boolean isProfilePicUploaded = false, isVehiclePicUploaded = false;

//    @BindView(R.id.fleetowner_url)
//    TextView fleetOwnerLink;
    @BindView(R.id.image_profile_pic)
    ImageView profilepic;
    @BindView(R.id.input_firstname)
    EditText firstname;
    @BindView(R.id.input_lastname)
    EditText lastname;
    @BindView(R.id.input_email)
    EditText email;
    @BindView(R.id.input_mobile)
    EditText mobileno;
    @BindView(R.id.input_password)
    EditText password;
    @BindView(R.id.input_repassword)
    EditText repassword;
    @BindView(R.id.spinner_country)
    Spinner country;
//    @BindView(R.id.checkbox_ownvehicle)
//    CheckBox vehicledetails;
//    @BindView(R.id.image_vehicle)
//    ImageView vehiclepic;
//    @BindView(R.id.input_vehicleregestration)
//    EditText vehiclenumber;
//    @BindView(R.id.spinner_vehiclgroup)
//    Spinner vehiclegroup;
//    @BindView(R.id.spinner_vehicletype)
//    Spinner vehicletype;
//    @BindView(R.id.table_vehicle)
    TableRow vehicle_tablerow;
    @BindView(R.id.btn_register)
    AppCompatButton loginButton;

    private String response;
    private String serverurl;
    private boolean isprofilePic = true;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordkey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String DRIVERID = "driverid";
    public static final String VEHICLEID = "vehicleid";

    Toast toast;
    ProgressDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(RegisterActivity.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.grey_3)
                .build();
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String mobNo = prefs.getString(Phone, null);
        String emailOtp = prefs.getString(Emailotp, null);
        String mobileOtp = prefs.getString(Mobileotp, null);
        if (mobNo != null && (emailOtp != null || mobileOtp != null)) {
            startActivity(new Intent(RegisterActivity.this, VerificationActivity.class));
            finish();
        }
        vehicle_tablerow.setVisibility(View.GONE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.vehicle_group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  //      vehiclegroup.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.vehicle_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //    vehicletype.setAdapter(adapter);

        View.OnClickListener buttononclicklistner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
//                    case R.id.checkbox_ownvehicle:
//                        if (vehicledetails.isChecked()) {
//                            vehicle_tablerow.setVisibility(View.VISIBLE);
//                        } else {
//                            vehicle_tablerow.setVisibility(View.GONE);
//                        }
//                        break;
                    case R.id.image_profile_pic:
                        isprofilePic = true;
                        CropImage.activity(null)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .start(RegisterActivity.this);
                        break;
//                    case R.id.image_vehicle:
//                        isprofilePic = false;
//                        CropImage.activity(null)
//                                .setGuidelines(CropImageView.Guidelines.ON)
//                                .start(RegisterActivity.this);
//                        break;
//                    case R.id.fleetowner_url:
//                        fleetOwnerLink.setTextColor(Color.parseColor("#FFFFFF"));
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.url_fleet_ownerrequest)));
//                        startActivity(browserIntent);
//                        break;
                }
            }
        };
    //    vehicledetails.setOnClickListener(buttononclicklistner);
    //    fleetOwnerLink.setOnClickListener(buttononclicklistner);
        profilepic.setOnClickListener(buttononclicklistner);
    //    vehiclepic.setOnClickListener(buttononclicklistner);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstname.getText().toString().matches("") || lastname.getText().toString().matches("")
                        || email.getText().toString().matches("")
                        || mobileno.getText().toString().matches("") || password.getText().toString().matches("")
                        || repassword.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter details", Toast.LENGTH_SHORT).show();
                } else if (!password.getText().toString().matches(repassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password Not Matched", Toast.LENGTH_SHORT).show();
                } else if (!isProfilePicUploaded) {
                    Toast.makeText(getApplicationContext(), "Please Select Profile Picture", Toast.LENGTH_SHORT).show();
                } else {
//                    if (vehicledetails.isChecked()) {
//                        if (!isVehiclePicUploaded) {
//                            Toast.makeText(getApplicationContext(), "Please Select Vehicle Picture", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        if (vehiclenumber.getText().toString().matches("")) {
//                            Toast.makeText(getApplicationContext(), "Please Enter Registration Number", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
                    }

                    JsonObject object = new JsonObject();
                    object.addProperty("flag", "I");
                    object.addProperty("Firstname", firstname.getText().toString());
                    object.addProperty("lastname", lastname.getText().toString());
                    object.addProperty("AuthTypeId", "2");
                    object.addProperty("Password", password.getText().toString());
                    object.addProperty("Mobilenumber", mobileno.getText().toString());
                    object.addProperty("Email", email.getText().toString());
                    object.addProperty("CountryId", getResources().getStringArray(R.array.country_type_id)[country.getSelectedItemPosition()]);
//                    if (vehicledetails.isChecked()) {
//                        object.addProperty("VehicleGroupId", getResources().getStringArray(R.array.vehicle_group_id)[vehiclegroup.getSelectedItemPosition()]);
//                        object.addProperty("RegistrationNo", vehiclenumber.getText().toString());
//                        object.addProperty("VehicleTypeId", getResources().getStringArray(R.array.vehicle_type_id)[vehicletype.getSelectedItemPosition()]);
//                        object.addProperty("isDriverOwned", "1");
//                    } else {
//                        object.addProperty("VehicleGroupId", "");
//                        object.addProperty("RegistrationNo", "");
//                        object.addProperty("VehicleTypeId", "");
//                        object.addProperty("isDriverOwned", "0");
//                    }
                    Bitmap bitmap = ((BitmapDrawable) profilepic.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    object.addProperty("DPhoto", "data:image/png;base64," + encodedImage);
//                    object.addProperty("bioMetricData", "null");
//                    if (vehicledetails.isChecked()) {
//                        bitmap = ((BitmapDrawable) vehiclepic.getDrawable()).getBitmap();
//                        baos = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                        imageBytes = baos.toByteArray();
//                        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//                        object.addProperty("VPhoto", "data:image/png;base64," + encodedImage);
//                    } else {
//                        object.addProperty("VPhoto", "");
//                    }
                    RegisterDriver(object);

                    /*serverurl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_register_drivers);
                    RegisterUserTask registerUserTask = new RegisterUserTask();
                    registerUserTask.execute();*/


                }
            });
        }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of CropImageActivity
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                if (isprofilePic) {
                    profilepic.setImageURI(result.getUri());
                    isProfilePicUploaded = true;
                } else {
                    //  vehiclepic.setImageURI(result.getUri());
                    isVehiclePicUploaded = true;
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    public void RegisterDriver(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(RegisterActivity.this).getrestadapter()
                .RegisterDriver(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RegisterDriverResponse>>() {
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
                    public void onNext(List<RegisterDriverResponse> responseList) {
                        RegisterDriverResponse response=responseList.get(0);
                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(Name, response.getName());
                        editor.putString(Phone, response.getPMobNo());
                        editor.putString(Email, response.getEmail());
                        editor.putString(Password, response.getPassword());
                        editor.putString(Mobileotp, response.getMobileotp());
                        editor.putString(Emailotp, response.getEmailotp());
                        editor.putString(DRIVERID, response.getDriverId());
                        editor.putString(VEHICLEID, response.getVehicleId());
                        editor.commit();
                        startActivity(new Intent(RegisterActivity.this, VerificationActivity.class));
                        finish();


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

    /*class RegisterUserTask extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(RegisterActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setTitle("Registration in process");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                RegisterRequest();
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
                    //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    JSONArray jsonObj = new JSONArray(response);
                    // looping through All Contacts

                    JSONObject c = jsonObj.getJSONObject(0);
                    if (c.has("Code")) {
                        Toast.makeText(getApplicationContext(), c.getString("description"), Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(Name, c.getString("Name"));
                        editor.putString(Phone, c.getString("PMobNo"));
                        editor.putString(Email, c.getString("Email"));
                        editor.putString(Password, c.getString("Password"));
                        editor.putString(Mobileotp, c.getString("Mobileotp"));
                        editor.putString(Emailotp, c.getString("Emailotp"));
                        editor.putString(DRIVERID, c.getString("DriverId"));
                        editor.putString(VEHICLEID, c.getString("VehicleId"));
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Otp " + c.getString("Mobileotp"), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, VerificationActivity.class));
                        finish();
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

    public void RegisterRequest() {
        BufferedReader reader = null;
        response = "";
        try {
            JSONObject object = new JSONObject();
            object.put("flag", "I");
            object.put("Firstname", firstname.getText().toString());
            object.put("lastname", lastname.getText().toString());
            object.put("AuthTypeId", "2");
            object.put("Password", password.getText().toString());
            object.put("Mobilenumber", mobileno.getText().toString());
            object.put("Email", email.getText().toString());
            object.put("CountryId", getResources().getStringArray(R.array.country_type_id)[country.getSelectedItemPosition()]);
            if (vehicledetails.isChecked()) {
                object.put("VehicleGroupId", getResources().getStringArray(R.array.vehicle_group_id)[vehiclegroup.getSelectedItemPosition()]);
                object.put("RegistrationNo", vehiclenumber.getText().toString());
                object.put("VehicleTypeId", getResources().getStringArray(R.array.vehicle_type_id)[vehicletype.getSelectedItemPosition()]);
                object.put("isDriverOwned", "1");
            } else {
                object.put("VehicleGroupId", "");
                object.put("RegistrationNo", "");
                object.put("VehicleTypeId", "");
                object.put("isDriverOwned", "0");
            }
            Bitmap bitmap = ((BitmapDrawable) profilepic.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            object.put("DPhoto", "data:image/png;base64," + encodedImage);
            object.put("bioMetricData", "null");
            if (vehicledetails.isChecked()) {
                bitmap = ((BitmapDrawable) vehiclepic.getDrawable()).getBitmap();
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                object.put("VPhoto", "data:image/png;base64," + encodedImage);
            } else {
                object.put("VPhoto", "");
            }
            URL url = new URL(serverurl);
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

            // Read Server DriverDetailsResponse
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }
            response = sb.toString();
        } catch (UnsupportedEncodingException e) {
            // response = response + e.getMessage();
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // response = response + e.getMessage();
            //e.printStackTrace();
        } catch (IOException e) {
            //response = response + e.getMessage();
        } catch (JSONException e) {
            //response = response + e.getMessage();
            //  e.printStackTrace();
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

    }*/

}
