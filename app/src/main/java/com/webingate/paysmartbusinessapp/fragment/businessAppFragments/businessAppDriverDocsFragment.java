package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.paysmartbusinessapp.R;

import com.webingate.paysmartbusinessapp.utils.RangeSeekBar;
import com.webingate.paysmartbusinessapp.utils.Utils;
import com.webingate.paysmartbusinessapp.utils.ViewAnimationUtils;

import java.util.List;

import butterknife.BindView;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class businessAppDriverDocsFragment extends Fragment {

    @BindView(R.id.priceRangeUpDownImageView)
    ImageView priceupdown;

    @BindView(R.id.colorUpDownImageView)
    ImageView colorUpDown;

    @BindView(R.id.sizeUpDownImageView)
    ImageView sizeUpDown;

    @BindView(R.id.materialUpDownImageView)
    ImageView materialUpDown;

    @BindView(R.id.priceRangeLayout)
    ImageView priceRangeLayout;

    @BindView(R.id.colorLayout)
    ImageView colorLayout;

    @BindView(R.id.sizeLayout)
    ImageView sizeLayout;

    @BindView(R.id.materialLayout)
    ImageView materialLayout;

    @BindView(R.id.priceRangeFromTitleValueTextView)
    ImageView priceRangeFromTitleValueTextView;

    @BindView(R.id.priceRangeToTitleValueTextView)
    ImageView priceRangeToTitleValueTextView;

    @BindView(R.id.priceRangeFromValueTextView)
    ImageView priceRangeFromValueTextView;

    @BindView(R.id.priceRangeToValueTextView)
    ImageView priceRangeToValueTextView;

    private RangeSeekBar seekBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.businessapp_newdriverdocs_fragment, container, false);

        initData();

        //initUI();

        initDataBindings();

        initActions();

        return view;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

    }

