package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/29/15.
 */
public class ImageFile {
    private int ImageFileID;
    private String ImageFileTitle;
    private String ImageFileDesc;
    private String ImageFileLocation;
    private Date DateCreated;
    private Date LastModified;
    private int UpdatedByID;
    private boolean Active;

    public ImageFile(int ImageFileID, String ImageFileTitle, String ImageFileLocation) {
        this.ImageFileID = ImageFileID;
        this.ImageFileTitle = ImageFileTitle;
        this.ImageFileLocation = ImageFileLocation;
    }

    public int getImageFileID() {
        return this.ImageFileID;
    }
    public void setImageFileID(int ImageFileID) { this.ImageFileID = ImageFileID; }
    public String getImageFileTitle() { return this.ImageFileTitle; }
    public void setImageFileTitle(String ImageFileTitle) { this.ImageFileTitle = ImageFileTitle; }
    public String getImageFileDesc() { return this.ImageFileDesc; }
    public void setImageFileDesc(String ImageFileDesc) { this.ImageFileDesc = ImageFileDesc; }
    public String getImageFileLocation() { return this.ImageFileLocation; }
    public void setImageFileLocation(String ImageFileLocation) { this.ImageFileLocation = ImageFileLocation; }
    public Date getDateCreated() { return this.DateCreated; }
    public void setDateCreated(Date DateCreated) { this.DateCreated = DateCreated; }
    public Date getLastModified() { return this.LastModified; }
    public void setLastModified(Date LastModified) { this.LastModified = LastModified; }
    public int getUpdatedByID() { return this.UpdatedByID; }
    public void setUpdatedByID(int UpdatedByID) { this.UpdatedByID = UpdatedByID; }
    public boolean getActive() { return this.Active; }
    public void setActive(boolean Active) { this.Active = Active; }
}
