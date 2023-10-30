package com.webingate.paysmartbusinessapp.driverapplication;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.webingate.paysmartbusinessapp.R;
public class MobileArrayAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater = null;

    public MobileArrayAdapter(Activity a) {
        activity = a;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return 5;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.activity_listview, null);

        //  TextView title = (TextView)vi.findViewById(R.id.title); // title

        return vi;
    }

    static class ViewHolder {
        @BindView(R.id.bookNo)
        TextView bookNo;
        @BindView(R.id.row1)
        TableRow row1;
        @BindView(R.id.textview1)
        TextView textview1;
        @BindView(R.id.row2)
        TableRow row2;
        @BindView(R.id.textview2)
        TextView textview2;
        @BindView(R.id.row3)
        TableRow row3;
        @BindView(R.id.accept)
        AppCompatButton accept;
        @BindView(R.id.reject)
        AppCompatButton reject;
        @BindView(R.id.row4)
        TableRow row4;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}