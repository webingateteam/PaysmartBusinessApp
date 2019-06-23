package com.webingate.paysmartbusinessapp.adapter;

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
import com.webingate.paysmartbusinessapp.object.DirectoryHome9ProductsVO;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.util.List;

public class customerapp_ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DirectoryHome9ProductsVO> productsList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, DirectoryHome9ProductsVO product, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public customerapp_ProductsAdapter(List<DirectoryHome9ProductsVO> productsList) {
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerapp_dashboard_productsitem, parent, false);
        return new ProductsViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductsViewHolder) {

            if (position == 9) {

                ProductsViewHolder productsViewHolder = (ProductsViewHolder) holder;
                Context context = productsViewHolder.productImageView.getContext();

                productsViewHolder.productTextView.setText("All Products");

                int allProductImageId = R.drawable.home9_all_products;
                Utils.setCircleImageToImageView(context, productsViewHolder.productImageView, allProductImageId, 0, 0);

            } else {

                DirectoryHome9ProductsVO productsVO = productsList.get(position);
                ProductsViewHolder productsViewHolder = (ProductsViewHolder) holder;
                Context context = productsViewHolder.productImageView.getContext();

                productsViewHolder.productTextView.setText(productsVO.getName());

                int productImageId = Utils.getDrawableInt(context, productsVO.getIcon());
                Utils.setCircleImageToImageView(context, productsViewHolder.productImageView, productImageId, 0, 0);
            }

            if (itemClickListener != null) {
                ((ProductsViewHolder) holder).productConstraintLayout.setOnClickListener(view -> itemClickListener.onItemClick(view, productsList.get(position), position));
            }

        }
    }

    @Override
    public int getItemCount() {
       // return productsList.size();
        int a ;
        if(productsList != null && !productsList.isEmpty()) {
            a = productsList.size();
        }
        else {
            a = 0;
        }
        return a;
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout productConstraintLayout;
        ImageView productImageView;
        TextView productTextView;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            productConstraintLayout = itemView.findViewById(R.id.productConstraint);
            productImageView = itemView.findViewById(R.id.productImageView);
            productTextView = itemView.findViewById(R.id.productTextView);
        }
    }
}
