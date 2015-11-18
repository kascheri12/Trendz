package com.trendzcatalog.trendz.models;

/**
 * Created by kennethascheri on 11/8/15.
 */
public class ClothingItem   {
    public int imageResId;
    public int titleResId;

    public ClothingItem(int imageResId, int titleResId){
        this.imageResId = imageResId;
        this.titleResId = titleResId;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public enum ClothingItemStyle {
        TOPS
        ,BOTTOMS
        ,SHOES
        ,DRESSES
    }
}
