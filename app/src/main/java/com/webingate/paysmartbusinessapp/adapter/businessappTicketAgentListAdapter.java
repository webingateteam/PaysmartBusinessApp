package com.webingate.paysmartbusinessapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.object.Place;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Panacea-Soft on 17/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class businessappTicketAgentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<DrivermasterResponse> placeArrayList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, DrivermasterResponse obj, int position);
    }

    public void setOnItemClickListener(final  OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public businessappTicketAgentListAdapter(ArrayList<DrivermasterResponse> placeArrayList) {
        this.placeArrayList = placeArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.businessapp_ticketagentlist_item, parent, false);

        return new TicetAgentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof TicetAgentViewHolder) {

            DrivermasterResponse place = placeArrayList.get(position);
            businessappTicketAgentListAdapter.PlaceViewHolder holder = (businessappTicketAgentListAdapter.PlaceViewHolder) viewHolder;
            holder.placeNameTextView.setText(place.getNAme());
            Context context = holder.placeHolderCardView.getContext();
            // int id = Utils.getDrawableInt(context, place.getPhoto());
            if(place.getUserPhoto()!=null){
                byte[] decodedString= Base64.decode(place.getUserPhoto().substring(place.getUserPhoto().indexOf(",")+1), Base64.DEFAULT);
                Bitmap image1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                holder.placeImageView.setImageBitmap(image1);
            }
            else{
//            int id = Utils.getDrawableInt(context, "user_round_button");
                int id = Utils.getDrawableInt(context, "profile2");
                Utils.setImageToImageView(context, holder.placeImageView, id);
            }

            holder.typeTextView.setText("12");
            holder.cityTextView.setText("");
            holder.placeRatingBar.setRating(Float.parseFloat("4"));
            holder.totalRatingTextView.setText("4");
            holder.ratingCountTextView.setText("4");

//            DriverViewHolder holder = (DriverViewHolder) viewHolder;
//
//            holder.placeNameTextView.setText(place.name);
//
//            Context context = holder.placeHolderCardView.getContext();
//
//            int id = Utils.getDrawableInt(context, place.imageName);
//            Utils.setImageToImageView(context, holder.placeImageView, id);
//
//            holder.typeTextView.setText(place.type);
//            holder.cityTextView.setText(place.city);
//            holder.placeRatingBar.setRating(Float.parseFloat(place.totalRating));
//            holder.totalRatingTextView.setText(place.totalRating);
//            holder.ratingCountTextView.setText(place.ratingCount);

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
    public class TicetAgentViewHolder extends RecyclerView.ViewHolder {
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

        public TicetAgentViewHolder(View view) {
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
