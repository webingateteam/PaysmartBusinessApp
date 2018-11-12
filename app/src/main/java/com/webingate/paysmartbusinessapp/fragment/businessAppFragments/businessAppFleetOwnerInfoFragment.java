package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

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
import com.webingate.paysmartbusinessapp.utils.Utils;

import butterknife.BindView;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppFleetOwnerInfoFragment extends Fragment {

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
    Toast toast;

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
        Utils.setCornerRadiusImageToImageView(view.getContext(), profileImageView, id, 20, 2,  R.color.md_white_1000);
        setRegNo((EditText)view.findViewById(R.id.s_Regno));

        RegNo = view.findViewById(R.id.s_Regno);
        chasisno = view.findViewById(R.id.s_chasisno);
        engineno = view.findViewById(R.id.s_engineno);
        vgroup = view.findViewById(R.id.s_vgroup);
        vtype = view.findViewById(R.id.s_vtype);
        modelyear = view.findViewById(R.id.s_modelyear);
        state = view.findViewById(R.id.s_state);
    }

    public EditText getRegNo() {
        return RegNo;
    }

    public void setRegNo(EditText RegNo) {
        this.RegNo = RegNo;
    }

}
