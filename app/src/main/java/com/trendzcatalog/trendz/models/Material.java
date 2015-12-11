package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class Material {
    public int MaterialID;
    public String MaterialName;
    public String MaterialDesc;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public Material(int MaterialID) {
        this.MaterialID = MaterialID;
    }
}
