package com.webingate.paysmartbusinessapp.driverapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;
public class OngoingTrips extends AppCompatActivity {
    MyCustomAdapter dataAdapter = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listView1)
    ListView listView1;


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
        dataAdapter = new MyCustomAdapter(this, R.layout.layout_tripscustom, ApplicationConstants.confirmedTrips);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        dataAdapter.notifyDataSetChanged();
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

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
            TextView bookingId, source, destination;
            Button accept, reject;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.activity_listview, null);

                holder = new ViewHolder();
                holder.bookingId = (TextView) convertView.findViewById(R.id.bookNo);
                holder.source = (TextView) convertView.findViewById(R.id.textview1);
                holder.destination = (TextView) convertView.findViewById(R.id.textview2);
                holder.accept = (Button) convertView.findViewById(R.id.accept);
                holder.reject = (Button) convertView.findViewById(R.id.reject);
                convertView.setTag(holder);
                holder.accept.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Accept",
                                Toast.LENGTH_LONG).show();
                    }
                });

                holder.reject.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Reject",
                                Toast.LENGTH_LONG).show();
                    }
                });

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            GetdriverTripsResponse logs = logsselected.get(position);
            // holder.code.setText(" (" +  logs.getCode() + ")");
            holder.bookingId.setText("Booking Id " + logs.getId());
            holder.source.setText(logs.getSrc());
            //holder.name.setChecked(logs.isSelected());
            holder.destination.setText(logs.getDest());
            return convertView;

        }

    }


}