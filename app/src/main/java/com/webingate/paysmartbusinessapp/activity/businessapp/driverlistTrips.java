package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.adapter.businessappDriverTripslistAdapter;
import com.webingate.paysmartbusinessapp.businessapp.Deo.CustomerRideDetailsResponse;
import com.webingate.paysmartbusinessapp.businessapp.Deo.GetBookingHistoryResponse;
import com.webingate.paysmartbusinessapp.businessapp.Dialog.ProgressDialog;
import com.webingate.paysmartbusinessapp.customerapp.ApplicationConstants;
import com.webingate.paysmartbusinessapp.customerapp.ConfirmedTripsDetails;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class driverlistTrips extends AppCompatActivity {
    MyCustomAdapter dataAdapter = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listView1)
    ListView listView1;
    private int bookingno;
    private String response;

    Toast toast;
    ProgressDialog dialog ;
    List<GetdriverTripsResponse> list;
    List<GetdriverTripsResponse> list1;
    ArrayList <GetdriverTripsResponse> DrivertripList;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApplicationConstants.tripFlag = 3;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.confirmed_trips);
        setContentView(R.layout.businessapp_driverstripslist_tabfragment);
        ButterKnife.bind(this);

        dialog =  new ProgressDialog.Builder(driverlistTrips.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        // adding toolbar
        setSupportActionBar(toolbar);
        initData();

        // you can title and subtitle dynamically
        //getSupportActionBar().setTitle("Payments");
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Generate list View from ArrayList

    }

    private void initData() {
        String mb= com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants.mobileNo;
        GetDriverTrips(mb);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        return true;
    }

    @SuppressWarnings("unchecked")
    private void displayListView() {


        //create an ArrayAdaptar from the String Array

        dataAdapter = new MyCustomAdapter(this,R.layout.layout_tripscustom, DrivertripList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        dataAdapter.notifyDataSetChanged();
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
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
                LayoutInflater vi = (LayoutInflater) getSystemService(
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
                holder = (ViewHolder) convertView.getTag();
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

//    public void RideDetails(JsonObject jsonObject){
//
//        StartDialogue();
//        com.webingate.paysmartbusinessapp.businessapp.Utils.DataPrepare.get(driverlistTrips.this).getrestadapter()
//                .RideDetails(jsonObject)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<CustomerRideDetailsResponse>>() {
//                    @Override
//                    public void onCompleted() {
//                        //  DisplayToast("Successfully Registered");
//                        StopDialogue();
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        try {
//                            Log.d("OnError ", e.getMessage());
//                            DisplayToast("Error");
//                            StopDialogue();
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onNext(List<CustomerRideDetailsResponse> responselist) {
//                        CustomerRideDetailsResponse response=responselist.get(0);
//                        Intent intent = new Intent(driverlistTrips.this, ConfirmedTripsDetails.class);
//                        intent.putExtra("details", response);
//                        startActivity(intent);
//
//                    }
//                });
//    }

    public void GetDriverTrips( String driverNo){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .Getdrivertrips(driverNo,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetdriverTripsResponse>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully get Drivers Trip list");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            //DisplayToast("Unable to Register");
//                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<GetdriverTripsResponse> trips) {
                        DrivertripList= (ArrayList <GetdriverTripsResponse>) trips;
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
        toast=Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
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
    /*class TripRequest extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(ConfirmedTrips.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setTitle("Getting Details");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                TripDetailsProcess();
                return new String[]{"Success"};
            } catch (Exception e) {
                return new String[]{"error"};
            }
        }

        @Override
        protected void onPostExecute(String... result) {
            try {
                if (response.matches("")) {
                    Toast.makeText(getApplicationContext(), "An error has occurred ", Toast.LENGTH_LONG).show();
                } else {
                    JSONArray jsonObj = new JSONArray(response);
                    JSONObject c = jsonObj.getJSONObject(0);
                    //	editor.putString(VEHICLEID, c.getString("VehicleId"));
                    Intent intent = new Intent(ConfirmedTrips.this, ConfirmedTripsDetails.class);
                    intent.putExtra("sourcelat", c.getString("SrcLatitude"));
                    intent.putExtra("sourcelong", c.getString("SrcLongitude"));
                    intent.putExtra("destlat", c.getString("DestLatitude"));
                    intent.putExtra("destlong", c.getString("DestLongitude"));
                    ApplicationConstants.driverimage = c.getString("DPhoto");
                    intent.putExtra("ddetails", c.getString("driver"));
                    intent.putExtra("comments", c.getString("Comments"));
                    ApplicationConstants.vehicleimage = c.getString("VPhoto");
                    intent.putExtra("vdetails", c.getString("RegistrationNo"));
                    intent.putExtra("starttime", c.getString("BookedDate"));
                    intent.putExtra("endtime", c.getString("DepartureTime"));
                    intent.putExtra("src", c.getString("Src"));
                    intent.putExtra("dest", c.getString("Dest"));
                    intent.putExtra("amount", c.getString("Amount"));
                    intent.putExtra("paymenttypeid", c.getString("PaymentModeId"));
                    intent.putExtra("gatewattransid", c.getString("GatewayTransId"));
                    startActivity(intent);

					*//*Log.i("TripDetails",response);
					ActivityManager activityManager = (ActivityManager) ConfirmedTrips.this.getSystemService(ACTIVITY_SERVICE);
					ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
					activityManager.getMemoryInfo(memoryInfo);
					Log.i("TripDetails",memoryInfo.totalMem+" "+memoryInfo.availMem);
					System.runFinalization();
					Runtime.getRuntime().gc();
					System.gc();
					Log.i("TripDetails","Heap Cleared");
					activityManager = (ActivityManager) ConfirmedTrips.this.getSystemService(ACTIVITY_SERVICE);
					memoryInfo = new ActivityManager.MemoryInfo();
					activityManager.getMemoryInfo(memoryInfo);
					Log.i("TripDetails",memoryInfo.totalMem+" "+memoryInfo.availMem);*//*
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void TripDetailsProcess() {
        BufferedReader reader = null;
        response = "";
        try {
            JSONObject object = new JSONObject();
            object.put("BNo", bookingno);
            //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
            // Defined URL  where to send data
            URL url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_get_trip_details));
            Log.d("Login url", url.toString());
            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Host", "android.schoolportal.gr");
            conn.connect();
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(object.toString());
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }
            response = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // response = response + e.getMessage();
            //e.printStackTrace();
        } catch (IOException e) {
            //  response = response + e.getMessage();
        } catch (JSONException e) {
            //  e.printStackTrace();
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

    }*/

}