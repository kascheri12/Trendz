package com.trendzcatalog.trendz.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.adapters.BaseListAdapter;

/**
 * Created by kennethascheri on 10/25/15.
 */
public class ListViewFragment extends BaseFragment {

    public static ListViewFragment newInstance() {
        final Bundle bundle = new Bundle();
        final ListViewFragment fragment = new ListViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {

        final View view = inflater.inflate(R.layout.fragment_list_view, parent, false);

        mListView = findView(view, R.id.list_view);
        final BaseListAdapter adapter = new BaseListAdapter(getActivity(), 100);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Click: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return mListView != null && mListView.canScrollVertically(direction);
    }

    @Override
    public CharSequence getTitle(Resources r) {
        return null;
    }

    @Override
    public String getSelfTag() {
        return null;
    }
}
