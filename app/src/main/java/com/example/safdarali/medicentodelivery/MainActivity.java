package com.example.safdarali.medicentodelivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.safdarali.medicentodelivery.adapter.JobListAdapter;
import com.example.safdarali.medicentodelivery.data.AreaDeliveries;
import com.example.safdarali.medicentodelivery.data.Constants;
import com.example.safdarali.medicentodelivery.data.DeliveryJob;
import com.example.safdarali.medicentodelivery.fragment.CurrentJobFragment;
import com.example.safdarali.medicentodelivery.fragment.DashboardFragment;
import com.example.safdarali.medicentodelivery.fragment.JobDetailFragment;
import com.example.safdarali.medicentodelivery.fragment.JobListFragment;
import com.example.safdarali.medicentodelivery.fragment.ProfileFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, JobListAdapter.OnJobItemClickListener {

    SharedPreferences mSharedPreferences;
    RecyclerView mRecyclerView;
    DashboardFragment mDashboardFragment;
    JobListFragment mJobListFragment;
    ProfileFragment mProfileFragment;
    FragmentManager mFragmentManager;
    CurrentJobFragment mCurrentJobFragment;
    JobDetailFragment mJobDetailFragment;
    FloatingActionButton mCurrentDeliveryBtn;
    Fragment mCurrentFragment;


    @Override
    protected void onResume() {
        super.onResume();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (mSharedPreferences.getString(Constants.EMAIL, "").isEmpty()) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivityForResult(intent, Constants.RC_SIGN_IN);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "We are here", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mCurrentDeliveryBtn = findViewById(R.id.fab);
        mCurrentDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentManager.beginTransaction().replace(R.id.main_container, mCurrentJobFragment).commit();
                mCurrentDeliveryBtn.setVisibility(View.INVISIBLE);
            }
        });


        mDashboardFragment = new DashboardFragment();
        mJobListFragment = new JobListFragment();
        mProfileFragment = new ProfileFragment();
        mCurrentJobFragment = new CurrentJobFragment();

        mJobListFragment.passOnJobItemClickListener(this);
        ArrayList<AreaDeliveries> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new AreaDeliveries("Test Area " + i, i*10 + 10));
        }

        mDashboardFragment.func(list);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.main_container, mDashboardFragment).commit();
        mCurrentFragment = mDashboardFragment;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (mCurrentFragment == mJobDetailFragment) {
            mFragmentManager.beginTransaction().replace(R.id.main_container, mJobListFragment).commit();
            mCurrentFragment = mJobListFragment;
        } else if (mCurrentFragment != mDashboardFragment) {
            mFragmentManager.beginTransaction().replace(R.id.main_container, mDashboardFragment).commit();
            mCurrentFragment = mDashboardFragment;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.dashboard) {
            mFragmentManager.beginTransaction().replace(R.id.main_container, mDashboardFragment).commit();
            mCurrentDeliveryBtn.setVisibility(View.VISIBLE);
            mCurrentFragment = mDashboardFragment;
        } else if (id == R.id.job_list) {
            mFragmentManager.beginTransaction().replace(R.id.main_container, mJobListFragment).commit();
            mCurrentDeliveryBtn.setVisibility(View.VISIBLE);
            mCurrentFragment = mJobListFragment;
        } else if (id == R.id.get_best_job) {
            mFragmentManager.beginTransaction().replace(R.id.main_container, mCurrentJobFragment).commit();
            mCurrentDeliveryBtn.setVisibility(View.INVISIBLE);
            mCurrentFragment = mCurrentJobFragment;
        } else if (id == R.id.sign_out) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(Constants.EMAIL, "");
            editor.apply();
            if (mSharedPreferences.getString(Constants.EMAIL, "").isEmpty()) {
                Intent intent = new Intent(this, SignInActivity.class);
                startActivityForResult(intent, Constants.RC_SIGN_IN);
            }

        } else if (id == R.id.profile) {
            mFragmentManager.beginTransaction().replace(R.id.main_container, mProfileFragment).commit();
            mCurrentDeliveryBtn.setVisibility(View.VISIBLE);
            mCurrentFragment = mProfileFragment;
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "To be implemented...", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onJobItemClick(DeliveryJob job) {
        mJobDetailFragment = new JobDetailFragment();
        mJobDetailFragment.setDeliveryJob(job);
        mFragmentManager.beginTransaction().replace(R.id.main_container, mJobDetailFragment).commit();
        mCurrentFragment = mJobDetailFragment;
    }


}
