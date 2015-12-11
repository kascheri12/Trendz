package com.trendzcatalog.trendz.fragments;

//import android.support.v4.app.Fragment;

//import android.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.activities.ClosetActivity;
import com.trendzcatalog.trendz.activities.FillItUpActivity;
import com.trendzcatalog.trendz.activities.LaundryActivity;
import com.trendzcatalog.trendz.adapters.DashboardAdapter;
import com.trendzcatalog.trendz.models.DashboardItem;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    GridView gridView;

    public static DashboardFragment newInstance() {
        final Bundle bundle = new Bundle();
        final DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

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
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        initButtons(view);

        gridView = (GridView) view.findViewById(R.id.gridview1);
        Resources res = getActivity().getResources();
        ArrayList<DashboardItem> dashboardItems = DashboardItem.getDashboardItems();
        gridView.setAdapter(new DashboardAdapter(getContext(), dashboardItems));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent fillItUpIntent = new Intent(getActivity(), FillItUpActivity.class);
                        getActivity().finish();
                        startActivity(fillItUpIntent);
                        break;
                    case 1:
                        Intent ClosetIntent = new Intent(getActivity(), ClosetActivity.class);
                        getActivity().finish();
                        startActivity(ClosetIntent);
                        break;
                    case 2:
                        Intent laundryIntent = new Intent(getActivity(), LaundryActivity.class);
                        startActivity(laundryIntent);
                        break;
                    case 3:
                        SharedPreferences pref = getContext().getSharedPreferences("UserInfo", 0);
                        SharedPreferences.Editor edit = pref.edit();
                        edit.clear();
                        edit.commit();
                        Toast.makeText(getContext(), "Logged out successfully.", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                        break;
                    default:
                        Toast.makeText(getContext(), "HI", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
