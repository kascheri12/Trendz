package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class Combination {
    public int CombinationID;
    public int ClosetID;
    public int ClothingArticleTopID;
    public int ClothingArticleBottomID;
    public int ClothingArticleShoesID;
    public int ClothingArticleDressesID;
    public int ClothingArticleLayerID;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public Combination(int CombinationID) {
        this.CombinationID = CombinationID;
    }
}
