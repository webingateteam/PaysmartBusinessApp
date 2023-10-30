package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.ConfigResponse;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.ActiveCountries;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cropper.CropImage;
import cropper.CropImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppUploadDocsFragment.GET_FROM_GALLERY;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppVehicleEditInfoFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    ImageView profileImageView;
    @BindView(R.id.s_Regno)
    EditText RegNo;
    @BindView(R.id.s_chasisno)
    EditText chasisno;
    @BindView(R.id.s_engineno)
    EditText engineno;
    @BindView(R.id.s_vgroup)
    Spinner vgroup;
    @BindView(R.id.s_vtype)
    Spinner vtype;
    @BindView(R.id.s_modelyear)
    EditText modelyear;
    @BindView(R.id.s_state)
    EditText state;
    @BindView(R.id.editvphoto)
    ImageView vphoto;
    Toast toast;
    Spinner country;


    int grp,type,ct;
    List<ConfigResponse> res,res1;

    ArrayAdapter arlist,groupadapter,countries;

    List<String> vtypes = new ArrayList<String>();
    List<String> typegroup = new ArrayList<String>();
    List<String> ctry = new ArrayList<String>();
    List<ActiveCountries> res2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.businessapp_editvehicle_infofragment, container, false);

        initData();
//

        vgroup = view.findViewById(R.id.s_vgroup);
        vgroup.setOnItemSelectedListener(this);
        vtype = view.findViewById(R.id.s_vtype);
        vtype.setOnItemSelectedListener(this);
        country = view.findViewById(R.id.s_country);
        country.setOnItemSelectedListener(this);

        initUI(view);
//
//        initDataBindings();
//
        initActions(view);
