package com.webingate.paysmartbusinessapp.activity.businessapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.adapter.uicollection.CustomSpinnerAdapter;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

public class login_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView forgotTextView, signUpTextView;
    @BindView(R.id.loginButton)
    Button loginButton;
    CardView facebookCardView, twitterCardView;

    Spinner spinner;
    TextView textView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    int loginasOption = -1;
    @BindView(R.id.input_mobile)
    EditText mobileNo;

    String[] fruits = {"Driver", "Fleet owner", "Ticket Agent", "Brand ambassador","Business Owner"};
    int[] icons = {R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24
            , R.drawable.baseline_person_outline_black_24,R.drawable.baseline_person_outline_black_24};

    ImageView bgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessapp_login_activity);

        initUI();

        initDataBindings();

        initActions();

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        list = new ArrayList<>(Arrays.asList(fruits));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        CustomSpinnerAdapter uiloginasCustomSpinnerAdapter =new CustomSpinnerAdapter(getApplicationContext(),icons,fruits);
        spinner.setAdapter(uiloginasCustomSpinnerAdapter);

    }

    //region Init Functions
    private void initUI() {
        forgotTextView = findViewById(R.id.forgotTextView);
        signUpTextView = findViewById(R.id.signuptTextView);

        loginButton = findViewById(R.id.loginButton);
//        facebookCardView = findViewById(R.id.facebookCardView);
//        twitterCardView = findViewById(R.id.twitterCardView);
        bgImageView = findViewById(R.id.bgImageView);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {
        forgotTextView.setOnClickListener(view -> {
            //Toast.makeText(getApplicationContext(), "Clicked Forgot Password.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, customerForgotPasswordActivity.class);
            startActivity(intent);
        });

        signUpTextView.setOnClickListener(view -> {
           // Toast.makeText(getApplicationContext(), "Clicked Sign Up.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, customerSignUpActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(view -> {

//            if (mobileNo.getText().toString().matches("") || password.getText().toString().matches("")) {
//                Toast.makeText(getApplicationContext(), "Please Enter details", Toast.LENGTH_SHORT).show();
//            } else {
//                JsonObject jsonObject = new JsonObject();
//                jsonObject.addProperty("Mobilenumber", mobileNo.getText().toString());
//                jsonObject.addProperty("Password", password.getText().toString());
//                DriverLogin(jsonObject);
//            }
            //Toast.makeText(getApplicationContext(), "Clicked Login.", Toast.LENGTH_SHORT).show();
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
                   Toast.makeText(getApplicationContext(), "Clicked option 1.", Toast.LENGTH_SHORT).show();
                    intent = new Intent(this, businessappFleetownerDashboardActivity.class);
                   // EditText editText = (EditText) findViewById(R.id.editText);
                   // String message = editText.getText().toString();
                   //  intent.putExtra(EXTRA_MESSAGE, message);
                   startActivity(intent);
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


        });

//        facebookCardView.setOnClickListener(view -> {
//            Toast.makeText(getApplicationContext(), "Clicked Facebook.", Toast.LENGTH_SHORT).show();
//        });
//
//        twitterCardView.setOnClickListener(view -> {
//            Toast.makeText(getApplicationContext(), "Clicked Twitter.", Toast.LENGTH_SHORT).show();
//        });
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
}
