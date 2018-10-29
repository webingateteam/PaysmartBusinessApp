package com.webingate.paysmartbusinessapp.driverapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.webingate.paysmartbusinessapp.R;
public class CompletedTrips extends Activity {
    // All static variables
    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";

    MobileArrayAdapter adapter;
    @BindView(R.id.list)
    ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listfile);
        ButterKnife.bind(this);



        // Getting adapter by passing xml data ArrayList
        adapter = new MobileArrayAdapter(this);
        list.setAdapter(adapter);

        // Click event for single list row


    }
}