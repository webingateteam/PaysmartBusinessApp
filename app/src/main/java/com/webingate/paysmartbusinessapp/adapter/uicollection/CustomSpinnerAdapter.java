package com.webingate.paysmartbusinessapp.adapter.uicollection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.webingate.paysmartbusinessapp.R;

public class CustomSpinnerAdapter extends BaseAdapter {

    private int icons[];
    private String[] fruits;
    private LayoutInflater layoutInflater;

    public CustomSpinnerAdapter(Context applicationContext, int[] icons, String[] fruits) {
        this.icons = icons;
        this.fruits = fruits;
        layoutInflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.ui_customspinner_item, viewGroup, false);

        ImageView icon = view.findViewById(R.id.imageView);
        TextView names = view.findViewById(R.id.textView);

        icon.setImageResource(icons[i]);
        names.setText(fruits[i]);
        
        return view;
    }
}