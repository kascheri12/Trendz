package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class ImageFileType {
    public int ImageFileTypeID;
    public String ImageFileTypeName;
    public String ImageFileTypeDesc;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public ImageFileType(int ImageFileTypeID) {
        this.ImageFileTypeID = ImageFileTypeID;
    }
}
