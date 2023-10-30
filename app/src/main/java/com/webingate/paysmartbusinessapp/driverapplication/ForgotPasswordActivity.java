package com.webingate.paysmartbusinessapp.driverapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverForgotpasswordResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverPasswordVerificationResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RideDetailsResponse;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
public class ForgotPasswordActivity extends FragmentActivity {
    @BindView(R.id.input_mobileno)
    EditText mobileno;
    @BindView(R.id.input_email)
    EditText email;
    @BindView(R.id.input_otp)
    EditText otp;
    @BindView(R.id.input_password)
    EditText password;
    @BindView(R.id.input_repassword)
    EditText repassword;
    @BindView(R.id.btn_confirm)
    AppCompatButton confirm;
    private String response;
    Toast toast;
    ProgressDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(ForgotPasswordActivity.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        if (ApplicationConstants.isResetPasswordfirstWondow) {
            otp.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            repassword.setVisibility(View.GONE);
        } else {
            mobileno.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
        }
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ApplicationConstants.isResetPasswordfirstWondow) {
                    if (mobileno.getText().toString().length() < 10 || email.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Please Enter valid Details", Toast.LENGTH_SHORT).show();
                    } else {
                        JsonObject object = new JsonObject();
                        object.addProperty("Email", email.getText().toString());
                        object.addProperty("Mobilenumber", mobileno.getText().toString());
                        ForgotPasswordRequest(object);

                       /* ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
                        forgotPasswordRequest.execute();*/
                    }
                } else {
                    if (otp.getText().toString().matches("") || password.getText().toString().matches("") || repassword.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Please Enter Details", Toast.LENGTH_SHORT).show();
                    } else if (!password.getText().toString().matches(repassword.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Password Not Matched", Toast.LENGTH_SHORT).show();
                    } else {
                        JsonObject object = new JsonObject();
                        object.addProperty("Passwordotp", otp.getText().toString());
                        object.addProperty("Mobilenumber", ApplicationConstants.mobileNo);
                        object.addProperty("Email", ApplicationConstants.email);
                        object.addProperty("Password", password.getText().toString());
                        PasswordVerificationRequest(object);

                    }
                        /*ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
                        forgotPasswordRequest.execute();*/
                    }

            }
        });

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
    }

    public void ForgotPasswordRequest(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(ForgotPasswordActivity.this).getrestadapter()
                .Forgotpassword(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverForgotpasswordResponse>>() {
                    @Override
                    public void onCompleted() {
                     //   DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Do");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DriverForgotpasswordResponse> responseList) {
                        DriverForgotpasswordResponse response=responseList.get(0);
                            if (response.getCode()!=null) {
                                Log.i("ForgotPassword status", "Error" + response.getDescription());
                               DisplayToast( response.getDescription());
                            } else if (response.getCode()==null) {
                                ApplicationConstants.isResetPasswordfirstWondow = false;
                                ApplicationConstants.mobileNo = mobileno.getText().toString();
                                ApplicationConstants.email = email.getText().toString();
                                startActivity(new Intent(ForgotPasswordActivity.this, ForgotPasswordActivity.class));
                                finish();
                            }


                    }
                });
    }

    public void PasswordVerificationRequest(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(ForgotPasswordActivity.this).getrestadapter()
                .Passwordverification(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverPasswordVerificationResponse>>() {
                    @Override
                    public void onCompleted() {
                        //   DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Do");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DriverPasswordVerificationResponse> responseList) {
                        DriverPasswordVerificationResponse response=responseList.get(0);
                            if (response.getUserAccountNo()!=null) {
                                ApplicationConstants.isResetPasswordfirstWondow = true;
                                DisplayToast("Password Reset Successful");
                                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                                finish();
                            } else if (response.getCode()!=null) {
                                DisplayToast(response.getDescription());
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




    /*class ForgotPasswordRequest extends AsyncTask<String, Void, String[]> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please Wait");
            dialog.setTitle("Request in process");
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                PasswordRequest();
                return new String[]{"Success"};
            } catch (Exception e) {
                return new String[]{"error"};
            }
        }

        @Override
        protected void onPostExecute(String... result) {
            try {
                JSONArray jsonObj = new JSONArray(response);
                JSONObject c = jsonObj.getJSONObject(0);
                if (ApplicationConstants.isResetPasswordfirstWondow) {
                    if (c.has("Code")) {
                        Log.i("ForgotPassword status", "Error" + c.getString("description"));
                        Toast.makeText(getApplicationContext(), c.getString("description"), Toast.LENGTH_LONG).show();
                    } else if (c.has("Passwordotp")) {
                        Log.i("ForgotPassword status", "Got otp " + response);
                        ApplicationConstants.isResetPasswordfirstWondow = false;
                        ApplicationConstants.mobileNo = mobileno.getText().toString();
                        ApplicationConstants.email = email.getText().toString();
                        startActivity(new Intent(ForgotPasswordActivity.this, ForgotPasswordActivity.class));
                        finish();
                    }
                } else {
                    if (c.has("NAme")) {
                        ApplicationConstants.isResetPasswordfirstWondow = true;
                        Log.i("ForgotPassword status", "success" + response);
                        Toast.makeText(getApplicationContext(), "Password Reset Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                        finish();
                    } else if (c.has("Code")) {
                        Log.i("ForgotPassword status", "Error" + c.getString("description"));
                        Toast.makeText(getApplicationContext(), c.getString("description"), Toast.LENGTH_LONG).show();
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

    public void PasswordRequest() {
        BufferedReader reader = null;
        response = "";
        URL url = null;
        JSONObject object = null;
        try {
            if (ApplicationConstants.isResetPasswordfirstWondow) {
                Log.i("ForgotPassword status", "Sending request");
                url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_driver_forgot_password));
                object = new JSONObject();
                object.put("Email", email.getText().toString());
                object.put("Mobilenumber", mobileno.getText().toString());
            } else {
                Log.i("ForgotPassword status", "Verfication  request");
                url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_driver_forgot_password_verfication));
                object = new JSONObject();
                object.put("Passwordotp", otp.getText().toString());
                object.put("Mobilenumber", ApplicationConstants.mobileNo);
                object.put("Email", ApplicationConstants.email);
                object.put("Password", password.getText().toString());
            }
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
