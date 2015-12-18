package com.trendzcatalog.trendz.services;

import com.trendzcatalog.trendz.models.Closet;
import com.trendzcatalog.trendz.models.ClothingArticle;
import com.trendzcatalog.trendz.models.Combination;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kennethascheri on 12/5/15.
 */
public interface ClosetService {
    @GET("/api/closet/getClosetByUserID")
    Call<Closet> GetClosetByUserID(@Query("userid") int userid);

    @GET("/api/closet/getclothes")
    Call<List<ClothingArticle>> GetClothes(@Query("userid") String userid, @Query("styletypeid") String styletypeid);

    @GET("/api/closet/getCombinationsByUserID")
    Call<List<Combination>> GetCombinations(@Query("UserID") String userid);

    @GET("/api/closet/getSaveCombination")
    Call<Combination> SaveCombination(
            @Query("CombinationID") int CombinationID,
            @Query("ClosetID") int ClosetID,
            @Query("TopID") int TopID,
            @Query("BottomID") int BottomID,
            @Query("ShoesID") int ShoesID,
            @Query("DressID") int DressID,
            @Query("LayerID") int LayerID);

    @GET("/api/closet/getClothingArticle")
    Call<ClothingArticle> GetClothingArticle(@Query("id") String id);

    @GET("/api/closet/getsaveClothingArticle")
    Call<ClothingArticle> SaveClothingArticle(
            @Query("caid") int caid,
            @Query("clid") int clid,
            @Query("imgid") int imgid,
            @Query("styleid") int styleid,
            @Query("sstyleid") int sstyleid,
            @Query("matid") int matid,
            @Query("colorid") int colorid);

}
