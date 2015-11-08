package com.trendzcatalog.trendz;

import android.os.SystemClock;

import java.util.Random;

/**
 * Created by kennethascheri on 10/25/15.
 */
public class ColorRandomizer {

    private final Random mRandom;
    private final int[] mColors;
    private final int mMax;

    public ColorRandomizer(int[] colors) {
        this.mRandom = new Random(SystemClock.elapsedRealtime());
        this.mColors = colors;
        this.mMax = mColors.length - 1;
    }

    public int next() {
        final int index = mRandom.nextInt(mMax);
        return mColors[index];
    }
}