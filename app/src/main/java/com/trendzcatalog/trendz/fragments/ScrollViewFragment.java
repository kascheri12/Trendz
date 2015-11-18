package com.trendzcatalog.trendz.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.adapters.BaseListAdapter;
import com.trendzcatalog.trendz.layouts.LinearListView;

/**
 * Created by kennethascheri on 10/25/15.
 */
public class ScrollViewFragment extends BaseFragment {

    public static ScrollViewFragment newInstance() {
        final Bundle bundle = new Bundle();
        final ScrollViewFragment fragment = new ScrollViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private ScrollView mScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {

        final View view = inflater.inflate(R.layout.fragment_scroll_view, parent, false);

        mScrollView = findView(view, R.id.scroll_view);
        final LinearListView linearListView = findView(view, R.id.linear_list_view);
        final BaseListAdapter adapter = new BaseListAdapter(getActivity(), 30);
        linearListView.setAdapter(adapter);

        return view;
    }

    @Override
    public CharSequence getTitle(Resources r) {
        return null;
    }

    @Override
    public String getSelfTag() {
        return null;
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return mScrollView != null && mScrollView.canScrollVertically(direction);
    }
}
