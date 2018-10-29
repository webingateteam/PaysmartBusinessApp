package com.webingate.paysmartbusinessapp.customerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.webingate.paysmartbusinessapp.R;
public class Travels extends Fragment {

    // Declare Variables

    TravelsAdapter adapter;



    private static final String ARG_SECTION_NUMBER = "section_number";

    @BindView(R.id.search)
    SearchView editsearch;
    @BindView(R.id.listview)
    ListView list;
    @BindView(R.id.layout)
    LinearLayout layout;
    Unbinder unbinder;

    public static Travels newInstance(int SectionNumber) {
        Travels home = new Travels();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, SectionNumber);
        home.setArguments(args);
        return home;
    }

    @SuppressLint("NewApi")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.stops, container, false);


        // Generate sample data

        // Locate the ListView in listview_main.xml
        list = (ListView) v.findViewById(R.id.listview);
           /* TravelModel travelModel=new TravelModel();
        travelModel.setName("Alhind Tours And Travels");
        travelModel.setTime("6:30AM");
        travelModel.setSubTitle("VOLVO AC MULTIAXLE SEMI SLEEPER 2+2");
        travelModel.setPrice("1100$");
            arraylist.add(travelModel);
         travelModel=new TravelModel();
        travelModel.setName("Khallada Tours And Travels");
        travelModel.setTime("9:30AM");
        travelModel.setSubTitle("VOLVO AC  SLEEPER 2+2");
        travelModel.setPrice("1900$");
        arraylist.add(travelModel);
         travelModel=new TravelModel();
        travelModel.setName("Orange Tours And Travels");
        travelModel.setTime("8:30AM");
        travelModel.setSubTitle("VOLVO AC MULTIAXLE SEMI SLEEPER 2+2");
        travelModel.setPrice("1200$");
        arraylist.add(travelModel);
         travelModel=new TravelModel();
        travelModel.setName("Deepak Tours And Travels");
        travelModel.setTime("3:30AM");
        travelModel.setSubTitle("VOLVO NON AC SEATER 2+2");
        travelModel.setPrice("800$");
        arraylist.add(travelModel);
         travelModel=new TravelModel();
        travelModel.setName("Laxman Tours And Travels");
        travelModel.setTime("7:30AM");
        travelModel.setSubTitle("VOLVO AC MULTIAXLE SEMI SLEEPER 2+2");
        travelModel.setPrice("1100$");
        arraylist.add(travelModel);
         travelModel=new TravelModel();
        travelModel.setName("Suneel Tours And Travels");
        travelModel.setTime("6:50AM");
        travelModel.setSubTitle("VOLVO AC MULTIAXLE SEMI SLEEPER 2+2");
        travelModel.setPrice("1100$");
        arraylist.add(travelModel);*/
        // Pass results to ListViewAdapter Class
        adapter = new TravelsAdapter(getContext(), ApplicationConstants.travelsArraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch.setVisibility(View.GONE);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                ApplicationConstants.travel = ApplicationConstants.travelsArraylist.get(position);
                ApplicationConstants.FRAGMENT = ApplicationConstants.BUSLAYOUT;
                goPage(ApplicationConstants.FRAGMENT);
            }
        });
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    private void goPage(int page) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (page) {

            case ApplicationConstants.BUSLAYOUT:
                fragmentClass = BusLayout.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       /* fragmentTransaction.setCustomAnimations(
                R.anim.rotate_forward,
                R.anim.rotate_backward);*/
        fragmentTransaction.replace(R.id.flContent, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}