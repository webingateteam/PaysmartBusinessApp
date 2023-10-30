package com.webingate.paysmartbusinessapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.WalletBalanceResponse;
import com.webingate.paysmartbusinessapp.R;

import java.util.List;

public class businessapp_transactionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WalletBalanceResponse> translist;

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, WalletBalanceResponse flight, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public businessapp_transactionsAdapter(List<WalletBalanceResponse> flightsList) {
        this.translist = flightsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View flightsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.businessapp_tras_item, parent, false);
        return new FlightsViewHolder(flightsView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FlightsViewHolder) {
            WalletBalanceResponse tlist = translist.get(position);
            FlightsViewHolder flightsViewHolder = (FlightsViewHolder) holder;
            Context context = ((FlightsViewHolder) holder).flightsCardView.getContext();
            flightsViewHolder.flightsDescTextView.setText(tlist.getComments());
            flightsViewHolder.dispalydate.setText(tlist.getDate());
            flightsViewHolder.dispalamt.setText("$"+tlist.getAmt());
            //flightsViewHolder.flightsDescTextView.setText(tlist.getAmount());
            if (itemClickListener != null) {
                ((FlightsViewHolder) holder).flightsCardView.setOnClickListener(view -> itemClickListener.onItemClick(view, translist.get(position), position));
            }
        }
    }

    @Override
    public int getItemCount() {
        int a ;
        if(translist != null && !translist.isEmpty()) {
            a = translist.size();
        }
        else {
            a = 0;
        }
        return a;
    }

    public class FlightsViewHolder extends RecyclerView.ViewHolder {

        CardView flightsCardView;
        ImageView flightsImageView;
        TextView dispalydate;
        TextView flightsDescTextView;
        TextView dispalamt;
        TextView flightsPeriodTextView;


        public FlightsViewHolder(View itemView) {
            super(itemView);
            flightsCardView = itemView.findViewById(R.id.placeHolderCardView);
            flightsImageView = itemView.findViewById(R.id.flightsImageView);
            dispalydate = itemView.findViewById(R.id.textView7);
            flightsDescTextView = itemView.findViewById(R.id.tdd);
            dispalamt = itemView.findViewById(R.id.textView8);

        }
    }
}
