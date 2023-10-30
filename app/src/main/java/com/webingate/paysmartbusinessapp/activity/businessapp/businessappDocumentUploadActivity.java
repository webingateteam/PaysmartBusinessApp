package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsTable1Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class businessappDocumentUploadActivity extends AppCompatActivity implements
        View.OnClickListener {
    private static final int READ_REQUEST_CODE = 42;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.document_name)
    TextView documentName;
    @BindView(R.id.document_no)
    EditText documentNo;
    @BindView(R.id.expire_date)
    TextView expireDate;
    @BindView(R.id.choose_file)
    AppCompatButton chooseFile;
    @BindView(R.id.submit)
    AppCompatButton submit;

    private DatePickerDialog DatePickerDialog;
    public Dialog d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_document_upload);
        ButterKnife.bind(this);
        // adding toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setDateTimeField();
        expireDate.setOnClickListener(this);
        chooseFile.setOnClickListener(this);
        submit.setOnClickListener(this);
        DriverDetailsTable1Item documentsModel = (DriverDetailsTable1Item) ApplicationConstants.documentslist.get(ApplicationConstants.documentNo);
        documentName.setText(documentsModel.getDocName());
        documentNo.setText(documentsModel.getDocumentNo());
        expireDate.setText(documentsModel.getExpiryDate());

    }

    //toolbar button click
    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        onBackPressed();
        return true;
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        final int year = newCalendar.get(Calendar.YEAR), month = newCalendar.get(Calendar.MONTH), day = newCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog = new DatePickerDialog(businessappDocumentUploadActivity.this, R.style.Dialog_Theme, new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(selectedYear, selectedMonth, selectedDay);
                expireDate.setText(selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
            }

        }, year, month, day);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Uri uri = data.getData();
                    ApplicationConstants.document_format = getContentResolver().getType(uri);
                    Toast.makeText(getApplicationContext(), ApplicationConstants.document_format, Toast.LENGTH_SHORT).show();
                    getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    inputStream.close();
                    String encodedImage = Base64.encodeToString(stringBuilder.toString().getBytes(), Base64.DEFAULT);
                    ApplicationConstants.document_data = encodedImage;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Document Selection Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == expireDate) {
            DatePickerDialog.show();
        } else if (v == chooseFile) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            String[] mimeTypes = {"image/*", "application/pdf"};
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            startActivityForResult(intent, READ_REQUEST_CODE);

        } else if (v == submit) {
            if (documentNo.getText().toString().matches("") || expireDate.getText().toString().matches("") || ApplicationConstants.document_data.matches("")) {
                Toast.makeText(getApplicationContext(), "Please Enter All Details", Toast.LENGTH_SHORT).show();
            } else {
                ApplicationConstants.document_number = documentNo.getText().toString();
                ApplicationConstants.document_expire_date = expireDate.getText().toString();
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }
}