package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.object.News;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

/**
 * Created by Panacea-Soft on 8/9/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class customerappTTicketTimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<News> newsList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, News obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public customerappTTicketTimelineAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerapp_timelineitem, parent, false);
        return new GeneralTimelineViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof GeneralTimelineViewHolder) {
            News news = newsList.get(position);

            GeneralTimelineViewHolder holder = (GeneralTimelineViewHolder) viewHolder;
            holder.dateTextView.setText(news.date);
            holder.newsTitleTextView.setText(news.title);
            holder.agoTextView.setText(news.ago);

            Context context = holder.iconImageView.getContext();

            if (context != null) {
                int id = Utils.getDrawableInt(context, news.newsImage);
                Utils.setCornerRadiusImageToImageView(context, holder.iconImageView, id, 50, 0, 0);
            }

            if (itemClickListener != null) {
                holder.constraintLayout.setOnClickListener((View v) -> itemClickListener.onItemClick(v, newsList.get(position), position));
            }
        }
    }


    @Override
    public int getItemCount() {

        return newsList.size();

    }

    public class GeneralTimelineViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView;
        TextView newsTitleTextView;
        TextView agoTextView;
        ConstraintLayout constraintLayout;
        ImageView iconImageView;


        GeneralTimelineViewHolder(View view) {
            super(view);

            dateTextView = view.findViewById(R.id.dateTextView);
            newsTitleTextView = view.findViewById(R.id.newsTitleTextView);
            agoTextView = view.findViewById(R.id.agoTextView);
            constraintLayout = view.findViewById(R.id.constraintLayout);
            iconImageView = view.findViewById(R.id.iconImageView);

        }
    }
}

