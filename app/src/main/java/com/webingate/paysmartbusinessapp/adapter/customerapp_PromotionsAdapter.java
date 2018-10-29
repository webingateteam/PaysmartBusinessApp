package com.webingate.paysmartbusinessapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.object.DirectoryHome9PromotionsVO;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

public class customerapp_PromotionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DirectoryHome9PromotionsVO> promotionsList;

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, DirectoryHome9PromotionsVO promotion, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public customerapp_PromotionsAdapter(List<DirectoryHome9PromotionsVO> promotionsList) {
        this.promotionsList = promotionsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View prmotionsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cusotmerapp_dashboard_promotionsfirstitem, parent, false);
            return new PromotionsViewHolder(prmotionsView);
        }

        View prmotionsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerapp_dashboard_promotionsitem, parent, false);
        return new PromotionsViewHolder(prmotionsView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PromotionsViewHolder) {

            if (position == 0) {

                PromotionsViewHolder promotionsViewHolder = (PromotionsViewHolder) holder;
                Context context = promotionsViewHolder.promotionsImageView.getContext();

                int promotionsImageId = R.drawable.home9_promo_cell;
                Utils.setImageToImageView(context, promotionsViewHolder.promotionsImageView, promotionsImageId);

                if (itemClickListener != null) {
                    ((PromotionsViewHolder) holder).promotionFirstConstraint.setOnClickListener(view -> itemClickListener.onItemClick(view, promotionsList.get(position), position));
                }

            } else {

                DirectoryHome9PromotionsVO PromotionsVO = promotionsList.get(position);
                PromotionsViewHolder promotionsViewHolder = (PromotionsViewHolder) holder;
                Context context = promotionsViewHolder.promotionsImageView.getContext();

                int promotionsImageId = Utils.getDrawableInt(context, PromotionsVO.getImage());
                Utils.setImageToImageView(context, promotionsViewHolder.promotionsImageView, promotionsImageId);

                if (itemClickListener != null) {
                    ((PromotionsViewHolder) holder).promotionConstraint.setOnClickListener(view -> itemClickListener.onItemClick(view, promotionsList.get(position), position));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return promotionsList.size();
    }

    public class PromotionsViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout promotionFirstConstraint;
        ConstraintLayout promotionConstraint;
        ImageView promotionsImageView;

        public PromotionsViewHolder(View itemView) {
            super(itemView);
            promotionFirstConstraint = itemView.findViewById(R.id.promotionFirstItemConstraint);
            promotionConstraint = itemView.findViewById(R.id.promotionConstraint);
            promotionsImageView = itemView.findViewById(R.id.promotionsImageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
