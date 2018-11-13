package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessapptripslistFragment extends Fragment {

    private TextView viewDetailsTextView1;
    private TextView viewDetailsTextView2;
    private TextView viewDetailsTextView3;
    private TextView viewDetailsTextView4;

    Toast toast;
    private Context context;
    ListView listView1;
    private String bookingno;
    private String response;

    @BindView(R.id.listView1)
    ListView listView2;


    MyCustomAdapter dataAdapter = null;

    List<GetdriverTripsResponse> list;
    ArrayList <GetdriverTripsResponse> DrivertripList;
    com.webingate.paysmartbusinessapp.businessapp.Dialog.ProgressDialog dialog ;
    public businessapptripslistFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.businessapp_driverstripslist_tabfragment, container, false);

        initData();

        initUI();

        //  initDataBindings();

        initActions();

        return view;
    }


    private void initData() {
        String mb= com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants.mobileNo;
        GetDriverTrips(mb);
    }

    private void initUI() {
        //initToolbar();
//             Intent intent=new Intent(getActivity(),driverlistTrips.class);
//             startActivity(intent);
//        viewDetailsTextView1 = view.findViewById(R.id.viewDetailsTextView1);
//        viewDetailsTextView2 = view.findViewById(R.id.viewDetailsTextView2);
//        viewDetailsTextView3 = view.findViewById(R.id.viewDetailsTextView3);
//        viewDetailsTextView4 = view.findViewById(R.id.viewDetailsTextView4);

    }

    private void initDataBinding() {

    }

    private void initActions() {

//        viewDetailsTextView1.setOnClickListener(view -> {
//            //Toast.makeText(getActivity().getApplicationContext(), "Click View Details", Toast.LENGTH_SHORT).show();
//            GoToDetails();
//        });
//
//        viewDetailsTextView2.setOnClickListener(view -> {
//          //  Toast.makeText(getActivity().getApplicationContext(), "Click View Details", Toast.LENGTH_SHORT).show();
//            GoToDetails();
//        });
//
//        viewDetailsTextView3.setOnClickListener(view -> {
//            //Toast.makeText(getActivity().getApplicationContext(), "Click View Details", Toast.LENGTH_SHORT).show();
//            GoToDetails();
//        });
//
//        viewDetailsTextView4.setOnClickListener(view -> {
//            //Toast.makeText(getActivity().getApplicationContext(), "Click View Details", Toast.LENGTH_SHORT).show();
//            GoToDetails();
//        });
    }

    private void displayListView() {


        //create an ArrayAdaptar from the String Array

        dataAdapter = new MyCustomAdapter(getActivity(),R.layout.layout_tripscustom, DrivertripList);

        // Assign adapter to ListView
        dataAdapter.notifyDataSetChanged();
        ListView listView = (ListView) getActivity().findViewById(R.id.listView1);
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                GetdriverTripsResponse tripModel = (GetdriverTripsResponse)list.get(position);
                bookingno = tripModel.getId();
                JsonObject object = new JsonObject();
                object.addProperty("BNo", bookingno);
                //RideDetails(object);

               /* TripRequest tripRequest = new TripRequest();
                tripRequest.execute();*/
            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<GetdriverTripsResponse> {

        private ArrayList<GetdriverTripsResponse> logsselected;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<GetdriverTripsResponse> logsSelected) {
            super(context, textViewResourceId, logsSelected);
            this.logsselected = DrivertripList;
            this.logsselected.addAll(logsSelected);
        }

        private class ViewHolder {
            TextView time, booking_no, source, destination, price;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                //sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
                LayoutInflater vi = (LayoutInflater)getActivity().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.layout_tripscustom, null);

                holder = new ViewHolder();
                holder.time = (TextView) convertView.findViewById(R.id.time);
                holder.price = (TextView) convertView.findViewById(R.id.price);
                holder.booking_no = (TextView) convertView.findViewById(R.id.booking_no);
                holder.source = (TextView) convertView.findViewById(R.id.source);
                holder.destination = (TextView) convertView.findViewById(R.id.destination);
                convertView.setTag(holder);

	   /* holder.code.setOnClickListener( new View.OnClickListener() {
		     public void onClick(View v) {
		      TextView tb = (TextView) v ;
		      LogModel log =new LogModel();
		    		  log= (LogModel) tb.getTag();
		      Toast.makeText(getApplicationContext(),"Clicked On Log" + log.getName()+" "+log.getDate()+"/"+log.getMonth()+"/"+log.getYear(),
		      Toast.LENGTH_LONG).show();
		      //log.setSelected(cb.isChecked());
		     }
		    }); */
            } else {
                holder = (MyCustomAdapter.ViewHolder) convertView.getTag();
            }

            GetdriverTripsResponse logs = logsselected.get(position);
            // holder.code.setText(" (" +  logs.getCode() + ")");
            holder.time.setText(logs.getBookedDate());
            holder.price.setText("$ " + logs.getAmount());
            holder.booking_no.setText("Booking No " + logs.getId());
            holder.source.setText(logs.getSrc());

            holder.destination.setText(logs.getDest());
            return convertView;

        }

    }

    public void GetDriverTrips( String driverNo){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .Getdrivertrips(driverNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetdriverTripsResponse>>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully get Drivers Trip list");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
//                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<GetdriverTripsResponse> trips) {
                        DrivertripList= (ArrayList<GetdriverTripsResponse>) trips;
                        DisplayToast("Next to Register");
                        displayListView();
                        //   SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        //   SharedPreferences.Editor editor = sharedpreferences.edit();
                        //  editor.putString(Emailotp, response.getEmail());
                        //    editor.commit();
                        //startActivity(new Intent(busianessappEOTPVerificationActivity.this, login_activity.class));
                        // DriverList
                        //adapter = new businessappDriverTripslistAdapter(DrivertripList);
                        //recyclerView.setAdapter(adapter);

//                       // adapter.setOnItemClickListener((view, obj, position) ->
//                                {
//                                    //Toast.makeText(this, "Selected : " + obj.getNAme(), Toast.LENGTH_LONG).show();
//
//                                    GoToDetails(obj);
//                                }
//                        );
                    }
                });
    }
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT);
        toast.show();

    }
    public void StartDialogue(){

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    public void StopDialogue(){
        if(dialog.isShowing()){
            dialog.cancel();
        }

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
