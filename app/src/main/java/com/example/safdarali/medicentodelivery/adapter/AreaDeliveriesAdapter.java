package com.example.safdarali.medicentodelivery.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.safdarali.medicentodelivery.R;
import com.example.safdarali.medicentodelivery.data.AreaDeliveries;

import java.util.ArrayList;

public class AreaDeliveriesAdapter extends RecyclerView.Adapter<AreaDeliveriesAdapter.CustomViewHolder> {

    ArrayList<AreaDeliveries> mList;
    public AreaDeliveriesAdapter(ArrayList<AreaDeliveries> list) {
        mList = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_area_deliveries_no, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView mArea, mNoOfDeliveries;
        public CustomViewHolder(View itemView) {
            super(itemView);
            mArea = itemView.findViewById(R.id.area_name);
            mNoOfDeliveries = itemView.findViewById(R.id.no_of_deliveries);
        }

        public void bind(int pos) {
            mArea.setText(mList.get(pos).getAreaName());
            mNoOfDeliveries.setText(mList.get(pos).getNoOfDeliveries());
        }
    }
}
