package com.trendzcatalog.trendz.adapters;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.models.ClothingItem;
import com.trendzcatalog.trendz.models.CoverFlow;

import java.io.FileInputStream;

/**
 * Created by kennethascheri on 11/12/15.
 */

public class ImageAdapter extends BaseAdapter {
    int mGalleryItemBackground;
    ClothingItem.ClothingItemStyle mClothingItemStyle;
    private Context mContext;

    private FileInputStream fis;

    private Integer[] mTopImageIds = {
            R.drawable.top01,
            R.drawable.top02,
            R.drawable.top03,
            R.drawable.top04,
            R.drawable.top05,
            R.drawable.top06,
    };
    private Integer[] mBottomImageIds = {
            R.drawable.bottom01,
            R.drawable.bottom02,
            R.drawable.bottom03
    };
    private Integer[] mShoesImageIds = {
            R.drawable.shoes01,
            R.drawable.shoes02,
            R.drawable.shoes03,
            R.drawable.shoes04,
            R.drawable.shoes05,
    };

    private ImageView[] mImages;

    public ImageAdapter(Context c, ClothingItem.ClothingItemStyle clothingItemStyle) {
        mContext = c;
        mClothingItemStyle = clothingItemStyle;
        switch (clothingItemStyle) {
            case TOPS:
                mImages = new ImageView[mTopImageIds.length];
                break;
            case BOTTOMS:
                mImages = new ImageView[mBottomImageIds.length];
                break;
            case SHOES:
                mImages = new ImageView[mShoesImageIds.length];
                break;
            default:
                break;
        }
    }
    public int getCount() {
        switch (mClothingItemStyle) {
            case TOPS:
                return mTopImageIds.length;
            case BOTTOMS:
                return mBottomImageIds.length;
            case SHOES:
                return mShoesImageIds.length;
            default:
                return 0;
        }
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        //Use this code if you want to load from resources
        ImageView i = new ImageView(mContext);
        switch (mClothingItemStyle) {
            case TOPS:
                i.setImageResource(mTopImageIds[position]);
                break;
            case BOTTOMS:
                i.setImageResource(mBottomImageIds[position]);
                break;
            case SHOES:
                i.setImageResource(mShoesImageIds[position]);
                break;
            default:
                break;
        }
        i.setLayoutParams(new CoverFlow.LayoutParams(130, 130));
        i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        //Make sure we set anti-aliasing otherwise we get jaggies
        BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
        drawable.setAntiAlias(true);
        return i;

        //return mImages[position];
    }
    /** Returns the size (0.0f to 1.0f) of the views
     * depending on the 'offset' to the center. */
    public float getScale(boolean focused, int offset) {
        /* Formula: 1 / (2 ^ offset) */
        return Math.max(0, 0.1f / (float)Math.pow(2, Math.abs(offset)));
    }
}
