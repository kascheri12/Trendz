package com.trendzcatalog.trendz.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tobishiba.circularviewpager.library.BaseCircularViewPagerAdapter;
import com.trendzcatalog.trendz.fragments.MyViewPagerItemFragment;
import com.trendzcatalog.trendz.models.MyItem;

import java.util.List;


/**
 * Created by kennethascheri on 10/22/15.
 */
public class MyCircularViewPagerAdapter extends BaseCircularViewPagerAdapter<MyItem> {
    private final Context mContext;
    public MyCircularViewPagerAdapter(final Context context, final FragmentManager fragmentManager, final List<MyItem> myItemList) {
        super(fragmentManager, myItemList);
        mContext = context;
    }

    @Override
    public Fragment getFragmentForItem(final MyItem item) {
        return MyViewPagerItemFragment.instantiateWithArgs(mContext, item);
    }
}
