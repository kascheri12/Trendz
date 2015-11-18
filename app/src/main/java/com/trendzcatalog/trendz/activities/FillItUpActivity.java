package com.trendzcatalog.trendz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.fragments.Camera2BasicFragment;
import com.trendzcatalog.trendz.fragments.CategorizeFragment;

/**
 * Created by kennethascheri on 11/14/15.
 */
public class FillItUpActivity extends FragmentActivity {

    private enum STATE {
        one,two
    };
    private STATE state = STATE.one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (state == STATE.one) {
            state = STATE.two;
            getFragmentManager().beginTransaction()
                    .add(R.id.container_camera_frag, Camera2BasicFragment.newInstance())
                    .commit();
        } else if (state == STATE.two) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container_camera_frag, CategorizeFragment.newInstance(""))
                    .commit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent intent = new Intent(this, MainActivity.class);
                finish();
                startActivity(intent);

            default:
                return super.onKeyDown(keyCode, event);
        }
    }
}
