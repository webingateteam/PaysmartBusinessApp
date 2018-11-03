package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.businessappDriversListActivity;
import com.webingate.paysmartbusinessapp.activity.businessapp.businessappStaffListActivity;
import com.webingate.paysmartbusinessapp.activity.businessapp.businessappStatisticsActivity;
import com.webingate.paysmartbusinessapp.activity.businessapp.businessappTicketAgentListActivity;
import com.webingate.paysmartbusinessapp.activity.businessapp.businessappVehicleListActivity;
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

import java.util.List;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppFleetownerFragment extends Fragment  {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String UserAccountNo = "UserAccountNo";
    List<DirectoryHome9ProductsVO> productsList;
    List<DirectoryHome9CategoryVO> categoryList;
    List<DirectoryHome9PromotionsVO> promotionsList;
    List<DirectoryHome9PopularVO> popularList;
    List<DirectoryHome9FlightsVO> flightsList;

    customerapp_ProductsAdapter productsAdapter;
   // AppDirectoryHome9CategoryAdapter categoryAdapter;
    customerapp_PromotionsAdapter promotionsAdapter;
    customerapp_PopularAdapter popularAdapter;
    customerapp_FlightsAdapter flightsAdapter;

    RecyclerView rvProduct, rvCategory, rvPromotions, rvPopular, rvFlights;

    ImageView moreImageView, moreImageView2, profileImageView;

    TextView loginRegisterTextView;

    int noOfProductColumn = 5;
    int noOfPopularColumn = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.customerapp_directory_home_fragment, container, false);

        initData();

        initUI(view);

        initDataBindings();

        initActions();

        return view;
    }

    private void initData() {
        productsList = DirectoryHome9Repository.getfleetownerList();
        categoryList = DirectoryHome9Repository.getCategoryList();
        promotionsList = DirectoryHome9Repository.getPromotionsList();
        popularList = DirectoryHome9Repository.getPopularList();
        flightsList = DirectoryHome9Repository.getFlightsList();
    }

    private void initUI(View view) {

        productsAdapter = new customerapp_ProductsAdapter(productsList);
       // categoryAdapter = new AppDirectoryHome9CategoryAdapter(categoryList);
        promotionsAdapter = new customerapp_PromotionsAdapter(promotionsList);
        popularAdapter = new customerapp_PopularAdapter(popularList);
        flightsAdapter = new customerapp_FlightsAdapter(flightsList);

        if (getActivity() != null) {

            rvProduct = view.findViewById(R.id.rvProducts);
           // rvCategory = view.findViewById(R.id.rvCategory);
            rvPromotions = view.findViewById(R.id.rvPromotions);
            rvPopular = view.findViewById(R.id.rvPopular);
            rvFlights = view.findViewById(R.id.rvFlights);

            RecyclerView.LayoutManager productLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), noOfProductColumn);
            rvProduct.setLayoutManager(productLayoutManager);
            rvProduct.setAdapter(productsAdapter);
    //        rvProduct.setOnClickListener();

           // RecyclerView.LayoutManager categoryLayoutManger = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
           // rvCategory.setLayoutManager(categoryLayoutManger);
           // rvCategory.setAdapter(categoryAdapter);

            RecyclerView.LayoutManager promotionsLayoutManger = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            rvPromotions.setLayoutManager(promotionsLayoutManger);
            rvPromotions.setAdapter(promotionsAdapter);

            RecyclerView.LayoutManager popularLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), noOfPopularColumn);
            rvPopular.setLayoutManager(popularLayoutManager);
            rvPopular.setAdapter(popularAdapter);

            RecyclerView.LayoutManager flightsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            rvFlights.setLayoutManager(flightsLayoutManager);
            rvFlights.setAdapter(flightsAdapter);
        }

        moreImageView = view.findViewById(R.id.moreImageView);
        moreImageView2 = view.findViewById(R.id.moreImageView2);
        //profileImageView = view.findViewById(R.id.home9ProfileImageView);
        //loginRegisterTextView = view.findViewById(R.id.loginRegisterTextView);
    }

    private void initDataBindings() {
        int leftImageId = R.drawable.baseline_arrow_right_24;
        int profileImageId = R.drawable.home9_profile;

        Utils.setImageToImageView(getContext(), moreImageView, leftImageId);
        Utils.setImageToImageView(getContext(), moreImageView2, leftImageId);
      //  Utils.setImageToImageView(getContext(), profileImageView, profileImageId);

       // profileImageView.setOnClickListener(view -> Toast.makeText(getContext(), "Clicked : Profile", Toast.LENGTH_SHORT).show());

       // loginRegisterTextView.setOnClickListener(view -> Toast.makeText(getContext(), "Clicked : Log in and Register", Toast.LENGTH_SHORT).show());
    }

    private void initActions() {
        //productsAdapter.setOnItemClickListener((view, product, position) -> Toast.makeText(getContext(), "Clicked : " + product.getName(), Toast.LENGTH_SHORT).show());
        productsAdapter.setOnItemClickListener((View view, DirectoryHome9ProductsVO promotion, int position) -> {

            SharedPreferences prefs = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String usan = prefs.getString(UserAccountNo, null);


//            SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE); // 0 - for private mode
//            String usan = (pref.getString("UserAccountNo", null));

                switch(position){
                    case 0:
                       // ApplicationConstants.marker = R.mipmap.marker_taxi;

                        Intent intent = new Intent(getActivity(), businessappDriversListActivity.class);
                        intent.putExtra("UserAccountNo", usan);
                        intent.putExtra("usertypeid", 109);
//                        intent.putExtra("uid", response.getusreid());
//                        Intent intent = new Intent(getActivity(), businessappDriversListActivity.class);
                       startActivity(intent);
                        break;
                    case 1:
                         intent = new Intent(getActivity(), businessappVehicleListActivity.class);
                        startActivity(intent);
//                        AppDirectoryHome1Fragment af1 = new AppDirectoryHome1Fragment();
//
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.home9Frame, af1)
//                                .commitAllowingStateLoss();
                        break;
                    case 2:


                        break;
                    case 3:
                        intent = new Intent(getActivity(), businessappStatisticsActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getActivity(), businessappTicketAgentListActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(getActivity(), businessappStaffListActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        break;
                }
            //Toast.makeText(getContext(), "Clicked : " + promotion.getName(), Toast.LENGTH_SHORT).show();
        });


        //    categoryAdapter.setOnItemClickListener((view, category, position) -> Toast.makeText(getContext(), "Clicked : " + category.getName(), Toast.LENGTH_SHORT).show());

        promotionsAdapter.setOnItemClickListener((view, promotion, position) -> {
            if (position == 0)
                Toast.makeText(getContext(), "Clicked : See All Promos", Toast.LENGTH_SHORT).show();
            else
            {
                switch(position){
                    case 1:
//                        AppDirectoryHome1Fragment af = new AppDirectoryHome1Fragment();
//
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.home9Frame, af)
//                                .commitAllowingStateLoss();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        break;
                }

            }
                //Toast.makeText(getContext(), "Clicked : " + promotion.getName(), Toast.LENGTH_SHORT).show();
        });

        popularAdapter.setOnItemClickListener((view, popular, position) -> Toast.makeText(getContext(), "Clicked : " + popular.getName(), Toast.LENGTH_SHORT).show());

        flightsAdapter.setOnItemClickListener((view, flight, position) -> Toast.makeText(getContext(), "Clicked : " + flight.getCountry(), Toast.LENGTH_SHORT).show());

    }


}
