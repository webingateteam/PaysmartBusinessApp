package com.webingate.paysmartbusinessapp.driverapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetCustomerAccountResponce;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.webingate.paysmartbusinessapp.R;
/**
 * Created by Seshu on 10/18/2017.
 */

public class Activity_Payments extends AppCompatActivity {
    TextView addpaymentMethod;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.add_payment_method)
    TextView addPaymentMethod;
    @BindView(R.id.listView1)
    ListView listView;

    MyCustomAdapter dataAdapter = null;
    private String response;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_payments);
        ButterKnife.bind(this);
        // adding toolbar
        setSupportActionBar(toolbar);
        // you can title and subtitle dynamically
        //getSupportActionBar().setTitle("Payments");
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        GetCustomerAccountResponce payment_method_model = new GetCustomerAccountResponce();
        payment_method_model.setAccountNumber("cash");
        payment_method_model.setIsPrimary("null");
        ApplicationConstants.paymentMethods.add(0, payment_method_model);
        dataAdapter = new MyCustomAdapter(this, R.layout.layout_payment_method, ApplicationConstants.paymentMethods);
        // Assign adapter to ListView
        dataAdapter.notifyDataSetChanged();
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                for (int i = 0; i < ApplicationConstants.paymentMethods.size(); i++) {
                    if (i == position) {
                        ((GetCustomerAccountResponce) ApplicationConstants.paymentMethods.get(i)).setIsPrimary("1");
                    } else {
                        ((GetCustomerAccountResponce) ApplicationConstants.paymentMethods.get(i)).setIsPrimary("null");
                    }
                }
                dataAdapter.notifyDataSetChanged();
            }
        });


    }

    @OnClick(R.id.add_payment_method)
    void AddPaymentClick(View v){
        Dialogue_Payment dialogue_payment = new Dialogue_Payment(Activity_Payments.this);
        dialogue_payment.setCanceledOnTouchOutside(false);
        dialogue_payment.show();
    }

    //toolbar button click
    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        return true;
    }

    private class MyCustomAdapter extends ArrayAdapter<GetCustomerAccountResponce> {

        private List<GetCustomerAccountResponce> logsselected;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               List<GetCustomerAccountResponce> logsSelected) {
            super(context, textViewResourceId, logsSelected);
            this.logsselected = new ArrayList<GetCustomerAccountResponce>();
            this.logsselected.addAll(logsSelected);
        }

        private class ViewHolder {
            TextView paymentMethod, issected;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.layout_payment_method, null);

                holder = new ViewHolder();
                holder.paymentMethod = (TextView) convertView.findViewById(R.id.payment_method);
                holder.issected = (TextView) convertView.findViewById(R.id.selected);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            GetCustomerAccountResponce payment_method_model = logsselected.get(position);
            holder.paymentMethod.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.visa_card, 0, 0, 0);
            holder.paymentMethod.setText(payment_method_model.getAccountNumber());
            if (!payment_method_model.getIsPrimary().matches("null")) {
                holder.issected.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_mark, 0, 0, 0);
            } else {
                holder.issected.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            return convertView;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 42) {
            if (resultCode == Activity.RESULT_OK) {
                dataAdapter.clear();
                dataAdapter = new MyCustomAdapter(this, R.layout.layout_payment_method, ApplicationConstants.paymentMethods);
                dataAdapter.notifyDataSetChanged();
                listView.setAdapter(dataAdapter);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Result Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }//onActivityResult


}
