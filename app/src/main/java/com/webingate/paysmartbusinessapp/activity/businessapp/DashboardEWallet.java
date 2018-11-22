package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.WalletBalanceResponse;
import com.webingate.paysmartbusinessapp.adapter.businessapp_transactionsAdapter;
import com.webingate.paysmartbusinessapp.customerapp.ApplicationConstants;
import com.webingate.paysmartbusinessapp.utils.Tools;
import com.webingate.paysmartbusinessapp.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class DashboardEWallet extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Phone = "phoneKey";
    private TabLayout tab_layout;
    private NestedScrollView nested_scroll_view;
    @BindView(R.id.send)
    LinearLayout send;
    @BindView(R.id.recieve)
    LinearLayout recieve;
    @BindView(R.id.balance)
    TextView balance;
    businessapp_transactionsAdapter adapter;
    // RecyclerView
    RecyclerView recyclerView;


    AppCompatButton transfer;
    Unbinder unbinder;
    private String response;
    private String amount,text1,mno;
    private int flag;
    Toast toast;
    ArrayList<WalletBalanceResponse> traslist,traslist1;
    //ArrayList<GetCurrentBalanceResponse> traslist1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_wallet1);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mno=prefs.getString(Phone, null);
        send=findViewById(R.id.send);
        recieve=findViewById(R.id.recieve);
        balance=findViewById(R.id.balance);


        initToolbar();
        initActions();
        initData();
        initUI();
        initComponent();

    }
    private void initUI(){
        adapter = new businessapp_transactionsAdapter(null);
        // get recycler view
        recyclerView = findViewById(R.id.placeList1RecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    private void initData(){

        Getcurrentbalance(mno);
    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.light_blue_500), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {
        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_equalizer), 0);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_credit_card), 1);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_pie_chart_outline), 2);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_person), 3);

        // set icon color pre-selected
        tab_layout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.light_blue_100), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.light_blue_100), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.light_blue_500));
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void initActions()
    {
        final JsonObject object = new JsonObject();

        send.setOnClickListener(view -> {
            //startActivity( new Intent(this, EWallet.class));
            object.addProperty("flag", "T");
            object.addProperty("Mobilenumber", mno);

            object.addProperty("Status", "1");
            text1 = "Transfer";
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.Dialog_Theme);
            alertDialog.setTitle(text1);
            alertDialog.setMessage("Please Enter " + text1 + " Amount");
            final EditText input = new EditText(this);
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
                                Toast.makeText(DashboardEWallet.this, "Please Enter amount", Toast.LENGTH_SHORT).show();

                            } else {
                                amount = input.getText().toString();
                                object.addProperty("Amount", amount + "");
//                                Intent intent=new Intent(DashboardEWallet.this,customerappEwalletSendTransactionslistActivity.class);
//                                startActivity(intent);
                               WalletBalance(object);

                                   /* GetBalance getBalance = new GetBalance();
                                    getBalance.execute();*/

                            }
                        }
                    });
            alertDialog.show();
        });
        recieve.setOnClickListener(view -> {
            //startActivity( new Intent(this, EWallet.class));
            object.addProperty("flag", "A");
            object.addProperty("Mobilenumber", mno);

            object.addProperty("Status", "1");
            text1 = "Recieve";
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.Dialog_Theme);
            alertDialog.setTitle(text1);
            alertDialog.setMessage("Please Enter " + text1 + " Amount");
            final EditText input = new EditText(this);
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
                                Toast.makeText(DashboardEWallet.this, "Please Enter amount", Toast.LENGTH_SHORT).show();

                            } else {
                                amount = input.getText().toString();
                                object.addProperty("Amount", amount + "");
//                                Intent intent=new Intent(DashboardEWallet.this,customerappEwalletSendTransactionslistActivity.class);
//                                startActivity(intent);
                                WalletBalance(object);

                                   /* GetBalance getBalance = new GetBalance();
                                    getBalance.execute();*/

                            }
                        }
                    });
            alertDialog.show();
        });
    }
    public void Getcurrentbalance(String mobileNo){

       // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .Getcurrentbalance1(mobileNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<WalletBalanceResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<WalletBalanceResponse> responselist) {
                        WalletBalanceResponse response=responselist.get(0);
                        ApplicationConstants.walletBalance = response.getAmount();
                        balance.setText(response.getAmount() + " $");
                        traslist1= (ArrayList<WalletBalanceResponse>) responselist;
                        adapter = new businessapp_transactionsAdapter(traslist1);
                        recyclerView.setAdapter(adapter);

                    }
                });
    }
    public void WalletBalance(JsonObject object){

        //StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .WalletBalance(object)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<WalletBalanceResponse>>() {
                    @Override
                    public void onCompleted() {
                        //  DisplayToast("Successfully Registered");
                       // StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<WalletBalanceResponse> responselist) {
                        WalletBalanceResponse response=responselist.get(0);
                        traslist= (ArrayList<WalletBalanceResponse>) responselist;
                        adapter = new businessapp_transactionsAdapter(traslist);
                        recyclerView.setAdapter(adapter);

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
        toast=Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();

    }

}

