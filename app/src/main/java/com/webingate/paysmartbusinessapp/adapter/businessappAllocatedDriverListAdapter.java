package com.webingate.paysmartbusinessapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AllocatedDriverListResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Panacea-Soft on 17/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class businessappAllocatedDriverListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<AllocatedDriverListResponse> placeArrayList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, AllocatedDriverListResponse obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public businessappAllocatedDriverListAdapter(ArrayList<AllocatedDriverListResponse> placeArrayList) {
        this.placeArrayList = placeArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.businessapp_allocateddriverlist_item, parent, false);

        return new PlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof PlaceViewHolder) {

            AllocatedDriverListResponse place = placeArrayList.get(position);

            PlaceViewHolder holder = (PlaceViewHolder) viewHolder;
            holder.placeNameTextView.setText(place.getNAme());



            Context context = holder.placeHolderCardView.getContext();

            // int id = Utils.getDrawableInt(context, place.getPhoto());
            int id = Utils.getDrawableInt(context, "user_driver");
            Utils.setImageToImageView(context, holder.placeImageView, id);

            holder.typeTextView.setText(place.getRegistrationNo().toString());
            holder.cityTextView.setText(place.getDriverCode());
            holder.placeRatingBar.setRating(Float.parseFloat("4"));
            holder.totalRatingTextView.setText("4");
            holder.ratingCountTextView.setText("4");

//            if (place.discount != null && Integer.parseInt(place.discount) > 0) {
//                holder.promoCardView.setVisibility(View.VISIBLE);
//                String discount = place.discount + " %";
//                holder.promoAmtTextView.setText(discount);
//            } else {
//                holder.promoCardView.setVisibility(View.GONE);
//            }

            if ( itemClickListener != null ) {
                holder.placeHolderCardView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, placeArrayList.get(position), position));
            }
        }
    }

    @Override
    public int getItemCount() {
        int a ;
        if(placeArrayList != null && !placeArrayList.isEmpty()) {
            a = placeArrayList.size();
        }
        else {
            a = 0;
        }
        return a;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        public ImageView placeImageView;
        public TextView placeNameTextView;
        public TextView typeTextView;
        public TextView cityTextView;
        public TextView totalRatingTextView;
        public TextView ratingCountTextView;
        public RatingBar placeRatingBar;
      //  public TextView promoAmtTextView;
        public CardView promoCardView;
        public CardView placeHolderCardView;

        public PlaceViewHolder(View view) {
            super(view);

            placeImageView = view.findViewById(R.id.placeImageView);
            placeNameTextView = view.findViewById(R.id.placeNameTextView);
            typeTextView = view.findViewById(R.id.distanceTextView);
            cityTextView = view.findViewById(R.id.cityTextView);
            totalRatingTextView = view.findViewById(R.id.totalRatingTextView);
            ratingCountTextView = view.findViewById(R.id.ratingCountTextView);
            placeRatingBar = view.findViewById(R.id.placeRatingBar);
         //   promoAmtTextView = view.findViewById(R.id.promoAmtTextView);
            promoCardView = view.findViewById(R.id.promoCardView);
            placeHolderCardView = view.findViewById(R.id.placeHolderCardView);
        }


    }

    public class AllocatedDriverViewHolder extends RecyclerView.ViewHolder {
        public ImageView placeImageView;
        public TextView placeNameTextView;
        public TextView typeTextView;
        public TextView cityTextView;
        public TextView totalRatingTextView;
        public TextView ratingCountTextView;
        public RatingBar placeRatingBar;
        //  public TextView promoAmtTextView;
        public CardView promoCardView;
        public CardView placeHolderCardView;

        public AllocatedDriverViewHolder(View view) {
            super(view);

            placeImageView = view.findViewById(R.id.placeImageView);
            placeNameTextView = view.findViewById(R.id.placeNameTextView);
            typeTextView = view.findViewById(R.id.distanceTextView);
            cityTextView = view.findViewById(R.id.cityTextView);
            totalRatingTextView = view.findViewById(R.id.totalRatingTextView);
            ratingCountTextView = view.findViewById(R.id.ratingCountTextView);
            placeRatingBar = view.findViewById(R.id.placeRatingBar);
            //   promoAmtTextView = view.findViewById(R.id.promoAmtTextView);
            promoCardView = view.findViewById(R.id.promoCardView);
            placeHolderCardView = view.findViewById(R.id.placeHolderCardView);
        }


    }
}
