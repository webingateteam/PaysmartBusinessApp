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

public class TicketsAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<TicketModel> animalNamesList = null;
    private ArrayList<TicketModel> arraylist;

    public TicketsAdapter(Context context, List<TicketModel> animalNamesList) {
        mContext = context;
        this.animalNamesList = animalNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<TicketModel>();
        this.arraylist.addAll(animalNamesList);
    }



    public class ViewHolder {
        TextView ticketNo,dateAndTime,source,destination;
    }

    @Override
    public int getCount() {
        return animalNamesList.size();
    }

    @Override
    public TicketModel getItem(int position) {
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
            view = inflater.inflate(R.layout.ticketslist, null);
            // Locate the TextViews in listview_item.xml
            holder.ticketNo = (TextView) view.findViewById(R.id.ticketno);
            holder.dateAndTime = (TextView) view.findViewById(R.id.dateandtime);
            holder.source = (TextView) view.findViewById(R.id.source);
            holder.destination = (TextView) view.findViewById(R.id.destination);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.ticketNo.setText("Ticket NO - "+animalNamesList.get(position).getTicketNo()+"            ");
        holder.dateAndTime.setText("Date : "+animalNamesList.get(position).getDate());
        holder.source.setText("From : "+animalNamesList.get(position).getSource());
        holder.destination.setText("To : "+animalNamesList.get(position).getDestination());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        animalNamesList.clear();
        if (charText.length() == 0) {
            animalNamesList.addAll(arraylist);
        } else {
            for (TicketModel wp : arraylist) {
                if (wp.getTicketNo().toLowerCase(Locale.getDefault()).contains(charText)) {
                    animalNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
