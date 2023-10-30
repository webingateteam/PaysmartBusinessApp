package com.webingate.paysmartbusinessapp.customerapp;
import com.webingate.paysmartbusinessapp.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Iterator;

@SuppressLint("NewApi")
public class BusLayout extends Fragment {
    TextView name,time,subtitle,price,seatsselected;
    private static final String ARG_SECTION_NUMBER = "section_number";
    Button done,seats[][]=new Button[6][4];
    public static BusLayout newInstance(int SectionNumber) {
        BusLayout home = new BusLayout();
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
        View v = inflater.inflate(R.layout.activity_buslayout, container, false);
        name = (TextView) v.findViewById(R.id.name);
        time = (TextView) v.findViewById(R.id.time);
        subtitle = (TextView) v.findViewById(R.id.subtitle);
        price = (TextView) v.findViewById(R.id.price);
        seatsselected=(TextView)v.findViewById(R.id.seats);
        done=(Button)v.findViewById(R.id.next);
        seats[0][0]=(Button)v.findViewById(R.id.seat01);
        seats[0][1]=(Button)v.findViewById(R.id.seat02);
        seats[0][2]=(Button)v.findViewById(R.id.seat03);
        seats[0][3]=(Button)v.findViewById(R.id.seat04);
        seats[1][0]=(Button)v.findViewById(R.id.seat11);
        seats[1][1]=(Button)v.findViewById(R.id.seat12);
        seats[1][2]=(Button)v.findViewById(R.id.seat13);
        seats[1][3]=(Button)v.findViewById(R.id.seat14);
        seats[2][0]=(Button)v.findViewById(R.id.seat21);
        seats[2][1]=(Button)v.findViewById(R.id.seat22);
        seats[2][2]=(Button)v.findViewById(R.id.seat23);
        seats[2][3]=(Button)v.findViewById(R.id.seat24);
        seats[3][0]=(Button)v.findViewById(R.id.seat31);
        seats[3][1]=(Button)v.findViewById(R.id.seat32);
        seats[3][2]=(Button)v.findViewById(R.id.seat33);
        seats[3][3]=(Button)v.findViewById(R.id.seat34);
        seats[4][0]=(Button)v.findViewById(R.id.seat41);
        seats[4][1]=(Button)v.findViewById(R.id.seat42);
        seats[4][2]=(Button)v.findViewById(R.id.seat43);
        seats[4][3]=(Button)v.findViewById(R.id.seat44);
        seats[5][0]=(Button)v.findViewById(R.id.seat51);
        seats[5][1]=(Button)v.findViewById(R.id.seat52);
        seats[5][2]=(Button)v.findViewById(R.id.seat53);
        seats[5][3]=(Button)v.findViewById(R.id.seat54);

        name.setText(ApplicationConstants.travel.getName());
        time.setText(ApplicationConstants.travel.getTime());
        subtitle.setText(ApplicationConstants.travel.getSubTitle());
        price.setText(ApplicationConstants.travel.getPrice());
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ApplicationConstants.seatsSelected.size()>0){
                    ApplicationConstants.FRAGMENT=ApplicationConstants.PASSENGERDETAILS;
                    goPage(ApplicationConstants.FRAGMENT);
                }
            }
        });
        View.OnClickListener seatclicked=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),v.isActivated()+"",Toast.LENGTH_SHORT).show();

                    if (!v.isActivated()) {
                        if (ApplicationConstants.seatsSelected.size() > 4) {
                            Toast.makeText(getContext(),"Maximum 5 seats Only",Toast.LENGTH_SHORT).show();

                        }else {
                            v.setBackgroundResource(R.drawable.seatselected);
                            v.setActivated(true);
                            ApplicationConstants.seatsSelected.add("S" + v.getId());
                            Iterator iterator = ApplicationConstants.seatsSelected.iterator();
                            String m = "";
                            while (iterator.hasNext()) {
                                m = m + iterator.next() + ",";
                            }
                            seatsselected.setText("Seats : " + m.substring(0, m.length()-1));
                        }
                    } else {
                        v.setBackgroundResource(R.drawable.seat);
                        v.setActivated(false);
                        ApplicationConstants.seatsSelected.remove("S" + v.getId());
                        Iterator iterator = ApplicationConstants.seatsSelected.iterator();
                        String m = "";
                        while (iterator.hasNext()) {
                            m = m + iterator.next() + ",";
                        }
                        seatsselected.setText("Seats : " + m.substring(0, m.length()-1));
                    }
                }

        };
        int number=0;
        for(int i=0;i<6;i++)
            for (int j = 0; j < 4; j++) {
                seats[i][j].setOnClickListener(seatclicked);
                number=number+1;
                seats[i][j].setId(number);
            }
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
            case ApplicationConstants.PASSENGERDETAILS:
                fragmentClass = PassengerDetails.class;
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
