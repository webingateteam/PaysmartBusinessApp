package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.activity.businessapp.customerappGetaLyftBookingsListActivity;
import com.webingate.paysmartbusinessapp.adapter.businessapp_AdapterBookingType;
import com.webingate.paysmartbusinessapp.adapter.customerapp_AdapterBookingType;
import com.webingate.paysmartbusinessapp.data.DataGenerator;
import com.webingate.paysmartbusinessapp.model.BookingType;
import com.webingate.paysmartbusinessapp.utils.Tools;
import com.webingate.paysmartbusinessapp.widget.LineItemDecoration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

import static com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppUploadDocsFragment.GET_FROM_GALLERY;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppDocCheckingFragment extends Fragment {


    private businessapp_AdapterBookingType mAdapter;
    private RecyclerView recyclerView;

    @BindView(R.id.statusdoc)
    TextView sts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.customerapp_bookingsmain_activity, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);
//        //setting <-- button to toolbar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initUI(view);
        initToolbar(view);
        initComponent(view);
//        expireDate.setOnClickListener(getActivity());
//        chooseFile.setOnClickListener();
//        submit.setOnClickListener();


//        DriverDetailsTable1Item documentsModel = (DriverDetailsTable1Item) ApplicationConstants.documentslist.get(ApplicationConstants.documentNo);
//        documentName.setText(documentsModel.getDocName());
//        documentNo.setText(documentsModel.getDocumentNo());
//        expireDate.setText(documentsModel.getExpiryDate());

        return view;
    }

    private void initUI(View view) {
       sts=(TextView) view.findViewById(R.id.statusdoc);
    }

    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Booking Types");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Tools.setSystemBarColor(getActivity());
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//        return true;
//    }
    private void initComponent(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<BookingType> items = DataGenerator.getDoctypes(getContext());

        //set data and list adapter
        mAdapter = new businessapp_AdapterBookingType(getContext(), items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new businessapp_AdapterBookingType.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BookingType obj, int position) {
                Toast.makeText(getContext(), "You have selected "+obj.name, Toast.LENGTH_SHORT).show();
                browsePhoto("");

            }
        });

    }
//    private void GoToBookingsList(int pos){
//        Intent intent;
//        intent = new Intent(this, customerappGetaLyftBookingsListActivity.class);
//        startActivity(intent);
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           // finish();
        } else {
            Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void browsePhoto(String imageName) {

        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Detects request codes

        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
                Toast.makeText(getContext(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
//                sts.setText("Uploaded");
//                sts.setTextColor(Color.GREEN);
                //imag.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
