package com.trendzcatalog.trendz.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.tobishiba.circularviewpager.library.CircularViewPagerHandler;
import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.adapters.MyCircularViewPagerAdapter;
import com.trendzcatalog.trendz.models.MyItem;

//import android.support.v4.app.FragmentActivity;
//import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setBackgroundColor(Color.TRANSPARENT);
        viewPager.setAdapter(new MyCircularViewPagerAdapter(this, getSupportFragmentManager(), MyItem.createSampleMemes()));
        final CircularViewPagerHandler circularViewPagerHandler = new CircularViewPagerHandler(viewPager);
        viewPager.addOnPageChangeListener(circularViewPagerHandler);
    }
}
