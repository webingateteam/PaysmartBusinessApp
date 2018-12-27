package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.driverapplication.ApplicationConstants;
import com.webingate.paysmartbusinessapp.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import cropper.CropImage;
import cropper.CropImageView;


//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppFleetOwnerInfoFragment extends Fragment {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Username = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String UserAccountNumber = "UserAccountNo";
    public static final String usertypeid = "usertypeid";
    public static final String gender = "GenderKey";
    public static final String Address = "AddressKey";
    private static final int GET_FROM_GALLERY = 0;


    @BindView(R.id.profileImageView) ImageView profileImageView;
    @BindView(R.id.imageView46) ImageView ppic;
    @BindView(R.id.s_name)
    EditText name;
    @BindView(R.id.s_email)
    EditText email;
    @BindView(R.id.s_mobileno)
    EditText mbno;
    @BindView(R.id.s_address)
    EditText address;
    @BindView(R.id.s_city)
    EditText city;
    @BindView(R.id.s_postal)
    EditText postal;
    @BindView(R.id.s_state)
    EditText state;
    Toast toast;
    String email1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.businessapp_newfleetowner_infofragment, container, false);

//        initData();
//
        initUI(view);
//
//        initDataBindings();
//
        initActions(view);
//
        return view;
    }
//
//    private void initData() {
//        productsList = DirectoryHome9Repository.getfleetownerList();
//        categoryList = DirectoryHome9Repository.getCategoryList();
//        promotionsList = DirectoryHome9Repository.getPromotionsList();
//        popularList = DirectoryHome9Repository.getPopularList();
//        flightsList = DirectoryHome9Repository.getFlightsList();
//    }
//
private void initActions(View view) {

    ppic.setOnClickListener((View v) -> {
        Toast.makeText(getActivity(), "Clicked on Pen of Profile", Toast.LENGTH_SHORT).show();
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setActivityTitle("My Crop")
                .setCropMenuCropButtonTitle("Done")
                .setRequestedSize(400, 800)
                .setCropMenuCropButtonIcon(R.drawable.badge_menu)
                .start(this.getActivity());
//            startActivity(new Intent(getActivity(), customerappTrainBookingSearchActivity.class));
        //startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    });
}
    private void initUI(View view) {


        if(ApplicationConstants.upic!=null)
        {
            profileImageView = view.findViewById(R.id.profileImageView);
            byte[] decodedString= Base64.decode( ApplicationConstants.upic.substring( ApplicationConstants.upic.indexOf(",")+1), Base64.DEFAULT);
            Bitmap image1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profileImageView.setImageBitmap(image1);
        }
        else
        {
        profileImageView = view.findViewById(R.id.profileImageView);
        int id = R.drawable.profile2;
        Utils.setCornerRadiusImageToImageView(view.getContext(), profileImageView, id, 20, 2, R.color.md_white_1000);
        }

        setname((EditText) view.findViewById(R.id.s_name));
        name = view.findViewById(R.id.s_name);
        email = view.findViewById(R.id.s_email);
        mbno = view.findViewById(R.id.s_mobileno);
        address = view.findViewById(R.id.s_address);
        city = view.findViewById(R.id.s_city);
        postal = view.findViewById(R.id.s_postal);
        state = view.findViewById(R.id.s_state);
        name.setText(ApplicationConstants.username);
        email.setText(ApplicationConstants.email);
        mbno.setText(ApplicationConstants.mobileNo);
        address.setText(ApplicationConstants.address);
        city.setText(ApplicationConstants.gender);
        ppic=view.findViewById(R.id.imageView46);
    }

    public EditText getName() {
        return name;
    }

    public void setname(EditText name) {
        this.name = name;
    }
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //Detects request codes
//
//        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
//            Uri selectedImage = data.getData();
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
//
//                Toast.makeText(getActivity(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
////                missing.setText("Upload");
////                missing.setTextColor(ctx.getResources().getColor(R.color.md_yellow_500));
////                status.setColorFilter(ctx.getResources().getColor(R.color.md_yellow_500));
////                Doc.setImageBitmap(bitmap);
//
//                // for coverting the file to send ///
//                profileImageView.setImageBitmap(bitmap);
//                Uri uri = data.getData();
//                ApplicationConstants.document_format = getActivity().getContentResolver().getType(uri);
//
////                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
////                BufferedReader reader = new BufferedReader(new InputStreamReader(
////                        inputStream));
////                StringBuilder stringBuilder = new StringBuilder();
////                String line;
////                while ((line = reader.readLine()) != null) {
////                    stringBuilder.append(line);
////                }
////                inputStream.close();
////                String encodedImage = Base64.encodeToString(stringBuilder.toString().getBytes(), Base64.DEFAULT);
//
//
//
//
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                byte[] imageBytes = baos.toByteArray();
//                ApplicationConstants.picdata = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//                //ApplicationConstants.document_data = encodedImage;
//                //ApplicationConstants.picdata=encodedImage;
//                // email.setText(encodedImage);
//
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

}
