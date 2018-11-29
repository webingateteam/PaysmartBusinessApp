package com.webingate.paysmartbusinessapp.activity.businessapp;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webingate.paysmartbusinessapp.R;
import com.webingate.paysmartbusinessapp.utils.RangeSeekBar;
import com.webingate.paysmartbusinessapp.utils.Utils;
import com.webingate.paysmartbusinessapp.utils.ViewAnimationUtils;

public class businessappDriverDocList extends AppCompatActivity {

    private ImageView priceRangeUpDownImageView;
    private ImageView colorUpDownImageView;
    private ImageView sizeUpDownImageView;
    private ImageView materialUpDownImageView;

    private View priceRangeLayout;
    private View colorLayout;
    private View sizeLayout;
    private View materialLayout;

    private ImageView size1BgImageView;
    private ImageView size2BgImageView;
    private ImageView size3BgImageView;
    private ImageView size4BgImageView;
    private ImageView size5BgImageView;

    private TextView size1TextView;
    private TextView size2TextView;
   // private TextView size3TextView;
    private TextView size4TextView;
    private TextView size5TextView;

    private ImageView color1ImageView;
    private ImageView color2ImageView;
    private ImageView color3ImageView;
    private ImageView color4ImageView;
    private ImageView color5ImageView;
    private ImageView color6ImageView;
    private ImageView color7ImageView;

    private TextView sizeTitleValueTextView;
    private TextView colorTitleValueTextView;
    private TextView priceRangeFromTitleValueTextView;
    private TextView priceRangeToTitleValueTextView;
    private TextView priceRangeFromValueTextView;
    private TextView priceRangeToValueTextView;

