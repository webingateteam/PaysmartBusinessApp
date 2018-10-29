package com.webingate.paysmartbusinessapp.customerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.businessapp.Deo.CustomerGetstopsResponse;
import com.webingate.paysmartbusinessapp.businessapp.Deo.GetBookingHistoryResponse;
import com.webingate.paysmartbusinessapp.businessapp.Deo.GetCustomerAccountResponce;
import com.webingate.paysmartbusinessapp.businessapp.Dialog.ProgressDialog;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
public class HomeActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final int HOME = 0;
    private final int TICKET_SOURCE_DESTINATION = 1;
    private final int CITY_SELECTION = 2;
    private final int TRAVELS_SELECTION = 3;
    @BindView(R.id.home_toolbar)
    Toolbar toolbar;
    @BindView(R.id.flContent)
    FrameLayout flContent;
    @BindView(R.id.homesmall)
    AppCompatButton home_small;
    @BindView(R.id.logout)
    AppCompatButton logout;
    @BindView(R.id.sos)
    AppCompatButton sos;
    @BindView(R.id.accountsmall)
    AppCompatButton account_small;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    ActionBarDrawerToggle toggle;
    private TextView profileName;
    private ImageView profilepic;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Phone = "phoneKey";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordkey";
    public static final String Dateofbirth = "dateofbirth";
    public static final String Profilepic = "profilepic";
    private int serverrequestFlag;
    private String response;

    Toast toast;
    ProgressDialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(HomeActivity1.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ApplicationConstants.username = prefs.getString(Name, null);
        ApplicationConstants.mobileNo = prefs.getString(Phone, null);
        ApplicationConstants.email = prefs.getString(Email, null);
        ApplicationConstants.dateofbirth = prefs.getString(Dateofbirth, null);
        ApplicationConstants.profilepic = prefs.getString(Profilepic, null);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null)
            goPage(HOME);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //  drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        profileName = (TextView) header.findViewById(R.id.profile_name);
        profilepic = (ImageView) header.findViewById(R.id.profilepic);
        if (ApplicationConstants.profilepic != null && !ApplicationConstants.profilepic.matches("")) {
            profilepic.setImageURI(Uri.parse(ApplicationConstants.profilepic));
        }
        profileName.setText(ApplicationConstants.username + "\t" + ApplicationConstants.mobileNo);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.homesmall:
                        goPage(ApplicationConstants.HOME);
                        break;
                    case R.id.logout:
                        /*ApplicationConstants.marker = R.mipmap.marker_taxi;
                        ApplicationConstants.source="";
                        ApplicationConstants.destination="";
                        serverrequestFlag = 3;
                        ServerTask  serverTask= new ServerTask();
                        serverTask.execute();*/
                        SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();
                        editor.putString(Phone, null);
                        editor.putString(Name, null);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "You Are successfully LoggedOut", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity1.this, WelcomeActivity.class));
                        finish();
                        break;
                    case R.id.sos:
                        startActivity(new Intent(HomeActivity1.this, SosContacts.class));
                        break;
                    case R.id.accountsmall:
                        drawer.openDrawer(navigationView);
                        // goPage(ApplicationConstants.A);
                        break;

                }
            }
        };
        home_small.setOnClickListener(onClickListener);
        logout.setOnClickListener(onClickListener);
        sos.setOnClickListener(onClickListener);
        account_small.setOnClickListener(onClickListener);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (ApplicationConstants.FRAGMENT == 0) {
            finish();
        } else if (ApplicationConstants.FRAGMENT == ApplicationConstants.STOPS) {
            ApplicationConstants.FRAGMENT = ApplicationConstants.TICKET_SOURCE_DESTINATION;
            goPage(ApplicationConstants.FRAGMENT);
        } else {
            // Toast.makeText(getApplicationContext(),ApplicationConstants.FRAGMENT-1+"",Toast.LENGTH_SHORT).show();
            ApplicationConstants.FRAGMENT = 0;
            goPage(ApplicationConstants.HOME);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    private void goPage(int page) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (page) {
            case ApplicationConstants.HOME:
                fragmentClass = Home.class;
                break;
            case ApplicationConstants.TICKET_SOURCE_DESTINATION:
                fragmentClass = Ticket_Source_Destination_Date.class;
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
                fragmentClass = EWallet.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       /* fragmentTransaction.setCustomAnimations(
                R.anim.rotate_forward,
                R.anim.rotate_backward);*/
        fragmentTransaction.replace(R.id.flContent, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.edit_profile:
                startActivity(new Intent(HomeActivity1.this, AccountProfile.class));
                finish();
                break;

            case R.id.reset_password:
                startActivity(new Intent(HomeActivity1.this, ResetPassword.class));
                break;
            case R.id.MyTrips:
                serverrequestFlag = 1;
                GetBookingHistory(ApplicationConstants.mobileNo);

               /* ServerTask serverTask = new ServerTask();
                serverTask.execute();*/

                break;
            case R.id.menu_payments:
                serverrequestFlag = 2;
                GetCustomerAccount(ApplicationConstants.mobileNo);
               /* serverTask = new ServerTask();
                serverTask.execute();*/
                break;

        }
        drawer.closeDrawer(navigationView);
        return true;
    }


    public void GetBookingHistory(String mobileNO ){
        StartDialogue();
        com.webingate.paysmartbusinessapp.businessapp.Utils.DataPrepare.get(HomeActivity1.this).getrestadapter()
                .GetBookingHistory(mobileNO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetBookingHistoryResponse>>() {
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
                    public void onNext(List<GetBookingHistoryResponse> responselist) {
                        Intent intent=new Intent(HomeActivity1.this, ConfirmedTrips.class);
                        intent.putExtra("details", (Serializable) responselist);
                        startActivity(intent);
                    }
                });
    }

    public void GetCustomerAccount(String userId ){
        StartDialogue();
        com.webingate.paysmartbusinessapp.businessapp.Utils.DataPrepare.get(HomeActivity1.this).getrestadapter()
                .GetCustomerAccount(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetCustomerAccountResponce>>() {
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
                    public void onNext(List<GetCustomerAccountResponce> responselist) {
                        Intent intent=new Intent(HomeActivity1.this, Activity_Payments.class);
                        intent.putExtra("details", (Serializable) responselist);
                        startActivity(intent);
                    }
                });
    }

    public void TaxiStops( ){
        StartDialogue();
        com.webingate.paysmartbusinessapp.businessapp.Utils.DataPrepare.get(HomeActivity1.this).getrestadapter()
                .TaxiStops()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CustomerGetstopsResponse>>() {
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
                    public void onNext(List<CustomerGetstopsResponse> responselist) {
                        Intent intent=new Intent(HomeActivity1.this, MeteredTaxi.class);
                        intent.putExtra("details", (Serializable) responselist);
                        startActivity(intent);
                    }
                });
    }
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast=Toast.makeText(HomeActivity1.this,text,Toast.LENGTH_SHORT);
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


   /* class ServerTask extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(HomeActivity1.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setTitle("Request in process");
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String[] doInBackground(String... params) {
            try {
                RegisterRequest();
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
                    switch (serverrequestFlag) {
                        case 1:
                            JSONArray jsonObj = new JSONArray(response);
                            ApplicationConstants.confirmedTrips = new ArrayList();
                            for (int i = 0; i < jsonObj.length(); i++) {
                                JSONObject c = jsonObj.getJSONObject(i);
                                TripModel tripModel = new TripModel();
                                tripModel.setId(c.getString("BNo"));
                                tripModel.setSource(c.getString("Src"));
                                tripModel.setDestination(c.getString("Dest"));
                                tripModel.setDate(c.getString("BookedDate"));
                                tripModel.setAmount(c.getString("Amount"));
                                ApplicationConstants.confirmedTrips.add(tripModel);

                            }
                            startActivity(new Intent(HomeActivity1.this, ConfirmedTrips.class));
                            break;
                        case 2:
                            ApplicationConstants.paymentMethods = new ArrayList();
                            jsonObj = new JSONArray(response);
                            for (int i = 0; i < jsonObj.length(); i++) {
                                JSONObject c = jsonObj.getJSONObject(i);
                                Payment_Method_model payment_method_model = new Payment_Method_model();
                                payment_method_model.setId(c.getString("Id"));
                                payment_method_model.setNo(c.getString("AccountNumber"));
                                payment_method_model.setIsprimary(c.getString("IsPrimary"));
                                payment_method_model.setType(c.getString("paymenttype"));
                                ApplicationConstants.paymentMethods.add(payment_method_model);

                            }
                            startActivity(new Intent(HomeActivity1.this, Activity_Payments.class));
                            ApplicationConstants.tripFlag = 0;
                            break;
                        case 3:
                            jsonObj = new JSONArray(response);
                            for (int i = 0; i < jsonObj.length(); i++) {
                                JSONObject c = jsonObj.getJSONObject(i);
                                StopsModel stopsModel = new StopsModel();
                                stopsModel.setId(Integer.parseInt(c.getString("Id")));
                                stopsModel.setStopName(c.getString("Name"));
                                stopsModel.setDescription(c.getString("Description"));
                                stopsModel.setLatitude(c.getString("Latitude"));
                                stopsModel.setLongitude(c.getString("Longitude"));
                                ApplicationConstants.stopsArraylist.add(stopsModel);
                                // Toast.makeText(getContext(), "Stop  " + c.getString("Name"), Toast.LENGTH_LONG).show();
                                //  startActivity(new Intent(LoginActivity.this, HomeActivity1.class));
                                //  finish();

                            }
                            startActivity(new Intent(HomeActivity1.this, MeteredTaxi.class));
                            break;

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void RegisterRequest() {
        BufferedReader reader = null;
        response = "";
        try {
            URL url = null;
            switch (serverrequestFlag) {
                case 1:
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_get_booking_history) + ApplicationConstants.mobileNo);
                    break;
                case 2:
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_get_payment_methods) + ApplicationConstants.mobileNo);
                    //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
                    // Defined URL  where to send data
                    break;
                case 3:
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_get_taxi_stops));
                    break;

            }
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

    }*/
}
