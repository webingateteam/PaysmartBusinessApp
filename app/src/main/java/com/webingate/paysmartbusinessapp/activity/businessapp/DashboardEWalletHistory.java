package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.WalletBalanceResponse;
import com.webingate.paysmartbusinessapp.adapter.businessapp_transactionsAdapter;
import com.webingate.paysmartbusinessapp.customerapp.ApplicationConstants;
import com.webingate.paysmartbusinessapp.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class DashboardEWalletHistory extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Phone = "phoneKey";
    //private TabLayout tab_layout;
    // private NestedScrollView nested_scroll_view;

    businessapp_transactionsAdapter adapter;
    // RecyclerView
    RecyclerView recyclerView;

    AppCompatButton transfer;
    @BindView(R.id.balance)
    TextView balance;

    Unbinder unbinder;
    private String response;
    private String amount,text1,mno;
    private int flag;
    Toast toast;
    String mibleno;
    ArrayList<WalletBalanceResponse> traslist,traslist1;
    //ArrayList<GetCurrentBalanceResponse> traslist1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_history);
//        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        mno=prefs.getString(Phone, null);
        balance=(TextView) findViewById(R.id.balance);
        mibleno= com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants.mobileNo;

        initToolbar();
        initActions();
        initData();
        initUI();
        initComponent();


    }
    private void initUI(){
        adapter = new businessapp_transactionsAdapter(null);
        // get recycler view
        recyclerView =(RecyclerView) findViewById(R.id.placeList1RecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    private void initData(){

       Getcurrentbalance(mibleno);
    }
    private void initToolbar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_menu);
//        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.light_blue_500), PorterDuff.Mode.SRC_ATOP);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(null);
//        Tools.setSystemBarColor(this, R.color.grey_5);
//        Tools.setSystemBarLight(this);


            Toolbar toolbar = findViewById(R.id.toolbar);

            toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);

            if(toolbar.getNavigationIcon() != null) {
                toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
            }

            toolbar.setTitle("Ewallet Transactions History");

            try {
                toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
            }catch (Exception e){
                Log.e("TEAMPS","Can't set color.");
            }

            try {
                setSupportActionBar(toolbar);
            }catch (Exception e) {
                Log.e("TEAMPS","Error in set support action bar.");
            }

            try {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }catch (Exception e) {
                Log.e("TEAMPS","Error in set display home as up enabled.");
            }


    }

    private void initComponent() {
//        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
//        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
//        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_equalizer), 0);
//        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_credit_card), 1);
//        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_pie_chart_outline), 2);
//        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_person), 3);
//
//        // set icon color pre-selected
//        tab_layout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.light_blue_100), PorterDuff.Mode.SRC_IN);
//        tab_layout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);
//        tab_layout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);
//        tab_layout.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);
//
//        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                tab.getIcon().setColorFilter(getResources().getColor(R.color.light_blue_100), PorterDuff.Mode.SRC_IN);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                tab.getIcon().setColorFilter(getResources().getColor(R.color.light_blue_700), PorterDuff.Mode.SRC_IN);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
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


    }
    public void Getcurrentbalance(String mobileNo){

       // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
                .GetEwalletHistory(mobileNo)
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
//                        if(response.getAmount()!=null){
//                        balance.setText(response.getAmount() + " $");
//                        }
//                        else
//                        {
//                            balance.setText("0.00 $");
//                        }
                        traslist1= (ArrayList<WalletBalanceResponse>) responselist;
                        adapter = new businessapp_transactionsAdapter(traslist1);
                        recyclerView.setAdapter(adapter);

                    }
                });
    }
//    public void WalletBalance(JsonObject object){
//
//        //StartDialogue();
//        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(this).getrestadapter()
//                .WalletBalance(object)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<WalletBalanceResponse>>() {
//                    @Override
//                    public void onCompleted() {
//                        //  DisplayToast("Successfully Registered");
//                       // StopDialogue();
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        try {
//                            Log.d("OnError ", e.getMessage());
//                            DisplayToast("Error");
//                            //StopDialogue();
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onNext(List<WalletBalanceResponse> responselist) {
//                        WalletBalanceResponse response=responselist.get(0);
//                        traslist= (ArrayList<WalletBalanceResponse>) responselist;
//                        adapter = new businessapp_transactionsAdapter(traslist);
//                        recyclerView.setAdapter(adapter);
//
//                        ApplicationConstants.walletBalance = response.getAmount();
//                        balance.setText(response.getAmount() + " $");
//
//                    }
//                });
//    }
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();

    }

}

