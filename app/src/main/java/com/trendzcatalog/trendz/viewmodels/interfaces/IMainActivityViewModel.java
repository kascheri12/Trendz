package com.trendzcatalog.trendz.viewmodels.interfaces;

import com.trendzcatalog.trendz.models.ClothingArticle;
import com.trendzcatalog.trendz.models.ImageFile;

import java.util.List;

/**
 * Created by kennethascheri on 11/20/15.
 */
public interface IMainActivityViewModel {

    void uploadImageFiles(List<ImageFile> items, ClothingArticle ca);

}
