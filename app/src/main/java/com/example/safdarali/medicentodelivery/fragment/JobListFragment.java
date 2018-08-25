package com.example.safdarali.medicentodelivery.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.safdarali.medicentodelivery.MainActivity;
import com.example.safdarali.medicentodelivery.R;
import com.example.safdarali.medicentodelivery.adapter.JobListAdapter;
import com.example.safdarali.medicentodelivery.data.Constants;
import com.example.safdarali.medicentodelivery.data.DeliveryJob;

import java.util.ArrayList;

public class JobListFragment extends Fragment {


    RecyclerView mPendingJobRv, mCompletedJobRv;
    TextView mPendingJobLabel, mCompletedJobLabel;
    JobListAdapter adapter1, adapter2;

    public JobListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);
        mPendingJobLabel = view.findViewById(R.id.pending_jobs_label);
        mCompletedJobLabel = view.findViewById(R.id.completed_jobs_label);
        mPendingJobRv = view.findViewById(R.id.pending_jobs_rv);
        mCompletedJobRv = view.findViewById(R.id.completed_jobs_rv);


        mPendingJobRv.setHasFixedSize(true);
        mPendingJobRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mPendingJobRv.setAdapter(adapter1);

        mCompletedJobRv.setHasFixedSize(true);
        mCompletedJobRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCompletedJobRv.setAdapter(adapter2);

        mPendingJobLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mPendingJobLabel.setTextAppearance(R.style.selected_text_appearnace);
                    mCompletedJobLabel.setTextAppearance(R.style.deselected_text_appearnace);
                } else {
                    mPendingJobLabel.setTextColor(getResources().getColor(R.color.colorWhite));
                    mCompletedJobLabel.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                mCompletedJobRv.setVisibility(View.GONE);
                mPendingJobRv.setVisibility(View.VISIBLE);

            }
        });
        mCompletedJobLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mCompletedJobLabel.setTextAppearance(R.style.selected_text_appearnace);
                    mPendingJobLabel.setTextAppearance(R.style.deselected_text_appearnace);
                } else {
                    mCompletedJobLabel.setTextColor(getResources().getColor(R.color.colorWhite));
                    mPendingJobLabel.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                mPendingJobRv.setVisibility(View.GONE);
                mCompletedJobRv.setVisibility(View.VISIBLE);

            }
        });

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

    public void passOnJobItemClickListener(JobListAdapter.OnJobItemClickListener listener) {
        ArrayList<DeliveryJob> jobs = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            jobs.add(new DeliveryJob("Test Distr", "Test Pharma",
                    2, i * 23.4f, Constants.STATUS_PENDING, i * 30f));
        }


        ArrayList<DeliveryJob> job2 = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            job2.add(new DeliveryJob("Test Distributor " + i, "Test Pharmacy " + i,
                    1, i * 2f, Constants.STATUS_COMPLETED, i * 3f));
        }
        adapter1 = new JobListAdapter(jobs);
        adapter2 = new JobListAdapter(job2);

        adapter1.setOnJobItemClickListener(listener);
        adapter2.setOnJobItemClickListener(listener);
    }
}
