package com.webingate.paysmartbusinessapp.driverapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsTableItem;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RegisterDriverResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

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
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cropper.CropImage;
import cropper.CropImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Seshu on 9/18/2017.
 */

public class AccountProfile extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_profile_pic)
    ImageView imageProfilePic;
    @BindView(R.id.input_name)
    TextView firstName;
    @BindView(R.id.input_mobile)
    TextView inputMobile;
    @BindView(R.id.input_firstname)
    EditText inputFirstname;
    @BindView(R.id.input_lastname)
    EditText lastName;
    @BindView(R.id.input_email)
    EditText email;
    @BindView(R.id.input_dob)
    EditText inputDateofbirth;
  //  @BindView(R.id.input_address)
  //  EditText address;
   // @BindView(R.id.input_pincode)
    EditText pincode;
    @BindView(R.id.radio_male)
    RadioButton radioMale;
    @BindView(R.id.radio_female)
    RadioButton radioFemale;
    @BindView(R.id.radio_gender)
    RadioGroup radioGender;
    //@BindView(R.id.textview_country)
    //TextView textviewCountry;
   // @BindView(R.id.country)
   // Spinner country;
   // @BindView(R.id.textview_currency)
    //TextView textviewCurrency;
   // @BindView(R.id.currency)
    Spinner currency;
    @BindView(R.id.btn_update)
    AppCompatButton btnUpdateProfile;
    private DatePickerDialog DatePickerDialog;

    private String response;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordkey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String Dateofbirth = "dateofbirth";
    public static final String Gender = "gender";
    Toast toast;
    ProgressDialog dialog ;
    DriverDetailsTableItem driver;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_profile);
        ButterKnife.bind(this);
        driver= (DriverDetailsTableItem) getIntent().getSerializableExtra("details");
        dialog =  new ProgressDialog.Builder(AccountProfile.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.grey_3)
                .build();
        // adding toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ApplicationConstants.email = prefs.getString(Email, null);
        ApplicationConstants.dateofbirth = prefs.getString(Dateofbirth, null);
        ApplicationConstants.gender = prefs.getString(Gender, null);
        firstName.setText(driver.getNAme());
        inputMobile.setText(driver.getPMobNo());
        inputDateofbirth.setText(driver.getDOB());
        email.setText(prefs.getString(Email, null));
        firstName.setText(driver.getFirstname());
        lastName.setText(driver.getLastname());
      //  address.setText(driver.getAddress());
        pincode.setText(driver.getPin());
        byte[] decodedString = Base64.decode(driver.getPhoto().substring(driver.getPhoto().indexOf(",") + 1), Base64.DEFAULT);
        Bitmap image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageProfilePic.setImageBitmap(image);
        ArrayAdapter<CharSequence> countryadapter = ArrayAdapter.createFromResource(this, R.array.country_type, android.R.layout.simple_spinner_item);
        countryadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //country.setAdapter(countryadapter);

       // currency = (Spinner) findViewById(R.id.currency);
        ArrayAdapter<CharSequence> currencyadapter = ArrayAdapter.createFromResource(this, R.array.currency_type, android.R.layout.simple_spinner_item);
        currencyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currency.setAdapter(currencyadapter);
        setDateTimeField();



    }

    @OnClick({R.id.image_profile_pic,R.id.btn_update,R.id.input_dob})
    public void OnClick (View v){
            switch (v.getId()) {
                case R.id.image_profile_pic:
                    CropImage.activity(null)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(AccountProfile.this);
                    break;
                case R.id.input_dob:
                    DatePickerDialog.show();
                    break;
                case R.id.btn_update:
                    JsonObject object = new JsonObject();
                    object.addProperty("flag", "I");
                    object.addProperty("Company", "Zambia");
                    object.addProperty("DId", ApplicationConstants.driverId);
                    object.addProperty("NAme", ApplicationConstants.username);
                  //  object.addProperty("Address", address.getText().toString());
                   // object.addProperty("PermanentAddress", address.getText().toString());
                    object.addProperty("Pin", pincode.getText().toString());
                    object.addProperty("PermanentPin", pincode.getText().toString());
                    object.addProperty("Mobilenumber", ApplicationConstants.mobileNo);
                    object.addProperty("FirstName", firstName.getText().toString());
                    object.addProperty("LastName", lastName.getText().toString());
                    object.addProperty("EmailId", email.getText().toString());
                    object.addProperty("drivercode", "");
                    Bitmap bitmap = ((BitmapDrawable) imageProfilePic.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    object.addProperty("Photo", "data:image/png;base64," + encodedImage);
                  //  object.addProperty("CurrentStateId", getResources().getStringArray(R.array.country_type_id)[country.getSelectedItemPosition()]);
                    UpdatedriverProfile(object);


                    /*UpdateProfileTask updateProfileTask = new UpdateProfileTask();
                    updateProfileTask.execute();*/
                    break;
            }
        }


    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        final int year = newCalendar.get(Calendar.YEAR), month = newCalendar.get(Calendar.MONTH), day = newCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog = new DatePickerDialog(AccountProfile.this, R.style.Dialog_Theme, new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(selectedYear, selectedMonth, selectedDay);
                inputDateofbirth.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
            }

        }, year, month, day);
    }

    /* protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
         super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
         switch(requestCode) {
             case 2:
                 if(resultCode == RESULT_OK){
                     Uri selectedImage = imageReturnedIntent.getData();
                     profilePhoto.setImageURI(selectedImage);
                 }
                 break;
             case 1:
                 if(resultCode == RESULT_OK){
                     Uri selectedImage = imageReturnedIntent.getData();
                     profilePhoto.setImageURI(selectedImage);
                 }
                 break;
         }
     }*/
    //toolbar button click
    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        onBackPressed();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ((ImageView) findViewById(R.id.image_profile_pic)).setImageURI(result.getUri());
                //   Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void UpdatedriverProfile(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(AccountProfile.this).getrestadapter()
                .DriverMaster(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DrivermasterResponse>>() {
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
                    public void onNext(List<DrivermasterResponse> responselist) {
                        //DisplayToast(response.toString());


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





   /* class UpdateProfileTask extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(AccountProfile.this);

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
                UpdateProfileProcess();
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
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void UpdateProfileProcess() {
        BufferedReader reader = null;
        response = "";
        try {
            JSONObject object = new JSONObject();
            object.put("flag", "I");
            object.put("Company", "Zambia");
            object.put("DId", ApplicationConstants.driverId);
            object.put("NAme", ApplicationConstants.username);
            object.put("Address", address.getText().toString());
            object.put("PermanentAddress", address.getText().toString());
            object.put("Pin", pincode.getText().toString());
            object.put("PermanentPin", pincode.getText().toString());
            object.put("Mobilenumber", ApplicationConstants.mobileNo);
            object.put("FirstName", firstName.getText().toString());
            object.put("LastName", lastName.getText().toString());
            object.put("EmailId", email.getText().toString());
            object.put("drivercode", "");
            Bitmap bitmap = ((BitmapDrawable) imageProfilePic.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            object.put("Photo", "data:image/png;base64," + encodedImage);
            object.put("CurrentStateId", getResources().getStringArray(R.array.country_type_id)[country.getSelectedItemPosition()]);
            URL url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_driver_profile));
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
}
