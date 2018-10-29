package com.webingate.paysmartbusinessapp.customerapp;

/**
 * Created by Seshu on 3/24/2017.
 */
import com.webingate.paysmartbusinessapp.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TravelsAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<TravelModel> animalNamesList = null;
    private ArrayList<TravelModel> arraylist;

    public TravelsAdapter(Context context, List<TravelModel> animalNamesList) {
        mContext = context;
        this.animalNamesList = animalNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<TravelModel>();
        this.arraylist.addAll(animalNamesList);
    }



    public class ViewHolder {
        TextView name,time,subtitle,price;
    }

    @Override
    public int getCount() {
        return animalNamesList.size();
    }

    @Override
    public TravelModel getItem(int position) {
        return animalNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.travelslist, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.time = (TextView) view.findViewById(R.id.time);
            holder.subtitle = (TextView) view.findViewById(R.id.subtitle);
            holder.price = (TextView) view.findViewById(R.id.price);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(animalNamesList.get(position).getName());
        holder.time.setText(animalNamesList.get(position).getTime());
        holder.subtitle.setText(animalNamesList.get(position).getSubTitle());
        holder.price.setText(animalNamesList.get(position).getPrice());


        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        animalNamesList.clear();
        if (charText.length() == 0) {
            animalNamesList.addAll(arraylist);
        } else {
            for (TravelModel wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    animalNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
