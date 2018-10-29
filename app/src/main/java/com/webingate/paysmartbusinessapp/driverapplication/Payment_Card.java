package com.webingate.paysmartbusinessapp.driverapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DefaultResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetCustomerAccountResponce;
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
import java.util.ArrayList;
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

public class Payment_Card extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edittext_cardnumber)
    EditText edittextCardnumber;
    @BindView(R.id.card_month)
    EditText cardMonth;
    @BindView(R.id.card_year)
    EditText cardYear;
    @BindView(R.id.card_cvv)
    EditText cardCvv;
    @BindView(R.id.enter_country_code)
    EditText enterCountryCode;
    @BindView(R.id.card_button)
    AppCompatButton cardButton;
    private String response;
    private int flag = 0;
    ArrayList<String[]> listOfPattern = new ArrayList<String[]>();

    Toast toast;
    ProgressDialog dialog ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_details);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(Payment_Card.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        // adding toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String ptVisa[] = {"^4[0-9]$", "0"};
        listOfPattern.add(ptVisa);
        String ptMasterCard[] = {"^5[1-5]$", "1"};
        listOfPattern.add(ptMasterCard);
        String ptAmeExp[] = {"^3[47]$", "2"};
        listOfPattern.add(ptAmeExp);
        String ptDinClb[] = {"^3(?:0[0-5]|[68])$", "3"};
        listOfPattern.add(ptDinClb);
        String ptDiscover[] = {"^6(?:011|5)$", "4"};
        listOfPattern.add(ptDiscover);
        String ptJcb[] = {"^(?:2131|1800|35)$", "5"};
        listOfPattern.add(ptJcb);
        edittextCardnumber.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.dummy_card, 0, 0, 0);

        edittextCardnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String ccNum = s.toString();
                for (String[] p : listOfPattern) {
                    if (ccNum.matches(p[0])) {
                        switch (p[1]) {
                            case "0":
                                edittextCardnumber.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.visa_card, 0, 0, 0);
                                break;
                            case "1":
                                edittextCardnumber.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.master_card, 0, 0, 0);
                                break;
                            case "2":
                                edittextCardnumber.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.amex_card, 0, 0, 0);
                                break;
                            case "3":
                                edittextCardnumber.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.dinersclub_card, 0, 0, 0);
                                break;
                            case "4":
                                edittextCardnumber.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.discover_card, 0, 0, 0);
                                break;
                            case "5":
                                edittextCardnumber.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.jcb_card, 0, 0, 0);
                                break;
                        }
                        break;
                    } else {
                        if (ccNum.length() < 2) {
                            edittextCardnumber.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.dummy_card, 0, 0, 0);
                        }
                    }
                }
            }
        });
        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!luhnCheck(edittextCardnumber.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Invaild Card Nummber", Toast.LENGTH_SHORT).show();
                } else {
                    JsonObject object = new JsonObject();
                    object.addProperty("insUpdDelflag", "I");
                    object.addProperty("Id", "1");
                    object.addProperty("UserId", ApplicationConstants.driverId);
                    object.addProperty("PaymentModeId", "122");
                    object.addProperty("HolderName", "");
                    object.addProperty("ExpMonth", cardMonth.getText().toString());
                    object.addProperty("ExpYear", cardYear.getText().toString());
                    object.addProperty("AccountCode", cardCvv.getText().toString());
                    object.addProperty("AccountType", "Credit/Debit Card");
                    object.addProperty("IsPrimary", "");
                    object.addProperty("IsVerified", "");
                    object.addProperty("Active", "");
                    object.addProperty("CountryId", enterCountryCode.getText().toString());
                    object.addProperty("UserTypeId", "");
                    object.addProperty("AccountNumber", edittextCardnumber.getText().toString());
                    object.addProperty("code", "");
                    object.addProperty("BVerificationCode", "");
                    object.addProperty("OtpVerfied", "");
                    object.addProperty("Mobilenumber", "");
                    CustomerAccount(object);



                  /*  flag = 1;
                    AccountRequest accountRequest = new AccountRequest();
                    accountRequest.execute();*/
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

    public static boolean luhnCheck(String card) {
        if (card == null)
            return false;
        char checkDigit = card.charAt(card.length() - 1);
        String digit = calculateCheckDigit(card.substring(0, card.length() - 1));
        return checkDigit == digit.charAt(0);
    }

    /**
     * Calculates the last digits for the card number received as parameter
     *
     * @param card {@link String} number
     * @return {@link String} the check digit
     */
    public static String calculateCheckDigit(String card) {
        if (card == null)
            return null;
        String digit;
        /* convert to array of int for simplicity */
        int[] digits = new int[card.length()];
        for (int i = 0; i < card.length(); i++) {
            digits[i] = Character.getNumericValue(card.charAt(i));
        }

        /* double every other starting from right - jumping from 2 in 2 */
        for (int i = digits.length - 1; i >= 0; i -= 2) {
            digits[i] += digits[i];

            /* taking the sum of digits grater than 10 - simple trick by substract 9 */
            if (digits[i] >= 10) {
                digits[i] = digits[i] - 9;
            }
        }
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
        }
        /* multiply by 9 step */
        sum = sum * 9;

        /* convert to string to be easier to take the last digit */
        digit = sum + "";
        return digit.substring(digit.length() - 1);
    }

    public void GetCustomerAccount(String userid){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(Payment_Card.this).getrestadapter()
                .GetCustomerAccount(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetCustomerAccountResponce>>() {
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
                    public void onNext(List<GetCustomerAccountResponce> response) {
                        ApplicationConstants.paymentMethods = response;
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();

                    }
                });
    }
    public void CustomerAccount(JsonObject object){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(Payment_Card.this).getrestadapter()
                .CustomerAccount(object)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DefaultResponse>>() {
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
                    public void onNext(List<DefaultResponse> responseList) {
                        DefaultResponse response=responseList.get(0);
                        GetCustomerAccount(ApplicationConstants.driverId);
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



   /* class AccountRequest extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(Payment_Card.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setTitle("Adding Payment Method");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                AccountServerProcess();
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
                    switch (flag) {
                        case 1:
                            flag = 2;
                            AccountRequest accountRequest = new AccountRequest();
                            accountRequest.execute();
                            break;
                        case 2:
                            ApplicationConstants.paymentMethods = new ArrayList();
                            JSONArray jsonObj = new JSONArray(response);
                            JSONObject c = new JSONObject();
                            for (int i = 0; i < jsonObj.length(); i++) {
                                c = jsonObj.getJSONObject(i);
                                Payment_Method_model payment_method_model = new Payment_Method_model();
                                payment_method_model.setId(c.getString("Id"));
                                payment_method_model.setNo(c.getString("AccountNumber"));
                                payment_method_model.setIsprimary(c.getString("IsPrimary"));
                                payment_method_model.setType(c.getString("paymenttype"));
                                ApplicationConstants.paymentMethods.add(payment_method_model);
                            }
                            Intent returnIntent = new Intent();
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                            break;
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

    public void AccountServerProcess() {
        BufferedReader reader = null;
        response = "";
        URLConnection conn = null;
        try {
            switch (flag) {
                case 1:
                    JSONObject object = new JSONObject();
                    object.put("insUpdDelflag", "I");
                    object.put("Id", "1");
                    object.put("UserId", ApplicationConstants.driverId);
                    object.put("PaymentModeId", "122");
                    object.put("HolderName", "");
                    object.put("ExpMonth", cardMonth.getText().toString());
                    object.put("ExpYear", cardYear.getText().toString());
                    object.put("AccountCode", cardCvv.getText().toString());
                    object.put("AccountType", "Credit/Debit Card");
                    object.put("IsPrimary", "");
                    object.put("IsVerified", "");
                    object.put("Active", "");
                    object.put("CountryId", enterCountryCode.getText().toString());
                    object.put("UserTypeId", "");
                    object.put("AccountNumber", edittextCardnumber.getText().toString());
                    object.put("code", "");
                    object.put("BVerificationCode", "");
                    object.put("OtpVerfied", "");
                    object.put("Mobilenumber", "");
                    //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
                    // Defined URL  where to send data
                    URL url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_add_payment_method));
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
                    break;
                case 2:
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_get_payment_methods) + ApplicationConstants.driverId);
                    conn = url.openConnection();
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Host", "android.schoolportal.gr");
                    conn.connect();
                    break;

            }

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
