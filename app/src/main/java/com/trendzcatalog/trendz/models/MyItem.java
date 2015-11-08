package com.trendzcatalog.trendz.models;

import com.trendzcatalog.trendz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kennethascheri on 10/22/15.
 */
public class MyItem {
    public String   mTitle;
    public int      mImageResourceId;

    public static List<MyItem> createSampleMemes() {
        final List<MyItem> memes = new ArrayList<MyItem>();
        memes.add(new MyItem(R.id.AFrameLayout, "0"));
        memes.add(new MyItem(R.id.BFrameLayout, "1"));
        memes.add(new MyItem(R.id.content_frame, "2"));
        return memes;
    }

    public MyItem(final int imageResourceId, final String title) {
        mImageResourceId = imageResourceId;
        mTitle = title;
    }
}
