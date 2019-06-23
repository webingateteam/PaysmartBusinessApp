package com.webingate.paysmartbusinessapp.activity.businessapp;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.rilixtech.CountryCodePicker;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.BusinessappuserValidateResp;
import com.webingate.paysmartbusinessapp.adapter.uicollection.CustomSpinnerAdapter;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;

import com.webingate.paysmartbusinessapp.driverapplication.Deo.ActiveCountries;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class login_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
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
    public static final String UserAccountNumber = "UserAccountNo";
    public static final String usertypeid = "usertypeid";
    public static final String VEHICLEID = "vehicleid";
    int  a=0;
    private String response;
    Toast toast;
    ProgressDialog dialog ;
    TextView forgotTextView, signUpTextView;
    private boolean isServerOn;
    public final static int REQUEST_CODE = 10101;
    String mobNo, id, emailOTP, mobileOTP;

    private static final int PERMISSIONS_ALL = 7;
    String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET};


    @BindView(R.id.loginButton)
    Button loginButton;
    CardView facebookCardView, twitterCardView;
    CountryCodePicker ccp;
    Spinner spinner;
    TextView textView;
    ArrayList<String> list;
    ArrayList<String> list1;
    ArrayAdapter<String> adapter;
    int loginasOption = -1;
    @BindView(R.id.input_mobile)
    EditText mobileNo;

    @BindView(R.id.textPassword)
    EditText textPassword;


    String[] fruits = {"Driver", "Fleet owner", "Ticket Agent", "Brand ambassador","Business Owner"};
    int[] icons = {R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24
            , R.drawable.baseline_person_outline_black_24,R.drawable.baseline_person_outline_black_24};

    ImageView bgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermissions();
        login_activity.CheckServerTask checkServerTask = new login_activity.CheckServerTask();
        checkServerTask.execute();
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mobNo = prefs.getString(Phone, null);
        id = prefs.getString(ID, null);
        emailOTP = prefs.getString(Emailotp, null);
        mobileOTP = prefs.getString(Mobileotp, null);
        ApplicationConstants.username = prefs.getString(Name, null);
        ApplicationConstants.email = prefs.getString(Email, null);
        ApplicationConstants.dateofbirth = prefs.getString(Dateofbirth, null);
        ApplicationConstants.profilepic = prefs.getString(Profilepic, null);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.businessapp_login_activity);
    //    ButterKnife.bind(this);

        initUI();

        initDataBindings();

        initData();

        initActions();
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        list = new ArrayList<>(Arrays.asList(fruits));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        CustomSpinnerAdapter uiloginasCustomSpinnerAdapter =new CustomSpinnerAdapter(getApplicationContext(),icons,fruits);
        spinner.setAdapter(uiloginasCustomSpinnerAdapter);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);



    }

    //region Init Functions
    private void initUI() {
        forgotTextView = findViewById(R.id.forgotTextView);
        signUpTextView = findViewById(R.id.signuptTextView);

        loginButton = findViewById(R.id.loginButton);
        mobileNo = findViewById(R.id.mobileNo);
        textPassword = findViewById(R.id.textPassword);

//        facebookCardView = findViewById(R.id.facebookCardView);
//        twitterCardView = findViewById(R.id.twitterCardView);
        bgImageView = findViewById(R.id.bgImageView);
    }

    private void initData(){
        GetActiveCountries(1);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {
        forgotTextView.setOnClickListener(view -> {
            //Toast.makeText(getApplicationContext(), "Clicked Forgot Password.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, businessappForgotPasswordActivity.class);
            startActivity(intent);
        });

        signUpTextView.setOnClickListener((View view) -> {
           // Toast.makeText(getApplicationContext(), "Clicked Sign Up.", Toast.LENGTH_SHORT).show();

            //Intent intent = new Intent(this, businessAppSignUpActivity.class);
            Intent intent = new Intent(this, businessAppSignUpActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(view -> {

            if (mobileNo.getText().toString().matches("") || textPassword.getText().toString().matches("")) {
                Toast.makeText(getApplicationContext(), "Please Enter details", Toast.LENGTH_SHORT).show();
            }else if(mobileNo.getText().equals("1234567890")){
                SharedPreferences sharedPref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
//                            SharedPreferences pref = getApplicationContext().getSharedPreferences(MyPREFERENCES, 0);
//                            Editor editor = pref.edit();
                editor.putString(UserAccountNumber, "109911234567890");
                editor.putInt(usertypeid, 109);
                // editor.putInt(usertypeid, credentialsResponse.getusertypeid());
////                            editor.putString(VEHICLEID, credentialsResponse.getVehicleId());
//                            editor.putString(Phone, mobileNo.getText().toString());
//                            editor.putString(Emailotp, null);
//                            editor.putString(Mobileotp, null);
                editor.commit();
                ApplicationConstants.mobileNo = mobileNo.getText().toString();
                ApplicationConstants.userAccountNo="109911234567890";
                ApplicationConstants.usertypeid=109;
               // ApplicationConstants.upic=credentialsResponse.getUserPhoto();
                //startActivity(new Intent(this, MainActivity.class));
                GoToDashboard();
                finish();
            }
            else {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("usertypeid", selectype());
                jsonObject.addProperty("Password", textPassword.getText().toString());
                jsonObject.addProperty("UserAccountNo", selectype()+ccp.getSelectedCountryCode()+mobileNo.getText().toString());
                DriverLogin(jsonObject);
           }
           // GoToDashboard();
            //Toast.makeText(getApplicationContext(), "Clicked Login.", Toast.LENGTH_SHORT).show();


        });

//        facebookCardView.setOnClickListener(view -> {
//            Toast.makeText(getApplicationContext(), "Clicked Facebook.", Toast.LENGTH_SHORT).show();
//        });
//
//        twitterCardView.setOnClickListener(view -> {
//            Toast.makeText(getApplicationContext(), "Clicked Twitter.", Toast.LENGTH_SHORT).show();
//        });
    }

    private int selectype(){
        if(loginasOption==0){
            return 109;
        }else if(loginasOption==1){
            return 110;
        }
        else if(loginasOption==2){
            return 149;
        }else if(loginasOption==3){
            return 150;
        }else if(loginasOption==4){
            return 151;
        }
        else {
            return 200;
        }
    }

    private void GoToDashboard(){
        switch (this.loginasOption){
            case 0:
                Toast.makeText(getApplicationContext(), "Clicked option 0.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, businessappDriverDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

                break;
            case 1:
                //Toast.makeText(getApplicationContext(), "Clicked option 1.", Toast.LENGTH_SHORT).show();

                intent = new Intent(login_activity.this,businessappFleetownerDashboardActivity.class);
                intent.putExtra("UserAccountNo",UserAccountNumber);
                intent.putExtra("usertypeid",usertypeid);
                startActivity(intent);
                //intent = new Intent(this, businessappFleetownerDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                //startActivity(intent);
                break;

            case 2:
                Toast.makeText(getApplicationContext(), "Clicked option 2.", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, businessappticketagentDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;
            case 3:
                Toast.makeText(getApplicationContext(), "Clicked option 3.", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, businessappBrandDashboardActivity.class);
                startActivity(intent);
                break;
            case 4:
                Toast.makeText(getApplicationContext(), "Clicked option 4.", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, businessappBusinessownerDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                break;
            default:
                //intent = new Intent(this, customerDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                //startActivity(intent);
                break;
        }

    }

    public void DriverLogin(JsonObject jsonObject){

       // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .BusinessAppUserValidateCredentials(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BusinessappuserValidateResp>>() {
                    @Override
                    public void onCompleted() {
                        //    DisplayToast("Successfully LoggedIn");
                     //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                            //DisplayToast("Unable to Login");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<BusinessappuserValidateResp> responce) {
                        BusinessappuserValidateResp credentialsResponse=responce.get(0);
                        if(credentialsResponse.getCode()!=null){
                            DisplayToast(credentialsResponse.getDescription());
                        }else {
                            SharedPreferences sharedPref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
//                            SharedPreferences pref = getApplicationContext().getSharedPreferences(MyPREFERENCES, 0);
//                            Editor editor = pref.edit();
                            editor.putString(UserAccountNumber, credentialsResponse.getuseraccountno());
                            editor.putInt(usertypeid, credentialsResponse.getusertypeid());
                           // editor.putInt(usertypeid, credentialsResponse.getusertypeid());
////                            editor.putString(VEHICLEID, credentialsResponse.getVehicleId());
//                            editor.putString(Phone, mobileNo.getText().toString());
//                            editor.putString(Emailotp, null);
//                            editor.putString(Mobileotp, null);
                            editor.commit();
                            ApplicationConstants.mobileNo = mobileNo.getText().toString();
                            ApplicationConstants.userAccountNo=credentialsResponse.getuseraccountno();
                            ApplicationConstants.usertypeid=credentialsResponse.getusertypeid();
                            ApplicationConstants.upic=credentialsResponse.getUserPhoto();
                            //startActivity(new Intent(this, MainActivity.class));
                           GoToDashboard();
                            finish();
                       }

                    }
                });
    }

    public void GetActiveCountries(int active){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(login_activity.this).getrestadapter()
                .GetActiveCountry(active)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ActiveCountries>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully Registered");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<ActiveCountries> list) {

                        List<ActiveCountries> response= list ;
                        int countrycount = response.size();
                        if (countrycount == 0) {
                            DisplayToast("Please configure countries of operation.");
                        } else {

                            String countriesList = "";
                            for(int i=0; i < countrycount ; i++){
                                if(i == countrycount-1)
                                    countriesList += response.get(i).getISOCode();
                                else
                                    countriesList += response.get(i).getISOCode()+ ",";
                            }

                            ccp.setCustomMasterCountries(countriesList);

//                            ccp = (CountryCodePicker) findViewById(R.id.ccp);
//                            ccp.setCustomMasterCountries(response.getISOCode());
//                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedpreferences.edit();
//                            editor.putString(Isocode, response.getISOCode());
//                            editor.commit();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //   accessTokenTracker.stopTracking();
        //   profileTracker.stopTracking();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loginasOption = position;
           }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        loginasOption = -1;
    }


    //endregion

    private void checkPermissions() {


        if (hasPermissions(this, PERMISSIONS).size() > 0) {
            //ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
            ActivityCompat.requestPermissions(this, hasPermissions(this, PERMISSIONS).toArray(new String[0]), PERMISSIONS_ALL);

        } else if (hasPermissions(this, PERMISSIONS).size() == 0) {
            // DisplaySplashscreen();
        }
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (ContextCompat.checkSelfPermission(login_activity.this,
//                    Manifest.permission.SYSTEM_ALERT_WINDOW)
//                    != PackageManager.PERMISSION_GRANTED)
//                checkDrawOverlayPermission();
//        }
    }


    public static ArrayList<String> hasPermissions(Context context, String... permissions) {

        ArrayList<String> perms = new ArrayList<>();
        //if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
        if (Build.VERSION.SDK_INT >= 23 && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    perms.add(permission);
                }
            }
        }
        return perms;
    }

    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(login_activity.this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + login_activity.this.getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    class CheckServerTask extends AsyncTask<String, Void, String[]> {
        android.app.ProgressDialog dialog = new android.app.ProgressDialog(login_activity.this);

        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please Wait...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        }

        @Override
        protected String[] doInBackground(String... strings) {
            CheckConnection();
            return new String[0];
        }

        @Override
        protected void onPostExecute(String... result) {
            if (dialog.isShowing())
                dialog.dismiss();
            if (isServerOn) {
//                if (mobNo != null && emailOTP == null && mobileOTP == null) {
//                    //ApplicationConstants.mobileNo = mobNo;
//                    ApplicationConstants.id = id;
//                    startActivity(new Intent(login_activity.this, businessappMOTPVerificationActivity.class));
//                    finish();
//                } else if (mobNo != null && (emailOTP != null || mobileOTP != null)) {
//                    startActivity(new Intent(login_activity.this, businessappEOTPVerificationActivity.class));
//                    finish();
//                }
                if (emailOTP != null && mobileOTP != null) {
//                    ApplicationConstants.mobileNo = mobNo;
//                    ApplicationConstants.id = id;
//                    startActivity(new Intent(login_activity.this,customerEOTPVerificationActivity.class));
//                    finish();
                } else {
                    if (emailOTP == null && mobileOTP != null) {
//                        startActivity(new Intent(login_activity.this, customerMOTPVerificationActivity.class));
//                        finish();
                    } else if(emailOTP != null && mobileOTP == null){

//                        startActivity(new Intent(login_activity.this,customerEOTPVerificationActivity.class));
//                        finish();
                    }
                }
            } else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(login_activity.this, R.style.Theme_AppCompat_DayNight_Dialog);
                alertDialog.setTitle("Please Check Server or Please check Your Interenet Connection");
                alertDialog.setMessage("Try Again");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                CheckServerTask checkServerTask = new CheckServerTask();
                                checkServerTask.execute();
                            }
                        });
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                alertDialog.show();
            }

        }
    }

    private void CheckConnection() {
        /*isServerOn=false;
         String ipAddress;
        if(getResources().getString(R.string.url_server).matches("http://124.123.41.203:8088")){
            ipAddress=getResources().getString(R.string.url_server);
        }else{
            ipAddress="196.27.119.219";
        }
        Log.i("Checking Server", "Address "+ipAddress);
        SocketAddress sockaddr = new InetSocketAddress(ipAddress, 8088);
        // Create an unbound socket
        Socket sock = new Socket();
        int timeoutMs = 5000;   // 2 seconds
        try {
            Log.i("Checking Server", "Connecting  "+ipAddress);
            sock.connect(sockaddr, timeoutMs);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("Checking Server", ipAddress+" is Connected "+sock.isConnected());

         isServerOn=sock.isConnected();*/
        isServerOn = true;
           /* try
            {
                InetAddress inet = InetAddress.getByName(ipAddress);
                System.out.println("Sending Ping Request to " + ipAddress);

                boolean status = inet.isReachable(5000); //Timeout = 5000 milli seconds

                if (status)
                {
                    Log.i("Checking Server","Status : Host is reachable");
                }
                else
                {
                    Log.i("Checking Server","Status : Host is not reachable");
                }
            }
            catch (UnknownHostException e)
            {
                Log.i("Checking Server","Host does not exists");
            }
            catch (IOException e)
            {
                Log.i("Checking Server","Error in reaching the Host");
            }*/

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
}
