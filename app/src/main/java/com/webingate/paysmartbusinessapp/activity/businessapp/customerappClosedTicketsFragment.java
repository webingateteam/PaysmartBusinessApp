package com.webingate.paysmartbusinessapp.activity.businessapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webingate.paysmartbusinessapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class customerappClosedTicketsFragment extends Fragment {

    private TextView viewDetailsTextView1;
    private TextView viewDetailsTextView2;
    private TextView viewDetailsTextView3;
    private TextView viewDetailsTextView4;
    public customerappClosedTicketsFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.customerapp_closedtickets_tabfragment, container, false);

        initData();

        initUI(view);

      //  initDataBindings();

        initActions();

        return view;
    }


    private void initData() {

    }

    private void initUI(View view) {
        //initToolbar();

        viewDetailsTextView1 = view.findViewById(R.id.viewDetailsTextView1);
        viewDetailsTextView2 = view.findViewById(R.id.viewDetailsTextView2);
        viewDetailsTextView3 = view.findViewById(R.id.viewDetailsTextView3);
        viewDetailsTextView4 = view.findViewById(R.id.viewDetailsTextView4);

    }

    private void initDataBinding() {

    }

    private void initActions() {

        viewDetailsTextView1.setOnClickListener(view -> {
            //Toast.makeText(getActivity().getApplicationContext(), "Click View Details", Toast.LENGTH_SHORT).show();
            GoToDetails();
        });

        viewDetailsTextView2.setOnClickListener(view -> {
            //  Toast.makeText(getActivity().getApplicationContext(), "Click View Details", Toast.LENGTH_SHORT).show();
            GoToDetails();
        });

        viewDetailsTextView3.setOnClickListener(view -> {
            //Toast.makeText(getActivity().getApplicationContext(), "Click View Details", Toast.LENGTH_SHORT).show();
            GoToDetails();
        });

        viewDetailsTextView4.setOnClickListener(view -> {
            //Toast.makeText(getActivity().getApplicationContext(), "Click View Details", Toast.LENGTH_SHORT).show();
            GoToDetails();
        });
    }
    private void GoToDetails(){
        Intent intent = new Intent(this.getActivity(), customerAppTTicketTimelineActivity.class);
        startActivity(intent);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.customerapp_closedtickets_tabfragment, container, false);
//    }

}
