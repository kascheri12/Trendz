package com.trendzcatalog.trendz.activities;

/**
 * Created by kennethascheri on 10/24/15.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.manuelpeinado.glassactionbar.GlassActionBar;
import com.manuelpeinado.glassactionbar.GlassActionBarHelper;
import com.trendzcatalog.trendz.R;


public class SettingsActivity extends AppCompatActivity {

    private GlassActionBarHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new GlassActionBarHelper().contentLayout(R.layout.activity_settings);
        setContentView(helper.createView(this));

        helper.setBlurRadius(GlassActionBar.DEFAULT_BLUR_RADIUS);
        helper.setDownsampling(GlassActionBar.DEFAULT_DOWNSAMPLING);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}