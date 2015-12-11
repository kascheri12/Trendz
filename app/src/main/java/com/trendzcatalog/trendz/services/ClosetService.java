package com.trendzcatalog.trendz.services;

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
    @GET("/api/closet/getclothes")
    Call<List<ClothingArticle>> GetClothes(@Query("userid") String userid, @Query("styletypeid") String styletypeid);

    @GET("/api/closet/getCombinations")
    Call<List<Combination>> GetCombinations(@Query("userid") String userid);

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
