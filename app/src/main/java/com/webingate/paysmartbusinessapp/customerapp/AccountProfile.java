package com.webingate.paysmartbusinessapp.customerapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
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
import com.webingate.paysmartbusinessapp.businessapp.Deo.UpdateUserResponse;


import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cropper.CropImage;
import cropper.CropImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.customerapp.Dialog.ProgressDialog;

/**
 * Created by Seshu on 9/18/2017.
 */

public class AccountProfile extends Activity {
    @BindView(R.id.image_profile_pic)
    ImageView profilePhoto;
    @BindView(R.id.edit_profile_photo)
    TextView editProfilePhoto;
    @BindView(R.id.input_name)
    EditText name;
    @BindView(R.id.input_email)
    EditText email;
    @BindView(R.id.input_mobile)
    EditText mobileNo;
    @BindView(R.id.input_dob)
    EditText dateOfBirth;
    @BindView(R.id.radio_male)
    RadioButton male;
    @BindView(R.id.radio_female)
    RadioButton female;
    @BindView(R.id.radio_gender)
    RadioGroup radioGender;
    @BindView(R.id.payment_spinner)
    Spinner paymentType;
    @BindView(R.id.btn_skip)
    Button skip;
    @BindView(R.id.btn_update)
    Button update;
    private DatePickerDialog DatePickerDialog;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordkey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String Dateofbirth = "dateofbirth";
    public static final String Gender = "gender";
    public static final String Paymenttype = "paymenttype";
    public static final String Profilepic = "profilepic";
    private String response;

    Toast toast;
    ProgressDialog dialog ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_profile);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(AccountProfile.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ApplicationConstants.email = prefs.getString(Email, null);
        ApplicationConstants.dateofbirth = prefs.getString(Dateofbirth, null);
        ApplicationConstants.gender = prefs.getString(Gender, null);
        ApplicationConstants.paymenttype = prefs.getString(Paymenttype, null);
        ApplicationConstants.profilepic = prefs.getString(Profilepic, null);

        name.setText(ApplicationConstants.username);
        email.setText(ApplicationConstants.email);
        mobileNo.setText(ApplicationConstants.mobileNo);
        dateOfBirth.setText(ApplicationConstants.dateofbirth);
        if (ApplicationConstants.profilepic != null && !ApplicationConstants.profilepic.matches("")) {
            profilePhoto.setImageURI(Uri.parse(ApplicationConstants.profilepic));
        }
        if (ApplicationConstants.gender != null && ApplicationConstants.gender.matches("female")) {
            male.setChecked(false);
            female.setChecked(true);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.payments_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentType.setAdapter(adapter);

        View.OnClickListener photoclOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.edit_profile_photo:
                        CropImage.activity(null)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .start(AccountProfile.this);
                        break;
                    case R.id.btn_skip:
                        startActivity(new Intent(AccountProfile.this, HomeActivity1.class));
                        finish();
                        break;
                    case R.id.btn_update:
                        if (name.getText().toString().matches("") || email.getText().toString().matches("") || mobileNo.getText().toString().matches("")
                                || dateOfBirth.getText().toString().matches("") || ApplicationConstants.profilepic.matches("")) {
                            Toast.makeText(getApplicationContext(), "Please Fill All Details", Toast.LENGTH_SHORT).show();
                        } else {
                            //encode image to base64 string
                            Bitmap bitmap = ((BitmapDrawable) profilePhoto.getDrawable()).getBitmap();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] imageBytes = baos.toByteArray();
                            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                            // email.setText(encodedImage);
                            // Toast.makeText(getApplicationContext(), encodedImage, Toast.LENGTH_LONG).show();
                            SharedPreferences sharedPref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(Email, email.getText().toString());
                            editor.putString(Dateofbirth, dateOfBirth.getText().toString());
                            editor.putString(Profilepic, ApplicationConstants.profilepic);
                            editor.commit();

                            startActivity(new Intent(AccountProfile.this, HomeActivity1.class));
                            finish();
                        }
                        break;
                    case R.id.input_dob:
                        DatePickerDialog.show();
                        break;
                }
            }
        };
        editProfilePhoto.setOnClickListener(photoclOnClickListener);
        skip.setOnClickListener(photoclOnClickListener);
        update.setOnClickListener(photoclOnClickListener);
        dateOfBirth.setOnClickListener(photoclOnClickListener);
        setDateTimeField();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                profilePhoto.setImageURI(result.getUri());
                ApplicationConstants.profilepic = result.getUri().toString();

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        final int year = newCalendar.get(Calendar.YEAR), month = newCalendar.get(Calendar.MONTH), day = newCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog = new DatePickerDialog(AccountProfile.this, R.style.Dialog_Theme, new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(selectedYear, selectedMonth, selectedDay);
                dateOfBirth.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
            }

        }, year, month, day);
    }

    public void UpdateAppUser(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.customerapp.Utils.DataPrepare.get(AccountProfile.this).getrestadapter()
                .UpdateAppUser(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UpdateUserResponse>>() {
                    @Override
                    public void onCompleted() {
                      //  DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Update");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<UpdateUserResponse> responselist) {



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


   /* class UpdateProfile extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getApplicationContext());
            dialog.setMessage("Please Wait...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                BalanceRequest();
                return new String[]{"Success"};
            } catch (Exception e) {
                return new String[]{"error"};
            }
        }

        @Override
        protected void onPostExecute(String... result) {
            try {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();


                if (dialog.isShowing())
                    dialog.dismiss();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void BalanceRequest() {
        BufferedReader reader = null;
        response = "";
        URL url = null;
        JSONObject object = null;
        try {
            url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_uadate_appuser));
            object = new JSONObject();
            object.put("Id", 1);
            object.put("flag", "U");
            object.put("Username", name.getText().toString());
            object.put("Email", email.getText().toString());
            object.put("Mobilenumber", mobileNo.getText().toString());
            object.put("Password", "12345");
            object.put("Mobileotp", "236236");
            object.put("Emailotp", "456456");
            object.put("Passwordotp", "786786");
            object.put("Firstname", "Rajendra");
            object.put("lastname", "prasad");
            object.put("AuthTypeId", 58);
            object.put("AltPhonenumber", mobileNo.getText().toString());
            object.put("Altemail", email.getText().toString());
            object.put("AccountNo", "014455652");
            object.put("Amount", 200000);
            object.put("UserPhoto", "");
            object.put("Gender", "1");
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Host", "android.schoolportal.gr");
            conn.setDoOutput(true);
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
            e.printStackTrace();
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

    }*/


}
