package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.utils.Utils;

import butterknife.BindView;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppFleetOwnerInfoFragment extends Fragment {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String UserAccountNumber = "UserAccountNo";
    public static final String usertypeid = "usertypeid";
    public static final String gender = "GenderKey";
    public static final String Address = "AddressKey";

    ImageView profileImageView;
    @BindView(R.id.s_name)
    EditText name;
    @BindView(R.id.s_email)
    EditText email;
    @BindView(R.id.s_mobileno)
    EditText mbno;
    @BindView(R.id.s_address)
    EditText address;
    @BindView(R.id.s_city)
    EditText city;
    @BindView(R.id.s_postal)
    EditText postal;
    @BindView(R.id.s_state)
    EditText state;
    Toast toast;
    String email1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.businessapp_newfleetowner_infofragment, container, false);

//        initData();
//
        initUI(view);
//
//        initDataBindings();
//
//        initActions();
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
        int id = R.drawable.profile2;
        Utils.setCornerRadiusImageToImageView(view.getContext(), profileImageView, id, 20, 2, R.color.md_white_1000);
        setname((EditText) view.findViewById(R.id.s_name));

        name = view.findViewById(R.id.s_name);
        email = view.findViewById(R.id.s_email);
        mbno = view.findViewById(R.id.s_mobileno);
        address = view.findViewById(R.id.s_address);
        city = view.findViewById(R.id.s_city);
        postal = view.findViewById(R.id.s_postal);
        state = view.findViewById(R.id.s_state);
        name.setText(ApplicationConstants.username);
        email.setText(ApplicationConstants.email);
        mbno.setText(ApplicationConstants.mobileNo);
        address.setText(ApplicationConstants.address);
        city.setText(ApplicationConstants.gender);
    }

    public EditText getName() {
        return name;
    }

    public void setname(EditText name) {
        this.name = name;
    }

}
