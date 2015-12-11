package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class ClothingArticle {
    public int ClothingArticleID;
    public int ClosetID;
    public int ImageFileID;
    public ImageFile ImageFile;
    public int StyleTypeID;
    public StyleType StyleType;
    public int SubStyleTypeID;
    public SubStyleType SubStyleType;
    public int MaterialID;
    public Material Material;
    public int ColorID;
    public Color Color;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public ClothingArticle(int ClothingArticleID) {
        this.ClothingArticleID = ClothingArticleID;
    }
}
