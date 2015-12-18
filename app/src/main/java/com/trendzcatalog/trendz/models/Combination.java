package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class Combination {
    public int CombinationID;
    public int ClosetID;
    public Closet Closet;
    public int ClothingArticleTopID;
    public ClothingArticle ClothingArticleTop;
    public int ClothingArticleBottomID;
    public ClothingArticle ClothingArticleBottom;
    public int ClothingArticleShoesID;
    public ClothingArticle ClothingArticleShoes;
    public int ClothingArticleDressesID;
    public ClothingArticle ClothingArticleDress;
    public int ClothingArticleLayerID;
    public ClothingArticle ClothingArticleLayer;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public Combination(int CombinationID) {
        this.CombinationID = CombinationID;
    }
    public Combination(int CombinationsID, int ClosetID, int TopID, int BottomID, int ShoesID, int DressID, int LayerID) {
        this.CombinationID = CombinationsID;
        this.ClosetID = ClosetID;
        this.ClothingArticleTopID = TopID;
        this.ClothingArticleBottomID = BottomID;
        this.ClothingArticleShoesID = ShoesID;
        this.ClothingArticleDressesID = DressID;
        this.ClothingArticleLayerID = LayerID;
    }
}
