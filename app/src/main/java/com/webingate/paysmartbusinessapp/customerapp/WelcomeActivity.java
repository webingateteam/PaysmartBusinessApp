package com.webingate.paysmartbusinessapp.customerapp;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;
public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Phone = "phoneKey";
    public static final String ID = "idKey";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordkey";
    public static final String Mobileotp = "mobileotpkey";
    public static final String Emailotp = "emailotpkey";
    public static final String Dateofbirth = "dateofbirth";
    public static final String Gender = "gender";
    public static final String Paymenttype = "paymenttype";
    public static final String Profilepic = "profilepic";
    public final static int REQUEST_CODE = 10101;
    private boolean isServerOn;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermissions();
        CheckServerTask checkServerTask = new CheckServerTask();
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
        // Toast.makeText(getApplicationContext(),mobNo,Toast.LENGTH_SHORT).show();


        // Checking for first time launch - before calling setContentView()


        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLoginScreen();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegisterScreen();
            }
        });


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

    private void launchLoginScreen() {

        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();
    }

    private void launchRegisterScreen() {

        startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
        //finish();
    }

    private void checkPermissions() {


        if (hasPermissions(this, PERMISSIONS).size() > 0) {
            //ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
            ActivityCompat.requestPermissions(this, hasPermissions(this, PERMISSIONS).toArray(new String[0]), PERMISSIONS_ALL);

        } else if (hasPermissions(this, PERMISSIONS).size() == 0) {
            // DisplaySplashscreen();
        }
    }

    public void managepermissions() {
        if (hasPermissions(this, PERMISSIONS).size() > 0) {
            ActivityCompat.requestPermissions(this, hasPermissions(this, PERMISSIONS).toArray(new String[0]), PERMISSIONS_ALL);
        } else if (hasPermissions(this, PERMISSIONS).size() == 0) {

        }


        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(WelcomeActivity.this,
                    Manifest.permission.SYSTEM_ALERT_WINDOW)
                    != PackageManager.PERMISSION_GRANTED)
                checkDrawOverlayPermission();
        }
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
        if (!Settings.canDrawOverlays(WelcomeActivity.this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + WelcomeActivity.this.getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    class CheckServerTask extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(WelcomeActivity.this);

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
                if (mobNo != null && emailOTP == null && mobileOTP == null) {
                    ApplicationConstants.mobileNo = mobNo;
                    ApplicationConstants.id = id;
                    startActivity(new Intent(WelcomeActivity.this, HomeActivity1.class));
                    finish();
                } else if (mobNo != null && (emailOTP != null || mobileOTP != null)) {
                    startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
                    finish();
                }
            } else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(WelcomeActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
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
}
