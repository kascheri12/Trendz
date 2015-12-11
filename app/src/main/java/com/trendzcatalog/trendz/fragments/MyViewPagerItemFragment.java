package com.trendzcatalog.trendz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.models.MyItem;

/**
 * Created by kennethascheri on 10/22/15.
 */
public class MyViewPagerItemFragment extends Fragment {
    private static final String BUNDLE_KEY_IMAGE_RESOURCE_ID    = "bundle_key_image_resource_id";
    private static final String BUNDLE_KEY_TITLE                = "bundle_key_title";
    private int                 mImageResourceId;
    private String              mTitle;

    public static MyViewPagerItemFragment instantiateWithArgs(final Context context, final MyItem item) {
        final MyViewPagerItemFragment fragment = (MyViewPagerItemFragment) instantiate(context, MyViewPagerItemFragment.class.getName());
        final Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_IMAGE_RESOURCE_ID, item.mImageResourceId);
        args.putString(BUNDLE_KEY_TITLE, item.mTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArguments();
//        initFragments();


    }

    private void initArguments() {
        final Bundle arguments = getArguments();
        if(arguments != null) {
            mImageResourceId = arguments.getInt(BUNDLE_KEY_IMAGE_RESOURCE_ID);
            mTitle = arguments.getString(BUNDLE_KEY_TITLE);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view, savedInstanceState);

        return view;
    }

    private void initViews(final View view, final Bundle bundle) {
        initLayouts(view, bundle);
    }

    private void initLayouts(final View view, final Bundle bundle) {
        final FrameLayout aFrameLayout, bFrameLayout, content_frame;

        aFrameLayout = (FrameLayout) view.findViewById(R.id.AFrameLayoutWrapper);
        bFrameLayout = (FrameLayout) view.findViewById(R.id.BFrameLayout);
        content_frame = (FrameLayout) view.findViewById(R.id.content_frame);

        if (mTitle == "0") {
            if (bundle == null) {
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.AFrameLayoutWrapper, DashboardFragment.newInstance())
                        .commit();
            }
            aFrameLayout.setVisibility(View.VISIBLE);
        } else if (mTitle == "1") {
            if (bundle == null) {
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.BFrameLayout, LoveItFragment.newInstance())
                        .commit();
            }
            bFrameLayout.setVisibility(View.VISIBLE);
        } else if (mTitle == "2" ) {
            if (bundle == null) {
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.content_frame, ScrollableFragment.newInstance())
                        .commit();
            }
            content_frame.setVisibility(View.VISIBLE);
        }
    }

}
