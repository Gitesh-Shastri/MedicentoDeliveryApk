package com.example.safdarali.medicentodelivery.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.safdarali.medicentodelivery.R;
import com.example.safdarali.medicentodelivery.data.DeliveryJob;


public class JobDetailFragment extends Fragment {

    DeliveryJob mDeliveryJob;
    public JobDetailFragment() {
        // Required empty public constructor
    }

    public void setDeliveryJob(DeliveryJob job){
        mDeliveryJob = job;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_detail, container, false);
        TextView pickupPoint, pickupPointAddress, pickupPointInstr, pickupContact,
                deliveryPoint, deliveryPointAddress, deliveryPointInstr, deliveryContact,
                totalDistance, distPickup, distDelivery, estTime;
        pickupPoint = view.findViewById(R.id.pickup_point_value);
        pickupPointAddress = view.findViewById(R.id.pickup_point_address_value);
        pickupPointInstr = view.findViewById(R.id.pickup_point_instruction_value);
        pickupContact = view.findViewById(R.id.pickup_point_contact_value);

        deliveryPoint = view.findViewById(R.id.delivery_point_value);
        deliveryPointAddress = view.findViewById(R.id.delivery_point_address_value);
        deliveryPointInstr = view.findViewById(R.id.delivery_point_instruction_value);
        deliveryContact = view.findViewById(R.id.delivery_point_contact_value);

        distPickup = view.findViewById(R.id.dist_pickup_value);
        distDelivery = view.findViewById(R.id.dist_delivery_value);
        totalDistance = view.findViewById(R.id.total_dist_value);
        estTime = view.findViewById(R.id.est_time_value);

        pickupPoint.setText(mDeliveryJob.getPickUpPoint());
        deliveryPoint.setText(mDeliveryJob.getDeliveryPoint());
        totalDistance.setText(mDeliveryJob.getDistance());

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
}
