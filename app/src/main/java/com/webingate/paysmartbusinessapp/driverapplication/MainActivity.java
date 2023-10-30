package com.webingate.paysmartbusinessapp.driverapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsTableItem;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetCustomerAccountResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.PendingDocsResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.PendingDocsTable1Item;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.PendingDocsTableItem;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleDetailsTableItem;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String DRIVERID = "driverid";
    public static final String VEHICLEID = "vehicleid";
    private static final int HOME = 0;

    TextView mytext;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private int serverrequestFlag;
    private String response;
    Toast toast;
   ProgressDialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(MainActivity.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.grey_3)
                .build();
       /* initGoogleAPIClient();//Init Google API Client
        checkPermissions();//Check Permission*/
        //Open home pase as start page
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ApplicationConstants.driverId = prefs.getString(DRIVERID, null);
        ApplicationConstants.vid = prefs.getString(VEHICLEID, null);
        GoPage(HOME);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        serverrequestFlag = 3;
        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ApplicationConstants.driverId = prefs.getString(DRIVERID, null);
        PendingDocs(ApplicationConstants.driverId);

       /* ServerTask serverTask = new ServerTask();
        serverTask.execute();*/
    }

    @Override
    public void onResume() {
        super.onResume();
        // serverrequestFlag=3;
        // ServerTask serverTask=new ServerTask();
        // serverTask.execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (item.getItemId()) {

//            case R.id.my_profile:
//                SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                ApplicationConstants.driverId = prefs.getString(DRIVERID, null);
//                GetDriverDetails(ApplicationConstants.driverId);

               /* serverrequestFlag = 1;
                ServerTask serverTask = new ServerTask();
                serverTask.execute();*/

                //break;
//            case R.id.vehicle_profile:
//                 prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                ApplicationConstants.vid = prefs.getString(VEHICLEID, null);
//                GetVehicleDetails(ApplicationConstants.vid);

               /* serverrequestFlag = 2;
                serverTask = new ServerTask();
                serverTask.execute();*/

              //  break;
            case R.id.menu_payments:
//                prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                ApplicationConstants.driverId = prefs.getString(DRIVERID, null);
//                GetCustomerAccount(ApplicationConstants.driverId);

                /*serverrequestFlag = 4;
                serverTask = new ServerTask();
                serverTask.execute();*/


                break;
//            case R.id.menu_reset_passwords:
//                startActivity(new Intent(MainActivity.this, ResetPassword.class));
//                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Fragment Transaction Method
    private void GoPage(int page) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (page) {
            case HOME:
                fragmentClass = Home.class;
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
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }



    public void GetDriverDetails(String did){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(MainActivity.this).getrestadapter()
                .GetDriverDetails(did)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DriverDetailsResponse>() {
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
                    public void onNext(DriverDetailsResponse response) {

                        List<DriverDetailsTableItem> tableItems= response.getTable();
                        DriverDetailsTableItem tableItem= tableItems.get(0);

                        /*ApplicationConstants.username = tableItem.getNAme();
                        ApplicationConstants.address = tableItem.getAddress();
                        ApplicationConstants.mobileNo = tableItem.getPMobNo();
                        ApplicationConstants.profilepic = tableItem.getPhoto();*/

                        ApplicationConstants.documentslist = response.getTable1();
                        Intent intent=new Intent(new Intent(MainActivity.this, DriverDetails.class));
                        intent.putExtra("details",tableItem);
                        startActivity(intent);


                    }
                });
    }

    public void GetVehicleDetails(String vid){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(MainActivity.this).getrestadapter()
                .GetVehicleDetails(vid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VehicleDetailsResponse>() {
                    @Override
                    public void onCompleted() {
                     //   DisplayToast("Successfully Registered");
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
                    public void onNext(VehicleDetailsResponse response) {

                        List<VehicleDetailsTableItem> tableItems= response.getTable();
                        VehicleDetailsTableItem tableItem= tableItems.get(0);
                        ApplicationConstants.registrationNo =tableItem.getRegistrationNo();
                        ApplicationConstants.vehicleType = tableItem.getVehicleType();
                        ApplicationConstants.vehiclepic = tableItem.getPhoto();
                        ApplicationConstants.documentslist = response.getTable1();
                        startActivity(new Intent(MainActivity.this, VehicleDetails.class));


                    }
                });
    }

    public void PendingDocs(String userid){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(MainActivity.this).getrestadapter()
                .PendingDocs(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PendingDocsResponce>() {
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
                    public void onNext(PendingDocsResponce response) {
                        StringBuffer stringBuffer = new StringBuffer();
                        List<PendingDocsTableItem> tableItems=response.getTable();
                        ApplicationConstants.documentslist = new ArrayList();
                        for (int i = 0; i < tableItems.size(); i++) {

                            stringBuffer.append(tableItems.get(i).getName() + "\n");
                            serverrequestFlag = 1;
                        }
                        List<PendingDocsTable1Item> tableI1tems=response.getTable1();
                        ApplicationConstants.documentslist = new ArrayList();
                        for (int i = 0; i < tableI1tems.size(); i++) {

                            stringBuffer.append(tableI1tems.get(i).getName()  + "\n");
                            if (serverrequestFlag != 1)
                                serverrequestFlag = 2;
                        }
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setMessage("Documents To Upload\n\n\n" + stringBuffer);
                        LinearLayout layout = new LinearLayout(MainActivity.this);
                        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        layout.setLayoutParams(parms);

                        layout.setGravity(Gravity.CLIP_VERTICAL);
                        layout.setPadding(2, 2, 2, 2);

                        alertDialogBuilder.setCancelable(false);

                        // Setting Negative "Cancel" Button
                        alertDialogBuilder.setNegativeButton("Update Now", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                                SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                                ApplicationConstants.driverId = prefs.getString(VEHICLEID, null);
                                ApplicationConstants.driverId = prefs.getString(DRIVERID, null);
                                GetVehicleDetails(ApplicationConstants.vid);
                                if(serverrequestFlag==1){
                                    GetDriverDetails(ApplicationConstants.driverId);
                                }else {
                                    GetVehicleDetails(ApplicationConstants.driverId);
                                }
                               /* ServerTask serverTask = new ServerTask();
                                serverTask.execute();*/

                            }
                        });

                        // Setting Positive "OK" Button
                        alertDialogBuilder.setPositiveButton("Update Later", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                // finish();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();

                        try {
                            if (serverrequestFlag != 3) {
                                alertDialog.show();
                            }
                        } catch (Exception e) {
                            // WindowManager$BadTokenException will be caught and the app would
                            // not display the 'Force Close' message
                            e.printStackTrace();
                        }
                    }
                });
    }


    public void GetCustomerAccount(String userid){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(MainActivity.this).getrestadapter()
                .GetCustomerAccount(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GetCustomerAccountResponce>>() {
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
                    public void onNext(List<GetCustomerAccountResponce> response) {
                        ApplicationConstants.paymentMethods = response;
                        startActivity(new Intent(MainActivity.this, Activity_Payments.class));
                        ApplicationConstants.tripflag = 0;

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





   /* class ServerTask extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);

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
                            // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Table");
                            JSONObject c = jsonArray.getJSONObject(0);
                            ApplicationConstants.username = c.getString("NAme");
                            ApplicationConstants.address = c.getString("Address");
                            ApplicationConstants.mobileNo = c.getString("PMobNo");
                            ApplicationConstants.profilepic = c.getString("Photo");
                            jsonArray = jsonObject.getJSONArray("Table1");
                            ApplicationConstants.documentslist = new ArrayList();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                c = jsonArray.getJSONObject(i);
                                DocumentsModel documentsModel = new DocumentsModel();
                                documentsModel.setId(c.getString("DocTypeId"));
                                documentsModel.setName(c.getString("docName"));
                                documentsModel.setExpireDate(c.getString("expiryDate"));
                                documentsModel.setNumber(c.getString("DocumentNo"));
                                // documentsModel.setIsuploaded(c.getString("IsExpired"));
                                ApplicationConstants.documentslist.add(documentsModel);
                            }
                            startActivity(new Intent(MainActivity.this, DriverDetails.class));
                            break;
                        case 2:
                            // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("Table");
                            c = jsonArray.getJSONObject(0);
                            ApplicationConstants.registrationNo = c.getString("RegistrationNo");
                            ApplicationConstants.vehicleType = c.getString("VehicleType");
                            ApplicationConstants.vehiclepic = c.getString("Photo");
                            jsonArray = jsonObject.getJSONArray("Table1");
                            ApplicationConstants.documentslist = new ArrayList();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                c = jsonArray.getJSONObject(i);
                                DocumentsModel documentsModel = new DocumentsModel();
                                documentsModel.setId(c.getString("DocTypeId"));
                                documentsModel.setName(c.getString("docName"));
                                documentsModel.setExpireDate(c.getString("expiryDate"));
                                documentsModel.setNumber(c.getString("DocumentNo"));
                                // documentsModel.setIsuploaded(c.getString("IsExpired"));
                                ApplicationConstants.documentslist.add(documentsModel);
                            }
                            startActivity(new Intent(MainActivity.this, VehicleDetails.class));
                            break;
                        case 3:
                            jsonObject = new JSONObject(response);
                            StringBuffer stringBuffer = new StringBuffer();
                            jsonArray = jsonObject.getJSONArray("Table");
                            ApplicationConstants.documentslist = new ArrayList();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                c = jsonArray.getJSONObject(i);
                                stringBuffer.append(c.getString("Name") + "\n");
                                serverrequestFlag = 1;
                            }
                            jsonArray = jsonObject.getJSONArray("Table1");
                            ApplicationConstants.documentslist = new ArrayList();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                c = jsonArray.getJSONObject(i);
                                stringBuffer.append(c.getString("Name") + "\n");
                                if (serverrequestFlag != 1)
                                    serverrequestFlag = 2;
                            }
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                            alertDialogBuilder.setMessage("Documents To Upload\n\n\n" + stringBuffer);
                            LinearLayout layout = new LinearLayout(MainActivity.this);
                            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.setLayoutParams(parms);

                            layout.setGravity(Gravity.CLIP_VERTICAL);
                            layout.setPadding(2, 2, 2, 2);

                            alertDialogBuilder.setCancelable(false);

                            // Setting Negative "Cancel" Button
                            alertDialogBuilder.setNegativeButton("Update Now", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                                    ServerTask serverTask = new ServerTask();
                                    serverTask.execute();

                                }
                            });

                            // Setting Positive "OK" Button
                            alertDialogBuilder.setPositiveButton("Update Later", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    // finish();
                                }
                            });

                            AlertDialog alertDialog = alertDialogBuilder.create();

                            try {
                                if (serverrequestFlag != 3) {
                                    alertDialog.show();
                                }
                            } catch (Exception e) {
                                // WindowManager$BadTokenException will be caught and the app would
                                // not display the 'Force Close' message
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            ApplicationConstants.paymentMethods = new ArrayList();
                            JSONArray jsonObj = new JSONArray(response);
                            for (int i = 0; i < jsonObj.length(); i++) {
                                c = jsonObj.getJSONObject(i);
                                Payment_Method_model payment_method_model = new Payment_Method_model();
                                payment_method_model.setId(c.getString("Id"));
                                payment_method_model.setNo(c.getString("AccountNumber"));
                                payment_method_model.setIsprimary(c.getString("IsPrimary"));
                                payment_method_model.setType(c.getString("paymenttype"));
                                ApplicationConstants.paymentMethods.add(payment_method_model);

                            }
                            startActivity(new Intent(MainActivity.this, Activity_Payments.class));
                            ApplicationConstants.tripflag = 0;
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
                    SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    ApplicationConstants.driverId = prefs.getString(DRIVERID, null);
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_getdriver_details) + ApplicationConstants.driverId);
                    break;
                case 2:
                    prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    ApplicationConstants.vid = prefs.getString(VEHICLEID, null);
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_getvehicle_details) + ApplicationConstants.vid);
                    break;
                case 3:
                    prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    ApplicationConstants.driverId = prefs.getString(DRIVERID, null);
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_pendingdocs) + ApplicationConstants.driverId);
                    break;
                case 4:
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_get_payment_methods) + ApplicationConstants.driverId);
                    //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
                    // Defined URL  where to send data
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
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

    }
*/

}
