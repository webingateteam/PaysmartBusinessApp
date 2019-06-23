package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.customerappFlightBookingSearchActivity;
import com.webingate.paysmartbusinessapp.activity.businessapp.customerappTrainBookingSearchActivity;
import com.webingate.paysmartbusinessapp.adapter.customerapp_FlightsAdapter;
import com.webingate.paysmartbusinessapp.adapter.customerapp_PopularAdapter;
import com.webingate.paysmartbusinessapp.adapter.customerapp_ProductsAdapter;
import com.webingate.paysmartbusinessapp.adapter.customerapp_PromotionsAdapter;
import com.webingate.paysmartbusinessapp.object.DirectoryHome9CategoryVO;
import com.webingate.paysmartbusinessapp.object.DirectoryHome9FlightsVO;
import com.webingate.paysmartbusinessapp.object.DirectoryHome9PopularVO;
import com.webingate.paysmartbusinessapp.object.DirectoryHome9ProductsVO;
import com.webingate.paysmartbusinessapp.object.DirectoryHome9PromotionsVO;
import com.webingate.paysmartbusinessapp.repository.directory.DirectoryHome9Repository;
import com.webingate.paysmartbusinessapp.utils.Utils;
import com.webingate.paysmartbusinessapp.utils.ViewAnimationUtils;

import java.util.List;

import butterknife.BindView;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppVehicleDocsFragment extends Fragment {


    ImageView sizeUpDownImageView;
    private View sizeLayout;
//    @BindView(R.id.priceRangeToTitleValueTextView)
//    EditText pricetitlevalue;
//
//    @BindView(R.id.priceRangeTitleTextView)
//    EditText pricetitle;
//
//    ImageView priceRangeUpDownImageView;
//
//    @BindView(R.id.priceRangeLayout)
//    ImageView pricerange;
//    @BindView(R.id.minimumEditText2)
//    EditText editsearch;

//    List<DirectoryHome9ProductsVO> productsList;
//    List<DirectoryHome9CategoryVO> categoryList;
//    List<DirectoryHome9PromotionsVO> promotionsList;
//    List<DirectoryHome9PopularVO> popularList;
//    List<DirectoryHome9FlightsVO> flightsList;
//
//    customerapp_ProductsAdapter productsAdapter;
//   // AppDirectoryHome9CategoryAdapter categoryAdapter;
//    customerapp_PromotionsAdapter promotionsAdapter;
//    customerapp_PopularAdapter popularAdapter;
//    customerapp_FlightsAdapter flightsAdapter;
//
//    RecyclerView rvProduct, rvCategory, rvPromotions, rvPopular, rvFlights;
//
//    ImageView moreImageView, moreImageView2, profileImageView;
//
//    TextView loginRegisterTextView;
//
//    int noOfProductColumn = 5;
//    int noOfPopularColumn = 2;
//
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.businessapp_newvehicledocs_fragment, container, false);

//        initData();
//
          initUI(view);
//
//        initDataBindings();
//
        initActions();

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

//        priceRangeUpDownImageView = (ImageView) getActivity().findViewById(R.id.priceRangeUpDownImageView);
//        pricetitle = (EditText)getActivity().findViewById(R.id.priceRangeTitleTextView);
//        pricetitlevalue = (EditText)getActivity().findViewById(R.id.priceRangeToTitleValueTextView);
//        pricerange = (ImageView)getActivity().findViewById(R.id.priceRangeLayout);
//
//        editsearch = (EditText)getActivity().findViewById(R.id.minimumEditText2);

        sizeUpDownImageView  = (ImageView) getActivity().findViewById(R.id.sizeUpDownImageView);
     //   sizeLayout = getActivity().findViewById(R.id.sizeLayout);

       // sizeLayout.setVisibility(View.GONE);

    }

//    private void initDataBindings() {
//        int leftImageId = R.drawable.baseline_arrow_right_24;
//        int profileImageId = R.drawable.home9_profile;
//
//        Utils.setImageToImageView(getContext(), moreImageView, leftImageId);
//        Utils.setImageToImageView(getContext(), moreImageView2, leftImageId);
//      //  Utils.setImageToImageView(getContext(), profileImageView, profileImageId);
//
//       // profileImageView.setOnClickListener(view -> Toast.makeText(getContext(), "Clicked : Profile", Toast.LENGTH_SHORT).show());
//
//       // loginRegisterTextView.setOnClickListener(view -> Toast.makeText(getContext(), "Clicked : Log in and Register", Toast.LENGTH_SHORT).show());
//    }
//
    private void initActions() {

//        sizeUpDownImageView.setOnClickListener((View v) -> {
//            boolean show = Utils.toggleUpDownWithAnimation(v);
//            if (show) {
//                ViewAnimationUtils.expand(sizeLayout);
//            } else {
//                ViewAnimationUtils.collapse(sizeLayout);
//            }
//        });

    }


}
