package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.businessappDocumentUploadActivity;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsTable1Item;
import com.webingate.paysmartbusinessapp.utils.RangeSeekBar;
import com.webingate.paysmartbusinessapp.utils.Utils;
import com.webingate.paysmartbusinessapp.utils.ViewAnimationUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import butterknife.BindView;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppUploadDocsFragment extends Fragment {


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

    private android.app.DatePickerDialog DatePickerDialog;
    public Dialog d;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_document_upload, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        //setting <-- button to toolbar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        setDateTimeField();
        expireDate = (TextView)getActivity().findViewById(R.id.expire_date);
        chooseFile = (AppCompatButton)getActivity().findViewById(R.id.choose_file);
        submit = (AppCompatButton)getActivity().findViewById(R.id.submit);
//        expireDate.setOnClickListener(getActivity());
//        chooseFile.setOnClickListener();
//        submit.setOnClickListener();
        DriverDetailsTable1Item documentsModel = (DriverDetailsTable1Item) ApplicationConstants.documentslist.get(ApplicationConstants.documentNo);
        documentName.setText(documentsModel.getDocName());
        documentNo.setText(documentsModel.getDocumentNo());
        expireDate.setText(documentsModel.getExpiryDate());

        return view;
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        final int year = newCalendar.get(Calendar.YEAR), month = newCalendar.get(Calendar.MONTH), day = newCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog = new DatePickerDialog(getActivity(), R.style.Dialog_Theme, new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(selectedYear, selectedMonth, selectedDay);
                expireDate.setText(selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
            }

        }, year, month, day);
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == 42) {
//            if (resultCode == Activity.RESULT_OK) {
//                try {
//                    Uri uri = data.getData();
//                    ApplicationConstants.document_format = getActivity().getContentResolver().getType(uri);
//                    Toast.makeText(getActivity().getApplicationContext(), ApplicationConstants.document_format, Toast.LENGTH_SHORT).show();
//                    getActivity().getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(
//                            inputStream));
//                    StringBuilder stringBuilder = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        stringBuilder.append(line);
//                    }
//                    inputStream.close();
//                    String encodedImage = Base64.encodeToString(stringBuilder.toString().getBytes(), Base64.DEFAULT);
//                    ApplicationConstants.document_data = encodedImage;
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (resultCode == Activity.RESULT_CANCELED) {
//                Toast.makeText(getActivity().getApplicationContext(), "Document Selection Failed", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v == expireDate) {
//            DatePickerDialog.show();
//        } else if (v == chooseFile) {
//            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.setType("*/*");
//            String[] mimeTypes = {"image/*", "application/pdf"};
//            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
//            startActivityForResult(intent, READ_REQUEST_CODE);
//
//        } else if (v == submit) {
//            if (documentNo.getText().toString().matches("") || expireDate.getText().toString().matches("") || ApplicationConstants.document_data.matches("")) {
//                Toast.makeText(getActivity().getApplicationContext(), "Please Enter All Details", Toast.LENGTH_SHORT).show();
//            } else {
//                ApplicationConstants.document_number = documentNo.getText().toString();
//                ApplicationConstants.document_expire_date = expireDate.getText().toString();
//                Intent returnIntent = new Intent();
//                getActivity().setResult(Activity.RESULT_OK, returnIntent);
//                finish();
//            }
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

    }

    private void initUI() {

    }

    private void initDataBindings() {

    }

    private void initActions() {

    }


}
