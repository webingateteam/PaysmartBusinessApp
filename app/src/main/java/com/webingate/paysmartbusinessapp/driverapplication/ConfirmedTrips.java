package com.webingate.paysmartbusinessapp.driverapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RideDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
public class ConfirmedTrips extends AppCompatActivity {
    MyCustomAdapter dataAdapter = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listView1)
    ListView listView1;

    private int bookingno;
    private String response;

    Toast toast;
    ProgressDialog dialog ;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApplicationConstants.tripflag = 3;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmed_trips);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(ConfirmedTrips.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.grey_3)
                .build();
        // adding toolbar
        setSupportActionBar(toolbar);
        // you can title and subtitle dynamically
        //getSupportActionBar().setTitle("Payments");
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Generate list View from ArrayList
        displayListView();
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
        dataAdapter = new MyCustomAdapter(this,
                R.layout.layout_tripscustom, ApplicationConstants.confirmedTrips);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        dataAdapter.notifyDataSetChanged();
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                GetdriverTripsResponse tripModel = (GetdriverTripsResponse) ApplicationConstants.confirmedTrips.get(position);
                bookingno = tripModel.getId();
                JsonObject object = new JsonObject();
                object.addProperty("BNo", bookingno);
                TripRequest(object);
             //   TripRequest tripRequest = new TripRequest();
             //   tripRequest.execute();
            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<GetdriverTripsResponse> {

        private ArrayList<GetdriverTripsResponse> logsselected;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               List<GetdriverTripsResponse> logsSelected) {
            super(context, textViewResourceId, logsSelected);
            this.logsselected = new ArrayList<GetdriverTripsResponse>();
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
    public void TripRequest(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(ConfirmedTrips.this).getrestadapter()
                .RideDetails(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RideDetailsResponse>>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Unable to Register");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<RideDetailsResponse> responseList) {
                        RideDetailsResponse response=responseList.get(0);
                        //	editor.putString(VEHICLEID, c.getString("VehicleId"));
                        Intent intent = new Intent(ConfirmedTrips.this, ConfirmedTripsDetails.class);
                        intent.putExtra("sourcelat", response.getSrcLatitude());
                        intent.putExtra("sourcelong", response.getSrcLongitude());
                        intent.putExtra("destlat", response.getDestLatitude());
                        intent.putExtra("destlong", response.getDestLongitude());
                        ApplicationConstants.driverpic = response.getDPhoto();
                        intent.putExtra("ddetails", response.getDriver());
                        intent.putExtra("comments", response.getComments());
                        ApplicationConstants.vehiclepic = response.getVPhoto();
                        intent.putExtra("vdetails", response.getRegistrationNo());
                        intent.putExtra("starttime", response.getBookedDate());
                        intent.putExtra("endtime", response.getDepartureTime());
                        intent.putExtra("src", response.getSrc());
                        intent.putExtra("dest", response.getDest());
                        intent.putExtra("amount", response.getAmount());
                        intent.putExtra("paymenttypeid", response.getPaymentModeId());
                        intent.putExtra("gatewattransid", response.getGatewayTransId());
                        startActivity(intent);


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


   /* class TripRequest extends AsyncTask<String, Void, String[]> {
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
                    Log.d("Confirmed Trips",response);
                    JSONArray jsonObj = new JSONArray(response);
                    // looping through All Contacts
                    JSONObject c = jsonObj.getJSONObject(0);
                    //	editor.putString(VEHICLEID, c.getString("VehicleId"));
                    Intent intent = new Intent(ConfirmedTrips.this, ConfirmedTripsDetails.class);
                    intent.putExtra("sourcelat", c.getString("SrcLatitude"));
                    intent.putExtra("sourcelong", c.getString("SrcLongitude"));
                    intent.putExtra("destlat", c.getString("DestLatitude"));
                    intent.putExtra("destlong", c.getString("DestLongitude"));
                    ApplicationConstants.driverpic = c.getString("DPhoto");
                    intent.putExtra("ddetails", c.getString("driver"));
                    intent.putExtra("comments", c.getString("Comments"));
                    ApplicationConstants.vehiclepic = c.getString("VPhoto");
                    intent.putExtra("vdetails", c.getString("RegistrationNo"));
                    intent.putExtra("starttime", c.getString("BookedDate"));
                    intent.putExtra("endtime", c.getString("DepartureTime"));
                    intent.putExtra("src", c.getString("Src"));
                    intent.putExtra("dest", c.getString("Dest"));
                    intent.putExtra("amount", c.getString("Amount"));
                    intent.putExtra("paymenttypeid", c.getString("PaymentModeId"));
                    intent.putExtra("gatewattransid", c.getString("GatewayTransId"));
                    startActivity(intent);
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
            URL url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_get_trip_details));
            JSONObject object = new JSONObject();
            object.put("BNo", bookingno);
            //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
            // Defined URL  where to send data
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

            // Read Server DriverDetailsResponse
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