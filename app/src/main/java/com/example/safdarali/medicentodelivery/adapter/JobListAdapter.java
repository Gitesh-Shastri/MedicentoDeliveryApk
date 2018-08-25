package com.example.safdarali.medicentodelivery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.safdarali.medicentodelivery.R;
import com.example.safdarali.medicentodelivery.data.Constants;
import com.example.safdarali.medicentodelivery.data.DeliveryJob;

import java.util.ArrayList;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobListViewHolder> {


    ArrayList<DeliveryJob> mList;
    OnJobItemClickListener mListener;
    public JobListAdapter (ArrayList<DeliveryJob> list) {
        mList = list;
    }

    public void setOnJobItemClickListener(OnJobItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public JobListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_delivery_job, parent, false);
        return new JobListViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull JobListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class JobListViewHolder extends RecyclerView.ViewHolder {

        TextView mDistributorName, mPharmacyName, mStatus, mSlot, mDistance, mCost;
        ImageView mStatusIcon;
        Context mContext;
        View mView;
        JobListViewHolder(View itemView, Context context) {
            super(itemView);

            mView = itemView;
            mDistributorName = itemView.findViewById(R.id.distributor_name);
            mPharmacyName = itemView.findViewById(R.id.pharmacy_name);
            mSlot = itemView.findViewById(R.id.slot);
            mDistance = itemView.findViewById(R.id.distance);
            mCost = itemView.findViewById(R.id.order_cost);
            mStatus = itemView.findViewById(R.id.status);
            mStatusIcon = itemView.findViewById(R.id.status_icon);
            mContext = context;
        }

        void bind(int pos) {
            final DeliveryJob job = mList.get(pos);
            mView.setTag(pos);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onJobItemClick(job);
                }
            });
            mDistributorName.setText(job.getPickUpPoint());
            mPharmacyName.setText(job.getDeliveryPoint());
            mSlot.setText(job.getSlot());
            mStatus.setText(job.getStatus());
            mDistance.setText(job.getDistance());
            mCost.setText(job.getCost());
            if (job.getStatus().equals(Constants.STATUS_COMPLETED)) {
                mStatusIcon.setBackground(mContext.getDrawable(R.drawable.ic_completed));
            } else if (job.getStatus().equals(Constants.STATUS_PENDING)) {
                mStatusIcon.setBackground(mContext.getDrawable(R.drawable.ic_pending));
            }
        }
    }

    public interface OnJobItemClickListener {
        public void onJobItemClick(DeliveryJob job);
    }
}
