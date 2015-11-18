package com.trendzcatalog.trendz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trendzcatalog.trendz.R;

/**
 * Created by kennethascheri on 11/9/15.
 */
public class LaundryFragment extends Fragment {

    public static LaundryFragment newInstance() {
        final Bundle args = new Bundle();
        final LaundryFragment closetFragment = new LaundryFragment();
        closetFragment.setArguments(args);
        return closetFragment;
    }
    public LaundryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_laundry, container, false);
        return view;
    }
}
