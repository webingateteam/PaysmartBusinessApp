package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.rilixtech.CountryCodePicker;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.RegisterBusinessUsers;
import com.webingate.paysmartbusinessapp.activity.businessapp.businessappNewDriverActivity;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.ActiveCountries;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cropper.CropImage;
import cropper.CropImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppDriverUserInfoFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    ImageView profileImageView;
    @BindView(R.id.s_name)
    EditText name;
    @BindView(R.id.s_email)
    EditText email;
    @BindView(R.id.s_address)
    EditText address;
    @BindView(R.id.s_city)
    EditText city;
    @BindView(R.id.s_mobileno)
    EditText mno;
    @BindView(R.id.s_postal)
    EditText postal;
    @BindView(R.id.s_state)
    EditText state;
    Toast toast;
    @BindView(R.id.edituserphoto)
    ImageView userphoto;

    Spinner country;


    List<ActiveCountries> res2;

    ArrayAdapter countries;

    List<String> ctry = new ArrayList<String>();

    int ct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.businessapp_newdriver_userinfofragment, container, false);

        country = view.findViewById(R.id.s_country);
        country.setOnItemSelectedListener(this);
        initData();
//
        initUI(view);
//
//        initDataBindings();
//
        initActions(view);
//
        return view;
    }
//
//    private void initData() {
//        productsList = DirectoryHome9Repository.getfleetownerList();
//        categoryList = DirectoryHome9Repository.getCategoryList();
//        promotionsList = DirectoryHome9Repository.getPromotionsList();
//        popularList = DirectoryHome9Repository.getPopularList();
//        flightsList = DirectoryHome9Repository.getFlightsList();
//    }
//
    private void initUI(View view) {

        profileImageView = view.findViewById(R.id.profileImageView);
        int id = R.drawable.home9_profile;
        Utils.setCornerRadiusImageToImageView(view.getContext(), profileImageView, id, 20, 2,  R.color.md_white_1000);

        //setName((EditText)view.findViewById(R.id.s_name));

        name = view.findViewById(R.id.s_name);
        email = view.findViewById(R.id.s_email);
        mno = view.findViewById(R.id.s_mobileno);
        address = view.findViewById(R.id.s_address);
        city = view.findViewById(R.id.s_city);
        postal = view.findViewById(R.id.s_postal);
        state = view.findViewById(R.id.s_state);
        userphoto = view.findViewById(R.id.edituserphoto);
    }
    private void initActions(View view) {


        userphoto.setOnClickListener((View v) -> {
            Toast.makeText(getActivity(), "Clicked on Pen of Profile", Toast.LENGTH_SHORT).show();
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

    public void initData(){
        GetActiveCountries(1);
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
//                        List<ActiveCountries> response= list ;
//                        int countrycount = response.size();
//                        if (countrycount == 0) {
//                            DisplayToast("Please configure countries of operation.");
//                        } else {
//
//                            String countriesList = "";
//                            for (int i = 0; i < countrycount; i++) {
//                                if (i == countrycount - 1)
//                                    countriesList += response.get(i).getISOCode();
//                                else
//                                    countriesList += response.get(i).getISOCode() + ",";
//                            }
//                            ccp.setCustomMasterCountries(countriesList);
//                        }

                        for (int i = 0; i < res2.size(); i++) {
                            ctry.add(res2.get(i).getName());
                        }

                        countries = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, ctry);
                        countries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        country.setAdapter(countries);


//                            ccp = (CountryCodePicker) findViewById(R.id.ccp);
//                            ccp.setCustomMasterCountries(response.getISOCode());
//                            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedpreferences.edit();
//                            editor.putString(Isocode, response.getISOCode());
//                            editor.commit();
                    }
                });
    }


    public EditText getName() {
        return name;
    }

    public void setName(EditText name) {
        this.name = name;
    }

    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
        Spinner country = (Spinner)parent;

        if(country.getId() == R.id.s_country)
        {
            // Toast.makeText(this, "Your are selected :" + res.get(position).getName(),Toast.LENGTH_SHORT).show();
            ct=position;
            ApplicationConstants.countryid = res2.get(ct).getId();
            ApplicationConstants.countrycode = res2.get(ct).getCountryCode();

        }
    }

    @Override
    public void onNothingSelected(AdapterView <?> parent) {

    }

    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(getContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }

}
