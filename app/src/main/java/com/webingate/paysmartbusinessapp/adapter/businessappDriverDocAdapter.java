package com.webingate.paysmartbusinessapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDocumentsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.model.BookingType;
import com.webingate.paysmartbusinessapp.utils.Utils;
import com.webingate.paysmartbusinessapp.utils.ViewAnimationUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


import static com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants.*;
import static com.webingate.paysmartbusinessapp.fragment.businessAppFragments.businessAppUploadDocsFragment.GET_FROM_GALLERY;
import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Panacea-Soft on 17/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class businessappDriverDocAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BookingType> placeArrayList = new ArrayList<>();
//    private ArrayList<BookingType> placeArrayList;
    private OnItemClickListener itemClickListener;

    private ImageView sizeUpDownImageView;
    Calendar dateTime = Calendar.getInstance();
    private View sizeLayout;
    private View materialLayout;
    @BindView(R.id.Upload)
    Button Uploading;
    //@BindView(R.id.Updoc)  ImageView Doc;
    @BindView(R.id.status)  ImageView status;
    @BindView(R.id.expdate)  Button expdate;
    @BindView(R.id.dateofex)  TextView dateofex;
    @BindView(R.id.submit)  Button submit;
    @BindView(R.id.missing)  TextView missing;
    @BindView(R.id.sizeTitleValueTextView)  TextView sizeTitleValueTextView;
    @BindView(R.id.statusdoc) TextView sts;
    @BindView(R.id.sizeTitleTextView) TextView sizeTitleTextView;

    private Context ctx;
    private businessappDriverDocAdapter.OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(View view, BookingType obj, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }
    public businessappDriverDocAdapter(Context context,List<BookingType> placeArrayList) {
        this.placeArrayList = placeArrayList;
        ctx = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_expandable_ecommerce_expandable_1_activity, parent, false);
      //  View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.driverdoclistitms, parent, false);
        return new PlaceViewHolder(itemView);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof PlaceViewHolder) {
            BookingType place = placeArrayList.get(position);
            PlaceViewHolder holder = (PlaceViewHolder) viewHolder;

            Context context = holder.placeHolderCardView.getContext();
            Drawable selectedList = context.getResources().getDrawable(R.drawable.baseline_selected_list_24);
            selectedList.setColorFilter(context.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
            Uploading = ((PlaceViewHolder) viewHolder).Uploading.findViewById(R.id.Upload);
            sizeUpDownImageView = ((PlaceViewHolder) viewHolder).sizeUpDownImageView.findViewById(R.id.sizeUpDownImageView);
           // submit = ((PlaceViewHolder) viewHolder).submit.findViewById(R.id.submit);
            //expdate=((PlaceViewHolder) viewHolder).expdate.findViewById(R.id.expdate);
//            dateofex=((PlaceViewHolder) viewHolder).dateofex.findViewById(R.id.dateofex);
//            expdate=((PlaceViewHolder) viewHolder).expdate.findViewById(R.id.expdate);
//            expdate=((PlaceViewHolder) viewHolder).expdate.findViewById(R.id.expdate);
//            Doc=((PlaceViewHolder) viewHolder).Doc.findViewById(R.id.Updoc);
            sizeTitleTextView=((PlaceViewHolder) viewHolder).sizeTitleTextView.findViewById(R.id.sizeTitleTextView);
            sizeTitleValueTextView=((PlaceViewHolder) viewHolder).sizeTitleValueTextView.findViewById(R.id.sizeTitleValueTextView);
            sizeLayout=((PlaceViewHolder) viewHolder).sizeLayout.findViewById(R.id.sizeLayout);
            sizeLayout.setVisibility(View.GONE);
            holder.sizeTitleTextView.setText(place.name);


//            // int id = Utils.getDrawableInt(context, place.getPhoto());
//            if(place.getUserPhoto()!=null){
//                byte[] decodedString= Base64.decode(place.getUserPhoto().substring(place.getUserPhoto().indexOf(",")+1), Base64.DEFAULT);
//                Bitmap image1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                holder.placeImageView.setImageBitmap(image1);
//            }
//            else{
//            int id = Utils.getDrawableInt(context, "photo_male_7");
//            Utils.setImageToImageView(context, holder.placeImageView, id);
//            }
//            holder.typeTextView.setText("12");
//            holder.cityTextView.setText("");
//            holder.placeRatingBar.setRating(Float.parseFloat("4"));
//            holder.totalRatingTextView.setText("4");
//            holder.ratingCountTextView.setText("4");

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
//            sizeUpDownImageView.setOnClickListener((View v) -> {
//                boolean show = Utils.toggleUpDownWithAnimation(v);
//                if (show) {
//                    ViewAnimationUtils.expand(sizeLayout);
//                } else {
//                    ViewAnimationUtils.collapse(sizeLayout);
//                }
//            });


//            Uploading.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    browsePhoto(view);
//                }
//            });
//            expdate.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    openDatePicker(view);
//                }
//            });
//            submit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    JsonObject object = new JsonObject();
//                    object.addProperty("Id", "");
//                    object.addProperty("DriverId",1003 );
//                    object.addProperty("FileName", document_format);
//                    object.addProperty("DocTypeId", 86);
//                    object.addProperty("ExpiryDate", document_expire_date);
//                    object.addProperty("UpdatedById", 1);
//                    object.addProperty("DueDate", document_expire_date);
//                    object.addProperty("FileContent", "data:" + document_format + ";base64," + document_data);
//                    object.addProperty("change", "I");
//                    object.addProperty("loggedinUserId", 1);
//                    object.addProperty("DocumentNo", document_number);
//                    //UploadDocument(object);
//                }
//            });
            sizeUpDownImageView.setOnClickListener((View v) -> {
                boolean show = Utils.toggleUpDownWithAnimation(v);
                if (show) {
                    ViewAnimationUtils.expand(sizeLayout);
                } else {
                    ViewAnimationUtils.collapse(sizeLayout);
                }
            });
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
        public CardView placeHolderCardView;
        //public ImageView Doc;
        public ImageView status;
        public TextView missing;
//        public TextView dateofex;
        public TextView sts;
        public TextView sizeTitleTextView;
        public TextView sizeTitleValueTextView;
//        public Button expdate;
//        public Button submit;
        public Button Uploading;
        public ImageView sizeUpDownImageView;
        Calendar dateTime = Calendar.getInstance();
        private View sizeLayout;

        public PlaceViewHolder(View view) {
            super(view);
            placeHolderCardView=view.findViewById(R.id.sizeCardView);
            Uploading = view.findViewById(R.id.Upload);
            //Doc=view.findViewById(R.id.Updoc);
            sizeTitleTextView=view.findViewById(R.id.sizeTitleTextView);
            sizeUpDownImageView = view.findViewById(R.id.sizeUpDownImageView);
//            expdate=view.findViewById(R.id.expdate);
//            dateofex=view.findViewById(R.id.dateofex);
//            submit=view.findViewById(R.id.submit);
            missing=view.findViewById(R.id.missing);
            status=view.findViewById(R.id.status);
            sizeTitleValueTextView=view.findViewById(R.id.sizeTitleValueTextView);
            sizeLayout = view.findViewById(R.id.sizeLayout);
            sizeLayout.setVisibility(View.GONE);

        }
    }


//    private void browsePhoto(View view) {
//        ((Activity)view.getContext()).startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
//    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//      super.onActivityResult(requestCode, resultCode, data);
//        //Detects request codes
//
//        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
//            Uri selectedImage = data.getData();
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(ctx.getContentResolver(), selectedImage);
//                Toast.makeText(ctx, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
//                missing.setText("Upload");
//                missing.setTextColor(ctx.getResources().getColor(R.color.md_yellow_500));
//                status.setColorFilter(ctx.getResources().getColor(R.color.md_yellow_500));
//                Doc.setImageBitmap(bitmap);
//
//                // for coverting the file to send ///
//                Uri uri = data.getData();
//                ApplicationConstants.document_format = ctx.getContentResolver().getType(uri);
//
//                InputStream inputStream = ctx.getContentResolver().openInputStream(uri);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(
//                        inputStream));
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    stringBuilder.append(line);
//                }
//                inputStream.close();
//                String encodedImage = Base64.encodeToString(stringBuilder.toString().getBytes(), Base64.DEFAULT);
//                ApplicationConstants.document_data = encodedImage;
////
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
    public void UploadDocument(JsonObject jsonObject){

        // StartDialogue();
        com.webingate.paysmartbusinessapp.driverapplication.Utils.DataPrepare.get(ctx).getrestadapter()
                .SaveDriverDocuments1(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DriverDocumentsResponse>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(ctx, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        //DisplayToast("Successfully Registered");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getMessage());
                            //DisplayToast("Unable to Register");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<DriverDocumentsResponse> response) {
                        DriverDocumentsResponse rr =response.get(0);
                        Toast.makeText(ctx, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        if(rr.getCode()!=null){
                            Toast.makeText(ctx, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ctx, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    DatePickerDialog.OnDateSetListener datePickerDialog = (view, year, monthOfYear, dayOfMonth) -> {
        dateTime.set(Calendar.YEAR, year);
        dateTime.set(Calendar.MONTH, monthOfYear);
        dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateDate();
    };

    private void openDatePicker(View view){
        new DatePickerDialog(view.getContext(), datePickerDialog, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String shortTimeStr = sdf.format(dateTime.getTime());
        //ApplicationConstants.date = shortTimeStr;
        document_expire_date=shortTimeStr;
        document_expire_date=shortTimeStr;
//        dateofex.setText(document_expire_date);
        sizeTitleValueTextView.setText(document_expire_date);
    }
}
