package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.object.News;
import com.webingate.paysmartbusinessapp.repository.news.NewsRepository;

import java.util.List;

public class customerAppTTicketTimelineActivity extends AppCompatActivity {

    private List<News> newsList;

    // ui variables
    private customerappTTicketTimelineAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.customerapp_ttickettimeline_activity);

        initData();

        initUI();

        initDataBindings();

        initActions();

    }

//   @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(this, "Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initData() {

        // get place list
        newsList = NewsRepository.getNewsList();

    }

    private void initUI() {

        initToolbar();

        // get list adapter
        adapter = new customerappTTicketTimelineAdapter(newsList);

        // get recycler view
        recyclerView = findViewById(R.id.reviewRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

    }

    private void initDataBindings() {

        // bind adapter to recycler
        recyclerView.setAdapter(adapter);
    }

    private void initActions() {

        adapter.setOnItemClickListener((view, obj, position) -> Toast.makeText(this, "Selected : " + obj.title, Toast.LENGTH_LONG).show());

    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Trouble ticket details");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        } catch (Exception e) {
            Log.e("TEAMPS", "Can't set color.");
        }

        try {
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set display home as up enabled.");
        }
    }
}
