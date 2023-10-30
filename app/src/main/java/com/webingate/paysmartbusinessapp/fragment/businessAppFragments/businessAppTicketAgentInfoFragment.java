package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.utils.Utils;

import butterknife.BindView;
import cropper.CropImage;
import cropper.CropImageView;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppTicketAgentInfoFragment extends Fragment {

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
    @BindView(R.id.edituserphoto)
    ImageView userphoto;
    Toast toast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.businessapp_newticketagent_infofragment, container, false);

//        initData();
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

    public EditText getRegNo() {
        return RegNo;
    }

    public void setRegNo(EditText RegNo) {
        this.RegNo = RegNo;
    }

}