    private RangeSeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_expandable_ecommerce_expandable_1_activity);

        initData();

        initUI();

        initDataBinding();

        initActions();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

    }

    private void initUI() {

        // Init Toolbar
        initToolbar();

        priceRangeUpDownImageView = findViewById(R.id.priceRangeUpDownImageView);
        colorUpDownImageView = findViewById(R.id.colorUpDownImageView);
        sizeUpDownImageView = findViewById(R.id.sizeUpDownImageView);
        materialUpDownImageView = findViewById(R.id.materialUpDownImageView);

        priceRangeLayout = findViewById(R.id.priceRangeLayout);
        colorLayout = findViewById(R.id.colorLayout);
        sizeLayout = findViewById(R.id.sizeLayout);
        materialLayout = findViewById(R.id.materialLayout);

        priceRangeLayout.setVisibility(View.GONE);
        colorLayout.setVisibility(View.GONE);
        sizeLayout.setVisibility(View.GONE);
        materialLayout.setVisibility(View.GONE);


        priceRangeFromTitleValueTextView = findViewById(R.id.priceRangeFromTitleValueTextView);
        priceRangeToTitleValueTextView = findViewById(R.id.priceRangeToTitleValueTextView);
        priceRangeFromValueTextView = findViewById(R.id.priceRangeFromValueTextView);
        priceRangeToValueTextView = findViewById(R.id.priceRangeToValueTextView);


        int maxValue2 = 100;
        seekBar = new RangeSeekBar<>(0, maxValue2, this);

        seekBar.setSelectedMinValue(0);
        seekBar.setAbsoluteMaxValue(100);

        LinearLayout linearLayout = findViewById(R.id.price_range_bar_container);
        linearLayout.addView(seekBar);

        ImageView color1BgImageView = findViewById(R.id.color1BgImageView);
        ImageView color2BgImageView = findViewById(R.id.color2BgImageView);
        ImageView color3BgImageView = findViewById(R.id.color3BgImageView);
        ImageView color4BgImageView = findViewById(R.id.color4BgImageView);
        ImageView color5BgImageView = findViewById(R.id.color5BgImageView);
        ImageView color6BgImageView = findViewById(R.id.color6BgImageView);
        ImageView color7BgImageView = findViewById(R.id.color7BgImageView);

        setDefaultCircleImage(color1BgImageView, R.color.md_white_1000);
        setDefaultCircleImage(color2BgImageView, R.color.md_grey_400);
        setDefaultCircleImage(color3BgImageView, R.color.md_yellow_400);
        setDefaultCircleImage(color4BgImageView, R.color.md_green_500);
        setDefaultCircleImage(color5BgImageView, R.color.md_green_900);
        setDefaultCircleImage(color6BgImageView, R.color.md_blue_500);
        setDefaultCircleImage(color7BgImageView, R.color.md_black_1000);

        color1ImageView = findViewById(R.id.color1ImageView);
        color2ImageView = findViewById(R.id.color2ImageView);
        color3ImageView = findViewById(R.id.color3ImageView);
        color4ImageView = findViewById(R.id.color4ImageView);
        color5ImageView = findViewById(R.id.color5ImageView);
        color6ImageView = findViewById(R.id.color6ImageView);
        color7ImageView = findViewById(R.id.color7ImageView);

        Drawable selectedList = getApplicationContext().getResources().getDrawable(R.drawable.baseline_selected_list_24);
        selectedList.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        size1BgImageView = findViewById(R.id.size1BgImageView);
        size2BgImageView = findViewById(R.id.size2BgImageView);
        size3BgImageView = findViewById(R.id.size3BgImageView);
        size4BgImageView = findViewById(R.id.size4BgImageView);
        size5BgImageView = findViewById(R.id.size5BgImageView);

        setDefaultCircleImage(size1BgImageView, R.color.md_grey_400);
        setDefaultCircleImage(size2BgImageView, R.color.md_grey_400);
        setDefaultCircleImage(size3BgImageView, R.color.md_grey_400);
        setDefaultCircleImage(size4BgImageView, R.color.md_grey_400);
        setDefaultCircleImage(size5BgImageView, R.color.md_grey_400);

        size1TextView = findViewById(R.id.size1TextView);
        size2TextView = findViewById(R.id.size2TextView);
       // size3TextView = findViewById(R.id.size3TextView);
        size4TextView = findViewById(R.id.size4TextView);
        size5TextView = findViewById(R.id.size5TextView);

        sizeTitleValueTextView = findViewById(R.id.sizeTitleValueTextView);
        colorTitleValueTextView = findViewById(R.id.colorTitleValueTextView);


        // Set Color Default
        color1ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
        color1Status = true;
        updateColorTitle();

        // Set Size Default
        //setSelectUnSelectSizeFilter(size3BgImageView, R.color.colorPrimary, size3TextView, R.color.md_white_1000);
       // size3Status = true;
       // updateSizeTitle();

        // Set Material Default
        Button material1Button = findViewById(R.id.material1Button);
        material1Button.setSelected(true);

    }

    private void setDefaultCircleImage(ImageView imageView, int color) {
        Utils.setCircleImageToImageView(getApplicationContext(), imageView, R.drawable.white_background, 0, 0);
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

    private void initDataBinding() {

    }

    private void initActions() {

        seekBar.setOnRangeSeekBarChangeListener((bar, minValue, maxValue) -> {

            Log.d("TEAMPS", "initUI: " + minValue + " : " + maxValue);

            String minStr = "$ " + minValue;
            String maxStr = "$ " + maxValue;
            priceRangeFromTitleValueTextView.setText(minStr);
            priceRangeFromValueTextView.setText(minStr);
            priceRangeToTitleValueTextView.setText(maxStr);
            priceRangeToValueTextView.setText(maxStr);

        });

        priceRangeUpDownImageView.setOnClickListener((View v) -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtils.expand(priceRangeLayout);
            } else {
                ViewAnimationUtils.collapse(priceRangeLayout);
            }
        });

        sizeUpDownImageView.setOnClickListener((View v) -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtils.expand(sizeLayout);
            } else {
                ViewAnimationUtils.collapse(sizeLayout);
            }
        });

        colorUpDownImageView.setOnClickListener((View v) -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtils.expand(colorLayout);
            } else {
                ViewAnimationUtils.collapse(colorLayout);
            }
        });

        materialUpDownImageView.setOnClickListener((View v) -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtils.expand(materialLayout);
            } else {
                ViewAnimationUtils.collapse(materialLayout);
            }
        });


        //region Size
        size1TextView.setOnClickListener((View v) -> {
            if (size1Status) {
                setSelectUnSelectSizeFilter(size1BgImageView, R.color.md_grey_400, size1TextView, R.color.md_grey_800);
                size1Status = false;
            } else {
                setSelectUnSelectSizeFilter(size1BgImageView, R.color.colorPrimary, size1TextView, R.color.md_white_1000);
                size1Status = true;
            }
            updateSizeTitle();
        });

        size2TextView.setOnClickListener((View v) -> {
            if (size2Status) {
                setSelectUnSelectSizeFilter(size2BgImageView, R.color.md_grey_400, size2TextView, R.color.md_grey_800);
                size2Status = false;
            } else {
                setSelectUnSelectSizeFilter(size2BgImageView, R.color.colorPrimary, size2TextView, R.color.md_white_1000);
                size2Status = true;
            }
            updateSizeTitle();
        });

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

        size4TextView.setOnClickListener((View v) -> {
            if (size4Status) {
                setSelectUnSelectSizeFilter(size4BgImageView, R.color.md_grey_400, size4TextView, R.color.md_grey_800);
                size4Status = false;
            } else {
                setSelectUnSelectSizeFilter(size4BgImageView, R.color.colorPrimary, size4TextView, R.color.md_white_1000);
                size4Status = true;
            }
            updateSizeTitle();
        });

        size5TextView.setOnClickListener((View v) -> {
            if (size5Status) {
                setSelectUnSelectSizeFilter(size5BgImageView, R.color.md_grey_400, size5TextView, R.color.md_grey_800);
                size5Status = false;
            } else {
                setSelectUnSelectSizeFilter(size5BgImageView, R.color.colorPrimary, size5TextView, R.color.md_white_1000);
                size5Status = true;
            }
            updateSizeTitle();
        });

        //endregion

        //region Color

        color1ImageView.setOnClickListener((View v) -> {
            if (color1Status) {
                color1ImageView.setImageDrawable(null);
                color1Status = false;
            } else {
                color1ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color1Status = true;
            }
            updateColorTitle();
        });

        color2ImageView.setOnClickListener((View v) -> {
            if (color2Status) {
                color2ImageView.setImageDrawable(null);
                color2Status = false;
            } else {
                color2ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color2Status = true;
            }
            updateColorTitle();
        });

        color3ImageView.setOnClickListener((View v) -> {
            if (color3Status) {
                color3ImageView.setImageDrawable(null);
                color3Status = false;
            } else {
                color3ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color3Status = true;
            }
            updateColorTitle();
        });

        color4ImageView.setOnClickListener((View v) -> {
            if (color4Status) {
                color4ImageView.setImageDrawable(null);
                color4Status = false;
            } else {
                color4ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color4Status = true;
            }
            updateColorTitle();
        });

        color5ImageView.setOnClickListener((View v) -> {
            if (color5Status) {
                color5ImageView.setImageDrawable(null);
                color5Status = false;
            } else {
                color5ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color5Status = true;
            }
            updateColorTitle();
        });

        color6ImageView.setOnClickListener((View v) -> {
            if (color6Status) {
                color6ImageView.setImageDrawable(null);
                color6Status = false;
            } else {
                color6ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color6Status = true;
            }
            updateColorTitle();
        });

        color7ImageView.setOnClickListener((View v) -> {
            if (color7Status) {
                color7ImageView.setImageDrawable(null);
                color7Status = false;
            } else {
                color7ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color7Status = true;
            }
            updateColorTitle();
        });

        //endregion


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

        colorTitleValueTextView.setText(result);

    }

    private void updateSizeTitle() {
        String value = "";

        if (size1Status) {
            value = size1TextView.getText().toString();
        }

        if (size2Status) {
            if (value.equals("")) {
                value += size2TextView.getText().toString();
            } else {
                value += ", " + size2TextView.getText().toString();
            }
        }

//        if (size3Status) {
//            if (value.equals("")) {
//                value += size3TextView.getText().toString();
//            } else {
//                value += ", " + size3TextView.getText().toString();
//            }
//        }

        if (size4Status) {
            if (value.equals("")) {
                value += size4TextView.getText().toString();
            } else {
                value += ", " + size4TextView.getText().toString();
            }
        }

        if (size5Status) {
            if (value.equals("")) {
                value += size5TextView.getText().toString();
            } else {
                value += ", " + size5TextView.getText().toString();
            }
        }

        if (value.equals("")) {
            value = "Not Set.";
        }
        sizeTitleValueTextView.setText(value);
    }

    public void clickMaterial(View view) {
        if (view != null && view instanceof Button) {
            view.setSelected(!view.isSelected());
        }
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Expandable 1");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        } catch (Exception e) {
            Log.e("TEAMPS", "Can't set color.");
        }

        try {
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set display home as up enabled.");
        }

    }

}