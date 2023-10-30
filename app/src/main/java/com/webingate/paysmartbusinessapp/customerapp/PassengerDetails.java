package com.webingate.paysmartbusinessapp.customerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import com.webingate.paysmartbusinessapp.R;
@SuppressLint("NewApi")
public class PassengerDetails extends Fragment {
    //TextView name,time,subtitle,price,seatsselected;
    private static final String ARG_SECTION_NUMBER = "section_number";
    TableRow tableRow[]=new TableRow[5];
    EditText names[]=new EditText[5];
    EditText age[]=new EditText[5];
    ToggleButton gender[]=new ToggleButton[5];
    Button payment;
    ArrayList selected=new ArrayList();
    public static PassengerDetails newInstance(int SectionNumber) {
        PassengerDetails home = new PassengerDetails();
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
        View v = inflater.inflate(R.layout.passengers_details, container, false);
        tableRow[0]= (TableRow) v.findViewById(R.id.tabrow1);
        tableRow[1]= (TableRow) v.findViewById(R.id.tabrow2);
        tableRow[2]= (TableRow) v.findViewById(R.id.tabrow3);
        tableRow[3]= (TableRow) v.findViewById(R.id.tabrow4);
        tableRow[4]= (TableRow) v.findViewById(R.id.tabrow5);
        names[0]= (EditText) v.findViewById(R.id.name1);
        names[1]= (EditText) v.findViewById(R.id.name2);
        names[2]= (EditText) v.findViewById(R.id.name3);
        names[3]= (EditText) v.findViewById(R.id.name4);
        names[4]= (EditText) v.findViewById(R.id.name5);
        age[0]= (EditText) v.findViewById(R.id.age1);
        age[1]= (EditText) v.findViewById(R.id.age2);
        age[2]= (EditText) v.findViewById(R.id.age3);
        age[3]= (EditText) v.findViewById(R.id.age4);
        age[4]= (EditText) v.findViewById(R.id.age5);
        gender[0]= (ToggleButton) v.findViewById(R.id.gender1);
        gender[1]= (ToggleButton) v.findViewById(R.id.gender2);
        gender[2]= (ToggleButton) v.findViewById(R.id.gender3);
        gender[3]= (ToggleButton) v.findViewById(R.id.gender4);
        gender[4]= (ToggleButton) v.findViewById(R.id.gender5);
        payment= (Button) v.findViewById(R.id.btn_payments);

        for(int i=5;i>ApplicationConstants.seatsSelected.size();i--){
            tableRow[i-1].setVisibility(View.GONE);
        }
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationConstants.FRAGMENT=ApplicationConstants.PAYMENTS;
                goPage(ApplicationConstants.FRAGMENT);
            }
        });
        return v;
    }

    private void goPage(int page) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;
        switch(page){
            case ApplicationConstants.HOME:
                fragmentClass = Home.class;
                break;
            case ApplicationConstants.TRAVELS:
                fragmentClass = Travels.class;
                break;
            case ApplicationConstants.PAYMENTS:
                fragmentClass = Payments.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       /* fragmentTransaction.setCustomAnimations(
                R.anim.rotate_forward,
                R.anim.rotate_backward);*/
        fragmentTransaction.replace(R.id.flContent, fragment);
        fragmentTransaction.commit();
    }
}
