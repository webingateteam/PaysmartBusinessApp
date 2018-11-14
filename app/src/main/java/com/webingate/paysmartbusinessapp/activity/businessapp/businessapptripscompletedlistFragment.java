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

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class businessapptripscompletedlistFragment extends Fragment {
    Toast toast;
    private Context context;
    private int bookingno;
    @BindView(R.id.listView1)
    ListView listView2;
    MyCustomAdapter dataAdapter = null;
    List<GetdriverTripsResponse> list;
    ArrayList <GetdriverTripsResponse> completetripList;
    com.webingate.paysmartbusinessapp.businessapp.Dialog.ProgressDialog dialog ;
    public businessapptripscompletedlistFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.businessapp_driverstripscompletedlist_tabfragment, container, false);

        initData();

        initUI();

        //  initDataBindings();

        initActions();

        return view;
    }


    private void initData() {
        String mb= com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants.mobileNo;
        int tt=2;
        GetDrivercompleteTrips(mb,tt);
    }

    private void initUI() {
    }

    private void initDataBinding() {
    }

    private void initActions() {
    }

    private void displayListView1() {


        //create an ArrayAdaptar from the String Array

        dataAdapter = new MyCustomAdapter(getActivity(),R.layout.layout_tripscustom, completetripList);

        // Assign adapter to ListView
        dataAdapter.notifyDataSetChanged();
        ListView listView = (ListView) getActivity().findViewById(R.id.listView1);
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //dataAdapter.getPosition(GetdriverTripsResponse tripModel);

               // GetdriverTripsResponse tripModel = (GetdriverTripsResponse)list.get(position);
                bookingno =  dataAdapter.getItem(position).getId();
                //bookingno = tripModel.getId();
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
            this.logsselected = completetripList;
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

    public void GetDrivercompleteTrips( String driverNo, int status){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .Getdrivertrips(driverNo,status)
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
                        completetripList= (ArrayList<GetdriverTripsResponse>) trips;
                        DisplayToast("Next to Register");
                        displayListView1();
                        //   SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        //   SharedPreferences.Editor editor = sharedpreferences.edit();
                        //  editor.putString(Emailotp, response.getEmail());
                        //    editor.commit();
                        //startActivity(new Intent(busianessappEOTPVerificationActivity.this, login_activity.class));
                        // DriverList
                        //adapter = new businessappDriverTripslistAdapter(completetripList);
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
