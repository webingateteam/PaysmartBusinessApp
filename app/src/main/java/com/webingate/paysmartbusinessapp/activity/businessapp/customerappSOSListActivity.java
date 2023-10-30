package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.adapter.customerapp_AdapterDragAndDropList;
import com.webingate.paysmartbusinessapp.object.GeneralList;
import com.webingate.paysmartbusinessapp.repository.general.GeneralListRepository;

import com.webingate.paysmartbusinessapp.utils.drag_and_swipe.OnStartDragListener;
import com.webingate.paysmartbusinessapp.utils.drag_and_swipe.SimpleItemDragOnlyHelperCallback;

import java.util.List;

public class customerappSOSListActivity extends AppCompatActivity implements OnStartDragListener {

    private List<GeneralList> generalListList;
    private customerapp_AdapterDragAndDropList adapter;

    private RecyclerView recyclerView;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerapp_soslist_activity);

        initData();

        initUI();

        initDataBindings();

        initActions();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

        // get place list
        generalListList = GeneralListRepository.getGeneralList();
    }

    private void initUI() {
        initToolbar();

        // get list adapter
        adapter = new customerapp_AdapterDragAndDropList(generalListList, this);

        // get recycler view
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        ItemTouchHelper.Callback callback = new SimpleItemDragOnlyHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initDataBindings() {
        // bind adapter to recycler
        recyclerView.setAdapter(adapter);
    }

    private void initActions() {
        adapter.setOnItemClickListener((view, obj, position) -> Toast.makeText(this, "Selected : " + obj.name, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Drag and Drop List");

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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
