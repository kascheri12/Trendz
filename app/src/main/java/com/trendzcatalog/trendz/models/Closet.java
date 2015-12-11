package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class Closet {
    public int ClosetID;
    public String Description;
    public int OwnerID;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public Closet(int ClosetID) {
        this.ClosetID = ClosetID;
    }
}
