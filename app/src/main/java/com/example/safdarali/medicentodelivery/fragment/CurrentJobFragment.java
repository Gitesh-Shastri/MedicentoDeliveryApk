package com.example.safdarali.medicentodelivery.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safdarali.medicentodelivery.R;


public class CurrentJobFragment extends Fragment {


    BottomSheetBehavior mBottomSheetBehavior;
    public CurrentJobFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currentjob, container, false);
        View bottomView = view.findViewById(R.id.bottom_sheet);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomView);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setPeekHeight(100);
        return view;
    }

    public void onButtonPressed(Uri uri) {
   }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
