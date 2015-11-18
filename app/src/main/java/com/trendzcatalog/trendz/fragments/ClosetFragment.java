package com.trendzcatalog.trendz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.adapters.ImageAdapter;
import com.trendzcatalog.trendz.models.ClothingItem;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

/**
 *  Created by kennethascheri on 11/9/15.
 */
public class ClosetFragment extends Fragment {
    private FeatureCoverFlow mCoverFlowTop;
    private FeatureCoverFlow mCoverFlowMiddle;
    private FeatureCoverFlow mCoverFlowBottom;
    private int mCoverFlowTopPos;
    private int mCoverFlowMiddlePos;
    private int mCoverFlowBottomPos;
    private ImageAdapter mAdapterTop;
    private ImageAdapter mAdapterMiddle;
    private ImageAdapter mAdapterBottom;

    public static ClosetFragment newInstance() {
        final Bundle args = new Bundle();
        final ClosetFragment closetFragment = new ClosetFragment();
        closetFragment.setArguments(args);
        return closetFragment;
    }
    public ClosetFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_closet, container, false);

        ImageView btn = (ImageView) view.findViewById(R.id.btnButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = String.valueOf(mCoverFlowTopPos) + " " + String.valueOf(mCoverFlowMiddlePos) + " " + String.valueOf(mCoverFlowBottomPos);
                Toast.makeText(getActivity(),
                        " -- " + str + " -- ", Toast.LENGTH_SHORT).show();
            }
        });

        initFlows(view);

        return view;
    }

    private void initFlows(View view) {

        mAdapterTop = new ImageAdapter(getContext(), ClothingItem.ClothingItemStyle.TOPS);
        mAdapterMiddle = new ImageAdapter(getContext(), ClothingItem.ClothingItemStyle.BOTTOMS);
        mAdapterBottom = new ImageAdapter(getContext(), ClothingItem.ClothingItemStyle.SHOES);
        mCoverFlowTop = (FeatureCoverFlow) view.findViewById(R.id.coverflowtop);
        mCoverFlowMiddle = (FeatureCoverFlow) view.findViewById(R.id.coverflowmiddle);
        mCoverFlowBottom = (FeatureCoverFlow) view.findViewById(R.id.coverflowbottom);

        mCoverFlowTop.setAdapter(mAdapterTop);
        mCoverFlowMiddle.setAdapter(mAdapterMiddle);
        mCoverFlowBottom.setAdapter(mAdapterBottom);
        mCoverFlowTop.setReflectionOpacity(0);
        mCoverFlowMiddle.setReflectionOpacity(0);
        mCoverFlowBottom.setReflectionOpacity(0);
        mCoverFlowTop.setMaxScaleFactor(1.8f);
        mCoverFlowMiddle.setMaxScaleFactor(1.8f);
        mCoverFlowBottom.setMaxScaleFactor(1.5f);

        mCoverFlowTop.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mCoverFlowTopPos = position;
            }

            @Override
            public void onScrolling() {
            }
        });
        mCoverFlowMiddle.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mCoverFlowMiddlePos = position;
            }

            @Override
            public void onScrolling() {

            }
        });
        mCoverFlowBottom.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mCoverFlowBottomPos = position;
            }

            @Override
            public void onScrolling() {

            }
        });
    }
}
