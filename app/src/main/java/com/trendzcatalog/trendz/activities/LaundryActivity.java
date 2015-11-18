package com.trendzcatalog.trendz.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.fragments.LaundryFragment;

public class LaundryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayoutLaundryActivity, LaundryFragment.newInstance())
                .commit();
    }
}