//    private void initUI() {
//
//        // Init Toolbar
//        //initToolbar();
//
//        priceupdown = (ImageView) getActivity().findViewById(R.id.priceRangeUpDownImageView);
//        colorUpDown = (ImageView) getActivity().findViewById(R.id.colorUpDownImageView);
//        sizeUpDown = (ImageView) getActivity().findViewById(R.id.sizeUpDownImageView);
//        materialUpDown = (ImageView) getActivity().findViewById(R.id.materialUpDownImageView);
//
//        priceRangeLayout = (ImageView)getActivity().findViewById(R.id.priceRangeLayout);
//        colorLayout = (ImageView) getActivity().findViewById(R.id.colorLayout);
//        sizeLayout = (ImageView) getActivity().findViewById(R.id.sizeLayout);
//        materialLayout = (ImageView) getActivity().findViewById(R.id.materialLayout);
//
//        priceRangeLayout.setVisibility(View.GONE);
//        colorLayout.setVisibility(View.GONE);
//        sizeLayout.setVisibility(View.GONE);
//        materialLayout.setVisibility(View.GONE);
//
//
//        priceRangeFromTitleValueTextView = (ImageView)getActivity().findViewById(R.id.priceRangeFromTitleValueTextView);
//        priceRangeToTitleValueTextView = (ImageView)getActivity().findViewById(R.id.priceRangeToTitleValueTextView);
//        priceRangeFromValueTextView = (ImageView)getActivity().findViewById(R.id.priceRangeFromValueTextView);
//        priceRangeToValueTextView = (ImageView)getActivity().findViewById(R.id.priceRangeToValueTextView);
//
//
//        int maxValue2 = 100;
//        //seekBar = new RangeSeekBar <>(0, maxValue2, this);
//
//        seekBar.setSelectedMinValue(0);
//        seekBar.setAbsoluteMaxValue(100);
//
//        LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(R.id.price_range_bar_container);
//        linearLayout.addView(seekBar);
////
////        ImageView color1BgImageView = findViewById(R.id.color1BgImageView);
////        ImageView color2BgImageView = findViewById(R.id.color2BgImageView);
////        ImageView color3BgImageView = findViewById(R.id.color3BgImageView);
////        ImageView color4BgImageView = findViewById(R.id.color4BgImageView);
////        ImageView color5BgImageView = findViewById(R.id.color5BgImageView);
////        ImageView color6BgImageView = findViewById(R.id.color6BgImageView);
////        ImageView color7BgImageView = findViewById(R.id.color7BgImageView);
////
////        setDefaultCircleImage(color1BgImageView, R.color.md_white_1000);
////        setDefaultCircleImage(color2BgImageView, R.color.md_grey_400);
////        setDefaultCircleImage(color3BgImageView, R.color.md_yellow_400);
////        setDefaultCircleImage(color4BgImageView, R.color.md_green_500);
////        setDefaultCircleImage(color5BgImageView, R.color.md_green_900);
////        setDefaultCircleImage(color6BgImageView, R.color.md_blue_500);
////        setDefaultCircleImage(color7BgImageView, R.color.md_black_1000);
////
////        color1ImageView = findViewById(R.id.color1ImageView);
////        color2ImageView = findViewById(R.id.color2ImageView);
////        color3ImageView = findViewById(R.id.color3ImageView);
////        color4ImageView = findViewById(R.id.color4ImageView);
////        color5ImageView = findViewById(R.id.color5ImageView);
////        color6ImageView = findViewById(R.id.color6ImageView);
////        color7ImageView = findViewById(R.id.color7ImageView);
////
//        Drawable selectedList = getContext().getResources().getDrawable(R.drawable.baseline_selected_list_24);
//        selectedList.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
////
////        size1BgImageView = findViewById(R.id.size1BgImageView);
////        size2BgImageView = findViewById(R.id.size2BgImageView);
////        size3BgImageView = findViewById(R.id.size3BgImageView);
////        size4BgImageView = findViewById(R.id.size4BgImageView);
////        size5BgImageView = findViewById(R.id.size5BgImageView);
////
////        setDefaultCircleImage(size1BgImageView, R.color.md_grey_400);
////        setDefaultCircleImage(size2BgImageView, R.color.md_grey_400);
////        setDefaultCircleImage(size3BgImageView, R.color.md_grey_400);
////        setDefaultCircleImage(size4BgImageView, R.color.md_grey_400);
////        setDefaultCircleImage(size5BgImageView, R.color.md_grey_400);
////
////        size1TextView = findViewById(R.id.size1TextView);
////        size2TextView = findViewById(R.id.size2TextView);
////        size3TextView = findViewById(R.id.size3TextView);
////        size4TextView = findViewById(R.id.size4TextView);
////        size5TextView = findViewById(R.id.size5TextView);
////
////        sizeTitleValueTextView = findViewById(R.id.sizeTitleValueTextView);
////        colorTitleValueTextView = findViewById(R.id.colorTitleValueTextView);
//
//
//        // Set Color Default
//        //color1ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
//        color1Status = true;
//        updateColorTitle();
//
//        // Set Size Default
//        //setSelectUnSelectSizeFilter(size3BgImageView, R.color.colorPrimary, size3TextView, R.color.md_white_1000);
//        size3Status = true;
//        updateSizeTitle();
//
//        // Set Material Default
//        Button material1Button = (Button)getActivity().findViewById(R.id.material1Button);
//        material1Button.setSelected(true);
//
//    }

    private void setDefaultCircleImage(ImageView imageView, int color) {
        Utils.setCircleImageToImageView(getContext(), imageView, R.drawable.white_background, 0, 0);
        imageView.setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_IN);
    }

    private void setSelectUnSelectSizeFilter(ImageView imageView, int bgColor, TextView textView, int color) {
        imageView.setColorFilter(getResources().getColor(bgColor), PorterDuff.Mode.SRC_IN);
        textView.setTextColor(getResources().getColor(color));
    }

    private Boolean size1Status = false;
    private Boolean size2Status = false;
    private Boolean size3Status = false;
    private Boolean size4Status = false;
    private Boolean size5Status = false;

    private Boolean color1Status = true;
    private Boolean color2Status = false;
    private Boolean color3Status = false;
    private Boolean color4Status = false;
    private Boolean color5Status = false;
    private Boolean color6Status = false;
    private Boolean color7Status = false;

    private void initDataBindings() {

    }

    private void initActions() {

        seekBar.setOnRangeSeekBarChangeListener((bar, minValue, maxValue) -> {

            Log.d("TEAMPS", "initUI: " + minValue + " : " + maxValue);

            String minStr = "$ " + minValue;
            String maxStr = "$ " + maxValue;
            priceRangeFromTitleValueTextView.setTag(minStr);
            priceRangeFromValueTextView.setTag(minStr);
            priceRangeToTitleValueTextView.setTag(maxStr);
            priceRangeToValueTextView.setTag(maxStr);

        });


        priceupdown.setOnClickListener((View v) -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtils.expand(priceRangeLayout);
            } else {
                ViewAnimationUtils.collapse(priceRangeLayout);
            }
        });

        sizeUpDown.setOnClickListener((View v) -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtils.expand(sizeLayout);
            } else {
                ViewAnimationUtils.collapse(sizeLayout);
            }
        });

        colorUpDown.setOnClickListener((View v) -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtils.expand(colorLayout);
            } else {
                ViewAnimationUtils.collapse(colorLayout);
            }
        });

        materialUpDown.setOnClickListener((View v) -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtils.expand(materialLayout);
            } else {
               ViewAnimationUtils.collapse(materialLayout);
            }
        });


