package com.webingate.paysmartbusinessapp.driverapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RideDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.SaveSOSNumberResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Dialog.ProgressDialog;

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
public class ContactsList extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listview1)
    ListView listview;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    ArrayList<ContactModel> StoreContacts;
    MyCustomAdapter dataAdapter = null;
    Cursor cursor;
    public static final int RequestPermissionCode = 1;
    boolean islongclicked = false;

    private String response;
    String selectedName, selectedMobileNo;
    Toast toast;
    ProgressDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacts_list);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(ContactsList.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // you can title and subtitle dynamically
        //getSupportActionBar().setTitle("Payments");
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        EnableRuntimePermission();
        StoreContacts = new ArrayList<ContactModel>();
        listview = (ListView) findViewById(R.id.listview1);
        GetContactsIntoArrayList();
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        fabAdd.setVisibility(View.GONE);
        dataAdapter = new MyCustomAdapter(this, R.layout.contact_items_listview, StoreContacts);
        dataAdapter.notifyDataSetChanged();
        listview.setAdapter(dataAdapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                selectedName = StoreContacts.get(position).getName();
                selectedMobileNo = StoreContacts.get(position).getPhoneNo();
                JsonObject object = new JsonObject();
                object.addProperty("flag", "I");
                object.addProperty("Id", "");
                object.addProperty("UserId", ApplicationConstants.mobileNo);
                object.addProperty("MobileNumber", selectedMobileNo);
                object.addProperty("UserTypeId", "");
                object.addProperty("CreatedOn", "");
                object.addProperty("Active", "");
                object.addProperty("MobiOrder", "");
                Savesos(object);

                /*SavesosTask savesosTask = new SavesosTask();
                savesosTask.execute();*/
            }
        });
        /*listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Object o = adapterView.getAdapter().getItem(position);
                String keyword = o.toString();
                Toast.makeText(getApplicationContext(), "You selected: " + keyword, Toast.LENGTH_SHORT).show();
                listView.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                listView.getChildAt(position).setSelected(true);
                return false;
            }
        });*/

    }

    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        return true;
    }

    public void GetContactsIntoArrayList() {

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        while (cursor.moveToNext()) {
            ContactModel contactModel = new ContactModel();
            contactModel.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            contactModel.setPhoneNo(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

            StoreContacts.add(contactModel);
        }

        cursor.close();

    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                ContactsList.this,
                Manifest.permission.READ_CONTACTS)) {

            Toast.makeText(ContactsList.this, "CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(ContactsList.this, new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(ContactsList.this, "Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(ContactsList.this, "Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    private class MyCustomAdapter extends ArrayAdapter<ContactModel> {

        private ArrayList<ContactModel> logsselected;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<ContactModel> logsSelected) {
            super(context, textViewResourceId, logsSelected);
            this.logsselected = new ArrayList<ContactModel>();
            this.logsselected.addAll(logsSelected);
        }

        private class ViewHolder {
            TextView name, phoneNumber;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.contact_items_listview, null);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.contat_name);
                holder.phoneNumber = (TextView) convertView.findViewById(R.id.contact_number);
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

            ContactModel logs = logsselected.get(position);
            // holder.code.setText(" (" +  logs.getCode() + ")");
            holder.name.setText(logs.getName());
            holder.phoneNumber.setText(logs.getPhoneNo());
            return convertView;

        }

    }

    public void Savesos(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(ContactsList.this).getrestadapter()
                .SaveSOSNumber(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<SaveSOSNumberResponce>>() {
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
                    public void onNext(List<SaveSOSNumberResponce> response) {


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
  /*  class SavesosTask extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(ContactsList.this);

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
                SavesosProcess();
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
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    finish();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void SavesosProcess() {
        BufferedReader reader = null;
        response = "";
        try {
            JSONObject object = new JSONObject();
            object.put("flag", "I");

            object.put("Id", "");

            object.put("UserId", ApplicationConstants.mobileNo);

            object.put("MobileNumber", selectedMobileNo);

            object.put("UserTypeId", "");

            object.put("CreatedOn", "");

            object.put("Active", "");

            object.put("MobiOrder", "");

            //  String data = URLEncoder.encode(requestkey, "UTF-8") + "=" + URLEncoder.encode(requestvalue, "UTF-8");
            // Defined URL  where to send data
            URL url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_save_sos));
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

    }
*/

}
