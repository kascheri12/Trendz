package com.trendzcatalog.trendz.activities;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by kennethascheri on 10/25/15.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected <V> V findView(int id) {
        //noinspection unchecked
        return (V) findViewById(id);
    }
}