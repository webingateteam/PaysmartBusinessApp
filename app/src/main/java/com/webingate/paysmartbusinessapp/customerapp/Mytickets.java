package com.webingate.paysmartbusinessapp.customerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.webingate.paysmartbusinessapp.R;
public class Mytickets extends Fragment {

    // Declare Variables
    TicketsAdapter adapter;
    ArrayList<TicketModel> arraylist = new ArrayList<TicketModel>();

    private static final String ARG_SECTION_NUMBER = "section_number";
    @BindView(R.id.search)
    SearchView editsearch;
    @BindView(R.id.listview)
    ListView list;
    @BindView(R.id.layout)
    LinearLayout layout;

    Unbinder unbinder;

    public static Mytickets newInstance(int SectionNumber) {
        Mytickets home = new Mytickets();
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
        unbinder = ButterKnife.bind(this, v);

        // Generate sample data

        // Locate the ListView in listview_main.xml
        TicketModel ticketModel = new TicketModel();
        ticketModel.setTicketNo("11112345");
        ticketModel.setDate("22-05-2016   2:30 AM");
        ticketModel.setSource("Harare train station");
        ticketModel.setDestination("Chapman Golf Club");
        arraylist.add(ticketModel);
        ticketModel.setTicketNo("11112345");
        ticketModel.setDate("22-05-2016   2:30 AM");
        ticketModel.setSource("Harare train station");
        ticketModel.setDestination("Chapman Golf Club");
        arraylist.add(ticketModel);
        ticketModel.setTicketNo("11112345");
        ticketModel.setDate("22-05-2016   2:30 AM");
        ticketModel.setSource("Harare train station");
        ticketModel.setDestination("Chapman Golf Club");
        arraylist.add(ticketModel);
        ticketModel.setTicketNo("11112345");
        ticketModel.setDate("22-05-2016   2:30 AM");
        ticketModel.setSource("Harare train station");
        ticketModel.setDestination("Chapman Golf Club");
        arraylist.add(ticketModel);
        ticketModel.setTicketNo("11112345");
        ticketModel.setDate("22-05-2016   2:30 AM");
        ticketModel.setSource("Harare train station");
        ticketModel.setDestination("Chapman Golf Club");
        arraylist.add(ticketModel);
        // Pass results to ListViewAdapter Class
        adapter = new TicketsAdapter(getContext(), arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch.setVisibility(View.GONE);
       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
             //  ApplicationConstants.travel=stopsArraylist.get(position);
              //  ApplicationConstants.FRAGMENT=ApplicationConstants.BUSLAYOUT;
             //   goPage(ApplicationConstants.FRAGMENT);
            }
        });*/

        return v;
    }

    private void goPage(int page) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (page) {

            case ApplicationConstants.HOME:
                fragmentClass = Home.class;
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