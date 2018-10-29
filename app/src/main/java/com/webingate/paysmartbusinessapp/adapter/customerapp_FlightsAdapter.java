package com.webingate.paysmartbusinessapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.object.DirectoryHome9FlightsVO;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

public class customerapp_FlightsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DirectoryHome9FlightsVO> flightsList;

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, DirectoryHome9FlightsVO flight, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public customerapp_FlightsAdapter(List<DirectoryHome9FlightsVO> flightsList) {
        this.flightsList = flightsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View flightsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerapp_dashboard_flightsitem, parent, false);
        return new FlightsViewHolder(flightsView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FlightsViewHolder) {
            DirectoryHome9FlightsVO flightsVO = flightsList.get(position);
            FlightsViewHolder flightsViewHolder = (FlightsViewHolder) holder;
            Context context = flightsViewHolder.flightsImageView.getContext();

            flightsViewHolder.flightsTitleTextView.setText(flightsVO.getCountry());
            flightsViewHolder.flightsDescTextView.setText(flightsVO.getDescription());
            flightsViewHolder.flightsPeriodTextView.setText("Travel Period- " + flightsVO.getDuration());
            flightsViewHolder.flightsPriceTextView.setText(flightsVO.getPrice());

            int flightImageId = Utils.getDrawableInt(context, flightsVO.getImage());
            Utils.setImageToImageView(context, flightsViewHolder.flightsImageView, flightImageId);

            if (itemClickListener != null) {
                ((FlightsViewHolder) holder).flightsCardView.setOnClickListener(view -> itemClickListener.onItemClick(view, flightsList.get(position), position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return flightsList.size();
    }

    public class FlightsViewHolder extends RecyclerView.ViewHolder {

        CardView flightsCardView;
        ImageView flightsImageView;
        TextView flightsTitleTextView;
        TextView flightsDescTextView;
        TextView flightsPriceTextView;
        TextView flightsPeriodTextView;

        public FlightsViewHolder(View itemView) {
            super(itemView);

            flightsCardView = itemView.findViewById(R.id.flightCardView);
            flightsImageView = itemView.findViewById(R.id.flightsImageView);
            flightsTitleTextView = itemView.findViewById(R.id.flightsTitleTextView);
            flightsDescTextView = itemView.findViewById(R.id.flightsDescTextView);
            flightsPriceTextView = itemView.findViewById(R.id.flightsPriceTextView);
            flightsPeriodTextView = itemView.findViewById(R.id.flightsPeriodTextView);
        }
    }
}
