package com.webingate.paysmartbusinessapp.fragment.businessAppFragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webingate.paysmartbusinessapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class businessappBasicStepperActivity extends Fragment {

    private int position;

    public businessappBasicStepperActivity() {
        // Required empty public constructor
    }

    public static businessappBasicStepperActivity newInstance(int position) {
        businessappBasicStepperActivity fragment = new businessappBasicStepperActivity();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("POSITION", position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments() != null ? getArguments().getInt("POSITION") : 0;


    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.businessapp_basicstepper_fragment, container, false);

        TextView stepperTextView = view.findViewById(R.id.stepperTextView);

        stepperTextView.setText(String.valueOf(position));

        return view;
    }

}
