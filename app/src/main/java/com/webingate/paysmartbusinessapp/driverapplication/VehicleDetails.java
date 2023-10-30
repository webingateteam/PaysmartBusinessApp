package com.webingate.paysmartbusinessapp.driverapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DefaultResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleDetailsTable1Item;
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
public class VehicleDetails extends AppCompatActivity {
    MyCustomAdapter dataAdapter = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_vehicle_pic)
    ImageView vehicle_picture;
    @BindView(R.id.input_vehiclename)
    TextView name;
    @BindView(R.id.reg_number)
    TextView regdNo;
    @BindView(R.id.details_row)
    TableRow detailsRow;
    @BindView(R.id.listView1)
    ListView listView1;
    private String response;

    Toast toast;
    ProgressDialog dialog ;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_details);
        ButterKnife.bind(this);
        dialog =  new ProgressDialog.Builder(VehicleDetails.this)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .build();
        checkPermissions();
        // adding toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setting <-- button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        name.setText(ApplicationConstants.vehicleType);
        regdNo.setText(ApplicationConstants.registrationNo);
        byte[] decodedString = Base64.decode(ApplicationConstants.vehiclepic.substring(ApplicationConstants.vehiclepic.indexOf(",") + 1), Base64.DEFAULT);
        Bitmap image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        vehicle_picture.setImageBitmap(image);
        checkPermissions();
        detailsRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(VehicleDetails.this, AccountProfile.class));
            }
        });
        //Generate list View from ArrayList
        if (ApplicationConstants.documentslist != null)
            displayListView();

    }

    @Override
    public boolean onSupportNavigateUp() {
        //Toast.makeText(getApplicationContext(),"Back button Pressed",Toast.LENGTH_SHORT).show();
        onBackPressed();
        return true;
    }

    @SuppressWarnings("unchecked")
    private void displayListView() {


        //create an ArrayAdaptar from the String Array
        ListView listView = (ListView) findViewById(R.id.listView1);
        dataAdapter = new MyCustomAdapter(this, R.layout.layout_documents_list, ApplicationConstants.documentslist);
        // Assign adapter to ListView
        dataAdapter.notifyDataSetChanged();
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ApplicationConstants.documentNo = position;
                ApplicationConstants.document_number = "";
                ApplicationConstants.document_expire_date = "";
                ApplicationConstants.document_data = "";
                startActivityForResult(new Intent(VehicleDetails.this, DocumentUploadActivity.class), 42);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42) {
            if (resultCode == Activity.RESULT_OK) {


                JsonObject object = new JsonObject();
                VehicleDetailsTable1Item documentsModel = (VehicleDetailsTable1Item) ApplicationConstants.documentslist.get(ApplicationConstants.documentNo);
                object.addProperty("VehicleId", ApplicationConstants.vid);
                object.addProperty("Id", "1");
                object.addProperty("FileName", ApplicationConstants.document_format);
                object.addProperty("DocTypeId", documentsModel.getId());
                object.addProperty("ExpiryDate", ApplicationConstants.document_expire_date);
                object.addProperty("UpdatedById", "1");
                object.addProperty("DueDate", ApplicationConstants.document_expire_date);
                object.addProperty("FileContent", "data:" + ApplicationConstants.document_format + ";base64," + ApplicationConstants.document_data);
                object.addProperty("change", "I");
                object.addProperty("loggedinUserId", "1");
                object.addProperty("DocumentNo", ApplicationConstants.document_number);
                UploadDocument(object);

               /* DocumentUploadTask documentUploadTask = new DocumentUploadTask();
                documentUploadTask.execute();*/
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult


    private class MyCustomAdapter extends ArrayAdapter<VehicleDetailsTable1Item> {

        private List<VehicleDetailsTable1Item> logsselected;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               List<VehicleDetailsTable1Item> logsSelected) {
            super(context, textViewResourceId, logsSelected);
            this.logsselected = new ArrayList<VehicleDetailsTable1Item>();
            this.logsselected.addAll(logsSelected);
        }

        private class ViewHolder {
            TextView docname;
            ImageView approved;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.layout_documents_list, null);


                holder = new ViewHolder();
                holder.docname = (TextView) convertView.findViewById(R.id.text_name);
                // holder.docnumber = (TextView) convertView.findViewById(R.id.text_number);
                // holder.docexpireDate = (TextView) convertView.findViewById(R.id.text_expiredate);
                //  holder.approved = (ImageView) convertView.findViewById(R.id.image_is_approved);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            VehicleDetailsTable1Item logs = logsselected.get(position);
            // holder.code.setText(" (" +  logs.getCode() + ")");
            holder.docname.setText(logs.getDocName());
            // holder.docnumber.setText("Document No\t" + logs.getNumber());
            // holder.docexpireDate.setText("Document Expire Date\t" + logs.getExpireDate());
            return convertView;

        }

    }
    public void UploadDocument(JsonObject jsonObject){

        StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(VehicleDetails.this).getrestadapter()
                .SaveVehicleDoc(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DefaultResponse>>() {
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
                    public void onNext(List<DefaultResponse> responseList) {
                        DefaultResponse response=responseList.get(0);


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





   /* class DocumentUploadTask extends AsyncTask<String, Void, String[]> {
        ProgressDialog dialog = new ProgressDialog(VehicleDetails.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setTitle("Upload in process");
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
                VehicleDetailsTable1Item documentsModel = (VehicleDetailsTable1Item) ApplicationConstants.documentslist.get(ApplicationConstants.documentNo);
                if (response.matches("")) {
                    Toast.makeText(getApplicationContext(), documentsModel.getId(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "An error has occurred ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), documentsModel.getId(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

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
            JSONObject object = new JSONObject();
            VehicleDetailsTable1Item documentsModel = (VehicleDetailsTable1Item) ApplicationConstants.documentslist.get(ApplicationConstants.documentNo);
            object.put("VehicleId", ApplicationConstants.vid);
            object.put("Id", "1");
            object.put("FileName", ApplicationConstants.document_format);
            object.put("DocTypeId", documentsModel.getId());
            object.put("ExpiryDate", ApplicationConstants.document_expire_date);
            object.put("UpdatedById", "1");
            object.put("DueDate", ApplicationConstants.document_expire_date);
            object.put("FileContent", "data:" + ApplicationConstants.document_format + ";base64," + ApplicationConstants.document_data);
            object.put("change", "I");
            object.put("loggedinUserId", "1");
            object.put("DocumentNo", ApplicationConstants.document_number);
            URL url = new URL(getResources().getString(R.string.url_server) + getResources().getString(R.string.url_save_vehicledoc));
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
            e.printStackTrace();
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

    }*/

    /* Check Location Permission for Marshmallow Devices */
    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(VehicleDetails.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED)
                requesstoragePermission();

        }

    }

    /*  Show Popup to access User Permission  */
    private void requesstoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(VehicleDetails.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(VehicleDetails.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    20);

        } else {
            ActivityCompat.requestPermissions(VehicleDetails.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    20);
        }
    }
}