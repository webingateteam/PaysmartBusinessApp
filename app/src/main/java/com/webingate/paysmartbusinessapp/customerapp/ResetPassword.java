package com.webingate.paysmartbusinessapp.customerapp;
import com.webingate.paysmartbusinessapp.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.businessapp.Deo.CustomerChangePwdResponse;
import com.webingate.paysmartbusinessapp.businessapp.Dialog.ProgressDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    Toast toast;
    ProgressDialog dialog ;
    private String response;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(ResetPassword.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        // adding toolbar
        setSupportActionBar(toolbar);
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new_password.getText().toString().matches(repeat_new_password.getText().toString())) {
                    //  Toast.makeText(getApplicationContext(),ApplicationConstants.mobileNo,Toast.LENGTH_SHORT).show();
                    JsonObject object = new JsonObject();
                    object.addProperty("Mobilenumber", ApplicationConstants.mobileNo);
                    object.addProperty("Email", email.getText().toString());
                    object.addProperty("Password", old_password.getText().toString());
                    object.addProperty("NewPassword", new_password.getText().toString());
                    //ChangePassword(object);
                   /* ResetPasswordTask resetPasswordTask = new ResetPasswordTask();
                    resetPasswordTask.execute();*/
                } else {
                    DisplayToast("Password Did't match");
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

//    public void ChangePassword(JsonObject jsonObject){
//
//        StartDialogue();
//        com.webingate.paysmartbusinessapp.businessapp.Utils.DataPrepare.get(ResetPassword.this).getrestadapter()
//                .ChangePassword(jsonObject)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<CustomerChangePwdResponse>>() {
//                    @Override
//                    public void onCompleted() {
//                        //  DisplayToast("Successfully Registered");
//                        StopDialogue();
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        try {
//                            Log.d("OnError ", e.getMessage());
//                            DisplayToast("Error");
//                            StopDialogue();
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onNext(List<CustomerChangePwdResponse> responselist) {
//                        CustomerChangePwdResponse response=responselist.get(0);
//                        if (response.getUsername()!=null) {
//                            DisplayToast("Password Updated Successfully");
//                            finish();
//                        } else if (response.getCode()!=null) {
//                            DisplayToast(response.getDescription());
//
//                        }
//
//                    }
//                });
//    }
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
                if (c.has("Username")) {
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

