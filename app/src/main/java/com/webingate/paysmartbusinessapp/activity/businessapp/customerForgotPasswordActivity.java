package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.utils.Utils;

public class customerForgotPasswordActivity extends AppCompatActivity {

    Button resetButton;
    TextView signInTextView;
    ImageView bgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerapp_forgotpassword_activity);

        initUI();

        initDataBindings();

        initActions();

    }

    //region Init Functions
    private void initUI() {
        signInTextView = findViewById(R.id.signinTextView);
        resetButton = findViewById(R.id.resetButton);
        bgImageView = findViewById(R.id.bgImageView);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {

        signInTextView.setOnClickListener(view -> {
           // Toast.makeText(getApplicationContext(), "Clicked Sign In", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, login_activity.class);
            startActivity(intent);
        });


        resetButton.setOnClickListener(view -> {
            //Toast.makeText(getApplicationContext(), "Clicked Reset.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, customerpwdOTPVerificationActivity.class);
            startActivity(intent);
        });

    }
    //endregion
}
