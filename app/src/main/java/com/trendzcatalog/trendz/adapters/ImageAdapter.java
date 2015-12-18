package com.trendzcatalog.trendz.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trendzcatalog.trendz.models.ClothingArticle;

import java.util.List;

/**
 * Created by kennethascheri on 11/12/15.
 */

public class ImageAdapter extends BaseAdapter {

    private List<ClothingArticle> mClothes;
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }
    public ImageAdapter(Context c, List<ClothingArticle> clothes) {
        mContext = c;
        mClothes = clothes;
    }
    public void setClothes(List<ClothingArticle> clothes) { mClothes = clothes; }

    public int getCount() {
        return mClothes.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public int getItemClothingArticleID(int position) { return mClothes.get(position).ClothingArticleID; }

    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView i = new ImageView(mContext);
        Picasso.with(mContext).load(mClothes.get(position).ImageFile.getImageFileLocation()).rotate(90).into(i);
        return i;
    }
    /** Returns the size (0.0f to 1.0f) of the views
     * depending on the 'offset' to the center. */
    public float getScale(boolean focused, int offset) {
        /* Formula: 1 / (2 ^ offset) */
        return Math.max(0, 0.1f / (float)Math.pow(2, Math.abs(offset)));
    }
}
