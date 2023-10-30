package com.webingate.paysmartbusinessapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.object.GeneralList;
import com.webingate.paysmartbusinessapp.utils.Utils;
import com.webingate.paysmartbusinessapp.utils.drag_and_swipe.ItemTouchDragHelperAdapter;
import com.webingate.paysmartbusinessapp.utils.drag_and_swipe.OnStartDragListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by Panacea-Soft on 7/17/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class customerapp_AdapterDragAndDropList extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchDragHelperAdapter {

    private List<GeneralList> generalListList;
    private OnItemClickListener itemClickListener;
    private final OnStartDragListener mDragStartListener;

    public interface OnItemClickListener {
        void onItemClick(View view, GeneralList obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public customerapp_AdapterDragAndDropList(List<GeneralList> generalListList, OnStartDragListener mDragStartListener) {
        this.generalListList = generalListList;
        this.mDragStartListener = mDragStartListener;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(generalListList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerapp_draganddroplist_item, parent, false);
        return new GeneralListViewHolder(itemView);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof GeneralListViewHolder) {
            GeneralList financeTransaction = generalListList.get(position);

            GeneralListViewHolder holder = (GeneralListViewHolder) viewHolder;
            holder.dragImageView.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            });

            Context context = holder.rowImageView.getContext();
            if (context != null) {
                int id = Utils.getDrawableInt(context, financeTransaction.image);
                Utils.setImageToImageView(context, holder.rowImageView, id);
            }

            holder.titleTextView.setText(financeTransaction.name);
            holder.captionTextView.setText(financeTransaction.caption);

        }
    }


    @Override
    public int getItemCount() {

        return generalListList.size();

    }

    public class GeneralListViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView captionTextView;
        ImageView dragImageView, rowImageView;

        GeneralListViewHolder(View view) {
            super(view);

            titleTextView = view.findViewById(R.id.titleTextView);
            captionTextView = view.findViewById(R.id.captionTextView);
            dragImageView = view.findViewById(R.id.dragImageView);
            rowImageView = view.findViewById(R.id.rowImageView);

        }
    }
}
