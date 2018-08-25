package com.example.safdarali.medicentodelivery.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safdarali.medicentodelivery.R;
import com.example.safdarali.medicentodelivery.adapter.AreaDeliveriesAdapter;
import com.example.safdarali.medicentodelivery.data.AreaDeliveries;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    RecyclerView mRecyclerView;
    ArrayList<AreaDeliveries> mList;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mRecyclerView = view.findViewById(R.id.area_delivery_no_rv);
        mRecyclerView.setAdapter(new AreaDeliveriesAdapter(mList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void func(ArrayList<AreaDeliveries> list) {
        mList = list;
    }
}
