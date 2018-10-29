package com.webingate.paysmartbusinessapp.customerapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.businessapp.Deo.CustomerGetstopsResponse;
import com.webingate.paysmartbusinessapp.businessapp.Dialog.ProgressDialog;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.webingate.paysmartbusinessapp.R;
@SuppressLint("NewApi")
public class Home extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.layoutDots)
    LinearLayout dotsLayout;
    @BindView(R.id.btn_getalyft)
    AppCompatButton getaLyft;
    @BindView(R.id.btn_metered_taxi)
    AppCompatButton meteredTaxi;
    @BindView(R.id.btn_ticket)
    AppCompatButton bookTicket;
    @BindView(R.id.btn_train)
    AppCompatButton btnTrain;
    @BindView(R.id.btn_cruise)
    AppCompatButton btnCruise;
    @BindView(R.id.btn_ambulance)
    AppCompatButton ambulance_hire;
    @BindView(R.id.btn_towing)
    AppCompatButton towing_hire;
    @BindView(R.id.btn_parcel_services)
    AppCompatButton btnParcelServices;
    @BindView(R.id.btn_ewallet)
    AppCompatButton eWallet;
    @BindView(R.id.btn_shop)
    AppCompatButton shop;
    @BindView(R.id.btn_hotel)
    AppCompatButton hotel;
    @BindView(R.id.btn_flight)
    AppCompatButton btnFlight;
    Unbinder unbinder;
    private String response;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView[] dots;
    private int[] layouts;
    private Thread t;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Phone = "phoneKey";
    public static final String Name = "nameKey";
    private static final int ACCESS_FINE_LOCATION_INTENT_ID = 3;
    private int stopsflag = 0;
    public final static int REQUEST_CODE = 10101;

    Toast toast;
    ProgressDialog dialog ;

    public static Home newInstance(int SectionNumber) {
        Home home = new Home();
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
        View v = inflater.inflate(R.layout.home_screen, container, false);
        unbinder = ButterKnife.bind(this, v);
        dialog =  new ProgressDialog.Builder(getActivity())
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        ApplicationConstants.source = "";
        ApplicationConstants.destination = "";
        ApplicationConstants.date = "";
        checkPermissions();
        View.OnClickListener buttonoOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_shop:


                        break;
                    case R.id.btn_ticket:
                        stopsflag = 1;
                        GetStops();

                       /* StopsRequest stopsRequest = new StopsRequest();
                        stopsRequest.execute();*/

                        //  ApplicationConstants.FRAGMENT=ApplicationConstants.TICKET_SOURCE_DESTINATION;
                        //  goPage(ApplicationConstants.FRAGMENT);
                        break;
                    case R.id.btn_getalyft:
                        ApplicationConstants.marker = R.mipmap.marker_taxi;
                        Intent intent = new Intent(getActivity(), GetaLyft.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_metered_taxi:
                        ApplicationConstants.marker = R.mipmap.marker_taxi;
                        ApplicationConstants.source = "";
                        ApplicationConstants.destination = "";
                        stopsflag = 2;
                        TaxiStops();

                        /*stopsRequest = new StopsRequest();
                        stopsRequest.execute();*/

                        break;
                    case R.id.btn_ambulance:
                        ApplicationConstants.marker = R.mipmap.marker_ambulance;
                        intent = new Intent(getActivity(), GetaLyft.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_towing:
                        ApplicationConstants.marker = R.mipmap.marker_towing;
                        intent = new Intent(getActivity(), GetaLyft.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_ewallet:
                        startActivity(new Intent(getActivity(), EWallet.class));
                        break;
                    case R.id.btn_hotel:

                        break;

                }

            }
        };
        getaLyft.setOnClickListener(buttonoOnClickListener);
        meteredTaxi.setOnClickListener(buttonoOnClickListener);
        bookTicket.setOnClickListener(buttonoOnClickListener);
        ambulance_hire.setOnClickListener(buttonoOnClickListener);
        towing_hire.setOnClickListener(buttonoOnClickListener);
        eWallet.setOnClickListener(buttonoOnClickListener);

        viewPager = (ViewPager) v.findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) v.findViewById(R.id.layoutDots);
        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(4500);
                        if (getActivity() == null)
                            return;

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!

                                int current = getItem(+1);
                                if (current < layouts.length) {
                                    // move to next screen
                                    viewPager.setCurrentItem(current);
                                } else {
                                    viewPager.setCurrentItem(0);

                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

        return v;
    }

    private void goPage(int page,Bundle bundle) {
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (page) {
            case ApplicationConstants.HOME:
                fragmentClass = Home.class;
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
                fragmentClass = EWallet.class;
                break;
            case ApplicationConstants.FEEDBACK:
              //  fragmentClass = Feedback.class;
                break;
        }

        try {

            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
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
                fragmentClass = EWallet.class;
                break;
            case ApplicationConstants.FEEDBACK:
             //   fragmentClass = Feedback.class;
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

    public void GetStops(){

        StartDialogue();
        com.webingate.paysmartbusinessapp.businessapp.Utils.DataPrepare.get(getActivity()).getrestadapter()
                .getstops()
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
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("details", (Serializable) responselist);
                        ApplicationConstants.FRAGMENT = ApplicationConstants.TICKET_SOURCE_DESTINATION;
                        goPage(ApplicationConstants.FRAGMENT,bundle);
                    }
                });
    }

    public void TaxiStops( ){
        StartDialogue();
        com.webingate.paysmartbusinessapp.businessapp.Utils.DataPrepare.get(getActivity()).getrestadapter()
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
                        Intent intent=new Intent(getActivity(), MeteredTaxi.class);
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
        toast=Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT);
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

   /* class StopsRequest extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setTitle("Processing....");
            dialog.setCanceledOnTouchOutside(false);
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
                    Toast.makeText(getContext(), "An error has occurred ", Toast.LENGTH_LONG).show();
                } else {
                    // Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    JSONArray jsonObj = new JSONArray(response);

                    // Getting JSON Array node
                    // JSONArray values = jsonObj.getJSONArray("");

                    // looping through All Contacts
                    ApplicationConstants.stopsArraylist.clear();
                    switch (stopsflag) {
                        case 1:
                            for (int i = 0; i < jsonObj.length(); i++) {
                                JSONObject c = jsonObj.getJSONObject(i);
                                StopsModel stopsModel = new StopsModel();
                                stopsModel.setId(Integer.parseInt(c.getString("Id")));
                                stopsModel.setStopName(c.getString("Name"));
                                stopsModel.setDescription(c.getString("Description"));
                                stopsModel.setCode(c.getString("Code"));
                                stopsModel.setActive(Integer.parseInt(c.getString("Active")));
                                ApplicationConstants.stopsArraylist.add(stopsModel);
                                // Toast.makeText(getContext(), "Stop  " + c.getString("Name"), Toast.LENGTH_LONG).show();
                                //  startActivity(new Intent(LoginActivity.this, HomeActivity1.class));
                                //  finish();

                            }
                            ApplicationConstants.FRAGMENT = ApplicationConstants.TICKET_SOURCE_DESTINATION;
                            goPage(ApplicationConstants.FRAGMENT);
                            break;
                        case 2:
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
                            startActivity(new Intent(getActivity(), MeteredTaxi.class));
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
            switch (stopsflag) {
                case 1:
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_get_stops));
                    break;
                case 2:
                    url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_get_taxi_stops));
                    break;
            }
            // JSONObject object = new JSONObject();
            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            // conn.setDoOutput (true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Host", "android.schoolportal.gr");
            conn.connect();
            // OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            // wr.write(object.toString());
            // wr.flush();

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

    public void OnGPS() {
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(getContext(), R.style.Dialog_Theme);

        // Setting Dialog Title
        mAlertDialog.setTitle("Location not available, Open GPS?")
                .setMessage("Activate GPS to use use location services?")
                .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        getContext().startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        mAlertDialog.show();
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);


        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    /* Check Location Permission for Marshmallow Devices */
    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.SYSTEM_ALERT_WINDOW)
                    != PackageManager.PERMISSION_GRANTED)
                checkDrawOverlayPermission();
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED)
                requestcallPermission();
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
                requestLocationPermission();

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(getActivity())) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getActivity().getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);
        }
    }

    private void requestcallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    ACCESS_FINE_LOCATION_INTENT_ID);

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    ACCESS_FINE_LOCATION_INTENT_ID);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case ACCESS_FINE_LOCATION_INTENT_ID: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getActivity(), "Location Permission denied.", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
}