//        //region Size
//        size1TextView.setOnClickListener((View v) -> {
//            if (size1Status) {
//                setSelectUnSelectSizeFilter(size1BgImageView, R.color.md_grey_400, size1TextView, R.color.md_grey_800);
//                size1Status = false;
//            } else {
//                setSelectUnSelectSizeFilter(size1BgImageView, R.color.colorPrimary, size1TextView, R.color.md_white_1000);
//                size1Status = true;
//            }
//            updateSizeTitle();
//        });
//
//        size2TextView.setOnClickListener((View v) -> {
//            if (size2Status) {
//                setSelectUnSelectSizeFilter(size2BgImageView, R.color.md_grey_400, size2TextView, R.color.md_grey_800);
//                size2Status = false;
//            } else {
//                setSelectUnSelectSizeFilter(size2BgImageView, R.color.colorPrimary, size2TextView, R.color.md_white_1000);
//                size2Status = true;
//            }
//            updateSizeTitle();
//        });
//
//        size3TextView.setOnClickListener((View v) -> {
//            if (size3Status) {
//                setSelectUnSelectSizeFilter(size3BgImageView, R.color.md_grey_400, size3TextView, R.color.md_grey_800);
//                size3Status = false;
//            } else {
//                setSelectUnSelectSizeFilter(size3BgImageView, R.color.colorPrimary, size3TextView, R.color.md_white_1000);
//                size3Status = true;
//            }
//            updateSizeTitle();
//        });
//
//        size4TextView.setOnClickListener((View v) -> {
//            if (size4Status) {
//                setSelectUnSelectSizeFilter(size4BgImageView, R.color.md_grey_400, size4TextView, R.color.md_grey_800);
//                size4Status = false;
//            } else {
//                setSelectUnSelectSizeFilter(size4BgImageView, R.color.colorPrimary, size4TextView, R.color.md_white_1000);
//                size4Status = true;
//            }
//            updateSizeTitle();
//        });
//
//        size5TextView.setOnClickListener((View v) -> {
//            if (size5Status) {
//                setSelectUnSelectSizeFilter(size5BgImageView, R.color.md_grey_400, size5TextView, R.color.md_grey_800);
//                size5Status = false;
//            } else {
//                setSelectUnSelectSizeFilter(size5BgImageView, R.color.colorPrimary, size5TextView, R.color.md_white_1000);
//                size5Status = true;
//            }
//            updateSizeTitle();
//        });
//
//        //endregion
//
//        //region Color
//
//        color1ImageView.setOnClickListener((View v) -> {
//            if (color1Status) {
//                color1ImageView.setImageDrawable(null);
//                color1Status = false;
//            } else {
//                color1ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
//                color1Status = true;
//            }
//            updateColorTitle();
//        });
//
//        color2ImageView.setOnClickListener((View v) -> {
//            if (color2Status) {
//                color2ImageView.setImageDrawable(null);
//                color2Status = false;
//            } else {
//                color2ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
//                color2Status = true;
//            }
//            updateColorTitle();
//        });
//
//        color3ImageView.setOnClickListener((View v) -> {
//            if (color3Status) {
//                color3ImageView.setImageDrawable(null);
//                color3Status = false;
//            } else {
//                color3ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
//                color3Status = true;
//            }
//            updateColorTitle();
//        });
//
//        color4ImageView.setOnClickListener((View v) -> {
//            if (color4Status) {
//                color4ImageView.setImageDrawable(null);
//                color4Status = false;
//            } else {
//                color4ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
//                color4Status = true;
//            }
//            updateColorTitle();
//        });
//
//        color5ImageView.setOnClickListener((View v) -> {
//            if (color5Status) {
//                color5ImageView.setImageDrawable(null);
//                color5Status = false;
//            } else {
//                color5ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
//                color5Status = true;
//            }
//            updateColorTitle();
//        });
//
//        color6ImageView.setOnClickListener((View v) -> {
//            if (color6Status) {
//                color6ImageView.setImageDrawable(null);
//                color6Status = false;
//            } else {
//                color6ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
//                color6Status = true;
//            }
//            updateColorTitle();
//        });
//
//        color7ImageView.setOnClickListener((View v) -> {
//            if (color7Status) {
//                color7ImageView.setImageDrawable(null);
//                color7Status = false;
//            } else {
//                color7ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
//                color7Status = true;
//            }
//            updateColorTitle();
//        });
//
//        //endregion


    }

    private void updateColorTitle() {

        int value = 0;

        if (color1Status) {
            value++;
        }

        if (color2Status) {
            value++;
        }

        if (color3Status) {
            value++;
        }

        if (color4Status) {
            value++;
        }

        if (color5Status) {
            value++;
        }

        if (color6Status) {
            value++;
        }

        if (color7Status) {
            value++;
        }

        String result;
        if (value == 0) {
            result = "Not Set.";
        } else if (value == 1) {
            result = value + " Color";
        } else {
            result = value + " Colors";
        }

        //colorTitleValueTextView.setText(result);

    }

    private void updateSizeTitle() {
        String value = "";

        if (size1Status) {
            //value = size1TextView.getText().toString();
        }

        if (size2Status) {
            if (value.equals("")) {
                //value += size2TextView.getText().toString();
            } else {
                //value += ", " + size2TextView.getText().toString();
            }
        }

        if (size3Status) {
            if (value.equals("")) {
                //value += size3TextView.getText().toString();
            } else {
                //value += ", " + size3TextView.getText().toString();
            }
        }

        if (size4Status) {
            if (value.equals("")) {
                //value += size4TextView.getText().toString();
            } else {
                //value += ", " + size4TextView.getText().toString();
            }
        }

        if (size5Status) {
            if (value.equals("")) {
                //value += size5TextView.getText().toString();
            } else {
                //value += ", " + size5TextView.getText().toString();
            }
        }

        if (value.equals("")) {
            value = "Not Set.";
        }
        //sizeTitleValueTextView.setText(value);
    }

    public void clickMaterial(View view) {
        if (view != null && view instanceof Button) {
            view.setSelected(!view.isSelected());
        }
    }



}
