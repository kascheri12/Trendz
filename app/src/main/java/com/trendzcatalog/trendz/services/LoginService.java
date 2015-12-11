package com.trendzcatalog.trendz.services;

import com.trendzcatalog.trendz.models.UserInfo;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kennethascheri on 12/3/15.
 */
public interface LoginService {
    @GET("/api/login/getvalidate/")
    Call<UserInfo> Validate(@Query("username") String username, @Query("password") String password);

    @GET("/api/login/getcreate/")
    Call<UserInfo> Create(@Query("username") String username, @Query("password") String password);
}
