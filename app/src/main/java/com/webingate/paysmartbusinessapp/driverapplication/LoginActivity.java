package com.webingate.paysmartbusinessapp.driverapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverValidateCredentialsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

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
import com.facebook.login.widget.LoginButton;*/import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
public class LoginActivity extends FragmentActivity {


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
    public static final String DRIVERID = "driverid";
    public static final String VEHICLEID = "vehicleid";
    //  CallbackManager callbackManager;
    //  LoginButton facebookButton;
    //  AccessTokenTracker accessTokenTracker;
    //  ProfileTracker profileTracker;
    //@BindView(R.id.login_type)
   // Spinner loginType;
    @BindView(R.id.input_mobile)
    EditText mobileNo;
    @BindView(R.id.input_password)
    EditText password;
    @BindView(R.id.btn_login)
    AppCompatButton loginButton;
    @BindView(R.id.link_forgotpassword)
    TextView forgotpassword;
    @BindView(R.id.link_signup)
    TextView register;

    private String response;
    Toast toast;
    ProgressDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(LoginActivity.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
       /* callbackManager = CallbackManager.Factory.create();
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                // App code
            }
        };
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        facebookButton = (LoginButton) findViewById(R.id.login_facebook);*/
        //  facebookButton.setReadPermissions("email");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.login_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     //   loginType.setAdapter(adapter);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpassword.setTextColor(Color.parseColor("#FFFFFF"));
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                finish();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register.setTextColor(Color.parseColor("#FFFFFF"));
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(LoginActivity.this, Hirevehicle3.class));
                // finish();
                if (mobileNo.getText().toString().matches("") || password.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter details", Toast.LENGTH_SHORT).show();
                } else {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("Mobilenumber", mobileNo.getText().toString());
                    jsonObject.addProperty("Password", password.getText().toString());
                    DriverLogin(jsonObject);
                }
            }
        });
        // If using in a fragment
        /*   callbackManager = CallbackManager.Factory.create();
         *//* loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));
                }
            });*//*
        // Callback registration
        facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject json, GraphResponse response) {
                        // Application code
                        if (response.getError() != null) {
                            System.out.println("ERROR");
                        } else {
                            System.out.println("Success");
                            String jsonresult = String.valueOf(json);
                            System.out.println("JSON Result" + jsonresult);

                            String fbUserId = json.optString("id");
                            String fbUserFirstName = json.optString("name");
                            String fbUserEmail = json.optString("email");
                            String fbUserProfilePics = "http://graph.facebook.com/" + fbUserId + "/picture?type=large";
                            // callApiForCheckSocialLogin(fbUserId, fbUserFirstName, fbUserEmail, fbUserProfilePics, "fb");
                            ApplicationConstants.email = fbUserEmail;
                            ApplicationConstants.mobileNo = fbUserFirstName;
                            ApplicationConstants.password = fbUserId;

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
*//*
                           text.setText("ID                -    "+fbUserId+
                                   "\nFirst Name      -    "+fbUserFirstName+
                                   "\nEmail           -    "+fbUserEmail
                                  );*//*
                        }

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });*/

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_main, menu);
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
        //   accessTokenTracker.stopTracking();
        //   profileTracker.stopTracking();
    }


public void DriverLogin(JsonObject jsonObject){

    StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(LoginActivity.this).getrestadapter()
                .ValidateDriver(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverValidateCredentialsResponse>>() {
                    @Override
                    public void onCompleted() {
                    //    DisplayToast("Successfully LoggedIn");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                            DisplayToast("Unable to Login");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DriverValidateCredentialsResponse> responce) {
                        DriverValidateCredentialsResponse credentialsResponse=responce.get(0);
                        if(credentialsResponse.getCode()!=null){
                            DisplayToast(credentialsResponse.getDescription());
                        }else {
                            SharedPreferences sharedPref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(DRIVERID, credentialsResponse.getDid());
                            editor.putString(VEHICLEID, credentialsResponse.getVehicleId());
                            editor.putString(Phone, mobileNo.getText().toString());
                            editor.putString(Emailotp, null);
                            editor.putString(Mobileotp, null);
                            editor.commit();
                            ApplicationConstants.mobileNo = mobileNo.getText().toString();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }

                    }
                });
}

   /* class LoginRequest extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(LoginActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setTitle("Login in process");
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
                    JSONArray jsonObj = new JSONArray(response);
                    // looping through All Contacts
                    JSONObject c = jsonObj.getJSONObject(0);
                    if (c.has("Code")) {
                        Toast.makeText(getApplicationContext(), c.getString("description"), Toast.LENGTH_LONG).show();
                    } else {
                        //   Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(DRIVERID, c.getString("did"));
                        editor.putString(VEHICLEID, c.getString("VehicleId"));
                        editor.putString(Phone, mobileNo.getText().toString());
                        editor.putString(Emailotp, null);
                        editor.putString(Mobileotp, null);
                        editor.commit();
                        ApplicationConstants.mobileNo = mobileNo.getText().toString();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
            object.put("Mobilenumber", mobileNo.getText().toString());
            object.put("Password", password.getText().toString());
            //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
            // Defined URL  where to send data
            URL url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_driver_login));
            Log.d("Login url", url.toString());
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
