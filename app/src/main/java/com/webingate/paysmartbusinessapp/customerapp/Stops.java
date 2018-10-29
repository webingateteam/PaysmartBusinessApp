package com.webingate.paysmartbusinessapp.customerapp;
import com.webingate.paysmartbusinessapp.R;
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

import com.webingate.paysmartbusinessapp.businessapp.Deo.CustomerGetstopsResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Stops extends Fragment implements SearchView.OnQueryTextListener {
    // Declare Variables
    ListViewAdapter adapter;
    //String[] stopsList;
    private static final String ARG_SECTION_NUMBER = "section_number";

    @BindView(R.id.search)
    SearchView editsearch;
    @BindView(R.id.listview)
    ListView list;
    @BindView(R.id.layout)
    LinearLayout layout;
    Unbinder unbinder;
    List<CustomerGetstopsResponse> stopslist;
    public static Stops newInstance(int SectionNumber) {
        Stops home = new Stops();
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
        stopslist= (List<CustomerGetstopsResponse>) savedInstanceState.getSerializable("details");
        unbinder = ButterKnife.bind(this, v);
        super.onCreate(savedInstanceState);

        // Generate sample data


        // Locate the ListView in listview_main.xml
        list = (ListView) v.findViewById(R.id.listview);


        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(getContext(),stopslist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //   Toast.makeText(getContext(), stopsList[position], Toast.LENGTH_SHORT).show();
                if (ApplicationConstants.source.matches("")) {
                    ApplicationConstants.source =stopslist.get(position).getName();
                    ApplicationConstants.sourceid = Integer.parseInt(stopslist.get(position).getId());
                    ApplicationConstants.FRAGMENT = ApplicationConstants.TICKET_SOURCE_DESTINATION;
                    goPage(ApplicationConstants.FRAGMENT);
                } else {
                    ApplicationConstants.destination = stopslist.get(position).getName();
                    ApplicationConstants.destinationid = Integer.parseInt(stopslist.get(position).getId());
                    ApplicationConstants.FRAGMENT = ApplicationConstants.TICKET_SOURCE_DESTINATION;
                    goPage(ApplicationConstants.FRAGMENT);
                }
            }
        });

        // Locate the EditText in listview_main.xml
        editsearch.setOnQueryTextListener(this);

        return v;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

    private void goPage(int page) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (page) {
            case ApplicationConstants.TICKET_SOURCE_DESTINATION:
                fragmentClass = Ticket_Source_Destination_Date.class;
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