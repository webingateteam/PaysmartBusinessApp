package com.webingate.paysmartbusinessapp.customerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.webingate.paysmartbusinessapp.businessapp.Deo.CustomerGetstopsResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;
public class TaxiStops extends AppCompatActivity implements SearchView.OnQueryTextListener {
    // Declare Variables

    ListViewAdapter adapter;


    @BindView(R.id.search)
    SearchView editsearch;
    @BindView(R.id.listview)
    ListView list;
    @BindView(R.id.layout)
    LinearLayout layout;
    //String[] stopsList;
    List<CustomerGetstopsResponse> stops;
    @SuppressLint("NewApi")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stops);
        ButterKnife.bind(this);
        stops= (List<CustomerGetstopsResponse>) getIntent().getSerializableExtra("details");
        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(getApplicationContext(), stops);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //   Toast.makeText(getContext(), stopsList[position], Toast.LENGTH_SHORT).show();
                if (ApplicationConstants.source.matches("")) {
                    ApplicationConstants.sourceid = position;
                    setResult(RESULT_OK, getIntent());
                    finish();
                } else {
                    ApplicationConstants.destinationid = position;
                    setResult(RESULT_OK, getIntent());
                    finish();
                }
            }
        });

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

}