//
        return view;
    }
    private void initData() {

        GetVgrplist(4);
        GetVTypeslist(12);
        GetActiveCountries(1);
    }
    private void initUI(View view) {

//        profileImageView = view.findViewById(R.id.profileImageView);
//        int id = R.drawable.profile2;
//        Utils.setCornerRadiusImageToImageView(view.getContext(), profileImageView, id, 20, 2,  R.color.md_white_1000);

        setRegNo((EditText)view.findViewById(R.id.s_Regno));

        RegNo = view.findViewById(R.id.s_Regno);
        chasisno = view.findViewById(R.id.s_chasisno);
        engineno = view.findViewById(R.id.s_engineno);
        modelyear = view.findViewById(R.id.s_modelyear);
        state = view.findViewById(R.id.s_state);
        vphoto = view.findViewById(R.id.editvphoto);

        RegNo.setText(ApplicationConstants.registrationNo);
        chasisno.setText(ApplicationConstants.chasisNo);
        engineno.setText(ApplicationConstants.engineNo);

        if(ApplicationConstants.photo1!=null){
            profileImageView = view.findViewById(R.id.profileImageView);
            byte[] decodedString= Base64.decode(ApplicationConstants.photo1.substring(ApplicationConstants.photo1.indexOf(",")+1), Base64.DEFAULT);
            Bitmap image1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profileImageView.setImageBitmap(image1);
        }
        else {
            profileImageView = view.findViewById(R.id.profileImageView);
            int id = R.drawable.profile2;
            Utils.setCornerRadiusImageToImageView(view.getContext(), profileImageView, id, 20, 2,  R.color.md_white_1000);
        }
    }

    private void initActions(View view){
        vphoto.setOnClickListener((View v) -> {
            Toast.makeText(getActivity(), "Clicked on edit photo", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getActivity(), customerappTrainBookingSearchActivity.class));
            //startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("My Crop")
                    .setCropMenuCropButtonTitle("Done")
                    .setRequestedSize(400, 800)
                    .setCropMenuCropButtonIcon(R.drawable.badge_menu)
                    .start(this.getActivity());
        });
    }

    public EditText getRegNo() {
        return RegNo;
    }

    public void setRegNo(EditText RegNo) {
        this.RegNo = RegNo;
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //Detects request codes
//
//        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
//            Uri selectedImage = data.getData();
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
//
//                Toast.makeText(getActivity(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
////                missing.setText("Upload");
////                missing.setTextColor(ctx.getResources().getColor(R.color.md_yellow_500));
////                status.setColorFilter(ctx.getResources().getColor(R.color.md_yellow_500));
////                Doc.setImageBitmap(bitmap);
//
//                // for coverting the file to send ///
//                profileImageView.setImageBitmap(bitmap);
//                Uri uri = data.getData();
//                ApplicationConstants.document_vformat = getActivity().getContentResolver().getType(uri);
//
////                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
////                BufferedReader reader = new BufferedReader(new InputStreamReader(
////                        inputStream));
////                StringBuilder stringBuilder = new StringBuilder();
////                String line;
////                while ((line = reader.readLine()) != null) {
////                    stringBuilder.append(line);
////                }
////                inputStream.close();
////                String encodedImage = Base64.encodeToString(stringBuilder.toString().getBytes(), Base64.DEFAULT);
//
//
//
//
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                byte[] imageBytes = baos.toByteArray();
//                ApplicationConstants.vpicdata = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//                //ApplicationConstants.document_data = encodedImage;
//                //ApplicationConstants.picdata=encodedImage;
//                // email.setText(encodedImage);
//
////
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }


    public void GetVgrplist(int Id){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getContext()).getrestadapter()
                .GetGroups(Id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ConfigResponse>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully SignUp");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<ConfigResponse> responce) {
                        res = responce;

                        for (int i = 0; i < res.size(); i++) {
                            typegroup.add(res.get(i).getName());
//                            if( res.get(i).getId()==Integer.parseInt(ApplicationConstants.vgrp)){
//                            b=i;
//                            }
                        }
                        groupadapter=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,typegroup);
                        groupadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        vgroup.setAdapter(groupadapter);
                        int a =groupadapter.getPosition(ApplicationConstants.vehiclegroup);
                        vgroup.setSelection(a);
                    }

                });
    }

    public void GetVTypeslist(int Id){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getContext()).getrestadapter()
                .GetGroups(Id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ConfigResponse>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully SignUp");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<ConfigResponse> responce) {
                        res1 = responce;

                        for (int i = 0; i < res1.size(); i++) {
                            vtypes.add(res1.get(i).getName());
                        }

                        arlist=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,vtypes);
                        arlist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        vtype.setAdapter(arlist);
                        int b =arlist.getPosition(ApplicationConstants.vehicleType);
                        vtype.setSelection(b);
                        //spinnergametype.setAdapter(arlist);
                    }
                });
    }

    public void GetActiveCountries(int active){
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getContext()).getrestadapter()
                .GetActiveCountry(active)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ActiveCountries>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully Registered");
                        //StopDialogue();
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            //DisplayToast("Error");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List <ActiveCountries> list) {
                        res2 = list;
                        for (int i = 0; i < res2.size(); i++) {
                            ctry.add(res2.get(i).getName());
                        }

                        countries = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, ctry);
                        countries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        country.setAdapter(countries);
                        int c =countries.getPosition(ApplicationConstants.country);
                        country.setSelection(c);
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
        Spinner vgroup = (Spinner)parent;
        Spinner vtype = (Spinner)parent;
        Spinner country = (Spinner)parent;
        if(country.getId() == R.id.s_country)
        {
            // Toast.makeText(this, "Your are selected :" + res.get(position).getName(),Toast.LENGTH_SHORT).show();
            ct=position;
            ApplicationConstants.countryid=res2.get(ct).getId();
        }
        if(vgroup.getId() == R.id.s_vgroup)
        {
            // Toast.makeText(this, "Your are selected :" + res.get(position).getName(),Toast.LENGTH_SHORT).show();
            grp=position;
            ApplicationConstants.vgrp=String.valueOf(res.get(grp).getId());
        }
        if(vtype.getId() == R.id.s_vtype)
        {
            //  Toast.makeText(this, "Your are selected :" + res1.get(position).getName(),Toast.LENGTH_SHORT).show();
            type=position;
            ApplicationConstants.vtype=String.valueOf(res1.get(type).getId());
        }
    }

    @Override
    public void onNothingSelected(AdapterView <?> parent) {

    }

}
