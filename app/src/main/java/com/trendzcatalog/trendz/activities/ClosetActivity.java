package com.trendzcatalog.trendz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.fragments.ClosetFragment;
import com.trendzcatalog.trendz.fragments.NavigationDrawerFragment;

public class ClosetActivity extends FragmentActivity {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, ClosetFragment.newInstance())
                .commit();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mNavigationDrawerFragment.isDrawerOpen()) {
                    return true;
                } else {
                    Intent intent = new Intent(this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }

            default:
                return super.onKeyDown(keyCode, event);
        }
    }

}
