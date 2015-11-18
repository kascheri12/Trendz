package com.trendzcatalog.trendz.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.adapters.LayerDrawerAdapter;
import com.trendzcatalog.trendz.models.ClothingItem;

import java.util.ArrayList;

/**
 * Created by kennethascheri on 11/9/15.
 */
public class PlaceholderFragment extends Fragment {

    public static PlaceholderFragment newInstance() {
        PlaceholderFragment fragment = new PlaceholderFragment();
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main_nav_drawer, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);

        Resources res = getActivity().getResources();

        ArrayList<ClothingItem> layers = new ArrayList<ClothingItem>();
        layers.add(new ClothingItem(R.drawable.ic_closet_01, R.string.title2 ));
        layers.add(new ClothingItem(R.drawable.ic_closet_1, R.string.title2));
        layers.add(new ClothingItem(R.drawable.ic_closet_2, R.string.title2));
        layers.add(new ClothingItem(R.drawable.ic_publish_01, R.string.title1));
        layers.add(new ClothingItem(R.drawable.ic_publishit_1, R.string.title3));
        layers.add(new ClothingItem(R.drawable.ic_pub_1, R.string.title1));

        gridView.setAdapter(new LayerDrawerAdapter(getActivity(), layers));

        return rootView;
    }
}