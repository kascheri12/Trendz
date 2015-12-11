package com.trendzcatalog.trendz.models;

/**
 * Created by kennethascheri on 11/8/15.
 */
public class ClothingItem   {
    public int titleResId;
    private String photoURI;

    public ClothingItem(String URI, int titleResId){
        this.titleResId = titleResId;
        this.photoURI = URI;
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

    public String getPhoto() {
        return this.photoURI;
    }
    public void setPhoto(String photo) {
        this.photoURI = photo;
    }
}
