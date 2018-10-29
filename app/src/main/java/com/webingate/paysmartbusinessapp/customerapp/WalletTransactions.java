package com.webingate.paysmartbusinessapp.customerapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.webingate.paysmartbusinessapp.R;
public class WalletTransactions extends Fragment {
    MyCustomAdapter dataAdapter = null;
    private static Button deleteAll;
    private static final String ARG_SECTION_NUMBER = "section_number";

    @BindView(R.id.checkBox1)
    CheckBox checkBox1;
    @BindView(R.id.code)
    TextView view;
    @BindView(R.id.row1)
    TableRow row1;
    Unbinder unbinder;

    Toast toast;
    ProgressDialog dialog ;
    private String response;


    public static WalletTransactions newInstance(int SectionNumber) {
        WalletTransactions home = new WalletTransactions();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, SectionNumber);
        home.setArguments(args);
        return home;
    }

    @SuppressLint("NewApi")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetTransactions getTransactions = new GetTransactions();
        getTransactions.execute();
    }

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wallet_transactions, container, false);

        unbinder = ButterKnife.bind(this, v);
        /*dialog =  new ProgressDialog.Builder(getActivity())
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();*/
        return v;
    }

    @SuppressWarnings("unchecked")
    private void displayListView() {


        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(getActivity(), R.layout.transactions_custom_layout, ApplicationConstants.walletTransactions);
        ListView listView = (ListView) view.findViewById(R.id.listView1);
        // Assign adapter to ListView
        dataAdapter.notifyDataSetChanged();
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MyCustomAdapter extends ArrayAdapter<TransactionModel> {

        private ArrayList<TransactionModel> logsselected;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<TransactionModel> logsSelected) {
            super(context, textViewResourceId, logsSelected);
            this.logsselected = new ArrayList<TransactionModel>();
            this.logsselected.addAll(logsSelected);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.transactions_custom_layout, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        TransactionModel log = new TransactionModel();
                        log = (TransactionModel) cb.getTag();
                        //   Toast.makeText(getApplicationContext(),"Clicked on Checkbox: " + cb.getText() +" is " + cb.isChecked(),
                        //   Toast.LENGTH_LONG).show();
                        log.setSelected(cb.isChecked());
                    }
                });
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

            TransactionModel logs = logsselected.get(position);
            // holder.code.setText(" (" +  logs.getCode() + ")");
            holder.name.setText("Transaction Id " + logs.getTransactionId());
            holder.code.setText("Date & Time " + logs.getDate() + " & " + logs.getTime() + "\nTransactionType " + logs.getTransactionType() + "\nAmount" + logs.getAmount());
            holder.name.setChecked(logs.isSelected());
            holder.name.setTag(logs);
            return convertView;

        }

    }

   /* public void GetWalletTransDetails( String mobileNo){

        StartDialogue();
        com.webingate.paysmartbusinessapp.businessapp.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .GetWalletTransDetails(mobileNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetWalletTransDetailsResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                        StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<GetWalletTransDetailsResponse> responselist) {


                    }
                });
    }
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(getContext(),text,Toast.LENGTH_SHORT);
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

    }*/
    class GetTransactions extends AsyncTask<String, Void, String[]> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Please Wait...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                TransactionsRequest();
                return new String[]{"Success"};
            } catch (Exception e) {
                return new String[]{"error"};
            }
        }

        @Override
        protected void onPostExecute(String... result) {
            try {
                if (response.matches("")) {
                    Toast.makeText(getActivity(), "An error has occurred ", Toast.LENGTH_LONG).show();
                } else {
                    // Toast.makeText(getActivity(), response+"response", Toast.LENGTH_LONG).show();
                    JSONArray jsonObj = new JSONArray(response);

                    ApplicationConstants.walletTransactions = new ArrayList();

                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject c = jsonObj.getJSONObject(i);
                        TransactionModel transactionModel = new TransactionModel();
                        transactionModel.setDate(c.getString("Date"));
                        transactionModel.setTime(c.getString("Time"));
                        transactionModel.setTransactionType(c.getString("TransactionType"));
                        transactionModel.setAmount(c.getString("Amount"));
                        transactionModel.setTransactionId(c.getString("TransactionId"));
                        ApplicationConstants.walletTransactions.add(transactionModel);
                        displayListView();
                    }
                }

                if (dialog.isShowing())
                    dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void TransactionsRequest() {
        BufferedReader reader = null;
        response = "";
        URL url = null;
        JSONObject object = null;
        try {
            url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_getwallet_transdetails) + ApplicationConstants.mobileNo);
            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Host", "android.schoolportal.gr");
            conn.connect();


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
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

    }
}
