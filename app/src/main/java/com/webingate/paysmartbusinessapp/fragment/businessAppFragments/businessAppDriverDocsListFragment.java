package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverDocAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverListAdapter;
import com.webingate.paysmartbusinessapp.adapter.businessapp_AdapterBookingType;
import com.webingate.paysmartbusinessapp.data.DataGenerator;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DefaultResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDocumentsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.DriverDetails;
import com.webingate.paysmartbusinessapp.model.BookingType;
import com.webingate.paysmartbusinessapp.utils.RangeSeekBar;
import com.webingate.paysmartbusinessapp.utils.Utils;
import com.webingate.paysmartbusinessapp.utils.ViewAnimationUtils;
import com.webingate.paysmartbusinessapp.widget.LineItemDecoration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppUploadDocsFragment.GET_FROM_GALLERY;

public class businessAppDriverDocsListFragment extends Fragment {
    private businessappDriverDocAdapter docadapter;
//    private ImageView sizeUpDownImageView;
    Calendar dateTime = Calendar.getInstance();
    @BindView(R.id.placeList1RecyclerView)
    RecyclerView placeList1RecyclerView;
    private Context ctx;
//    private View sizeLayout;
//    private View materialLayout;
    @BindView(R.id.Upload)  Button Uploading;
    @BindView(R.id.Updoc)  ImageView Doc;
//    @BindView(R.id.status)  ImageView status;
//    @BindView(R.id.expdate)  Button expdate;
//    @BindView(R.id.dateofex)  TextView dateofex;
//    @BindView(R.id.submit)  Button submit;
//    @BindView(R.id.missing)  TextView missing;
//    @BindView(R.id.sizeTitleValueTextView)  TextView sizeTitleValueTextView;
//    private RangeSeekBar seekBar;
    List<DriverDocumentsResponse> rr;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.businessapp_doclit_activity, container, false);
        initData(view);
        initUI(view);
        initDataBinding();
        initActions(view);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           // finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData(View view) {

    }

    private void initUI(View view) {

        // Init Toolbar
        initToolbar(view);

        placeList1RecyclerView = view.findViewById(R.id.placeList1RecyclerView);
        placeList1RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        placeList1RecyclerView.addItemDecoration(new LineItemDecoration(getContext(), LinearLayout.VERTICAL));
        placeList1RecyclerView.setHasFixedSize(true);
        List<BookingType> items = DataGenerator.getDoctypes(getContext());

        //set data and list adapter
        docadapter = new businessappDriverDocAdapter(getContext(),items);
        placeList1RecyclerView.setAdapter(docadapter);
        Uploading = view.findViewById(R.id.Upload);
         Doc=view.findViewById(R.id.Updoc);
//
//        sizeUpDownImageView = view.findViewById(R.id.sizeUpDownImageView);
//        expdate=view.findViewById(R.id.expdate);
//        dateofex=view.findViewById(R.id.dateofex);
//        submit=view.findViewById(R.id.submit);
//        missing=view.findViewById(R.id.missing);
//        status=view.findViewById(R.id.status);
//        sizeTitleValueTextView=view.findViewById(R.id.sizeTitleValueTextView);
//        sizeLayout = view.findViewById(R.id.sizeLayout);
//        sizeLayout.setVisibility(View.GONE);
//        Drawable selectedList = getContext().getResources().getDrawable(R.drawable.baseline_selected_list_24);
//        selectedList.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

    }

    private void setDefaultCircleImage(ImageView imageView, int color) {
        Utils.setCircleImageToImageView(getContext(), imageView, R.drawable.white_background, 0, 0);
        imageView.setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_IN);
    }

    private void setSelectUnSelectSizeFilter(ImageView imageView, int bgColor, TextView textView, int color) {
        imageView.setColorFilter(getResources().getColor(bgColor), PorterDuff.Mode.SRC_IN);
        textView.setTextColor(getResources().getColor(color));
    }
    private void initDataBinding() {

    }
    private void initActions(View view) {
//        sizeUpDownImageView.setOnClickListener((View v) -> {
//            boolean show = Utils.toggleUpDownWithAnimation(v);
//            if (show) {
//                ViewAnimationUtils.expand(sizeLayout);
//            } else {
//                ViewAnimationUtils.collapse(sizeLayout);
//            }
//        });
//
//
        docadapter.setOnItemClickListener((view1,obj, position) ->
                {
                    Toast.makeText(getContext(), "Selected"+position, Toast.LENGTH_SHORT).show();
                            browsePhoto("");
                }
        );
        //Uploading.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    browsePhoto(view);
//                }
//            });

//        expdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openDatePicker();
//            }
//        });
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                JsonObject object = new JsonObject();
//                object.addProperty("Id", "");
//                object.addProperty("DriverId",1003 );
//                object.addProperty("FileName", ApplicationConstants.document_format);
//                object.addProperty("DocTypeId", 86);
//                object.addProperty("ExpiryDate", ApplicationConstants.document_expire_date);
//                object.addProperty("UpdatedById", 1);
//                object.addProperty("DueDate", ApplicationConstants.document_expire_date);
//                object.addProperty("FileContent", "data:" + ApplicationConstants.document_format + ";base64," + ApplicationConstants.document_data);
//                object.addProperty("change", "I");
//                object.addProperty("loggedinUserId", 1);
//                object.addProperty("DocumentNo", ApplicationConstants.document_number);
//                UploadDocument(object);
//            }
//        });
        //endregion
    }

    private void updateColorTitle() {
    }

    private void updateSizeTitle() {
        String value = "";
        if (value.equals("")) {
            value = "Not Set.";
        }
       // sizeTitleValueTextView.setText(value);
    }

    public void clickMaterial(View view) {
        if (view != null && view instanceof Button) {
            view.setSelected(!view.isSelected());
        }
    }

    private void initToolbar(View view) {
    }

    private void browsePhoto(String imageName) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Detects request codes

        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
                Toast.makeText(getContext(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                //missing.setText("Upload");
                //missing.setTextColor(getContext().getResources().getColor(R.color.md_yellow_500));
               // status.setColorFilter(getContext().getResources().getColor(R.color.md_yellow_500));
                 //Doc.setImageBitmap(bitmap);

                // for coverting the file to send ///
                Uri uri = data.getData();
                ApplicationConstants.document_format = getContext().getContentResolver().getType(uri);

                InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
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
                JsonObject object = new JsonObject();
                object.addProperty("Id", "");
                object.addProperty("DriverId",1015 );
                object.addProperty("FileName", ApplicationConstants.document_format);
                object.addProperty("DocTypeId", 86);
                object.addProperty("ExpiryDate", ApplicationConstants.document_expire_date);
                object.addProperty("UpdatedById", 1);
                object.addProperty("DueDate", ApplicationConstants.document_expire_date);
                object.addProperty("FileContent", "data:" + ApplicationConstants.document_format + ";base64," + ApplicationConstants.document_data);
                object.addProperty("change", "I");
                object.addProperty("loggedinUserId", 1);
                object.addProperty("DocumentNo", ApplicationConstants.document_number);
                UploadDocument(object);
//
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
//    DatePickerDialog.OnDateSetListener datePickerDialog = (view, year, monthOfYear, dayOfMonth) -> {
//        dateTime.set(Calendar.YEAR, year);
//        dateTime.set(Calendar.MONTH, monthOfYear);
//        dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        updateDate();
//    };
//
//    private void openDatePicker(){
//        new DatePickerDialog(this.getContext(), datePickerDialog, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
//    }

//    private void updateDate(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//        String shortTimeStr = sdf.format(dateTime.getTime());
//        //ApplicationConstants.date = shortTimeStr;
//        ApplicationConstants.document_expire_date=shortTimeStr;
//        dateofex.setText(shortTimeStr);
//        sizeTitleValueTextView.setText(shortTimeStr);
//        ApplicationConstants.document_expire_date=shortTimeStr;
//    }
    public void UploadDocument(JsonObject jsonObject){

        // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getContext()).getrestadapter()
                .SaveDriverDocuments1(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverDocumentsResponse>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getContext(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        //DisplayToast("Successfully Registered");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            //DisplayToast("Unable to Register");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DriverDocumentsResponse> response) {
                        DriverDocumentsResponse rr =response.get(0);
                        Toast.makeText(getContext(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        if(rr.getCode()!=null){
                            Toast.makeText(getContext(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                       }else {
                            Toast.makeText(getContext(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                    }
                    }
                });
    }
}
