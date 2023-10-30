package com.webingate.paysmartbusinessapp.driverapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.ChangepwdResponse;
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
/**
 * Created by Seshu on 10/18/2017.
 */

public class ResetPassword extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.input_email)
    EditText email;
    @BindView(R.id.old_password)
    EditText old_password;
    @BindView(R.id.new_password)
    EditText new_password;
    @BindView(R.id.repeat_new_password)
    EditText repeat_new_password;
    @BindView(R.id.reset_password_button)
    AppCompatButton resetPassword;
    private String response;

    Toast toast;
    ProgressDialog dialog ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(ResetPassword.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        // adding toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new_password.getText().toString().matches(repeat_new_password.getText().toString())) {
                    JsonObject object = new JsonObject();
                    object.addProperty("Mobilenumber", ApplicationConstants.mobileNo);
                    object.addProperty("Email", email.getText().toString());
                    object.addProperty("Password", old_password.getText().toString());
                    object.addProperty("NewPassword", new_password.getText().toString());
                    ResetPasswordTask(object);

                   /* ResetPasswordTask resetPasswordTask = new ResetPasswordTask();
                    resetPasswordTask.execute();*/


                } else {
                    Toast.makeText(getApplicationContext(), "Password Did't match", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //toolbar button click
    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        return true;
    }




    public void ResetPasswordTask(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(ResetPassword.this).getrestadapter()
                .ChangePassword(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ChangepwdResponse>>() {
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
                    public void onNext(List<ChangepwdResponse> responseList) {

                        ChangepwdResponse response=responseList.get(0);
                        if (response.getNAme()!=null) {
                            DisplayToast("Password Updated Successfully ");
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
   /* class ResetPasswordTask extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(ResetPassword.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setTitle("Request in process");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                ResetPasswordProcess();
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
                if (c.has("NAme")) {
                    Toast.makeText(getApplicationContext(), " Password Updated Successfully ", Toast.LENGTH_LONG).show();
                    Log.i("Password Change", "success " + response);
                    finish();
                } else if (c.has("Code")) {
                    Toast.makeText(getApplicationContext(), c.getString("description"), Toast.LENGTH_LONG).show();
                    Log.i("Password Change", "error " + c.getString("description"));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void ResetPasswordProcess() {
        BufferedReader reader = null;
        response = "";
        URLConnection conn = null;
        try {
            Log.i("Password Change", "sending request ");
            JSONObject object = new JSONObject();
            object.put("Mobilenumber", ApplicationConstants.mobileNo);
            object.put("Email", email.getText().toString());
            object.put("Password", old_password.getText().toString());
            object.put("NewPassword", new_password.getText().toString());
            //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
            // Defined URL  where to send data
            URL url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_reset_password));
            Log.d("Login url", url.toString());
            // Send POST data request
            conn = url.openConnection();
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

