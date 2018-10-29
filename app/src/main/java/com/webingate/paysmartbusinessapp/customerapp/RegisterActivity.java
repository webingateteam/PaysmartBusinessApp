package com.webingate.paysmartbusinessapp.customerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.businessapp.Deo.RegisterUserResponse;
import com.webingate.paysmartbusinessapp.businessapp.Dialog.ProgressDialog;

import java.io.ByteArrayOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cropper.CropImage;
import cropper.CropImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
/*import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;*/

public class RegisterActivity extends FragmentActivity {


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
    @BindView(R.id.btn_register)
    AppCompatButton registerButton;



    private String response;
    private String serverurl;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordkey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String Profilepic = "profilepic";
    private boolean isprofilePic = false;
    Toast toast;
    ProgressDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(RegisterActivity.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String mobNo = prefs.getString(Phone, null);
        String emailOtp = prefs.getString(Emailotp, null);
        String mobileOtp = prefs.getString(Mobileotp, null);
        if (mobNo != null && (emailOtp != null || mobileOtp != null)) {
            startActivity(new Intent(RegisterActivity.this, VerificationActivity.class));
            finish();
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adapter);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstname.getText().toString().matches("") || lastname.getText().toString().matches("")
                        || email.getText().toString().matches("")
                        || mobileno.getText().toString().matches("") || password.getText().toString().matches("")
                        || repassword.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter details", Toast.LENGTH_SHORT).show();
                } else if (!password.getText().toString().matches(repassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password Not Matched", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isprofilePic) {
                        Toast.makeText(getApplicationContext(), "Upload Profile Picture", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JsonObject object = new JsonObject();
                    object.addProperty("flag", "I");
                    object.addProperty("Username", firstname.getText().toString());
                    object.addProperty("Email", email.getText().toString());
                    object.addProperty("Mobilenumber", mobileno.getText().toString());
                    object.addProperty("Password", password.getText().toString());
                    object.addProperty("Firstname", firstname.getText().toString());
                    object.addProperty("lastname", lastname.getText().toString());
                    object.addProperty("AuthTypeId", "2");
                    object.addProperty("AltPhonenumber", mobileno.getText().toString());
                    object.addProperty("Altemail", email.getText().toString());
                    object.addProperty("AccountNo", "");
                    object.addProperty("CountryId", getResources().getStringArray(R.array.country_type_id)[country.getSelectedItemPosition()]);
                    Bitmap bitmap = ((BitmapDrawable) profilepic.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    ApplicationConstants.driverimage = encodedImage;
                    object.addProperty("UserPhoto", "data:image/png;base64," + encodedImage);
                    RegisterUser(object);

                  /*  serverurl = getResources().getString(R.string.url_server) + getResources().getString(R.string.url_registeruser);
                    RegisterUserTask registerUserTask = new RegisterUserTask();
                    registerUserTask.execute();*/

                }
            }
        });

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity(null)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(RegisterActivity.this);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                profilepic.setImageURI(result.getUri());
                isprofilePic = true;
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void RegisterUser(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.businessapp.Utils.DataPrepare.get(RegisterActivity.this).getrestadapter()
                .RegisterUser(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RegisterUserResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            DisplayToast("Error");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<RegisterUserResponse> responselist) {
                        RegisterUserResponse response=responselist.get(0);
                        if (response.getCode()!=null) {
                            DisplayToast(response.getDescription());
                        } else {
                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(Name, response.getUsername());
                            editor.putString(Phone, response.getMobilenumber());
                            editor.putString(Email,response.getEmail());
                            editor.putString(Password, response.getPassword());
                            editor.putString(Emailotp, response.getEmailotp());
                            editor.putString(Mobileotp, response.getMobileotp());
                            editor.putString(Profilepic, ApplicationConstants.driverimage);
                            editor.commit();
                            ApplicationConstants.driverimage = null;
                            startActivity(new Intent(RegisterActivity.this, VerificationActivity.class));
                            finish();
                        }

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
   /* class RegisterUserTask extends AsyncTask<String, Void, String[]> {
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
                    // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    JSONArray jsonObj = new JSONArray(response);
                    // looping through All Contacts

                    JSONObject c = jsonObj.getJSONObject(0);


                    if (c.has("Code")) {
                        Toast.makeText(getApplicationContext(), c.getString("description"), Toast.LENGTH_LONG).show();
                        Log.i("Register", "Registered error " + c.getString("description"));
                    } else {
                        Log.i("Register", "Registered Successfully");
                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(Name, c.getString("Username"));
                        editor.putString(Phone, c.getString("Mobilenumber"));
                        editor.putString(Email, c.getString("Email"));
                        editor.putString(Password, c.getString("Password"));
                        editor.putString(Emailotp, c.getString("Emailotp"));
                        editor.putString(Mobileotp, c.getString("Mobileotp"));
                        editor.putString(Profilepic, ApplicationConstants.driverimage);
                        editor.commit();
                        ApplicationConstants.driverimage = null;
                        Toast.makeText(getApplicationContext(), "Thank you " + c.getString("Username"), Toast.LENGTH_LONG).show();
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
            Log.i("Register", "Rester Request sending");
            JSONObject object = new JSONObject();
            object.put("flag", "I");
            object.put("Username", firstname.getText().toString());
            object.put("Email", email.getText().toString());
            object.put("Mobilenumber", mobileno.getText().toString());
            object.put("Password", password.getText().toString());
            object.put("Firstname", firstname.getText().toString());
            object.put("lastname", lastname.getText().toString());
            object.put("AuthTypeId", "2");
            object.put("AltPhonenumber", mobileno.getText().toString());
            object.put("Altemail", email.getText().toString());
            object.put("AccountNo", "");
            object.put("CountryId", getResources().getStringArray(R.array.country_type_id)[country.getSelectedItemPosition()]);
            Bitmap bitmap = ((BitmapDrawable) profilepic.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            ApplicationConstants.driverimage = encodedImage;
            object.put("UserPhoto", "data:image/png;base64," + encodedImage);


            //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
            // Defined URL  where to send data
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
            //  e.printStackTrace();
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

    }*/
}
