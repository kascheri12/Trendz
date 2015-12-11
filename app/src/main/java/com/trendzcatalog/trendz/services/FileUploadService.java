package com.trendzcatalog.trendz.services;

import com.squareup.okhttp.RequestBody;
import com.trendzcatalog.trendz.models.ImageFile;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by kennethascheri on 12/3/15.
 */
public interface FileUploadService {
    @POST("/api/imageFiles/post")
    Call<List<ImageFile>> upload(@Body RequestBody file);
}
