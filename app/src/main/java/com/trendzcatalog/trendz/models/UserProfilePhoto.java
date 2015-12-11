package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class UserProfilePhoto {
    public int UserProfilePhotoID;
    public int UserID;
    public int ImageFileID;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public UserProfilePhoto(int UserProfilePhotoID) {
        this.UserProfilePhotoID = UserProfilePhotoID;
    }
}
