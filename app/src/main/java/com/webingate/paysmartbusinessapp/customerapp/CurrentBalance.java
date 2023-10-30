package com.webingate.paysmartbusinessapp.customerapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.businessapp.Deo.GetCurrentBalanceResponse;
import com.webingate.paysmartbusinessapp.businessapp.Deo.WalletBalanceResponse;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

@SuppressLint("NewApi")
public class CurrentBalance extends Fragment {
    Button bookTicket, myTickets, eWallet;
    private static final String ARG_SECTION_NUMBER = "section_number";
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.btn_recharge)
    AppCompatButton recharge;
    @BindView(R.id.btn_transfer)
    AppCompatButton transfer;
    Unbinder unbinder;
    private String response;
    private String amount;
    private int flag;

    Toast toast;
    ProgressDialog dialog ;
    public static CurrentBalance newInstance(int SectionNumber) {
        CurrentBalance home = new CurrentBalance();
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
        View v = inflater.inflate(R.layout.balance, container, false);
        unbinder = ButterKnife.bind(this, v);
        dialog =  new ProgressDialog.Builder(getActivity())
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        if (ApplicationConstants.walletBalance.matches("")) {
            flag = 1;
            Getcurrentbalance(ApplicationConstants.mobileNo);

            /*GetBalance getBalance = new GetBalance();
            getBalance.execute();*/

        } else {
            balance.setText(ApplicationConstants.walletBalance + " $");
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JsonObject object = new JsonObject();
                String text;
                if (v == recharge) {
                    flag = 2;
                    object.addProperty("flag", "A");
                    object.addProperty("Mobilenumber", ApplicationConstants.mobileNo);
                    object.addProperty("Amount", amount + "");
                    object.addProperty("Status", "1");
                    text = "Recharge";
                } else {
                    flag = 3;
                    object.addProperty("flag", "T");
                    object.addProperty("Mobilenumber", ApplicationConstants.mobileNo);
                    object.addProperty("Amount", amount + "");
                    object.addProperty("Status", "1");
                    text = "Transfer";
                }
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity(), R.style.Dialog_Theme);
                alertDialog.setTitle(text);
                alertDialog.setMessage("Please Enter " + text + " Amount");
                final EditText input = new EditText(getActivity());
               /* LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);*/
                input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(4);
                input.setFilters(FilterArray);
                alertDialog.setView(input);
                alertDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (input.getText().toString().matches("")) {
                                    Toast.makeText(getActivity(), "Please Enter amount", Toast.LENGTH_SHORT).show();

                                } else {
                                    amount = input.getText().toString();
                                    WalletBalance(object);

                                   /* GetBalance getBalance = new GetBalance();
                                    getBalance.execute();*/

                                }
                            }
                        });


                alertDialog.show();
            }
        };
        recharge.setOnClickListener(onClickListener);
        transfer.setOnClickListener(onClickListener);

        return v;
    }

    private void goPage(int page) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (page) {
            case ApplicationConstants.HOME:
                fragmentClass = EWallet.class;
                break;
            case ApplicationConstants.TICKET_SOURCE_DESTINATION:
                fragmentClass = Ticket_Source_Destination_Date.class;
                break;
            case ApplicationConstants.TICKETS:
                fragmentClass = Mytickets.class;
                break;
            case ApplicationConstants.STOPS:
                fragmentClass = Stops.class;
                break;
            case ApplicationConstants.TRAVELS:
                fragmentClass = Travels.class;
                break;
            case ApplicationConstants.BUSLAYOUT:
                fragmentClass = BusLayout.class;
                break;
            case ApplicationConstants.EWALLET:
                fragmentClass = BusLayout.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       /* fragmentTransaction.setCustomAnimations(
                R.anim.rotate_forward,
                R.anim.rotate_backward);*/
        fragmentTransaction.replace(R.id.flContent, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void Getcurrentbalance(String mobileNo){

        StartDialogue();
        com.webingate.paysmartbusinessapp.customerapp.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .Getcurrentbalance(mobileNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetCurrentBalanceResponse>>() {
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
                    public void onNext(List<GetCurrentBalanceResponse> responselist) {
                        GetCurrentBalanceResponse response=responselist.get(0);
                        ApplicationConstants.walletBalance = response.getAmount();
                        balance.setText(response.getAmount() + " $");

                    }
                });
    }
    public void WalletBalance(JsonObject object){

        StartDialogue();
        com.webingate.paysmartbusinessapp.customerapp.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .WalletBalance(object)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<WalletBalanceResponse>>() {
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
                    public void onNext(List<WalletBalanceResponse> responselist) {
                        WalletBalanceResponse response=responselist.get(0);
                            ApplicationConstants.walletBalance = response.getAmount();
                            balance.setText(response.getAmount() + " $");

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

    }

   /* class GetBalance extends AsyncTask<String, Void, String[]> {

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
                BalanceRequest();
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
                    switch (flag) {
                        case 1:
                            // Toast.makeText(getApplicationContext(), response.substring(0,10), Toast.LENGTH_LONG).show();
                            JSONArray jsonObj = new JSONArray(response);
                            JSONObject c = jsonObj.getJSONObject(0);
                            ApplicationConstants.walletBalance = c.getString("Amount");
                            balance.setText(ApplicationConstants.walletBalance + " $");
                            break;
                        case 2:
                            // Toast.makeText(getApplicationContext(), response.substring(0,10), Toast.LENGTH_LONG).show();
                            jsonObj = new JSONArray(response);
                            c = jsonObj.getJSONObject(0);
                            ApplicationConstants.walletBalance = c.getString("Amount");
                            balance.setText(ApplicationConstants.walletBalance + " $");
                            break;
                        case 3:
                            // Toast.makeText(getApplicationContext(), response.substring(0,10), Toast.LENGTH_LONG).show();
                            jsonObj = new JSONArray(response);
                            c = jsonObj.getJSONObject(0);
                            ApplicationConstants.walletBalance = c.getString("Amount");
                            balance.setText(ApplicationConstants.walletBalance + " $");
                            break;
                    }
                }

                if (dialog.isShowing())
                    dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void BalanceRequest() {
        BufferedReader reader = null;
        response = "";
        response = "";
        URL url = null;
        JSONObject object = null;
        try {

            switch (flag) {
                case 1:
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_get_currentbalance) + ApplicationConstants.mobileNo);
                    // Send POST data request
                    break;

                case 2:
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_wallet_balance));
                    object = new JSONObject();
                    object.put("flag", "A");
                    object.put("Mobilenumber", ApplicationConstants.mobileNo);
                    object.put("Amount", amount + "");
                    object.put("Status", "1");
                    break;
                case 3:
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_wallet_balance));
                    object = new JSONObject();
                    object.put("flag", "T");
                    object.put("Mobilenumber", ApplicationConstants.mobileNo);
                    object.put("Amount", amount + "");
                    object.put("Status", "1");
                    break;
                // Send POST data request
            }
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Host", "android.schoolportal.gr");
            if (flag == 1) {
                conn.connect();
            } else {
                conn.setDoOutput(true);
                conn.connect();
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(object.toString());
                wr.flush();
            }


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
            e.printStackTrace();
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

    }*/
}
